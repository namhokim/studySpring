package com.tistory.findnamo.boot4.controller

import com.tistory.findnamo.boot3.controller.TestEntity
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository

@Repository
class TestRepository {
    private val log = LoggerFactory.getLogger(javaClass)

    @Cacheable(cacheNames = ["test"], key = "#id")
    fun find(id: Long): TestEntity {
        log.info("[TestRepository] find id: $id")
        return TestEntity(id, "Name_$id")
    }

    @CacheEvict(cacheNames = ["test"], key = "#id")
    fun evict(id: Long) {
        log.info("[TestRepository] evict id: $id")
    }
}
