<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找不到網頁 - iStockage</title>
<c:choose>
<c:when test="${empty user}">
<%@include file="../secure/head.jsp"%>
</c:when>
<c:when test="${not empty user}">
<%@include file="../head.jsp"%>
</c:when>
</c:choose>
</head>
<body>
	<c:choose>
		<c:when test="${empty user}">
			<div class="container-fluid">
				<div class="container top">
					<div class="title">
						<h3>page not found</h3>
					</div>
				</div>
			</div>
		</c:when>
			
		<c:when test="${not empty user}">
			<!-- .app -->
			<div class="app">
			
				<!-- header -->
				<jsp:include page="../header.jsp" />
				<!-- /header -->
		
				<!-- aside -->
				<%@include file="../aside.jsp"%>
				<!-- /aside -->
		
				<!-- main -->
				
				<!-- .app-main -->
				<main class="app-main">
					<!-- .wrapper -->
					<div class="wrapper">
						<!-- .page -->
						<div class="page">
							<!-- .page-inner -->
							<div class="page-inner">
								<!-- .page-title-bar -->
								<header class="page-title-bar">404</header>
								<!-- /.page-title-bar -->
								
								<!-- .page-section -->
								<div class="page-section"></div>
								<!-- /.page-section -->
							</div>
							<!-- /.page-inner -->
						</div>
						<!-- /.page -->
					</div>
					<!-- /.wrapper -->
				</main>
				<!-- /.app-main -->
				
				<!-- /main -->
				
			</div>
			<!-- /.app -->
			
			<script src="<%=request.getContextPath()%>/js/looper/main.min.js" type="text/javascript" charset="utf-8"></script>
		</c:when>
	</c:choose>
</body>
</html>