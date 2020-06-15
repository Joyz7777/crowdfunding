<%@ page import="java.io.Writer" %>
<%@ page import="org.springframework.stereotype.Repository" %>
<%@ page import="com.jo.crowd.mvc.controller.TestController" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.AnnotationConfigWebApplicationContext" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.jo.crowd.service.api.AdminService" %>
<%@ page import="com.jo.crowd.dao.AdminDao" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020/5/12
  Time: 9:51 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


    <% String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        AdminDao adminDao = new AdminDao();
        System.out.println(adminDao);

    %>


    <script type="text/javascript" src="<%=basePath%>jquery/jquery-2.1.1.min.js"></script>


    <script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">


        $(function () {

            $("#btn3").click(function () {
                $.ajax({
                    url: "/test/exception.html",
                    contentType: "application/json;charset=UTF-8",
                    type: "get",
                    success: function (response) {
                        console.log(response);
                    }

                });
            });


            $("#btn2").click(function () {

                var complexObj = {
                    name: 'jason',
                    age: '20',
                    school: {
                        primary: '小学',
                        middle: '中学',
                        senior: '高中'
                    },
                    map: {
                        k1: 'v2'
                    }


                };


                var requestBody = JSON.stringify(complexObj);
                $.ajax({
                    url: "student/test.html",
                    contentType: "application/json;charset=UTF-8",
                    dataType: "text",
                    type: "post",
                    data: requestBody,
                    success: function (response) {
                        alert(response);
                    }

                })
            });

        });


    </script>
</head>


<body>

<a href="${pageContext.request.contextPath}/test/ssm.html">jsp</a>

<button id="btn">点我传输数据</button>
<button id="btn2">发送复杂数据</button>
<br>
<button id="btn3">点我测试统一返回结果</button>


</body>
</html>
