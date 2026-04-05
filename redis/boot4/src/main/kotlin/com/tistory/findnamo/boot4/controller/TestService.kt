package com.tistory.findnamo.boot4.controller

import com.tistory.findnamo.boot3.controller.TestEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TestService(
    private val testRepository: TestRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun find(id: Long): TestEntity {
        log.info("[TestService] find id: $id")
        return testRepository.find(id)
    }

    fun delete(id: Long) {
        log.info("[TestService] delete id: $id")
        testRepository.evict(id)
    }
}
