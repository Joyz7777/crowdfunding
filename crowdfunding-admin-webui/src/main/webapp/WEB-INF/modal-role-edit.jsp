<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020/6/10
  Time: 11:33 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-signin" >
                    <input type="text" name="roleName"
                           class="form-control"
                           autofocus>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="updateRoleBtn" class="btn btn-primary">Update</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->