<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div id="productLearningListTabs" class="owl-carousel">
	<c:forEach items="${responsiveBanners}" var="learningComponent">
		<div class="item">
			<div class="full-width-item second-option">
				<cms:component component="${learningComponent}" />
			</div>
		</div>
	</c:forEach>
</div>