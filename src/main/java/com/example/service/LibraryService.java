package com.example.service;

import com.example.model.Book;

import java.util.List;

/**
 * @author tkq
 */
public interface LibraryService {
    List<Book> getAllBooks();
    int saveBook(Book book);
}
