<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<h3>Property Details</h3>
	<div>


		<div class="col-md-4">
			<a href=""><i class="fa fa-star-o" style="font-size: 18px"></i>
				Add Favorite</a> <br>

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
								<img style="height: 400px; width: 100%;" src="${photo.href}"
									alt="no image">
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
			</c:forEach>
		</div>
		<div class="col-md-4">
			<strong>Map View</strong>
			<div>
				<img
					src="https://maps.googleapis.com/maps/api/staticmap?q=1570+Woodward+Ave+floor+3,+Detroit,+MI+48226&size=200x200" />
			</div>

		</div>
		<div class="col-md-4">
			<strong>Agent form</strong>

			<div class="form">
				<div>
					FullName : <input type="text" name="fullname" />
				</div>
				<div>
					Phone : <input type="text" name="phone" />
				</div>
				<div>
					Email : <input type="text" name="email" />
				</div>
				<div>
					AgentId : <input type="text" name="agentid" />
				</div>
				<div>
					Quote : <input type="text" name="quote" />
				</div>
				<div>
					Comments :
					<textarea cols="20" name="quote"></textarea>
				</div>
				<div>
					<input type="button" value="Submit"></input>
				</div>
			</div>
		</div>

	</div>

</body>
</html>