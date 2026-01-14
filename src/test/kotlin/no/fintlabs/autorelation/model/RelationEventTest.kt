package no.fintlabs.autorelation.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RelationEventTest {
    @Test
    fun `init throws exception if orgId contains dash or uppercase`() {
        val ex1 =
            assertThrows<IllegalArgumentException> {
                RelationEvent("fintlabs-no", mockDescriptor(), "1", "data", RelationOperation.ADD)
            }
        assertEquals("OrgId must use dot instead of dash: fintlabs-no", ex1.message)

        val ex2 =
            assertThrows<IllegalArgumentException> {
                RelationEvent("Fintlabs.no", mockDescriptor(), "1", "data", RelationOperation.ADD)
            }
        assertEquals("OrgId must be lowercase: Fintlabs.no", ex2.message)
    }

    @Test
    fun `createAddEvent parses kafka topic correctly`() {
        val topic = "fintlabs-no.fint-core.entity.utdanning-vurdering-elevfravar"
        val key = "101"
        val value = "someData"

        val event = createAddEvent(key, topic, value)

        assertEquals("fintlabs.no", event.orgId) // Converted to dot
        assertEquals("utdanning", event.sourceEntity.domainName)
        assertEquals("vurdering", event.sourceEntity.packageName)
        assertEquals("elevfravar", event.sourceEntity.resourceName)
        assertEquals("101", event.sourceId)
        assertEquals(RelationOperation.ADD, event.operation)
    }

    @Test
    fun `createDeleteEvent creates valid event with dot orgId`() {
        val event =
            createDeleteEvent(
                domainName = "utdanning",
                packageName = "vurdering",
                resourceName = "elevfravar",
                orgId = "fintlabs-no", // Input with dash
                resource = "data",
                resourceId = "202",
            )

        assertEquals("fintlabs.no", event.orgId) // Converted
        assertEquals(RelationOperation.DELETE, event.operation)
        assertEquals("elevfravar", event.sourceEntity.resourceName)
    }

    private fun mockDescriptor() = EntityDescriptor("a", "b", "c")
}
