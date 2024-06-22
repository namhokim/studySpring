package com.example.service.orders

import com.example.service.orders.configuration.OrdersIntegrationConfiguration
import org.springframework.modulith.events.Externalized

@Externalized(target = OrdersIntegrationConfiguration.ORDERS_DESTINATION)
data class OrderPlacedEvent(
    val orderId: Int,
)
