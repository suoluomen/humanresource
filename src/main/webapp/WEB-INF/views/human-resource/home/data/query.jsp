<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 2016/3/20
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.SurveyTime" %>
<%@ page import="com.shandong.human.resource.domain.Area" %>
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
                数据查询
                <small>查询列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>数据查询</li>
                <li class="active">查询列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <%--<div class="box box-danger">--%>
            <%--<div class="box-header with-border">--%>
            <%--<h3 class="box-title">条件查询</h3>--%>
            <%--</div>--%>
            <%--<div class="box-body">--%>
            <%--<div class="row" style="position:relative">--%>
            <%--<form id="query-form" action="<%=basePath%>sys/record/query" method="post">--%>
            <%--<div class="col-xs-1">--%>
            <%--<label>市id</label>--%>
            <%--<input type="text" class="form-control" name="city_id">--%>
            <%--</div>--%>
            <%--<div class="col-xs-1">--%>
            <%--<label>区id</label>--%>
            <%--<input type="text" class="form-control" name="area_id">--%>
            <%--</div>--%>
            <%--<div class="col-xs-2">--%>
            <%--<label>企业名称</label>--%>
            <%--<input type="text" class="form-control" name="name">--%>
            <%--</div>--%>
            <%--<div class="col-xs-2">--%>
            <%--<label>企业代</label>--%>
            <%--<input type="text" class="form-control" name="code">--%>
            <%--</div>--%>
            <%--<div class="col-xs-2">--%>
            <%--<label>联系人</label>--%>
            <%--<input type="text" class="form-control" name="contact">--%>
            <%--</div>--%>


            <%--<button class="btn btn-primary" style="margin-top:24px;" type="submit" id="submit-btn">查询</button>--%>
            <%--</form>--%>
            <%--</div>--%>
            <%--</div><!-- /.box-body -->--%>
            <%--</div><!-- /.box -->--%>

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
                                    <th>调查开始时间</th>
                                    <th>调查结束时间</th>
                                    <th>建档期就业人数</th>
                                    <th>调查期就业人数</th>
                                    <th>其它原因</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${companyDataQuaryLists}" var="v">
                                    <tr class="odd" role="row">
                                        <td>
                                            <fmt:formatDate value="${v.start_time}" type="both"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td><fmt:formatDate value="${v.end_time}" type="both"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>${v.init_people}</td>
                                        <td>${v.cur_people}</td>
                                        <td>${v.other_reason}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
    </div>
    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 模板自带分页功能调用,不能删
            $("#dataTable").DataTable();
        });
    </script>
</body>
</html>