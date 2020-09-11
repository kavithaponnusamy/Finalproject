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

<h2>List of properties</h2>
<table>
<tr><th>Property Id</th><th>Address</th><tr>

<c:forEach var="property" items="${properties}">
<tr>
<td><c:out value="${property.property_id}"/></td>
<td><c:out value="${property.address.line}"/></td>
<tr>
</c:forEach>
</table>
<table>
<tr><th>Name</th><th>Rating</th><tr>

<c:forEach var="place" items="${places}">
<tr>
<td><c:out value="${place.name}"/></td>
<td><c:out value="${place.rating}"/></td>
<tr>
</c:forEach>

</table>
</body>
</html>