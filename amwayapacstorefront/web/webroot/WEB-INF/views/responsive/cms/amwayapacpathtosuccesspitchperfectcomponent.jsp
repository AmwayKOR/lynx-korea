<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="item-image-wrap">
	<cms:component component="${banner}" />
</div>
<div class="item-content-wrap">
	<h2 class="banner-title">
		<span>${title}</span>
	</h2>
	<h4 class="banner-sub-title">${title2}</h4>
	<div class="sub-title-wrap banner-sub-title">
		<cms:component component="${description}" />
	</div>
	<div class="banner-button-wrap">
		<div class="banner-button-link">
			<button class="btn-blue-white">
				<cms:component component="${link}" />
			</button>
		</div>
	</div>
</div>
