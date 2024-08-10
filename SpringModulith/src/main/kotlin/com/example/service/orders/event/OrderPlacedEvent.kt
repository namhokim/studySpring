package com.example.service.orders.event

import com.example.service.orders.configuration.OrdersIntegrationConfiguration
import com.example.service.products.ProductService
import org.springframework.modulith.ApplicationModule
import org.springframework.modulith.events.Externalized

@ApplicationModule(allowedDependencies = ["products"])
@Externalized(target = OrdersIntegrationConfiguration.ORDERS_DESTINATION)
data class OrderPlacedEvent(
    val orderId: Int,
)
