<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	<header class="header" id="myHeader" onclick="window.location = '/';">
		<h1>Find Homes That Fit Your Lifestyle</h1>
		<nav>
			<a href="/">Home</a> | <a href="/favorite-list">Favorites</a>
		</nav>
	</header>

	<div>
		<form class="form-inline" action="/submit-list">

			<div>
				Enter City <input type="text" name="city" autofocus /> State <select
					name="state">
					<c:forEach items="${states}" var="states">
						<option value="${states}">${states}</option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary mb-2">Search</button>
			</div>
			<br>
			<div>
			<h3>Saved Searches</h3>
			<table class="table"> 
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${searches}" var="searches">
					
					<tr>


					<td><a class = "nav-link" href="${searches.searchUrl}">${searches.name}</a></td>
				    <td><a href="<c:url value="/removeSearch?id=${searches.id}"/>" class="btn btn-dark">Delete</a></td>
             </tr>
					</c:forEach>
					</tbody>
					</table>
			</div>


		</form>
	</div>

	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer>


</body>
</html>