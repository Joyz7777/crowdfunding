<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-header.jsp" %>
<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page.html">首页</a></li>
                <li><a href="admin/get/user.html">用户维护</a></li>
                <li class="active">编辑</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">编辑Admin用户
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <p>${requestScope.exception.message}</p>
                    <form role="form" method="post" action="admin/do/update.html">
                        <input type="hidden" name="loginAcct" value="${requestScope.admin.loginAcct}" />
                        <input type="hidden" name="pageNum" value="${param.pageNum}" />
                        <input type="hidden" name="keyword" value="${param.keyword}" />

                        <div class="form-group">
                            <label for="exampleInputPassword1">登陆账号</label>
                            <input  name="loginAcct" value="${requestScope.admin.loginAcct}" disabled="disabled" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户名称</label>
                            <input name="userName" value="${requestScope.admin.userName}" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input name="email" value="${requestScope.admin.email}"   type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 更新
                        </button>
                        <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
