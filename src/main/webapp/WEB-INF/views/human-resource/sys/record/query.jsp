<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.SurveyTime" %>
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
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                企业备案
                <small>备案列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>企业备案管理</li>
                <li class="active">备案列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-danger">
                <div class="box-header with-border">
                    <h3 class="box-title">条件查询</h3>
                </div>
                <div class="box-body">
                    <div class="row" style="position:relative">
                        <form id="query-form" action="<%=basePath%>sys/record/query" method="post">
                            <div class="col-xs-1">
                                <label>市id</label>
                                <input type="text" class="form-control" name="city_id">
                            </div>
                            <div class="col-xs-1">
                                <label>区id</label>
                                <input type="text" class="form-control" name="area_id">
                            </div>
                            <div class="col-xs-2">
                                <label>企业名称</label>
                                <input type="text" class="form-control" name="name">
                            </div>
                            <div class="col-xs-2">
                                <label>企业代码</label>
                                <input type="text" class="form-control" name="code">
                            </div>
                            <div class="col-xs-2">
                                <label>联系人</label>
                                <input type="text" class="form-control" name="contact">
                            </div>

                            <button class="btn btn-primary" style="margin-top:24px;" type="submit" id="submit-btn">查询</button>
                        </form>
                    </div>
                </div><!-- /.box-body -->
            </div><!-- /.box -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">备案列表</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <table id="dataTable" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>公司名称</th>
                                    <th>联系人</th>
                                    <th>地址</th>
                                    <th>电话号码</th>
                                    <th>详情</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${recordResult}" var="v">
                                    <tr>
                                        <td>${v.id}</td>
                                        <td>${v.name}</td>
                                        <td>${v.contact}</td>
                                        <td>${v.address}</td>
                                        <td>${v.phone}</td>
                                        <td><a href="<%=basePath%>sys/record/${v.id}" class="btn btn-primary">详情</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
            <form id="excel-form" action="<%=basePath%>sys/record/export" method="post">
                <button type="submit" class="btn btn-primary" id="submit-btn">导出到excel</button>
            </form>
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 模板自带分页功能调用,不能删
            $("#dataTable").DataTable();
        });
    </script>
</body>
</html>

