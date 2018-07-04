<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%@ page contentType="application/msexcel" %>
<%
    //response.setHeader("Content-disposition","inline; filename=videos.xls");
    response.setHeader("Content-disposition","attachment; filename=CompanyRecord.xls");
    //以上这行设定传送到前端浏览器时的档名为test.xls
    //就是靠这一行，让前端浏览器以为接收到一个excel档
%>
<%@ page import="com.shandong.human.resource.domain.Company"%>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <title>spring jdbc test</title>
        <meta http-equiv="pragma" content="no-cache"></meta>
        <meta http-equiv="cache-control" content="no-cache"></meta>
        <meta http-equiv="expires" content="0"></meta>
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
        <meta http-equiv="description" content="This is my page"></meta>
        <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    </head>

    <body>
        <table border="1" width="100%">
            <tr>
                <td>ID</td>
                <td>省ID</td>
                <td>市ID</td>
                <td>区ID</td>
                <td>地址</td>
                <td>企业代码</td>
                <td>企业名称</td>
                <td>业务</td>
                <td>联系人</td>
                <td>邮编</td>
                <td>电话</td>
                <td>传真</td>
                <td>邮箱</td>
            </tr>
            <c:forEach items="${exResult}" var="v">
            <tr>
                <td>${v.id}</td>
                <td>${v.province_id}</td>
                <td>${v.city_id}</td>
                <td>${v.area_id}</td>
                <td>${v.address}</td>
                <td>${v.code}</td>
                <td>${v.name}</td>
                <td>${v.business}</td>
                <td>${v.contact}</td>
                <td>${v.zipcode}</td>
                <td>${v.phone}</td>
                <td>${v.fax}</td>
                <td>${v.email}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>