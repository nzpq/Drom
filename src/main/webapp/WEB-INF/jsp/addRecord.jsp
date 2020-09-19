<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/13
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.form_date').datetimepicker({
            language:'zh-CN',/*语言  默认值：’en’ */
            weekStart: 1,/* 一周从哪一天开始。0（星期日）到6（星期六） */
            todayBtn:  1,/*当天日期将会被选中。  */
            autoclose: 1,//选择后自动关闭当前时间控件
            todayHighlight: 1,/*高亮当天日期。  */
            startView: 2,/* 从月视图开始，选中那一天  3为选月份*/
            minView: 2,/* 从月视图开始，选天   选完天后，不在出现下级时分秒时间选择 */
            forceParse: 0,/*就是你输入的可能不正规，但是它胡强制尽量解析成你规定的格式（format）  */
            format: "yyyy-mm-dd", //时间格式  yyyy-mm-dd hh:ii:ss */
        });

    });



    $(document).ready(function(){
        $("#record").addClass("active");
    });
</script>
<script>
    function checkStuCode() {
        var stuCode =  $("#stuCode").val();
        var flag = true;
        $.ajaxSettings.async = false;
        $.post("/dorm/record/findOne",{stuCode:stuCode},function (data) {
            if(data == null){
                document.getElementById("error").innerHTML="该学号不存在！请重新输入！";
                 flag =  true;
            }else{
                document.getElementById("error").innerHTML="";
                flag =  false;
            }
        });
        $.ajaxSettings.async = true;
        return flag;
    }

    $(function () {
        $("#stuCode").blur(checkStuCode);
    });

</script>
<script>
    function checkForm() {
        var stuCode = $("#stuCode").val();
        var date = $("#date").val();
        var remark = $("#remark").val();

        if(stuCode == null || stuCode == ""){
            $("#error").html("学号不能为空！");
            return false;
        }
        if(date == null || date == ""){
            $("#error").html("时间不能为空！");
            return false;
        }
        if(checkStuCode()){
            $("#error").html("请输入正确的学号！");
            return false;
        }
        if(remark == null || remark == ""){
            $("#error").html("备注不能为空！");
            return false;
        }
        return true;
    }
</script>
<div class="data_list">
    <div class="data_list_title">
        添加缺勤记录
    </div>
    <form action="${pageContext.request.contextPath}/record/addRecord" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <div align="center">
                <font id="error" color="red"></font>
            </div>
            <table align="center">
                <tr>
                    <td><font color="red">*</font>学号：</td>
                    <td><input type="text"  id="stuCode"  name="stuCode" value="" placeholder="请输入八位学号" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>日期：</td>
                    <td><input id="date" name="date" value="" style="margin-top:5px;height:30px;" placeholder="缺勤日期" type="date" class="controls input-append date form_date"  ></td>
                </tr>
                <tr>
                    <td>备注：</td>
                    <td><input type="text" id="remark"  name="remark" value=""  style="margin-top:5px;height:80px;" /></td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="保存"/>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
            </div>
        </div>
    </form>
</div>
