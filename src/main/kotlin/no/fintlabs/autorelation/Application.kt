package no.fintlabs.autorelation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FintCoreAutorelationLibApplication

fun main(args: Array<String>) {
    runApplication<FintCoreAutorelationLibApplication>(*args)
}
