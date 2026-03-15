package io.spring.start.here.ch03

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
    val person = context.getBean(Person::class.java)

    println("Person's name: : ${person.name}")
    println("Person's parrot: ${person.parrot}")
}
