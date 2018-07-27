<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人帳戶 - iStockage</title>
<%@include file="../head.jsp"%>
</head>
<body>
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
						<header class="page-title-bar"></header>
						<!-- /.page-title-bar -->
						
						<!-- .page-section -->
						<div class="page-section">
							<!-- grid row -->
							<div class="row">
								<!-- grid column -->
								<div class="col-lg-3">
									<!-- .card -->
									<div class="card card-fluid">
										<!-- .nav -->
										<nav class="nav nav-tabs flex-column">
											<a href="<%=request.getContextPath()%>/settings/account" class="nav-link active">個人帳戶</a>
											<a href="<%=request.getContextPath()%>/settings/account" class="nav-link">證券帳戶</a>
											<a href="<%=request.getContextPath()%>/settings/account" class="nav-link">期貨帳戶</a>
										</nav>
										<!-- /.nav -->
									</div>
									<!-- /.card -->
								</div>
								<!-- /grid column -->
								
								<!-- grid column -->
								<div class="col-lg-9">
									<!-- .card -->
									<div class="card card-fluid">
										<!-- .card-body -->
										<div class="card-body">
											<!-- .form -->
											<form>
												<!-- .fieldset -->
												<fieldset>
													<legend>基本資料</legend>
													<!-- .form-group -->
													<div class="form-group">
														<label for="tf1">姓</label>
														<input type="text" id="tf1" class="form-control" />
													</div>
													<!-- /.form-group -->
													<!-- .form-group -->
													<div class="form-group">
														<label for="tf2">名</label>
														<input type="text" id="tf2" class="form-control" />
													</div>
													<!-- /.form-group -->
													<!-- .form-group -->
													<div class="form-group">
														<label for="tf3">信箱</label>
														<input type="email" id="tf3" class="form-control" aria-describedby="tf1Help" placeholder="">
														<small id="tf1Help" class="form-text text-muted">將被變更為您的登入帳號。</small>
													</div>
													<!-- /.form-group -->
												</fieldset>
												<!-- /.fieldset -->
												<!-- .form-actions -->
												<div class="form-actions">
													<button type="submit" class="btn btn-primary ml-auto">儲存</button>
												</div>
												<!-- /.form-actions -->
											</form>
											<!-- /.form -->
											<!-- .form -->
											<form>
												<!-- .fieldset -->
												<fieldset>
													<legend>密碼</legend>
													<!-- .form-group -->
													<div class="form-group">
														<label for="tf4">舊密碼</label>
														<input type="password" id="tf4" class="form-control" />
													</div>
													<!-- /.form-group -->
													<!-- .form-group -->
													<div class="form-group">
														<label for="tf5">新密碼</label>
														<input type="password" id="tf5" class="form-control" />
													</div>
													<!-- /.form-group -->
													<!-- .form-group -->
													<div class="form-group">
														<label for="tf6">確認新密碼</label>
														<input type="password" id="tf6" class="form-control" />
													</div>
													<!-- /.form-group -->
												</fieldset>
												<!-- /.fieldset -->
												<!-- .form-actions -->
												<div class="form-actions">
													<button type="submit" class="btn btn-primary ml-auto">儲存</button>
												</div>
												<!-- /.form-actions -->
											</form>
											<!-- /.form -->
										</div>
										<!-- /.card-body -->
									</div>
									<!-- /.card -->
								</div>
								<!-- /grid column -->
							</div>
							<!-- /grid row -->
						</div>
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
</body>
</html>