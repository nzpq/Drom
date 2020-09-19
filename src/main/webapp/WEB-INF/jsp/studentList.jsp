<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/12
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    function studentDelete(studentId,disabled) {
        if(confirm("您确定要删除这个学生吗？")) {
            location.href="${pageContext.request.contextPath}/student/updateDisabled?uid="+studentId+"&disabled="+disabled;
        }
    }

    $(document).ready(function(){
        $("#student").addClass("active");
    });
</script>
<style type="text/css">
    .span6 {
        width:0px;
        height: 0px;
        padding-top: 0px;
        padding-bottom: 0px;
        margin-top: 0px;
        margin-bottom: 0px;
    }

</style>
<div class="data_list">
    <div class="data_list_title">
        学生管理
    </div>
    <form name="myForm" class="form-search" method="post" action="student.action?action=list" style="padding-bottom: 0px">
        <button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="location.href='${pageContext.request.contextPath}/student/addStudent?para=add'">添加</button>
        <span class="data_search">
<%--						<select id="dormBuildId" name="dormBuildId" style="width: 110px;">--%>
<%--							<option value="">全部宿舍楼</option>--%>
<%--							<option value="1">1号楼</option>--%>
<%--						</select>--%>
<%--                    &nbsp;<input id="keyword" name="keyword" value="" type="text"  style="width:120px;height: 30px;" class="input-medium search-query">--%>
<%--					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>--%>
				</span>
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>宿舍楼</th>
                <th>寝室编号</th>
                <th>电话</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.stuCode}</td>
                    <td>${student.name}</td>
                    <td>${student.sex}</td>
                    <td>${student.dormBuildId}号楼</td>
                    <td>${student.dormCode}</td>
                    <td>${student.tel}</td>
                    <td>
                        <button class="btn btn-mini btn-info" type="button" onclick="location.href='${pageContext.request.contextPath}/student/findOneStudent?id=${student.id}'">修改</button>&nbsp;

                        <c:if test="${student.disabled == 0}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="studentDelete(${student.id},${student.disabled})">删除</button>
                        </c:if>
                        <c:if test="${student.disabled == 1}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="location.href='${pageContext.request.contextPath}/student/updateDisabled?uid=${student.id}&disabled=${student.disabled}'">激活</button>
                        </c:if>



                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div align="center"><font color="red"></font></div>
</div>
