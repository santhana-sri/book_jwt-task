package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;

    @Override
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return repo.findById(isbn);
    }

    @Override
    public Book addBook(Book book) {
        return repo.save(book);
    }

    @Override
    public Book updateBook(String isbn, Book updatedBook) {
        Book book = repo.findById(isbn).orElseThrow();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        return repo.save(book);
    }

    @Override
    public void deleteBook(String isbn) {
        repo.deleteById(isbn);
    }
}
