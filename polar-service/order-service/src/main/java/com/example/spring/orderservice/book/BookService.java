package com.example.spring.orderservice.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookClient bookClient;

    public Flux<Book> getBooks() {
        return bookClient.getBooks()
                .defaultIfEmpty(buildMessageBook("Not found!"));
    }

    public Mono<Book> enrollBook(Book book) {
        return bookClient.getBookByIsbn(book.isbn())
                .flatMap(existingBook -> Mono.just(buildMessageBook("Already Exist!")))
                .switchIfEmpty(bookClient.enrollBook(book));
    }

    public Mono<Book> updateBook(String isbn, Book book) {
        return bookClient.getBookByIsbn(isbn)
                .flatMap(existingBook -> bookClient.updateBook(isbn, book))
                .defaultIfEmpty(buildMessageBook("Not found!"));
    }

    public Mono<Void> deleteBook(String isbn) {
        return bookClient.deleteBook(isbn);
    }

    private static Book buildMessageBook(String message) {
        return Book.builder()
                .message(message)
                .build();
    }
}
