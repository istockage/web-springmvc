<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入 - iStockage</title>
<%@include file="head.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="container top">
			<div class="title">
				<h3>iStockage</h3>
			</div>
		</div>
		<c:if test="${not empty error}">
			<div class="container alert alert-danger error-message">
				<span>${error}</span>
				<button class="close">
					<span>&times;</span>
				</button>
			</div>
		</c:if>
		<c:if test="${not empty success}">
			<div class="container alert alert-success success-message">
				<span>${success}</span>
				<button class="close">
					<span>&times;</span>
				</button>
			</div>
		</c:if>
		<div class="container form">
			<form action="<c:url value='/secure/sign-in.do' />" method="post">
				<div class="form-group sign-in-google">
					<img src="<%=request.getContextPath()%>/images/btn_sign_in_google.svg" />
				</div>
				<div class="form-group">
					<hr />
				</div>
				<div class="form-group">
					<label for="me_email" class="control-label">信箱</label>
					<input type="text" id="me_email" class="form-control" name="me_email" value="${me_email}" autofocus />
				</div>
				<div class="form-group">
					<label for="me_password" class="control-label">密碼</label>
					<input type="password" id="me_password" class="form-control" name="me_password" value="${me_password}" />
					<a href="<%=request.getContextPath()%>/secure/forget-password">忘記密碼？</a>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success btn-block" value="登入" />
				</div>
			</form>
		</div>
		<div class="container bottom">
			<div class="sign-up">
				<span>還沒有帳號嗎？</span><a href="<%=request.getContextPath()%>/secure/sign-up">註冊</a>
			</div>
		</div>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/secure.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>