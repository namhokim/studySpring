package com.example.batch6

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class Batch6Application

fun main(args: Array<String>) {
    val context = runApplication<Batch6Application>(*args)
    exitProcess(SpringApplication.exit(context))
}
