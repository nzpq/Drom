<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/5
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宿舍管理系统</title>
    <link href="${pageContext.request.contextPath}/css/dorm.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet"><!--（含有bootstrap 所有css）  -->
    <!-- 日期控件所需的样式表 -->
    <link href="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <!-- 日期控件所需的js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <!-- 如需支持简体中文的显示，就需要加载中文的资源文件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <style type="text/css">
        .bs-docs-sidenav {
            background-color: #fff;
            border-radius: 6px;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
            padding: 0;
            width: 228px;
        }

        .bs-docs-sidenav > li > a {
            border: 1px solid #e5e5e5;
            display: block;
            padding: 8px 14px;
            margin: 0 0 -1px;
        }
        .bs-docs-sidenav > li:first-child > a {
            border-radius: 6px 6px 0 0;
        }
        .bs-docs-sidenav > li:last-child > a {
            border-radius: 0 0 6px 6px;
        }
        .bs-docs-sidenav > .active > a {
            border: 0 none;
            box-shadow: 1px 0 0 rgba(0, 0, 0, 0.1) inset, -1px 0 0 rgba(0, 0, 0, 0.1) inset;
            padding: 9px 15px;
            position: relative;
            text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
            z-index: 2;
        }
        .bs-docs-sidenav .icon-chevron-right {
            float: right;
            margin-right: -6px;
            margin-top: 2px;
            opacity: 0.25;
        }
        .bs-docs-sidenav > li > a:hover {
            background-color: #f5f5f5;
        }
        .bs-docs-sidenav a:hover .icon-chevron-right {
            opacity: 0.5;
        }
        .bs-docs-sidenav .active .icon-chevron-right, .bs-docs-sidenav .active a:hover .icon-chevron-right {
            background-image: url("${pageContext.request.contextPath}/bootstrap/img/glyphicons-halflings-white.png");
            opacity: 1;
        }
    </style>

</head>
<body>
<div class="container-fluid" style="padding-right: 0px;padding-left: 0px;">
    <div region="north" style="height: 100px;background-image: url('${pageContext.request.contextPath}/images/bg.jpg')">
        <div align="left" style="width: 80%;height:100px ;float: left;padding-top: 40px;padding-left: 30px;" ><font color="white" size="6" >宿舍管理系统</font></div>
        <div style="padding-top: 70px;padding-right: 20px;font-size:16px">
            当前用户：<font color="red" >${user.name}</font>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2 bs-docs-sidebar" >
            <ul class="nav nav-list bs-docs-sidenav">
                <%--超级管理员--%>
                <c:if test="${user.roleId == 0}">
                    <li><a href="${pageContext.request.contextPath}"><i class="icon-chevron-right"></i>首页</a></li>
                    <li id="dormManager"><a href="${pageContext.request.contextPath}/dormManager/findAll"><i class="icon-chevron-right"></i>宿舍管理员管理</a></li>
                    <li id="student"><a href="${pageContext.request.contextPath}/student/findAll"><i class="icon-chevron-right"></i>学生管理</a></li>
                    <li id="dormBuild"><a href="${pageContext.request.contextPath}/dormBuild/findDormBuildList"><i class="icon-chevron-right"></i>宿舍楼管理</a></li>
                    <li id="record"><a href="${pageContext.request.contextPath}/record/findAll"><i class="icon-chevron-right"></i>缺勤记录</a></li>
                    <li id="password"><a href="${pageContext.request.contextPath}/user/changePassword?para=ch"><i class="icon-chevron-right"></i>修改密码</a></li>
                    <li id="loginOut"><a href="${pageContext.request.contextPath}/user/exit"><i class="icon-chevron-right"></i>退出系统</a></li>
                </c:if>

                <c:if test="${user.roleId == 1}">
                    <li><a href="blank"><i class="icon-chevron-right"></i>首页</a></li>
                    <li id="student"><a href="${pageContext.request.contextPath}/student/findAll"><i class="icon-chevron-right"></i>学生管理</a></li>
                    <li id="record"><a href="${pageContext.request.contextPath}/record/findAll"><i class="icon-chevron-right"></i>缺勤记录</a></li>
                    <li id="password"><a href="${pageContext.request.contextPath}/user/changePassword?para=ch"><i class="icon-chevron-right"></i>修改密码</a></li>
                    <li id="loginOut"><a href="${pageContext.request.contextPath}/user/exit"><i class="icon-chevron-right"></i>退出系统</a></li>
                </c:if>

                <%--普通学生--%>
                <c:if test="${user.roleId == 2}">
                    <li><a href="blank"><i class="icon-chevron-right"></i>首页</a></li>
                    <li id="record"><a href="${pageContext.request.contextPath}/record/findAll"><i class="icon-chevron-right"></i>缺勤记录</a></li>
                    <li id="password"><a href="${pageContext.request.contextPath}/user/changePassword?para=ch"><i class="icon-chevron-right"></i>修改密码</a></li>
                    <li id="loginOut"><a href="${pageContext.request.contextPath}/user/exit"><i class="icon-chevron-right"></i>退出系统</a></li>
                </c:if>


            </ul>
        </div>
        <div class="span10">
            <!--右侧内容-->
            <jsp:include page="${mainRight==null? 'blank.jsp':mainRight}"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>
