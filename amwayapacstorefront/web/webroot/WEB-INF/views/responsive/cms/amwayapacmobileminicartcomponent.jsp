<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/cart/rollover/{/componentUid}" var="miniCartUrl" htmlEscape="false">
	<spring:param name="componentUid" value="${component.uid}" />
</spring:url>

<spring:url value="/cart/miniCart/{/totalDisplay}" var="miniCartItemUrl" htmlEscape="false">
	<spring:param name="totalDisplay" value="${totalDisplay}" />
</spring:url>

<c:set var="isUserAbo" value="false" />
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
</sec:authorize>

<div class=" miniCartSlot componentContainer mobile-header-cell mobile-cart-container">
	<div class="">
		<div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">
			<c:choose>
				<c:when test="${isUserAbo}">
					<a href="#" class="mini-cart-link js-mini-cart-link clearfix is-abo" data-mini-cart-url="${miniCartUrl}"
						data-mini-cart-item-url="${miniCartItemUrl}">
						<div class="mini-cart-icon">
							<span class="nav-items-total"></span>
							<span class="icon-shopping-cart"></span>
						</div>
						<div class="cart-icon-wrapper">
							<div class="mini-cart-arrow"></div>
						</div>
					</a>
				</c:when>
				<c:otherwise>
					<a href="#" class="mini-cart-link js-mini-cart-link clearfix " data-mini-cart-url="" data-mini-cart-refresh-url=""
						data-mini-cart-name="Shopping Cart" data-mini-cart-empty-name="Empty Cart">
						<div class="mini-cart-icon ">
							<span class="icon-shopping-cart"></span>
						</div>
					</a>
				</c:otherwise>
			</c:choose>
			<!--remove min-cart-->
		</div>
		<div class="mini-cart-container js-mini-cart-container"></div>
	</div>
</div>