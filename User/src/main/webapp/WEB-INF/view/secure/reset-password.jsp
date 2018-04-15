<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重設密碼 - iStockage</title>
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
		<c:if test="${not empty error}">
			<div class="container alert alert-danger">
				<span>${error}</span>
				<button class="close">
					<span>&times;</span>
				</button>
			</div>
		</c:if>
		<div class="container form">
			<form action="<c:url value='/secure/reset-password.do' />" method="post">
				<div class="form-group">
					<label for="me_password_random">驗證碼</label>
					<input type="password" id="me_password_random" class="form-control" name="me_password_random" value="${me_password_random}" autofocus />
				</div>
				<div class="form-group">
					<label for="me_password_new">新密碼</label>
					<input type="password" id="me_password_new" class="form-control" name="me_password_new" value="${me_password_new}" />
				</div>
				<div class="form-group">
					<label for="me_password_new_again">確認密碼</label>
					<input type="password" id="me_password_new_again" class="form-control" name="me_password_new_again" value="${me_password_new_again}" />
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success btn-block" value="重設密碼" />
				</div>
			</form>
		</div>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/validation.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/secure.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>