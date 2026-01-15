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
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.fail

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RelationRuleBuilderTest {
    private val metamodelService: MetamodelService = mockk()
    private val ruleBuilder = RelationRuleBuilder(metamodelService)

    @ParameterizedTest(name = "{0}")
    @MethodSource("successScenarios")
    fun `should build correct rules for valid inverse multiplicities`(scenario: TestScenario) {
        configureMocksForScenario(scenario)

        val result = ruleBuilder.buildEntityDescriptorToRules()

        val expectedKey = EntityDescriptor(scenario.domain, scenario.pkg, scenario.sourceName)
        val rule = result[expectedKey]?.single() ?: fail("Expected one rule for $expectedKey")

        with(rule) {
            assertEquals(scenario.forwardRelationName, targetRelation)
            assertEquals(scenario.inverseRelationName, inverseRelation)
            assertEquals(scenario.expectedTargetType, targetType)
            assertEquals(
                scenario.expectedMandatory,
                isMandatory,
                "Wrong mandatory flag for ${scenario.inverseMultiplicity}",
            )
        }
    }

    @ParameterizedTest(name = "Should ignore inverse multiplicity: {0}")
    @EnumSource(value = FintMultiplicity::class, names = ["ONE_TO_MANY", "NONE_TO_MANY"])
    fun `should ignore relation when inverse relation is a list`(invalidInverseMultiplicity: FintMultiplicity) {
        val scenario =
            TestScenario(
                testName = "Invalid Inverse",
                sourceMultiplicity = FintMultiplicity.ONE_TO_MANY,
                targetPackage = "no.fint.model.utdanning.target.person",
                expectedTargetType = EntityDescriptor("utdanning", "target", "person"),
                inverseMultiplicity = invalidInverseMultiplicity, // <--- The invalid part
            )

        configureMocksForScenario(scenario)

        val result = ruleBuilder.buildEntityDescriptorToRules()

        assertTrue(result.isEmpty(), "Expected no rules for inverse multiplicity $invalidInverseMultiplicity")
    }

    private fun successScenarios(): List<TestScenario> {
        val standardPkg = "no.fint.model.utdanning.target.person"
        val standardType = EntityDescriptor("utdanning", "target", "person")

        return listOf(
            TestScenario(
                testName = "Inverse: 1-1 (Mandatory)",
                sourceMultiplicity = FintMultiplicity.ONE_TO_MANY,
                targetPackage = standardPkg,
                expectedTargetType = standardType,
                inverseMultiplicity = FintMultiplicity.ONE_TO_ONE,
                expectedMandatory = true,
            ),
            TestScenario(
                testName = "Inverse: 0-1 (Optional)",
                sourceMultiplicity = FintMultiplicity.ONE_TO_MANY,
                targetPackage = standardPkg,
                expectedTargetType = standardType,
                inverseMultiplicity = FintMultiplicity.NONE_TO_ONE,
                expectedMandatory = false,
            ),
        )
    }

    private fun configureMocksForScenario(scenario: TestScenario) {
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

        val inverseRelationMock =
            relation(
                name = scenario.inverseRelationName,
                pkg = "any.package",
                multiplicity = scenario.inverseMultiplicity,
            )

        every { metamodelService.getComponents() } returns
            listOf(
                mockComponent(scenario.domain, scenario.pkg, listOf(sourceResource)),
            )

        every {
            metamodelService.getResource(
                scenario.expectedTargetType.domainName,
                scenario.expectedTargetType.packageName,
                scenario.expectedTargetType.resourceName,
            )
        } returns
            mockk<Resource> {
                every { relations } returns listOf(inverseRelationMock)
            }
    }

    data class TestScenario(
        val testName: String,
        val sourceMultiplicity: FintMultiplicity,
        val targetPackage: String,
        val expectedTargetType: EntityDescriptor,
        val inverseMultiplicity: FintMultiplicity = FintMultiplicity.ONE_TO_ONE,
        val expectedMandatory: Boolean = true,
        val domain: String = "utdanning",
        val pkg: String = "source",
        val sourceName: String = "elev",
        val forwardRelationName: String = "contactPerson",
        val inverseRelationName: String = "studentList",
    ) {
        override fun toString() = testName
    }

    private fun mockComponent(
        domain: String,
        pkg: String,
        resourcesList: List<Resource>,
    ): Component = spyk(Component(domain, pkg)) { every { this@spyk.resources } returns resourcesList }

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
