<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<ul class="menu-nav-list print-hide">
	<cms:pageSlot position="NavigationBar" var="component">
		<cms:component component="${component}"/>
	</cms:pageSlot>
</ul>

