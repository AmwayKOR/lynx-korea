<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>


<div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading4">
	<h4 class="panel-title">
		<a class="panel-toggle" role="button" data-toggle="collapse" data-parent="#mobile-menu-category-accordion"
			href="#navMenuMobCollapse4" aria-expanded="true" aria-controls="navMenuMobCollapse4">
			<div id="closeHead">
				<span class="title-element tab-image">
					<img src="${currentCountry.image.url}" alt="flag" />
				</span>
				<span class="title-element active-parent-icon">
					<span class="icon-chevron-left"></span>
				</span>
				<span class="title-element title-text">${currentCountry.linkName}</span>
				<span class="title-element accordion-icon-wrapper">
					<span class="pull-right icon-minus"></span>
				</span>
			</div>
			<div id="openHead">
				<span class="title-element title-text"><spring:theme code="text.mobilecountryselector.country" /></span>
			</div>
		</a>
	</h4>
</div>

<div id="navMenuMobCollapse4" class="panel-collapse collapse overlay-menu-mobile__panel__content" role="tabpanel"
	aria-labelledby="navMenuMobHeading4">
	<div class="panel-body">
		<div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
			<ul class="subcategory-list countryBox" id="navMenuMobCountry">
				<c:forEach items="${links}" var="component">
					<c:if test="${component.visible}">
						<cms:component component="${component}" evaluateRestriction="true" element="li" class="content-item" />
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>