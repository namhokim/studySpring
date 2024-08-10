package com.example.service.orders.service

import com.example.service.orders.event.OrderPlacedEvent
import com.example.service.orders.model.Order
import com.example.service.orders.repository.OrderRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val publisher: ApplicationEventPublisher,
) {

    fun place(order: Order) {
        val saved: Order = orderRepository.save(order)
        println("Order placed: $saved")
        publisher.publishEvent(OrderPlacedEvent(orderId = saved.id))
    }

}
