package no.fintlabs.autorelation.cache

import no.fintlabs.autorelation.model.RelationSyncRule
import no.fintlabs.autorelation.model.ResourceType
import org.springframework.stereotype.Component

@Component
class RelationRuleRegistry(
    ruleBuilder: RelationRuleBuilder
) {

    private val rulesByTrigger: Map<ResourceType, List<RelationSyncRule>> by lazy {
        ruleBuilder.buildResourceTypeToRelationSyncRules()
    }

    private val inverseRelationsIndex: Map<ResourceType, Set<String>> by lazy {
        indexInverseRelationsByTarget()
    }

    fun hasRules(trigger: ResourceType): Boolean =
        rulesByTrigger.containsKey(trigger)

    fun getRules(trigger: ResourceType): List<RelationSyncRule> =
        rulesByTrigger[trigger] ?: emptyList()

    fun getInverseRelations(target: ResourceType): Set<String> =
        inverseRelationsIndex[target] ?: emptySet()

    fun getInverseRelations(domain: String, pkg: String, resource: String): Set<String> =
        getInverseRelations(ResourceType.of(domain, pkg, resource))

    private fun indexInverseRelationsByTarget(): Map<ResourceType, Set<String>> =
        rulesByTrigger.values.asSequence()
            .flatten()
            .groupBy { it.targetType }
            .mapValues { (_, rules) -> rules.map { it.inverseRelation }.toSet() }

}