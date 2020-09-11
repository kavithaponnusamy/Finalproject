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
<h3>Welcome to realtor</h3>
<form method="post">	
	<select name="state_code">
	
					<c:forEach var="statecode" items="${properties.address.state_code}">

						<option value="${address.state_code}">${address.state_code}
					<!-- 	<c:out value="${genre.name}" /> -->
						</option>
						</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-warning">Submit</button>
						</form>
						<br><br>

</body>
</html>