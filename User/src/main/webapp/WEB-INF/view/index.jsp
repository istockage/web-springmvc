<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iStockage</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
</head>
<body>
	<h1>iStockage</h1>
	<c:choose>
		<c:when test="${not empty user}">
			<a href="<%=request.getContextPath()%>/secure/sign-out.do">登出</a>
		</c:when>
	</c:choose>
</body>
</html>