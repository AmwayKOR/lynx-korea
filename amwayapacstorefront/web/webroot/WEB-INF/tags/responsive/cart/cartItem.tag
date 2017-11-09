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
    <div class="col-xs-12 col-md-6 product-details print-col-6">
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
            <form:form id="cartEntryActionForm" action="" method="post" />
             <%-- Build entry numbers string for execute action -- Start --%>
            <c:choose>
                <c:when test="${entry.entryNumber eq -1}"> <%-- for multid entry --%>
                    <c:forEach items="${entry.entries}" var="subEntry" varStatus="stat">
                        <c:set var="actionFormEntryNumbers" value="${stat.first ? '' : actionFormEntryNumbers.concat(';')}${subEntry.entryNumber}" />
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:set var="actionFormEntryNumbers" value="${entry.entryNumber}" />
                </c:otherwise>
            </c:choose>
            <%-- Build entry numbers string for execute action -- End --%>
            <div class="list-item-remove">
                <div class="remove-item-btn js-remove-entry-button" id="removeEntry_1">
                     <c:forEach var="entryAction" items="${entry.supportedActions}">
                        <c:url value="/cart/entry/execute/${entryAction}" var="entryActionUrl"/>
                        <span class="js-execute-entry-action-button" id="actionEntry_${fn:escapeXml(entryNumber)}"
                              data-entry-action-url="${entryActionUrl}"
                              data-entry-action="${fn:escapeXml(entryAction)}"
                              data-entry-product-code="${fn:escapeXml(entry.product.code)}"
                              data-entry-initial-quantity="${entry.quantity}"
                              data-action-entry-numbers="${actionFormEntryNumbers}">
                              <a href="#"><spring:theme code="cart.groups.remove"/></a>
                        </span>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="product-item-element list-item-info">
            <span class="product-name">${entry.product.name}</span>
            <div class="product-code"><spring:theme code="basket.page.itemNumber" />
                <span class="product-item-number">${entry.product.code}</span></div>
            <div class="product-category">${entry.product.categories[0].name}</div>
            <product:productStockStatusDisplay product="${entry.product}" />
        </div>
    </div>
    <div class="col-xs-9 col-md-2 product-item-element list-item-quantity print-col-6">
        <c:url value="/cart/update" var="cartUpdateFormAction" />
        <form:form id="updateCartForm${entryNumber}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.entryNumber}"
                   class="js-qty-form${entryNumber}"
                    data-cart='{"cartCode" : "${fn:escapeXml(cartData.code)}","productPostPrice":"${entry.basePrice.value}","productName":"${fn:escapeXml(entry.product.name)}"}'>
            <input type="hidden" name="entryNumber" value="${entry.entryNumber}"/>
            <input type="hidden" name="productCode" value="${fn:escapeXml(entry.product.code)}"/>
            <input type="hidden" name="initialQuantity" value="${entry.quantity}"/>
            <ycommerce:testId code="cart_product_quantity">
                <form:label cssClass="visible-xs visible-sm" path="quantity" for="quantity${entry.entryNumber}"></form:label>
                <form:input cssClass="form-control js-update-entry-quantity-input quantity-value" disabled="${not entry.updateable}" type="text" size="1" id="quantity_${entryNumber}" path="quantity" />
            </ycommerce:testId>
        </form:form>
    </div>
    <div class="col-xs-9 product-item-element list-item-ibo-price col-md-2 list-item-price print-col-6">
        <span class="value-wrapper"><format:price priceData="${entry.basePrice}" /></span></div>
    <div class="col-xs-9 col-md-2 product-item-element list-item-total js-item-total  print-col-6">
        <span class="value-wrapper"><format:price priceData="${entry.totalPrice}" /></span></div>
</li>
