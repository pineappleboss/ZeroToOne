package com.example.shiro;

import com.example.model.User;
import com.example.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tkq
 *
 */
@Component
public class WJRealm extends AuthorizingRealm {
    @Resource
    UserService userServiceImpl;
    //简单重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }
    //获取token中的用户名盐等信息并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username =token.getPrincipal().toString();
        User user=userServiceImpl.getByName(username);
        String salt=user.getSalt();
        String pwd=user.getPassword();
        //将取出的信息 放入 authenticationInfo 返回
        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,pwd, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
