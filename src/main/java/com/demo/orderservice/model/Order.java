package com.demo.orderservice.model;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Simple order representation used as a teaching DTO.
 * In a real service this might map from a persistence entity with clear ownership.
 */
public record Order(
        String id,
        String customerId,
        String status,
        BigDecimal total,
        Instant createdAt
) {
}
