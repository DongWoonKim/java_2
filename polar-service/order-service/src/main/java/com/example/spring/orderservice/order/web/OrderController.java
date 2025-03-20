package com.example.spring.orderservice.order.web;

import com.example.spring.orderservice.order.domain.Order;
import com.example.spring.orderservice.order.domain.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Flux<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public Mono<Order> submitOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.submitOrder(
                orderRequest.isbn(), orderRequest.quantity()
        );
    }

}
