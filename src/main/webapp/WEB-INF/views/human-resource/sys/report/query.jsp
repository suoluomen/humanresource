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
                报表上报
                <small>报表查询</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>报表上报</li>
                <li class="active">报表查询</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-danger">
                <div class="box-header with-border">
                    <h3 class="box-title">查询</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <form class="form-horizontal" action="<%=basePath%>sys/report/query" method="get">
                            <div class="col-xs-2">
                                <button type="submit" class="btn btn-primary">查询全部已通过市审核报表</button>
                            </div>
                        </form>
                    </div>
                    <hr>
                    <div class="row">
                        <form class="form-horizontal" action="<%=basePath%>sys/report/query" method="post">
                            <div class="col-xs-12 form-group">
                                <div class="col-xs-2 ">
                                    <label class="control-label">请输入公司ID</label>
                                </div>
                                <div class="col-xs-2 ">
                                    <input class="form-control" name="company_id">
                                </div>
                                <div class="col-xs-4 ">
                                    <button class="btn btn-primary" type="submit">通过公司ID查询</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <hr>
                    <div class="row">
                        <form class="form-horizontal" id="query-form" action="<%=basePath%>sys/report/queryByName" method="post">
                            <div class="col-xs-12 form-group">
                                <div class="col-xs-2 ">
                                    <label class="control-label">请输入公司名称</label>
                                </div>
                                <div class="col-xs-2 ">
                                    <input class="form-control" name="name">
                                </div>
                                <div class="col-xs-4 ">
                                    <button class="btn btn-primary" type="submit">通过公司名称查询</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">报表查询</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <form id="report-form" method="post">
                                <table id="dataTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>选&nbsp;&nbsp;择</th>
                                        <th>ID</th>
                                        <th>公司ID</th>
                                        <th>初始人数</th>
                                        <th>现在人数</th>
                                        <th>其他原因</th>
                                        <th>状&nbsp;&nbsp;态</th>
                                        <th>创建时间</th>
                                        <th>更新时间</th>
                                        <th>查看详情</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${reportResult}" var="v">
                                        <tr>
                                            <td><input type="checkbox" name="choose" value="${v.id}"></td>
                                            <td>${v.id}</td>
                                            <td>${v.company_id}</td>
                                            <td>${v.init_people}</td>
                                            <td>${v.cur_people}</td>
                                            <td>${v.other_reason}</td>
                                            <td>${v.status}</td>
                                            <td>${v.create_time}</td>
                                            <td>${v.update_time}</td>
                                            <td><a href="<%=basePath%>sys/report/${v.company_id}"
                                                   class="btn btn-primary">详情</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <button type="button" class="btn btn-primary" id="submit-btn">上 报</button>
                            </form>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 模板自带分页功能的数据表格,不能删
            $("#dataTable").DataTable();

            // 数据上报
            $("#submit-btn").on('click', function () {
                var postUrl = "<%= basePath%>sys/report";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#report-form").serialize(),
                    method: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.status == 'SUCCESS') {
                            toastr.success(data.info);
                            // 1000ms之后执行的操作
                            setTimeout(function () {
                                // 刷新页面
                                location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                // window.location.href = "<%=basePath%>";
                            }, 1000);
                        } else {
                            toastr.error(data.info);
                            return false;
                        }
                    }
                });
            });
        });
    </script>
</body>
</html>

