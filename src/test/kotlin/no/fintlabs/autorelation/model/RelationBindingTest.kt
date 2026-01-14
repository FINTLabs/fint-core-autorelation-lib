package no.fintlabs.autorelation.model

import io.mockk.every
import io.mockk.mockk
import no.fint.model.felles.kompleksedatatyper.Identifikator
import no.fint.model.resource.FintResource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RelationBindingTest {
    private val rule =
        RelationSyncRule(
            targetRelation = "school",
            inverseRelation = "student",
            targetType = EntityDescriptor("edu", "admin", "school"),
            isMandatory = true,
        )

    @Test
    fun `toRelationBinding creates valid binding when ID exists`() {
        val resourceId = "12345"
        val resource = mockk<FintResource>()

        val idObject = Identifikator().apply { identifikatorverdi = resourceId }
        val idMap = mapOf("systemId" to idObject)

        every { resource.identifikators } returns idMap

        val binding = rule.toRelationBinding(resource, resourceId)

        assertEquals("student", binding.relationName)
        assertEquals("systemId/12345", binding.link.href)
    }

    @Test
    fun `toRelationBinding throws ResourceIdMismatchException when ID is not found`() {
        val resourceId = "999" // This ID does not exist in the resource below
        val resource = mockk<FintResource>()

        val idObject = Identifikator().apply { identifikatorverdi = "12345" }
        every { resource.identifikators } returns mapOf("systemId" to idObject)

        val exception =
            assertThrows<ResourceIdMismatchException> {
                rule.toRelationBinding(resource, resourceId)
            }

        assertEquals("ID mismatch: Resource ID '999' not found in payload", exception.message)
    }
}
