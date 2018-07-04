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
                企业信息录入
                <%--<small>table</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>企业用户</li>
                <li class="active">信息录入</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <form role="form" id="add-form">

                            <div class="col-md-6">
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title"></h3>
                                    </div><!-- /.box-header -->
                                    <!-- form start -->
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label>省份</label>
                                            <input class="form-control" value="山东省" disabled="" type="text"
                                                   id="province_id"
                                                   name="province_id">
                                        </div>
                                        <%--<form>--%>
                                        <div class="form-group">
                                            <label>城市</label>
                                            <select onchange="cityChange()" class="form-control" style="width: 100%;"
                                                    name="city_id" id="city_id">
                                                <c:forEach items="${editResult}" var="v">
                                                    <option value="${v.id}">${v.name}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                        <div class="form-group">
                                            <label>区域</label>
                                            <select aria-hidden="true" tabindex="-1" class="form-control"
                                                    style="width: 100%;" id="area_id" name="area_id">
                                                <%--<option selected="selected"></option>--%>
                                                <c:forEach items="${editResultCity}" var="v">
                                                    <option value="${v.id}">${v.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>企业名称</label>
                                            <input class="form-control" placeholder="中文、英文" type="text" name="name">
                                        </div>
                                        <div class="form-group">
                                            <label>主要经营业务</label>
                                            <input class="form-control" placeholder="按实际情况填写企业主要经营的业务" type="text"
                                                   name="business">
                                        </div>
                                        <div class="form-group">
                                            <label>企业性质</label>
                                            <select class="form-control" style="width: 100%;" name="industry_type">
                                                <c:forEach items="${listIndustryType}" var="v">
                                                    <option value="${v.id}">${v.name}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                        <div class="form-group">
                                            <label>所属行业</label>
                                            <select class="form-control" style="width: 100%;" name="industry_involved">
                                                <c:forEach items="${listIndustryInvolve}" var="v">
                                                    <option value="${v.id}">${v.name}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                        <div class="box-footer">
                                            <button type="button" id="submit-btn" class="btn btn-primary">提交</button>
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
                                            <label>组织机构代码</label>
                                            <input class="form-control" placeholder="只可输入字母、数字，不超过9位" type="text"
                                                   id="code"
                                                   name="code">
                                        </div>

                                        <div class="form-group">
                                            <label>邮政编码</label>
                                            <input class="form-control" placeholder="只可填写6位数字" type="text"
                                                   name="zipcode">
                                        </div>
                                        <div class="form-group">
                                            <label>联系人</label>
                                            <input class="form-control" placeholder="中文、英文" type="text" name="contact">
                                        </div>
                                        <div class="form-group">
                                            <label>联系电话</label>
                                            <input class="form-control" placeholder="格式必须符合（区号）+电话号码或者为手机号码" type="text"
                                                   name="phone">
                                        </div>
                                        <div class="form-group">
                                            <label>传真</label>
                                            <input class="form-control" placeholder="格式必须符合（区号）+电话" type="text"
                                                   name="fax">
                                        </div>
                                        <div class="form-group">
                                            <label for="email">邮箱</label>
                                            <input class="form-control" id="email" name="email"
                                                   placeholder="格式必须符合xxx@xxx.xxx"
                                                   type="email">
                                        </div>
                                        <div class="form-group">
                                            <label>联系地址</label>
                                            <input class="form-control" placeholder="请输入联系地址" type="text"
                                                   name="address">
                                            <%--<%  ArrayList<Area> areaList=(ArrayList<Area>)request.getAttribute("editResultCity"); %>--%>
                                            <%--<select aria-hidden="true" tabindex="-1" class="form-control select2 select2-hidden-accessible" style="width: 100%;" name="address">--%>

                                            <%--<c:forEach items="${editResultCity}" var="v">--%>
                                            <%--<option value="${v.id}">${v.name}</option>--%>
                                            <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        </div><!-- /.form-group -->

                                    </div><!-- /.box-body -->
                                </div><!-- /.content -->
                            </div><!--/.col (right) -->
                        </form>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div><!-- /.content-wrapper -->
    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        /**
         * 下拉框选择城市的时候响应事件
         */
        function cityChange() {
            var areaBox = document.getElementById("area_id");
            var obj = document.getElementById("city_id");
            var index = obj.selectedIndex; //序号，取当前选中选项的序号
            var str = obj.options[index].value;
            var str1 = "<%=basePath%>home/company/add/" + str;

            $.ajax({
                type: 'get',
                url: str1,
                async: false,
                data: {},
                dataType: 'json',
                success: function (data) {
                    areaBox.options.length = 0;
                    for (var i = 0; i < data.length; i++) {
                        areaBox.add(new Option(data[i].name, data[i].id));
                    }
                },
                error: function () {
                    toastr.erroralert("服务器无法连接！");
                }
            });

        }

        $(function () {
            <c:if test="${info eq 'exit'}">
            toastr.error("已经提交完毕，请勿重复提交！");
            setTimeout(function () {
                window.location.href = "<%=basePath%>";
            }, 3500);
            </c:if>

            // 提交备案数据
            $("#submit-btn").on('click', function () {
                var postUrl = "<%=basePath%>home/company/add/submit";
                $.ajax({
                    url: postUrl,
                    data: $("#add-form").serialize(),
                    async: false,
                    type: 'POST',
                    dataType: "json",
                    success: function (data) {
                        if (data.status == "ERROR") {
                            if (data.status == false) {
                                toastr.error(data.info);
                                setTimeout(function () {
                                    window.location.href = "<%=basePath%>";
                                }, 1000);
                                return false;
                            } else {
                                toastr.error(data.object);
                                return false;
                            }
                        } else {
                            toastr.success(data.info);
                        }
                    },
                    error: function () {
                        toastr.error("服务器无法连接！");
                    }
                });
            });
        });
    </script>
</div>
</body>
</html>