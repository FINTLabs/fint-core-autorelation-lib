package no.fintlabs.autorelation.model

class RelationSpec(
    val resourceRelation: String,
    val inversedRelation: String,
    val resourceType: ResourceType
) {
    companion object {
        fun from(resourceRelation: String, inversedRelation: String, componentResource: String) =
            RelationSpec(
                resourceRelation,
                inversedRelation,
                ResourceType.parse(componentResource)
            )
    }
}