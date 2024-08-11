package com.example.service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class ServiceApplicationTests {

	@Test
	fun contextLoads() {
		val modules: ApplicationModules = ApplicationModules.of(ServiceApplication::class.java)
		modules.forEach { println("module: ${it.name} : ${it.basePackage}") }
		modules.verify()

		Documenter(modules)
			.writeIndividualModulesAsPlantUml()
			.writeModulesAsPlantUml()
            .writeModuleCanvases()
	}

}
