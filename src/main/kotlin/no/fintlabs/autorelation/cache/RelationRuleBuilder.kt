package no.fintlabs.autorelation.cache

import no.fint.model.FintMultiplicity
import no.fint.model.FintRelation
import no.fintlabs.autorelation.model.RelationSyncRule
import no.fintlabs.autorelation.model.ResourceType
import no.fintlabs.metamodel.MetamodelService
import no.fintlabs.metamodel.model.Component
import no.fintlabs.metamodel.model.Resource
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service

@Service
@ComponentScan("no.fintlabs")
class RelationRuleBuilder(
    private val metamodelService: MetamodelService
) {

    fun buildResourceTypeToRelationSyncRules() = everyResourceInFint { component, resource ->
        resource.relations
            .filter { isOneOrNoneToMany(it.multiplicity) && belongsToSameDomain(component, it) }
            .map { createRelationSyncRule(component, it, resource.packageName) }
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
        inverseRelationPackageName: String
    ): RelationSyncRule =
        createTargetResourceType(component, targetRelation).let { targetResourceType ->
            RelationSyncRule(
                forwardRelation = targetRelation.name,
                inverseRelation = findInverseRelation(targetResourceType, inverseRelationPackageName).name,
                targetType = targetResourceType
            )
        }

    private fun Component.toResourceType(resource: String) = ResourceType(domainName, packageName, resource)

    private fun createTargetResourceType(component: Component, targetRelation: FintRelation): ResourceType =
        if (targetRelation.isCommonResource()) { // Inherit domain and packageName from parent - since common resources do not have their own component
            ResourceType(component.domainName, component.packageName, targetRelation.resourceName())
        } else {
            targetRelation.packageName.split(".") // packageName is actually a className
                .takeLast(3)
                .let { (domainName, pkg, resource) -> ResourceType(domainName, pkg, resource) }
        }

    private fun findInverseRelation(
        targetResourceType: ResourceType,
        inverseRelationPackageName: String
    ): FintRelation =
        targetResourceType.toMetamodelResource()
            ?.relations
            ?.firstInverseRelation(inverseRelationPackageName)
            ?: error("Could not find resource for $targetResourceType")

    private fun List<FintRelation>.firstInverseRelation(inverseRelationPackageName: String): FintRelation =
        this.firstOrNull { it.packageName == inverseRelationPackageName }
            ?: error("Could not find inverse relation of package $inverseRelationPackageName")

    private fun ResourceType.toMetamodelResource(): Resource? =
        metamodelService.getResource(domain, pkg, resource)

    private fun belongsToSameDomain(component: Component, relation: FintRelation): Boolean =
        relation.packageName.startsWith("no.fint.model.${component.domainName}")

    // packageName is actually className
    private fun FintRelation.isCommonResource() = this.packageName.split(".").size == 5

    private fun FintRelation.resourceName() = this.packageName.split(".").last().lowercase()

    private fun isOneOrNoneToMany(multiplicity: FintMultiplicity) =
        multiplicity in setOf(FintMultiplicity.ONE_TO_MANY, FintMultiplicity.NONE_TO_MANY)

}