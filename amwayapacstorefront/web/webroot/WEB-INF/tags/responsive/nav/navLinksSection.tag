<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>

<c:forEach items="${component.navigationNode.children}" var="childrenNode" varStatus="loop">
	<div role="tabpanel" class="tab-pane <c:if test="${loop.index==0}">active</c:if>" id="navMenuTab${loop.index}">
		<div class="overlay-menu-tab">
			<div class="overlay-menu-tab-first-col">
				<div class="overlay-menu__tab-wrapper">
					<nav:navLinksDetailSection navigationNode="${childrenNode}" />
					<nav:navShopByBrandsSection navigationNode="${childrenNode}" />
					<nav:navToolsAndAdviceSection navigationNode="${childrenNode}" />
				</div>
			</div>
			<div class=" col-sm-3 overlay-menu__tab__banner">
				<div class="banner__component banner">
					<a href="#">
						<img title="tab_banner.jpg" alt="tab_banner.jpg" src="./Checkout _ Lynx Site_files/tab-banner.jpg" />
					</a>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
