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

<form class="form-inline" action="/submit-list">

<table class="table">
			<tr>
				<th>Enter City</th>
				<td><input type="text" name="city" autofocus/></td>
				<th>State</th>
			<td>
				<select name= "states">
				<c:forEach items="${states}" var="states">
				<option value= "${states}">${states}</option>
				</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary mb-2">Search</button>
					</td>
		</table>
				
				</form>
			

</body>
</html>