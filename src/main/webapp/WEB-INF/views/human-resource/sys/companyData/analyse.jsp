<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="content-wrapper" style="min-height: 976px;">

        <section class="content-header">
            <h1>修改权限</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>主页</a></li>
                <li><a href="#">系统管理</a></li>
                <li class="active">授权</li>
            </ol>
        </section>

        <section class="content">
            <!-- Info boxes -->

            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">查看全省企业的汇总数据</h3>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                                <div class="btn-group">
                                    <button class="btn btn-box-tool dropdown-toggle" data-toggle="dropdown"><i
                                            class="fa fa-wrench"></i></button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                    </ul>
                                </div>
                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                                </button>
                            </div>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="chart">
                                        <!-- Sales Chart Canvas -->
                                        <div id="main" style="height: 360px; width: 1000px;"></div>
                                    </div><!-- /.chart-responsive -->
                                </div><!-- /.col -->
                                <%--<div class="col-md-4">--%>
                                    <%--<div>--%>
                                        <%--<p class="text-center">--%>
                                            <%--<strong>Goal Completion</strong>--%>
                                        <%--</p>--%>
                                        <%--<div class="progress-group">--%>
                                            <%--<span class="progress-text">Add Products to Cart</span>--%>
                                            <%--<span class="progress-number"><b>160</b>/200</span>--%>
                                            <%--<div class="progress sm">--%>
                                                <%--<div class="progress-bar progress-bar-aqua" style="width: 80%"></div>--%>
                                            <%--</div>--%>
                                        <%--</div><!-- /.progress-group -->--%>
                                        <%--<div class="progress-group">--%>
                                            <%--<span class="progress-text">Complete Purchase</span>--%>
                                            <%--<span class="progress-number"><b>310</b>/400</span>--%>
                                            <%--<div class="progress sm">--%>
                                                <%--<div class="progress-bar progress-bar-red" style="width: 80%"></div>--%>
                                            <%--</div>--%>
                                        <%--</div><!-- /.progress-group -->--%>
                                        <%--<div class="progress-group">--%>
                                            <%--<span class="progress-text">Visit Premium Page</span>--%>
                                            <%--<span class="progress-number"><b>480</b>/800</span>--%>
                                            <%--<div class="progress sm">--%>
                                                <%--<div class="progress-bar progress-bar-green" style="width: 80%"></div>--%>
                                            <%--</div>--%>
                                        <%--</div><!-- /.progress-group -->--%>
                                        <%--<div class="progress-group">--%>
                                            <%--<span class="progress-text">Send Inquiries</span>--%>
                                            <%--<span class="progress-number"><b>250</b>/500</span>--%>
                                            <%--<div class="progress sm">--%>
                                                <%--<div class="progress-bar progress-bar-yellow" style="width: 80%"></div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div><!-- /.progress-group -->--%>
                                <%--</div><!-- /.col -->--%>
                            </div><!-- /.row -->
                        </div><!-- ./box-body -->
                        <%--<div class="box-footer">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-sm-3 col-xs-6">--%>
                                    <%--<div class="description-block border-right">--%>
                                        <%--<span class="description-percentage text-green"><i class="fa fa-caret-up"></i> 17%</span>--%>
                                        <%--<h5 class="description-header">$35,210.43</h5>--%>
                                        <%--<span class="description-text">TOTAL REVENUE</span>--%>
                                    <%--</div><!-- /.description-block -->--%>
                                <%--</div><!-- /.col -->--%>
                                <%--<div class="col-sm-3 col-xs-6">--%>
                                    <%--<div class="description-block border-right">--%>
                                        <%--<span class="description-percentage text-yellow"><i--%>
                                                <%--class="fa fa-caret-left"></i> 0%</span>--%>
                                        <%--<h5 class="description-header">$10,390.90</h5>--%>
                                        <%--<span class="description-text">TOTAL COST</span>--%>
                                    <%--</div><!-- /.description-block -->--%>
                                <%--</div><!-- /.col -->--%>
                                <%--<div class="col-sm-3 col-xs-6">--%>
                                    <%--<div class="description-block border-right">--%>
                                        <%--<span class="description-percentage text-green"><i class="fa fa-caret-up"></i> 20%</span>--%>
                                        <%--<h5 class="description-header">$24,813.53</h5>--%>
                                        <%--<span class="description-text">TOTAL PROFIT</span>--%>
                                    <%--</div><!-- /.description-block -->--%>
                                <%--</div><!-- /.col -->--%>
                                <%--<div class="col-sm-3 col-xs-6">--%>
                                    <%--<div class="description-block">--%>
                                        <%--<span class="description-percentage text-red"><i class="fa fa-caret-down"></i> 18%</span>--%>
                                        <%--<h5 class="description-header">1200</h5>--%>
                                        <%--<span class="description-text">GOAL COMPLETIONS</span>--%>
                                    <%--</div><!-- /.description-block -->--%>
                                <%--</div>--%>
                            <%--</div><!-- /.row -->--%>
                        <%--</div><!-- /.box-footer -->--%>
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
            <div class="box box-success">
                <div class="box-header with-border">
                    <h3 class="box-title">取样分析</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                    </div>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-md-9 col-sm-8">
                            <div class="pad">
                                <!-- Map will be created here -->
                                <div id="shandong" style="height: 400px;width: 1000px;">
                                </div>
                            </div>
                        </div><!-- /.col -->
                        <%--<div class="col-md-3 col-sm-4">--%>
                            <%--<div class="pad box-pane-right bg-green" style="min-height: 280px">--%>
                                <%--<div class="description-block margin-bottom">--%>
                                    <%--<div class="sparkbar pad" data-color="#fff">--%>
                                        <%--<canvas width="34" height="30"--%>
                                                <%--style="display: inline-block; width: 34px; height: 30px; vertical-align: top;"></canvas>--%>
                                    <%--</div>--%>
                                    <%--<h5 class="description-header">8390</h5>--%>
                                    <%--<span class="description-text">Visits</span>--%>
                                <%--</div><!-- /.description-block -->--%>
                                <%--<div class="description-block margin-bottom">--%>
                                    <%--<div class="sparkbar pad" data-color="#fff">--%>
                                        <%--<canvas width="34" height="30"--%>
                                                <%--style="display: inline-block; width: 34px; height: 30px; vertical-align: top;"></canvas>--%>
                                    <%--</div>--%>
                                    <%--<h5 class="description-header">30%</h5>--%>
                                    <%--<span class="description-text">Referrals</span>--%>
                                <%--</div><!-- /.description-block -->--%>
                                <%--<div class="description-block">--%>
                                    <%--<div class="sparkbar pad" data-color="#fff">--%>
                                        <%--<canvas width="34" height="30"--%>
                                                <%--style="display: inline-block; width: 34px; height: 30px; vertical-align: top;"></canvas>--%>
                                    <%--</div>--%>
                                    <%--<h5 class="description-header">70%</h5>--%>
                                    <%--<span class="description-text">Organic</span>--%>
                                <%--</div><!-- /.description-block -->--%>
                            <%--</div>--%>
                        <%--</div><!-- /.col -->--%>
                    </div><!-- /.row -->
                </div><!-- /.box-body -->
            </div>

            <div class="box box-success">
                <div class="box-header with-border">
                    <h3 class="box-title">对比分析</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                    </div>

                </div><!-- /.box-header -->
                <div class="box-body">
                    <%--<form action="<%=basePath%>sys/data/duibifenxi" id="form1" name="form1" method="post">--%>
                    <form id="condition-form">

                        <h3 class="box-title">选择调查期</h3>
                        <div class="form-group col-md-6">
                            <label>调查期一</label>
                            <select name="periodOne" class="form-control select2"
                                    style="width: 100%;"
                                    tabindex="-1">
                                <c:forEach items="${surveyTimeList}" var="v">
                                    <option value="${v.id}">${v.start_time} - ${v.end_time}</option>
                                </c:forEach>
                            </select>

                        </div><!-- /.form-group -->

                        <div class="form-group col-md-6">
                            <label>调查期二</label>
                            <select name="periodTwo" class="form-control select2 "
                                    style="width: 100%;"
                                    tabindex="-1">
                                <c:forEach items="${surveyTimeList}" var="v">
                                    <option value="${v.id}">${v.start_time} - ${v.end_time}</option>
                                </c:forEach>
                            </select>

                        </div><!-- /.form-group -->
                        <div class="form-group col-md-6">
                            <h3 align="left" class="box-title">选择样本条件 </h3>
                            <label>审核</label>
                            <select name="sampleRequirements" class="form-control "
                                    style="width: 100%;"
                                    tabindex="-1">
                                <option value="1" selected="通过审核">通过审核</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <h3 align="left" class="box-title">选择分析方式</h3>
                            <label>地区</label>
                            <select name="areaRequirements" class="form-control select2  "
                                    style="width: 100%;"
                                    tabindex="-1">
                                <option value="1" selected="山东省">山东省</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <button type="button" class="btn btn-primary" id="condition-btn">提交</button>
                        </div>
                    </form>

                </div><!-- /.col -->
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-md-9 col-sm-8">
                            <div class="pad">
                                <!-- Map will be created here -->
                                <div id="duibifenxi" style="height: 400px;width: 950px;">
                                </div>
                            </div>
                        </div><!-- /.col -->
                        <div class="col-md-3 col-sm-4">
                            <%--<div class="pad box-pane-right bg-green" style="min-height: 280px">--%>

                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.box-body -->
            </div>
            <div class="box box-success">
                <div class="box-header with-border">
                    <h3 class="box-title">趋势分析</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                    </div>

                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-md-9 col-sm-8">
                            <div class="pad">
                                <!-- Map will be created here -->
                                <div id="trend" style="height: 400px;width: 1000px;">
                                </div>
                            </div>
                        </div><!-- /.col -->
                        <div class="col-md-3 col-sm-4">
                            <%--<div class="pad box-pane-right bg-green" style="min-height: 280px">--%>

                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.box-body -->
            </div>
            <!-- Main row -->
        </section>
    </div><!-- /.row -->

