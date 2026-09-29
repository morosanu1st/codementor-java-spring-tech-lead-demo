package com.demo.orderservice.controller;

import com.demo.orderservice.model.Order;
import com.demo.orderservice.service.OrderService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

/**
 * HTTP boundary for orders.
 * Teaching point: validate at the edge, delegate to the service, map failures to status codes.
 */
@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Order created = orderService.create(request.customerId(), request.total());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    public record CreateOrderRequest(
            @NotBlank String customerId,
            @DecimalMin("0.01") BigDecimal total
    ) {
    }
}
