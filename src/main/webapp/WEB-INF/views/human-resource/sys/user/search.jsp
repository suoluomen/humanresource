<%--
  Created by IntelliJ IDEA.
  User: syc
  Date: 2016/3/19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.util.Pager" %>
<%@ page import="com.shandong.human.resource.domain.User" %>
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

    <div class="content-wrapper" style="min-height: 916px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户列表
                <small>系统用户</small>
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
                            <h3 class="box-title">用户查询</h3>
                        </div>
                        <div class="box-body">
                            <form class="form-horizontal" role="form" id="select-user-form"
                                  action="<%=basePath%>sys/user/search" method="post">
                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4 ">
                                            <lable class="control-label" for="name">用户名</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <input id="userName" class="form-control" type="text" name="userName"
                                                   placeholder="用户名">
                                        </div>
                                    </div>

                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4 ">
                                            <lable class="control-label" for="name">单位名称</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <input id="companyName" class="form-control" type="text" name="companyName"
                                                   placeholder="单位名称">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4">
                                            <lable class="control-label" for="type">类型</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <select name="userType" class="form-control">
                                                <option name="userType" value="null">所有用户</option>
                                                <option name="userType" value="1">省用户</option>
                                                <option name="userType" value="2">企业用户</option>
                                                <c:forEach items="${cityList}" var="v">
                                                    <option name="userType" value="${v.id}">${v.name}用户</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4">
                                            <lable class="control-label" for="role">所属地市</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <select id="areaID" name="areaID" class="form-control">
                                                <option name="areaID" value="null">所有地市</option>
                                                <c:forEach items="${areaList}" var="v">
                                                    <option name="areaID" value="${v.id}">${v.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4 ">
                                            <lable class="control-label" for="name">地址</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <input id="address" class="form-control" type="text" name="address"
                                                   placeholder="地址">
                                        </div>
                                    </div>

                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4 ">
                                            <lable class="control-label" for="role">申报状态</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <select id="status" name="status" class="form-control">
                                                <option name="status" value="null">所有状态</option>
                                                <option name="status" value="-2">省审核不通过</option>
                                                <optiosn name="status" value="-1">市审核不通过</optiosn>
                                                <option name="status" value="0">待审核</option>
                                                <option name="status" value="1">市审核通过，待省审核</option>
                                                <option name="status" value="2">省审核通过，待申报</option>
                                                <option name="status" value="3">已上报</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4">
                                            <lable class="control-label" for="name">单位性质</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <input id="business" class="form-control" type="text" name="business"
                                                   placeholder="单位性质">
                                        </div>
                                    </div>

                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-4">
                                            <lable class="control-label" for="role">查询时间段</lable>
                                        </div>
                                        <div class="col-xs-8">
                                            <select id="surveyTimeID" name="surveyTimeID" class="form-control">
                                                <option name="surveyTimeID"
                                                        value="null">所有时间段
                                                </option>
                                                <c:forEach items="${surveyTimeList}" var="v">
                                                    <option name="surveyTimeID"
                                                            value="${v.id}"><fmt:formatDate value="${v.start_time}"
                                                                                            type="both"
                                                                                            pattern="yyyy-MM-dd"/>&nbsp;到&nbsp;<fmt:formatDate
                                                            value="${v.end_time}" type="both"
                                                            pattern="yyyy-MM-dd"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-xs-6">
                                        <input id="select-user-btn" type="submit" class="btn btn-info" value="查询">
                                    </div>
                                    <div class="col-xs-6">
                                        <input id="select-reset-btn" type="reset" class="btn btn-danger" value="重置">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>类型</th>
                                    <th>公司名</th>
                                    <th>报表</th>
                                </tr>
                                <c:forEach items="${data}" var="v">
                                <tr>
                                    <td>${v.first.id}</td>
                                    <td>${v.first.username}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${v.first.type eq '1'}">
                                                <span class="label label-success">省用户</span>
                                            </c:when>
                                            <c:when test="${v.first.type eq '2'}">
                                                <span class="label label-info">企业用户</span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="typeflag" value="true"/>
                                                <c:forEach items="${cityList}" var="r" varStatus="loopstatus">
                                                    <c:if test="typeflag"></c:if>
                                                    <c:if test="${v.first.type eq r.id}">
                                                        <span class="label label-warning">${r.name}用户</span>
                                                        <c:set var="typeflag" value="false"/>
                                                    </c:if>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${v.second.first ne null}">
                                                ${v.second.first.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-info" href="<%=basePath%>sys/record/${v.second.first.id}">详情</a>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${v.second.second ne null}">
                                                调查期就业人数:${v.second.second.cur_people}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-info" href="<%=basePath%>sys/user/detail/${v.second.second.id}">详情</a>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    </c:forEach>
                                </tr>
                                </tbody>
                            </table>
                        </div><!-- /.box-body -->
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h3>共${data.size()}条记录</h3>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.box -->
                </div>
            </div>


        </section><!-- /.content -->
    </div>

    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
</div>
</body>
</html>
