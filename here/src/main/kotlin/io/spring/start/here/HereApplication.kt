package io.spring.start.here

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HereApplication

fun main(args: Array<String>) {
	runApplication<HereApplication>(*args)
}
