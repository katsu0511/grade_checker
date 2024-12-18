<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="container">
		<c:choose>
			<c:when test="${empty grades}">
				<p>No grade info</p>
				<div class="add_grade">
					<a href="${pageContext.request.contextPath}/add/grade" class="button">Add Grade</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="display_title">
					<h2>Grade Status</h2>
				</div>
				<div class="add_grade">
					<a href="${pageContext.request.contextPath}/add/grade" class="button">Add Grade</a>
				</div>
				<div class="searchs">
					<div class="search grade_search">
						<h3>Search:</h3>
						<select id="term">
							<option value="">-</option>
						</select>
					</div>
				</div>
				<div class="display">
					<div class="display_thead">
						<div class="display_th">Term</div>
						<div class="display_th">Code</div>
						<div class="display_th">Course</div>
						<div class="display_th">Grade</div>
					</div>
					<c:forEach var="grade" items="${grades}">
						<div class="display_tbody">
							<div class="display_td">${grade.term}</div>
							<div class="display_td">${grade.code}</div>
							<div class="display_td">${grade.course}</div>
							<div class="display_td">${grade.grade}</div>
						</div>
					</c:forEach>
				</div>
				<div class="add_grade">
					<a href="${pageContext.request.contextPath}/add/grade" class="button">Add Grade</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>
