package com.example.spring.dispatcherservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.logging.Logger;

@Configuration
public class DispatchingFunctions {

    private static final Logger log = Logger.getLogger(DispatchingFunctions.class.getName());

    @Bean
    public Function<OrderAcceptedMessage, Long> pack() {
        return orderAcceptedMessage -> {
            log.info("The order with id {} is packed." + orderAcceptedMessage.orderId());
            return orderAcceptedMessage.orderId();
        };
//        return new Function<OrderAcceptedMessage, Long>() {
//            @Override
//            public Long apply(OrderAcceptedMessage orderAcceptedMessage) {
//                log.info("The order with id {} is packed." + orderAcceptedMessage.orderId());
//                return orderAcceptedMessage.orderId();
//            }
//        };
    }

    @Bean
    public Function<Flux<Long>, Flux<OrderAcceptedMessage>> label() {
        return orderFlux -> orderFlux.map( orderId -> {
            log.info("The order with id {} is labeled." + orderId);
            return new OrderAcceptedMessage(orderId);
        });
    }

}
