<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 2016/3/14
  Time: 13:25
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
                数据修改
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>企业</li>
                <li class="active">数据修改</li>
            </ol>
        </section>


        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <form role="form" id="edit-form">
                        <input type="hidden" value="${companyData.id}" name="id">
                        <input type="hidden" value="${companyData.company_id}" name="company_id">
                        <div class="row">
                            <!-- left column -->
                            <div class="col-md-6">
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title"></h3>
                                    </div><!-- /.box-header -->
                                    <!-- form start -->

                                    <div class="box-body">
                                        <div class="form-group">
                                            <label>调查期时间</label>
                                            <select aria-hidden="true" tabindex="-1" class="form-control"
                                                    style="width: 100%;" id="survey_time_id" name="survey_time_id">
                                                <%--<option selected="selected"></option>--%>
                                                <c:forEach items="${listSurverTime}" var="v">
                                                    <option value="${v.id}"><fmt:formatDate value="${v.start_time}"
                                                                                            type="both"
                                                                                            pattern="yyyy-MM-dd HH:mm"/>
                                                        到 <fmt:formatDate value="${v.end_time}" type="both"
                                                                          pattern="yyyy-MM-dd HH:mm"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>建档期就业人数</label>
                                            <input class="form-control" value="${companyData.init_people}" type="text"
                                                   id="init_people" name="init_people">
                                        </div>
                                        <div class="form-group">
                                            <label>调查期就业人数</label>
                                            <input class="form-control" value="${companyData.cur_people}" type="text"
                                                   id="cur_people" name="cur_people">
                                        </div>
                                        <div class="form-group">
                                            <label>就业人数减少类型</label>
                                            <select aria-hidden="true" tabindex="-1" class="form-control"
                                                    style="width: 100%;" id="reduce_type" name="reduce_type">
                                                <option selected="selected">${companyData.reduce_type}</option>
                                                <c:forEach items="${listType}" var="v">
                                                    <option value="${v.name}">${v.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>主要原因</label>
                                            <select aria-hidden="true" tabindex="-1" class="form-control"
                                                    style="width: 100%;" id="reason1" name="reason1">
                                                <option selected="selected">${companyData.reason1}</option>
                                                <c:forEach items="${listReason}" var="v">
                                                    <option value="${v.name}">${v.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>主要原因说明</label>
                                            <textarea style="height: 64px;" class="form-control" rows="3"
                                                      name="reason1_explain"
                                                      id="reason1_explain">${companyData.reason1_explain}</textarea>
                                        </div>
                                        <div class="box-footer">
                                            <button id="edit-btn" type="button" class="btn btn-primary">上报
                                            </button>
                                        </div>
                                    </div><!-- /.box-body -->
                                </div><!-- /.content -->
                            </div><!--/.col (left) -->
                            <!-- right column -->
                            <div class="col-md-6">
                                <!-- Horizontal Form -->
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title"></h3>
                                    </div><!-- /.box-header -->
                                    <!-- form start -->

                                    <div class="box-body">

                                        <div class="form-group">
                                            <label>次要原因</label>
                                            <select aria-hidden="true" tabindex="-1" class="form-control"
                                                    style="width: 100%;" id="reason2" name="reason2">
                                                <option selected="selected">${companyData.reason2}</option>
                                                <c:forEach items="${listReason}" var="v">
                                                    <option value="${v.name}">${v.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label>次要原因说明</label>
                                            <textarea style="height: 64px;" class="form-control" rows="3"
                                                      name="reason2_explain"
                                                      id="reason2_explain">${companyData.reason2_explain}</textarea>
                                        </div><!-- /.form-group -->
                                        <div class="form-group">
                                            <label>第三原因</label>
                                            <select aria-hidden="true" tabindex="-1" class="form-control"
                                                    style="width: 100%;" id="reason3" name="reason3">
                                                <option selected="selected">${companyData.reason3}</option>
                                                <c:forEach items="${listReason}" var="v">
                                                    <option value="${v.name}">${v.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label>第三原因说明</label>
                                            <textarea class="form-control" rows="3" placeholder=""
                                                      name="reason3_explain"
                                                      id="reason3_explain">${companyData.reason3_explain}</textarea>
                                        </div><!-- /.form-group -->
                                        <div class="form-group">
                                            <label>其它原因</label>
                                            <textarea class="form-control" rows="3" placeholder="" id="other_reason"
                                                      name="other_reason">${companyData.other_reason}</textarea>
                                        </div>
                                        <c:if test="${companyData.not_pass_reason != null and companyData.not_pass_reason !=''}">
                                        <div class="form-group">
                                            <label>审核不通过原因</label>
                                            <p>${companyData.not_pass_reason}</p>
                                        </div>
                                        </c:if>

                                    </div><!-- /.box-body -->

                                </div><!-- /.content -->
                            </div><!--/.col (right) -->
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div><!-- /.content-wrapper -->

    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            $("#edit-btn").on('click', function () {
                // ajax 方式提交数据到某个url
//                alert("fs");
                var postUrl = "<%= basePath%>home/data/edit";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#edit-form").serialize(),
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
                            // 1000ms之后执行的操作
                            setTimeout(function () {
                                // 刷新页面
//                                location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                window.location.href = "<%=basePath%>home/data/add";
                            }, 1000);
                        } else {
                            toastr.error(data.info);
                            return false;
                        }
                    }
                });
            })
        });

        <%--$(document).ready(function () {--%>
            <%--<%Integer status=(Integer) request.getAttribute("status");%>--%>
            <%--var status = "<%=status.intValue()%>";--%>
            <%--<%String infoStr=(String)request.getAttribute("Info");%>--%>
            <%--var info = "<%=infoStr%>";--%>
            <%--if (status == -2 || status == -1) {--%>
                <%--toastr.error(info);--%>
            <%--}--%>
            <%--else if (status >= 0) {--%>
                <%--toastr.error(info);--%>
                <%--setTimeout(function () {--%>
                    <%--window.location.href = "<%=basePath%>";--%>
                <%--}, 3500);--%>
            <%--}--%>
        <%--});--%>


    </script>
</body>
</html>