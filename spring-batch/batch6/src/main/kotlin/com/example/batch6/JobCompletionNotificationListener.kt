package com.example.batch6

import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.job.JobExecution
import org.springframework.batch.core.listener.JobExecutionListener
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class JobCompletionNotificationListener(
    private val jdbcTemplate: JdbcTemplate,
) : JobExecutionListener {

    override fun afterJob(jobExecution: JobExecution) {
        if (jobExecution.status != BatchStatus.COMPLETED) return

        log.info("!!! JOB FINISHED! Time to verify the results")
        jdbcTemplate
            .query("SELECT first_name, last_name FROM people", DataClassRowMapper(Person::class.java))
            .forEach { log.info("Found <{}> in the database.", it) }
    }

    companion object {
        private val log = LoggerFactory.getLogger(JobCompletionNotificationListener::class.java)
    }
}