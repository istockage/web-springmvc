<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
															<th>仟股</th>
															<th>折扣</th>
															<th>手續費</th>
															<th>交割金額</th>
															<th>買出時間</th>
															<th>價格</th>
															<th>仟股</th>
															<th>折扣</th>
															<th>手續費</th>
															<th>交易稅</th>
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
														<tr>
															<td>2317</td>
															<td>鴻海</td>
															<td>12/08/27</td>
															<td style="text-align:right">85</td>
															<td style="text-align:right">1</td>
															<td style="text-align:right">6</td>
															<td style="text-align:right">121</td>
															<td style="text-align:right">85121</td>
															<td>12/09/14</td>
															<td style="text-align:right">96.2</td>
															<td style="text-align:right">1</td>
															<td style="text-align:right">6</td>
															<td style="text-align:right">137</td>
															<td style="text-align:right">288</td>
															<td style="text-align:right">95775</td>
															<td style="text-align:right">13.18</td>
															<td style="text-align:right">10654</td>
															<td style="text-align:right">10654</td>
															<td class="align-middle text-right">
																<a href="#" class="btn btn-sm btn-light" title="編輯"><i class="fa fa-edit"></i></a>
																<a href="#" class="btn btn-sm btn-light" title="刪除"><i class="fa fa-trash-o"></i></a>
															</td>
														</tr>
														<tr>
															<td>3264</td>
															<td>欣銓</td>
															<td>12/09/17</td>
															<td style="text-align:right">20.8</td>
															<td style="text-align:right">1</td>
															<td style="text-align:right">6</td>
															<td style="text-align:right">29</td>
															<td style="text-align:right">20829</td>
															<td>12/09/27</td>
															<td style="text-align:right">20.2</td>
															<td style="text-align:right">1</td>
															<td style="text-align:right">6</td>
															<td style="text-align:right">28</td>
															<td style="text-align:right">60</td>
															<td style="text-align:right">20112</td>
															<td style="text-align:right">-2.88</td>
															<td style="text-align:right">717</td>
															<td style="text-align:right">9937</td>
															<td class="align-middle text-right">
																<a href="#" class="btn btn-sm btn-light" title="編輯"><i class="fa fa-edit"></i></a>
																<a href="#" class="btn btn-sm btn-light" title="刪除"><i class="fa fa-trash-o"></i></a>
															</td>
														</tr>
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
	
<%@include file="../body.jsp"%>
</body>
</html>