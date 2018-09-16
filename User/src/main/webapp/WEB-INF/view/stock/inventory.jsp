<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
									<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/stock/chart">股票</a></li>
									<li class="breadcrumb-item active">庫存明細</li>
								</ol>
							</nav>
							<div class="d-md-flex align-items-md-start">
								<h1 class="page-title mr-sm-auto">${pathName}</h1>
								<div class="btn-toolbar">
									<button type="button" class="btn btn-light">
										<i class="oi oi-data-transfer-download"></i>
										<span class="ml-1">下載</span>
									</button>
									<button type="button" class="btn btn-light">
										<i class="oi oi-data-transfer-upload"></i>
										<span class="ml-1">上傳</span>
									</button>
								</div>
							</div>
						</header>
						<!-- /.page-title-bar -->
						
						<!-- .page-section -->
						<div class="page-section">
							<!-- grid row -->
							<div class="row">
							
								<!-- grid column -->
								<div class="col-lg-12">
									
									<!-- .card -->
									<div class="card card-fluid">
										<!-- .card-body -->
										<div class="card-body">
											<!-- .table-responsive -->
											<div class="table-responsive">
												<table id="stock-inventory-table" class="table table-sm mb-0" style="font-size:.8rem">
													<thead class="thead-">
														<tr>
															<th>編號</th>
															<th>代號</th>
															<th>股票</th>
															<th>類別</th>
															<th>買進時間</th>
															<th>價格</th>
															<th>股數</th>
															<th>折扣</th>
															<th>手續費</th>
															<th>交割金</th>
															<th>賣出時間</th>
															<th>價格</th>
															<th>股數</th>
															<th>折扣</th>
															<th>手續費</th>
															<th>證交稅</th>
															<th>交割金</th>
															<th>漲幅</th>
															<th>淨利</th>
															<th>盈虧</th>
															<th class="text-right"><a href="<%=request.getContextPath()%>/stock/inventory/add" class="btn btn-sm btn-light" title="新增"><i class="fa fa-plus"></i></a></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="bean" items="${stockList}" varStatus="status">
															<tr>
																<td>${status.count + (currentPage - 1) * pageRowCount}</td>
																<td>${bean.st_no}</td>
																<td>${bean.st_name}</td>
																<td>${bean.st_CodeEntity.co_name}</td>
																<td><fmt:formatDate type="date" pattern="yy/MM/dd" value="${bean.st_buy_time}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern=".00" value="${bean.st_buy_price}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_buy_share}" /></td>
																<td style="text-align:right"><c:if test="${not empty bean.st_buy_discount && bean.st_buy_discount != 0}">${bean.st_buy_discount}%</c:if></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_buy_fee}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_buy_delivery}" /></td>
																<td id="st_sell_time"><fmt:formatDate type="date" pattern="yy/MM/dd" value="${bean.st_sell_time}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern=".00" value="${bean.st_sell_price}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_sell_share}" /></td>
																<td style="text-align:right"><c:if test="${not empty bean.st_sell_discount && bean.st_sell_discount != 0}">${bean.st_sell_discount}%</c:if></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_sell_fee}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_sell_tax}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_sell_delivery}" /></td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern=".00" value="${(bean.st_sell_price - bean.st_buy_price)/bean.st_buy_price * 100}" />%</td>
																<td style="text-align:right"><fmt:formatNumber type="currency" pattern="#,#00" value="${bean.st_sell_delivery - bean.st_buy_delivery}" /></td>
																<td style="text-align:right">10,757</td>
																<td class="align-middle text-right">
																	<a href="#" class="btn btn-sm btn-light" title="編輯"><i class="fa fa-edit"></i></a>
																	<a href="#" class="btn btn-sm btn-light" title="刪除"><i class="fa fa-trash-o"></i></a>
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