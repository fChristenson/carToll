<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="se.fidde.cartoll.war.util.constants.RelativeUrl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() + RelativeUrl.CSS.toString()%>" type="text/css" rel="stylesheet" />
<title>${title }</title>
</head>
<body>
	<div class="container">
		<jsp:include page="<%= RelativeUrl.HEADER.toString() %>">
			<jsp:param value="${addNewPassingHeaderText }" name="headerText"/>
			<jsp:param value="${linkBack }" name="link"/>
			<jsp:param value="${backText }" name="linkText"/>
		</jsp:include>
		<form:form method="POST" commandName="bean">
			<label>
				${vehicleText } 
				<form:select path="vehicleId">
					<c:forEach items="${vehicles }" var="vehicle">
						<form:option value="${vehicle.id }">
							${vehicle.displayName }
						</form:option>
					</c:forEach>
				</form:select>
			</label>
			<label>
				${stationText }
				<form:select path="stationId">
					<c:forEach items="${stations }" var="station">
						<form:option value="${station.id}">${station }</form:option>
					</c:forEach>
				</form:select>
			</label>
			<label>
				${timeText }${timeUnitText }
				<form:select path="hour">
					<c:forEach begin="0" end="23" var="val">
						<form:option value="${val }">${val }</form:option>
					</c:forEach>
				</form:select>
				<form:select path="minute">
					<c:forEach begin="00" end="59" var="val">
						<form:option value="${val }">${val }</form:option>
					</c:forEach>
				</form:select>
			</label>
			<input type="submit" value="${addText }">
		</form:form>
	</div>
</body>
</html>