package no.fintlabs.autorelation.model

import no.novari.fint.model.FintRelation

class RelationSpec(
    val resourceRelation: FintRelation,
    val inversedRelation: FintRelation,
    val resourceType: ResourceType
) {
    companion object {
        fun from(resourceRelation: FintRelation, inversedRelation: FintRelation, componentResource: String) =
            RelationSpec(
                resourceRelation,
                inversedRelation,
                ResourceType.Companion.parse(componentResource)
            )
    }
}