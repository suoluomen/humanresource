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
    <div class="content-wrapper" style="min-height: 946px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                通知管理
                <small>编辑通知</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li><a href="#">通知管理</a></li>
                <li class="active">编辑通知</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">编辑通知</h3>
                        </div><!-- /.box-header -->
                        <!-- form start -->
                        <form role="form" id="edit-news-form" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <input type="hidden" name="id" value="${newToEdit.id}" class="form-control"
                                           placeholder="">
                                    <input type="hidden" name="userId" id="userId" value="${newToEdit.userId}"
                                           class="form-control" placeholder="${newToEdit.userId}">
                                    <input type="hidden" name="createTime" id="createTime"
                                           value="${newToEdit.createTime}"
                                           class="form-control" placeholder="${newToEdit.userId}">
                                </div>
                                <div class="form-group">
                                    <label for="title">通知标题</label>
                                    <input type="text" name="title" id="title" value="${newToEdit.title}"
                                           class="form-control" required placeholder="${newToEdit.title}">
                                </div>
                                <div class="form-group">
                                    <label for="content">通知内容</label>
                                    <textarea class="form-control" id="content" name="content" rows="10" required
                                              placeholder="">${newToEdit.content}</textarea>
                                </div>
                            </div><!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" id="submit-btn" class="btn btn-primary">修 改</button>
                            </div>
                        </form>
                    </div><!-- /.box -->

                </div><!--/.col (left) -->

            </div>   <!-- /.row -->
        </section><!-- /.content -->
    </div>

    <!-- /.主要内容结束 -->
    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 编辑通知
            $("#submit-btn").on('click', function () {
                var postUrl = "<%= basePath%>sys/news/edit";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#edit-news-form").serialize(),
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
            });
        });
    </script>
</body>
</html>
