<%@ attribute name="orderData" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="amway-theme clearfix">
    <div class="table-content-rows order-history-summary">
        <div class="row header-row">
            <div class="col-xs-12">
                <div class="table-header clearfix">
                    <div class="col-xs-6 header-element">
                        <span class="header-text js-automation-header-summary"><spring:theme code="text.account.orderHistory.details.summary.orderSummary"/></span>
                    </div>
                    <div class="col-xs-3 header-element hidden-sm">
                        <span class="header-text js-automation-header-ao-bo"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/></span>
                    </div>
                    <div class="col-xs-3 header-element hidden-sm">
                        <span class="header-text js-automation-header-order"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                    </div>
                </div>
            </div>
        </div>

        <div class="row table-row">
            <div class="col-xs-12">
                <div class="table-row-wrapper clearfix">
                    <div class="col-xs-6 row-element">
                        <span class="row-text js-automation-bonus-period-title"><spring:theme code="text.account.orderHistory.details.summary.bonusPeriod"/></span>
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                        <span class="row-text  js-automation-bonus-period-value-ao-bo">---</span>
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                        <span class="row-text  js-automation-bonus-period-value-order">
                            
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="row table-row">
            <div class="col-xs-12">
                <div class="table-row-wrapper clearfix">
                    <div class="col-xs-6 row-element">
                        <span class="row-text js-automation-items-total-title"><spring:theme code="text.account.orderHistory.details.summary.itemsTotal"/></span>
                    </div>
                        <div class="col-xs-3 row-element">
                           
                                    <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                                    <span class="row-text js-automation-items-total-value-ao-bo">---</span>
                                
                        </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                        <span class="row-text js-automation-items-total-value-order">
                            
                        </span>
                    </div>
                </div>
            </div>
        </div>
        
        
        <div class="row table-row">
            <div class="col-xs-12">
                <div class="table-row-wrapper clearfix">
                    <div class="col-xs-6 row-element">
                        <span class="row-text js-automation-delivery-title">
                            
                                    <spring:theme code="text.account.orderHistory.details.summary.delivery"/>
                                
                        </span>
                    </div>
                    <div class="col-xs-3 row-element">
                       		<span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                             <span class="row-text js-automation-delivery-value-ao-bo">---</span>
                            
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                        <span class="row-text js-automation-delivery-value-order">
                            
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${showTax}">
            <div class="row table-row">
                <div class="col-xs-12">
                    <div class="table-row-wrapper clearfix">
                        <div class="col-xs-6 row-element">
                            <span class="row-text js-automation-tax-delivery-title">
                                <spring:theme code="text.account.orderHistory.details.summary.delivery.tax"/>
                            </span>
                        </div>
                        <div class="col-xs-3 row-element">
                            
                                    <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                                    <span class="row-text js-automation-tax-delivery-value-ao-bo">---</span>
                                
                        </div>
                        <div class="col-xs-3 row-element">
                            <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                            <span class="row-text js-automation-tax-delivery-value-order">
                               
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${showTax}">
            <div class="row table-row">
                <div class="col-xs-12">
                    <div class="table-row-wrapper clearfix">
                        <div class="col-xs-6 row-element">
                            <span class="row-text js-automation-sales-tax-title">
                                <spring:theme code="text.account.orderHistory.details.summary.salesTax"/>
                                <span class="order-details-wrapper">
                                    (<span class="order-details-btn js-checkout-tax-details-btn" data-content="orderSummaryTaxModal${orderData.code}">
                                        <spring:theme code="text.account.orderHistory.details.summary.details" text="Details"/>
                                    </span>)
                                </span>
                            </span>
                        </div>
                        <div class="col-xs-3 row-element">
                            
                                    <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                                    <span class="row-text js-automation-sales-tax-value-ao-bo">---</span>
                               
                        </div>
                        <div class="col-xs-3 row-element">
                            <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                            <span class="row-text js-automation-sales-tax-value-order">
                                
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <div class="row table-row">
            <div class="col-xs-12">
                <div class="table-row-wrapper clearfix">
                    <div class="col-xs-6 row-element">
                        <span class="row-text js-automation-service-fee-title">
                            
                                    <spring:theme code="text.account.orderHistory.details.summary.serviceFees"/>
                                
                            <span class="order-details-wrapper">
                                    (<span class="order-details-btn js-checkout-service-fee-details-btn" data-content="orderSummaryServiceFeeModal">
                                        <spring:theme code="text.account.orderHistory.details.summary.details"/>
                                     </span>)
                                </span>
                        </span>
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                        <span class="row-text js-automation-service-fee-value-ao-bo">---</span>
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                        <span class="row-text js-automation-service-fee-value-order">
                            
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="row table-row total-row">
            <div class="col-xs-12">
                <div class="table-row-wrapper clearfix">
                    <div class="col-xs-6 row-element">
                        <span class="row-text js-automation-total-title">
                            <spring:theme code="text.account.orderHistory.details.summary.total"/>
                        </span>
                    </div>
                    <div class="col-xs-3 row-element">
                       
                                <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                                <span class="row-text js-automation-total-value-ao-bo">---</span>
                           
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                        <span class="row-text js-automation-total-value-order">
                           <c:choose>
                                <c:when test="${showTax}">
                                    <format:price priceData="${orderData.totalPriceWithTax}" />
                                </c:when>
                                <c:otherwise>
                                    <format:price priceData="${orderData.totalPrice}" />
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        
            <div class="row table-row total-row">
                <div class="col-xs-12">
                    <div class="table-row-wrapper clearfix">
                        <div class="col-xs-6 row-element">
                            <span class="row-text js-automation-total-pvbv-title">
                                <spring:theme code="text.account.orderHistory.details.summary.totalPVBV"/>
                            </span>
                        </div>
                        <div class="col-xs-3 row-element">
                           		<span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                                    <span class="row-text js-automation-total-pvbv-value-ao-bo">---</span>
                                
                        </div>
                        <div class="col-xs-3 row-element">
                            <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                            <span class="row-text js-automation-total-pvbv-value-order">
                                 / 
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            
            
        <div class="row table-row sub-total-row">
            <div class="col-xs-12">
                <div class="table-row-wrapper clearfix">
                    <div class="col-xs-6 row-element">
                        <span class="row-text js-automation-total-saving-title">
                            <spring:theme code="text.account.orderHistory.details.summary.totalSavings"/>
                        </span>
                    </div>
                    <div class="col-xs-3 row-element">
                        
                                <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.AOBO"/>:</span>
                                <span class="row-text js-automation-total-saving-value-ao-bo">---</span>
                            
                    </div>
                    <div class="col-xs-3 row-element">
                        <span class="visible-sm header-text"><spring:theme code="text.account.orderHistory.details.summary.order"/></span>
                        <span class="row-text js-automation-total-saving-order">
                            
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>