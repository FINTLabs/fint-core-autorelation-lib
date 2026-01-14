package no.fintlabs.autorelation.model

import no.fint.model.resource.FintResource
import no.fint.model.resource.Link

data class RelationBinding(
    val relationName: String,
    val link: Link,
)

fun RelationSyncRule.toRelationBinding(
    resource: FintResource,
    resourceId: String,
) = RelationBinding(
    relationName = inverseRelation,
    link = resource.toLink(resourceId),
)

private fun FintResource.toLink(resourceId: String) =
    this.identifikators
        .filter { it.value != null }
        .entries
        .firstOrNull { it.value.identifikatorverdi == resourceId }
        ?.let { (idField, idValue) -> Link.with("$idField/${idValue.identifikatorverdi}") }
        ?: throw ResourceIdMismatchException(resourceId)
