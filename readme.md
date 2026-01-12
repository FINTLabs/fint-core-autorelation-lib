# FINT AutoRelation Library

AutoRelation is a lightweight library that automatically synchronizes bidirectional relationships between FINT resources. It ensures that when a "Trigger" resource is updated, its related "Target" resources are notified and updated to maintain consistency.

## 🚀 How It Works

1.  **Scan:** On startup, the `RelationRuleBuilder` scans the FINT Metamodel.
2.  **Filter:** It looks for relations that are **1-to-Many** or **0-to-Many** within the same domain.
3.  **Register:** It builds a registry of `RelationSyncRule`s.
4.  **Sync:** When your application receives a resource, it queries the registry to see if other resources need updating.

---

## 🔑 Key Components

### 1. RelationRuleRegistry (`@Component`)
The main entry point. It acts as a read-only lookup engine for synchronization rules.

**Key Methods:**
| Method | Description |
| :--- | :--- |
| `hasRules(trigger)` | Checks if the incoming resource triggers any updates. |
| `getRules(trigger)` | Returns the list of rules (what targets to update). |
| `getInverseRelations(target)` | Returns which fields on a target resource are controlled by this library. |

### 2. RelationSyncRule
Defines the "contract" for a synchronization update.

| Field | Description | Example |
| :--- | :--- | :--- |
| **forwardRelation** | The field name on the *Trigger* pointing to the Target. | `"elevfravar"` |
| **inverseRelation** | The field name on the *Target* that needs updating. | `"fravarsregistreringer"` |
| **targetType** | The `ResourceType` of the Target. | `utdanning.vurdering.elevfravar` |

### 3. ResourceType
A unique identifier for any FINT resource, consisting of:
* **Domain** (e.g., `utdanning`)
* **Package** (e.g., `vurdering` or `source`)
* **Resource** (e.g., `elevfravar`)

---

## 💡 Example Scenario

**Scenario:** You receive a new `Fravarsregistrering` (Absence Registration). This is the **Trigger**.
**Goal:** You must add this registration to the list of absences on the `Elevfravar` resource. This is the **Target**.

**The Flow:**
1.  **Input:** System receives `Fravarsregistrering`.
2.  **Lookup:** System calls `registry.getRules(FravarsregistreringType)`.
3.  **Rule Found:**
    * *Target:* `Elevfravar`
    * *Inverse Field:* `fravarsregistreringer`
4.  **Action:** System updates the `Elevfravar` resource by adding the ID of the new registration to its `fravarsregistreringer` list.

---

## 🛠️ Usage

Inject the registry into your service:

```kotlin
@Service
class MySyncService(
    private val registry: RelationRuleRegistry
) {
    fun handleResource(resource: Resource) {
        val triggerType = resource.toResourceType() // Your mapping logic
        
        if (registry.hasRules(triggerType)) {
            val rules = registry.getRules(triggerType)
            rules.forEach { rule ->
                // Perform the update on rule.targetType
                updateTarget(rule, resource)
            }
        }
    }
}