package com.example.service.Impl;


import com.example.dao.UserMapper;
import com.example.model.AdminMenu;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<AdminMenu> getAdminMenu(User user) {
        //获取当前用户角色对应的菜单

        List<AdminMenu> list=userMapper.getAdminMenu(user);

        //遍历的将数据转化为一棵树
        List<AdminMenu> adminMenus=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            List<AdminMenu> adminMenu=new ArrayList<>();
            int pid=list.get(i).getId();
            for (int j=0;j<list.size();j++){
                if(i!=j&&pid==list.get(j).getParentId()){
                    adminMenu.add(list.get(j));
                }
            }
            list.get(i).setChildren(adminMenu);
        }
        //去掉非顶层的菜单
        list.removeIf(m->m.getParentId()!=0);
        return list;
    }

}
