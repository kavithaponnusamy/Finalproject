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

<script src="/script.js"></script>
<script>
function distance(lat1, lon1, lat2, lon2) {
	if ((lat1 == lat2) && (lon1 == lon2)) {
		return 0;
	}
	else {
		var radlat1 = Math.PI * lat1/180;
		var radlat2 = Math.PI * lat2/180;
		var theta = lon1-lon2;
		var radtheta = Math.PI * theta/180;
		var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
		if (dist > 1) {
			dist = 1;
		}
		dist = Math.acos(dist);
		dist = dist * 180/Math.PI;
		dist = dist * 60 * 1.1515;
		return (dist.toFixed(1));
	}
}

let map; 

function initMap() {
	var lat=${lat};
	var lng=${lon};
 map = new google.maps.Map(document.getElementById("map"), {
  center: { lat: lat, lng: lng },
  zoom: 12
 }); 
	    
  
 addMaker(lat,lng,'House','H');

<c:forEach var="smarket" items="${supermarkets}">  
var smarketLat=${smarket.geometry.location.lat};
var smarketLng=${smarket.geometry.location.lng};
var dist=distance(lat,lng,smarketLat,smarketLng);
var smarketInfo='<h6>Name: <c:out value="${smarket.name}" /></h6><br><h6>Address: <c:out value="${smarket.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(smarketLat,smarketLng,'SuperMarket','SM','superMarket.png',smarketInfo);
</c:forEach>


<c:forEach var="restaurant" items="${restaurants}"> 
var restaurantLat=${restaurant.geometry.location.lat};
var restaurantLng=${restaurant.geometry.location.lng};
var dist=distance(lat,lng,restaurantLat,restaurantLng);
var restaurantInfo='<h6>Name: <c:out value="${restaurant.name}" /></h6><br><h6>Address: <c:out value="${restaurant.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(restaurantLat,restaurantLng,'Restaurent','R','restaurant.png',restaurantInfo);
</c:forEach>

<c:forEach var="gym" items="${gyms}"> 	
var gymLat=${gym.geometry.location.lat};
var gymLng=${gym.geometry.location.lng};
var dist=distance(lat,lng,gymLat,gymLng);
var gymInfo='<h6>Name: <c:out value="${gym.name}" /></h6><br><h6>Address: <c:out value="${gym.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(gymLat,gymLng,'Gym','G','gymicon.png',gymInfo );
</c:forEach> 
 
<c:forEach var="school" items="${schools}"> 	
var schoolLat=${school.geometry.location.lat};
var schoolLng=${school.geometry.location.lng};
var dist=distance(lat,lng,schoolLat,schoolLng);
var schoolInfo='<h6>Name: <c:out value="${school.name}" /></h6><br><h6>Address: <c:out value="${school.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(schoolLat,schoolLng,'School','S' ,'school.png',schoolInfo);
</c:forEach> 

<c:forEach var="petstore" items="${petstores}">
var petstoreLat=${petstore.geometry.location.lat};
var petstoreLng=${petstore.geometry.location.lng};
var dist=distance(lat,lng,petstoreLat,petstoreLng);
var petstoreInfo='<h6>Name: <c:out value="${petstore.name}" /></h6><br><h6>Address: <c:out value="${petstore.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(petstoreLat,petstoreLng,'PetStore','P','pet-store.png',petstoreInfo );
</c:forEach> 

<c:forEach var="bar" items="${bars}">
var barLat=${bar.geometry.location.lat};
var barLng=${bar.geometry.location.lng};
var dist=distance(lat,lng,barLat,barLng);
var barInfo='<h6>Name: <c:out value="${bar.name}" /></h6><br><h6>Address: <c:out value="${bar.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(barLat,barLng,'Bar','B','bar.png',barInfo);
</c:forEach> 

<c:forEach var="transit" items="${transits}">
var transitLat=${transit.geometry.location.lat};
var transitLng=${transit.geometry.location.lng};
var dist=distance(lat,lng,transitLat,transitLng);
var transitInfo='<h6>Name: <c:out value="${transit.name}" /></h6><br><h6>Address: <c:out value="${transit.vicinity}"/></h6><br><h6>Distance: '+dist+' miles</h6><br>';
addMaker(transitLat,transitLng,'Transport','T','transport.png',transitInfo);
</c:forEach> 
 
}

function addMaker(typelat, typelng,title, label,icon, popupInfo){
	
	var marker= new google.maps.Marker({
		  position: { lat:typelat , lng: typelng},
		  map: map,
		  title: title, 
		//  label: label,
		  icon: icon
		 });
	
	if(popupInfo){
		 
		let baseDirectionurl="https://www.google.com/maps/dir/?api=1&origin="+${lat}+","+${lon}+"&destination=";
		baseDirectionurl+=typelat+","+typelng; 
		 
		popupInfo+='<a target="_blank" href="'+baseDirectionurl+'">Get Directions</a>'
		var infoWindow=new google.maps.InfoWindow({
			content:popupInfo
		});
		
		
		marker.addListener('click', function(){
			infoWindow.open(marker.get('map'), marker);
			
		})
	
	
	}
	
} 

 </script>
