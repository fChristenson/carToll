<%@page import="se.fidde.cartoll.war.util.constants.RelativeUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() + RelativeUrl.CSS.toString()%>" type="text/css" rel="stylesheet" />
<title>${title }</title>
</head>
<body>
	<jsp:include page="<%= RelativeUrl.HEADER.toString() %>">
		<jsp:param value="${ownerHeaderText }${owner.firstName } ${owner.lastName }" name="headerText"/>
		<jsp:param value="${linkBack }" name="link"/>
		<jsp:param value="${backText }" name="linkText"/>
	</jsp:include>	
	<div>
		<div>
		<ul>
			<li>${owner.street }</li>
			<li>${owner.zipcode } ${owner.city }</li>
		</ul>
		</div>
		<table>
			<thead>
				<th>${vehicleText}</th>
				<th>${vehicleTypeText}</th>
				<th>${numberOfPassingsText}</th>
				<th>${costText}</th>
			</thead>
			<tbody>
				<c:forEach items="${vehicles}" var="vehicle">
					<tr>
						<td>${vehicle.name}</td>
						<td>${vehicle.type }</td>
						<td>${vehicle.totalPassings }</td>
						<td>${vehicle.totalCost }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</div>
</body>
</html>