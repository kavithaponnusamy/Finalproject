<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<h3>Property Details</h3>
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
										src="${photo.href}" alt="no image"> <a href=""
										class="img_fav_a" data-toggle="tooltip" title="Add Favorite">
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
						</div>
						<div class="float-right mt-1">
							<a href="/" class="border-primary btn btn-outline-primary">Contact
								Agent</a>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div id="divMapView" style="display:none;" class="border col-md">
				<strong>Map View</strong>
				<div>
					<img
						src="https://maps.googleapis.com/maps/api/staticmap?q=1570+Woodward+Ave+floor+3,+Detroit,+MI+48226&size=200x200&key=AIzaSyDfO7vC2hX5xSfjZ5RBgC3M95vLuw8nHj8" />
				</div>

			</div>


		</div>
	</div>

</body>
</html>