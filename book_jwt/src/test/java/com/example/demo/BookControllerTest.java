package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book();
        book1.setIsbn("1111");
        book1.setTitle("Java Basics");
        book1.setAuthor("John");
        book1.setPublicationYear(2020);

        book2 = new Book();
        book2.setIsbn("2222");
        book2.setTitle("Spring Boot");
        book2.setAuthor("Jane");
        book2.setPublicationYear(2021);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetBookByIsbn() throws Exception {
        Mockito.when(bookService.getBookByIsbn("1111")).thenReturn(Optional.of(book1));

        mockMvc.perform(get("/api/books/1111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Basics"));
    }

    @Test
    public void testAddBook() throws Exception {
        Mockito.when(bookService.addBook(any(Book.class))).thenReturn(book1);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("1111"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Mockito.when(bookService.updateBook(any(String.class), any(Book.class))).thenReturn(book1);

        mockMvc.perform(put("/api/books/1111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Basics"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/1111"))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
