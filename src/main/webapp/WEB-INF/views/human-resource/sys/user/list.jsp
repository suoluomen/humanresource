<%--
  Created by IntelliJ IDEA.
  User: syc
  Date: 2016/3/19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.util.Pager" %>
<%@ page import="com.shandong.human.resource.domain.User" %>
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
    <jsp:include page="../../head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
    <jsp:include page="../../header.jsp" flush="true"></jsp:include>

    <jsp:include page="../../left.jsp" flush="true"></jsp:include>

    <div class="content-wrapper" style="min-height: 916px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small>系统用户</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li>系统管理</li>
                <li class="active">用户管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">新增用户</h3>
                        </div>
                        <div class="box-body">
                            <form class="form-horizontal" role="form" id="add-user-form">
                                <div class="row">
                                    <div class="col-md-11">
                                        <div class="col-lg-3 form-group">
                                            <lable class="col-sm-5 control-label" for="name">用户名</lable>
                                            <div class="col-sm-7">
                                                <input id="name" class="form-control" type="text" name="username"
                                                       placeholder="用户名">
                                            </div>
                                        </div>
                                        <div class="col-lg-3 form-group">
                                            <lable class="col-sm-4 control-label" for="pwd">密码</lable>
                                            <div class="col-sm-8">
                                                <input id="pwd" class="form-control" type="password" name="password"
                                                       placeholder="密码">
                                            </div>
                                        </div>
                                        <div class="col-lg-3 form-group">
                                            <lable class="col-sm-4 control-label" for="type">类型</lable>
                                            <div class="col-sm-8">
                                                <select id="type" name="type" class="form-control">
                                                    <option name="type" value="2">企业用户</option>
                                                    <option name="type" value="1">省用户</option>
                                                    <c:forEach items="${cityList}" var="v">
                                                        <option name="type" value="${v.id}">${v.name}用户</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 form-group">
                                            <lable class="col-sm-4 control-label" for="role">角色</lable>
                                            <div class="col-sm-8">
                                                <select id="role" name="role" class="form-control">
                                                    <c:forEach items="${roleList}" var="v">
                                                        <option name="type" value="${v.id}">${v.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <input type="button" class="btn btn-adn" value="添加" id="submit-btn">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>类型</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${pager.data}" var="v">
                                <tr>
                                    <td>${v.id}</td>
                                    <td>${v.username}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${v.type eq '1'}">
                                                <span class="label label-success">省用户</span>
                                            </c:when>
                                            <c:when test="${v.type eq '2'}">
                                                <span class="label label-info">企业用户</span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="typeflag" value="true"/>
                                                <c:forEach items="${cityList}" var="r" varStatus="loopstatus">
                                                    <c:if test="${typeflag eq 'true'}">
                                                    </c:if>
                                                    <c:if test="${v.type eq r.id}">
                                                        <span class="label label-warning">${r.name}用户</span>
                                                        <c:set var="typeflag" value="false"/>
                                                    </c:if>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="<%=basePath%>sys/userRole/edit/${v.id}">授权</a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="javascript:void(0);" class="del-user-btn" user_id="${v.id}">删除</a>
                                    </td>
                                    </c:forEach>
                                </tr>
                                </tbody>
                            </table>
                        </div><!-- /.box-body -->
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h3>共${pager.count}条记录&nbsp;${pager.currentPage}/${pager.totalPage}</h3>
                                </div>
                                <div class="col-sm-9">
                                    <ul class="pagination pull-right">
                                        <c:if test="${pager.currentPage - 2 gt 2}">
                                            <li class="paginate_button previous">
                                                <a href="<%=basePath%>sys/user?pageNum=${pager.currentPage - 1}">上一页</a>
                                            </li>
                                            <li class="paginate_button "><a href="<%=basePath%>sys/user?pageNum=1">1</a>
                                            </li>
                                            <li class="paginate_button"><a>...</a></li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage - 2}">${pager.currentPage - 2}</a>
                                            </li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage - 1}">${pager.currentPage - 1}</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${pager.currentPage == 4}">
                                            <li class="paginate_button "><a href="<%=basePath%>sys/user?pageNum=1">1</a>
                                            </li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage - 2}">${pager.currentPage - 2}</a>
                                            </li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage - 1}">${pager.currentPage - 1}</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${pager.currentPage - 2 lt 2}">
                                            <c:if test="${pager.currentPage == 3}">
                                                <li class="paginate_button "><a
                                                        href="<%=basePath%>sys/user?pageNum=1">1</a></li>
                                                <li class="paginate_button "><a
                                                        href="<%=basePath%>sys/user?pageNum=2">2</a></li>
                                            </c:if>
                                            <c:if test="${pager.currentPage == 2}">
                                                <li class="paginate_button "><a
                                                        href="<%=basePath%>sys/user?pageNum=1">1</a></li>
                                            </c:if>
                                        </c:if>
                                        <%--当前页码--%>
                                        <li class="paginate_button active">
                                            <a href="#">${pager.currentPage}</a>
                                        </li>
                                        <%--当前页码--%>

                                        <c:if test="${pager.currentPage + 3 lt pager.totalPage}">
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 1}">${pager.currentPage + 1}</a>
                                            </li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 2}">${pager.currentPage + 2}</a>
                                            </li>
                                            <li class="paginate_button"><a>...</a></li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.totalPage}">${pager.totalPage}</a>
                                            </li>
                                            <li class="paginate_button next"><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 1}">下一页</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${pager.currentPage + 3 == pager.totalPage}">
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 1}">${pager.currentPage + 1}</a>
                                            </li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 2}">${pager.currentPage + 2}</a>
                                            </li>
                                            <li class="paginate_button "><a
                                                    href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 3}">${pager.currentPage + 3}</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${pager.currentPage + 3 gt pager.totalPage}">
                                            <c:if test="${pager.currentPage == pager.totalPage -1}">
                                                <li class="paginate_button "><a
                                                        href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 1}">${pager.currentPage + 1}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${pager.currentPage == pager.totalPage -2}">
                                                <li class="paginate_button "><a
                                                        href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 1}">${pager.currentPage + 1}</a>
                                                </li>
                                                <li class="paginate_button "><a
                                                        href="<%=basePath%>sys/user?pageNum=${pager.currentPage + 2}">${pager.currentPage + 2}</a>
                                                </li>
                                            </c:if>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.box -->
                </div>
            </div>

        </section><!-- /.content -->
    </div>

    <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    <script>
        $(function () {
            // 新增用户
            $("#submit-btn").on('click', function () {
                // ajax 方式提交数据到某个url
                var postUrl = "<%=basePath%>sys/user/add";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: $("#add-user-form").serialize(),
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
                                location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                //window.location.href = "<%=basePath%>sys/user";
                            }, 1000);
                        } else {
                            toastr.error(data.info);
                            return false;
                        }
                    }
                });
            });

            // 删除用户
            $(".del-user-btn").on('click', function () {
                var user_id = $(this).attr('user_id');
                var postUrl = "<%= basePath%>sys/user/delete";
                $.ajax({
                    url: postUrl,//提交的地址
                    data: {
                        "id": user_id
                    },
                    method: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.status == 'SUCCESS') {
                            toastr.success(data.info);
                            // 3000ms之后执行的操作
                            setTimeout(function () {
                                // 刷新页面
                                location.reload(true);
                                // 跳转到某个界面,如果想跳转的页面与当前页面url一致,则不需要跳转
                                //window.location.href = "<%=basePath%>sys/user";
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
