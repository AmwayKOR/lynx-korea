<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="quick-links-misc col-sm-4">
	<ul>
		<li>
			<p class="quick-links-title">RECEIVE NEWS &amp; OFFERS</p>
			<form class="form-inline">
				<input type="email" class="quick-links-email" placeholder="Email Address" />
				<button href="#" class="btn-blue-white">subscribe</button>
			</form>
		</li>
		<c:forEach items="${specialComponents}" var="quickLinkSection">
			<cms:component component="${quickLinkSection}" />
		</c:forEach>
	</ul>
</div>
