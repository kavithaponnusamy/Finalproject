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
		<form class="form" action="/search-result">
		 
		<fieldset class="border p-2">
		<legend  class="w-auto">Filters:</legend>
		 
			<label for="minprice">Min Price</label> <select id="minprice"  
				name="minprice">
				<option ${ param.minprice==0?'selected':'' } value="0">Any Price</option>
				<option ${ param.minprice==90000?'selected':'' } value="90000">90K</option>
				<option ${ param.minprice==180000?'selected':'' } value="180000">180K</option>
				<option ${ param.minprice==250000?'selected':'' } value="250000">250K</option>
				<option ${ param.minprice==350000?'selected':'' } value="350000">350K</option>
				<option ${ param.minprice==450000?'selected':'' } value="450000">450K</option>
				<option ${ param.minprice==500000?'selected':'' } value="500000">500K</option>
				<option ${ param.minprice==600000?'selected':'' } value="600000">600K</option>

			</select> <label for="maxprice">Max Price</label> <select id="maxprice"
				name="maxprice">
				<option ${ param.maxprice==100000000?'selected':'' }  value="100000000">Any Price</option>
				<option ${ param.maxprice==150000?'selected':'' } value="150000">150K</option>
				<option ${ param.maxprice==300000?'selected':'' } value="300000">300K</option>
				<option ${ param.maxprice==450000?'selected':'' } value="450000">450K</option>
				<option ${ param.maxprice==600000?'selected':'' } value="600000">600K</option>
				<option ${ param.maxprice==800000?'selected':'' } value="800000">800K</option>
				<option ${ param.maxprice==900000?'selected':'' } value="900000">900K</option>
				<option ${ param.maxprice==1000000?'selected':'' } value="1000000">1M</option>
			</select> <label for="beds">Beds</label> <select id="beds" name="beds" >
				<option ${ param.beds==0?'selected':'' } value="0">Any</option>
				<option ${ param.beds==1?'selected':'' } value="1">1</option>
				<option ${ param.beds==2?'selected':'' } value="2">2</option>
				<option ${ param.beds==3?'selected':'' } value="3">3</option>
				<option ${ param.beds==4?'selected':'' } value="4">4</option>
				<option ${ param.beds==5?'selected':'' } value="5">5</option>
				<option ${ param.beds==6?'selected':'' } value="6">6</option>
			</select> <label for="baths">Baths</label> <select id="baths" name="baths">
				<option ${ param.baths==0?'selected':'' } value="0">Any</option>
				<option ${ param.baths==1?'selected':'' } value="1">1</option>
				<option ${ param.baths==1.5?'selected':'' } value="1.5">1.5</option>
				<option ${ param.baths==2?'selected':'' } value="2">2</option>
				<option ${ param.baths==2.5?'selected':'' } value="2.5">2.5</option>
				<option ${ param.baths==3?'selected':'' } value="3">3</option>
				<option ${ param.baths==3.5?'selected':'' } value="3.5">3.5</option>
				<option ${ param.baths==4?'selected':'' } value="4">4</option>
				<option ${ param.baths==4.5?'selected':'' } value="4.5">4.5</option>
				<option ${ param.baths==5?'selected':'' } value="5">5</option>
			</select>
			<button type="submit" class="btn btn-outline-primary">Filter By</button>
			<input type="hidden" name="state" value="${state}"> <input
				type="hidden" name="city" value="${city}">
			 
			
		</fieldset>
		 
		</form>

<br>


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