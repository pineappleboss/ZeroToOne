package com.example.shiro;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.model.User;
import com.example.service.UserService;
import com.example.utils.JWTToken;
import com.example.utils.JWTUtil;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author tkq
 *
 */
@Component
public class WJRealm extends AuthorizingRealm {
    @Resource
    UserService userServiceImpl;
    /**
     * 判断token是否事我们的这个jwttoekn
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    /**
    * @Description: 给当前用户授予角色和权限
    * @Param: [principals]
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: tiankaiqiang
    * @Date: 2020/12/17
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.getPrimaryPrincipal().toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据登录名获取登用户信息
        if (!StringUtils.isEmpty(username)) {
            //设置用户角色
            authorizationInfo.addRole("role.getName()");
            //设置权限
            if (!StringUtils.isEmpty(username)) {
                //权限操作代码
                authorizationInfo.addStringPermission("privilege.getPrivilegecode()");
            }

            return authorizationInfo;
        }
        return null;
    }
    /**
    * @Description: 验证当前用户
    * @Param: [token]
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: tiankaiqiang
    * @Date: 2020/12/17
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (StringUtils.isEmpty(username)) {
            throw new AuthenticationException("token错误!");
        }
        User user=userServiceImpl.getByName(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
        try {
            if (JWTUtil.verify(token, username)) {

                return new SimpleAuthenticationInfo(token, token, getName());
            } else {
                throw new AuthenticationException("token认证失败!");
            }
        } catch (TokenExpiredException e) {
            throw new AuthenticationException("token已过期!");
        } catch (SignatureVerificationException e) {
            throw new AuthenticationException("密码不正确!");
        }
    }
}
