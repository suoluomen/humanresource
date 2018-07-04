<%--
  Created by IntelliJ IDEA.
  User: syc
  Date: 2016/3/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.Auth" %>
<%@ page import="com.shandong.human.resource.common.AuthTree" %>
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
        <section class="content-header">
            <h1>修改角色权限</h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>系统管理</li>
                <li>角色管理</li>
                <li class="active">角色权限修改</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <form id="submit-form" role="form">
                    <%--防止什么都不选择的时候无法参数绑定--%>
                    <input type="hidden" name="authIds" value="0">
                    <input type="hidden" name="roleId" value="${roleId}">
                    <%--防止什么都不选择的时候无法参数绑定--%>
                    <c:forEach items="${existAuth}" var="v">
                        <c:choose>
                            <c:when test="${v.level eq 1}">
                                <div class="col-md-12">
                                    <div class="box box-primary">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">
                                                <input type="checkbox" class="auth-pid" name="authIds" value="${v.id}"
                                                       <c:if test="${v.hasAuth}">checked</c:if>
                                                > ${v.name}
                                            </h3>
                                        </div>
                                        <div class="box-body">
                                            <div class="form-group">
                                                <div class="row">
                                                    <c:choose>
                                                        <c:when test="${v.childList != null}">
                                                            <c:forEach items="${v.childList}" var="child">
                                                                <div class="col-md-2">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" class="child-${v.id}"
                                                                                   id="child-auth-${child.id}"
                                                                                   name="authIds" value="${child.id}"
                                                                                   <c:if test="${child.hasAuth}">checked</c:if>
                                                                            >${child.name}
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <div class="col-md-12">
                        <input id="submit-btn" type="button" class="btn btn-primary" value="提交">
                    </div>
                </form>
            </div>
        </section>
    </div>


    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 复选框的一系列操作
            // 选择父框
            $(".auth-pid").on('click', function () {
                var pidObj = $(this);
                var pid = $(this).val();
                console.log("pid = " + pid);
                var check = $(this).attr('checked');
                console.log(check);
                var toCheck = false;
                if (typeof(check) == "undefined") {
                    console.log("toChcek");
                    toCheck = true;
                    pidObj.prop("checked", true);
                    pidObj.attr('checked', true);
                } else {
                    console.log("noCheck");
                    toCheck = false;
                    pidObj.removeAttr("checked");
                }
                // 子权限
                var childAuth = $(".child-" + pid);
                childAuth.each(function (index, value) {
                    var iCheck = $(this).attr('checked');
                    console.log(iCheck);
                    if (toCheck || typeof(iCheck) == "undefined") {
                        console.log('toCheck2');
                        $(this).prop("checked", true);
                        $(this).attr('checked', true);
                    } else {
                        $(this).removeAttr("checked");
                    }

//                    console.log(index);//循环的下标值，从0开始
//                    console.log(this.value);
                });
            });

            $("#submit-btn").on('click', function () {
                // ajax 方式提交数据到某个url
                var postUrl = "<%=basePath%>sys/authRole/edit";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#submit-form").serialize(),
                    // 提交的数据,此处将整个表单的字段全部提交
                    // 也可以单独提交某个字段
//                data: {
//                    "username": username,
//                    "password": password
//                },
                    method: "post",
                    dataType: "json",
                    success: function (data) {

                        if (data.status == 'SUCCESS') {
                            toastr.success(data.info);
                            // 1000ms之后执行的操作
                            setTimeout(function () {
                                // 刷新页面
                                //location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                window.location.href = "<%=basePath%>sys/role";
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
