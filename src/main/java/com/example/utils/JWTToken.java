package com.example.utils;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author tiankaiqiang
 * @version 1.0
 * @date 2020/12/16 18:23
 * @describe
 */
public class JWTToken implements AuthenticationToken {

    /**
     * 凭证
     */
    private  String accessToken;

    public JWTToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Object getPrincipal() {
        return accessToken;
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }
}

