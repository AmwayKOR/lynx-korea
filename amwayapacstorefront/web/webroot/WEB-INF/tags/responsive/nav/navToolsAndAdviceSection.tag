<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ attribute name="navigationNode" required="true"	type="de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel"%>

<div class="overlay-menu-tab-row overlay-menu-tab-tools">
	<h6>
		<spring:theme code="text.header.nav.toolsandadvice" />
	</h6>
	<ul class="tools-list">
		<li class=" tools-item">
			<c:forEach items="${navigationNode.toolAdviceLinks}" var="tooladviceLink" varStatus="linkLoop">
				<cms:component component="${tooladviceLink}" />
			</c:forEach>
		</li>
	</ul>
</div>
