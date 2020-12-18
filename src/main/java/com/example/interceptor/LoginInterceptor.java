//package com.example.interceptor;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    //对所有后端请求进行验证，如果没有登录（没有存储session）则无法通过拦截器
//    @Override
//    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        //放行options请求，因为前端请求由于跨域情况下前会发送一次options进行试探，如果不放行，由于这个请求不带cookie，会导致无法进行验证
//        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
//            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
//            return true;
//        }
//        //使用shiro进行验证
//        Subject subject=SecurityUtils.getSubject();
//        //将可以理解为进行过login 之后session进行验证
//        if(!subject.isAuthenticated()){
//
//
//            //验证不通过不放行
//            return  false;
//        }
//        return true;
//    }
//
//}
