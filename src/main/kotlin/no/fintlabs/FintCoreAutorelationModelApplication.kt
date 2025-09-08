package no.fintlabs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FintCoreAutorelationModelApplication

fun main(args: Array<String>) {
    runApplication<FintCoreAutorelationModelApplication>(*args)
}
