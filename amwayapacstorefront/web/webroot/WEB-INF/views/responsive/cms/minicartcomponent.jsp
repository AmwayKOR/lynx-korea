<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/cart/mini-cart/{/componentUid}" var="miniCartUrl" htmlEscape="false">
	<spring:param name="componentUid"  value="${component.uid}"/>
</spring:url>

<spring:url value="/cart/miniCart/{/totalDisplay}" var="miniCartItemUrl" htmlEscape="false">
	<spring:param name="totalDisplay"  value="${totalDisplay}"/>
</spring:url>

<c:set var="isUserAbo" value="false" />
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
</sec:authorize>

<c:choose>
	<c:when test="${isUserAbo}">
	    <li class="componentContainer liOffcanvas nav-item nav-mini-cart">
	        <span class="">
	            <div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">

	                <a href="javascript:void(0);"
	                   	class="pos-relative mini-cart-link js-mini-cart-link clearfix is-abo"
	                   	data-target="#shoppingcar-drop-content"
						data-mini-cart-url="${miniCartUrl}"
						data-mini-cart-item-url="${miniCartItemUrl}"
	                   >
	                    <div class="mini-cart-icon">
	                        <span class="icon-shopping-cart"></span>
	                    </div>
	                    <div class="mini-cart-count js-mini-cart-count">
	                        <span class="nav-items-total">0</span></div>
	                    <div class="cart-icon-wrapper">
	                        <div class="mini-cart-arrow"></div>
	                    </div>
	                </a>

	            </div>
	            <div class="mini-cart-container js-mini-cart-container"></div>
	        </span>
	    </li>
	</c:when>
	<c:otherwise>
        <li class="componentContainer liOffcanvas nav-item nav-mini-cart">
			<span class="">
			    <div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">
			        <a href="#" class="pos-relative mini-cart-link clearfix"
			        	data-target="#shoppingcar-drop-content">
			            <div class="mini-cart-icon">
			                <span class="icon-shopping-cart"></span>
			            </div>
			            <div class="cart-icon-wrapper">
			                <div class="mini-cart-arrow"></div>
			            </div>
			        </a>

			    </div>
			    <div class="mini-cart-container js-mini-cart-container"></div>
			</span>
        </li>
	</c:otherwise>
</c:choose>