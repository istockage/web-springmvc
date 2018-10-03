<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${userPathEntity.up_name} - iStockage</title>
<%@include file="head.jsp"%>
<!-- react -->
<script src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<script src="https://npmcdn.com/babel-core@5.8.38/browser.min.js"></script>
</head>
<body>
	<!-- .app -->
	<div class="app">
	
		<!-- header -->
		<jsp:include page="header.jsp" />
		<!-- /header -->
		
		<!-- aside -->
		<%@include file="aside.jsp"%>
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
									<li class="breadcrumb-item"><a href="#"></a></li>
									<li class="breadcrumb-item active"></li>
								</ol>
							</nav>
							<div class="d-md-flex align-items-md-start">
								<h1 class="page-title mr-sm-auto">${userPathEntity.up_name}</h1>
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
										
											<div id="hello-world"></div>
											
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
			<%@include file="footer.jsp"%>
			<!-- /footer -->
			
		</main>
		<!-- /.app-main -->
		
		<!-- /main -->
		
	</div>
	<!-- /.app -->

<%@include file="body.jsp"%>
<script src="<%=request.getContextPath()%>/jsx/HelloWorld.jsx" type="text/babel"></script>
</body>
</html>