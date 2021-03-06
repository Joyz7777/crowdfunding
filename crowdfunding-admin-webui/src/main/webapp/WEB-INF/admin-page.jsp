<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<link rel="stylesheet" href="css/pagination.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>

<script>
    $(function () { // 调用专门的函数初始化分页导航条
        initPagination();
    });

    function initPagination() {
        //获取总记录数
        var totalRecord = ${requestScope.pageInfo.total};
        var properties = {
            //边缘
            num_edge_entries: 3,
            //主体
            num_display_entries: 3,
            //回调函数
            callback: paginationCallBack,
            //当前页
            current_page: ${requestScope.pageInfo.pageNum},
            //每页显示多少项
            items_per_page: ${requestScope.pageInfo.pageSize},
            //上一页
            prev_text: "上一页",
            next_text: "下一页"
            //下一页

        };
        // 调用分页导航条对应的 jQuery 对象的 pagination()方法生成导航条
        $("#Pagination").pagination(totalRecord, properties)
    }

    function paginationCallBack(pageIndex, jQuery) {
        var pageNum = pageIndex + 1;

        if (${empty param.keyword}) {
            window.location.href = "admin/get/user.html?pageNum=" + pageNum;
        } else {
            window.location.href = "admin/get/user.html?pageNum=" + pageNum + "&keyword=${param.keyword}";
        }

        return false;


    }


</script>


<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left; " action="admin/get/user.html"
                          method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>

                    <button type="button" class="btn btn-primary"
                            style="float: right;" onclick="window.location.href='admin/to/add.html'">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>

                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list }">
                                <tr>
                                    <td colspan="6" align="center">抱歉！没有查询到您要的数据！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list }">
                                <c:forEach items="${requestScope.pageInfo.list }" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count }</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.loginAcct }</td>
                                        <td>${admin.userName }</td>
                                        <td>${admin.email }</td>
                                        <td>
                                            <a href
                                            ="assign/to/assignRole/page.html?adminId=${admin.id }&pageNum=${requestScope.pageInfo.pageNum }&keyword=${param.keyword }"
                                            class="btn btn-success btn-xs"><i
                                                class=" glyphicon glyphicon-check"></i></a>
                                            <a href="admin/to/edit/${admin.id}.html?pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}"
                                               class="btn btn-primary btn-xs">
                                                <i class=" glyphicon glyphicon-pencil"></i>
                                            </a>
                                            <a href="admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html"
                                               class="btn btn-danger btn-xs">
                                                <i class=" glyphicon glyphicon-remove"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
s
</body>
</html>
