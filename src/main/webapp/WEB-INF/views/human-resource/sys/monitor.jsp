<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.SurveyTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.shandong.human.resource.domain.MonitorInformationAddtion" %>
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


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                系统监控
                <small>图表分析</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>系统管理</li>
                <li class="active">系统监控</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- DONUT CHART -->
                    <div class="box box-danger">
                        <div class="box-header with-border">
                            <h3 class="box-title">系统内存使用情况</h3>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                                </button>
                            </div>
                        </div>
                        <%--<div class="box-body col-md-6">--%>
                        <%--可使用内存:${monitor.totalMemory}--%>
                        <%--</div>--%>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="chart-responsive">
                                        <canvas id="pieChart" style="height:250px"></canvas>
                                    </div><!-- ./chart-responsive -->
                                </div><!-- /.col -->
                                <div class="col-md-6">
                                    <ul class="chart-legend clearfix">
                                        <li><i class="fa fa-circle-o text-red"></i> 可使用内存 : ${m.totalMemory}</li>
                                        <li><i class="fa fa-circle-o text-green"></i> 剩余内存 : ${m.freeMemory}</li>
                                        <li><i class="fa fa-circle-o text-yellow"></i> 最大可使用内存 : ${m.maxMemory}</li>
                                        <li><i class="fa fa-circle-o text-aqua"></i> 总物理内存 : ${m.totalMemorySize}</li>
                                        <li><i class="fa fa-circle-o text-light-blue"></i> 剩余物理内存
                                            : ${m.freePhysicalMemorySize}</li>
                                        <li><i class="fa fa-circle-o text-gray"></i> 已使用物理内存 : ${m.usedMemory}</li>
                                    </ul>
                                </div><!-- /.col -->
                            </div><!-- /.row -->
                        </div>
                        <div class="box-footer no-padding">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">操作系统名称 : <span class=" text-green"><i class="fa"></i>${m.osName}</span></a>
                                </li>
                                <li><a href="#">总线程数 : <span class=" text-green"><i
                                        class="fa"></i> ${m.totalThread}</span></a></li>
                                <li><a href="#">JavaVersion : <span class=" text-green"><i
                                        class="fa"></i> ${mia.javaVersion}</span></a></li>
                                <li><a href="#">操作系统架构 : <span class=" text-green"><i class="fa"></i> ${mia.arch}</span></a>
                                </li>
                                <li><a href="#">Server Name : <span class=" text-green"><i
                                        class="fa"></i> ${mia.serverName}</span></a></li>
                                <li><a href="#">Server Address : <span class=" text-green"><i
                                        class="fa"></i> ${mia.serverAddr}</span></a></li>
                                <li><a href="#">Server Host : <span class=" text-green"><i
                                        class="fa"></i> ${mia.serverHost}</span></a></li>
                                <li><a href="#">Server Port : <span class=" text-green"><i
                                        class="fa"></i> ${mia.serverPort}</span></a></li>
                            </ul>
                        </div><!-- /.footer -->
                    </div>
                </div><!-- /.col (LEFT) -->
            </div><!-- /.col (RIGHT) -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <jsp:include page="../footer.jsp" flush="true"></jsp:include>

    <script>
        $(function () {
            /* ChartJS
             * -------
             * Here we will create a few charts using ChartJS
             */
            //-------------
            //- PIE CHART -
            //-------------
            // Get context with jQuery - using jQuery's .get() method.
            var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
            var pieChart = new Chart(pieChartCanvas);
            var PieData = [
                {
                    value: ${m.totalMemory},
                    color: "#f56954",
                    highlight: "#f56954",
                    label: "可使用内存"
                },
                {
                    value: ${m.freeMemory},
                    color: "#00a65a",
                    highlight: "#00a65a",
                    label: "剩余内存"
                },
                {
                    value: ${m.maxMemory},
                    color: "#f39c12",
                    highlight: "#f39c12",
                    label: "最大可使用内存"
                },
                {
                    value: ${m.totalMemorySize},
                    color: "#00c0ef",
                    highlight: "#00c0ef",
                    label: "总物理内存"
                },
                {
                    value: ${m.freePhysicalMemorySize},
                    color: "#3c8dbc",
                    highlight: "#3c8dbc",
                    label: "剩余物理内存"
                },
                {
                    value: ${m.usedMemory},
                    color: "#d2d6de",
                    highlight: "#d2d6de",
                    label: "已使用物理内存"
                }
            ];
            var pieOptions = {
                //Boolean - Whether we should show a stroke on each segment
                segmentShowStroke: true,
                //String - The colour of each segment stroke
                segmentStrokeColor: "#fff",
                //Number - The width of each segment stroke
                segmentStrokeWidth: 2,
                //Number - The percentage of the chart that we cut out of the middle
                percentageInnerCutout: 50, // This is 0 for Pie charts
                //Number - Amount of animation steps
                animationSteps: 100,
                //String - Animation easing effect
                animationEasing: "easeOutBounce",
                //Boolean - Whether we animate the rotation of the Doughnut
                animateRotate: true,
                //Boolean - Whether we animate scaling the Doughnut from the centre
                animateScale: false,
                //Boolean - whether to make the chart responsive to window resizing
                responsive: true,
                // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
                maintainAspectRatio: true
                //String - A legend template

            };
            //Create pie or douhnut chart
            // You can switch between pie and douhnut using the method below.
            pieChart.Doughnut(PieData, pieOptions);

        });
    </script>
</body>
</html>

