package com.tistory.findnamo.boot3.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.tistory.findnamo.boot3.controller.TestEntity
import org.springframework.boot.autoconfigure.cache.CacheProperties
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext

@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfiguration(
    private val cacheProperties: CacheProperties,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun redisCacheConfiguration(): RedisCacheConfiguration {
        val redisProperties: Redis = cacheProperties.redis
        val config: RedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    Jackson2JsonRedisSerializer(objectMapper, TestEntity::class.java)
                )
            )
            .applyIfNotNull(redisProperties.timeToLive) { entryTtl(redisProperties.timeToLive) }
            .applyIfNotNull(redisProperties.keyPrefix) { prefixCacheNameWith(redisProperties.keyPrefix) }
            .applyIfNot(redisProperties.isCacheNullValues) { disableCachingNullValues() }
            .applyIfNot(redisProperties.isUseKeyPrefix) { disableKeyPrefix() }
        return config
    }

    private inline fun <T, R> T.applyIfNotNull(value: R?, block: T.(R) -> T): T =
        if (value != null) block(value) else this

    private inline fun <T> T.applyIfNot(condition: Boolean, block: T.() -> T): T =
        if (!condition) block() else this
}
