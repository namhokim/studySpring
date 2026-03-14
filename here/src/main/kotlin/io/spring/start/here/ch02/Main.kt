package io.spring.start.here.ch02

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    AnnotationConfigApplicationContext()
    val parrot = Parrot("Polly")
    println(parrot)
}
