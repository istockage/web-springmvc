<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- .app-aside -->
<aside class="app-aside">
	<!-- .aside-content -->
	<div class="aside-content">
	
		<!-- .aside-header -->
		<header class="aside-header d-block d-md-none">
			<!-- .btn-account -->
			<button class="btn-account" type="button" data-toggle="collapse" data-target="#dropdown-aside">
				<span class="tile tile-circle">
					<span class="oi oi-person" style="top:7px"></span>
				</span>
				<span class="account-icon">
					<span class="fa fa-caret-down fa-lg"></span>
				</span>
			</button>
			<!-- /.btn-account -->
			<!-- .dropdown-aside -->
			<div id="dropdown-aside" class="dropdown-aside collapse">
				<!-- dropdown-items -->
				<div class="pb-3">
					<p class="dropdown-header" style="line-height:8px">${user.me_email}</p>
					<p class="dropdown-header" style="line-height:8px">ID: ${user.me_no}</p>
					<div class="dropdown-divider"></div>
					<a href="<%=request.getContextPath()%>/member/profile" class="dropdown-item"><span class="dropdown-icon oi oi-person"></span>帳戶</a>
					<a href="<%=request.getContextPath()%>/settings/account" class="dropdown-item"><span class="dropdown-icon oi oi-cog"></span>設定</a>
					<div class="dropdown-divider"></div>
					<a href="<%=request.getContextPath()%>/secure/sign-out.do" class="dropdown-item"><span class="dropdown-icon oi oi-account-logout"></span>登出</a>
				</div>
				<!-- /dropdown-items -->
			</div>
			<!-- /.dropdown-aside -->
		</header>
		<!-- /.aside-header -->
	
		<!-- .aside-menu -->
		<section class="aside-menu has-scrollable">
			<!-- .stacked-menu -->
			<nav id="stacked-menu" class="stacked-menu">
				<!-- .menu -->
				<ul class="menu">
					<!-- .menu-item -->
					<li class="menu-item has-child">
						<a href="#" class="menu-link"><span class="menu-icon oi oi-bar-chart"></span><span class="menu-text">股票</span></a>
						<!-- child menu -->
						<ul class="menu">
							<li class="menu-item"><a href="#" class="menu-link">新增交易</a></li>
							<li class="menu-item"><a href="#" class="menu-link">統計</a></li>
						</ul>
						<!-- /child menu -->
					</li>
					<!-- /.menu-item -->
					<!-- .menu-item -->
					<li class="menu-item has-child">
						<a href="#" class="menu-link"><span class="menu-icon oi oi-bar-chart"></span><span class="menu-text">期貨</span></a>
						<!-- child menu -->
						<ul class="menu">
							<li class="menu-item"><a href="#" class="menu-link">新增交易</a></li>
							<li class="menu-item"><a href="#" class="menu-link">統計</a></li>
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
