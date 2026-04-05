package com.tistory.findnamo.boot3.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService,
) {
    @GetMapping("/tests/{id}")
    fun find(@PathVariable id: Long): TestEntity = testService.find(id)

    @DeleteMapping("/tests/{id}")
    fun delete(@PathVariable id: Long) = testService.delete(id)
}
