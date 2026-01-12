package no.fintlabs.autorelation.cache

import no.fintlabs.autorelation.model.RelationSyncRule
import no.fintlabs.autorelation.model.ResourceType
import org.springframework.stereotype.Repository

/**
 * Cache for relation synchronization rules.
 * Maps trigger resource types to the rules describing which target relations to update.
 * Also provides reverse lookup of which inverse relations are controlled per target type.
 */
@Repository
class RelationCache(
    relationRuleBuilder: RelationRuleBuilder
) {

    private val rulesByTriggerType: Map<ResourceType, List<RelationSyncRule>> by lazy {
        relationRuleBuilder.buildResourceTypeToRelationSyncRules()
    }

    private val inverseRelationsByTargetType: Map<ResourceType, Set<String>> by lazy {
        indexInverseRelationsByTarget()
    }

    private fun indexInverseRelationsByTarget(): Map<ResourceType, Set<String>> =
        rulesByTriggerType.values
            .asSequence()
            .flatten()
            .groupBy { it.targetType }
            .mapValues { (_, rules) -> rules.map { it.inverseRelation }.toSet() }

    fun isTriggerResourceType(resourceType: ResourceType): Boolean =
        rulesByTriggerType.containsKey(resourceType)

    fun rulesForTrigger(resourceType: ResourceType): List<RelationSyncRule> =
        rulesByTriggerType[resourceType] ?: emptyList()

    fun inverseRelationsForTarget(resourceType: ResourceType): Set<String> =
        inverseRelationsByTargetType[resourceType] ?: emptySet()

    fun inverseRelationsForTarget(domain: String, pkg: String, resource: String): Set<String> =
        inverseRelationsForTarget(ResourceType.of(domain, pkg, resource))

}