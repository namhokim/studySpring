package com.manning.javapersistence.springdatajpa.repositories

import com.manning.javapersistence.springdatajpa.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findAllByOrderByUsernameAsc(): List<User>
    fun findByRegistrationDateBetween(start: LocalDate, end: LocalDate): List<User>
    fun findByUsernameAndEmail(username: String, email: String): User?
    // ..
}
