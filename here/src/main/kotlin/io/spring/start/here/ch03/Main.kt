package io.spring.start.here.ch03

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
    val person = context.getBean(Person::class.java)
    val parrot = context.getBean(Parrot::class.java)

    println("Person: ${person.name}, Parrot: ${parrot.name}")
    println("Person's parrot: ${person.parrot}")
}
