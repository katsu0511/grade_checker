<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../common/header.jsp" flush="true"/>
<% String userId = (String)request.getAttribute("user_id"); %>
<% String password = (String)request.getAttribute("password"); %>

<main>
	<div class="container">
		<h2>Login</h2>
	
		<form class="login_form" action="<%= request.getContextPath() %>/login" method="POST">
			<div class="login_items">

				<div class="login_item">
					<label for="user_id" class="login_label">User ID :</label>
					<div class="login_input">
						<input type="text" id="user_id" class="login_info" name="user_id" value="<% if (userId != null) { %><%= userId %><% } %>">
					</div>
				</div>

				<div class="login_item">
					<label for="password" class="login_label">Password :</label>
					<div class="login_input">
						<input type="password" id="password" class="login_info" name="password" value="<% if (password != null) { %><%= password %><% } %>">
					</div>
				</div>

				<% if (request.getAttribute("errorMessage") != null && request.getAttribute("errorMessage") != "") { %>
					<p class="error_message"><%= request.getAttribute("errorMessage") %></p>
				<% } %>

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
