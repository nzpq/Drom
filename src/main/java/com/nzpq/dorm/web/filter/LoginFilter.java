package com.nzpq.dorm.web.filter;
/**
 * 判断用户是否登陆的Filter
 */

import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.UserService;
import com.nzpq.dorm.service.impl.UserServiceImpl;
import com.nzpq.dorm.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/dormBuild/*","/dormManager/*","/student/*","/record/*"})
//@WebFilter("/*")
public class LoginFilter implements Filter {

    private UserService userService = new UserServiceImpl();
    private User u = new User();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0、强转
        HttpServletRequest request = (HttpServletRequest) req;
        //1、获取资源请求路径
//        String uri = request.getRequestURI();
//        System.out.println(uri);
        //3、从session中获取user，判断是否登录成功
        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user);
        //判断user是否为空
        if(user != null) {
            //放行
            chain.doFilter(req, resp);
        }else{
            //自动登录功能的实现
            Cookie cookie = CookieUtils.getCookieByName("cookie_name_pass", request);
            if(cookie != null){
                String stuCodePass = cookie.getValue();
                String[] code_pass = stuCodePass.split("_");
                u.setStuCode(code_pass[0]);
                u.setPassword(code_pass[1]);
                User login_user = userService.login(u);
                if(login_user != null){
                    request.getSession().setAttribute("user",login_user);
                    //放行
                    chain.doFilter(req, resp);
                }else{
                    //没有登录。给出提示信息，跳转到登陆页面
                    request.setAttribute("error","请登录！");
                    request.getRequestDispatcher("/index.jsp").forward(request,resp);
                }
            }else{
                //没有登录。给出提示信息，跳转到登陆页面
                request.setAttribute("error","请登录！");
                request.getRequestDispatcher("/index.jsp").forward(request,resp);
            }

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }
}
