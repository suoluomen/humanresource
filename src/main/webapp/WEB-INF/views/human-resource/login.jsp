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
    <jsp:include page="head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition login-page">

<div class="login-box">
    <div class="login-logo" data-color="gray">
        <b>山东人力资源</b>&nbsp;&nbsp;采集系统
    </div><!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">欢迎来到山东资源管理系统</p>
        <form method="post" id="login-form">
            <div class="form-group has-feedback">
                <input name="username" id="username" class="form-control" placeholder="用户名" type="text">
            </div>
            <div class="form-group has-feedback">
                <input name="password" id="password" class="form-control" placeholder="密码" type="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-4">

                </div><!-- /.col -->
                <div class="col-xs-4">
                    <button type="button" class="btn btn-primary btn-block btn-flat" id="login-btn">登录</button>
                </div><!-- /.col -->
            </div>
        </form>
    </div><!-- /.login-box-body -->
</div><!-- /.login-box -->
<!-- jQuery 2.1.4 -->
<script src="<%=basePath%>static/human/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<%-- 消息提示小插件 --%>
<script type='text/javascript' src='<%=basePath%>static/human/js/plugins/toastr/toastr.min.js'></script>
<%-- js验证插件 --%>
<script type='text/javascript' src='<%=basePath%>static/human/js/validation.js'></script>
<script type="text/javascript">
    <%--Toastr配置--%>

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-top-center",
        "onclick": null,
        "showDuration": "50",
        "hideDuration": "100",
        "timeOut": "5000",
        "extendedTimeOut": "100",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    function isEmpty(str) {
        if (!str || $.trim(str).length <= 0)
            return true;
        return false;
    }

    $(function () {
        // 用户登陆按钮
        $("#login-btn").on('click', function () {
            var username = $("#username").val().trim();
            var password = $("#password").val().trim();
            if (isEmpty(username) || isEmpty(password)) {
                toastr.error("用户名密码不能为空");
                return false;
            }
            var postUrl = "<%= basePath%>login";
            $.ajax({
                url: postUrl,//提交的地址
                data: $("#login-form").serialize(),
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
                            window.location.href = "<%=basePath%>";
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
