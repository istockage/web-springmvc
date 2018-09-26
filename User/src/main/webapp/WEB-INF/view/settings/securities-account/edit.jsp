<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${userPathEntity.up_name} - iStockage</title>
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
									<li class="breadcrumb-item active">編輯</li>
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
										<h6 class="card-header">${userPathEntity.up_name}</h6>
										<!-- .card-body -->
										<div class="card-body">
											<!-- .form -->
											<c:url var="requestMapping" value="/settings/securities-account/edit.do" />
											<form:form id="securities-account-edit-form" modelAttribute="securitiesAccountEntity" action="${requestMapping}" method="post">
												<fieldset>
													<div class="form-group" style="display:none">
														<form:label path="sa_id">流水號</form:label>
														<form:hidden path="sa_id" />
													</div>
													<div class="form-group">
														<form:label path="sa_SecuritiesBrokerBranchEntity.sb_SecuritiesBrokerHeadEntity">證券商</form:label>
														<form:select id="sh_no" path="sa_SecuritiesBrokerBranchEntity.sb_SecuritiesBrokerHeadEntity" cssClass="custom-select">
															<form:option value="0" label="請選擇" />
															<form:options items="${securitiesBrokerHeadList}" itemValue="sh_no" itemLabel="sh_name" />
														</form:select>
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
													</div>
													<div class="form-group">
														<form:label path="sa_SecuritiesBrokerBranchEntity">分公司</form:label>
														<form:select id="sb_no" path="sa_SecuritiesBrokerBranchEntity" cssClass="custom-select">
															<form:option value="0" label="請選擇" />
															<form:options items="${securitiesBrokerBranchList}" itemValue="sb_no" itemLabel="sb_name" />
														</form:select>
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
													</div>
													<div class="form-group">
														<form:label path="sa_no">帳號</form:label>
														<form:input path="sa_no" cssClass="form-control" aria-describedby="sa_no-help" />
														<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														<small id="sa_no-help" class="form-text text-muted">帳號為後 7 位數字。</small>
													</div>
													<div class="form-group">
														<form:label path="sa_discount">電子下單手續費折扣</form:label>
														<div class="input-group input-group-alt">
															<form:input path="sa_discount" cssClass="form-control" aria-describedby="sa_discount-help" />
															<div class="input-group-append"><span class="input-group-text" style="border-radius:.25rem">%</span></div>
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<small id="sa_discount-help" class="form-text text-muted">若手續費折扣為 6 折，請輸入 60。</small>
													</div>
												</fieldset>
												<hr />
												<div class="form-actions">
													<a href="<%=request.getContextPath()%>/settings/securities-account" class="btn btn-secondary ml-auto">取消</a>
													<button type="submit" class="btn btn-primary">儲存</button>
												</div>
											</form:form>
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
			<%@include file="../../footer.jsp"%>
			<!-- /footer -->
			
		</main>
		<!-- /.app-main -->
		
		<!-- /main -->
		
	</div>
	<!-- /.app -->
	
<%@include file="../../body.jsp"%>
</body>
</html>