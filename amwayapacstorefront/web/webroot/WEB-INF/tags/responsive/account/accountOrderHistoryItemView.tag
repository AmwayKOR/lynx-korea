<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account" %>
<%@ taglib prefix="order-view" tagdir="/WEB-INF/tags/responsive/order/view" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<span class="order-history-short-description js-automation-item-view-description">
    ${fn:escapeXml(itemViewDescription)}
</span>
<div class="order-products-container">
    <c:if test="${not empty order.entries}">
        <ul>
            <li class="order-product-header row hidden-sm">
                <div class="col-xs-5 details-row">
                        <span>
                            <spring:theme code="text.account.orderHistory.product.header.details"/>
                        </span>
                </div>
                <div class="col-xs-1">
                        <span class="js-automation-table-header-qty">
                            <spring:theme code="text.account.orderHistory.product.header.qty"/>
                        </span>
                </div>
                <div class="col-xs-2">
                        <span class="js-automation-table-header-your-price">
                            <spring:theme code="text.account.orderHistory.product.header.yourPrice"/>
                        </span>
                </div>
                <div class="col-xs-2">
                        <span class="js-automation-table-header-total-price">
                            <spring:theme code="text.account.orderHistory.product.header.totalPrice"/>
                        </span>
                </div>
                <div class="col-xs-2">
                    		<span class="js-automation-table-header-total-pvbv">
                                <spring:theme
                                        code="text.account.orderHistory.product.header.totalPVBV"/>
                            </span>
                   
                </div>
            </li>
            <c:forEach items="${order.entries}" var="entry" varStatus="loop">
                <account:orderCustomerEntryDetails orderEntry="${entry}" order="${order}"
                                                 itemIndex="${loop.index}" itemView="true"/>
                <input id="qty_${entry.product.code}" hidden="hidden" value="${entry.quantity}"/>
            </c:forEach>
        </ul>
    </c:if>
</div>
<account:accountOrderHistoryDetailsTotals orderData="${order}" showTax="true"/>