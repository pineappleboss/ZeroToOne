package com.example.dao;

import com.example.model.AdminMenu;
import com.example.model.Role;
import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);

    int save(User user);

    /**
     * 当前用户所有的权限
     * @param user
     * @return
     */
    List<AdminMenu> getAdminMenu(User user);

}