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
    <jsp:include page="../head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../header.jsp" flush="true"></jsp:include>

    <jsp:include page="../left.jsp" flush="true"></jsp:include>

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                市审核
                <small>审核</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>主页</a></li>
                <li><a href="#">市审核</a></li>
                <li class="active">审核</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">审核</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" id="check-form" class="form-horizontal">
                                <div class="form-group" style="display: none">
                                    <label class="control-label">ID</label>
                                    <input name="cdid" value="${check.id}">
                                </div>

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
                                            <select onchange="passChange()" class="form-control" style="width: 80%;" id="pass" name="pass">
                                                <option value="1">通过</option>
                                                <option value="-1">不通过</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" id="reason" style="display: none">
                                    <div class="col-xs-6 form-group">
                                        <div class="col-xs-3 ">
                                            <label class="control-label">不通过原因</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input name="reason" class="form-control" style="width: 80%;">
                                        </div>
                                    </div>
                                </div>

                                <%--<div class="form-group" id="reason" style="display: none">--%>
                                    <%--<label>不通过原因</label>--%>
                                    <%--<input name="reason" class="form-control" style="width: 80%;">--%>
                                    <%--</select>--%>
                                <%--</div>--%>

                                <div class="box-footer">
                                    <button type="button" onclick="onSubmit()" class="btn btn-primary">提交</button>
                                </div>
                            </form>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:include page="../footer.jsp" flush="true"></jsp:include>
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

        function passChange(){
            var passBox = document.getElementById("pass");
            var index = passBox.selectedIndex;
            var value = passBox.options[index].value;
            if(value==-1)
            {
                document.getElementById('reason').style.display = "";
            }
            else
            {
                document.getElementById('reason').style.display = "none";
            }
        }

        function  onSubmit()
        {
            //标题
//            var title = $("#addNews-form input[name = title]").val();
            //正文
//            var content = $("#addNews-form textarea[name = content]").val();

            //数据校验
//            if (isEmpty(title)) {
//                alert("标题不能为空");
//                return false;
//            } else if (isEmpty(content)) {
//                alert("正文不能为空");
//                return false;
//            } else {
            //var str=$("#add-form").serialize();
//            alert("提交成功");
            var postUrl = "<%=basePath%>sys/city/check";
            $.ajax({
                url: postUrl,
                data: $("#check-form").serialize(),
                async:false,
                type: 'POST',
                dataType: "json",
                success: function (data) {
                    if (data.status == 'SUCCESS') {
                        toastr.success(data.info);
                        // 3000ms之后执行的操作
                        setTimeout(function () {
                            // 刷新页面
                            // location.reload(true);
                            // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                            window.location.href = "<%=basePath%>sys/city/query";
                        }, 1000);
                    } else {
                        toastr.error(data.info);
                        return false;
                    }
                }
            });
        }
    </script>
</body>
</html>

