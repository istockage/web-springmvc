<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap-4.1.2.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/looper/main.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/popper/popper.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap-4.1.2.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/looper/main.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<!-- .app-header -->
	<header class="app-header">
		<!-- .top-bar -->
		<div class="top-bar">
			<!-- .top-bar-brand -->
			<div class="top-bar-brand">
				<a href="<%=request.getContextPath()%>/index">iStockage</a>
			</div>
			<!-- /.top-bar-brand -->
			
			<!-- .top-bar-list -->
			<div class="top-bar-list">
				<!-- .top-bar-item -->
				<div class="top-bar-item top-bar-item-right px-0 d-none d-sm-flex">
					<!-- .dropdown -->
					<div class="dropdown">
						<!-- .btn-account -->
						<button class="btn-account d-none d-md-flex" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="user-avatar"></span>
						</button>
						<!-- /.btn-account -->
						<div class="dropdown-arrow dropdown-arrow-right"></div>
						<!-- .dropdown-menu -->
						<div class="dropdown-menu">
							<p class="dropdown-header">${user.me_email}</p>
							<div class="dropdown-divider"></div>
							<a href="<%=request.getContextPath()%>/secure/sign-out.do" class="dropdown-item">登出</a>
						</div>
						<!-- /.dropdown-menu -->
					</div>
					<!-- /.dropdown -->
				</div>
				<!-- /.top-bar-item -->
			</div>
			<!-- /.top-bar-list -->
		</div>
		<!-- /.top-bar -->
	</header>
	<!-- /.app-header -->
</body>
</html>