<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>Grade Checker</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo.png">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<script src="${pageContext.request.contextPath}/js/script.js" defer></script>
</head>
<body>

	<header>
		<div class="container">
			<h1><a href="${pageContext.request.contextPath}/top">Grade Checker</a></h1>
			<c:if test="${sessionScope.user_id != null && sessionScope.password != null}">
				<form class="header_btn" action="${pageContext.request.contextPath}/logout" method="POST">
					<button type="submit" class="button logout">Logout</button>
				</form>
			</c:if>
		</div>
	</header>