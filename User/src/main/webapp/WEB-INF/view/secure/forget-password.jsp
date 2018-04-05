<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘記密碼 - iStockage</title>
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
			<form action="<c:url value='/secure/forget-password.do' />" method="post">
				<div class="form-group">
					<label for="me_email" class="control-label">信箱</label>
					<input type="text" id="me_email" class="form-control" name="me_email" value="${me_email}" autofocus />
					<div><p style="font-size:92%">請輸入您的信箱，系統將發送驗證碼，以重設您的密碼。<p></div>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success btn-block" value="發送" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>