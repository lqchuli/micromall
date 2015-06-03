<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/29
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="">
    <link rel="stylesheet" href="css/common.css">
    <title></title>
    <script type="text/javascript">
        var customerId = ${customerId};
    </script>
    <style>
        .wpcontent {
            background: none repeat scroll 0 0 #FFF9D5;
            border: 1px solid #C9C9C9;
            border-radius: 5px 5px 5px 5px;
            color: #2db268;
            margin: 10px;
            padding: 6px;
            text-align: center;
        }
    </style>
</head>

<body style="background-color:#e7e9eb; max-width:640px; margin:0 auto">
<div class="wpcontent">图片较多，请在有WIFI的情况下打开，打开后直接保存图片，复制文字即可</div>
<p style="height:10px;"></p>

<p style="text-align:center">文字内容：${weaponBean.weaponContent}</p>

<p style="height:20px;"></p>

<div style="margin-left: 9px; margin-top: 29px;text-align:center;">
    <c:forEach items="${weaponBean.imgList}" var="img">
        <img style="width: 100%;" src="${uploadResourceServer.resourceUri(img)}">
    </c:forEach>
</div>
<%@include file="/resources/navbar/navbarmall.jsp" %>
</body>
</html>

