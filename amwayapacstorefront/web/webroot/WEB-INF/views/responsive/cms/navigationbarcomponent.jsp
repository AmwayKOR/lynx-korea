<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>

<c:set value="${component.styleClass} ${dropDownLayout}" var="bannerClasses" />

<li role="presentation" class=" nav-list-item js-remove-overlay-mobile-menu">
	<c:choose>
		<c:when test="${fn:length(component.navigationNode.children) > 1}">
			<div class="overlay-menu-component-desktop-wrapper">
				<a role="button" data-toggle="collapse" href="#overlay-menu-wrapper" aria-expanded="false"
					aria-controls="overlay-menu-wrapper" class="overlay-menu-toggle-desktop js-overlay-menu-toggle-desktop collapsed">
					<span class="hamburger-icon icon-Icon-Hamburger-01"></span>
					<span class="button-text">${component.navigationNode.nodeLink.linkName}
						<i class="main-menu__arrow-down glyphicon glyphicon-menu-down"></i>
					</span>
				</a>
				<div id="overlay-menu-wrapper" class="collapse hidden-sm overlay-menu-container">
					<nav:navBarOverlay />
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<cms:component component="${component.navigationNode.nodeLink}" />
		</c:otherwise>
	</c:choose>
</li>
