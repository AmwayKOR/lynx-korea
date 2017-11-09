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
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<button id="countrySelectorDropdown" class="btn small dropdown-toggle"
	type="button" data-toggle="dropdown" aria-haspopup="true"
	aria-expanded="true">
	<div class="wrapper">
		<span class="flag-icon flag-icon-US"> <img class="topbar__flag"
			src="${currentCountry.image.url}" alt="flag" /></span> <span
			class="current-country-code">${currentCountry.linkName}</span> <span
			class="icon icon-arrow-dropdown"> <i
			class="location-arrow-down glyphicon glyphicon-menu-down"></i>
		</span>
	</div>
</button>
<ul class="dropdown-menu countryBox"
	aria-labelledby="countrySelectorDropdown">
	<c:forEach items="${countryLinks}" var="component">
		<c:if test="${component.visible}">
			<cms:component component="${component}" evaluateRestriction="true" element="li" class="content-item" />
		</c:if>
	</c:forEach>
</ul>
