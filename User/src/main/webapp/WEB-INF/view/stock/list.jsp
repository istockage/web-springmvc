<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>股票交易明細 - iStockage</title>
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
									<li class="breadcrumb-item active">交易明細</li>
								</ol>
							</nav>
							<div class="d-md-flex align-items-md-start">
								<h1 class="page-title mr-sm-auto">股票交易明細</h1>
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
												<table id="stock-list-table" class="table table-sm mb-0">
													<thead class="thead-">
														<tr>
															<th>代號</th>
															<th>股票</th>
															<th>買進時間</th>
															<th>價格</th>
															<th>股數</th>
															<th>折扣</th>
															<th>手續費</th>
															<th>交割金額</th>
															<th>買出時間</th>
															<th>價格</th>
															<th>仟股</th>
															<th>折扣</th>
															<th>手續費</th>
															<th>證交稅</th>
															<th>交割金額</th>
															<th>漲幅</th>
															<th>淨利</th>
															<th>盈虧</th>
															<th class="text-right">
																<a href="<%=request.getContextPath()%>/stock/list/add" class="btn btn-sm btn-light" title="新增"><i class="fa fa-plus"></i></a>
															</th>
														</tr>
													</thead>
													<tbody style="font-size:13px">
														<c:forEach var="bean" items="${stockList}" varStatus="status">
															<tr>
																<td>${bean.st_no}</td>
																<td>${bean.st_name}</td>
																<fmt:formatDate type="date" var="st_buy_time" value="${bean.st_buy_time}" pattern="yy/MM/dd" />
																<td>${st_buy_time}</td>
																<fmt:formatNumber type="currency" var="st_buy_price" value="${bean.st_buy_price}" pattern=".00" />
																<td style="text-align:right">${st_buy_price}</td>
																<fmt:formatNumber type="currency" var="st_buy_share" value="${bean.st_buy_share}" pattern="#,#00" />
																<td style="text-align:right">${st_buy_share}</td>
																<td style="text-align:right"><c:if test="${not empty bean.st_buy_discount && bean.st_buy_discount != 0}">${bean.st_buy_discount}%</c:if></td>
																<fmt:formatNumber type="currency" var="st_buy_fee" value="${bean.st_buy_fee}" pattern="#,#00" />
																<td style="text-align:right">${st_buy_fee}</td>
																<fmt:formatNumber type="currency" var="st_buy_delivery" value="${bean.st_buy_delivery}" pattern="#,#00" />
																<td style="text-align:right">${st_buy_delivery}</td>
																<fmt:formatDate type="date" var="st_sell_time" value="${bean.st_sell_time}" pattern="yy/MM/dd" />
																<td>${st_sell_time}</td>
																<fmt:formatNumber type="currency" var="st_sell_price" value="${bean.st_sell_price}" pattern=".00" />
																<td style="text-align:right">${st_sell_price}</td>
																<fmt:formatNumber type="currency" var="st_sell_share" value="${bean.st_sell_share}" pattern="#,#00" />
																<td style="text-align:right">${st_sell_share}</td>
																<td style="text-align:right"><c:if test="${not empty bean.st_sell_discount && bean.st_sell_discount != 0}">${bean.st_sell_discount}%</c:if></td>
																<fmt:formatNumber type="currency" var="st_sell_fee" value="${bean.st_sell_fee}" pattern="#,#00" />
																<td style="text-align:right">${st_sell_fee}</td>
																<fmt:formatNumber type="currency" var="st_sell_tax" value="${bean.st_sell_tax}" pattern="#,#00" />
																<td style="text-align:right">${st_sell_tax}</td>
																<fmt:formatNumber type="currency" var="st_sell_delivery" value="${bean.st_sell_delivery}" pattern="#,#00" />
																<td style="text-align:right">${st_sell_delivery}</td>
																<fmt:formatNumber type="currency" var="st_percent" value="${bean.st_sell_price/bean.st_buy_price*100}" pattern=".00" />
																<td style="text-align:right">${st_percent}%</td>
																<fmt:formatNumber type="currency" var="st_surplus" value="${bean.st_sell_delivery - bean.st_buy_delivery}" pattern="#,#00" />
																<td style="text-align:right">${st_surplus}</td>
																<td style="text-align:right">9937</td>
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