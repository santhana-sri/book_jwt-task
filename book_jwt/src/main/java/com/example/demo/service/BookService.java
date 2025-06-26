package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookByIsbn(String isbn);
    Book addBook(Book book);
    Book updateBook(String isbn, Book book);
    void deleteBook(String isbn);
}
