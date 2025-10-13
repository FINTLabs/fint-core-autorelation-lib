package no.fintlabs.autorelation.model

data class RelationRequest(
    val type: ResourceType,
    val orgId: String,
    val resource: Any,
    val operation: RelationOperation
) {
    companion object {
        @JvmStatic
        fun from(
            operation: RelationOperation,
            orgId: String,
            domain: String,
            pkg: String,
            resourceName: String,
            resource: Any
        ) =
            RelationRequest(
                operation = operation,
                type = ResourceType(
                    domain = domain,
                    pkg = pkg,
                    resource = resourceName
                ),
                orgId = orgId,
                resource = resource
            )
    }
}
