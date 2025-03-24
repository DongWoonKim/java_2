package com.example.spring.orderservice.event;

public record OrderAcceptedMessage(
        Long orderId
) {
}
