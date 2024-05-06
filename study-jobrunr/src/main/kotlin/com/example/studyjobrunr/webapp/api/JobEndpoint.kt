package com.example.studyjobrunr.webapp.api

import com.example.studyjobrunr.core.service.SimpleService
import org.jobrunr.scheduling.JobScheduler
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime.now

@RestController
@RequestMapping("/jobs")
class JobEndpoint(
    private val jobScheduler: JobScheduler,
) {

    @GetMapping(value = ["/simple-job"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun simpleJob(@RequestParam(defaultValue = "World") name: String): String {
        val enqueuedJobId = jobScheduler.schedule<SimpleService>(now().plusSeconds(5)) {
            it.doSimpleJob(anArgument = "Hello $name")
        }
        return "Job Enqueued: $enqueuedJobId"
    }

}
