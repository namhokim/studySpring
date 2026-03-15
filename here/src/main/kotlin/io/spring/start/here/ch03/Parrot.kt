package io.spring.start.here.ch03

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
data class Parrot @Autowired constructor(
    val person: Person,
) {
    val name: String = "Koko"

    override fun toString(): String {
        return "Parrot: $name"
    }
}
