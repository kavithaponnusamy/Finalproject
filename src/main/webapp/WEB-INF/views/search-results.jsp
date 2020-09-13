<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<style>
.img-fav {
	position: relative;
}

.img-fav > a {
	position: absolute;
	background: white;
	bottom: 0px;
	right: 0;
}

.img_fav_a {
	position: absolute;
	background: white;
	top: 1em;
	left: 1em;
}
</style>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

</head>
<body>
	<h2>List of properties in ${city},${state}</h2>
	<div class="container">
		<form action="/search-result">
			<label for="minprice">Min Price</label> <select id="minprice"
				name="minprice">
				<option value="0">Any Price</option>
				<option value="90000">90K</option>
				<option value="180000">180K</option>
				<option value="250000">250K</option>
				<option value="350000">350K</option>
				<option value="450000">450K</option>
				<option value="500000">500K</option>
				<option value="600000">600K</option>

			</select> <label for="maxprice">Max Price</label> <select id="maxprice"
				name="maxprice">
				<option value="100000000">Any Price</option>
				<option value="150000">150K</option>
				<option value="300000">300K</option>
				<option value="450000">450K</option>
				<option value="600000">600K</option>
				<option value="800000">800K</option>
				<option value="900000">900K</option>
				<option value="1000000">1M</option>
			</select> <label for="beds">Beds</label> <select id="beds" name="beds">
				<option value="0">Any</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
			</select> <label for="baths">Baths</label> <select id="baths" name="baths">
				<option value="0">Any</option>
				<option value="1">1</option>
				<option value="1.5">1.5</option>
				<option value="2">2</option>
				<option value="2.5">2.5</option>
				<option value="3">3</option>
				<option value="3.5">3.5</option>
				<option value="4">4</option>
				<option value="4.5">4.5</option>
				<option value="5">5</option>
			</select>
			<button type="submit">Filter By</button>
			<input type="hidden" name="state" value="${state}"> <input
				type="hidden" name="city" value="${city}">
		</form>




		<div id="image-list" class="row">
			<c:forEach var="property" items="${properties}">


				<div class="card col-md-4">
					<div >
						<a href="/submit-details?propertyId=${property.property_id}">
							<img class="img-fav" style="width: 100%; height: 400px" alt="no image"
							src="${property.thumbnail}">
						</a> 
							<a class="img_fav_a" href="" data-toggle="tooltip" title="Add Favorite">
								<i class="fa fa-star-o" style="font-size: 30px"></i>	
							</a>

					</div>
					<div>
						<div class="float-left">
							<span>${property.beds} beds ${property.baths} bath </span><br>
							<span>Type: ${property.prop_type}</span><br> <span>Price:
								<fmt:formatNumber value="${property.price}" type="currency" />
							</span><br> <span>${property.address.line} </span><br>
							<p>${city},${state}${address.postal_code}</p>
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


</body>
</html>