<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重新發送確認信 - iStockage</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container form">
			<form action="<c:url value='/secure/sign-up-mail.do' />" method="post">
				<div class="form-group">
					<input type="submit" class="btn btn-success btn-block" value="重新發送確認信" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>