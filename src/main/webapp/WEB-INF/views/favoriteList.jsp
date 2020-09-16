<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>Favorite List</title>



<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

</head>
<body>
<%@include file="partials/header.jsp" %>
	<p class="message"><c:out value="${ message }"/></p>

	<h2>Favorite List</h2>
	<div class="container-fluid">

		<br>
		<div id="image-list" class="row">
			<c:forEach var="property" items="${properties}">
				<div class="card col-md-4">
					<div>
						<a href="/submit-details?propertyId=${property.propertyId}">
							<img class="img-fav" style="width: 100%; height: 400px"
							alt="no image" src="${property.thumbnail}">
						</a> <a class="img_fav_a"
							href="/removeFavorites?id=${property.id}"
							data-toggle="tooltip" title="Remove Favorite"> <i
							class="fa fa-star-o" style=" background-color:red; font-size: 30px"></i>
						</a>

					</div>
					<div>
						<div class="float-left">
							
						</div>
						<div class="float-right mt-1">
							<a href="/" class="btn btn-outline-primary">Contact Agent</a>
						</div>
					</div>

				</div>
			</c:forEach>
		</div>
	</div>
	<br>
	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer>

</body>
</html>