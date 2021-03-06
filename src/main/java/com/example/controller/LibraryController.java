package com.example.controller;

import com.example.model.Book;
import com.example.model.User;
import com.example.service.LibraryService;
import com.example.service.UserService;
import com.example.utils.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
    @RestController
    public class LibraryController {
        @Resource
        LibraryService libraryServiceImpl;
        @GetMapping("/api/books")
        public  AjaxResult getUser(){
            List<Book> books=libraryServiceImpl.getAllBooks();
            if(books.size()>0){
                return new AjaxResult(200,books,"");
            }
            return new AjaxResult();
        }
        @PostMapping("/api/books")
        public  AjaxResult saveBook(@RequestBody Book book){
            int status=libraryServiceImpl.saveBook(book);
            if(status==1){
                return new AjaxResult(200,status,"");
            }
            return new AjaxResult();
        }
}
