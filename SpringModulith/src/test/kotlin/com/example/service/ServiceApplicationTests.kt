package com.example.service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class ServiceApplicationTests {

	@Test
	fun contextLoads() {
		val modules: ApplicationModules = ApplicationModules.of(ServiceApplication::class.java)
		modules.verify()
	}

}
