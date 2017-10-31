<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div id="productTabs">
	<div class="tabHead"><spring:theme code="product.product.details" /></div>
	<div class="tabBody"><product:productDetailsTab product="${product}"/></div>
	<div class="tabHead" id="tab-reviews"><spring:theme code="review.reviews" /></div>
	<div class="tabBody" ><product:productPageReviewsTab product="${product}"/></div>
	<cms:pageSlot position="Tabs" var="tabs">
		<cms:component component="${tabs}"/>
	</cms:pageSlot>
</div>