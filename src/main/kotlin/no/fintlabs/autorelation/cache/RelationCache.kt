package no.fintlabs.autorelation.cache

import no.fintlabs.autorelation.model.RelationSpec
import no.fintlabs.autorelation.model.ResourceType
import org.springframework.stereotype.Repository

@Repository
class RelationCache(
    relationSpecBuilder: RelationSpecBuilder
) {

    private val cache: Map<ResourceType, List<RelationSpec>> by lazy {
        relationSpecBuilder.buildResourceTypeToRelationSpecs()
    }

    fun getResourceRelations(
        domainName: String,
        packageName: String
    ): Map<String, Set<String>> =
        cache.values
            .asSequence()
            .flatten()
            .filter { it.resourceType.sameComponent(domainName, packageName) }
            .groupBy { it.resourceType.resource }
            .mapValues { (_, specs) -> specs.map { it.inversedRelation.name }.toSet() }


    fun getRelationSpecs(resourceType: ResourceType): List<RelationSpec>? = cache[resourceType]

}