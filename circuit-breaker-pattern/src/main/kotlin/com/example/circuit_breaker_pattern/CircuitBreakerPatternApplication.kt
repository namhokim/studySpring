package com.example.circuit_breaker_pattern

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CircuitBreakerPatternApplication

fun main(args: Array<String>) {
	runApplication<CircuitBreakerPatternApplication>(*args)
}
