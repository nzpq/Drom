<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/12
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            /* format: "yyyy-mm-dd hh:ii:ss", //时间格式  yyyy-mm-dd hh:ii:ss */
        });

    });



    function deleteOrActive(recordId,disabled) {
        if(confirm("您确定要删除或激活这条记录吗？")) {
            location.href="${pageContext.request.contextPath}/record/updateDisabled?rid="+recordId+"&disabled="+disabled;
        }
    }



    $(document).ready(function(){
        $("#record").addClass("active");
    });
</script>

<div class="data_list">
    <div class="data_list_title">
        缺勤记录
    </div>
            <button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="location.href='${pageContext.request.contextPath}/record/addRecord?para=add'">添加</button>
                <span class="data_search">

				</span>
      <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th>日期</th>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>宿舍楼</th>
                <th>寝室</th>
                <th>备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${records}" var="record">
                <tr>
                    <td><fmt:formatDate value="${record.date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td>${record.user.stuCode}</td>
                    <td>${record.user.name}</td>
                    <td>${record.user.sex}</td>
                    <td>${record.user.dormBuildId}号楼</td>
                    <td>${record.user.dormCode}</td>
                    <td>${record.remark}</td>

                    <td>


                        <c:if test="${user.roleId != 2}">

<%--                            <button class="btn btn-mini btn-success" type="button" onclick="location.href='${pageContext.request.contextPath}/record/findRecord?id=${record.id}'">修改</button>--%>

                            <c:if test="${record.disabled == 0}">
                                <button class="btn btn-mini btn-danger" type="button" onclick="deleteOrActive(${record.id},${record.disabled})">删除</button>
                            </c:if>
                            <c:if test="${record.disabled == 1}">
                                <button class="btn btn-mini btn-danger" type="button" onclick="location.href='${pageContext.request.contextPath}/record/updateDisabled?rid=${record.id}&disabled=${record.disabled}'">激活</button>
                            </c:if>


                        </c:if>
                        <c:if test="${user.roleId == 2}">
                            <button class="btn btn-mini btn-success" type="button">你没有操作权限！</button>
                        </c:if>

                    </td>
                </tr>


            </c:forEach>

            </tbody>
        </table>
    </div>
    <div align="center"><font color="red"></font></div>
</div>
