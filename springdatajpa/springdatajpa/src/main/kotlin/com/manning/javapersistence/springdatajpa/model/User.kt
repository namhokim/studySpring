package com.manning.javapersistence.springdatajpa.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "USERS")
data class User(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val username: String,
    val registrationDate: LocalDate,
)
