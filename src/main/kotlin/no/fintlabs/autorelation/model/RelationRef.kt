package no.fintlabs.autorelation.model

import no.fint.model.FintIdentifikator
import no.fint.model.resource.FintResource
import no.fint.model.resource.Link

data class RelationRef(
    val name: String,
    val links: List<Link>
) {
    companion object {
        fun from(resource: FintResource, relationSyncRule: RelationSyncRule): RelationRef? =
            createLinks(resource)?.let { links ->
                RelationRef(
                    name = relationSyncRule.inverseRelation,
                    links = links
                )
            }

        private fun createLinks(resource: FintResource): List<Link>? =
            resource.identifikators
                .filterValues { it != null }
                .map { it.toLink() }
                .takeIf { it.isNotEmpty() }

        private fun Map.Entry<String, FintIdentifikator>.toLink() =
            Link.with("${this.key.lowercase()}/${this.value.identifikatorverdi}")
    }
}
