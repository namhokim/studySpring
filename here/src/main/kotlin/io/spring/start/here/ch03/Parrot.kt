package io.spring.start.here.ch03

import org.springframework.stereotype.Component

@Component
data class Parrot(
    val name: String = "Koko",
) {
    override fun toString(): String {
        return "Parrot: $name"
    }
}
