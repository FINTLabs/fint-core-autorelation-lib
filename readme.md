## fint-core-autorelation-lib

### Overview
`fint-core-autorelation-lib` is a library that combines the **autorelation model** definitions and **cache logic** used for managing relations between entities in the FINT ecosystem.  
It was originally part of the first iteration of the **Autorelation** architecture, where both the consumer application and the `fint-core-autorelation` service contained logic for determining which relations to mutate.

### Purpose
The library centralizes both:
- **Model definitions** (data classes representing relations and relation events)
- **Relation handling logic** (including caching and mutation determination)

This makes it possible for a service to perform autorelation logic locally, without depending on a centralized autorelation service.

### Relationship to the `model` Branch
There is also a lighter version of this library available in the **`model` branch** of this repository.  
That branch contains **only the model classes** — no caching or mutation logic.

The `model` branch is intended for scenarios where a **dedicated Autorelation Service** exists to manage relation updates. In those cases:
- The **Autorelation Service** uses the full `fint-core-autorelation-lib` (main branch) to perform relation logic and publish relation entities or events.
- The **consumers** use the lightweight `model` branch to deserialize and process these relation entities, without containing any autorelation logic themselves.

### Typical Use Cases
| Scenario | Recommended Version | Description |
|-----------|---------------------|--------------|
| **Standalone consumer** that needs to handle autorelation logic locally | `main` branch (`fint-core-autorelation-lib`) | Includes both model and logic layers for relation determination |
| **Centralized autorelation setup** where an Autorelation Service manages relations and sends updates to consumers | `model` branch for consumers, `main` branch for the Autorelation Service | The service uses the lib to compute and send relation updates; consumers only depend on the model definitions to handle them |

### Migration Note
During the transition to the current architecture, consumers that previously used the main branch (`fint-core-autorelation-lib`) can migrate to using the **`model` branch** when an Autorelation Service is introduced.  
This reduces duplication and simplifies consumer implementations by removing unnecessary relation logic, while the Autorelation Service continues to use the full library to manage and distribute relation updates.
