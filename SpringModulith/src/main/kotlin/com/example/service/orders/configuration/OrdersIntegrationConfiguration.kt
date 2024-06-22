package com.example.service.orders.configuration

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OrdersIntegrationConfiguration {

    @Bean
    fun binding(queue: Queue, exchange: Exchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(ORDERS_DESTINATION).noargs()
    }

    @Bean
    fun queue(): Queue {
        return QueueBuilder.durable().build()
    }

    @Bean
    fun exchange(): Exchange {
        return ExchangeBuilder.directExchange(ORDERS_DESTINATION).durable(true).build()
    }

    companion object {
        const val ORDERS_DESTINATION = "orders"
    }

}
