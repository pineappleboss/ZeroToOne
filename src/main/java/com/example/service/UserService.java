package com.example.service;

import com.example.model.AdminMenu;
import com.example.model.Permission;
import com.example.model.User;

import java.util.List;

public interface UserService {
    public boolean isExist(String username);

    public User getByName(String username);

    public User get(String username, String password);

    public void add(User user);

    public List<AdminMenu> getAdminMenu(User user);

    public List<Permission> getPermission(User user);

    public List<User> getAlluser();

    public User getUserById(String id);

    public User getUserByToken(String token);
}
