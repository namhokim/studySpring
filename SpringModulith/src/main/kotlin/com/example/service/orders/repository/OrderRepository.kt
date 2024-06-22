package com.example.service.orders.repository

import com.example.service.orders.model.Order
import org.springframework.data.repository.ListCrudRepository

interface OrderRepository : ListCrudRepository<Order, Int> {
}
