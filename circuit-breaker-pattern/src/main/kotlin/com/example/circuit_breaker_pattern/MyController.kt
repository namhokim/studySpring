package com.example.circuit_breaker_pattern

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MyController(
    private val myService: MyService,
) {

    @GetMapping("/call")
    fun callService(): String? {
        return myService.callExternalService()
    }

}
