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

    // TODO: Move some of the Fint logic into metamodel?

    fun buildRulesByTriggerType(): Map<ResourceType, List<RelationSyncRule>> =
        metamodelService.getComponents()
            .flatMap { component ->
                component.resources.flatMap { resource ->
                    resource.relations
                        .filter { isOneOrNoneToMany(it.multiplicity) }
                        .filter { belongsToSameDomain(component, it) }
                        .mapNotNull { relation ->
                            buildRulesByTriggerType(
                                component = component.name,
                                originalRelation = relation,
                                resourcePackage = resource.packageName
                            )
                        }
                }
            }
            .groupBy(
                keySelector = { (resourceType, _) -> resourceType },
                valueTransform = { (_, resourceRelations) -> resourceRelations }
            )

    private fun belongsToSameDomain(component: Component, relation: FintRelation): Boolean =
        relation.packageName.startsWith("no.fint.model.${component.domainName}")

    private fun buildRulesByTriggerType(
        component: String,
        originalRelation: FintRelation,
        resourcePackage: String
    ): Pair<ResourceType, RelationSyncRule>? =
        formatComponentResource(component, originalRelation.packageName)
            .let { componentResource -> ResourceType.parse(componentResource) }
            .let { resourceType ->
                metamodelService.getResource(resourceType.domain, resourceType.pkg, resourceType.resource)
                    ?.let { findFirstBackReference(it, resourcePackage) }
                    ?.let { relationResource ->
                        resourceType to createResourceSpec(relationResource, originalRelation)
                    }
            }

    private fun createResourceSpec(
        resourceRelation: FintRelation,
        originalRelation: FintRelation
    ) = RelationSyncRule.from(
        forwardRelation = resourceRelation.name,
        inverseRelation = originalRelation.name,
        componentResource = getComponentResource(resourceRelation.packageName)
    )

    fun formatComponentResource(component: String, packageName: String): String =
        if (isCommon(packageName))
            "${component}-${getResourceName(packageName)}"
        else getComponentResource(packageName)

    private fun isOneOrNoneToMany(multiplicity: FintMultiplicity) =
        multiplicity in setOf(FintMultiplicity.ONE_TO_MANY, FintMultiplicity.NONE_TO_MANY)

    private fun findFirstBackReference(relationResource: Resource, resourcePackageName: String) =
        relationResource.relations.firstOrNull { it.packageName == resourcePackageName }
            .takeIf { it?.multiplicity == FintMultiplicity.ONE_TO_ONE || it?.multiplicity == FintMultiplicity.NONE_TO_ONE }

    private fun getResourceName(packageName: String): String =
        packageName.split(".").last()

    private fun getComponentResource(packageName: String): String =
        packageName.split(".")
            .takeLast(3)
            .joinToString("-")

    private fun isCommon(packageName: String) =
        packageName.startsWith("no.fint.model.felles")

}