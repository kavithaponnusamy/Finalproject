<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
</head>
<body>
<%@include file="partials/header.jsp" %>
	<p class="message"><c:out value="${ message }"/></p>
	<div class = "container">
		<form class="form-inline" action="/submit-list">
		<br>

			<div>
				Enter City <input type="text" name="city" autofocus /> State <select
					name="state">
					<c:forEach items="${states}" var="states">
						<option value="${states}">${states}</option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary mb-2">Search</button>
			</div>


		</form>
	</div>
	<div class="container">
	<form action="/saveLifestyle">
	<fieldset>
	<legend>Select all that fit your lifestyle:</legend>
	<label>Kids</label>
	<input type="checkbox" name="kids" value="kids"/><br>
	<label>Pets</label>
	<input type="checkbox" name="pets" value="pets"/><br>
	<label>Active</label>
	<input type="checkbox" name="active" value="active"/><br>
	<label>Night Life</label>
	<input type="checkbox" name="nightLife" value="nightLife"/><br>
	<label>Public Transportation</label>
	<input type="checkbox" name="publicTransit" value="publicTransit"/><br>
	<button type="submit" class="btn btn-primary mb-2">Save Selected</button>
	</fieldset>
	</form>
	</div>
	<p></p>

	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by 
			<img src="logo.png" class="float-right" class="logo"/> </p>
		</div>
	</footer>


</body>
</html>