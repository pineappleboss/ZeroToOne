package com.example.controller;

import com.example.model.AdminMenu;
import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;
import com.example.utils.AjaxResult;
import com.example.utils.JWTUtil;
import com.example.utils.MD5Util;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tkq
 */
@CrossOrigin
@RestController
public class UserController {
    @Resource
    UserService userServiceImpl;
    /**
     * 用户登录
     * 核心在于 subject.login(usernamePasswordToken);
     * @param user
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("api/login")
    public AjaxResult login(@RequestBody User user){
        //获取subject

        //执行认证动作
            String salt="qqq";
            user.setSalt(salt);
            //加盐加密
            String pwd= MD5Util.MD5Encode(user.getPassword() + salt,"UTF-8");
            user.setPassword(pwd);
            User u=userServiceImpl.get(user.getUserName(),user.getPassword());

            if(u!=null) {
                //颁发凭证
                u.setToken(JWTUtil.sign(u.getUserName(), u.getId()));
                return new AjaxResult(200, u, "登录成功");
            }else {
                String message = "账号密码错误";
                return new AjaxResult(500, user, "登录失败，用户名或密码有误");
            }
    }

    /**
     * 用户注册
     * 数据存储的MD5加密后的密码和slat在shiro验证中还要使用
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("api/register")
    public  AjaxResult register(@RequestBody User user){
        user.setUserName(HtmlUtils.htmlEscape(user.getUserName()));
        //获取盐
        String salt=user.getUserName();
        user.setSalt(salt);
        //加盐加密
        String pwd= MD5Util.MD5Encode(user.getPassword() + salt,"UTF-8");
        user.setPassword(pwd);

        userServiceImpl.add(user);

        return new AjaxResult(200,user,"");
    }

    /**
     * 用户退出
     * 同样重点在于subject.logout()
     * @param
     * @return
     */
    @ApiOperation("用户退出")
    @GetMapping("/api/logout")
    public AjaxResult logout(){
        Subject subject=SecurityUtils.getSubject();
        //此方法会清除session
        subject.logout();
        return  new AjaxResult(200,null,"退出成功");
    }

    /**
     * 对所有前端对后端的访问进行验证，主要是走拦截器进行shiro验证
     * @return
     */
    @ApiOperation("对所有前端对后端的访问进行验证")
    @GetMapping("/api/authentication")
    public  AjaxResult authentication(){
        return new AjaxResult(200,null,"验证成功");
    }

    /**
     * 查询菜单
     * @param user
     * @return
     */
    @ApiOperation("查询菜单")
    @GetMapping("/api/menu")
    public AjaxResult getAdminMenu(@RequestBody  User user){
        List<AdminMenu> list=userServiceImpl.getAdminMenu(user);
        return  new AjaxResult(200,list,"");
    }
    /**
     * 用户接口查询
     */
    public AjaxResult getPermission(@RequestBody User user){
        return new AjaxResult(200,userServiceImpl.getPermission(user),"查询用户接口成功");
    }
    @ApiOperation("用户登录")
    @GetMapping("/api/user")
    public AjaxResult getAllUser(){

        return new AjaxResult(200,userServiceImpl.getAlluser(),"***Jenkins测试01##");
    }
    @ApiOperation("根据id获取用户信息")
    @GetMapping("/api/user/{id}")
    public AjaxResult getUserById(@PathVariable("id") String id){

        return new AjaxResult(200,userServiceImpl.getUserById(id),"***Jenkins测试01##");
    }
    @ApiOperation("根据token获取用户信息，主要用来判断用户是否登录")
    @GetMapping("/api/get_info")
    public AjaxResult getUserByToken(){
        User user=userServiceImpl.getUserById("5");
        if(user!=null){
            return new AjaxResult(200,user,"用户查询成功");
        }else{
            return new AjaxResult(500,null,"用户查询失败");
        }
    }
    @ApiOperation("获取用户信息")
    @GetMapping("api/message/count")
    public AjaxResult getCount(){
        User user=userServiceImpl.getUserById("1");
        if(user!=null){
            return new AjaxResult(200,user,"用户查询成功");
        }else{
            return new AjaxResult(500,user,"用户查询失败");
        }
    }
    @GetMapping("/api/role/{id}")
    public AjaxResult getRoleByid(@PathVariable("id") String id){
        List<Role> roles=userServiceImpl.getRoleById(id);
        if(roles!=null){
            return new AjaxResult(200,roles,"角色列表查询成功");
        }else{
            return new AjaxResult(500,roles,"角色列表查询成功");
        }
    }
    @GetMapping("/api/role")
    public AjaxResult getRole(){
        List<Role> roles=userServiceImpl.getRole();
        if(roles!=null){
            return new AjaxResult(200,roles,"角色列表查询成功");
        }else{
            return new AjaxResult(500,roles,"角色列表查询成功");
        }
    }
    /**
    * @Description: 修改用户权限
    * @Param: [user, roles]
    * @return: com.example.utils.AjaxResult
    * @Author: tiankaiqiang
    * @Date: 2020/12/22
    */
    @PutMapping("/api/user")
    public AjaxResult updateRole(@RequestBody   User user){
        userServiceImpl.saveUser(user);
        return new AjaxResult(200,user,"角色列表查询成功");
    }
    @GetMapping("/api/persission/{id}")
    public AjaxResult getUserPersisson(@PathVariable("id") String id){

        return new AjaxResult(200,userServiceImpl.getUserPermission(id),"角色列表查询成功");
    }
}
