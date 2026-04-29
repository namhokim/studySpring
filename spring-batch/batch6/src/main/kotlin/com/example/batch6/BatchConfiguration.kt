package com.example.batch6

import org.springframework.batch.core.job.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.job.parameters.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.Step
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class BatchConfiguration {

    @Bean
    fun reader(): FlatFileItemReader<Person> =
        FlatFileItemReaderBuilder<Person>()
            .name("personItemReader")
            .resource(ClassPathResource("sample-data.csv"))
            .delimited()
            .names("firstName", "lastName")
            .fieldSetMapper { fs ->
                Person(
                    firstName = fs.readString("firstName")!!,
                    lastName = fs.readString("lastName")!!,
                )
            }
            .build()

    @Bean
    fun processor(): PersonItemProcessor = PersonItemProcessor()

    @Bean
    fun writer(dataSource: DataSource): JdbcBatchItemWriter<Person> =
        JdbcBatchItemWriterBuilder<Person>()
            .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
            .dataSource(dataSource)
            .beanMapped()
            .build()

    @Bean
    fun importUserJob(
        jobRepository: JobRepository,
        step1: Step,
        listener: JobCompletionNotificationListener,
    ): Job =
        JobBuilder("importUserJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .listener(listener)
            .start(step1)
            .build()

    @Bean
    fun step1(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        reader: FlatFileItemReader<Person>,
        processor: PersonItemProcessor,
        writer: JdbcBatchItemWriter<Person>,
    ): Step =
        StepBuilder("step1", jobRepository)
            .chunk<Person, Person>(3)
            .transactionManager(transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build()
}