<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>證券帳戶 - iStockage</title>
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
									<li class="breadcrumb-item active">證券帳戶</li>
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
										<h6 class="card-header">證券帳戶</h6>
										<!-- .card-body -->
										<div class="card-body">
											<!-- .table-responsive -->
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>證券商</th>
															<th>帳號</th>
															<th>折扣</th>
															<th>交易次數</th>
															<th class="text-right">
																<a href="#" class="btn btn-lg btn-light"><i class="fa fa-plus"></i></a>
															</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="bean" items="${accountList}" varStatus="status">
															<tr>
																<td class="align-middle">${bean.ac_BrokerBranchEntity.bb_BrokerHeadEntity.bh_name}證券</td>
																<td class="align-middle">${bean.ac_BrokerBranchEntity.bb_BrokerHeadEntity.bh_no}${bean.ac_BrokerBranchEntity.bb_no}${bean.ac_no}</td>
																<td class="align-middle">${bean.ac_discount}</td>
																<td class="align-middle">100</td>
																<td class="align-middle text-right">
																	<a href="#" class="btn btn-lg btn-light"><i class="fa fa-edit"></i></a>
																	<a href="#" class="btn btn-lg btn-light"><i class="fa fa-trash-o"></i></a>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<!-- /.table-responsive -->
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
	
	<script src="<%=request.getContextPath()%>/js/looper/main.min.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>