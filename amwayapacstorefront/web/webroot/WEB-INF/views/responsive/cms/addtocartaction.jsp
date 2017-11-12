<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="${url}" var="addToCartUrl"/>
<c:url value="/cart" var="cartUrl"/>

<c:set var="outOfStock" value="${true}" />	
<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
	<c:set var="outOfStock" value="${false}" />
</c:if>

<spring:url value="${product.url}/configuratorPage/{/configuratorType}" var="configureProductUrl" htmlEscape="false">
    <spring:param name="configuratorType"  value="${configuratorType}"/>
</spring:url>

<form:form method="post" id="addToCartForm" class="add_to_cart_form" action="${addToCartUrl}">
	<input type="hidden" maxlength="3" size="1" id="qty" name="qty" class="qty js-qty-selector-input" value="1">
    <input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>
    <input type="hidden" id="cartUrl" value="${cartUrl}"/>

	<button type="submit" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6 <c:if test="${outOfStock}">out-of-stock</c:if>" disabled="disabled"><spring:theme code="basket.add.to.basket"/></button>
	<button type="button" id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="window.location.href='${cartUrl}'"><spring:theme code="checkout.checkout"/></button>
</form:form>

<div
	class="row add-to-cart-extra-actions col-xs-12 col-sm-12 col-md-12 js-add-to-actions-wrapper">
	<div
		class="add-button-wrap add-to-shopping-list-container new-add-to-shopping-list-container">
		<button type="button"
			class="btn btn-link btn-block js-add-list-shopping-button"
			data-toggle="dropdown" id="">
			<span class="icon-add-shopping-list"></span> <span
				class="shopping-list-button-text">Add To Shopping List</span>
		</button>
		<div class="cart-detail__dropdown-menu dropdown-menu" role="menu">
			<ul>
				<li><input class="" id="toSL1" name="toDitto" type="checkbox">
					<label class="cart-detail__addto-options" for="toSL1"></label><span>1</span>
				</li>
				<li><input class="" id="toSL2" name="toDitto" type="checkbox">
					<label class="cart-detail__addto-options" for="toSL2"></label><span>TEST2</span>

				</li>
				<li><input class="" id="toSL3" name="toDitto" type="checkbox">
					<label class="cart-detail__addto-options" for="toSL3"></label><span>test1</span>

				</li>
				<li><input class="" id="toSL4" name="toDitto" type="checkbox">
					<label class="cart-detail__addto-options" for="toSL4"></label><span>GG</span>

				</li>
				<li><input class="" id="toSL5" name="toDitto" type="checkbox">
					<label class="cart-detail__addto-options" for="toSL5"></label><span>GG8</span>

				</li>

			</ul>
			<div class="dropdown-menu-bottom">
				<button id="addToLIST" class="btn btn-primary">ADD TO LIST</button>
				<a href="javascript:void(0);" id="">Create New List</a>
			</div>
		</div>
	</div>
	<div class="add-button-wrap add-to-ditto-container">
		<button type="button"
			class="btn btn-link btn-block add-to-ditto js-add-to-ditto-button"
			data-toggle="dropdown">
			<span class="icon-add-to-ditto"></span>Add to DITTO
		</button>
		<div class="cart-detail__dropdown-menu dropdown-menu" role="menu">
			<ul>
				<li><input class="" id="toDitto1" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto1"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
				<li><input class="" id="toDitto2" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto2"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
				<li><input class="" id="toDitto3" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto3"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
				<li><input class="" id="toDitto4" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto4"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
				<li><input class="" id="toDitto5" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto5"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
				<li><input class="" id="toDitto6" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto6"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
				<li><input class="" id="toDitto7" name="toDitto"
					type="checkbox"> <label class="cart-detail__addto-options"
					for="toDitto7"></label><span>JeniferJones'First DITTO</span>
					<p>Created for Jenifer Jones</p></li>
			</ul>
			<div class="dropdown-menu-bottom">
				<button id="addToDitto" class="btn btn-primary">ADD TO
					DITTO</button>
				<a id="creatNewDitto" href="javascript:void(0);">Create New
					DITTO</a>
			</div>
		</div>
	</div>
</div>
<div
	class="row add-to-cart-extra-actions col-xs-12 col-sm-12 col-md-12 js-add-to-actions-wrapper link-line">
	<div>
		<a href=""> <img href="#"
			src="${themeResourcePath}/images/new-twitter2.png" alt="twitter"></a>
	</div>
	<div>
		<a href=""> <img href="#"
			src="${themeResourcePath}/images/new-f.png" alt="facebook"></a>
	</div>
	<div>
		<a href=""> <img href="#"
			src="${themeResourcePath}/images/new-line.png" alt="line"></a>
	</div>
	<div>
		<a href=""> <img href="#"
			src="${themeResourcePath}/images/new-tel.png" alt="tel"></a>
	</div>
	<div>
		<a href=""> <img href="#"
			src="${themeResourcePath}/images/new-talk.png" alt="talk"></a>
	</div>
</div>

