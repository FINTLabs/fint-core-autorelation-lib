package no.fintlabs.autorelation.model

import no.fint.model.resource.Link

data class RelationRef(
    val name: String,
    val links: List<Link>
)
