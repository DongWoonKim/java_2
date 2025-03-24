package com.example.spring.catalogservice.service;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.domain.BookRespository;
import com.example.spring.catalogservice.exception.BookAlreadyExistsException;
import com.example.spring.catalogservice.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRespository bookRespository;

    public Iterable<Book> viewBookList() {
        return bookRespository.findAll();
    }

    public Book viewBook(String isbn) {
        return bookRespository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRespository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }

        return bookRespository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRespository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRespository.findByIsbn(isbn)
                .map(
                        existingBook -> {
                            Book bootToUpdate = Book.builder()
                                    .id(existingBook.id())
                                    .isbn(isbn)
                                    .title(book.title())
                                    .author(book.author())
                                    .price(book.price())
                                    .createAt(existingBook.createAt())
                                    .lastModifiedAt(existingBook.lastModifiedAt())
                                    .version(existingBook.version())
                                    .build();
                            return bookRespository.save(bootToUpdate);
                        }
                ).orElseGet(() -> bookRespository.save(book));
    }
}
