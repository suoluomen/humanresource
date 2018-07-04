<%--
  Created by IntelliJ IDEA.
  User: syc
  Date: 2016/3/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.Auth" %>
<%@ page import="com.shandong.human.resource.common.AuthTree" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
    <jsp:include page="../../header.jsp" flush="true"></jsp:include>

    <jsp:include page="../../left.jsp" flush="true"></jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>新增权限</h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>系统管理</li>
                <li>权限管理</li>
                <li class="active">添加权限</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-lg-6">
                    <div class="box box-info">
                        <div class="box-body">
                            <form action="<%=basePath%>sys/auth/add" role="form" id="add-auth-form">

                                <div class="form-group">
                                    <label>权限名称</label>
                                    <input name="name" class="form-control" type="text" placeholder="请输入权限名称">
                                </div>

                                <div class="form-group">
                                    <label>权限链接</label>
                                    <input name="url" class="form-control" type="text" placeholder="请输入权限链接">
                                </div>

                                <% AuthTree _Auths = (AuthTree) request.getAttribute("existAuth"); %>
                                <div class="form-group">
                                    <label>上级权限</label>
                                    <select name="pid" class="form-control">
                                        <% List<Auth> authList = AuthTree.toList(_Auths);
                                            for (Auth r : authList) {
                                                StringBuilder s = new StringBuilder();
                                                for (int i = 0; i < r.getLevel(); ++i)
                                                    s.append("&nbsp&nbsp&nbsp&nbsp");
                                                s.append('└').append(r.getName());
                                                int id = r.getId();
                                        %>
                                        <option name="pid" value="<%= id%>"><%= s.toString()%>
                                        </option>
                                        <%
                                            }
                                        %>

                                    </select>
                                </div>

                                <div class="form-group">
                                    <input type="button" class="btn btn-info" value="提交" id="add-auth-btn">
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>


    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <%--<script type='text/javascript' src='<%=basePath%>static/human/js/plugins/toastr/toastr.min.js'></script>--%>
    <script>
        //        toastr.options = {
        //            "closeButton": true,
        //            "debug": false,
        //            "progressBar": true,
        //            "positionClass": "toast-top-center",
        //            "onclick": null,
        //            "showDuration": "50",
        //            "hideDuration": "100",
        //            "timeOut": "1200",
        //            "extendedTimeOut": "100",
        //            "showEasing": "swing",
        //            "hideEasing": "linear",
        //            "showMethod": "fadeIn",
        //            "hideMethod": "fadeOut"
        //        };
        $(function () {
            // 新增角色
            $("#add-auth-btn").on('click', function () {
                // ajax 方式提交数据到某个url
                var postUrl = "<%= basePath%>sys/auth/add";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#add-auth-form").serialize(),
                    // 提交的数据,此处将整个表单的字段全部提交
                    // 也可以单独提交某个字段
//                data: {
//                    "username": username,
//                    "password": password
//                },
                    method: "post",
                    dataType: "json",
                    success: function (data) {

                        if (data.status == 'SUCCESS') {
                            toastr.success(data.info);
//                            toastr.success(data.info);
                            // 1000ms之后执行的操作
                            setTimeout(function () {
                                // 刷新页面
                                location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                //window.location.href = "<%=basePath%>sys/user";
                            }, 1000);
                        } else {
                            toastr.error(data.info);
//                            toastr.error(data.info);
                            return false;
                        }
                    }
                });
            });
        });
    </script>
</div>
</body>
</html>
