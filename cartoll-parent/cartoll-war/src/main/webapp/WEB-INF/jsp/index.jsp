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
			<jsp:param value="${welcomeMessage}" name="headerText"/>
		</jsp:include>
		<div>
			<a href="<%= RelativeUrl.ADD_PASSING.toString() %>">${addPassingText}</a>
			<a href="<%= RelativeUrl.REMOVE_ALL_PASSINGS.toString() %>">${removeAllPassingsText}</a>
			<label>${totalPassingsText} <span>${totalPassings}</span></label>
			<label>${totalProfitText} <span>${totalProfit}</span></label>
			<table>
				<thead>
					<th>${stationText}</th>
					<th>${numberOfPassingsText}</th>
					<th>${profitText}</th>
				</thead>
				<tbody>
					<c:forEach items="${stations}" var="station">
						<tr>
							<td><a href="<%= RelativeUrl.SHOW_STATION.toString() %>${station.id }">${station.name}</a></td>
							<td>${station.totalPassings }</td>
							<td>${station.totalCost }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>