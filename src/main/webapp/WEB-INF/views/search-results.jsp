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
<tr><th>Property</th><tr>

<c:forEach var="property" items="${properties}">
<tr> 
 
<td>
<a href="/submit-details?propertyId=${property.property_id}">
<img src="${property.thumbnail}" />
</a>
<br>
${property.address.line}
</td>
<tr>
</c:forEach>
</table>

</body>
</html>