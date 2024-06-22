package com.example.service.orders.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("orders_line_items")
data class LineItem(
    @Id val id: Int,
    val product: Int,
    val quantity: Int,
)
