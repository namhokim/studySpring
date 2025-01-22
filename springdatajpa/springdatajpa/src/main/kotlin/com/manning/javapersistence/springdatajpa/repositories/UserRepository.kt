package com.manning.javapersistence.springdatajpa.repositories

import com.manning.javapersistence.springdatajpa.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
}
