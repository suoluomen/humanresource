<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.SurveyTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../header.jsp" flush="true"></jsp:include>

    <jsp:include page="../left.jsp" flush="true"></jsp:include>

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                市审核
                <small>查询</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>主页</a></li>
                <li><a href="#">市审核</a></li>
                <li class="active">查询</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <%--<div class="box box-danger">--%>
                <%--<div class="box-header with-border">--%>
                    <%--<h3 class="box-title">查询</h3>--%>
                <%--</div>--%>
                <%--<div class="box-body">--%>
                    <%--<div class="row">--%>
                        <%--<form id="query-form" action="<%=basePath%>city/query" method="get">--%>
                            <%--<div class="col-xs-2">--%>
                            <%--<button type="submit" id="submit-btn">查询全部</button>--%>
                            <%--</div>--%>
                        <%--</form>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">查询结果</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <table id="dataTable" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>公司ID</th>
                                    <th>初始人数</th>
                                    <th>现在人数</th>
                                    <th>其他原因</th>
                                    <th>状态</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th>查看详情</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${cityQueryResult}" var="v">
                                    <tr>
                                        <td>${v.id}</td>
                                        <td>${v.company_id}</td>
                                        <td>${v.init_people}</td>
                                        <td>${v.cur_people}</td>
                                        <td>${v.other_reason}</td>
                                        <td>${v.status}</td>
                                        <td>${v.create_time}</td>
                                        <td>${v.update_time}</td>
                                        <td><a href="<%=basePath%>sys/city/check/${v.id}" class="btn btn-primary">审核</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:include page="../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            $("#dataTable").DataTable();
        });
    </script>
</body>
</html>

