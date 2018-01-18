<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<div class="collapse mini-cart-items-container js-mini-cart-items-container mini-cart-wrapper"
	id="shoppingcar-drop-content">
	<div class="arrow" style=""></div>
	<ul class="nav nav-tabs">
		<li class="active">
			<a data-toggle="tab" href="#miniShoppingCartPopupContentPC"><spring:theme code="text.header.minicart.shoppingcart" /></a>
		</li>
		<li>
			<a data-toggle="tab" href="#dittoPopupContentPC"><spring:theme code="text.header.minicart.ditto" /></a>
		</li>
	</ul>
	<div class="tab-content js-tab-content">
		<div id="miniShoppingCartPopupContentPC" class="tab-pane fade in active"></div>
		<header:miniCartDittoContent/>
	</div>
</div>
