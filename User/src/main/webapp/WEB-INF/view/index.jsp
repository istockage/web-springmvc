<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>iStockage</title>
<%@include file="head.jsp"%>
</head>
<body>
	<!-- .app -->
	<div class="app">
	
		<!-- header -->
		<jsp:include page="header.jsp" />
		<!-- /header -->
		
		<!-- aside -->
		<%@include file="aside.jsp"%>
		<!-- /aside -->
		
	</div>
	<!-- /.app -->
	
	<script src="<%=request.getContextPath()%>/js/looper/main.min.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>