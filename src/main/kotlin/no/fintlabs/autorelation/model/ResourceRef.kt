package no.fintlabs.autorelation.model

import no.novari.fint.model.resource.FintResource
import no.novari.fint.model.resource.Link

data class ResourceRef(
    val name: String,
    val id: String
) {
    companion object {
        fun from(resource: FintResource, relationSyncRule: RelationSyncRule): ResourceRef? =
            getTargetResourceLink(resource, relationSyncRule.forwardRelation)
                ?.href
                ?.substringAfterLast("/")
                ?.let { ResourceRef(relationSyncRule.targetType.resource, it) }

        private fun getTargetResourceLink(fintLinks: FintResource, relationName: String): Link? =
            fintLinks.links?.get(relationName)?.firstOrNull()
    }
}