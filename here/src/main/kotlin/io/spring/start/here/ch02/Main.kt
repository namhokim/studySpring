package io.spring.start.here.ch02

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.function.Supplier

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
    val x = Parrot("Juju")
    context.registerBean("parrot1", Parrot::class.java, Supplier { x })
    val parrot = context.getBean(Parrot::class.java)
    println(parrot.name)
}
