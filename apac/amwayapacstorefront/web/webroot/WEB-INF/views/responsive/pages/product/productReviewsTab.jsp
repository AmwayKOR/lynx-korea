<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="accordion-panel-heading" role="tab" id="tabreview">
    <div class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#productPageAccordion" href="#reviewsbody" aria-controls="bundleBody">
        <h5 class="accordion-panel-title">
            <span class="accordion-title-wrapper">
                <span class="title-element accordion-header-icon"></span>
                <span class="title-element title-text"><spring:theme code="review.reviews" /></span>
                <span class="title-element accordion-icon-wrapper"></span>
            </span>
        </h5>
    </div>
</div>
<div id="reviewsbody" class="accordion-panel-collapse collapse">
	<div class="panel-body">
        <product:productPageReviewsGraph />
    	<product:productPageReviewsTab product="${product}" />
    </div>
</div>