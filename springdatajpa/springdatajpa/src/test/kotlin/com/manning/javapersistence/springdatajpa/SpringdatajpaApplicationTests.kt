package com.manning.javapersistence.springdatajpa

import com.manning.javapersistence.springdatajpa.model.User
import com.manning.javapersistence.springdatajpa.repositories.UserRepository
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import kotlin.test.assertEquals

@SpringBootTest
@Suppress("SpellCheckingInspection")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringdatajpaApplicationTests(
    @Autowired private val userRepository: UserRepository,
) {

    @BeforeAll
    fun beforeAll() {
        userRepository.saveAll(generateUsers())
    }

    @AfterAll
    fun afterAll() {
        userRepository.deleteAll()
    }

    private fun generateUsers(): List<User> {
        val john = User(username = "john", registrationDate = LocalDate.parse("2020-04-13"))
        john.email = "john@some.com"
        john.level = 1
        john.active = true

        val bart = User(username = "bart", registrationDate = LocalDate.parse("2020-05-13"))
        john.email = "bart@some.com"
        john.level = 10
        john.active = true

        val hazel = User(username = "hazel", registrationDate = LocalDate.parse("2020-10-13"))
        john.email = "hazel@some.com"
        john.level = 1
        john.active = true

        return listOf(john, bart, hazel)
    }

    @Test
    fun testFindAll() {
        val users: MutableList<User> = userRepository.findAll()
        assertEquals(3, users.size)
    }

    @Test
    fun testFindUser() {
        val john: User? = userRepository.findByUsername(username = "john")
        assertEquals("john", john?.username)
    }

    @Test
    fun testFindAllByOrderByUsernameAsc() {
        val users: List<User> = userRepository.findAllByOrderByUsernameAsc()
        assertAll(
            { assertEquals(3, users.size) },
            { assertEquals("bart", users[0].username) },
            { assertEquals("hazel", users[1].username) },
            { assertEquals("john", users[2].username) }
        )
    }

    @Test
    fun testFindByRegistrationDateBetween() {
        val users: List<User> = userRepository.findByRegistrationDateBetween(
            start = LocalDate.parse("2020-05-01"),
            end = LocalDate.parse("2020-10-31")
        )
        assertAll(
            { assertEquals(2, users.size) },
            { assertEquals("bart", users[0].username) },
            { assertEquals("hazel", users[1].username) }
        )
    }

}
