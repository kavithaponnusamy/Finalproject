<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
<script>

//Jquery standard document.ready functions
$(document).ready(
		
		function(){
			//converting normal input text box to AutoComplete jquery control
			$("#search").autocomplete({
				//setting minimum length to 4, so after user entering 4 letters only it will get the data from api
				 minLength : 4,
				//setting the source for autocomplete, which expects 2 parameter 
				//request (which user enters), response (api response data)
				 source:function(request,response)
				 {
					//starting the spinner during the api call	
					 $("#loadSpinner").addClass('spinner-border text-primary'); 				      
					//Starting the ajax call        
					 $.ajax({
						//api getmapping url
						 url:"cityStateInfo",
						//Method GET
						 method:"GET",
						//data: input parameters to api, which user enter text
						 data:{cityText : request.term},
						//json type
						 dataType:"json",
						//if success then we need to set the ApiresponseData to jquery autocomplete control response to display data 						 
						 success: function(data){
							//Setting the Jquery AutoComplete control response	 
							 response(data);
							//Stopping the loading spinner as we got the response
							 $("#loadSpinner").removeClass('spinner-border text-primary');
						 },
						 error: function(err){
							//Stopping the loading spinner as we got the response
							$("#loadSpinner").removeClass('spinner-border text-primary');
							 response(null);
							 window.location="/error";
							
						 }
					 });
				 }
			});
			
		});

</script>
</head>
<body>
<%@include file="partials/header.jsp" %>
	<p class="message"><c:out value="${ message }"/></p>

	<div class="container-fluid">
		<form action="/search">		
			<div class="search-container">
				<div class="ui-widget">
					City/State: <input type="text" id="search" name="search" required
						class="search" /> <span id="loadSpinner"></span>
					<button type="submit" class="btn btn-primary mb-2">Search</button>	
		</div>
		</form>	 
</div>

	<div class="container">
	<form action="/saveLifestyle">
	<fieldset>
	<legend>Select all that fit your lifestyle:</legend>
	<label>Kids</label>
	<input type="checkbox" name="kids" value="kids"/><br>
	<label>Pets</label>
	<input type="checkbox" name="pets" value="pets"/><br>
	<label>Active</label>
	<input type="checkbox" name="active" value="active"/><br>
	<label>Night Life</label>
	<input type="checkbox" name="nightLife" value="nightLife"/><br>
	<label>Public Transportation</label>
	<input type="checkbox" name="publicTransit" value="publicTransit"/><br>
	<button type="submit" class="btn btn-primary mb-2">Save Selected</button>
	</fieldset>
	</form>
	</div>
	<p></p>



	 <footer>


		<div>
			<p>© Copyright 2020 All rights reserved by 
			<img style="width:100px;"  src="logo.png" class="float-right" class="logo"/> </p>
		</div>

	</footer>

	
	


</body>
</html>