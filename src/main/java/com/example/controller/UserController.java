package com.example.controller;

import com.example.model.AdminMenu;
import com.example.model.User;
import com.example.service.UserService;
import com.example.utils.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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
    @PostMapping("api/login")
    public AjaxResult login(@RequestBody User user){
        //获取subject
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //设置
        usernamePasswordToken.setRememberMe(true);
        //执行认证动作
        try {
            subject.login(usernamePasswordToken);
            return new AjaxResult(200,user,"登录成功");
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return new AjaxResult(500,user,"登录失败，用户名或密码有误");
        }


    }

    /**
     * 用户注册
     * 数据存储的MD5加密后的密码和slat在shiro验证中还要使用
     * @param user
     * @return
     */
    @PostMapping("api/register")
    public  AjaxResult register(@RequestBody User user){
        user.setUsername(HtmlUtils.htmlEscape(user.getUsername()));
        //获取盐
        String salt=new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        //加盐加密
        String pwd=new SimpleHash("md5",user.getPassword(),salt,2).toString();
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
    @GetMapping("/api/authentication")
    public  AjaxResult authentication(){
        return new AjaxResult(200,null,"验证成功");
    }

    /**
     * 查询菜单
     * @param user
     * @return
     */
    @PostMapping("/api/menu")
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
    @GetMapping("/api/user")
    public AjaxResult getAllUser(){

        return new AjaxResult(200,userServiceImpl.getAlluser(),"👳‍查询全部用户信息成功##");
    }
}
