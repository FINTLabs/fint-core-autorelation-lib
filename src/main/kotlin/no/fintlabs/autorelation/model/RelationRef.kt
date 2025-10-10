package no.fintlabs.autorelation.model

data class RelationRef(
    val name: String,
    val ids: List<ResourceId>
) {
    fun createLinks() = ids.map { it.createLink() }
}
