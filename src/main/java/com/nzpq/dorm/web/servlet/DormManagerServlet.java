package com.nzpq.dorm.web.servlet;

import com.nzpq.dorm.domain.DormBuild;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.DormManagerService;
import com.nzpq.dorm.service.impl.DormManagerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/dormManager/*")
public class DormManagerServlet extends BaseServlet {

    private DormManagerService dormManagerService = new DormManagerServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = dormManagerService.findAll();
//        System.out.println(users);
        request.setAttribute("users",users);
        request.setAttribute("mainRight","/WEB-INF/jsp/dormManagerList.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }

    public void addManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String para = request.getParameter("para");
        if(para != null && "add".equals(para)){
            request.setAttribute( "mainRight","/WEB-INF/jsp/addDormManager.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        //宿管管理的宿舍楼号
        String[] dormBuildIds = request.getParameterValues("dormBuildId");
        User user = new User();
        BeanUtils.populate(user,map);
        //封装user
        user.setRoleId(1);
        user.setDisabled(0);
        User session_user = (User) request.getSession().getAttribute("user");
        user.setCreateUserId(session_user.getId());

        //完成保存宿管的操作并返回改宿管的id
        Integer insertUser_id = dormManagerService.addManager(user);
        //调用service保存中间表信息 tb_manage_dormBuildId
        dormManagerService.addManagerToBuild(insertUser_id,dormBuildIds);
        user.setId(insertUser_id);
//        System.out.println(user);
        response.sendRedirect(request.getContextPath()+"/dormManager/findAll");
    }

    public void findCode (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stuCode = request.getParameter("stuCode");
        User user = dormManagerService.findManagerByCode(stuCode);
//        System.out.println(user+stuCode);
        writeValue(user,response);
    }
    public void findOneManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        User user_manage = dormManagerService.findOne(Integer.valueOf(uid));
        //获取改宿管管理的宿舍楼
        List<DormBuild> builds = dormManagerService.findBuildByUid(Integer.valueOf(uid));
        user_manage.setBuilds(builds);
//        System.out.println(user_manage);
        request.setAttribute("user_manage",user_manage);
        request.setAttribute( "mainRight","/WEB-INF/jsp/updateDormManager.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }

    public void updateManager (HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        String[] dormBuildIds = request.getParameterValues("dormBuildId");
        User user = new User();
        BeanUtils.populate(user,map);
//        System.out.println(user+"***"+ Arrays.toString(dormBuildIds));
        //调用service完成更新
        dormManagerService.updateManager(user,dormBuildIds);
        response.sendRedirect(request.getContextPath()+"/dormManager/findAll");
    }
    public void deleteManager (HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        String uid = request.getParameter("id");
        String disabled = request.getParameter("disabled");
        //调用service完成删除或者激活
        dormManagerService.activeOrDelete(Integer.valueOf(uid),Integer.valueOf(disabled));
        response.sendRedirect(request.getContextPath()+"/dormManager/findAll");
    }
}
