<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<a href="#" class="go-to-top hidden-print"><i class="fa fa-angle-up"></i></a>

<script type='text/javascript' src='<%=basePath%>static/human/js/echarts.js'></script>
<script type='text/javascript' src='<%=basePath%>static/human/js/shandong.js'></script>
<script type='text/javascript' src='<%=basePath%>static/human/js/vintage.js'></script>
<script type='text/javascript' src='<%=basePath%>static/human/js/china.js'></script>
<script type='text/javascript' src='<%=basePath%>static/human/js/xianggang.js'></script>

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
        "timeOut": "1200",
        "extendedTimeOut": "100",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };


</script>