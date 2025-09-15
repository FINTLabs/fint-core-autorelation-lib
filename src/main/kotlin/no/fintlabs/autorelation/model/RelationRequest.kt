package no.fintlabs.autorelation.model

data class RelationRequest(
    val type: ResourceType,
    val orgId: String,
    val resource: Any,
    val operation: RelationOperation,
    val entityRetentionTime: Long?
) {
    companion object {
        fun from(
            operation: RelationOperation,
            orgId: String,
            domain: String,
            pkg: String,
            resourceName: String,
            resource: Any,
            entityRetentionTime: Long?
        ) =
            RelationRequest(
                operation = operation,
                type = ResourceType(
                    domain = domain,
                    pkg = pkg,
                    resource = resourceName
                ),
                orgId = orgId,
                resource = resource,
                entityRetentionTime = entityRetentionTime
            )
    }
}
