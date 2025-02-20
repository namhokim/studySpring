package com.example.circuit_breaker_pattern

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MyService(
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    // Circuit Breaker 적용
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallback")
    fun callExternalService(): String? {
        // 외부 서비스 호출 (예: REST API)
        logger.info { "Calling external service" }
        return restTemplate.getForObject("https://example.com/api", String::class.java)
    }

    // Fallback 메서드 (외부 서비스 실패 시 호출됨)
    fun fallback(ex: Exception?): String {
        logger.info { "Fallback exception: $ex" }
        return "Fallback response: Service is unavailable"
    }

    private val logger: KLogger = KotlinLogging.logger {}

}
