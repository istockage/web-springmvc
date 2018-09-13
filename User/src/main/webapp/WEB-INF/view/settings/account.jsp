<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${pathName} - iStockage</title>
<%@include file="../head.jsp"%>
</head>
<body>
	<!-- .app -->
	<div class="app">
	
		<!-- header -->
		<jsp:include page="../header.jsp" />
		<!-- /header -->
		
		<!-- aside -->
		<jsp:include page="../aside.jsp" />
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
						<header class="page-title-bar">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/settings/account">設定</a></li>
									<li class="breadcrumb-item active">個人帳戶</li>
								</ol>
							</nav>
						</header>
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
											<a class="nav-link active">個人帳戶</a>
											<a href="<%=request.getContextPath()%>/settings/securities-account" class="nav-link">證券帳戶</a>
											<a href="<%=request.getContextPath()%>/settings/futures-account" class="nav-link">期貨帳戶</a>
										</nav>
										<!-- /.nav -->
									</div>
									<!-- /.card -->
									
								</div>
								<!-- /grid column -->
								
								<!-- grid column -->
								<div class="col-lg-9">
								
									<!-- .alert -->
									<c:if test="${not empty error}">
										<div class="alert alert-danger alert-dismissible fade show">
											<button type="button" class="close" data-dismiss="alert">×</button>
											<strong>密碼變更失敗</strong>
											<div>${error}</div>
										</div>
									</c:if>
									<!-- /.alert -->
									
									<!-- .alert -->
									<c:if test="${not empty success}">
										<div class="alert alert-success alert-dismissible fade show">
											<button type="button" class="close" data-dismiss="alert">×</button>
											<strong>密碼變更成功</strong>
											<div>${success}</div>
										</div>
									</c:if>
									<!-- /.alert -->
								
									<!-- .card -->
									<div class="card card-fluid">
										<h6 class="card-header">基本資料</h6>
										<!-- .card-body -->
										<div class="card-body">
											<!-- .form -->
											<c:url var="requestMapping" value="/settings/account/info.do" />
											<form:form id="settings-account-info-form" modelAttribute="user" action="${requestMapping}" method="post">
												<fieldset>
													<div class="form-row">
														<div class="col-md-6 mb-3">
															<form:label path="me_lastname">姓</form:label>
															<form:input path="me_lastname" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="col-md-6 mb-3">
															<form:label path="me_firstname">名</form:label>
															<form:input path="me_firstname" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
													</div>
													<div class="form-group">
														<form:label path="me_email">信箱</form:label>
														<form:input path="me_email" cssClass="form-control" aria-describedby="me_email-help" />
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														<small id="me_email-help" class="form-text text-muted">將被變更為您的登入信箱。</small>
													</div>
												</fieldset>
												<hr />
												<div class="form-actions">
													<button type="submit" class="btn btn-primary ml-auto">儲存</button>
												</div>
											</form:form>
											<!-- /.form -->
										</div>
										<!-- /.card-body -->
									</div>
									<!-- /.card -->
									
									<!-- .card -->
									<div class="card card-fluid">
										<h6 class="card-header">變更密碼</h6>
										<!-- .card-body -->
										<div class="card-body">
											<!-- .form -->
											<form id="settings-account-change-password-form" action="<c:url value='/settings/account/change-password.do' />" method="post">
												<fieldset>
													<div class="form-group">
														<label for="me_password">舊密碼</label>
														<input type="password" id="me_password" class="form-control" name="me_password" value="${me_password}" />
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
													</div>
													<div class="form-group">
														<label for="me_password_new">新密碼</label>
														<input type="password" id="me_password_new" class="form-control" name="me_password_new" value="${me_password_new}" />
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
													</div>
													<div class="form-group">
														<label for="me_password_new_again">確認密碼</label>
														<input type="password" id="me_password_new_again" class="form-control" name="me_password_new_again" value="${me_password_new_again}" />
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
													</div>
												</fieldset>
												<hr />
												<div class="form-actions">
													<button type="submit" class="btn btn-primary ml-auto">儲存</button>
												</div>
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
			
			<!-- footer -->
			<%@include file="../footer.jsp"%>
			<!-- /footer -->
			
		</main>
		<!-- /.app-main -->
		
		<!-- /main -->
		
	</div>
	<!-- /.app -->
	
<%@include file="../body.jsp"%>
</body>
</html>