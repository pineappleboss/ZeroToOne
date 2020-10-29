package com.example.dao;

import com.example.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LibraryMapper {
    List<Book> getAll();
    int saveBook(Book book);
}
