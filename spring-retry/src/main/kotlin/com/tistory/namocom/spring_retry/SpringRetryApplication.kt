package com.tistory.namocom.spring_retry

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.retry.annotation.EnableRetry
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableRetry	// (order = Ordered.LOWEST_PRECEDENCE + 1)
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE - 2)
@SpringBootApplication
class SpringRetryApplication(
	private val applicationService: ApplicationService,
) {

	@Bean
	fun run(): CommandLineRunner {
		return CommandLineRunner {
			println("Hello, Spring Retry!")
			applicationService.run()
		}
	}

}

fun main(args: Array<String>) {
	runApplication<SpringRetryApplication>(*args)
}
