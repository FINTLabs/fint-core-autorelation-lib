# fint-core-autorelation-lib

Small library that **precomputes relation edges** from the FINT metamodel so you can quickly decide:

- **Trigger side:** When a resource of type **X** arrives, which **other resource types** should get relation updates
- **Target side:** For a given resource type **Y**, which **relation names are managed** (i.e., will get update events)

## Core pieces

- `RelationSpecBuilder`  
  Builds specs by scanning the metamodel for:
    - trigger relations with multiplicity **ONE_TO_MANY** or **NONE_TO_MANY**
    - matching back-references with **ONE_TO_ONE** or **NONE_TO_ONE**
    - only relations **within the same domain**

- `RelationCache` (in-memory, lazy)
    - `triggerResourceRelations: Map<ResourceType, List<RelationSpec>>`  
      *If a resource of this type comes in, we will trigger relation updates to these other resource types*.
    - `targetResourceRelations: Map<ResourceType, Set<String>>`  
      *For this target type, these relation names are “managed” and will receive update events*.