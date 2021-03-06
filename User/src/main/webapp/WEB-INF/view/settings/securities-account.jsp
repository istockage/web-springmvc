<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${userPathEntity.up_name} - iStockage</title>
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
										<h6 class="card-header">${userPathEntity.up_name}</h6>
										<!-- .card-body -->
										<div class="card-body">
											<!-- .table-responsive -->
											<div class="table-responsive">
												<table id="securities-account-table" class="table">
													<thead>
														<tr>
															<th>編號</th>
															<th>證券商</th>
															<th>分公司</th>
															<th>帳號</th>
															<th>折扣</th>
															<th>交易次數</th>
															<th class="text-right">
																<a href="<%=request.getContextPath()%>/settings/securities-account/add" class="btn btn-lg btn-light" title="新增"><i class="fa fa-plus"></i></a>
															</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="bean" items="${securitiesAccountList}" varStatus="status">
															<tr>
																<td>${status.count + (currentPage - 1) * pageRowCount}</td>
																<td class="align-middle">${bean.sa_SecuritiesBrokerBranchEntity.sb_SecuritiesBrokerHeadEntity.sh_name}證券</td>
																<td class="align-middle">${bean.sa_SecuritiesBrokerBranchEntity.sb_name}分公司</td>
																<td class="align-middle">${bean.sa_SecuritiesBrokerBranchEntity.sb_SecuritiesBrokerHeadEntity.sh_no}${bean.sa_SecuritiesBrokerBranchEntity.sb_no}-${bean.sa_no}</td>
																<td class="align-middle">
																	<c:if test="${not empty bean.sa_discount && bean.sa_discount != 0}">
																		<c:choose>
																			<c:when test="${bean.sa_discount >= 10}">
																				<fmt:formatNumber type="number" var="sa_discount" value="${bean.sa_discount%10 == 0 ? bean.sa_discount/10 : bean.sa_discount}" maxFractionDigits="0" />${sa_discount} 折
																			</c:when>
																			<c:otherwise>
																				${bean.sa_discount/10} 折
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																</td>
																<td class="align-middle">${bean.sa_count}</td>
																<td class="align-middle text-right">
																	<a href="<%=request.getContextPath()%>/settings/securities-account/edit?sa_id=${bean.sa_id}" class="btn btn-lg btn-light" title="編輯"><i class="fa fa-edit"></i></a>
																	<a href="#" class="btn btn-lg btn-light" title="刪除"><i class="fa fa-trash-o"></i></a>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<!-- /.table-responsive -->
											<!-- pagination -->
											<jsp:include page="../pagination.jsp" />
											<!-- /pagination -->
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