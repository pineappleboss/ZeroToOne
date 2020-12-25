//package com.example.exception;
//
//import com.example.utils.AjaxResult;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author tiankaiqiang
// * @version 1.0
// * @date 2020/12/23 10:21
// * @describe 处理异常，为了简便将所有异常都处理为返回500.
// * 当项目出现问题想要看日志，将此页所有代码都注释掉。
// *  @ControllerAdvice 是spring新增的注解，用来解析异常,当访问过程中抛出异常，都会在这个类中进行处理、
// * 可以将异常堆栈信息返回，方便调试。
// */
//
//@ControllerAdvice
//public class WebExceptionHandler {
//
//
//    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseBody
//    public AjaxResult handleUnauthorizedException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
//
//        return new AjaxResult(500,null,ex.getMessage());
//    }
//
//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseBody
//    public AjaxResult handleUnauthenticatedException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
//        return  new AjaxResult(500,null,ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public AjaxResult handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
//        return  new AjaxResult(500,null,ex.getMessage());
//    }
//
//}
