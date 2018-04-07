<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊 - iStockage</title>
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
		<div class="container form">
			<form:form action="/User/secure/sign-up.do" method="post" modelAttribute="memberEntity">
				<div class="form-group">
					<form:label path="me_email">信箱</form:label>
					<form:input path="me_email" cssClass="form-control" autofocus="autofocus" />
				</div>
				<div class="form-group">
					<form:label path="me_password">密碼</form:label>
					<form:password path="me_password" cssClass="form-control" />
				</div>
				<div class="form-group" style="margin-bottom:20px">
					<label for="me_password_again">確認密碼</label>
					<input type="password" id="me_password_again" class="form-control" name="me_password_again" />
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success btn-block" value="註冊" />
				</div>
			</form:form>
		</div>
		<div class="container bottom">
			<div class="sign-in">
				<span>已經有帳號了嗎？</span><a href="<%=request.getContextPath()%>/secure/sign-in">登入</a>
			</div>
		</div>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/validation.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>