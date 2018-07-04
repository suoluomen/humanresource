<%--
  Created by IntelliJ IDEA.
  User: syc
  Date: 2016/3/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.Auth" %>
<%@ page import="com.shandong.human.resource.common.AuthTree" %>
<%@ page import="com.shandong.human.resource.domain.AuthRole" %>
<%@ page import="com.shandong.human.resource.domain.Role" %>
<%@ page import="com.shandong.human.resource.domain.UserRole" %>
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
        <section class="content-header"><h1>修改权限</h1></section>

        <section class="content">
            <div class="row">
                <div class="col-lg-8">
                    <div class="box box-info">
                        <div class="box-body">
                            <form role="form" method="post" id="user-role-form">
                                <%--<% String user_id = (String)request.getAttribute("user_id"); %>--%>
                                <%--<input name="user_id" value="<%= user_id%>" type="hidden">--%>
                                <input name="user_id" value=${user_id} type="hidden">

                                <div class="form-group">
                                    <c:forEach items="${allRole}" var="v">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="roles" value="${v.id}"
                                                       <c:if test="${v.hasRole eq true}">checked="checked"</c:if>>
                                                    ${v.name}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="form-group">
                                    <input type="button" class="btn btn-primary" id="submit-btn" value="提交">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 用户授权
            $("#submit-btn").on('click', function () {
                // ajax 方式提交数据到某个url
                var postUrl = "<%=basePath%>sys/userRole/edit";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#user-role-form").serialize(),
                    method: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.status == 'SUCCESS') {
                            toastr.success(data.info);
                            // 3000ms之后执行的操作
                            setTimeout(function () {
                                // 刷新页面
                                // location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                window.location.href = "<%=basePath%>sys/user";
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
</div>
</body>
</html>
