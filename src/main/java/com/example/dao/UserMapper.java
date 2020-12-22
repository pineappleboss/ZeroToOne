package com.example.dao;

import com.example.model.AdminMenu;
import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    List<User> findAllUser();
    User getByUsernameAndPassword(String username,String password);

    int save(User user);

    /**
     * 当前用户所有的权限
     * @param user
     * @return
     */
    List<AdminMenu> getAdminMenu(User user);

    /**
     * 当前拥有访问权限的接口
     * @param user
     * @return
     */
    List<Permission> getPermission(User user);

    User getUserById(String id);

    User getUserByToken(String token);

    List<Role> getRoleById(String id);

}