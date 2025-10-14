package no.fintlabs.autorelation.model

data class RelationRequest(
    val type: ResourceType,
    val orgId: String,
    val resource: Any,
    val operation: RelationOperation
) {
    companion object {

        fun fromEntity(topic: String, resource: Any) =
            RelationRequest(
                type = getResourceType(topic),
                orgId = getOrgId(topic),
                resource = resource,
                operation = RelationOperation.ADD
            )

        @JvmStatic
        fun from(
            operation: RelationOperation,
            orgId: String,
            domain: String,
            pkg: String,
            resourceName: String,
            resource: Any
        ) = RelationRequest(
            operation = operation,
            type = ResourceType(
                domain = domain,
                pkg = pkg,
                resource = resourceName
            ),
            orgId = orgId,
            resource = resource
        )

        private fun getOrgId(topic: String) = topic.substringBefore(".")

        private fun getResourceType(topic: String) =
            ResourceType.parse(topic.substringAfterLast("."))
    }
}
