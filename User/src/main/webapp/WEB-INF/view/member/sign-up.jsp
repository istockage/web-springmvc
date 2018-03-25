<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" type="text/css" />
</head>
<body>
	<form:form action="/User/member/sign-up.do" method="post" modelAttribute="memberEntity">
		<div class="form-group">
			<form:label path="me_email">信箱</form:label>
			<form:input path="me_email" cssClass="form-control" />
		</div>
		<div class="form-group">
			<form:label path="me_password">密碼</form:label>
			<form:password path="me_password" cssClass="form-control" />
		</div>
		<div class="form-group">
			<label for="me_password_again">確認密碼</label>
			<input type="password" id="me_password_again" class="form-control" name="me_password_again" />
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-success" value="註冊" />
		</div>
	</form:form>
</body>
</html>