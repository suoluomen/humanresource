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
                报表详情
                <small>详情页</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>系统管理</li>
                <li class="active">用户查询</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">详情</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" id="check-form" class="form-horizontal">
                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">ID</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.id}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">PID</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.pid}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">公司ID</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.company_id}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">初始人数</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.init_people}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">当前人数</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.cur_people}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">其它原因</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.other_reason}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">减少类型</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reduce_type}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">原因1</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reason1}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">原因1解释</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reason1_explain}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">原因2</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reason2}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">原因2解释</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reason2_explain}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">原因3</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reason3}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">原因3解释</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.reason3_explain}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">状态</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.status}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">创建时间</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.create_time}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">更新时间</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.update_time}">
                                        </div>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">是否通过</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.status}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row" id="reason" style="display: none">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">不通过原因</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="form-control" disabled="disabled" value="${check.not_pass_reason}">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
    </script>
</body>
</html>

