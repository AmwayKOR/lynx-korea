<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ attribute name="navigationNode" required="true"	type="de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel"%>

<div class="overlay-menu-tab-row overlay-menu-tab-brands">
	<div class="">
		<h6>
			<spring:theme code="text.header.nav.shopbybrands" />
		</h6>
		<div class="row images-container">
			<c:forEach items="${navigationNode.brandBanners}" var="banner" varStatus="loop">
				<div class=" col-md-3">
					<cms:component component="${banner}" element="div" class="banner__component banner" />
				</div>
			</c:forEach>
		</div>
	</div>
</div>