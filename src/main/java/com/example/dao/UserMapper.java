package com.example.dao;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);

    int save(User user);
}