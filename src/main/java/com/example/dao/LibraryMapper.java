package com.example.dao;

import com.example.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LibraryMapper {
    List<Book> getAll(@Param("id") String id);
    int saveBook(Book book);

    int updateBook(Book book);
}
