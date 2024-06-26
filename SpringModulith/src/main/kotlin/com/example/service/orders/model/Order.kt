package com.example.service.orders.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("orders")
data class Order(
    @Id val id: Int,
    val lineItems: Set<LineItem>,
)
