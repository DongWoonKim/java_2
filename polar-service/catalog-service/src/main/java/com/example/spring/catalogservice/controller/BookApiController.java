package com.example.spring.catalogservice.controller;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookApiController {

    private final BookService bookService;

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookService.viewBookList();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

}
