package com.example.spring.catalogservice.service;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.domain.BookRespository;
import com.example.spring.catalogservice.exception.BookAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRespository bookRespository;

    public Iterable<Book> viewBookList() {
        return bookRespository.findAll();
    }

    public Book addBookToCatalog(Book book) {
        if (bookRespository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }

        return bookRespository.save(book);
    }
}
