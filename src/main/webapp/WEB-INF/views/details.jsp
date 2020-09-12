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
<h3>Property Details</h3>
<c:forEach var="prop" items="${property}">



<c:forEach var="property" items="${properties}">
<tr>
<td><c:out value="${property.property_id}"/></td>
<td><c:out value="${property.address.line}"/></td>
<tr>
</c:forEach>
</table>

<!--  <td><img src="http://image.tmdb.org/t/p/original${property.rdc_web_url}" /></td>-->
<!-- <td><a href="${property.rdc_web_url}"></a>
<td><img src="http://image.tmdb.org/t/p/original${property.photo_count}" /> -->
<p>Id: <c:out value="${prop.property_id}"/></p>
<p>Type: <c:out value="${prop.prop_type}"/></p>
<p>Status: <c:out value="${prop.prop_status}"/></p>
<p>Price: <c:out value="${prop.price}"/></p>
<p>Address: <c:out value="${prop.address.line}"/>
<p>Baths: <c:out value="${prop.baths}"/>

</c:forEach>



</body>
</html>