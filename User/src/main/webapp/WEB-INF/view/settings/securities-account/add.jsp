<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增證券帳戶 - iStockage</title>
<%@include file="../../head.jsp"%>
</head>
<body>
	<!-- .app -->
	<div class="app">
	
		<!-- header -->
		<jsp:include page="../../header.jsp" />
		<!-- /header -->
		
		<!-- aside -->
		<jsp:include page="../../aside.jsp" />
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
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/settings/securities-account">證券帳戶</a></li>
									<li class="breadcrumb-item active">新增</li>
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
											<a href="<%=request.getContextPath()%>/settings/account" class="nav-link">個人帳戶</a>
											<a class="nav-link active">證券帳戶</a>
											<a href="<%=request.getContextPath()%>/settings/futures-account" class="nav-link">期貨帳戶</a>
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
										<h6 class="card-header">新增證券帳戶</h6>
										<!-- .card-body -->
										<div class="card-body">
											<form:form modelAttribute="securitiesAccountEntity" action="/User/settings/securities-account/add.do" method="post">
												<fieldset>
													<div class="form-group">
														<form:label path="sa_SecuritiesBrokerHeadEntity">證券商</form:label>
														<form:select path="sa_SecuritiesBrokerHeadEntity" cssClass="custom-select">
															<form:option value="0" label="請選擇" />
															<form:options items="${securitiesBrokerHeadList}" itemValue="sh_id" itemLabel="sh_name" />
														</form:select>
													</div>
													<div class="form-group">
														<form:label path="sa_SecuritiesBrokerBranchEntity">分公司</form:label>
														<form:select path="sa_SecuritiesBrokerBranchEntity" cssClass="custom-select">
															<form:option value="0" label="請選擇" />
														</form:select>
													</div>
													<div class="form-group">
														<form:label path="sa_no">帳號</form:label>
														<form:input path="sa_no" cssClass="form-control" />
													</div>
													<div class="form-group">
														<form:label path="sa_discount">折扣</form:label>
														<form:input path="sa_discount" cssClass="form-control" />
													</div>
												</fieldset>
												<hr />
												<div class="form-actions">
													<button type="submit" class="btn btn-primary ml-auto">新增</button>
												</div>
											</form:form>
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
			<%@include file="../../footer.jsp"%>
			<!-- /footer -->
			
		</main>
		<!-- /.app-main -->
		
		<!-- /main -->
		
	</div>
	<!-- /.app -->
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/looper/main.min.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>