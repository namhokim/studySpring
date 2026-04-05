package com.tistory.findnamo.boot4.configuration

import org.springframework.boot.cache.autoconfigure.CacheProperties
import org.springframework.boot.cache.autoconfigure.CacheProperties.Redis
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import tools.jackson.databind.json.JsonMapper

@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfiguration(
    private val cacheProperties: CacheProperties,
    private val jsonMapperBuilder: JsonMapper.Builder,
) {
    @Bean
    fun redisCacheConfiguration(): RedisCacheConfiguration {
        val redisProperties: Redis = cacheProperties.redis

        val config: RedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJacksonJsonRedisSerializer.builder { jsonMapperBuilder }
                        .enableUnsafeDefaultTyping()
                        .build()
                )
            )
            .applyIfNotNull(redisProperties.timeToLive) { timeToLive -> entryTtl(timeToLive) }
            .applyIfNotNull(redisProperties.keyPrefix) { keyPrefix -> prefixCacheNameWith(keyPrefix) }
            .applyIfNot(redisProperties.isCacheNullValues) { disableCachingNullValues() }
            .applyIfNot(redisProperties.isUseKeyPrefix) { disableKeyPrefix() }
        return config
    }

    private inline fun <T, R> T.applyIfNotNull(value: R?, block: T.(R) -> T): T =
        if (value != null) block(value) else this

    private inline fun <T> T.applyIfNot(condition: Boolean, block: T.() -> T): T =
        if (!condition) block() else this
}
