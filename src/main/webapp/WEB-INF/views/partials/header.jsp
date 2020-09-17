<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="header" >

<h2 style="text-align:center;">Find Homes That Fit Your Lifestyle</h2>
<div>
	<nav class="float-left">
		<a href="/">Home</a> 
		<c:if test = "${ not empty user }"> 
		 | <a href="/favorite-list">Favorites |</a> 
		   <a href="/saved-searches">Saved Searches</a> 
		</c:if>
	</nav>

	<span class="float-right"> <c:choose>
			<c:when test="${ not empty user }">
			Welcome <c:out value="${ user.username }" />
				<a href="/logout">Logout</a>
			</c:when>
			<c:otherwise>
				<a href="/login">Login</a>
				<a href="/signup">Sign Up</a>
			</c:otherwise>
		</c:choose>
	</span>
</div>
</header>