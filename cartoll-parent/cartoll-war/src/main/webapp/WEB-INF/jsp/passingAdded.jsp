<%@page import="se.fidde.cartoll.war.util.constants.WarStringConstants"%>
<%@page import="se.fidde.cartoll.war.util.constants.RelativeUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() + RelativeUrl.CSS.toString()%>" type="text/css" rel="stylesheet" />
<title>${title }</title>
</head>
<body>
	<jsp:include page="<%= RelativeUrl.HEADER.toString() %>">
		<jsp:param value="${passingAddedHeaderText }" name="headerText"/>
	</jsp:include>
	<div>
		<table>
			<tr>
				<td><span>${idText}</span></td>
				<td>${passing.id}</td>
			</tr>
			<tr>
				<td><span>${vehicleText}</span></td>
				<td>${passing.vehicle.displayName}</td>
			</tr>
			<tr>
				<td><span>${stationText}</span></td>
				<td>${passing.station}</td>
			</tr>
			<tr>
				<td><span>${timeText}</span></td>
				<td>${passing.date}</td>
			</tr>
			<tr>
				<td><span>${costText}</span></td>
				<td>${passing.cost}</td>
			</tr>
		</table>
	</div>
	<footer>
		<a href="<%=RelativeUrl.INDEX_HTML.toString()%>">${startPageLinkText}</a> <a href="<%=RelativeUrl.ADD_PASSING.toString() %>">${addNewPassingLinkText}</a>
	</footer>
</body>
</html>