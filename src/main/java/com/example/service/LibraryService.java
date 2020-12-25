package com.example.service;

import com.example.model.Book;

import java.util.List;

/**
 * @author tkq
 */
public interface LibraryService {
    List<Book> getAllBooks(String id);
    int saveBook(Book book);
}
