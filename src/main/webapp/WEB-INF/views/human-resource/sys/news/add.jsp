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
    <!-- Content Header (Page header) -->

    <div class="content-wrapper">
        <section class="content-header">
            <h1>通知管理</h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>通知管理</li>
                <li class="active">添加通知</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">发布通知</h3>
                        </div><!-- /.box-header -->
                        <!-- form start -->
                        <form role="form" id="addNews-form">
                            <div class="box-body">
                                <!-- text input -->
                                <div class="form-group">
                                    <input type="text" name="title" class="form-control" required placeholder="通知标题">
                                </div>

                                <!-- textarea -->
                                <div class="form-group">
                                    <textarea class="form-control" name="content" rows="10" required
                                              placeholder="通知内容"></textarea>
                                </div>
                            </div><!-- /.box-body -->

                            <div class="box-footer">
                                <button type="button" class="btn btn-primary" id="add-btn">添加</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 新增通知
            $("#add-btn").on('click', function () {
                //标题
                var title = $("#addNews-form input[name = title]").val();
                //正文
                var content = $("#addNews-form textarea[name = content]").val();

                //数据校验
                if (isEmpty(title)) {
                    toastr.error("标题不能为空");
                    return false;
                } else if (isEmpty(content)) {
                    toastr.error("正文不能为空");
                    return false;
                } else {
                    var postUrl = "<%=basePath%>sys/news/add";
                    $.ajax({
                        url: postUrl,
                        data: $("#addNews-form").serialize(),
                        method: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data.status == 'SUCCESS') {
                                toastr.success(data.info);
                                // 3000ms之后执行的操作
                                setTimeout(function () {
                                    // 刷新页面
                                    //location.reload(true);
                                    // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                    window.location.href = "<%=basePath%>sys/news";
                                }, 1000);
                            } else {
                                toastr.error(data.info);
                                return false;
                            }
                        }

                    });
                }
            });
        });
    </script>
</div>
</div>
</body>
</html>
