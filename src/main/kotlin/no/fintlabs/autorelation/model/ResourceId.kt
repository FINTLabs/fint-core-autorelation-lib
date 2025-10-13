package no.fintlabs.autorelation.model

import no.fint.model.resource.Link

data class ResourceId(
    val field: String,
    val value: String
) {
    fun createLink(): Link = Link.with("$field/$value")
}