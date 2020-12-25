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
    public List<Book> getAllBooks(String id) {

        return mapper.getAll(id);
    }

    @Override
    public int saveBook(Book book) {
        if(book.getId()==null){
            book.setId(UUID.randomUUID().toString());
            mapper.saveBook(book);
        }else{
            mapper.updateBook(book);
        }

        return 1;
    }
}
