<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="errorStatus" value="<%= de.hybris.platform.catalog.enums.ProductInfoStatus.valueOf(\"ERROR\") %>" />

<ul class="shopping-cart-item-list js-shopping-cart-item-list shopping-cart-product-item-list">
    <li class="visible-md visible-lg">
        <div class="col-xs-12 list-header">
            <div class="col-xs-5 list-item-info">Item (style number)</div>
            <div class="col-xs-2 list-item-quantity">Qty</div>
            <div class="col-xs-3 list-item-set-price">set price</div>
            <div class="col-xs-1 list-item-price">Price</div>
            <div class="col-xs-1 list-item-total">Total</div></div>
    </li>
    <c:forEach items="${cartData.rootGroups}" var="group" varStatus="loop">
        <cart:rootEntryGroup cartData="${cartData}" entryGroup="${group}"/>
    </c:forEach>
</ul>
