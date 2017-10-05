<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="simpleimagecomponent pcp-prod row">
	<c:forEach items="${shopByCategoryComponents}" var="shopByCategoryComponent">
		<cms:component component="${shopByCategoryComponent}" />
	</c:forEach>
</div>