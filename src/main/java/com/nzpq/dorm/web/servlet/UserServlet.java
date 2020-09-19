package com.nzpq.dorm.web.servlet;

import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.UserService;
import com.nzpq.dorm.service.impl.UserServiceImpl;
import com.nzpq.dorm.utils.CookieUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        String remember = request.getParameter("remember");//获取‘记住我’勾选框
        User user = new User();
        BeanUtils.populate(user,map);
        User u = userService.login(user);
        if(u != null){
            //登陆成功
            if(remember != null && remember.equals("remember-me")){
                CookieUtils.addCookie("cookie_name_pass",7*24*60*60,u.getStuCode(),u.getPassword(),request,response);
            }
            request.getSession().setAttribute("user",u);
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
        }else{
            //登陆失败
            request.setAttribute("error","登陆失败！学号或者密码错误！");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().removeAttribute("user");
        //清除cookie
        Cookie cookie = CookieUtils.getCookieByName("cookie_name_pass", request);
        if(cookie != null){
            cookie.setMaxAge(0);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String para = request.getParameter("para");
        if(para != null && "ch".equals(para)){
            request.setAttribute("mainRight","/WEB-INF/jsp/passwordChange.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        String password = request.getParameter("password");
        userService.updatePassword(user.getId(),password);
        //登陆失败
        request.setAttribute("error","请重新登录！");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    public void findOldPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String oldPassword = request.getParameter("oldPassword");
        User u = userService.findUser(user.getId(),oldPassword);
//        System.out.println(user+"=="+oldPassword);
//        System.out.println(u);
        writeValue(u,response);
    }

}
