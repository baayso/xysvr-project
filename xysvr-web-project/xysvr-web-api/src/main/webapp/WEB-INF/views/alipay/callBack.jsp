<%--
     //***********页面功能说明***********
     // 该页面可在本机电脑测试，可放入HTML等美化页面的代码、商户业务逻辑程序代码
     TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
     TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
     //********************************
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝页面跳转同步通知页面</title>
</head>
<body>
    <c:choose>
        <c:when test="${verify_result == true}">
            <h1>商户订单号 : ${out_trade_no}</h1>
            <h1>支付宝交易号 : ${trade_no}</h1>
            <h1>${username}您已支付成功,请点击上方菜单返回。</h1>
        </c:when>

        <c:otherwise>
            <h1>验证失败,请点击上方菜单返回。</h1>
        </c:otherwise>
    </c:choose>
</body>
</html>