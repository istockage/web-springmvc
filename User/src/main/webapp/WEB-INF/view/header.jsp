<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- .app-header -->
<header class="app-header">
	<!-- .top-bar -->
	<div class="top-bar">
	
		<!-- .top-bar-brand -->
		<div class="top-bar-brand">
			<a href="<%=request.getContextPath()%>/index" style="font-size:20px">iStockage</a>
		</div>
		<!-- /.top-bar-brand -->
		
		<!-- .top-bar-list -->
		<div class="top-bar-list">
		
			<!-- .top-bar-item -->
			<div class="top-bar-item px-2 d-md-none d-lg-none d-xl-none">
				<!-- toggle menu -->
				<button class="hamburger hamburger-squeeze" type="button" data-toggle="aside" aria-label="Menu" aria-controls="navigation">
					<span class="hamburger-box">
						<span class="hamburger-inner"></span>
					</span>
				</button>
				<!-- /toggle menu -->
			</div>
			<!-- /.top-bar-item -->
			
			<!-- .top-bar-item -->
			<div class="top-bar-item top-bar-item-full">
				<!-- .top-bar-search -->
				<div class="top-bar-search">
					<div class="input-group input-group-search">
						<div class="input-group-prepend">
							<span class="input-group-text">
								<span class="oi oi-magnifying-glass"></span>
							</span>
						</div>
						<input type="text" class="form-control" aria-label="Search" placeholder="Search" />
					</div>
				</div>
				<!-- /.top-bar-search -->
			</div>
			<!-- /.top-bar-item -->
			
			<!-- .top-bar-item -->
			<div class="top-bar-item top-bar-item-right px-0 d-none d-sm-flex">
				<!-- .dropdown -->
				<div class="dropdown">
					<!-- .btn-account -->
					<button type="button" class="btn-account d-none d-md-flex" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="tile tile-circle">
							<span class="oi oi-person" style="top:25%"></span>
						</span>
					</button>
					<!-- /.btn-account -->
					<div class="dropdown-arrow dropdown-arrow-right"></div>
					<!-- .dropdown-menu -->
					<div class="dropdown-menu">
						<p class="dropdown-header" style="line-height:8px">${user.me_email}</p>
						<p class="dropdown-header" style="line-height:8px">ID: ${user.me_no}</p>
						<div class="dropdown-divider"></div>
						<a href="<%=request.getContextPath()%>/member/profile" class="dropdown-item"><span class="dropdown-icon oi oi-person"></span>帳戶</a>
						<a href="<%=request.getContextPath()%>/settings/account" class="dropdown-item"><span class="dropdown-icon oi oi-cog"></span>設定</a>
						<div class="dropdown-divider"></div>
						<a href="<%=request.getContextPath()%>/secure/sign-out.do" class="dropdown-item"><span class="dropdown-icon oi oi-account-logout"></span>登出</a>
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
