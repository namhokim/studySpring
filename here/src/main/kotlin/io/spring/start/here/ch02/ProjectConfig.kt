package io.spring.start.here.ch02

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProjectConfig {
    @Bean
    fun parrot(): Parrot {
        return Parrot("Polly")
    }
}
