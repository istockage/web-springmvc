<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap-4.0.0.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap-4.0.0.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/index">iStockage</a>
		<div id="navbarNavDropdown" class="collapse navbar-collapse justify-content-end">
			<c:choose>
				<c:when test="${not empty user}">
					<ul class="navbar-nav">
						<li class="nav-item dropdown">
							<a href="#" id="navbarDropdownMenuLink" class="nav-link dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${user.me_no}</a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
								<p class="dropdown-header">${user.me_email}</p>
								<div class="dropdown-divider"></div>
								<a href="<%=request.getContextPath()%>/secure/sign-out.do" class="dropdown-item">登出</a>
							</div>
						</li>
					</ul>
				</c:when>
			</c:choose>
		</div>
	</nav>
</body>
</html>