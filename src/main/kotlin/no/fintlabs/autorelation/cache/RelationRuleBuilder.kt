package no.fintlabs.autorelation.cache

import no.fint.model.FintMultiplicity
import no.fint.model.FintRelation
import no.fintlabs.autorelation.model.EntityDescriptor
import no.fintlabs.autorelation.model.RelationSyncRule
import no.fintlabs.autorelation.model.createEntityDescriptor
import no.fintlabs.metamodel.MetamodelService
import no.fintlabs.metamodel.model.Component
import no.fintlabs.metamodel.model.Resource
import org.springframework.stereotype.Component as SpringComponent

@SpringComponent
class RelationRuleBuilder(
    private val metamodelService: MetamodelService,
) {
    fun buildEntityDescriptorToRules() =
        everyResourceInFint { component, resource ->
            resource.relations
                .filter { it.isListMultiplicity() && it.belongsToDomain(component.domainName) && it.inverseName != null }
                .mapNotNull { targetRelation -> createRelationSyncRule(component, targetRelation) }
                .takeIf { it.isNotEmpty() }
                ?.let { rules -> component.toEntityDescriptor(resource.name) to rules }
        }.toMap()

    private fun <T> everyResourceInFint(transform: (Component, Resource) -> T?): List<T> =
        metamodelService
            .getComponents()
            .flatMap { component ->
                component.resources.mapNotNull { resource ->
                    transform(component, resource)
                }
            }

    private fun createRelationSyncRule(
        component: Component,
        targetRelation: FintRelation,
    ): RelationSyncRule? {
        val targetEntity = targetRelation.toEntityDescriptor(component)
        val inverseRelation = targetEntity.findInverseRelation(targetRelation.inverseName) ?: return null

        // We only support 0-1 -> 0-* relations
        if (!inverseRelation.multiplicity.isOneRelation()) return null

        return RelationSyncRule(
            targetRelation = targetRelation.name,
            inverseRelation = targetRelation.inverseName,
            targetType = targetEntity,
            isMandatory = inverseRelation.multiplicity == FintMultiplicity.ONE_TO_ONE,
        )
    }

    /**
     * We are unable to get all inverse Multiplicities because of a problem in the information model.
     * There are resources that have relations to abstract resources. These are not considered resources in metamodel, so they are not able to be fetched.
     * These relations are found here as of 3.21.11:
     * - administrasjon.fullmakt.Fullmakt has a relation to (myndighet - Abstract resource)
     * - arkiv.noark.Arkivdel has a relation to (registrering - Abstract resource)
     * - arkiv.noark.Arkivdel has a relation to (mappe - Abstract resource)
     */
    private fun EntityDescriptor.findInverseRelation(inverseName: String): FintRelation? =
        metamodelService
            .getResource(domainName, packageName, resourceName)
            ?.relations
            ?.firstOrNull { it.name == inverseName }

    private fun Component.toEntityDescriptor(resource: String) = createEntityDescriptor(domainName, packageName, resource)

    private fun FintMultiplicity.isOneRelation() = this in setOf(FintMultiplicity.ONE_TO_ONE, FintMultiplicity.NONE_TO_ONE)

    private fun FintRelation.toEntityDescriptor(component: Component): EntityDescriptor =
        // Inherit domain and packageName from parent - since common resources do not have their own component
        if (isCommonResource()) {
            createEntityDescriptor(component.domainName, component.packageName, resourceName())
        } else {
            packageName
                .split(".") // packageName is actually a className
                .takeLast(3)
                .let { (domainName, pkg, resource) -> createEntityDescriptor(domainName, pkg, resource) }
        }

    private fun FintRelation.isListMultiplicity() = this.multiplicity in setOf(FintMultiplicity.ONE_TO_MANY, FintMultiplicity.NONE_TO_MANY)

    private fun FintRelation.belongsToDomain(domain: String): Boolean = this.packageName.startsWith("no.fint.model.$domain")

    // packageName is actually className
    private fun FintRelation.isCommonResource() = this.packageName.split(".").size == 5

    private fun FintRelation.resourceName() =
        this.packageName
            .split(".")
            .last()
}
