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
</head>
<body>
<h2>List of properties in ${city},${state}</h2>
<form action="/search-result">
			<label for="minprice">Min Price</label> 
				<select id="minprice" name="minprice">
				<option value= "0">Any Price</option>	
				<option value= "90000">90K</option>
				<option value= "180000">180K</option>		
				<option value= "250000">250K</option>		
				<option value= "350000">350K</option>		
				<option value= "450000">450K</option>
				<option value= "500000">500K</option>	
				<option value= "600000">600K</option>	
													
				</select>
				<label for="maxprice">Max Price</label> 
				<select id="maxprice" name="maxprice">
				<option value= "100000000">Any Price</option>	
				<option value= "150000">150K</option>
				<option value= "300000">300K</option>		
				<option value= "450000">450K</option>		
				<option value= "600000">600K</option>		
				<option value= "800000">800K</option>
				<option value= "900000">900K</option>	
				<option value= "1000000">1M</option>										
				</select>
				
				<label for="beds">Beds</label>
				<select id="beds" name="beds">
				<option value= "0">Any</option>	
				<option value= "1">1</option>	
				<option value= "2">2</option>
				<option value= "3">3</option>
				<option value= "4">4</option>
				<option value= "5">5</option>
				<option value= "6">6</option>
				</select>
				<label for="baths">Baths</label> 
				<select id="baths" name="baths">
				<option value= "0">Any</option>	
				<option value= "1">1</option>	
				<option value= "1.5">1.5</option>
				<option value= "2">2</option>
				<option value= "2.5">2.5</option>
				<option value= "3">3</option>
				<option value= "3.5">3.5</option>
				<option value= "4">4</option>
				<option value= "4.5">4.5</option>
				<option value= "5">5</option>
				</select>				
				<button type="submit">Filter By</button>				
			<input type="hidden" name="state" value="${state}">
			<input type="hidden" name="city" value="${city}">
</form>
<br>
<div class="container">
<div class="row">
<c:forEach var="property" items="${properties}">
<div class="col-sm">
<a href="/submit-details?propertyId=${property.property_id}">
<img src="${property.thumbnail}" />
</a>
<br>
${property.beds} beds ${property.baths} bath
<br>
${property.address.line},
${city}, ${state} ${address.postal_code}
<br>
Price: ${property.price}
</div>
<br>
</c:forEach>

</div>
</div>
</body>
</html>