package no.fintlabs.autorelation.model

data class RelationSyncRule(
    val targetRelation: String,
    val inverseRelation: String,
    val targetType: ResourceType,
) {
    companion object {
        fun from(
            forwardRelation: String,
            inverseRelation: String,
            componentResource: String,
        ) = RelationSyncRule(
            forwardRelation,
            inverseRelation,
            ResourceType.parse(componentResource),
        )
    }
}
