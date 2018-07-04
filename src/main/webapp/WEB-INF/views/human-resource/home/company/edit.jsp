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
                企业信息修改
                <%--<small>table</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>企业用户</li>
                <li class="active">信息修改</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
            <!-- left column -->
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
                                <input class="form-control" value="山东省" disabled="" type="text" id="province_id" name="province_id">
                            </div>
                            <%--<form>--%>
                            <div class="form-group">
                                <label>城市</label>
                                <select  onchange="cityChange()" aria-hidden="true" tabindex="-1" class="form-control" style="width: 100%;" name="city_id" id="city_id">
                                    <option selected="selected" value="${cityArea.id}">${cityArea.name}</option>
                                    <c:forEach items="${listCity}" var="v">
                                        <option value="${v.id}">${v.name}</option>
                                    </c:forEach>
                                </select>

                            </div><!-- /.form-group -->
                            <div class="form-group">
                                <label>区域</label>
                                <select aria-hidden="true" tabindex="-1" class="form-control" style="width: 100%;" id="area_id" name="area_id">
                                    <option selected="selected" value="${areaArea.id}">${areaArea.name}</option>
                                    <c:forEach items="${listArea}" var="v">
                                        <option value="${v.id}">${v.name}</option>
                                    </c:forEach>
                                </select>
                            </div><!-- /.form-group -->
                            <div class="form-group">
                                <label>企业名称</label>
                                <input class="form-control" value="${company.name}" placeholder="中文、英文" type="text" name="name">
                            </div>
                            <div class="form-group">
                                <label>主要经营业务</label>
                                <input class="form-control" value="${company.business}" placeholder="按实际情况填写企业主要经营的业务" type="text" name="business">
                            </div>
                            <div class="form-group">
                                <label>企业性质</label>
                                <select class="form-control" style="width: 100%;" name="industry_type">
                                    <option selected="selected" value="${industryType.id}">${industryType.name}</option>
                                    <c:forEach items="${listIndustryType}" var="v">
                                        <option value="${v.id}">${v.name}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="form-group">
                                <label>所属行业</label>
                                <select class="form-control" style="width: 100%;" name="industry_involved">
                                    <option selected="selected" value="${industryType1.id}">${industryType1.name}</option>
                                    <c:forEach items="${listIndustryInvolve}" var="v">
                                        <option value="${v.id}">${v.name}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="box-footer">
                                <button type="button" onclick="onSubmit()" class="btn btn-primary">修改</button>
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
                                <input class="form-control" value="${company.code}" placeholder="只可输入字母、数字，不超过9位" type="text" id="code" name="code">
                            </div>

                            <div class="form-group">
                                <label>邮政编码</label>
                                <input class="form-control" value="${company.zipcode}" placeholder="只可填写6位数字" type="text" name="zipcode">
                            </div>
                            <div class="form-group">
                                <label>联系人</label>
                                <input class="form-control" value="${company.contact}" placeholder="中文、英文" type="text" name="contact">
                            </div>

                            <div class="form-group">
                                <label>联系电话</label>
                                <input class="form-control" value="${company.phone}" placeholder="格式必须符合（区号）+电话号码或者为手机号码" type="text" name="phone">
                            </div>
                            <div class="form-group">
                                <label>传真</label>
                                <input class="form-control" value="${company.fax}" placeholder="格式必须符合（区号）+电话" type="text" name="fax">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">EMAIL</label>
                                <input class="form-control" value="${company.email}" name="email" placeholder="格式必须符合xxx@xxx.xxx" type="email">
                            </div>
                            <div class="form-group">
                                <label>联系地址</label>
                                <input class="form-control" value="${company.address}" placeholder="请输入联系地址"  type="text" name="address">
                            </div><!-- /.form-group -->

                        </div><!-- /.box-body -->
                    </div><!-- /.content -->
                </div><!--/.col (right) -->
            </form>

            </div><!-- /.content -->
            </div><!-- /.content-wrapper -->
        </section>
    </div>
        <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
        <script>
            $(function () {
                $("#example1").DataTable();
                $('#example2').DataTable({
                    "paging": true,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": true,
                    "info": true,
                    "autoWidth": false
                });
            });
            function  onSubmit()
            {
                var postUrl = "<%=basePath%>home/company/edit";
                $.ajax({
                    url: postUrl,
                    data: $("#add-form").serialize(),
                    async:false,
                    type: 'POST',
                    dataType: "json",
                    success: function (data) {
                        if(data.success=="success")
                        {
                            toastr.success("修改成功");
                            window.location.href = "<%=basePath%>";
                        }
                        else if(data.success=="noInfo")
                        {
                            toastr.error("请填写信息！");
                            window.location.href = "<%=basePath%>home/company/add";
                        }
                        else if(data.success=="error0")
                        {
                            toastr.error("企业名称格式有误！");
                        }
                        else if(data.success=="error01")
                        {
                            toastr.error("企业名称输入过长！");
                        }
                        else if(data.success=="error02")
                        {
                            toastr.error("企业名称不能为空！");
                        }
                        else if(data.success=="error1")
                        {
                            toastr.error("邮政编码格式有误！");
                        }
                        else if(data.success=="error2")
                        {
                            toastr.error("联系电话格式有误！");
                        }
                        else if(data.success=="error3")
                        {
                            toastr.error("传真格式有误！");
                        }
                        else if(data.success=="error4")
                        {
                            toastr.error("EMAIL格式有误！");
                        }
                        else if(data.success=="error5")
                        {
                            toastr.error("主要经营业务输入过长！");
                        }
                        else if(data.success=="error51")
                        {
                            toastr.error("主要经营业务不能为空！");
                        }
                        else if(data.success=="error6")
                        {
                            toastr.error("组织机构代码格式有误！");
                        }
                        else if(data.success=="error61")
                        {
                            toastr.error("组织机构代码不能为空！");
                        }
                        else if(data.success=="error7")
                        {
                            toastr.error("联系人格式格式有误！！");
                        }
                        else if(data.success=="error71")
                        {
                            toastr.error("联系人输入过长！");
                        }
                        else if(data.success=="error72")
                        {
                            toastr.error("联系人不能为空！");
                        }
                        else if(data.success=="error8")
                        {
                            toastr.error("联系地址输入过长！");
                        }
                        else if(data.success=="error81")
                        {
                            toastr.error("联系地址不能为空！");
                        }
                        else
                        {
                            toastr.error("修改失败");
                        }
                    },
                    error: function(){
                        toastr.error("服务器无法连接！");
                    }
                });

            }


            function cityChange()
            {
                var areaBox = document.getElementById("area_id");
                var obj=document.getElementById("city_id");
                var index=obj.selectedIndex; //序号，取当前选中选项的序号
                var str = obj.options[index].value;
                var str1="<%=basePath%>home/company/add/"+str;

                $.ajax({
                    type: 'get',
                    url: str1 ,
                    async:false,
                    data: {} ,
                    dataType: 'json',

                    success:function(data) {
                        areaBox.options.length=0;
                        for (var i = 0; i < data.length; i++)
                        {
                            areaBox.add(new Option(data[i].name,data[i].id));
                        }
                    },
                    error:function(){
                        toastr.error("服务器无法连接！");}
                });

            }

        </script>
</div>
</body>
</html>