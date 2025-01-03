<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="container">
		<div class="display_title">
			<h2>Grade Graph</h2>
		</div>
		<c:choose>
			<c:when test="${empty grades}">
				<p class="empty_info">No grade info</p>
			</c:when>
			<c:otherwise>
				<div class="searchs">
					<div class="grade_search">
						<h3>Search:</h3>
						<select id="search_term">
							<option value="">-</option>
							<c:forEach var="term" items="${terms}">
								<option value="${term}">${term}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div id="canvas">
					<c:forEach var="grade" items="${grades}">
						<input type="text" value="${grade.term}">
						<input type="text" value="${grade.avgGpa}">
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
		
		<div class="link">
			<a href="${pageContext.request.contextPath}/top">&lt;&lt; Top</a>
		</div>
	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>
