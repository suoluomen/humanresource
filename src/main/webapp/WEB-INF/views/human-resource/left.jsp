<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.shandong.human.resource.domain.User" %>
<%@ page import="com.shandong.human.resource.domain.Auth" %>
<%@ page import="com.shandong.human.resource.util.PermissionUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<%=basePath%>static/human/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">

                <% /*用户信息*/
                    User localUser = (User) request.getSession().getAttribute("localUser");
                    /*是否已登录*/
                    boolean loged = (localUser != null);%>
                <p><%= loged == true ? localUser.getUsername() : "未登录"%>
                </p>
                <a href="#"><i class="fa fa-circle  <%= loged ==true?"text-success":"text-danger"%>"></i>
                    <%= loged == true ? "online" : "offline"%>
                </a>
            </div>
        </div>
        <!-- search form -->
        <%--<form action="#" method="get" class="sidebar-form">--%>
        <%--<div class="input-group">--%>
        <%--<input type="text" name="q" class="form-control" placeholder="Search...">--%>
        <%--<span class="input-group-btn">--%>
        <%--<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>--%>
        <%--</button>--%>
        <%--</span>--%>
        <%--</div>--%>
        <%--</form>--%>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">控制台</li>

            <% /*用户权限*/
                Set<Auth> auths = (Set<Auth>) request.getSession().getAttribute("auth"); %>

            <!-- 企业用户备案 -->
            <% boolean home_company_add = PermissionUtil.find("/home/company/add", auths);
                boolean home_company_edit = PermissionUtil.find("/home/company/edit", auths);
                if (home_company_add || home_company_edit) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>企业备案</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <% if (home_company_add) { %>
                    <li><a href="<%=basePath%>home/company/add"><i class="fa fa-circle-o"></i> 信息录入</a></li>
                    <%}%>
                    <% if (home_company_edit) {%>
                    <li><a href="<%=basePath%>home/company/edit"><i class="fa fa-circle-o"></i> 信息修改</a></li>
                    <%}%>
                </ul>
            </li>
            <% } %>

            <!-- 企业用户数据上报 -->
            <% boolean home_data_add = PermissionUtil.find("/home/data/add", auths);
                boolean home_data_query = PermissionUtil.find("/home/data/query", auths);
                if (home_data_add || home_data_query) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>数据上报</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <% if (home_data_add) {%>
                    <li><a href="<%=basePath%>home/data/add"><i class="fa fa-circle-o"></i> 数据填报</a></li>
                    <%}%>
                    <% if (home_data_query) {%>
                    <li><a href="<%=basePath%>home/data/query"><i class="fa fa-circle-o"></i> 数据查询</a></li>
                    <%}%>
                </ul>
            </li>
            <%}%>

            <!-- 市用户功能-->
            <% boolean sys_city_query = PermissionUtil.find("/sys/city/query", auths);
                if (sys_city_query) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>市用户功能</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=basePath%>sys/city/query"><i class="fa fa-circle-o"></i>待审核列表</a></li>
                </ul>
            </li>
            <%}%>

            <!-- 企业备案管理 -->
            <% boolean sys_record_query = PermissionUtil.find("/sys/record/query", auths);
                boolean sys_data_list = PermissionUtil.find("/sys/data/list", auths);
                if (sys_record_query || sys_data_list) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>企业备案管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <% if (sys_record_query) { %>
                    <li><a href="<%=basePath%>sys/record/query"><i class="fa fa-circle-o"></i> 备案列表</a></li>
                    <%}%>
                    <% if (sys_data_list) { %>
                    <li><a href="<%=basePath%>sys/data/list"><i class="fa fa-circle-o"></i> 数据列表</a></li>
                    <%}%>
                </ul>
            </li>
            <%}%>

            <%--<% boolean sys_data_list = PermissionUtil.find("/sys/data/list", auths);--%>
            <%--if (sys_data_list) {%>--%>
            <%--<li>--%>
            <%--<a href="<%=basePath%>sys/data/list">--%>
            <%--<i class="fa fa-th"></i> <span>报表管理</span>--%>
            <%--<small class="label pull-right bg-green">new</small>--%>
            <%--</a>--%>
            <%--</li>--%>
            <%--<%}%>--%>

            <!-- 报表管理 -->
            <%
                boolean sys_data_display = PermissionUtil.find("/sys/data/display", auths);
                if (sys_data_display) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>报表管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">

                    <% if (sys_data_display) { %>
                    <li><a href="<%=basePath%>sys/data/display"><i class="fa fa-circle-o"></i> 图表分析</a></li>
                    <%}%>
                </ul>
            </li>
            <%}%>

            <!-- 报表上报-->
            <% boolean sys_report_query = PermissionUtil.find("/sys/report/query", auths);
                if (sys_report_query) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>报表上报</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=basePath%>sys/report/query"><i class="fa fa-circle-o"></i>报表查询</a></li>
                </ul>
            </li>
            <%}%>

            <!-- 通知管理 -->
            <% boolean sys_news_add = PermissionUtil.find("/sys/news/add", auths);
                boolean sys_news = PermissionUtil.find("/sys/news", auths);
                if (sys_news || sys_news_add) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>通知管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <%if (sys_news_add) {%>
                    <li><a href="<%=basePath%>sys/news/add"><i class="fa fa-circle-o"></i> 发布通知</a></li>
                    <%}%>
                    <%if (sys_news) {%>
                    <li><a href="<%=basePath%>sys/news"><i class="fa fa-circle-o"></i> 所有通知</a></li>
                    <%}%>
                </ul>
            </li>
            <%}%>

            <!-- 系统管理 -->
            <% boolean sys_surveyTime = PermissionUtil.find("/sys/surveyTime", auths);
                boolean sys_auth_add = PermissionUtil.find("/sys/auth/add", auths);
                boolean sys_auth_delete = PermissionUtil.find("/sys/auth/delete", auths);
                boolean sys_role = PermissionUtil.find("/sys/role", auths);
                boolean sys_user = PermissionUtil.find("/sys/user", auths);
                boolean sys_user_search = PermissionUtil.find("/sys/user/search", auths);
                boolean sys_monitor = PermissionUtil.find("/sys/monitor", auths);
                if (sys_surveyTime || sys_auth_add || sys_auth_delete || sys_role || sys_user || sys_monitor) {%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>系统管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">

                    <% if (sys_surveyTime) {%>
                    <li><a href="<%=basePath%>sys/surveyTime"><i class="fa fa-circle-o"></i> 上报时限</a></li>
                    <%}%>

                    <% if (sys_auth_add || sys_auth_delete) {%>
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> 权限管理 <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">

                            <% if (sys_auth_add) {%>
                            <li><a href="<%=basePath%>sys/auth/add"><i class="fa fa-circle-o"></i> 新增权限</a></li>
                            <%}%>

                            <% if (sys_auth_delete) {%>
                            <li><a href="<%=basePath%>sys/auth/delete"><i class="fa fa-circle-o"></i> 删除权限</a></li>
                            <%}%>

                        </ul>
                    </li>
                    <%}%>

                    <% if (sys_role) {%>
                    <li><a href="<%=basePath%>sys/role"><i class="fa fa-circle-o"></i> 角色管理</a></li>
                    <%}%>
                    <% if (sys_user) {%>
                    <li><a href="<%=basePath%>sys/user"><i class="fa fa-circle-o"></i> 用户管理</a></li>
                    <%}%>
                    <% if (sys_user_search) {%>
                    <li><a href="<%=basePath%>sys/user/search"><i class="fa fa-circle-o"></i> 用户查询</a></li>
                    <%}%>
                    <% if (sys_monitor) {%>
                    <li><a href="<%=basePath%>sys/monitor"><i class="fa fa-circle-o"></i> 系统监控</a></li>
                    <%}%>
                </ul>
            </li>
            <%}%>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
