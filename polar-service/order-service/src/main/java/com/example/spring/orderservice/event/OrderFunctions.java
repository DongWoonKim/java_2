package com.example.spring.orderservice.event;

import com.example.spring.orderservice.order.domain.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class OrderFunctions {

    private static final Logger log = LoggerFactory.getLogger(OrderFunctions.class);

    @Bean
    public Consumer<Flux<OrderDispatchedMessage>> dispatchOrder(OrderService orderService) {
        return flux -> orderService.consumerOrderDispatchedEvent(flux)
                .doOnNext(order -> log.info("Order dispatched id : {}", order.id()))
                .subscribe();
    }
}
