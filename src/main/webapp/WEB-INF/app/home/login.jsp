<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="container">
		<h2>Login</h2>
	
		<form class="login_form" action="${pageContext.request.contextPath}/login" method="POST">
			<div class="login_items">

				<div class="login_item">
					<label for="user_id" class="login_label">User ID :</label>
					<div class="login_input">
						<input type="text" id="user_id" class="login_info" name="user_id" value="<c:if test="${userId != null}">${user_name}</c:if>">
					</div>
				</div>

				<div class="login_item">
					<label for="password" class="login_label">Password :</label>
					<div class="login_input">
						<input type="password" id="password" class="login_info" name="password" value="<c:if test="${password != null}">${password}</c:if>">
					</div>
				</div>

				<c:if test="${errorMessage != null && errorMessage != ''}">
					<p class="error_message">${errorMessage}</p>
				</c:if>

				<div class="login_item">
					<div class="login_submit">
						<input type="submit" class="button login" name="login_btn" value="Login">
					</div>
				</div>

			</div>
		</form>

	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>
