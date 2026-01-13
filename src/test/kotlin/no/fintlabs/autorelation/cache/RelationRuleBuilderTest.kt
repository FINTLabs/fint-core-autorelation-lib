package no.fintlabs.autorelation.cache

import io.mockk.every
import io.mockk.mockk
import no.fint.model.FintMultiplicity
import no.fint.model.FintRelation
import no.fint.model.resource.FintResource
import no.fintlabs.autorelation.model.ResourceType
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
        // GIVEN
        val componentName = "${scenario.domain}-${scenario.pkg}"

        val sourceResource =
            resource(
                name = scenario.sourceName,
                pkg = scenario.sourcePackage,
                relations =
                    arrayOf(
                        relation(
                            // DISTINCT NAME CHECK:
                            // We use a specific relation name (e.g. "contactTeacher")
                            // to prove we aren't just copying the resource name ("person").
                            name = scenario.forwardRelationName,
                            pkg = scenario.targetPackage,
                            multiplicity = scenario.sourceMultiplicity,
                            inverseName = scenario.inverseRelationName,
                        ),
                    ),
            )

        every { metamodelService.getComponents() } returns
            listOf(
                Component(componentName, "xml", listOf(sourceResource)),
            )

        // WHEN
        val result = ruleBuilder.buildResourceTypeToRelationSyncRules()

        // THEN
        val expectedKey = ResourceType(scenario.domain, scenario.pkg, scenario.sourceName)
        val rule = result[expectedKey]?.single() ?: fail("Expected one rule for $expectedKey")

        with(rule) {
            // Verify we mapped the RELATION names, not the resource names
            assertEquals(scenario.forwardRelationName, targetRelation)
            assertEquals(scenario.inverseRelationName, inverseRelation)

            // Verify the target TYPE is still correct based on the package
            assertEquals(scenario.expectedTargetType, targetType)
        }
    }

    @Test
    fun `should ignore relation if inverseName is null (Uni-directional)`() {
        val sourceResource =
            resource(
                "elev",
                "no.fint.model.utdanning.source.elev",
                relation(
                    name = "relatedPerson",
                    pkg = "no.fint.model.utdanning.target.person",
                    multiplicity = FintMultiplicity.ONE_TO_MANY,
                    inverseName = null,
                ),
            )
        every { metamodelService.getComponents() } returns
            listOf(
                Component("utdanning-source", "xml", listOf(sourceResource)),
            )

        assertTrue(
            ruleBuilder.buildResourceTypeToRelationSyncRules().isEmpty(),
            "Should ignore uni-directional relations",
        )
    }

    @Test
    fun `should ignore relation if source multiplicity is 1-1`() {
        val sourceResource =
            resource(
                "elev",
                "no.fint.model.utdanning.source.elev",
                relation(
                    "relatedPerson",
                    "no.fint.model.utdanning.target.person",
                    FintMultiplicity.ONE_TO_ONE,
                    "studentList",
                ),
            )
        every { metamodelService.getComponents() } returns
            listOf(
                Component("utdanning-source", "xml", listOf(sourceResource)),
            )

        assertTrue(ruleBuilder.buildResourceTypeToRelationSyncRules().isEmpty())
    }

    @Test
    fun `should ignore relation if different domain`() {
        val sourceResource =
            resource(
                "elev",
                "no.fint.model.utdanning.source.elev",
                relation("ansatt", "no.fint.model.administrasjon.ansatt", FintMultiplicity.ONE_TO_MANY, "elev"),
            )
        every { metamodelService.getComponents() } returns
            listOf(
                Component("utdanning-source", "xml", listOf(sourceResource)),
            )

        assertTrue(ruleBuilder.buildResourceTypeToRelationSyncRules().isEmpty())
    }

    // --- Data Providers ---

    private fun successScenarios(): List<TestScenario> {
        val standardPkg = "no.fint.model.utdanning.target.person"
        val commonPkg = "no.fint.model.utdanning.person"

        val standardType = ResourceType("utdanning", "target", "person")
        val commonType = ResourceType("utdanning", "source", "person")

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
        val expectedTargetType: ResourceType,
        val domain: String = "utdanning",
        val pkg: String = "source",
        val sourceName: String = "elev",
        val targetName: String = "person",
        // Distinct relation names to ensure we aren't confusing them with resource names
        val forwardRelationName: String = "contactPerson",
        val inverseRelationName: String = "studentList",
    ) {
        val sourcePackage get() = "no.fint.model.$domain.$pkg.$sourceName"

        override fun toString() = testName
    }

    private fun resource(
        name: String,
        pkg: String,
        vararg relations: FintRelation,
    ) = Resource(
        name = name,
        packageName = pkg,
        resourceType = FintResource::class.java,
        isCommon = false,
        writeable = true,
        fields = emptySet(),
        idFields = emptySet(),
        relations = relations.toList(),
    )

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
