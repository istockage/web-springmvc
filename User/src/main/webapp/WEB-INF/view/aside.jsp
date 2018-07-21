<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>aside</title>
<script src="<%=request.getContextPath()%>/js/stacked-menu/stacked-menu.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<!-- .app-aside -->
	<aside class="app-aside">
		<!-- .aside-content -->
		<div class="aside-content">
			<!-- .aside-menu -->
			<section class="aside-menu has-scrollable">
				<!-- .stacked-menu -->
				<nav id="stacked-menu" class="stacked-menu">
					<!-- .menu -->
					<ul class="menu">
						<!-- .menu-item -->
						<li class="menu-item has-active">
							<a href="#" class="menu-link"><span class="menu-text">Dashboard</span></a>
						</li>
						<!-- /.menu-item -->
						<!-- .menu-item -->
						<li class="menu-item has-child">
							<a href="#" class="menu-link"><span class="menu-text">Layouts</span></a>
							<!-- child menu -->
							<ul class="menu">
								<li class="menu-item"><a href="#" class="menu-link">Blank Page</a></li>
								<li class="menu-item"><a href="#" class="menu-link">Header no Search</a></li>
							</ul>
							<!-- /child menu -->
						</li>
						<!-- /.menu-item -->
					</ul>
					<!-- /.menu -->
				</nav>
				<!-- /.stacked-menu -->
			</section>
			<!-- /.aside-menu -->
		</div>
		<!-- /.aside-content -->
	</aside>
	<!-- /.app-aside -->
</body>
</html>