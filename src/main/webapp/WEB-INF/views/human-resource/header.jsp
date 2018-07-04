<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<header class="main-header">
    <!-- Logo -->
    <a href="<%=basePath%>" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini">山东</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>人力</b>资源</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

                <% /*用户信息*/
                    User localUser = (User) request.getSession().getAttribute("localUser");
                    /*是否已登录*/
                    boolean loged = (localUser != null);%>

                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="<%=basePath%>static/human/dist/img/user2-160x160.jpg" class="user-image"
                             alt="User Image">
                        <span class="hidden-xs"><%= ((loged == false) ? "未登录" : localUser.getUsername())%></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="<%=basePath%>static/human/dist/img/user2-160x160.jpg" class="img-circle"
                                 alt="User Image">
                            <p>
                                <%= ((loged == false) ? "未登录" : localUser.getUsername())%>
                                <small><%= ((loged == false) ? "未登录" : ("uid" + localUser.getId()))%>
                                </small>
                            </p>
                        </li>
                        <li class="user-footer">
                            <a href="<%= basePath%><%= loged==true?"logout":"login"%>" class="btn btn-default">
                                <%= loged==true?"注销":"登录"%></a>
                        </li>
                    </ul>
                </li>

                <!-- Control Sidebar Toggle Button -->
                <%--<li>--%>
                    <%--<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>--%>
                <%--</li>--%>
            </ul>
        </div>

    </nav>
</header>
