<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/12
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    function checkForm(){
        var stuCode=document.getElementById("stuCode").value;
        var name=document.getElementById("name").value;
        var sex=document.getElementById("sex").value;
        var tel=document.getElementById("tel").value;
        var password=document.getElementById("passWord").value;
        var rPassword=document.getElementById("rPassword").value;
        var dormBuildId=document.getElementById("dormBuildId").value;
        var dormCode=document.getElementById("dormCode").value;

        if(stuCode=="" ||name==""||tel=="" ||password==""||rPassword==""||dormBuildId==""||dormCode==""){
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
        $("#student").addClass("active");
    });
</script>
<script>
    $(function () {
        $.post("/dorm/dormBuild/findBuildName",{},function (data) {
            var lis = "";
            for(var i=0;i<data.length;i++){
                lis += ' <option value="'+data[i].id+'">'+data[i].name+'</option>';
            }
            $("#dormBuildId").html(lis);
        })

    });
</script>
<div class="data_list">
    <div class="data_list_title">
        修改学生信息
    </div>
    <form action="${pageContext.request.contextPath}/student/updateStudent" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <div align="center">
                <font id="error" color="red"></font>
                <input type="hidden" id="id"  name="id" value="${student.id}" />
            </div>
            <table align="center">
                <tr>
                    <td><font color="red">*</font>姓名：</td>
                    <td><input type="text" id="name"  name="name" value="${student.name}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>性别：</td>
                    <td>
                        <select id="sex" name="sex" style="width: 90px;">
                            <option value="男" ${student.sex == "男" ? "selected" : ""}>男</option>
                            <option value="女" ${student.sex == "女" ? "selected" : ""}>女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font>联系电话：</td>
                    <td><input type="text" id="tel"  name="tel" value="${student.tel}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>密码：</td>
                    <td><input type="password" id="passWord"  name="password" value="${student.password}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>重复密码：</td>
                    <td><input type="password" id="rPassword"  name="rPassword" value="${student.password}"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>原来的宿舍楼及寝室编号：</td>
                    <td>
                        <span>${student.dormBuildId}号楼&nbsp;${student.dormCode}</span>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font>新宿舍楼：</td>
                    <td>
                        <select id="dormBuildId" name="dormBuildId" style="width: 90px;">

                        </select>
                    </td>
                </tr>
                <tr>
                    <td><font color="red">*</font>新寝室编号：</td>
                    <td><input type="text" id="dormCode"  name="dormCode" placeholder="格式：x-xxx" value=""  style="margin-top:5px;height:30px;" /></td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="保存"/>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
            </div>
        </div>
    </form>
</div>
