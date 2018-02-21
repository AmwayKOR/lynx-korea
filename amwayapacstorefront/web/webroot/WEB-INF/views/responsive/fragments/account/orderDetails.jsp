<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 
                <div class="orders-header js-orders-header clearfix">
				    <div class="order-detail-title ">
				        <span class="order-number js-automation-order-details-number-title"><spring:theme code="text.account.orderHistory.details.orderNumber"/></span>
				        <span class="order-number js-automation-order-details-number-value">#${orderData.code}</span>
				        <div class="order-details-status hidden-sm amw__key-value">
				            <div>
				                <div class="js-automation-order-details-status-title"><spring:theme code="text.account.orderHistory.status"/></div>
				                <div class="value js-automation-order-details-status-value"><spring:theme code="text.account.order.status.display.${orderData.statusDisplay}"/></div>
				            </div>
				            <div>
				                <div class="js-automation-order-details-date-title"><spring:theme code="text.account.orderHistory.orderDate"/></div>
				                <div class="value js-automation-order-details-date-value"><fmt:formatDate value="${orderData.created}" pattern="MM/dd/yyyy"/></div>
				            </div>
				            <div>
				                 <span class="js-automation-order-original-title"><spring:theme code="text.account.orderHistory.originalOrder"/></span>
				                 <a class="js-automation-order-original-link" href="${orderDataDetailsUrl}orders">#${orderData.code}</a>
				             </div>
				        </div>
				    </div>
				    <div class="edit-order-button-container">
				        
				            <a data-toggle="tab" href="#cancelOrderContent${orderData.code}" class="btn btn-link modify-order js-automation-order-cancel">
				                <spring:theme code="text.account.orderHistory.details.cancel"/>
				            </a>|
				        
				            <a href="${orderDataDetailsUrl}order/edit/${orderData.code}" class="btn btn-link modify-order js-automation-order-view-edit">
				                <spring:theme code="text.account.orderHistory.details.viewModify"/>
				            </a>
				    </div>
				</div>
				
				<div class="tab-content js-tab-content">
				    <input type="hidden" value="${orderData.code}" class="js-order-id">
				
				    <div id="cancelOrderContent${orderData.code}"
				         class="tab-pane js-tab-pane fade cancel-order-content account-section-header">
				        <div class="title-container">
				            <span class="title">
				                <spring:theme code="text.account.orderHistory.cancel.title"/>
				            </span>
				        </div>
				        <div class="message-container">
				            <span class="message">
				                <spring:theme code="text.account.orderHistory.cancel.message"/>
				            </span>
				        </div>
				        <div class="buttons-container">
				        
				            			<button class="btn btn-primary hidden-sm hidden-xs js-cancel-order">
				                        <spring:theme code="text.account.orderHistory.cancel.buttons.yesCancel"/>
				                    </button>
				
				            <button class="btn btn-link dont-cancel js-dont-cancel hidden-sm hidden-xs">
				                <spring:theme code="text.account.orderHistory.cancel.buttons.noDont"/>
				            </button>
				            <button class="btn btn-primary hidden-md hidden-lg">
				                <spring:theme code="text.account.orderHistory.cancel.buttons.yes"/>
				            </button>
				            <button class="btn btn-link dont-cancel js-dont-cancel hidden-md hidden-lg">
				                <spring:theme code="text.account.orderHistory.cancel.buttons.no"/>
				                        </button>
				                    </div>
				                </div>
				            </div>
				
				<div class="navigation-tabs-container order-history-tabs-wrapper">
				                <ul class="tabs-toggles option2">
				                    
				                    <li class="tab-toggle-wrap active">
				                        <a class="tab-toggle js-automation-tab-item-view" href="#itemView${orderData.code}" data-toggle="tab">
				                <h6 class="toggle-text">
				                    <spring:theme code="text.account.orderHistory.item.view"/>
				                </h6>
				            </a>
				        </li>
				    </ul>
				
				    <div class="tab-content tabs-content-blocks">
				        
				        <div class="tab-pane content-block active" id="itemView${orderData.code}">
				            <account:accountOrderHistoryItemView order="${orderData}"/>
				        </div>
				    </div>
				</div>

            