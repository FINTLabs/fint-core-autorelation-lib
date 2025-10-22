package no.fintlabs.autorelation.model

data class RelationRequest(
    val type: ResourceType,
    val orgId: String,
    val resource: Any,
    val operation: RelationOperation
) {
    constructor(topic: String, resource: Any, operation: RelationOperation) : this(
        type = getResourceType(topic),
        orgId = getOrgId(topic),
        resource = resource,
        operation = operation,
    )

    companion object {
        private fun getOrgId(topic: String) = topic.substringBefore(".")

        private fun getResourceType(topic: String) =
            ResourceType.parse(topic.substringAfterLast("."))
    }

}

fun createDeleteRequest(
    orgId: String,
    domain: String,
    pkg: String,
    resourceName: String,
    resource: Any
) = RelationRequest(
    operation = RelationOperation.DELETE,
    type = ResourceType(
        domain = domain,
        pkg = pkg,
        resource = resourceName
    ),
    orgId = orgId,
    resource = resource
)