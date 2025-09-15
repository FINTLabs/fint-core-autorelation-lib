package no.fintlabs.autorelation

import no.fintlabs.autorelation.cache.RelationCache
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import kotlin.test.Ignore

@Ignore
@SpringBootTest
class RelationCacheTest @Autowired constructor(
    private val relationCache: RelationCache
) {

    private val fintResourceRelations = createFintResourceRelations()

    @Test
    fun `verify FINT models in v3_19 exist within the cache`() {
        val resourceRelations = relationCache.getResourceRelations("utdanning", "vurdering")
        fintResourceRelations.forEach { (fintResource, fintRelationNames) ->
            val relationNames = resourceRelations[fintResource]
            assertNotNull(relationNames!!)
            assertTrue(fintRelationNames.all { relationNames.contains(it) })
        }
    }

    private fun createFintResourceRelations() = mapOf(
        "elevvurdering" to listOf(
            "eksamensvurdering",
            "halvarsfagvurdering",
            "halvarsordensvurdering",
            "sluttfagvurdering",
            "sluttordensvurdering",
            "underveisfagvurdering",
            "underveisordensvurdering"
        ),
        "eksamensgruppe" to listOf("gruppemedlemskap", "sensor"),
        "elevfravar" to listOf("fravarsregistrering")
    )

}