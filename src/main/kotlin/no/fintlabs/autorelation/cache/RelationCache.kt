package no.fintlabs.autorelation.cache

import no.fintlabs.autorelation.model.RelationSpec
import no.fintlabs.autorelation.model.ResourceType
import org.springframework.stereotype.Repository

@Repository
class RelationCache(
    relationSpecBuilder: RelationSpecBuilder
) {

    private val triggerResourceRelations: Map<ResourceType, List<RelationSpec>> by lazy {
        relationSpecBuilder.buildResourceTypeToRelationSpecs()
    }

    private val targetResourceRelations: Map<ResourceType, Set<String>> by lazy {
        mapTargetRelations()
    }

    private fun mapTargetRelations(): Map<ResourceType, Set<String>> =
        triggerResourceRelations.values
            .asSequence()
            .flatten()
            .groupBy { it.resourceType }
            .mapValues { (_, specs) -> specs.map { it.inversedRelation }.toSet() }

    fun isTriggerResourceType(resourceType: ResourceType): Boolean =
        triggerResourceRelations.containsKey(resourceType)

    fun getRelationSpecsForTrigger(resourceType: ResourceType): List<RelationSpec> =
        triggerResourceRelations[resourceType] ?: emptyList()

    fun getControlledRelationsForTarget(resourceType: ResourceType): Set<String> =
        targetResourceRelations[resourceType] ?: emptySet()

    fun getControlledRelationsForTarget(domain: String, pkg: String, resource: String): Set<String> =
        getControlledRelationsForTarget(ResourceType.of(domain, pkg, resource))

}