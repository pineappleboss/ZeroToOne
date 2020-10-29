package com.example.model;

import lombok.Data;

import java.util.Date;
@Data
public class Book {
    private String id;
    private String cover;
    private String title;
    private String author;
    private Date date;
    private String press;
    private String abs;
    private String cid;

}
