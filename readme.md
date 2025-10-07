# 🆕 Update Notice (October 2025)

> **Deprecation Notice:**  
> The `model` branch (V1) is now **deprecated** and will no longer be maintained.
>
> Starting from this release, the main library (V2.2) introduces **lazy caching**, meaning the cache is only initialized when accessed. This eliminates the need for consumers to use a separate lightweight model branch.
>
> All consumers should now depend directly on the main branch (`main` / V2), which provides both the model definitions and the new efficient caching mechanism.

---

## fint-core-autorelation-lib

### Versioning
The repository previously followed a two-version structure that reflected different use cases:

| Version | Branch | Description |
|----------|---------|-------------|
| **V1 (Deprecated)** | `model` | Lightweight version containing only model classes. Intended for consumers that receive and process relation updates sent from an Autorelation Service. |
| **V2 (Active)** | `main` | Full version that includes both model definitions and autorelation cache logic for determining which relations are controlled. Used by services that perform relation control and publish updates. |

> ⚠️ **Important:**  
> The `model` branch (V1) is **deprecated**.  
> Consumers should migrate to the `main` branch (V2), which now supports **lazy caching** and can safely be used in all scenarios, including lightweight consumers.

---

### Overview
`fint-core-autorelation-lib` is a library that combines the autorelation model definitions and cache logic used for managing relations between entities in the FINT ecosystem.  
It was originally part of the first iteration of the Autorelation architecture, where both the consumer application and the `fint-core-autorelation` service contained logic for determining which relations were controlled or managed.

---

### Purpose
The library centralizes both:

- **Model definitions** — data classes representing relations and relation events
- **Cache logic** — determining which relations are controlled and should be maintained by the service

With the new **lazy caching** approach, the cache is initialized only when it is first accessed, minimizing memory footprint and improving startup performance.

This makes it possible for both lightweight consumers and full autorelation services to use the same library efficiently.

---

### Relationship to the Model Branch
The `model` branch is now **deprecated**. It previously contained only the model classes without caching or control logic.

All consumers should now depend on the **main branch (V2)**, which includes:
- Model definitions
- Autorelation control logic
- Lazy caching for on-demand initialization

This simplifies versioning and ensures compatibility between producers and consumers of relation data.

---

### Typical Use Cases
| Scenario | Recommended Version | Description |
|-----------|--------------------|--------------|
| Service or consumer determining and caching relations it controls | **V2 (main)** | Includes both model definitions and cache logic with lazy initialization |
| Centralized autorelation setup where an Autorelation Service manages and sends relation updates to consumers | **V2 (main)** | Both the service and consumers can safely use the main branch thanks to lazy caching |

---

### Migration Note
Consumers that previously used the `model` branch should now migrate to the `main` branch.  
The new lazy caching design ensures that the additional logic will not impact consumers that only need to deserialize or process relation updates.
