package com.example.service.Impl;


import com.example.dao.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userMapper.findByUsername(username);
    }

    public User get(String username, String password){
        return userMapper.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userMapper.save(user);
    }
}
