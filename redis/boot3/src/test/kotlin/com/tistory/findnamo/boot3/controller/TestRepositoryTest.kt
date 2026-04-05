package com.tistory.findnamo.boot3.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestRepositoryTest {

    private val testRepository = TestRepository()

    @Test
    fun `find should return the same name for the same id`() {
        val id = 1L
        val result1 = testRepository.find(id)
        val result2 = testRepository.find(id)

        assertEquals("Name_$id", result1.name)
        assertEquals(result1.name, result2.name)
    }

    @Test
    fun `find should return different names for different ids`() {
        val id1 = 1L
        val id2 = 2L
        val result1 = testRepository.find(id1)
        val result2 = testRepository.find(id2)

        assertEquals("Name_$id1", result1.name)
        assertEquals("Name_$id2", result2.name)
    }
}
