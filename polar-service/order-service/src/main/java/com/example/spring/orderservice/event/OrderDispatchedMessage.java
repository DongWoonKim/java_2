package com.example.spring.orderservice.event;

public record OrderDispatchedMessage(
    Long orderId
) {
}
