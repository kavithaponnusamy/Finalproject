<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Find Homes That Fit Your Lifestyle</h2>

<form class="form-inline" action="/details">

<table class="table">
			<tr>
				<th>Enter City</th>
				<td><input type="text" name="city" autofocus/></td>
				<th>State</th>
			<td>
				<select name= "state_code">
				<c:forEach items="${states}" var="states">
				<option value= "${states}" name="state_code">${states}</option>
				</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary mb-2">Search</button>
					</td>
		</table>
				
				</form>
			

<c:forEach var="property" items="${properties}">
<tr>
<td><c:out value="${property.property_id}"/></td>
<td><c:out value="${property.address.line}"/></td>
<tr>
</c:forEach>



</body>
</html>