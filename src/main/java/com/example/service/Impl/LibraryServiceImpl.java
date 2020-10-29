package com.example.service.Impl;

import com.example.dao.LibraryMapper;
import com.example.model.Book;
import com.example.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    LibraryMapper mapper;
    @Override
    public List<Book> getAllBooks() {

        return mapper.getAll();
    }

    @Override
    public int saveBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        return mapper.saveBook(book);
    }
}
