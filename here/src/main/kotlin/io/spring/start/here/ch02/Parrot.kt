package io.spring.start.here.ch02

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
data class Parrot(
    var name: String? = null,
) {
    @PostConstruct
    fun init() {
        this.name = "Parrot"
    }
}
