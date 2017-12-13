<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ attribute name="navigationNode" required="true"	type="de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel"%>

<c:url var="categoryLandingPageUrl" value="${navigationNode.nodeLink.url}"/>
<div class="overlay-menu-tab-row">
	<div class="overlay-menu-tab-categories clearfix row">
		<c:forEach items="${navigationNode.children}" var="grandChildrenNode" varStatus="loop">
			<div class="col-sm-3 tab__category">
				<c:url var="childLinkUrl" value="${grandChildrenNode.nodeLink.url}" />
				<div class="category-top-link" onclick="javascript:location.href='${childLinkUrl}'">
					<h6>${grandChildrenNode.nodeLink.linkName}</h6>
				</div>
				<!-- <cms:component component="${grandChildrenNode.nodeLink}" element="span" class="category-top-link"/> -->
				<ul class="category-links">
					<c:forEach items="${grandChildrenNode.links}" var="grandChildrenLinks">
						<cms:component component="${grandChildrenLinks}" evaluateRestriction="true" element="li" />
					</c:forEach>
				</ul>
				<c:if test="${loop.index==0}">
					<a href="${categoryLandingPageUrl}" class="overlay-tab-title-bottom">
						<spring:theme code="text.header.nav.morecategories" />
					</a>
				</c:if>
			</div>
		</c:forEach>
	</div>
</div>