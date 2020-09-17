<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
</head>
<body>
<%@include file="partials/header.jsp" %>


  <form action="/contact-submit" class="form-container" method="post">
 <h3>Find out more about this property</h3>
  <label for="name"><b>Name</b></label>
    <input type="text" name="name" value="${name}" required>
    <br>
     <label for="email"><b>Email</b></label>
    <input type="text" name="email" value="${email}" required>
     <br>
     <label for="phoneno"><b>Phoneno</b></label>
     <input type="text" name="phoneno" value="${phoneno}" required>	
      <br>
       <label for="comments"><b>Comments</b></label>
      <textarea id="comment" name="comment"  value="${existingBI.comments}" rows="4" cols="50"></textarea>
     <label for="comments"><b>Comments</b></label>
   <!--   <input type="text" name="comments" value="${existingBI.comments}" required> -->
    <br>
     <label for="comments"><b>Quote</b></label>
    <input type="number" placeholder="Enter Quote" name="quote" value="${existingBI.quote}" required>    
    <button type="submit" class="btn">Submit</button>   
    <input type="hidden" name="propertyId" value="${propertyId}">
     <input type="hidden" name="userId" value="${userId}">

    
  </form>

<!--	<footer>
		<div>
			<p>© Copyright 2020 All rights reserved by</p>
		</div>
	</footer> -->
	<div class="footer">
		<p>© Copyright 2020 All rights reserved by</p>
	</div>
	
</body>
</html>