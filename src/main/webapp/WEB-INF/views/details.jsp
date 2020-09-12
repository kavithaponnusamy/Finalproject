<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
	<h3>Property Details</h3>

	<c:forEach var="prop" items="${property}">
	

		<p>
			<img alt="no image" src="${prop.photos[0].href}" />
		</p>

		<p>
			Id:
			<c:out value="${prop.property_id}" />
		</p>
		<p>
			Type:
			<c:out value="${prop.prop_type}" />
		</p>
		<p>
			Status:
			<c:out value="${prop.prop_status}" />
		</p>
		<p>
			Price:
			<c:out value="${prop.price}" />
		</p>
		<p>
			Address:
			<c:out value="${prop.address.line}" />
		<p>
			Baths:
			<c:out value="${prop.baths}" />
	</c:forEach>


</body>
</html>