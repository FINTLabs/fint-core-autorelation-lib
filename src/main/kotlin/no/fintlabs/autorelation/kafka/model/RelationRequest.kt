package no.fintlabs.autorelation.kafka.model

data class RelationRequest(
    val type: ResourceType,
    val orgId: String,
    val resource: Any,
    val operation: RelationOperation
)
