<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/4
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宿舍管理系统登录</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
    <script type="text/javascript">
        function checkForm() {
            var stuCode = document.getElementById("stuCode").value;
            var password = document.getElementById("password").value;
            if (stuCode == null || stuCode == "") {
                document.getElementById("error").innerHTML = "学号不能为空";
                return false;
            }
            if (password == null || password == "") {
                document.getElementById("error").innerHTML = "密码不能为空";
                return false;
            }

            return true;
        }

        $(document).ready(function(){
            $("#login").addClass("active");
        });
    </script>

    <style type="text/css">
        body {
            padding-top: 200px;
            padding-bottom: 40px;
            background-image: url('${pageContext.request.contextPath}/images/bg.jpg');
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        .radio {
            padding-top: 10px;
            padding-bottom:10px;
        }

        .form-signin-heading{
            text-align: center;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 0px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style>

</head>
<body>
<div class="container">
    <form name="myForm" class="form-signin" action="${pageContext.request.contextPath}/user/login" method="post" onsubmit="return checkForm()">
        <h2 class="form-signin-heading"><font color="gray">宿舍管理系统</font></h2>
        <input type="hidden"  name="action"  value="submit">
        <!-- 如果想让控件像块级元素一样占满容器，就可以为它添加 .input-block-level 类。这样，控件不仅可以占满容器，还可以根据浏览器窗口自动调整尺寸 -->
        <input id="stuCode" name="stuCode" value="" type="text" class="input-block-level" placeholder="请输入八位学号或者工号">
        <input id="password" name="password" value="" type="password" class="input-block-level" placeholder="请输入密码" >
        <label class="checkbox">
            <input id="remember" name="remember" type="checkbox" value="remember-me" >记住我 &nbsp;&nbsp;&nbsp;&nbsp;
            <font id="error" color="red">${error}</font>
        </label>
        <div style="text-align: center;">
            <button class="btn btn-large btn-primary" type="submit">登录</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <p align="center" style="padding-top: 15px;">2020 </p>
    </form>
</div>
</body>
</html>
