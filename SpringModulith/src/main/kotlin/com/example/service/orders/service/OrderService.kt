package com.example.service.orders.service

import com.example.service.orders.model.Order
import com.example.service.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {

    fun place(order: Order) {
        val saved: Order = orderRepository.save(order)
        println("Order placed: $saved")
    }

}
