package com.example.spring.orderservice.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class BookClient {

    private static final String BOOKS_ROOT_API = "/books/";
    private final WebClient webClient;
    
    public Mono<Book> getBookByIsbn(String isbn) {
        return webClient.get()
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .retrieve() // 요청을 보내고 응답을 기다린다
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }

}
