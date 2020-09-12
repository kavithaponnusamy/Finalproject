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
<!--  <td><img src="http://image.tmdb.org/t/p/original${property.rdc_web_url}" /></td>-->
<!-- <td><a href="${property.rdc_web_url}"></a>
<td><img src="http://image.tmdb.org/t/p/original${property.photo_count}" /> -->
<td><c:out value="${property.property_id}"/></td>
<td><a href="/submit-details?propertyId=${property.property_id}">${property.address.line}</a></td>
<tr>
</c:forEach>
</table>

</body>
</html>