<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData" %>
<%@ attribute name="index" required="false" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/responsive/grid" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order" %>


<%--
    Represents single cart item on cart page
 --%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="errorStatus" value="<%= de.hybris.platform.catalog.enums.ProductInfoStatus.valueOf(\"ERROR\") %>" />
<c:set var="entryNumber" value="${entry.entryNumber}"/>
<c:if test="${empty index}">
    <c:set property="index" value="${entryNumber}"/>
</c:if>

<li class="product-list-item  js-shopping-cart-list-item">
    <div class="col-xs-12 col-md-5 product-details print-col-6">
        <div class="product-item-element list-item-toggle">
            <label class="checkbox-element-wrapper">
                <input class="shopping-cart-entry-checkbox _checkbox-element-global-class" value="" data-product-code="" type="checkbox">
                <span class="_checkbox-element-global-span"></span>
            </label>
        </div>
        <div class="product-item-element list-item-image">
            <div>
                <a href="#">
                    <product:productPrimaryImage product="${entry.product}" format="thumbnail"/>
                </a>
            </div>
            <div class="list-item-remove">
                <div class="remove-item-btn js-remove-entry-button" id="removeEntry_1">
                    <span class="">Remove</span></div>
            </div>
        </div>
        <div class="product-item-element list-item-info">
            <span class="product-name">Nutrilite Men's Pack</span>
            <div class="product-code">Item #
                <span class="product-item-number">105480</span></div>
            <div class="product-category">Vitamins &amp; Supplements</div>
            <div class="product-stock">
                <div>
                    <span class="stock">
                        <span class="product-availability">
                            <span class="green">
                                <span class="icon icon-check-bold"></span>
                                <span class="text text-uppercase">In Stock</span></span>
                        </span>
                    </span>
                    <br>
                    <span class="pick-up product-availability"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-9 col-md-2 product-item-element list-item-quantity print-col-6">
        <form id="updateCartForm0" class="js-qty-form0" data-cart="" action="#" method="post">
            <input id="quantity_0" class="form-control js-update-entry-quantity-input quantity-value " name="quantity" value="1" size="1" type="text"></form>
    </div>
    <div class="col-xs-9 product-item-element list-item-ibo-price col-md-3 list-item-price print-col-6">
        <form id="sortForm1" name="sortForm1" method="get" action="#">
            <select id="sortOptions1" name="sort" class="form-control">
                <option disabled="">Sort by</option>
                <option value="name-asc-c" selected="selected">A Price | Choose</option>
                <option value="name-desc-c">Name (Z-A)</option>
                <option value="retail-price-asc-c">Price (Lowest To Highest)</option>
                <option value="retail-price-desc-c">Price (Highest To Lowest)</option>
                <option value="newest-asc-c">Newest First</option>
                <option value="newest-desc-c">Oldest First</option>
                <option value="category-asc-c">Category (A-Z)</option>
                <option value="category-desc-c">Category (Z-A)</option></select>
            <input name="q" value=":name-asc-c" type="hidden">
            <input name="pageType" value="CATEGORY" type="hidden">
            <input name="text" value="" type="hidden">
            <br>
            <input id="quantity_0" class="form-control js-update-entry-quantity-input quantity-value " name="quantity" value="$22.10" size="1" type="text"></form>
    </div>
    <div class="col-xs-9 product-item-element list-item-ibo-price col-md-1 list-item-price print-col-6">
        <span class="price-label">Price</span>
        <span class="value-wrapper">$34.10</span></div>
    <div class="col-xs-9 col-md-1 product-item-element list-item-total js-item-total  print-col-6">
        <span class="total-price-label">Total Price</span>
        <span class="value-wrapper">$26.39</span></div>
</li>
