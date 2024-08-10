package com.example.retryjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class RetryJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetryJavaApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RetryableService retryableService) throws Exception {
		return (String[] args) -> {
			retryableService.service();
		};
	}

}
