<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/include-header.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js" charset="UTF-8"></script>
<script type="text/javascript" src="crowd/my-role.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(function () {

        // 1.为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        generatePage();

        $("#rolePageBody").on("click",".roleAuthBtn",function() {

           $("#roleAssignAuthModal").modal("show");
        });


        $("#rolePageBody").on("click", ".removeBtn", function () {

            var roleName = $(this).parent().prev().text();
            //单个role对象放入数组
            var roleArray = [{
                roleId: this.id,
                roleName: roleName
            }];

            showConfirmModal(roleArray);
        });


        // 10.给总的checkbox绑定单击响应函数
        $("#summaryBox").click(function() {

            // ①获取当前多选框自身的状态
            var currentStatus = this.checked;

            // ②用当前多选框的状态设置其他多选框
            $(".itemBox").prop("checked", currentStatus);
        });


            // 11.全选、全不选的反向操作
        $("#rolePageBody").on("click",".itemBox",function(){

            // 获取当前已经选中的.itemBox的数量
            var checkedBoxCount = $(".itemBox:checked").length;

            // 获取全部.itemBox的数量
            var totalBoxCount = $(".itemBox").length;

            // 使用二者的比较结果设置总的checkbox
            $("#summaryBox").prop("checked", checkedBoxCount == totalBoxCount);

        });



        $("#searchRoleBtn").click(function () {
            window.keyword = $("#keywordInput").val();
            generatePage();
        });



        $("#batchRemoveBtn").click(function () {

            var roleArray = [];

            // 遍历当前选中的多选框
            $(".itemBox:checked").each(function(){
                // 使用this引用当前遍历得到的多选框
                var roleId = this.id;
                // 通过DOM操作获取角色名称
                var roleName = $(this).parent().next().text();
                roleArray.push({
                    "roleId":roleId,
                    "roleName":roleName
                });
            });
            if(roleArray.length ==0){
                layer.msg("请至少选择一个角色!");
                return;
            }



            showConfirmModal(roleArray);
        });







    });


    function showAddModel() {
        $('#addModal').modal('show');

    }


    $(function () {


        //确认并发送ajax请求
        $("#removeRoleBtn").click(function () {
            var requestBody = JSON.stringify(window.roleIdArray);
            $.ajax({
                url: "role/remove/array.json",
                method: "post",
                data: requestBody,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED") {
                        layer.msg("操作失败:" + "" + response.message);
                    }

                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }

            });
            $("#confirmModal").modal("hide");

        });


        // 通过ajax添加角色
        $('#saveRoleBtn').click(function () {


            var roleName = $.trim($("#addModal [name=roleName]").val());
            // 发送ajax请求
            $.ajax({
                url: "role/save.json",
                data: {
                    roleName: roleName,

                },
                method: "post",
                dataType: "json",
                success: function (response) {
                    var result = response.result;

                    if (result == "SUCCESS") {
                        layer.msg("操作成功！");

                        // 将页码定位到最后一页
                        window.pageNum = 99999999;

                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == 'FAILED') {
                        layer.msg("操作失败！" + response.message);
                    }
                }

            });
            $('#addModal').modal('hide');
            $("#addModal [name=roleName]").val("")

        });


        //为每个更新按钮绑定单击响应函数
        $("#rolePageBody").on("click", ".pencilBtn", function () {


            $("#editModal").modal("show");
            var roleName = $(this).parent().prev().text();

            window.roleId = this.id;
            $("#editModal [name=roleName]").val(roleName);
        });

        // role 更新操作
        $("#updateRoleBtn").click(function () {
            var roleName = $.trim($("#editModal [name=roleName]").val());
            $.ajax({
                url: "role/update.json",
                method: "post",
                data: {
                    roleId: window.roleId,
                    roleName: roleName
                },
                dataType: "json",
                success: function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED") {

                        layer.msg("操作失败" + response.message);
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }

            });
            $("#editModal").modal("hide")

        });

    });


</script>


<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form  class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div id="" class="input-group-addon">查询条件</div>
                                <input  id="keywordInput"  class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchRoleBtn"  type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button onclick="showAddModel()" type="button" class="btn btn-primary" style="float:right;"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="summaryBox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">

                            </tbody>
                            <tfoot>
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
</body>
<%@ include file="/WEB-INF/modal-role-edit.jsp" %>
<%@ include file="/WEB-INF/modal-role-add.jsp" %>
<%@ include file="/WEB-INF/modal-roleArray.jsp" %>
<%@ include file="/WEB-INF/modal-role-assign.jsp" %>


</html>
