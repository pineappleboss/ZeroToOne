package com.example.service;

import com.example.model.User;

public interface UserService {
    public boolean isExist(String username);

    public User getByName(String username);

    public User get(String username, String password);

    public void add(User user);
}
