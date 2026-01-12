package no.fintlabs.autorelation.model

import no.fint.model.resource.FintResource

data class RelationUpdate(
    val orgId: String,
    val domainName: String,
    val packageName: String,
    val resource: ResourceRef,
    val relation: RelationRef,
    val operation: RelationOperation
) {
    companion object {
        fun from(request: RelationRequest, resource: FintResource, relationSyncRule: RelationSyncRule) =
            ResourceRef.from(resource, relationSyncRule)?.let { resourceRef ->
                RelationRef.from(resource, relationSyncRule)?.let { relationRef ->
                    RelationUpdate(
                        orgId = request.orgId,
                        domainName = relationSyncRule.targetType.domainName,
                        packageName = relationSyncRule.targetType.packageName,
                        resource = resourceRef,
                        relation = relationRef,
                        operation = request.operation
                    )
                }
            }
    }
}