package com.example.spring.catalogservice.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRespository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
