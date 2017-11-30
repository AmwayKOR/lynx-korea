<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<c:set var="isUserAbo" value="false" />
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
</sec:authorize>

<header class="js-mainHeader main-header general-header <c:if test="${loginError}">userinfo-open</c:if>">
	<div class="amway-theme">
		<nav class="header-container">
			<div class="header-wrapper pos-relative">
				<c:choose>
					<c:when test="${isUserAbo}">
						<header:headerLoggedInAccountSectionPopUp />
					</c:when>
					<c:otherwise>
						<header:headerLoginFormPopUp />
					</c:otherwise>
				</c:choose>
				<header:headerMiniCartPopUp />
				<header:headerRightIconsSection />
				<div class="">
					<div class="nav-links-container">
						<ul class="nav-list clearfix"></ul>
					</div>
				</div>
			</div>
		</nav>
	</div>
	<div class="amway-theme">
		<nav class="print-hide amw-navigation-menu js-navigation--middle">
			<div class="navigation-menu-container">
				<header:headerMobileSection />
				<header:headerDesktopSection />
			</div>
		</nav>
	</div>
	<header:headerSearchResultSection />
</header>
<common:globalMessages />
<common:globalMessagesTemplates />
<cms:pageSlot position="BreadCrumb" var="component">
	<cms:component component="${component}" />
</cms:pageSlot>