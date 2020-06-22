<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<base
        href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
<%@include file="/WEB-INF/include-header.jsp" %>


<script type="text/javascript">

    $(function () {


        $("#toRightBtn").click(function () {
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        });

        $("#toLeftBtn").click(function () {
            $("select:eq(1)>option:selected").prependTo("select:eq(0)");
        });

        $("#submitBtn").click(function () {
            $("select:eq(1)>option").prop("selected","selected")
        });


    });

</script>


<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>


<div class="container-fluid">

    <%@ include file="/WEB-INF/include-sidebar.jsp" %>
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/assign.html" role="form" method="post" class="form-inline">

                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRole}" var="role">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>


                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="" size="10"
                                    style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRole}" var="role">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br>

                        <button id ="submitBtn" type="submit" class="btn btn-success"> 确认</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
