package com.nzpq.dorm.web.servlet;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.StudentService;
import com.nzpq.dorm.service.impl.StudentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/student/*")
public class StudentServlet extends BaseServlet {

    private StudentService studentService = new StudentServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<User> students = studentService.findAll(user.getId(),user.getRoleId());
        request.setAttribute("students",students);
        request.setAttribute("mainRight","/WEB-INF/jsp/studentList.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);

    }

    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String para = request.getParameter("para");
        if(para != null && para.equals("add")){
            request.setAttribute("mainRight","/WEB-INF/jsp/addStudent.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);
//        System.out.println(user);
        //调用service完成学生信息保存
        User session_user = (User) request.getSession().getAttribute("user");
        user.setCreateUserId(session_user.getId());
        studentService.addStudent(user);
        response.sendRedirect(request.getContextPath()+"/student/findAll");
    }

    public void updateDisabled(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        String disabled = request.getParameter("disabled");
        studentService.updateDisabled(Integer.valueOf(uid),Integer.valueOf(disabled));
        response.sendRedirect(request.getContextPath()+"/student/findAll");
    }
    public void findOneStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uid = request.getParameter("id");
        User user = studentService.findOne(uid);
        request.setAttribute("student",user);
        request.setAttribute("mainRight","/WEB-INF/jsp/updateStudent.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }
    public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);
        System.out.println(user);
        studentService.updateStudent(user);
        response.sendRedirect(request.getContextPath()+"/student/findAll");
    }
}
