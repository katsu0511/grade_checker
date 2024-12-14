<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>Grade Checker</title>
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	<script src="<%= request.getContextPath() %>/js/script.js" defer></script>
</head>
<body>

	<header>
		<div class="container">
			<h1><a href="<%= request.getContextPath() %>/top">Grade Checker</a></h1>
			<% if (session.getAttribute("user_id") != null && session.getAttribute("password") != null) { %>
				<form class="header_btn" action="<%= request.getContextPath() %>/logout" method="POST">
					<button type="submit" class="button logout">Logout</button>
				</form>
			<% } %>
		</div>
	</header>