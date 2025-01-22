package com.manning.javapersistence.springdatajpa

import com.manning.javapersistence.springdatajpa.model.User
import com.manning.javapersistence.springdatajpa.repositories.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDate
import java.time.Month

@SpringBootApplication
@Suppress("SpellCheckingInspection")
class SpringdatajpaApplication {
    // @Bean
    fun configure(userRepository: UserRepository) = ApplicationRunner {
        val user1 = User(username = "beth", registrationDate = LocalDate.of(2020, Month.AUGUST, 3))
        val user2 = User(username = "mike", registrationDate = LocalDate.of(2020, Month.JANUARY, 18))

        userRepository.save(user1)
        userRepository.save(user2)

        userRepository.findAll().forEach { println(it) }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringdatajpaApplication>(*args)
}
