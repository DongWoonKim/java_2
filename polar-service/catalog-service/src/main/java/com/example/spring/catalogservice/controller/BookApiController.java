package com.example.spring.catalogservice.controller;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookApiController {

    private final BookService bookService;

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookService.viewBookList();
    }

}
