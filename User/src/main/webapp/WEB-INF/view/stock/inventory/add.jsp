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
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/stock/chart">股票</a></li>
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/stock/inventory">庫存明細</a></li>
									<li class="breadcrumb-item active">新增</li>
								</ol>
							</nav>
							<div class="d-md-flex align-items-md-start">
								<h1 class="page-title mr-sm-auto">${userPathEntity.up_name}</h1>
							</div>
						</header>
						<!-- /.page-title-bar -->
						
						<!-- .page-section -->
						<div class="page-section">
						
							<!-- .card -->
							<div class="card card-fluid">
								<!-- .card-body -->
								<div class="card-body">
									<!-- .form -->
									<c:url var="requestMapping" value="/stock/inventory/add.do" />
									<form:form id="stock-inventory-add-form" modelAttribute="stockEntity" action="${requestMapping}" method="post">
										<fieldset>
											<div class="form-group">
												<form:label path="st_SecuritiesAccountEntity">證券商&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
												<form:select path="st_SecuritiesAccountEntity" cssClass="custom-select">
													<form:option value="0" label="請選擇" />
													<c:forEach var="bean" items="${securitiesAccountList}">
														<form:option value="${bean.sa_id}"><c:out value="${bean.sa_SecuritiesBrokerBranchEntity.sb_SecuritiesBrokerHeadEntity.sh_name} ${bean.sa_SecuritiesBrokerBranchEntity.sb_SecuritiesBrokerHeadEntity.sh_no}${bean.sa_SecuritiesBrokerBranchEntity.sb_no}-${bean.sa_no}" /></form:option>
													</c:forEach>
												</form:select>
												<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
											</div>
											<div class="form-row">
												<div class="col-md-6 mb-3">
													<form:label path="st_SecuritiesEntity.se_no">代號&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
													<form:input path="st_SecuritiesEntity.se_no" cssClass="form-control" />
													<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
												</div>
												<div class="col-md-6 mb-3">
													<label for="se_name">股票</label>
													<input type="text" id="se_name" class="form-control" />
												</div>
											</div>
<!-- 											<div class="form-group"> -->
<!-- 												<label for="select2-data-remote" class="control-label">股票&nbsp;<span class="badge badge-subtle badge-warning">Required</span></label> -->
<!-- 												<select id="select2-data-remote" class="form-control"></select> -->
<!-- 											</div> -->
											<div class="btn-group btn-group-toggle" style="margin:10px 0">
												<label id="buy-radio" class="btn btn-outline-danger active" style="cursor:default">
													<input type="radio" value="buy" name="buy-sell" checked />買進
												</label>
												<label id="sell-radio" class="btn btn-outline-success">
													<input type="radio" value="sell" name="buy-sell" />賣出
												</label>
											</div>
											<!-- .card -->
											<div id="buy-sell-card" class="card card-fluid alert-danger">
												<!-- .card-body -->
												<div class="card-body">
													<div class="form-group">
														<label class="d-block">類別</label>
														<div id="spot-share-radio" class="custom-control custom-control-inline custom-radio">
															<input type="radio" id="spot-share" class="custom-control-input" value="1" name="co_no" checked />
															<label for="spot-share" class="custom-control-label">現股</label>
														</div>
														<div id="margin-purchase-radio" class="custom-control custom-control-inline custom-radio">
															<input type="radio" id="margin-purchase" class="custom-control-input" value="2" name="co_no" />
															<label for="margin-purchase" class="custom-control-label">融資</label>
														</div>
														<div id="short-sale-radio" class="custom-control custom-control-inline custom-radio not-show">
															<input type="radio" id="short-sale" class="custom-control-input" value="3" name="co_no" />
															<label for="short-sale" class="custom-control-label">融券</label>
														</div>
														<div id="drawing-lots-radio" class="custom-control custom-control-inline custom-radio">
															<input type="radio" id="drawing-lots" class="custom-control-input" value="4" name="co_no" />
															<label for="drawing-lots" class="custom-control-label">中籤</label>
														</div>
													</div>
													<div id="buy-form">
														<div class="form-group">
															<form:label path="st_buy_time">時間&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
															<form:input path="st_buy_time" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_buy_price">價格&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
															<form:input path="st_buy_price" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_buy_share">股數&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
															<form:input path="st_buy_share" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_buy_discount">手續費折扣</form:label>
															<div class="input-group input-group-alt">
																<form:input path="st_buy_discount" cssClass="form-control" />
																<div class="input-group-append"><span class="input-group-text" style="border-radius:.25rem">%</span></div>
																<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
															</div>
														</div>
														<div class="form-group">
															<form:label path="st_buy_fee">手續費</form:label>
															<form:input path="st_buy_fee" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_buy_delivery">交割金額</form:label>
															<form:input path="st_buy_delivery" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
													</div>
													<div id="sell-form" class="not-show">
														<div class="form-group">
															<form:label path="st_sell_time">時間&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
															<form:input path="st_sell_time" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_sell_price">價格&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
															<form:input path="st_sell_price" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_sell_share">股數&nbsp;<span class="badge badge-subtle badge-warning">Required</span></form:label>
															<form:input path="st_sell_share" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_sell_discount">手續費折扣</form:label>
															<div class="input-group input-group-alt">
																<form:input path="st_sell_discount" cssClass="form-control" />
																<div class="input-group-append"><span class="input-group-text" style="border-radius:.25rem">%</span></div>
																<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
															</div>
														</div>
														<div class="form-group">
															<form:label path="st_sell_fee">手續費</form:label>
															<form:input path="st_sell_fee" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_sell_tax">證交稅</form:label>
															<form:input path="st_sell_tax" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
														<div class="form-group">
															<form:label path="st_sell_delivery">交割金額</form:label>
															<form:input path="st_sell_delivery" cssClass="form-control" />
															<div class="invalid-feedback"><i class="fa fa-exclamation-circle fa-fw"></i></div>
														</div>
													</div>
												</div>
												<!-- /.card-body -->
											</div>
											<!-- /.card -->
										</fieldset>
										<hr />
										<div class="form-actions">
											<a href="<%=request.getContextPath()%>/stock/inventory" class="btn btn-secondary ml-auto">取消</a>
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