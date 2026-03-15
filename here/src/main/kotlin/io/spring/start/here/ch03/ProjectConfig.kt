package io.spring.start.here.ch03

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["io.spring.start.here.ch03"])
class ProjectConfig {
    @Bean
    fun parrot1(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    fun parrot2(): Parrot {
        return Parrot("Miki")
    }
}
