package com.example.service.Impl;


import com.example.dao.UserMapper;
import com.example.model.*;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取所有用户权限
     * @param user
     * @return
     */
    @Override
    public List<Permission> getPermission(User user) {
        List list=userMapper.getPermission(user);
        return  userMapper.getPermission(user);
    }

    @Override
    public List<User> getAlluser() {

        return userMapper.findAllUser();
    }

    @Override
    public User getUserById(String id) {
        User user=userMapper.getUserById(id);
        user.setRole(this.getRoleById(user.getId()));
        String[] access=user.getAccesses().split(",");
        user.setAccess(access);
        return user;
    }

    @Override
    public User getUserByToken(String token) {


        return userMapper.getUserByToken(token);
    }

    @Override
    public List<Role> getRoleById(String id) {
        return userMapper.getRoleById(id);
    }

    @Override
    public List<Role> getRole() {
        return userMapper.getRole();
    }
    @Transactional
    @Override
    public int saveUser(User user) {
        List<UserRoleMap> userRoleMaps=new ArrayList<>();
        List<Role> roles=user.getRole();
        roles.stream().forEach(x->{
            UserRoleMap userRoleMap=new UserRoleMap();
            userRoleMap.setUid(user.getId());
            userRoleMap.setRid(x.getId());
            userRoleMaps.add(userRoleMap);
        });
        int info=userMapper.updateUser(user);
        info=info+userMapper.delRole(user.getId());
        info=info+userMapper.insRoles(userRoleMaps);

        user.setRole(null);
        return info;
    }

    @Override
    public List<Permission> getUserPermission(String id) {
        User user=userMapper.getUserById(id);
        String[] permission=(user.getPerssion()==null)?new String[]{}:user.getPerssion().split(",");
        List<Permission> permissions=userMapper.getAllPermission(id);
        for(int i=0;i<permissions.size();i++){
            List<Permission> childPermission=new ArrayList<>();
            for(int j=0;j<permissions.size();j++){
                for(int k=0;k<permission.length;k++){
                    if(permission[k].equals(permissions.get(j).getUrl())){
                    //设备选中属性
                        permissions.get(j).setChecked(true);
                    }
                }
                if(permissions.get(j).getPid()==permissions.get(i).getId()){
                    permissions.get(i).setExpand(true);
                    childPermission.add(permissions.get(j));
                }
            }
            permissions.get(i).setChildren(childPermission);
        }
        //取出处理过的返回值
        return permissions.stream().filter(x->x.getPid()==0).collect(Collectors.toList());
    }


    /**
     * 判断此接口是否是用户拥有的
     * @param UrlName
     * @return
     */
     public boolean is(String UrlName){

            return  true;
     }
}
