package com.tistory.namocom.coverage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoverageApplication

fun main(args: Array<String>) {
	runApplication<CoverageApplication>(*args)
}
