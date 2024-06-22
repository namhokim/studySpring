package com.example.service.orders.endpoint

import com.example.service.orders.model.Order
import com.example.service.orders.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/orders")
@RestController
class OrdersController(
    private val orderService: OrderService,
) {

    @PostMapping
    fun place(@RequestBody order: Order) {
        orderService.place(order = order)
    }

}
