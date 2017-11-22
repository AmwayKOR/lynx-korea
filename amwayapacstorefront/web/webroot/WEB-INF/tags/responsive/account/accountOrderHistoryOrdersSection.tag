<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="orders" required="true" type="de.hybris.platform.commerceservices.search.pagedata.SearchPageData"%>
<%@ attribute name="category" required="true" type="java.lang.String"%>

<c:if test="${empty orders.results}">
    <div class="account-section-content content-empty amway-theme">
        <ycommerce:testId code="orderHistory_noOrders_label">
            
            <div class="no-orders-found-container">
                    <div class="no-orders-found-title-container">
                        <h3 class="no-orders-found-title"><spring:theme code="text.account.orderHistory.noOrders" arguments="${category}"/></h3>
                    </div>
                </div>
        </ycommerce:testId>
    </div>
</c:if>
