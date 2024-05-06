package com.example.studyjobrunr.core.service

import org.springframework.stereotype.Service

@Service
class SimpleService {
    fun doSimpleJob(anArgument: String) {
        println("Doing some work: $anArgument")
    }
}
