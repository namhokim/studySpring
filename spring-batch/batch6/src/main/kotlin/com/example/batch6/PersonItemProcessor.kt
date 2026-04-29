package com.example.batch6

import org.slf4j.LoggerFactory
import org.springframework.batch.infrastructure.item.ItemProcessor

class PersonItemProcessor : ItemProcessor<Person, Person> {

    override fun process(item: Person): Person {
        val transformed = Person(
            firstName = item.firstName.uppercase(),
            lastName = item.lastName.uppercase(),
        )
        log.info("Converting ({}) into ({})", item, transformed)
        return transformed
    }

    companion object {
        private val log = LoggerFactory.getLogger(PersonItemProcessor::class.java)
    }
}