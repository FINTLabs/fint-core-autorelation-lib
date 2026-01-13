package no.fintlabs.autorelation.docs

import no.fintlabs.autorelation.model.RelationSyncRule
import no.fintlabs.autorelation.model.ResourceType
import java.time.LocalDate

class RelationDocumentationGenerator {
    fun generateMarkdown(rules: Map<ResourceType, List<RelationSyncRule>>): String =
        buildString {
            appendLine("# 🔗 Supported Auto-Relations")
            appendLine("This list is automatically generated from the FINT Metamodel.")
            appendLine("It is grouped by **FINT Component** (`domain.package`).")
            appendLine()

            // 1. Group by Component (e.g. "administrasjon.personal")
            val rulesByComponent =
                rules.entries
                    .groupBy { "${it.key.domainName}.${it.key.packageName}" }
                    .toSortedMap()

            rulesByComponent.forEach { (component, triggers) ->
                appendLine("## 📦 Component: $component")

                // 2. Iterate Trigger Resources
                triggers.sortedBy { it.key.resource }.forEach { (trigger, syncRules) ->
                    appendLine("### ⚡ Trigger: `${trigger.resource}`")
                    appendLine("When `${trigger.resource}` is updated, it updates the following targets:")
                    appendLine()

                    appendLine("| Target Resource | Relations to Update (on Target) |")
                    appendLine("| :--- | :--- |")

                    // 3. Group rules by the Target Resource to combine multiple updates to the same target
                    val groupedTargets = syncRules.groupBy { it.targetType }

                    groupedTargets.toSortedMap(compareBy { it.resource }).forEach { (targetType, rulesForTarget) ->
                        val targetName = "`${targetType.domainName}.${targetType.packageName}.${targetType.resource}`"

                        // Join all fields being updated on this target into a list like "`fieldA`, `fieldB`"
                        val fieldsToUpdate = rulesForTarget
                            .map { "`" + it.inverseRelation + "`" }
                            .sorted()
                            .joinToString(", ")

                        appendLine("| $targetName | $fieldsToUpdate |")
                    }
                    appendLine()
                }
                appendLine("---")
            }

            appendLine("*Generated: ${LocalDate.now()}*")
        }
}