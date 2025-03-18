package com.example.spring.catalogservice.persistence;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.domain.BookRespository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRespository implements BookRespository {

    private static final Map<String, Book> books = new ConcurrentHashMap<>();


    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ?
                Optional.of(books.get(isbn)) :
                Optional.empty();
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return books.containsKey(isbn);
    }
}
