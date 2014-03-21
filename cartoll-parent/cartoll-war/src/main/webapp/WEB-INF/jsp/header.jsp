<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<c:if test="${not empty param.link}">
		<a href="${param.link}">${param.linkText}</a>
	</c:if>	
	<h1>${param.headerText}</h1>
</header>