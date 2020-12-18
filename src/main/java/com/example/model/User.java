package com.example.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String id;
    private String name;
    private String access;
    private String userName;
    private String password;
    private String salt;
    private String role;
    private String avatar;
    private String token;
}
