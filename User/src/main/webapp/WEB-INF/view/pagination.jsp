<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- .pagination -->
<c:if test="${pageCount > 1}">
	<ul class="pagination pagination-sm justify-content-center mt-4">
		<c:choose>
			<c:when test="${currentGroup eq 1}">
				<li class="page-item disabled"><a class="page-link">&laquo;&laquo;</a></li>
			</c:when>
			<c:when test="${currentGroup > 1}">
				<li class="page-item"><a href="<%=request.getContextPath()%>${servletPath}?page=${(currentGroup - 2) * groupRowCount + 1}" class="page-link" title="前 ${groupRowCount} 頁">&laquo;&laquo;</a></li>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${currentPage eq 1}">
				<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a></li>
			</c:when>
			<c:when test="${currentPage > 1}">
				<li class="page-item"><a href="<%=request.getContextPath()%>${servletPath}?page=${currentPage - 1}" class="page-link" title="上 1 頁">&laquo;</a></li>
			</c:when>
		</c:choose>
		<c:forEach begin="${currentGroupBegin}" end="${currentGroupEnd}" varStatus="status">
			<li id="page-${status.count + (currentGroup - 1) * groupRowCount}" class="page-item"><a href="<%=request.getContextPath()%>${servletPath}?page=${status.count + (currentGroup - 1) * groupRowCount}" class="page-link">${status.count + (currentGroup - 1) * groupRowCount}</a></li>
		</c:forEach>
		<c:choose>
			<c:when test="${currentPage < pageCount}">
				<li class="page-item"><a href="<%=request.getContextPath()%>${servletPath}?page=${currentPage + 1}" class="page-link" title="下 1 頁">&raquo;</a></li>
			</c:when>
			<c:when test="${currentPage eq pageCount}">
				<li class="page-item disabled"><a class="page-link">&raquo;</a></li>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${currentGroup < groupCount}">
				<li class="page-item"><a href="<%=request.getContextPath()%>${servletPath}?page=${currentGroup * groupRowCount + 1}" class="page-link" title="後 ${groupRowCount} 頁">&raquo;&raquo;</a></li>
			</c:when>
			<c:when test="${currentGroup eq groupCount}">
				<li class="page-item disabled"><a class="page-link">&raquo;&raquo;</a></li>
			</c:when>
		</c:choose>
	</ul>
</c:if>
<!-- /.pagination -->

<!-- load -->
<script src="<%=request.getContextPath()%>/js/pagination.js" type="text/javascript" charset="utf-8"></script>
