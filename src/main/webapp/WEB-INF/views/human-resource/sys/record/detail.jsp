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
                企业备案详情
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>企业备案管理</li>
                <li class="active">企业备案详情</li>
            </ol>
        </section>


        <section class="content">

            <div class="row">
                <div class="col-md-3">
                    <!-- About Me Box -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title"><strong>${companyDetail.name}</strong>的详情</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <strong><i class="fa fa-book margin-r-5"></i>ID</strong>
                            <p class="text-muted">${companyDetail.id}</p>
                            <hr>

                            <strong><i class="fa fa-map-marker margin-r-5"></i>省,市,区 ID</strong>
                            <p class="text-muted">${companyDetail.province_id},${companyDetail.city_id},${companyDetail.area_id}</p>
                            <hr>

                            <strong><i class="fa fa-map-marker margin-r-5"></i>地址</strong>
                            <p class="text-muted">${companyDetail.address}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>邮编</strong>
                            <p class="text-muted">${companyDetail.zipcode}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>企业代码</strong>
                            <p class="text-muted">${companyDetail.code}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>公司名称</strong>
                            <p class="text-muted">${companyDetail.name}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>联系人</strong>
                            <p class="text-muted">${companyDetail.contact}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>电话</strong>
                            <p class="text-muted">${companyDetail.phone}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>传真</strong>
                            <p class="text-muted">${companyDetail.fax}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>电子邮箱</strong>
                            <p class="text-muted">${companyDetail.email}</p>
                            <hr>

                            <strong><i class="fa fa-book margin-r-5"></i>业务</strong>
                            <p class="text-muted">${companyDetail.business}</p>
                            <hr>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#timeline" data-toggle="tab">报表时间线</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="timeline">
                                <!-- The timeline -->
                                <c:forEach items="${companyDataList}" var="v">
                                    <ul class="timeline timeline-inverse">
                                        <!-- timeline time label -->
                                        <li class="time-label">
                                            <span class="bg-red">创建时间:${v.create_time}</span>
                                        </li>
                                        <!-- /.timeline-label -->
                                        <!-- timeline item -->
                                        <li>
                                            <i class="fa fa-envelope bg-blue"></i>
                                            <div class="timeline-item">
                                                <span class="time"><i class="fa fa-clock-o"></i></span>
                                                <h3 class="timeline-header">
                                                        <strong>${companyDetail.name}</strong>的报表
                                                </h3>
                                                <div class="timeline-body">
                                                    <strong>ID : </strong>${v.id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <strong>PID : </strong>${v.pid}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <strong>公司ID : </strong>${v.company_id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <strong>调查时间ID : </strong>${v.survey_time_id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>初始人数 : </strong>${v.init_people}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <strong>当前人数 : </strong>${v.cur_people}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>其它原因 : </strong>${v.other_reason}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <strong>减少类型 : </strong>${v.reduce_type}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>主要原因 : </strong>${v.reason1}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>主要原因解释 : </strong>${v.reason1_explain}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>次要原因 : </strong>${v.reason2}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>次要原因解释 : </strong>${v.reason2_explain}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>第三原因 : </strong>${v.reason3}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>第三原因解释 : </strong>${v.reason3_explain}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <br>
                                                    <strong>状态 : </strong>${v.status}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(状态:
                                                    -2:省审核不通过; -1:市审核不通过; 0:待市审核; 1:市审核通过,待省审核; 2:省审核通过,待上报到部; 3:已上报到部)
                                                    <br>
                                                    <strong>未过审理由 : </strong>${v.not_pass_reason}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                </div>
                                                <div class="timeline-footer">
                                                </div>
                                            </div>
                                        </li>
                                        <!-- END timeline item -->
                                        <!-- timeline item -->
                                        <li class="time-label">
                                            <span class="bg-green">更新时间:${v.update_time}</span>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </div><!-- /.tab-pane -->
                        </div><!-- /.tab-content -->
                    </div><!-- /.nav-tabs-custom -->
                </div><!-- /.col -->
            </div><!-- /.row -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>

    <script>
    </script>
</body>
</html>

