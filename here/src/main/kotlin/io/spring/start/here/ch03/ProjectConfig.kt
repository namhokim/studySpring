package io.spring.start.here.ch03

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProjectConfig {
    @Bean
    fun parrot(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    fun person(): Person {
        val p = Person("ella")
        p.parrot = parrot()
        return p
    }
}
