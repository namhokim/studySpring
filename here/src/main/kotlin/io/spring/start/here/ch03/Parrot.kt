package io.spring.start.here.ch03

data class Parrot(
    val name: String = "Unknown",
) {
    init {
        println("Parrot created with name: $name")
    }

    override fun toString(): String {
        return "Parrot: $name"
    }
}
