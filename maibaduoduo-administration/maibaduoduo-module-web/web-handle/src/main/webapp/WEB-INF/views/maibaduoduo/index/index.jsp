<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <title>综合面板</title>
    <meta charset="utf-8">
    <%--<meta name="decorator" content="default"/>--%>
    <script src="/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script type="text/javascript" src="/static/echart/echarts.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body style="height: 100%; margin: 0">
<div id="calendar" style="height: 100%;">
    <%@include file="calendar.jsp"%>
</div>

</body>
</html>