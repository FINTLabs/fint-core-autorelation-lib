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

        // 1. Define Target Resource (The one being pointed TO)
        val targetResource = resource(
            name = scenario.targetName,
            pkg = scenario.targetPackage,
            relations = arrayOf(relation(scenario.sourceName, scenario.sourcePackage, scenario.inverseMultiplicity))
        )

        // 2. Define Source Resource (The one starting the relation)
        val sourceResource = resource(
            name = scenario.sourceName,
            pkg = scenario.sourcePackage,
            relations = arrayOf(relation(scenario.targetName, scenario.targetPackage, scenario.sourceMultiplicity))
        )

        // 3. Mock Service
        every { metamodelService.getComponents() } returns listOf(
            Component(componentName, "xml", listOf(sourceResource))
        )
        // Ensure builder can find the target resource
        every {
            metamodelService.getResource(
                scenario.expectedTargetType.domain,
                scenario.expectedTargetType.pkg,
                scenario.expectedTargetType.resource
            )
        } returns targetResource

        // WHEN
        val result = ruleBuilder.buildResourceTypeToRelationSyncRules()

        // THEN
        val expectedKey = ResourceType(scenario.domain, scenario.pkg, scenario.sourceName)
        val rule = result[expectedKey]?.single() ?: fail("Expected one rule for $expectedKey")

        with(rule) {
            assertEquals(scenario.targetName, forwardRelation)
            assertEquals(scenario.sourceName, inverseRelation)
            assertEquals(scenario.expectedTargetType, targetType)
        }
    }

    @Test
    fun `should ignore relation if source multiplicity is 1-1`() {
        // 1-1 relations are not synced by this rule builder
        val sourceResource = resource(
            "elev", "no.fint.model.utdanning.source.elev",
            relation("person", "no.fint.model.utdanning.target.person", FintMultiplicity.ONE_TO_ONE)
        )
        every { metamodelService.getComponents() } returns listOf(
            Component("utdanning-source", "xml", listOf(sourceResource))
        )

        assertTrue(ruleBuilder.buildResourceTypeToRelationSyncRules().isEmpty())
    }

    @Test
    fun `should ignore relation if different domain`() {
        val sourceResource = resource(
            "elev", "no.fint.model.utdanning.source.elev",
            relation("ansatt", "no.fint.model.administrasjon.ansatt", FintMultiplicity.ONE_TO_MANY)
        )
        every { metamodelService.getComponents() } returns listOf(
            Component("utdanning-source", "xml", listOf(sourceResource))
        )

        assertTrue(ruleBuilder.buildResourceTypeToRelationSyncRules().isEmpty())
    }

    private fun successScenarios(): List<TestScenario> {
        val standardPkg = "no.fint.model.utdanning.target.person"
        val commonPkg = "no.fint.model.utdanning.person"

        val standardType = ResourceType("utdanning", "target", "person")
        val commonType = ResourceType("utdanning", "source", "person") // Inherits 'source'

        return listOf(
            TestScenario(
                "Standard: 1-N to 1-1",
                FintMultiplicity.ONE_TO_MANY,
                FintMultiplicity.ONE_TO_ONE,
                standardPkg,
                standardType
            ),
            TestScenario(
                "Standard: 1-N to 0-1",
                FintMultiplicity.ONE_TO_MANY,
                FintMultiplicity.NONE_TO_ONE,
                standardPkg,
                standardType
            ),
            TestScenario(
                "Standard: 0-N to 1-1",
                FintMultiplicity.NONE_TO_MANY,
                FintMultiplicity.ONE_TO_ONE,
                standardPkg,
                standardType
            ),
            TestScenario(
                "Standard: 0-N to 0-1",
                FintMultiplicity.NONE_TO_MANY,
                FintMultiplicity.NONE_TO_ONE,
                standardPkg,
                standardType
            ),

            TestScenario(
                "Common Resource: Inherits Component Package",
                FintMultiplicity.ONE_TO_MANY,
                FintMultiplicity.NONE_TO_ONE,
                commonPkg,
                commonType
            )
        )
    }

    data class TestScenario(
        val testName: String,
        val sourceMultiplicity: FintMultiplicity,
        val inverseMultiplicity: FintMultiplicity,
        val targetPackage: String,
        val expectedTargetType: ResourceType,
        val domain: String = "utdanning",
        val pkg: String = "source",
        val sourceName: String = "elev",
        val targetName: String = "person"
    ) {
        val sourcePackage get() = "no.fint.model.$domain.$pkg.$sourceName"
        override fun toString() = testName
    }

    private fun resource(name: String, pkg: String, vararg relations: FintRelation) = Resource(
        name = name,
        packageName = pkg,
        resourceType = FintResource::class.java,
        isCommon = false, writeable = true, fields = emptySet(), idFields = emptySet(),
        relations = relations.toList()
    )

    private fun relation(name: String, pkg: String, multiplicity: FintMultiplicity) =
        TestRelation(packageName = pkg, multiplicity = multiplicity, name = name)

    data class TestRelation(
        private val packageName: String,
        private val multiplicity: FintMultiplicity,
        private val name: String
    ) : FintRelation {
        override fun getPackageName() = packageName
        override fun getMultiplicity() = multiplicity
        override fun getName() = name
    }
}