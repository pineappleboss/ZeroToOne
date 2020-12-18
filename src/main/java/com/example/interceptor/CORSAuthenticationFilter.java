//package com.example.interceptor;
//
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import com.alibaba.fastjson.JSONObject;
///**
// * @author tiankaiqiang
// * @version 1.0
// * @date 2020/12/17 9:23
// * @describe
// */
//public class CORSAuthenticationFilter extends FormAuthenticationFilter {
//
//    /**
//     * 直接过滤可以访问的请求类型
//     */
//    private static final String REQUET_TYPE = "OPTIONS";
//
//
//    public CORSAuthenticationFilter() {
//        super();
//    }
//
//
//    @Override
//    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        if (((HttpServletRequest) request).getMethod().toUpperCase().equals(REQUET_TYPE)) {
//            return true;
//        }
//        return super.isAccessAllowed(request, response, mappedValue);
//    }
//
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse res = (HttpServletResponse)response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setStatus(HttpServletResponse.SC_OK);
//        res.setCharacterEncoding("UTF-8");
//        PrintWriter writer = res.getWriter();
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("msg","请先登录");
//        writer.write(String.valueOf(jsonObject));
//        writer.close();
//        return false;
//    }
//}
