<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="cart-popup__item-detail">
	<p class="cart-popup__item-title">${product.name}</p>
	<span class="cart-popup__item-number">Item #: ${product.code}</span>
	<div class="cart-popup__item-title cart-popup__item-aboprice">
	    <span class="product-list__item-abovalue new-product-list__item-abovalue">${product.price.formattedValue}</span>
	    <span>ABO Price:</span>
	</div>
	<div class="cart-popup__item-retailprice">
	    <span>Retail Price:</span>
	    <span class="product-list__item-abovalue Retail-product-list__item-abovalue">${product.retailPrice.formattedValue}</span>
	    <span class="view-box-divider">|</span>
	    <span>PV / BV:</span>
	    <span class="product-list__item-abovalue PV-product-list__item-abovalue">${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span>
	</div>
	<div class="cart-popup__quantity cart-popup__item-retailprice">
	    <div class="amway-theme qty-selector js-qty-selector control-group">
	        <div class="row">
	            <div class="size-selector-container">
	                <label class="control-label" for="pdpAddtoCartInput">Size</label>
	                <select class="size-select text-center js-qty-selector-input form-control">
	                    <option>60 Packets</option>
	                    <option>30 Packets</option>
	                </select>
	            </div>
	            <div class="qty-selector-container">
	                <label class="control-label" for="pdpAddtoCartInput">Qty</label>
	                <input type="text" maxlength="3" class="text-center js-qty-selector-input" size="1" value="1" data-max="FORCE_IN_STOCK" data-min="1" name="pdpAddtoCartInput" id="pdpAddtoCartInput">
	                <product:quickViewStockStatusSection stockLevelStatus="${product.stock.stockLevelStatus}"/>
	            </div>
	            <c:url value="${product.url}" var="productUrl"/>
	            <a href="${productUrl}"><div class="view-more-details">View More Details</div></a>
	        </div>
	    </div>
	</div>
</div>