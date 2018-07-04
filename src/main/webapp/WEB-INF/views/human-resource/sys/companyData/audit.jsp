<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../../header.jsp" flush="true"></jsp:include>

    <jsp:include page="../../left.jsp" flush="true"></jsp:include>

    <!-- 主要内容开始 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->

        <!-- Main content -->
        <section class="content-header">
            <h1>
                企业上报数据
                <small>详情</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>企业管理</li>
                <li class="active">企业数据上报</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <h3 class="box-title">上报数据详情</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" method="post" id="company-data-form">
                                <!-- text input -->
                                <div class="form-group">
                                    <label for="company_id">公司名</label>
                                    <p>${company.name}</p>
                                    <input type="hidden" type="text" value=${companyData.id} class="form-control" name="id" id="id" placeholder="Enter ...">
                                    <input type="hidden" type="text" value=${companyData.pid} class="form-control" name="pid" id="pid" placeholder="Enter ...">
                                    <input type="hidden" value=${companyData.company_id} class="form-control" name="company_id" id="company_id">
                                </div>
                                <div class="form-group">
                                    <label for="survey_time_id">调查期</label>
                                    <p>
                                        <fmt:formatDate value="${surveyTime.start_time}" pattern="yyyy年MM月dd日HH点mm分ss秒" />
                                        至
                                        <fmt:formatDate value="${surveyTime.end_time}" pattern="yyyy年MM月dd日HH点mm分ss秒" />
                                    </p>
                                    <input type="hidden" value=${companyData.survey_time_id} class="form-control"
                                           name="survey_time_id" id="survey_time_id"
                                           placeholder="">
                                </div>
                                <div class="form-group">
                                    <label>建档期就业人数</label>
                                    <p>${companyData.init_people}</p>
                                    <%--<input type="text" value=${companyData.init_people} class="form-control"--%>
                                           <%--name="init_people" id="init_people"--%>
                                           <%--placeholder="建档期就业人数">--%>
                                </div>
                                <div class="form-group">
                                    <label>调查期就业人数</label>
                                    <p>${companyData.cur_people}</p>
                                    <%--<input type="text" value=${companyData.cur_people} class="form-control"--%>
                                           <%--name="cur_people" id="cur_people"--%>
                                           <%--placeholder="调查期就业人数">--%>
                                </div>
                                <div class="form-group">
                                    <label>减少类型</label>
                                    <p>${companyData.reduce_type}</p>
                                    <%--<input type="text" value=${companyData.reduce_type} class="form-control"--%>
                                           <%--name="reduce_type" id="reduce_type"--%>
                                           <%--placeholder="Enter ...">--%>
                                </div>
                                <div class="form-group">
                                    <label>主要原因</label>
                                    <p>${companyData.reason1}</p>
                                    <%--<input type="text" value=${companyData.reason1} class="form-control" name="reason1"--%>
                                           <%--id="reason1"--%>
                                           <%--placeholder="主要原因">--%>
                                </div>
                                <div class="form-group">
                                    <label>主要原因说明</label>
                                    <p>${companyData.reason1_explain}</p>
                                    <%--<input type="text" value=${companyData.reason1_explain} class="form-control"--%>
                                           <%--name="reason1_explain" id="reason1_explain"--%>
                                           <%--placeholder="主要原因说明">--%>
                                </div>
                                <div class="form-group">
                                    <label>次要原因</label>
                                    <p>${companyData.reason2}</p>
                                    <%--<input type="text" value=${companyData.reason2} class="form-control" name="reason2"--%>
                                           <%--id="reason2"--%>
                                           <%--placeholder="次要原因">--%>
                                </div>
                                <div class="form-group">
                                    <label>次要原因说明</label>
                                    <p>${companyData.reason2_explain}</p>
                                    <%--<input type="text" value=${companyData.reason2_explain} class="form-control"--%>
                                           <%--name="reason2_explain" id="reason2_explain"--%>
                                           <%--placeholder="次要原因说明">--%>
                                </div>
                                <div class="form-group">
                                    <label>第三原因</label>
                                    <p>${companyData.reason3}</p>
                                    <%--<input type="text" value=${companyData.reason3} class="form-control" name="reason3"--%>
                                           <%--id="reason3"--%>
                                           <%--placeholder="第三原因">--%>
                                </div>
                                <div class="form-group">
                                    <label>第三原因说明</label>
                                    <p>${companyData.reason3_explain}</p>
                                    <%--<input type="text" value=${companyData.reason3_explain} class="form-control"--%>
                                           <%--name="reason3_explain" id="reason3_explain"--%>
                                           <%--placeholder="第三原因说明">--%>
                                </div>
                                <!-- select -->
                                <div class="form-group">
                                    <label>审核</label>
                                    <select class="form-control" name="status" id="status">
                                        <c:choose>
                                            <c:when test="${localUser.type eq 1}">
                                                <%--省用户--%>
                                                <option value="-2">省审核不通过</option>
                                                <option value="1">市审核通过,待省审核</option>
                                                <option value="2">省审核通过,待上报到部</option>
                                                <option value="3">已上报到部</option>
                                            </c:when>
                                            <c:when test="${localUser.type gt 2}">
                                                <%--市用户--%>
                                                <option value="-1">市审核不通过</option>
                                                <option value="0">待市审核</option>
                                                <option value="1">市审核通过,待省审核</option>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="not_pass_reason">审核不通过原因</label>
                                    <input type="text" value="${companyData.not_pass_reason}" class="form-control"
                                           name="not_pass_reason" id="not_pass_reason"
                                           placeholder="${companyData.not_pass_reason}">
                                </div>
                                <div class="form-group">
                                    <button type="button" id="submit-btn" class="btn btn-primary">提交修改</button>
                                </div>
                            </form>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!--/.col (right) -->
            </div>   <!-- /.row -->
        </section>
    </div>
</div>

<!-- /.主要内容结束 -->
<jsp:include page="../../footer.jsp" flush="true"></jsp:include>
<script>
    $(function () {
        // 提交按钮
        $("#submit-btn").on('click', function () {
            var status = $("#status").val().trim();
            var not_pass_reason = $("#not_pass_reason").val();

            if ((status == -2 || status == -1) && not_pass_reason == "") {
                toastr.error("审核不通过必须填写不通过原因");
                return false;
            }

            var postUrl = "<%= basePath%>sys/data/audit";
            $.ajax({
                url: postUrl,//提交的地址
                data: $("#company-data-form").serialize(),
                method: "post",
                dataType: "json",
                success: function (data) {
                    if (data.status == 'SUCCESS') {
                        toastr.success(data.info);
                        // 1000ms之后执行的操作
                        setTimeout(function () {
                            // 刷新页面
                            // location.reload(true);
                            // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                            window.location.href = "<%=basePath%>sys/data/list";
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
