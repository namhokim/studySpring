package io.spring.start.here.ch03

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProjectConfig {
    @Bean
    fun parrot1(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    fun parrot2(): Parrot {
        return Parrot("Miki")
    }

    @Bean
    fun person(@Qualifier("parrot2") parrot: Parrot): Person {
        return Person(parrot)
    }
}
