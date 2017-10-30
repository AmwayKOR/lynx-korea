<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<c:url var="removeProductUrl" value="/shopping-lists/remove-product" />
<ul class="shopping-cart-item-list js-shopping-cart-item-list">
		    <li class="visible-md visible-lg">
		        <div class="col-xs-12 list-header">
		            <div class="col-xs-6 list-item-info"><spring:theme code="shoppinglist.items.section.table.item.label" /></div>
		            <div class="col-xs-2 list-item-quantity"><spring:theme code="shoppinglist.items.section.table.qty.label" /></div>
		            <div class="col-xs-2 list-item-price"><spring:theme code="shoppinglist.items.section.table.totalprice.label" /></div>
		            <div class="col-xs-2 list-item-total"><spring:theme code="shoppinglist.items.section.table.totalpvbv.label" /></div>
				</div>
		    </li>
	<c:forEach items="${shoppingListData.entries}" var="shoppingListEntry" varStatus="loop">
			<c:url var="productUrl" value="${shoppingListEntry.product.url}" />
		    <li class="product-list-item js-shopping-cart-list-item">
		        <div class="col-xs-12 col-md-6 product-details print-col-6">
		            <div class="product-item-element list-item-toggle">
		                <label class="checkbox-element-wrapper">
		                    <input class="shopping-cart-entry-checkbox _checkbox-element-global-class" value="" data-product-code="${shoppingListEntry.product.code}" type="checkbox">
		                </label>
		            </div>
		            <div class="product-item-element list-item-image">
		                <div>
		                    <a href="${productUrl}">
		                    	<product:productPrimaryImage product="${shoppingListEntry.product}" format="cartIcon"/>
		                     </a>
		                </div>
		                <div class="list-item-remove">
		                    <div class="remove-item-btn js-remove-entry-button" id="removeEntry_${loop.index}">
		                        <span class="remove-product-from-shopping-list" data-shopping-list-uid="${shoppingListData.uid}" data-product-code="${shoppingListEntry.product.code}" data-remove-url="${removeProductUrl}"><spring:theme code="shoppinglist.items.section.table.item.remove.label" /></span></div>
		                </div>
		            </div>
		            <div class="product-item-element list-item-info">
		                <a href="${productUrl}"><span class="product-name">${shoppingListEntry.product.name}</span></a>
		                <div class="product-code"><spring:theme code="shoppinglist.items.section.table.item.itemnumber.label" />
		                    <span class="product-item-number">${shoppingListEntry.product.code}</span></div>
		                <c:if test="${(not empty shoppingListEntry.product.categories) and (fn:length(shoppingListEntry.product.categories) > 0)}">
		                	<div class="product-category">${shoppingListEntry.product.categories[0].name}</div>
		                </c:if>
						<c:if test="${not empty shoppingListEntry.product.stock.stockLevelStatus.code}">
			                <div class="product-stock">
			                    <div>
			                        <span class="stock">
			                            <span class="product-availability">
			                                <span class="green">
				                                    <span class="icon icon-check-bold"></span>
				                                    <span class="text text-uppercase">In Stock</span>
											</span>
			                            </span>
			                        </span>
			                        <br>
			                        <span class="pick-up product-availability"></span>
			                    </div>
			                </div>
						</c:if>
		            </div>
		        </div>
		        <div class="col-xs-9 col-md-2 product-item-element list-item-quantity print-col-6">
		            <form id="updateCartForm0" class="js-qty-form0" data-cart="" action="#" method="post">
		                <input id="quantity_0" class="form-control js-update-entry-quantity-input quantity-value " name="quantity" value="1" size="1" type="text"></form>
		            <span class="usage-cal"><spring:theme code="shoppinglist.items.section.table.item.usagecalculator.label" /></span>
		        </div>
		        <div class="col-xs-9 product-item-element list-item-ibo-price col-md-2 list-item-price print-col-6">
		            <span class="price-label"><spring:theme code="shoppinglist.items.section.table.item.price.label" /></span>
		            <span class="value-wrapper"><format:price priceData="${shoppingListEntry.product.price}"/></span></div>
		            
		        
		        <div class="col-xs-9 col-md-2 product-item-element list-item-total js-item-total  print-col-6">
		            <span class="total-price-label"><spring:theme code="shoppinglist.items.section.table.item.pvbv.label" /></span>
		            <span class="value-wrapper">${shoppingListEntry.product.price.amwayValue.pointValue}/${shoppingListEntry.product.price.amwayValue.businessVolume}</span></div>
		    </li>
    </c:forEach>
</ul>