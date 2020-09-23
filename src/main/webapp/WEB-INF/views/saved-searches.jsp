<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class = "newpage">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
</head>
<body class = "newpage">

<%@include file="partials/header.jsp" %>
	<h1>Saved Searches</h1>
	<div  class="container-fluid">
			<table class="table"> 
			<thead>
				<tr>
					<th>Search Name</th>
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
<br>
	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer>


</body>
</html>