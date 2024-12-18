<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="container">
		<div class="display_title">
			<h2>Add Grade</h2>
		</div>
		
		<form id="grade_form" action="${pageContext.request.contextPath}/add/grade" method="POST">
		
			<div class="tr">
				<label for="term" class="th">Term:</label>
				<div class="td">
					<select id="term" name="term">
						<option value="">-</option>
						<c:forEach var="term" items="${terms}">
							<option value="${term}">${term}</option>
						</c:forEach>
					</select>
					<p id="term_error" class="error">Choose term</p>
				</div>
			</div>
			
			<div class="tr">
				<label for="code" class="th">Course Code:</label>
				<div class="td">
					<input type="text" id="code" name="code" autocomplete="off" required>
					<p id="code_error1" class="error">Input course code</p>
					<p id="code_error2" class="error"></p>
				</div>
			</div>
			
			<div class="tr">
				<label for="name" class="th">Course Name:</label>
				<div class="td">
					<input type="text" id="name" name="name" autocomplete="off" required>
					<p id="name_error1" class="error">Input course name</p>
					<p id="name_error2" class="error"></p>
				</div>
			</div>
			
			<div class="tr">
				<label for="grade" class="th">Grade:</label>
				<div class="td">
					<select id="grade" name="grade">
						<option value="">-</option>
						<c:forEach var="mark" items="${marks}">
							<option value="${mark}">${mark}</option>
						</c:forEach>
					</select>
					<p id="grade_error" class="error">Choose grade</p>
				</div>
			</div>
			
			<div class="add_buttons">
				<input type="hidden" id="user_id" name="user_id" value="${userId}" required>
				<input type="submit" class="submit_btn button" name="submit" value="Add" form="grade_form">
			</div>
			
		</form>
	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>
