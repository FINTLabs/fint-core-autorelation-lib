# FINT AutoRelation Library

AutoRelation is a lightweight library for managing bidirectional relationships between FINT resources.
It ensures that when one resource (the trigger) changes, any related resources (the targets) are automatically updated to maintain consistent relationships.

------------------------------------------------------------

## Overview

Many FINT resources are interrelated — for example, Elevfravar (student absence) and Fravarsregistrering (absence registration).
When you receive a new Fravarsregistrering, it references its corresponding Elevfravar.
AutoRelation ensures that both sides of the relationship stay synchronized by generating and applying relation update rules.

This library provides:
- A domain model for describing bidirectional relations.
- A cache and lookup API (RelationCache) for resolving which relations need synchronization.
- A foundation for services that handle automatic relation updates when resources change.

------------------------------------------------------------

## Core Concepts

### ResourceType
A ResourceType uniquely identifies a resource in FINT by its:
- domain (e.g. utdanning)
- pkg (or component, e.g. vurdering)
- resource (e.g. elevfravar)

Together, these three fields form a composite identity for a resource type:
ResourceType.of("utdanning", "elev", "elevfravar")

------------------------------------------------------------

### RelationSyncRule
A RelationSyncRule defines how two resources relate to each other and how updates should propagate.

Field             | Description                                               | Example
------------------|-----------------------------------------------------------|--------------------------
forwardRelation   | The relation name on the trigger resource pointing to the target. | "elevfravar"
inverseRelation   | The relation name on the target pointing back to the trigger.    | "fravarsregistrering"
targetType        | The type of resource that will be updated (the target).          | ResourceType("utdanning", "vurdering", "elevfravar")

Example:
When a Fravarsregistrering is received:
RelationSyncRule(
forwardRelation = "elevfravar",
inverseRelation = "fravarsregistrering",
targetType = ResourceType.of("utdanning", "vurdering", "elevfravar")
)

This tells the system:
When we receive a fravarsregistrering (trigger), find the related elevfravar (target), and update its fravarsregistrering relation accordingly.

------------------------------------------------------------

### RelationCache
The RelationCache acts as a precomputed lookup index for all relation rules.

It exposes convenient lookup functions:
fun isTriggerResourceType(resourceType: ResourceType): Boolean
fun rulesForTrigger(resourceType: ResourceType): List<RelationSyncRule>
fun inverseRelationsForTarget(resourceType: ResourceType): Set<String>

Internally, it builds two maps:
- rulesByTriggerType: Maps a trigger resource type → list of relation rules describing which targets to update.
- inverseRelationsByTargetType: Maps a target resource type → set of inverse relation names that should be updated when triggers are processed.

This ensures efficient bidirectional synchronization without recalculating relationships at runtime.

------------------------------------------------------------

## Example Flow

Let’s walk through the Elevfravar / Fravarsregistrering case:

1. A new Fravarsregistrering resource is received.
2. The system looks up RelationCache.rulesForTrigger(Fravarsregistrering).
3. It finds a RelationSyncRule pointing to the Elevfravar resource.
4. The service updates the related Elevfravar to include the new Fravarsregistrering in its relations.
5. Both sides are now synchronized.

------------------------------------------------------------

## Relation Models

The library’s model layer defines the building blocks used across all auto-relation logic:

Model              | Purpose
-------------------|----------------------------------------------------------
ResourceType       | Describes a specific resource in the FINT ecosystem.
RelationSyncRule   | Defines a bidirectional synchronization rule between two resources.
RelationUpdate*    | Represents the payload or event generated to apply an update (if included in your project).

------------------------------------------------------------

## Integration Notes

- Use RelationCache as a dependency where relation synchronization is needed.
- Populate it via a builder (e.g., RelationRuleBuilder) that defines all supported relations and ownership rules.
- The cache is lazy-initialized, so startup performance remains fast.
- Extend the model with your own types if your domain has additional metadata (e.g., ownership flags or conditional relations).

------------------------------------------------------------

## Glossary

Term                 | Meaning
---------------------|----------------------------------------------------------
Trigger Resource     | The resource you receive or update — the event source.
Target Resource      | The resource that must be updated to keep the relationship consistent.
Forward Relation     | The relation field on the trigger resource pointing to its target.
Inverse Relation     | The relation field on the target resource pointing back to the trigger.
Relation Rule        | A declarative definition describing how to synchronize a trigger and its target.
Relation Cache       | The in-memory registry that maps resource types to their relation rules.

------------------------------------------------------------

## Testing

You can test the behavior using your favorite test framework (JUnit5, Kotest, etc.):

@Test
fun `should resolve inverse relations for target`() {
val cache = RelationCache(mockBuilder)
val resourceType = ResourceType.of("utdanning", "elev", "elevfravar")

    assertTrue(cache.inverseRelationsForTarget(resourceType).contains("fravarsregistrering"))
}

------------------------------------------------------------

## Summary

The AutoRelation Library provides a clear, type-safe way to define, discover, and apply relation synchronization rules in FINT-based systems.
It bridges the gap between resources and their dependencies — ensuring your data relationships stay consistent, automatically.
