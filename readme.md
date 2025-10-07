## fint-core-autorelation-lib

### Versioning
The repository follows a **two-version structure** that reflects different use cases:

| Version | Branch | Description |
|----------|---------|-------------|
| **V1** | `model` | Lightweight version containing only model classes. Intended for consumers that receive and process relation updates sent from an Autorelation Service. |
| **V2** | `main` | Full version that includes both model definitions and autorelation cache logic for determining which relations are controlled. Used by services that perform relation control and publish updates. |

> **Important:**  
> For the `model` branch (V1), **always use the latest version.**  
> The `model` branch is continuously updated to reflect the data structures from the main branch, ensuring compatibility with updates sent by services using the full library.

---

### Overview
`fint-core-autorelation-lib` is a library that combines the **autorelation model** definitions and **cache logic** used for managing relations between entities in the FINT ecosystem.  
It was originally part of the first iteration of the **Autorelation** architecture, where both the consumer application and the `fint-core-autorelation` service contained logic for determining which relations were controlled or managed.

### Purpose
The library centralizes both:
- **Model definitions** (data classes representing relations and relation events)
- **Cache logic** for determining which relations are controlled and should be maintained by the service

This makes it possible for a service to understand and cache control over relations locally, without depending on a centralized autorelation service.

### Relationship to the `model` Branch
There is also a lighter version of this library available in the **`model` branch** of this repository.  
That branch contains **only the model classes** — no caching or control logic.

The `model` branch is intended for scenarios where a **dedicated Autorelation Service** exists to manage and publish relation updates. In those cases:
- The **Autorelation Service** uses the full `fint-core-autorelation-lib` (main branch / V2) to determine and control which relations should be updated, and to publish relation entities or events.
- The **consumers** use the lightweight `model` branch (V1) to deserialize and process these relation entities, without containing any logic for determining or caching relation control.

### Typical Use Cases
| Scenario | Recommended Version | Description |
|-----------|---------------------|--------------|
| **Standalone consumer** that needs to determine and cache which relations it controls | **V2** (`main` branch) | Includes both model definitions and cache logic for relation control |
| **Centralized autorelation setup** where an Autorelation Service manages and sends relation updates to consumers | **V2** (`main` branch) for the Autorelation Service, **V1** (`model` branch) for consumers | The service uses the lib to control and publish relation updates; consumers only depend on the model definitions to handle them |

---

### Usage
#### For the `model` Branch (V1)
- **Always use the latest version.**  
  The `model` branch is kept in sync with the main branch to ensure full compatibility with updates sent from the Autorelation Service.
- Consumers should only depend on this branch for model classes and should **not** contain or implement any autorelation logic.

#### For the `main` Branch (V2)
Use this version if your service needs to:
- Determine which relations it controls
- Cache and manage local relation control state
- Act as the Autorelation Service that publishes relation updates

---

### Migration Note
During the transition to the current architecture, consumers that previously used the main branch (`fint-core-autorelation-lib`) can migrate to using the **`model` branch** when an Autorelation Service is introduced.  
This reduces duplication and simplifies consumer implementations by removing unnecessary control logic, while the Autorelation Service continues to use the full library to manage and distribute relation updates.
