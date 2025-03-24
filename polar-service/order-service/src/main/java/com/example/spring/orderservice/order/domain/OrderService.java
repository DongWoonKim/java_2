package com.example.spring.orderservice.order.domain;

import com.example.spring.orderservice.book.Book;
import com.example.spring.orderservice.book.BookClient;
import com.example.spring.orderservice.event.OrderAcceptedMessage;
import com.example.spring.orderservice.event.OrderDispatchedMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.spring.orderservice.order.domain.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final BookClient bookClient;
    private final StreamBridge streamBridge;

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> submitOrder(String isbn, int quantity) {
        return bookClient.getBookByIsbn(isbn)
                .map(book -> buildAcceptedOrder(book, quantity))
                .defaultIfEmpty(buildRejectedOrder(isbn, quantity))
                .flatMap(orderRepository::save)
                .doOnNext(this::publishOrderAcceptedEvent);

    }

    public Flux<Order> consumerOrderDispatchedEvent(Flux<OrderDispatchedMessage> orderDispatchedMessageFlux) {
        return orderDispatchedMessageFlux
                .flatMap(orderDispatchedMessage -> orderRepository.findById(orderDispatchedMessage.orderId()))
                .map(this::buildDispatchedOrder)
                .flatMap(orderRepository::save);
    }

    private void publishOrderAcceptedEvent(Order order) {
        if (!order.status().equals(ACCEPTED)) {
            return;
        }

        OrderAcceptedMessage orderAcceptedMessage = new OrderAcceptedMessage(order.id());
        log.info("Publishing order accepted event id : {}", orderAcceptedMessage.orderId());

        boolean result = streamBridge.send("acceptOder-out-0", orderAcceptedMessage);
        log.info("Result of sending order accepted event : {}, id : {}", result, orderAcceptedMessage.orderId());
    }

    private Order buildDispatchedOrder(Order existingOrder) {
        return Order.builder()
                .id(existingOrder.id())
                .bookIsbn(existingOrder.bookIsbn())
                .bookName(existingOrder.bookName())
                .quantity(existingOrder.quantity())
                .bookPrice(existingOrder.bookPrice())
                .status(DISPATCHED)
                .createdDate(existingOrder.createdDate())
                .lastModifiedDate(existingOrder.lastModifiedDate())
                .version(existingOrder.version())
                .build();
    }

    private static Order buildAcceptedOrder(Book book, int quantity) {
        return Order.builder()
                .bookIsbn(book.isbn())
                .bookName(book.title() + "-" + book.author())
                .bookPrice(book.price())
                .quantity(quantity)
                .status(ACCEPTED)
                .build();
    }

    private static Order buildRejectedOrder(String bookIsbn, int quantity) {
        return Order.builder()
                .bookIsbn(bookIsbn)
                .quantity(quantity)
                .status(REJECTED)
                .build();
    }
}
