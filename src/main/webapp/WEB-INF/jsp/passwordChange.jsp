<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script>
    function checkPassword() {
        var oldPassword =  $("#oldPassword").val();
        var flag = false;
        $.ajaxSettings.async = false;
        $.post("/dorm/user/findOldPassword",{oldPassword:oldPassword},function (data) {
            if(data == null ){
                document.getElementById("error").innerHTML="原密码不正确！";
                flag =  true;
            }else{
                document.getElementById("error").innerHTML="";
                flag = false;
            }
        });
        $.ajaxSettings.async = true;
        return flag;
    }
    $(function () {
        $("#oldPassword").blur(checkPassword);
    });
</script>
<script type="text/javascript">
    function checkForm(){
        var oldPassword=document.getElementById("oldPassword").value;
        var newPassword=document.getElementById("newPassword").value;
        var rPassword=document.getElementById("rPassword").value;

        $("#error").html("");
        if(oldPassword==""||newPassword==""||rPassword==""){
            document.getElementById("error").innerHTML="信息填写不完整！";
            return false;
        }
        if(newPassword == oldPassword){
            document.getElementById("error").innerHTML="新密码修改前后一致！";
            return false;
        }
        if(newPassword!=rPassword){
            document.getElementById("error").innerHTML="新密码两次填写不一致！";
            return false;
        }
        if(checkPassword()){
            document.getElementById("error").innerHTML="请填写正确的原始密码！";
            return false;
        }

        return true;
    }

    $(document).ready(function(){
        $("ul li:eq(5)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title">
        修改密码
    </div>
    <form action="${pageContext.request.contextPath}/user/changePassword" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <table align="center">
                <tr>
                    <td><font color="red">*</font>原密码：</td>
                    <td><input type="password"  id="oldPassword"  name="oldPassword" value=""  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>新密码：</td>
                    <td><input type="password" id="newPassword"  name="password" value="" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>重复密码：</td>
                    <td><input type="password" id="rPassword"  name="rPassword" value="" style="margin-top:5px;height:30px;" /></td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="提交"/>
            </div>
            <div align="center">
                <font id="error" color="red"></font>
            </div>
        </div>
    </form>
</div>
