<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="/style.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script
	src="/script.js"></script>
</head>
<body>
<%@include file="partials/header.jsp" %>
	<p class="message"><c:out value="${ message }"/></p>


	<h3>Property Details</h3>
	<a href="<c:url value="${searchUrl}"/>" class="btn btn-secondary">Back to Results</a>
	<div class="container-fluid">
	
		<div class="btn-group float-right">
			<button class="btn btn-primary" value="Details" onClick="onBtnDetailViewClick();">Details</button>
			<button id="btnMapView" class="btn btn-secondary" onClick="onBtnMapViewClick();">Maps</button>
		</div>
	
		<br>
		<br>

		<div class="row">

			<div class="border col-md">

				<c:forEach var="prop" items="${property}">


					<!-- Image section -->

					<div id="myCarousel" class="carousel slide" data-ride="carousel">


						<!-- Indicators -->
						<ol class="carousel-indicators">

							<c:forEach var="photo" items="${prop.photos}">
								<c:set var="cnt" value="${cnt+1}" />
								<li data-target="#myCarousel" data-slide-to="${cnt}"
									class="${cnt==1?'active':''}"></li>
							</c:forEach>
						</ol>


						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<c:forEach var="photo" items="${prop.photos}">
								<c:set var="cnts" value="${cnts+1}" />
								<div class="${cnts==1? 'item active':'item'}">
									<img class="img-fav" style="height: 400px; width: 100%;"
										src="${photo.href}" alt="no image"> 
										<a href="/addFavorites?propertyId=${prop.property_id}&thumbnail=${prop.photos[0].href}&weburl=${prop.weburl}"
										class="img_fav_details_a" data-toggle="tooltip" title="Add Favorite">
										<i class="fa fa-star-o" style="font-size:30px"></i>
									</a>
								</div>
							</c:forEach>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#myCarousel"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#myCarousel"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>


					</div>
					<!-- End Image section -->

					<br>

					<div>
						<div class="float-left">

							<p>
								Id:
								<c:out value="${prop.property_id}" />
							</p>
							<p>
								Type:
								<c:out value="${prop.prop_type}" />
							</p>
							<p>
								Status:
								<c:out value="${prop.prop_status}" />
							</p>
							<p>
								Price:
								<c:out value="${prop.price}" />
							</p>
							<p>
								Address:
								<c:out value="${prop.address.line}" />					
						
							<p>
								Baths:
								<c:out value="${prop.baths}" />
								<p>
								Beds:
								<c:out value="${prop.beds}" />
							 
						</div>
						<div class="float-right mt-1">
							<a href="/contact-submit?propertyId=${prop.property_id}" class="border-primary btn btn-outline-primary">Contact
								Agent</a>

						
						</div>
					</div>
				</c:forEach>
			</div>
			

			<div id="divMapView" style="display:block;" class="border col-md">
				<strong>Map View</strong>
			  
				<div>

			<img src="https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lon}&zoom=12&size=500x500&markers=color:green%7Clabel:.%7C${lat},${lon}&${sMarkers}&${gMarkers}&key=${key}"/>
		
			</div>


			</div>


		</div>
	</div>

<h2>Nearby locations</h2>
<table>
	<tr>
		<td style="vertical-align:top" >
			<h3>Super Markets</h3>
			<table>
				<tr><th>Name</th><th>Rating</th></tr>
				<c:forEach var="supermarket" items="${supermarkets}">
					<tr>	<td> <c:out value="${supermarket.name}" /> </td>
							<td> <c:out value="${supermarket.rating}" /> </td>
					</tr>
				</c:forEach>
			</table>
		</td>

		<td style="vertical-align:top">
			<h3>Gyms</h3>
			<table>
				<tr><th>Name</th><th>Rating</th></tr>
				<c:forEach var="gym" items="${gyms}">
					<tr>	<td> <c:out value="${gym.name}" /> </td>
							<td> <c:out value="${gym.rating}" /> </td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
<br>
<br>
<br>
	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer>
	

</body>
</html>