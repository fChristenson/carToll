<%@page import="se.fidde.cartoll.war.util.constants.WarStringConstants"%>
<%@page import="se.fidde.cartoll.war.util.constants.RelativeUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() + RelativeUrl.CSS.toString()%>" type="text/css" rel="stylesheet" />
<title>${title}</title>
</head>
<body>
	<div class="container">
		<jsp:include page="<%= RelativeUrl.HEADER.toString() %>">
			<jsp:param value="${showStationHeaderText }${station.name }" name="headerText"/>
			<jsp:param value="${linkBack }" name="link"/>
			<jsp:param value="${backText }" name="linkText"/>
		</jsp:include>
		<div>
			<table>
				<thead>
					<th>${regNumberText}</th>
					<th>${vehicleTypeText}</th>
					<th>${ownerText}</th>
					<th>${profitText}</th>
					<th>${dateText}</th>
				</thead>
				<tbody>
					<c:forEach items="${passings}" var="passing">
						<tr>
							<td>${passing.vehicle.regNumber}</td>
							<td>${passing.vehicle.type}</td>
							<td><a href="<%= RelativeUrl.OWNER.toString()%>${passing.vehicle.owner.id}">${passing.vehicle.owner}</a></td>
							<td>${passing.cost}</td>
							<td>${passing.date }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>