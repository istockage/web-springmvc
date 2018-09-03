<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增股票交易 - iStockage</title>
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
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/stock/chart">股票</a></li>
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/stock/list">交易明細</a></li>
									<li class="breadcrumb-item active">新增</li>
								</ol>
							</nav>
						</header>
						<!-- /.page-title-bar -->
						
						<!-- .page-section -->
						<div class="page-section">
						
							<!-- .card -->
							<div class="card card-fluid">
								<h6 class="card-header">新增股票交易</h6>
								<!-- .card-body -->
								<div class="card-body">
									<!-- .form -->
									<c:url var="requestMapping" value="/stock/list/add.do" />
									<form:form id="stock-list-add-form" modelAttribute="stockEntity" action="${requestMapping}" method="post">
										<fieldset>
											<div class="form-group">
												<form:label path="st_SecuritiesAccountEntity">證券商&nbsp;<span class="badge badge-subtle badge-danger">Required</span></form:label>
												<form:select path="st_SecuritiesAccountEntity" cssClass="custom-select">
													<form:option value="0" label="請選擇" />
													<form:options items="${securitiesAccountEntity}" itemValue="sa_id" itemLabel="sa_SecuritiesBrokerBranchEntity.sb_name" />
												</form:select>
											</div>
											<div class="form-row">
												<div class="col-md-6 mb-3">
													<label for="st_no">代號&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
													<input id="st_no" class="form-control" />
												</div>
												<div class="col-md-6 mb-3">
													<label for="st_name">股票&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
													<input id="st_name" class="form-control" />
												</div>
											</div>
											<div class="form-group">
												<label class="d-block">買賣</label>
												<div class="custom-control custom-control-inline custom-radio">
													<input type="radio" id="buy" class="custom-control-input" name="rdGroup1" checked="checked" />
													<label for="buy" class="custom-control-label">買進</label>
												</div>
												<div class="custom-control custom-control-inline custom-radio">
													<input type="radio" id="sell" class="custom-control-input" name="rdGroup1" />
													<label for="sell" class="custom-control-label">賣出</label>
												</div>
											</div>
											<div class="form-group">
												<label class="d-block">買賣</label>
												<div class="custom-control custom-control-inline custom-radio">
													<input type="radio" id="1" class="custom-control-input" name="rdGroup2" checked="checked" />
													<label for="1" class="custom-control-label">現股</label>
												</div>
												<div class="custom-control custom-control-inline custom-radio">
													<input type="radio" id="2" class="custom-control-input" name="rdGroup2" />
													<label for="2" class="custom-control-label">融資</label>
												</div>
												<div class="custom-control custom-control-inline custom-radio">
													<input type="radio" id="3" class="custom-control-input" name="rdGroup2" />
													<label for="3" class="custom-control-label">融券</label>
												</div>
											</div>
											<div class="form-group">
												<label for="st_buy_time">時間&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
												<input id="st_buy_time" class="form-control" />
											</div>
											<div class="form-group">
												<label for="st_buy_price">價格&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
												<input id="st_buy_price" class="form-control" />
											</div>
											<div class="form-group">
												<label for="st_buy_share">股數&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
												<input id="st_buy_share" class="form-control" />
											</div>
											<div class="form-group">
												<label for="st_buy_fee">手續費&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
												<input id="st_buy_fee" class="form-control" />
											</div>
											<div class="form-group">
												<label for="st_buy_cost">成本&nbsp;<span class="badge badge-subtle badge-danger">Required</span></label>
												<input id="st_buy_cost" class="form-control" />
											</div>
										</fieldset>
										<hr />
										<div class="form-actions">
											<a href="<%=request.getContextPath()%>/stock/list" class="btn btn-secondary ml-auto">取消</a>
											<button type="submit" class="btn btn-primary">新增</button>
										</div>
									</form:form>
									<!-- /.form -->
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
							
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