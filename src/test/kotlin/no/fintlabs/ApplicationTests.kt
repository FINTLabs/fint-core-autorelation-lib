package no.fintlabs

import no.fintlabs.metamodel.MetamodelService
import org.junit.jupiter.api.Test
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class ApplicationTests {
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan("no.fintlabs")
    class TestConfig

    @MockitoBean
    lateinit var metamodelService: MetamodelService

    @Test
    fun contextLoads() {
    }
}
