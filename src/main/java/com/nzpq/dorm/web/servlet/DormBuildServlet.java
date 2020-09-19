package com.nzpq.dorm.web.servlet;

import com.nzpq.dorm.domain.DormBuild;
import com.nzpq.dorm.service.DormBuildService;
import com.nzpq.dorm.service.impl.DormBuildServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/dormBuild/*")
public class DormBuildServlet extends BaseServlet {
    
    private DormBuildService dormBuildService = new DormBuildServiceImpl();

    public void findDormBuildList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DormBuild> builds = dormBuildService.findAll();
        request.setAttribute("builds",builds);
        request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildList.jsp");
//        System.out.println(builds);
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }

    public void addBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String para = request.getParameter("para");
        if(para != null && "add".equals(para)){
            request.setAttribute( "mainRight","/WEB-INF/jsp/addDormBuild.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        DormBuild build = new DormBuild();
        BeanUtils.populate(build,map);
        build.setDisabled(0);
        DormBuild b = dormBuildService.findOne(build.getName(),null);
        if(b != null){
            //该宿舍楼的名字已经存在
            request.setAttribute("error","当前宿舍楼名已经存在！");
            request.setAttribute( "mainRight","/WEB-INF/jsp/addDormBuild.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
        }else{
            //保存宿舍楼
            dormBuildService.addBuild(build);
            findDormBuildList(request,response);
        }

    }

    public void findOneBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buildId = request.getParameter("buildId");
        DormBuild build = dormBuildService.findOne(null,buildId);
//        System.out.println("findOneBuild:  "+build);
        request.setAttribute("ud_build",build);
        request.setAttribute( "mainRight","/WEB-INF/jsp/updateDormBuild.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }

    public void updateBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        DormBuild build = new DormBuild();
        BeanUtils.populate(build,map);
//        System.out.println(build);
        dormBuildService.updateBuild(build);
        findDormBuildList(request,response);
    }

    public void activeBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String bid = request.getParameter("bid");
        String disabled = request.getParameter("disabled");
        dormBuildService.activeBuild(Integer.valueOf(bid),Integer.valueOf(disabled));
        findDormBuildList(request,response);
    }

    public void deleteBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String bid = request.getParameter("bid");
        String disabled = request.getParameter("disabled");
        dormBuildService.deleteBuild(Integer.valueOf(bid),Integer.valueOf(disabled));
        findDormBuildList(request,response);
    }

    /**查找所有宿舍楼名，用于添加宿管时显示
     */
    public void findBuildName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DormBuild> builds = dormBuildService.findName();
        writeValue(builds,response);
    }

}
