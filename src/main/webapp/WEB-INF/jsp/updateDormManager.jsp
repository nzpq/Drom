<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/7
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">


    function checkForm(){
        var name=document.getElementById("name").value;
        var password=document.getElementById("passWord").value;
        var rPassword=document.getElementById("rPassword").value;
        var stuCode=document.getElementById("stuCode").value;
        var sex=document.getElementById("sex").value;
        var tel=document.getElementById("tel").value;
        var buildIds = document.getElementsByName("dormBuildId");


        var checks = new Array();
        for(var i = 0; i<buildIds.length;i++){
            if(buildIds[i].checked){
                checks.push(buildIds[i].value);
            }
        }

        if(name==""||password==""||rPassword==""||sex==""||tel=="" ||  checks.length < 1 ){
            document.getElementById("error").innerHTML="信息填写不完整！";
            return false;
        } else if(password!=rPassword){
            document.getElementById("error").innerHTML="密码填写不一致！";
            return false;
        }else if(!/^1[34578]\d{9}$/.test(tel)){
            document.getElementById("error").innerHTML="手机号码格式错误！";
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $("#dormManager").addClass("active");
    });
</script>
<script>
    $(function () {
        $.post("/dorm/dormBuild/findBuildName",{},function (data) {
            var lis = "";
            for(var i=0;i<data.length;i++){
                lis += '<input name="dormBuildId" value="'+data[i].id+'"  style="heigth:14px;vertical-align:top"  type="checkbox" >'+data[i].name+'  &nbsp;';
            }
            $("#build").html(lis);
        })
        
    });
    

</script>
<div class="data_list">
    <div class="data_list_title">
        修改宿管信息
    </div>
    <form action="${pageContext.request.contextPath}/dormManager/updateManager" method="post" onsubmit="return checkForm()">
        <input name="id" type="hidden" value="${user_manage.id}">
        <div class="data_form" >
            <div align="center">
                <font id="error" color="red"></font>
            </div>
            <table align="center">
                <tr>
                    <td><font color="red">*</font>姓名：</td>
                    <td><input type="text" id="name"  name="name" value="${user_manage.name}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>密码：</td>
                    <td><input type="password" id="passWord"  name="password" value="${user_manage.password}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>确认密码：</td>
                    <td><input type="password" id="rPassword"  name="rPassword" value="${user_manage.password}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>性别：</td>
                    <td>
                        <select id="sex" name="sex" style="width: 90px;">
                            <option value="男" ${user_manage.sex == "男" ? "selected" : ""}>男</option>
                            <option value="女" ${user_manage.sex == "女" ? "selected" : ""}>女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font>联系电话：</td>
                    <td><input type="text" id="tel"  name="tel" value="${user_manage.tel}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>原来管理楼栋：</td>
                    <td id="">
                        <c:forEach items="${user_manage.builds}" var="build">
                            <span>${build.name}</span>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font>修改管理楼栋：</td>
                    <td id="build">
<%--                        <input name="dormBuildId" value="1"  style="heigth:14px;vertical-align:top"  type="checkbox" >1号宿舍楼  &nbsp;--%>
<%--                        <input name="dormBuildId" value="2"  style="heigth:14px;vertical-align:top"  type="checkbox" >2号宿舍楼  &nbsp;--%>
<%--                        <input name="dormBuildId" value="3"  style="heigth:14px;vertical-align:top"  type="checkbox" >3号宿舍楼  &nbsp;--%>
                    </td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="保存"/>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
            </div>

        </div>
    </form>
</div>