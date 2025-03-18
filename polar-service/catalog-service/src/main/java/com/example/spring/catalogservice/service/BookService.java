package com.example.spring.catalogservice.service;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.domain.BookRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRespository bookRespository;

    public Iterable<Book> viewBookList() {
        return bookRespository.findAll();
    }
}
