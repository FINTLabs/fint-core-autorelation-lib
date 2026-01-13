package no.fintlabs.autorelation.docs

import no.fintlabs.autorelation.cache.RelationRuleBuilder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import java.io.File

@SpringBootTest
class GenerateDocsTest {
    @Autowired
    private lateinit var ruleBuilder: RelationRuleBuilder

    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan("no.fintlabs")
    class TestConfig

    @Test
    fun `generate supported relations markdown`() {
        // NOTE: This will only produce content if 'MetamodelService'
        // is successfully injected and returns REAL data.
        // If MetamodelService needs external properties (urls, etc),
        // ensure they are in src/test/resources/application.properties.

        val rules = ruleBuilder.buildResourceTypeToRelationSyncRules()

        if (rules.isEmpty()) {
            println("⚠️ WARNING: No rules were found! Is MetamodelService returning data?")
        }

        val markdown = RelationDocumentationGenerator().generateMarkdown(rules)

        val file = File("SUPPORTED_RELATIONS.md")
        file.writeText(markdown)

        println("✅ Documentation generated at: ${file.absolutePath}")
    }
}
