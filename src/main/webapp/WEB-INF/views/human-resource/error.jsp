<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="head.jsp" flush="true"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:include page="header.jsp" flush="true"></jsp:include>

  <jsp:include page="left.jsp" flush="true"></jsp:include>

  <!-- 主要内容开始 -->
    <div class="content-wrapper" style="min-height: 1096px;">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>
          未找到页面
        </h1>
      </section>

      <!-- Main content -->
      <section class="content">
        <div class="error-page">
          <div class="row">
            <h2 class="headline text-yellow" style="font-size: 300px;"> Err！</h2>
          </div>
          <div class="row">
            <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i> 您的请求被拒绝</h3>
              <p>
                ${requestScope.error},您可以
                <a href="<%=basePath%>">返回首页</a>
              </p>
            </div><!-- /.error-content -->
          </div>
        </div><!-- /.error-page -->
      </section><!-- /.content -->
    </div>
    <!-- /.主要内容结束 -->

  <jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>
