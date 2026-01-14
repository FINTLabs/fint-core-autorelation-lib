package no.fintlabs.autorelation.model

/**
 * Defines a synchronization rule for a specific relation.
 *
 * This class holds the configuration required to construct a [RelationUpdate]. It maps a
 * relation on the source resource to a target resource and defines how the connection
 * should be established.
 *
 * @property targetRelation The name of the relation (link) on the source resource that points
 * to the target (e.g., "fravarsregistrering"). We extract the target's ID from this link.
 *
 * @property inverseRelation The name of the relation on the target resource that points back
 * to the source (e.g., "elevfravar"). This becomes the `relationName` in the [RelationUpdate].
 *
 * @property targetType The [EntityDescriptor] (domain, package, resource) of the target.
 * This identifies which consumer service owns the target resource.
 *
 * @property isMandatory If `true`, a missing [targetRelation] link on the source resource
 * constitutes an error (MissingMandatoryLinkException). If `false`, the update is silently skipped.
 */
data class RelationSyncRule(
    val targetRelation: String,
    val inverseRelation: String,
    val targetType: EntityDescriptor,
    val isMandatory: Boolean,
)
