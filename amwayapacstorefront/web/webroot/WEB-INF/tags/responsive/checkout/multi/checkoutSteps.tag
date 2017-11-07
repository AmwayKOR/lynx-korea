<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="checkoutSteps" required="true" type="java.util.List" %>
<%@ attribute name="progressBarId" required="true" type="java.lang.String" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<ul class="checkout-steps">
	<c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="loopStatus">
		<li class="checkout-step col-xs-6 col-md-4 <c:if test="${progressBarId eq checkoutStep.progressBarId}">checkout-steps-active</c:if>">
			<span class="checkout-steps-circle<c:if test="${progressBarId eq checkoutStep.progressBarId}">active</c:if>">${loopStatus.count}</span>
			<span class="checkout-steps-text"><spring:theme code="checkout.multi.${checkoutStep.progressBarId}"/></span>
		</li>
	</c:forEach>
</ul>

