<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


  <form action="/contact-submit" class="form-container" method="post">
 <h3>Find out more about this property</h3>
	<label for="fullname"><b>Name</b></label>
	 <input type="text" placeholder="Enter Fullname" name="name" required>	
	 <br>
    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>
    <br>
    <label for="phone"><b>Phone Number</b></label>    
    <input type="number" placeholder="Enter Phone Number" name="phoneNo" required>
    <br>
     <label for="comments"><b>Comments</b></label>
    <input type="text" name="comments" required>
    <br>
     <label for="comments"><b>Quote</b></label>
    <input type="number" placeholder="Enter Quote" name="quote" required>    
    <button type="submit" class="btn">Submit</button>   
    <input type="hidden" name="propertyId" value="${propertyId}">
    
  </form>


</body>
</html>