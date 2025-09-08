package no.fintlabs.autorelation.kafka.model

data class RelationRequest(
    val resource: Any,
    val type: ResourceType,
    val operation: RelationOperation
)
