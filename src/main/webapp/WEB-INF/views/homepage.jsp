<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="homepage">
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

			function() {
				//converting normal input text box to AutoComplete jquery control
				$("#search").autocomplete(
						{
							//setting minimum length to 4, so after user entering 4 letters only it will get the data from api
							minLength : 4,
							//setting the source for autocomplete, which expects 2 parameter 
							//request (which user enters), response (api response data)
							source : function(request, response) {
								//starting the spinner during the api call	
								$("#loadSpinner").addClass(
										'spinner-border text-primary');
								//Starting the ajax call        
								$.ajax({
									//api getmapping url
									url : "cityStateInfo",
									//Method GET
									method : "GET",
									//data: input parameters to api, which user enter text
									data : {
										cityText : request.term
									},
									//json type
									dataType : "json",
									//if success then we need to set the ApiresponseData to jquery autocomplete control response to display data 						 
									success : function(data) {
										//Setting the Jquery AutoComplete control response	 
										response(data);
										//Stopping the loading spinner as we got the response
										$("#loadSpinner").removeClass(
												'spinner-border text-primary');
									},
									error : function(err) {
										//Stopping the loading spinner as we got the response
										$("#loadSpinner").removeClass(
												'spinner-border text-primary');
										response(null);
										window.location = "/error";

									}
								});
							}
						});

			});
</script>


</head>
<body class="homepage">

	<%@include file="partials/header.jsp"%>
	<p class="message"><c:out value="${ message }" /></p>

	<div class="container-fluid">
		<form action="/search">
			<div class="ui-widget">
				City/State: <input type="text" id="search" name="search" required
					class="search" /> <span id="loadSpinner"></span>
				<button type="submit" class="btn btn-primary mb-2">Search</button>
			</div>
		</form>
		<br>
		<br>
		<div>
			<form action="/saveLifestyle">
				<fieldset>
					<legend>Select all that fit your lifestyle:</legend>
					<input id="kids" type="checkbox" ${kids=='kids'?'checked':'' }
						name="kids" value="kids" /> <label for="kids">Kids</label><br>
					<input id="pets" type="checkbox" ${pets=='pets'?'checked':'' }
						name="pets" value="pets" /> <label for="pets">Pets</label><br>
					<input id="active" type="checkbox"
						${active=='active'?'checked':'' } name="active" value="active" />
					<label for="active">Active</label><br> <input id="nightLife"
						type="checkbox" ${nightLife=='nightLife'?'checked':'' }
						name="nightLife" value="nightLife" /> <label for="nightLife">Night
						Life</label><br> <input id="publicTransit" type="checkbox"
						${publicTransit=='publicTransit'?'checked':'' }
						name="publicTransit" value="publicTransit" /> <label
						for="publicTransit">Public Transportation</label><br>
					<button type="submit" class="btn btn-primary mb-2">Save
						Selected</button>
				</fieldset>
			</form>
		</div>
	</div>





	<footer>


		<div>

			<p>© Copyright 2020 All rights reserved by 
			<img style="width:100px;"  src="Logo.png" class="float-right" class="logo"/> </p>

		</div>

	</footer>





</body>
</html>