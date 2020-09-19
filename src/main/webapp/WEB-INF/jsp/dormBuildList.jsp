<%--
  Created by IntelliJ IDEA.
  User: nzp
  Date: 2020/6/5
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function dormBuildDeleteOrActive(dormBuildId,disabled) {
        if(confirm("您确定要删除这个宿舍楼吗？")) {
            location.href="${pageContext.request.contextPath}/dormBuild/deleteBuild?bid="+dormBuildId+"&disabled="+disabled;
        }
    }

    //文档加载完后
    window.onload=function(){
        //获取后台保存的当前要修改的foodTypeId值
        var id = "${id}";
        //获取菜系select标签
        var idSelect = document.getElementById("id");
        //获取下拉框中所有的option
        var  options = idSelect.options;

        //遍历菜系select标签中所有的option标签
        $.each( options, function(i, option){
            $(option).attr("selected",option.value == id);
        });
    }

    $(document).ready(function(){
        $("#dormBuild").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title">
        宿舍楼管理
    </div>
    <form name="myForm" class="form-search" method="post" action="dormBuild.action?action=list">
        <button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="location.href='${pageContext.request.contextPath}/dormBuild/addBuild?para=add'">添加</button>
        <span class="data_search">
<%--					<select id="id" name="id" style="width: 120px;">--%>
<%--						<option value="">全部宿舍楼</option>--%>
<%--							<option value=""></option>--%>
<%--					</select>--%>
<%--					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>--%>
				</span>
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th width="15%">序号</th>
                <th>名称</th>
                <th>简介</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach  items="${builds}" var="build">
                <tr>
                    <td>${build.id}</td>
                    <td>${build.name}</td>
                    <td>${build.remark}</td>
                    <td>
                        <button class="btn btn-mini btn-info" type="button" onclick="location.href='${pageContext.request.contextPath}/dormBuild/findOneBuild?buildId=${build.id}'">修改</button>&nbsp;

                        <c:if test="${build.disabled == 1}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="location.href='${pageContext.request.contextPath}/dormBuild/activeBuild?bid=${build.id}&disabled=${build.disabled}'">激活</button>
                        </c:if>

                        <c:if test="${build.disabled == 0}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(${build.id},${build.disabled})">禁用</button>
                        </c:if>

                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
    <div align="center"><font color="red"></font></div>
</div>