</div>


<!-- /.主要内容结束 -->
<jsp:include page="../../footer.jsp" flush="true"></jsp:include>
<jsp:include page="../../js.jsp" flush="true"></jsp:include>

<!-- page script -->

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    var seriesDataJson = [];
    <c:forEach items="${peopleOfEverySurveyTime}" var="v">
    seriesDataJson.push(${v.cur_people});
    </c:forEach>
    var option = {
        title: {
//            text: '查看企业的汇总数据'
        },
        tooltip: {},
        legend: {
            data: ['统计：根据不同的调查期显示出企业的汇总数据']
        },
        xAxis: {
            data: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            axisTick: {
                interval: 0
            },
            axisLabel: {
                interval: 0
            }
        },
        yAxis: {},
        series: [{
            name: '统计：根据不同的调查期显示出企业的汇总数据',
            type: 'line',
            data: seriesDataJson
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('trend'));

    var seriesDataJson = [];
    <c:forEach items="${trend}" var="v">
    seriesDataJson.push(${v});
    </c:forEach>
    var option = {
        title: {
//            text: '查看企业的汇总数据'
        },
        tooltip: {},
        legend: {
            data: ['统计：岗位变化数量占比']
        },
        xAxis: {
            data: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            axisTick: {
                interval: 0
            },
            axisLabel: {
                interval: 0
            }
        },
        yAxis: {},
        series: [{
            name: '统计：岗位变化数量占比',
            type: 'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            },
            data: seriesDataJson
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

<script>

    var chart = echarts.init(document.getElementById('shandong'));

    option = {
        title: {
            text: '山东各市企业数量汇总',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params, ticket, callback) {
                var res = '所选地区:' + params.name + '<br/>';
                res += '数量:' + params.value + '<br/>';
                res += '比例：' + params.data.proportion + "%" + '<br/>';
                setTimeout(function () {
                    callback(ticket, res);
                }, 120);
                return 'loading';
            }
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        visualMap: {
            min: 0,
            max: 8,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [{
            name: '山东各市企业数量汇总',
            type: 'map',
            mapType: '山东',
            roam: false,
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            data: [
                <c:forEach var="v" items="${statisticsOfCompanyList}">
                {name: "${v.city}", value: ${v.number}, proportion:${v.proportion}},
                </c:forEach>
            ]
        }]
    };
    chart.setOption(option)
</script>
<script type="text/javascript">
    jQuery('document').ready(function ($) {

        //添加通知
        $("#condition-btn").on('click', function () {

            condition();
        });

        /**
         * 添加通知
         * @returns {boolean}
         */
        function condition() {

            //标题
            var periodOne = $("#condition-form select[name = periodOne]").val();
            var periodTwo = $("#condition-form select[name = periodTwo]").val();
            var sampleRequirements = $("#condition-form select[name = sampleRequirements]").val();
            var areaRequirements = $("#condition-form select[name = areaRequirements]").val();

            //正文
            //数据校验
            if (isEmpty(periodOne)) {
//                alert("标题不能为空");
                return false;
            } else if (isEmpty(periodTwo)) {
//                alert("正文不能为空");
                return false;
            } else {

                var postUrl = "<%=basePath%>sys/data/duibifenxi";

                var chart = echarts.init(document.getElementById('duibifenxi'));
                option = {
                    title: {
//            text: '对比两个调查期的企业岗位变动情况',
                        subtext: '纯属虚构',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['调查期一', '调查期二']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        data: ['企业总数', '建档期总岗位数', '调查期总岗位数', '岗位变化总数', '岗位减少总数', '岗位变化数量占比'],
                        axisTick: {
                            interval: 0
                        },
                        axisLabel: {
                            interval: 0
                        }
                    },


                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '调查期一',
                            type: 'bar',
                            data: (function () {
                                var arr = [];
                                var period = periodOne;
                                $.ajax({
                                    url: postUrl,
                                    async: false, //同步执行
                                    data: {period},
                                    method: "post",
                                    dataType: "json",
                                    success: function (result) {

                                        if (result) {
//                                            alert(result[0]);
                                            for (var i = 0; i < result.length; i++) {
                                                console.log(result[i]);
                                                arr.push(result[i]);

//                                                arr.push(result.get(i));
                                            }
                                        }
                                    },
                                    error: function (errorMsg) {
                                        alert("图表请求数据失败");
                                        myChart.hideLoading();
                                    }

                                })
                                return arr;
                            })()
                        },
                        {
                            name: '调查期二',
                            type: 'bar',
                            stack: '调查期二',
                            data: (function () {
                                var arr = [];
                                var period = periodTwo;
                                $.ajax({
                                    url: postUrl,
                                    async: false, //同步执行
                                    data: {period},
                                    method: "post",
                                    dataType: "json",
                                    success: function (result) {

                                        if (result) {
                                            for (var i = 0; i < result.length; i++) {
                                                console.log(result[i]);
                                                arr.push(result[i]);
                                            }
                                        }
                                    },
                                    error: function (errorMsg) {
                                        alert("图表请求数据失败");
                                        myChart.hideLoading();
                                    }

                                })
                                return arr;
                            })()


                        },
                    ]
                };

                chart.setOption(option)

            }

        }

        function isEmpty(str) {
            if (!str || $.trim(str).length <= 0)
                return true;
            return false;
        }
    });
</script>
</body>
</html>
