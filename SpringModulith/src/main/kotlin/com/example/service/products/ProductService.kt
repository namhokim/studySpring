package com.example.service.products

import com.example.service.orders.OrderPlacedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductService {

    @EventListener
    fun on(event: OrderPlacedEvent) {
        println("Starting: $event")
        Thread.sleep(5_000)
        println("Stopping: $event")
    }

}
