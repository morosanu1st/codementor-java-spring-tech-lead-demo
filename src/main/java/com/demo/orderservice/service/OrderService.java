package com.demo.orderservice.service;

import com.demo.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Business layer for orders.
 * Teaching points for tech leads:
 * - Controllers stay thin; rules and orchestration live here
 * - Prefer clear Optional / exception boundaries over null returns
 * - In-memory store is fine for a demo; production would inject a repository
 */
@Service
public class OrderService {

    private final Map<String, Order> store = new ConcurrentHashMap<>();

    public OrderService() {
        store.put("demo-1", new Order(
                "demo-1",
                "cust-42",
                "CONFIRMED",
                new BigDecimal("19.99"),
                Instant.parse("2026-01-15T10:00:00Z")
        ));
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public Order create(String customerId, BigDecimal total) {
        String id = "ord-" + Instant.now().toEpochMilli();
        Order order = new Order(id, customerId, "PENDING", total, Instant.now());
        store.put(id, order);
        return order;
    }
}
