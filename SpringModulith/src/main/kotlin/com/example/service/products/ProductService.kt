package com.example.service.products

import com.example.service.orders.OrderPlacedEvent
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Service

@Service
class ProductService {

    @ApplicationModuleListener
    fun on(event: OrderPlacedEvent) {
        println("Starting: $event")
        Thread.sleep(10_000)
        println("Stopping: $event")
    }

}
