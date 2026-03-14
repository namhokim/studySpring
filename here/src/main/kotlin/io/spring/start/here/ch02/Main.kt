package io.spring.start.here.ch02

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
    val parrot = context.getBean(Parrot::class.java)
    println(parrot.name)
}
