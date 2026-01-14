package no.fintlabs.autorelation.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityDescriptorTest {
    @Test
    fun `init throws exception if names are not lowercase`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                EntityDescriptor("Utdanning", "vurdering", "elevfravar")
            }
        assertEquals("Domain name must be lowercase: Utdanning", exception.message)
    }

    @Test
    fun `createEntityDescriptor converts inputs to lowercase`() {
        val descriptor = createEntityDescriptor("Utdanning", "Vurdering", "Elevfravar")

        assertEquals("utdanning", descriptor.domainName)
        assertEquals("vurdering", descriptor.packageName)
        assertEquals("elevfravar", descriptor.resourceName)
    }

    @Test
    fun `toEntityDescriptor parses valid string format`() {
        val input = "utdanning-vurdering-elevfravar"
        val descriptor = input.toEntityDescriptor()

        assertEquals("utdanning", descriptor.domainName)
        assertEquals("vurdering", descriptor.packageName)
        assertEquals("elevfravar", descriptor.resourceName)
    }

    @Test
    fun `toEntityDescriptor throws error on invalid format`() {
        assertThrows<IllegalStateException> { "utdanning-vurdering".toEntityDescriptor() }
        assertThrows<IllegalStateException> { "utdanning".toEntityDescriptor() }
        assertThrows<IllegalStateException> { "a-b-c-d".toEntityDescriptor() }
    }
}
