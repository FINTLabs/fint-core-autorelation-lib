package no.fintlabs.autorelation.cache

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import no.fint.model.FintMultiplicity
import no.fint.model.FintRelation
import no.fintlabs.autorelation.model.EntityDescriptor
import no.fintlabs.metamodel.MetamodelService
import no.fintlabs.metamodel.model.Component
import no.fintlabs.metamodel.model.Resource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.fail

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RelationRuleBuilderTest {
    private val metamodelService: MetamodelService = mockk()
    private val ruleBuilder = RelationRuleBuilder(metamodelService)

    @ParameterizedTest(name = "{0}")
    @MethodSource("successScenarios")
    fun `should build correct rules for valid combinations`(scenario: TestScenario) {
        val sourceResource =
            resource(
                name = scenario.sourceName,
                relations =
                    arrayOf(
                        relation(
                            name = scenario.forwardRelationName,
                            pkg = scenario.targetPackage,
                            multiplicity = scenario.sourceMultiplicity,
                            inverseName = scenario.inverseRelationName,
                        ),
                    ),
            )

        every { metamodelService.getComponents() } returns
            listOf(
                mockComponent(scenario.domain, scenario.pkg, listOf(sourceResource)),
            )

        every { metamodelService.getResource(any(), any(), any()) } returns
            mockk<Resource> {
                every { relations } returns emptyList()
            }

        val result = ruleBuilder.buildEntityDescriptorToRules()

        val expectedKey = EntityDescriptor(scenario.domain, scenario.pkg, scenario.sourceName)
        val rule = result[expectedKey]?.single() ?: fail("Expected one rule for $expectedKey")

        with(rule) {
            assertEquals(scenario.forwardRelationName, targetRelation)
            assertEquals(scenario.inverseRelationName, inverseRelation)
            assertEquals(scenario.expectedTargetType, targetType)
        }
    }

    @Test
    fun `should ignore relation if inverseName is null (Uni-directional)`() {
        val sourceResource =
            resource(
                name = "elev",
                relations =
                    arrayOf(
                        relation(
                            name = "relatedPerson",
                            pkg = "no.fint.model.utdanning.target.person",
                            multiplicity = FintMultiplicity.ONE_TO_MANY,
                            inverseName = null,
                        ),
                    ),
            )

        every { metamodelService.getComponents() } returns
            listOf(
                mockComponent("utdanning", "vurdering", listOf(sourceResource)),
            )

        assertTrue(
            ruleBuilder.buildEntityDescriptorToRules().isEmpty(),
            "Should ignore uni-directional relations",
        )
    }

    @Test
    fun `should ignore relation if source multiplicity is 1-1`() {
        val sourceResource =
            resource(
                name = "elev",
                relations =
                    arrayOf(
                        relation(
                            "relatedPerson",
                            "no.fint.model.utdanning.target.person",
                            FintMultiplicity.ONE_TO_ONE,
                            "studentList",
                        ),
                    ),
            )

        every { metamodelService.getComponents() } returns
            listOf(
                mockComponent("utdanning", "vurdering", listOf(sourceResource)),
            )

        assertTrue(ruleBuilder.buildEntityDescriptorToRules().isEmpty())
    }

    @Test
    fun `should ignore relation if different domain`() {
        val sourceResource =
            resource(
                name = "elev",
                relations =
                    arrayOf(
                        relation("ansatt", "no.fint.model.administrasjon.ansatt", FintMultiplicity.ONE_TO_MANY, "elev"),
                    ),
            )

        every { metamodelService.getComponents() } returns
            listOf(
                mockComponent("utdanning", "vurdering", listOf(sourceResource)),
            )

        assertTrue(ruleBuilder.buildEntityDescriptorToRules().isEmpty())
    }

    private fun successScenarios(): List<TestScenario> {
        val standardPkg = "no.fint.model.utdanning.target.person"
        val commonPkg = "no.fint.model.utdanning.person"

        val standardType = EntityDescriptor("utdanning", "target", "person")
        val commonType = EntityDescriptor("utdanning", "source", "person")

        return listOf(
            TestScenario("Standard: 1-N", FintMultiplicity.ONE_TO_MANY, standardPkg, standardType),
            TestScenario("Standard: 0-N", FintMultiplicity.NONE_TO_MANY, standardPkg, standardType),
            TestScenario("Common Resource", FintMultiplicity.ONE_TO_MANY, commonPkg, commonType),
        )
    }

    data class TestScenario(
        val testName: String,
        val sourceMultiplicity: FintMultiplicity,
        val targetPackage: String,
        val expectedTargetType: EntityDescriptor,
        val domain: String = "utdanning",
        val pkg: String = "source",
        val sourceName: String = "elev",
        val targetName: String = "person",
        val forwardRelationName: String = "contactPerson",
        val inverseRelationName: String = "studentList",
    ) {
        override fun toString() = testName
    }

    private fun mockComponent(
        domain: String,
        pkg: String,
        resourcesList: List<Resource>,
    ): Component =
        spyk(Component(domain, pkg)) {
            every { this@spyk.resources } returns resourcesList
        }

    private fun resource(
        name: String,
        vararg relations: FintRelation,
    ): Resource =
        mockk<Resource> {
            every { this@mockk.name } returns name.lowercase()
            every { this@mockk.relations } returns relations.toList()
        }

    private fun relation(
        name: String,
        pkg: String,
        multiplicity: FintMultiplicity,
        inverseName: String? = null,
    ) = TestRelation(packageName = pkg, multiplicity = multiplicity, name = name, inverseName = inverseName)

    data class TestRelation(
        private val packageName: String,
        private val multiplicity: FintMultiplicity,
        private val name: String,
        private val inverseName: String?,
    ) : FintRelation {
        override fun getPackageName() = packageName

        override fun getMultiplicity() = multiplicity

        override fun getName() = name

        override fun getInverseName() = inverseName
    }
}
