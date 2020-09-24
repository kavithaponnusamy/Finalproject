<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="homepage">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signup</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
</head>
<body class="homepage">
	
	<h1>Sign up</h1>

	<%@include file="partials/header.jsp"%>
	<p class="message"><c:out value="${ message }" /></p>

	<div class="container-fluid">
		<form action="signup" method="post">

			<p>
				<label for="username">Username:</label> <input id="username"
					name="username" value="<c:out value="${ param.username }"/>"
					required minlength="2" />
			</p>
			<p>
				<label for="password">Password:</label> <input id="password"
					type="password" name="password" required minlength="2" />
			</p>
			<p>
				<label for="password2">Confirm Password:</label> <input
					id="password2" type="password" name="confirm-password" required
					minlength="2" />
			</p>
			<p>
				<label for="name">Email:</label> <input id="email" name="email"
					value="<c:out value="${ param.email }"/>" required minlength="2"
					autocomplete="off" />
			</p>
			<p>
				<label for="name">phone:</label> <input id="phone" name="phone"
					type="text" value="<c:out value="${ param.phone }"/>"
					pattern="[2-9]{1}[0-9]{2}-[0-9]{3}-[0-9]{4}"
					placeholder="###-###-####" autocomplete="off" />
			</p>

			<p>
				<button>Sign me Up!</button>
			</p>
		</form>
	</div>
	<br>

	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer>
</body>
</html>