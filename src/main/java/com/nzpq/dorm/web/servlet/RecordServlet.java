package com.nzpq.dorm.web.servlet;

import com.nzpq.dorm.domain.Record;
import com.nzpq.dorm.domain.User;
import com.nzpq.dorm.service.RecordService;
import com.nzpq.dorm.service.impl.RecordServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PseudoColumnUsage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/record/*")
public class RecordServlet extends BaseServlet {

    private RecordService recordService = new RecordServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Record> records = recordService.findAll();
        request.setAttribute("records",records);
        request.setAttribute("mainRight","/WEB-INF/jsp/recordList.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }

    public void updateDisabled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        String disabled = request.getParameter("disabled");
        recordService.updateDisabled(Integer.valueOf(rid),Integer.valueOf(disabled));
        response.sendRedirect(request.getContextPath()+"/record/findAll");
    }
    public void addRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException, ParseException {
        String para = request.getParameter("para");
        if(para != null && "add".equals(para)){
            request.setAttribute("mainRight","/WEB-INF/jsp/addRecord.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
            return;
        }
        String stuCode = request.getParameter("stuCode");
        String dateStr = request.getParameter("date");
        String remark = request.getParameter("remark");
        Record record = new Record();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        User user = recordService.findOne(Integer.valueOf(stuCode));
        record.setStudentId(Integer.valueOf(user.getId()));
        record.setDate(date);
        record.setRemark(remark);

//        System.out.println(record);
        recordService.addRecord(record);
        response.sendRedirect(request.getContextPath()+"/record/findAll");
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stuCode = request.getParameter("stuCode");
        if(stuCode != null && !"".equals(stuCode)){
            User user = recordService.findOne(Integer.valueOf(stuCode));
//        System.out.println(record);
            writeValue(user,response);
        }

    }
    public void findRecord(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String rid = request.getParameter("id");
        Record record = recordService.findRecord(Integer.valueOf(rid));
        request.setAttribute("record",record);
        request.setAttribute("mainRight","/WEB-INF/jsp/updateRecord.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request,response);
    }

    public void updateRecord(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String stuCode = request.getParameter("stuCode");
        String dateStr = request.getParameter("date");
        String remark = request.getParameter("remark");
        Record record = new Record();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        User user = recordService.findOne(Integer.valueOf(stuCode));
        record.setStudentId(user.getId());
        record.setDate(date);
        record.setRemark(remark);

        System.out.println(user);
        System.out.println(record);
        recordService.updateRecord(record);
        response.sendRedirect(request.getContextPath()+"/record/findAll");
    }
}
