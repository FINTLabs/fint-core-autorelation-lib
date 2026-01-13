package no.fintlabs.autorelation.cache

import no.fint.model.FintMultiplicity
import no.fint.model.FintRelation
import no.fintlabs.autorelation.model.RelationSyncRule
import no.fintlabs.autorelation.model.ResourceType
import no.fintlabs.metamodel.MetamodelService
import no.fintlabs.metamodel.model.Component
import no.fintlabs.metamodel.model.Resource
import org.springframework.stereotype.Component as SpringComponent

@SpringComponent
class RelationRuleBuilder(
    private val metamodelService: MetamodelService,
) {
    fun buildResourceTypeToRelationSyncRules() =
        everyResourceInFint { component, resource ->
            resource.relations
                .filter { it.isListMultiplicity() && it.belongsToDomain(component.domainName) && it.inverseName != null }
                .map { targetRelation -> createRelationSyncRule(component, targetRelation) }
                .takeIf { it.isNotEmpty() }
                ?.let { rules -> component.toResourceType(resource.name) to rules }
        }.toMap()

    private fun <T> everyResourceInFint(transform: (Component, Resource) -> T?): List<T> =
        metamodelService.getComponents().flatMap { component ->
            component.resources.mapNotNull { resource ->
                transform(component, resource)
            }
        }

    private fun createRelationSyncRule(
        component: Component,
        targetRelation: FintRelation,
    ): RelationSyncRule =
        RelationSyncRule(
            targetRelation = targetRelation.name,
            inverseRelation = targetRelation.inverseName,
            targetType = createTargetResourceType(component, targetRelation),
        )

    private fun Component.toResourceType(resource: String) = ResourceType(domainName, packageName, resource)

    private fun createTargetResourceType(
        component: Component,
        targetRelation: FintRelation,
    ): ResourceType =
        // Inherit domain and packageName from parent - since common resources do not have their own component
        if (targetRelation.isCommonResource()) {
            ResourceType(component.domainName, component.packageName, targetRelation.resourceName())
        } else {
            targetRelation.packageName
                .split(".") // packageName is actually a className
                .takeLast(3)
                .let { (domainName, pkg, resource) -> ResourceType(domainName, pkg, resource) }
        }

    private fun FintRelation.isListMultiplicity() = this.multiplicity in setOf(FintMultiplicity.ONE_TO_MANY, FintMultiplicity.NONE_TO_MANY)

    private fun FintRelation.belongsToDomain(domain: String): Boolean = this.packageName.startsWith("no.fint.model.$domain")

    // packageName is actually className
    private fun FintRelation.isCommonResource() = this.packageName.split(".").size == 5

    private fun FintRelation.resourceName() =
        this.packageName
            .split(".")
            .last()
            .lowercase()
}
