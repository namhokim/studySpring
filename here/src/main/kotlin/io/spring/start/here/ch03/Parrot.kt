package io.spring.start.here.ch03

data class Parrot(
    val name: String = "Koko"
) {
    override fun toString(): String {
        return "Parrot: $name"
    }
}
