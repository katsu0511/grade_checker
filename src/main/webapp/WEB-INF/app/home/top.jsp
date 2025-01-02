<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="container">
		<div class="top">
			<div class="menus">
				<div class="menu">
					<a href="${pageContext.request.contextPath}/index/grade">Index Grade</a>
				</div>
				<div class="menu">
					<a href="${pageContext.request.contextPath}/graph/grade">Graph Grade</a>
				</div>
			</div>
		</div>
	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>
