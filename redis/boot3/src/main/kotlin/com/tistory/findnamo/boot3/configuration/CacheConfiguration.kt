package com.tistory.findnamo.boot3.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.tistory.findnamo.boot3.controller.TestEntity
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration

@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfiguration(
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
        return RedisCacheManagerBuilderCustomizer { builder ->
            builder
                .withCacheConfiguration(
                    "test", RedisCacheConfiguration
                        .defaultCacheConfig().entryTtl(Duration.ofMinutes(1))
                        .serializeValuesWith(
                            RedisSerializationContext.SerializationPair.fromSerializer(
                                Jackson2JsonRedisSerializer(objectMapper, TestEntity::class.java)
                            )
                        )
                )
        }
    }
}
