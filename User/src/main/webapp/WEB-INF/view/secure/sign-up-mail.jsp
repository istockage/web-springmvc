<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發送確認信 - iStockage</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap-4.0.0.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure.css" type="text/css" />
<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="container top">
			<div class="title">
				<h3>iStockage</h3>
			</div>
		</div>
		<c:if test="${not empty success}">
			<div class="container alert alert-success success-message">
				<span>${success}</span>
				<button class="close">
					<span>&times;</span>
				</button>
			</div>
		</c:if>
		<div class="container alert alert-success">
			<span>恭喜您註冊成為 iStockage 的會員，確認信已發送至您的信箱，請開啟信件並點擊連結以啟用您的帳號。</span>
		</div>
		<div class="container form">
			<form action="<c:url value='/secure/sign-up-mail.do' />" method="post">
				<div class="form-group">
					<p>若您未收到確認信，請點擊按鈕重新發送。<p>
					<input type="submit" class="btn btn-success btn-block" value="發送確認信" />
				</div>
			</form>
			<div class="form-group">
				<a href="<%=request.getContextPath()%>/secure/sign-in" class="btn btn-outline-success btn-block">登入</a>
			</div>
		</div>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/secure.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>