</head>
<body>
	<%@include file="partials/header.jsp"%>


	<h3>Property Details</h3>
	
	<a href="<c:url value="${searchUrl}"/>"
		class="btn btn-secondary">Back to Results</a>

	
	<div class="container-fluid">
	<br>
	<form action="/updateLifestyle">
	<fieldset>
	<c:forEach var="prop" items="${property}">
	<legend><strong>Update Your Lifestyle Preferences:</strong></legend>
		<label>Kids</label>
	<input type="checkbox"  ${kids=='kids'?'checked':'' } name="kids" value="kids"/>
	<label>Pets</label>
	<input type="checkbox"  ${pets=='pets'?'checked':'' }  name="pets" value="pets"/>
	<label>Active</label>
	<input type="checkbox" ${active=='active'?'checked':'' } name="active" value="active"/>
	<label>Night Life</label>
	<input type="checkbox" ${nightLife=='nightLife'?'checked':'' } name="nightLife" value="nightLife"/>
	<label>Public Transportation</label>
	<input type="checkbox" ${publicTransit=='publicTransit'?'checked':'' }  name="publicTransit" value="publicTransit"/>
	<input type="hidden" name="propertyId" value="${prop.property_id}"/>
	<button type="submit" class="btn btn-primary mb-2">Update</button>
	</c:forEach>
	</fieldset>
	</form>

	
		<div class="btn-group float-right">
			<button class="btn btn-primary" value="Details"
				onClick="onBtnDetailViewClick();">Details</button>
			<button id="btnMapView" class="btn btn-secondary"
				onClick="onBtnMapViewClick();">Maps</button>
		</div>

		<br> <br>

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
										src="${photo.href}" alt="no image"> <a
										href="/addFavorites?propertyId=${prop.property_id}&thumbnail=${prop.photos[0].href}&weburl=${prop.weburl}"
										class="img_fav_details_a" data-toggle="tooltip"
										title="Add Favorite"> <i class="fa fa-star-o"
										style="font-size: 30px"></i>
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
								<strong>Id:</strong>
								<c:out value="${prop.property_id}" />
							</p>
							<p>
								<strong>Type:</strong>
								<c:out value="${prop.prop_type}" />
							</p>
							<p>
								<strong>Status:</strong>
								<c:out value="${prop.prop_status}" />
							</p>
							<p>

								<strong>Price: $</strong>

								<c:out value="${prop.price}" />
							</p>
							<p>
								<strong>Address:</strong>
								<c:out value="${prop.address.line}" />
							<p>
								<strong>Baths:</strong>
								<c:out value="${prop.baths}" />
							<p>
								<strong>Beds:</strong>
								<c:out value="${prop.beds}" />
						</div>
						<div class="float-right mt-1">
							<a href="/contact-submit?propertyId=${prop.property_id}"
								class="border-primary btn btn-outline-primary">Contact Agent</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<div id="divMapView" style="display: block;" class="border col-md">
			<table class = "key">
				<th>Map Key</th>
						<tr>
							<td><img src = "school.png">
							Schools &nbsp;&nbsp;</td>
							<td><img src = "pet-store.png"></td>
							<td> Pet Store &nbsp;&nbsp;</td>
							<td><img src = "gymicon.png"></td>
							<td> Gym &nbsp;&nbsp; </td>
							<td><img src = "restaurant.png"></td>
							<td> Restaurant &nbsp;&nbsp;</td>	
							<td><img src = "bar.png"></td>
							<td> Bar &nbsp;&nbsp;</td>
							<td><img src = "supermarket.png"></td>
							<td> Grocery Store &nbsp;&nbsp;</td>	
								<td><img src = "transport.png"></td>
							<td> Transit Station &nbsp;&nbsp;</td>
						</tr>
				</table>
				<div id="map" class="details-map"></div>
			</div>
		</div>
		<br>
		<br>
	</div>
		
	<h2>Nearby locations</h2>
	<table>
		<tr>
			<td style="vertical-align: top">
				<h3>Super Markets</h3>
				<table>
					<tr>
						<th>Name</th>
						<th>Rating</th>
						<th>Distance(miles)</th>
					</tr>
				<c:forEach var="supermarket" items="${supermarkets}">
						<tr>
							<td><c:out value="${supermarket.name}" /></td>
							<td><c:out value="${supermarket.rating}" /></td>
							<td id="sm${supermarket.place_id}"></td>					
						</tr>
						<script type="text/javascript">
							var smDistance=distance('${lat}','${lon}','${supermarket.geometry.location.lat}','${supermarket.geometry.location.lng}')
							document.getElementById('sm${supermarket.place_id}').innerHTML = smDistance;	
						</script>
					</c:forEach>
				</table>
			</td>

			<td style="vertical-align: top">
				<h3>Restaurants</h3>
				<table>
					<tr>
						<th>Name</th>
						<th>Rating</th>
						<th>Distance (miles)</th>
					</tr>
					<c:forEach var="restaurant" items="${restaurants}">
						<tr>
						    <td><c:out value="${restaurant.name}" /></td>
							<td><c:out value="${restaurant.rating}" /></td>
							<td id="res${restaurant.place_id}"></td>					
						</tr>
						<script type="text/javascript">
							var resDistance=distance('${lat}','${lon}','${restaurant.geometry.location.lat}','${restaurant.geometry.location.lng}')
							document.getElementById('res${restaurant.place_id}').innerHTML = resDistance;
						</script>
					</c:forEach>
				</table>
				</td>
				</table>
	
		
	<br>
	<br>
	<br>
	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=${key}&callback=initMap&libraries=&v=weekly"
		defer></script>


</body>
</html>