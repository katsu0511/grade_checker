<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="container">
		<div class="display_title">
			<h2>Grade Index</h2>
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
				<div class="display">
					<div class="display_thead">
						<div class="display_th term">Term</div>
						<div class="display_th code">Code</div>
						<div class="display_th course">Course</div>
						<div class="display_th grade">Grade</div>
						<div class="display_th grade">GPA</div>
					</div>
					<c:forEach var="grade" items="${grades}">
						<div class="display_tbody">
							<div class="display_td term">${grade.term}</div>
							<div class="display_td code">${grade.code}</div>
							<div class="display_td course">${grade.course}</div>
							<div class="display_td grade">${grade.grade}</div>
							<div class="display_td gpa">${grade.gpa}</div>
						</div>
					</c:forEach>
					<div class="display_bottom">
						<div class="display_total">Total GPA</div>
						<div id="display_gpa" class="display_gpa">${avgGpa}</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="add_grade">
			<a href="${pageContext.request.contextPath}/add/grade" class="button">Add Grade</a>
		</div>
		
		<div class="link">
			<a href="${pageContext.request.contextPath}/top">&lt;&lt; Top</a>
		</div>
	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>
