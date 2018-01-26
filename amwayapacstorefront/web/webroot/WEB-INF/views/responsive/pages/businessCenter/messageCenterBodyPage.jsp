<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id ="message-center">
  <div class="container-fluid main-container">
        <h1 class="page-title">Message Center</h1>
        <div class="message-title">
        		<c:choose>
	        		<c:when test="${countOfNotifications eq 0}">
	        			<div class="notice">You have 0 messages</div>
	        		</c:when>
	        		<c:otherwise>
	        			<div class="notice">${countOfNotifications} messages</div>
	        		</c:otherwise>
        		</c:choose>
            <div>Type of Messages:<div>&nbsp; All<span class="line">|</span><a href="<c:url value="/business-center/message-center" />">Message Type 1</a><span class="line">|</span><a>Message Type 2</a></div></div>
        </div>
      
        <div id="message-center-list" class="message-center-list">
            <div class="billing-shipping">
                <div class="panel-group accordion-billing-shipping" >
                		
                		<c:if test="${not empty searchPageData.results}">
                			<c:forEach items="${searchPageData.results}" var="notification">
                				<div class="panel">
                					<div id="paymentInformationBody" class="panel-collapse">
		                            <div class="panel-body">
		                                <div class="account-paymentdetails account-list ">
		                                    <div class="account-cards card-select">
		                                        <div>
		                                            <div class="payment-info-container container-fluid">
		                                                <div class="row">
		                                                    <div class="col-lg-6 col-md-6 col-xs-12 card-info-block">
		                                                        <div class="card-info">
		                                                            <div class="card-data">
		                                                                <a><span class="panel-title-text">${notification.shortDescription}</span></a>
		                                                                
		                                                                <fmt:parseDate value="${notification.publishDate}" pattern="mm/dd/yyyy" var="parsedDate"/>
								                                        <fmt:formatDate value='${parsedDate}' pattern="mm/dd/yyyy hh:mma" var="formattedParsedDate"/>
		                                                                
		                                                                <span class="expiration-date">${fn:toLowerCase(formattedParsedDate)}</span>
		                                                            </div>
		                                                        </div>
		
		                                                    </div>
		                                                    <div class="col-lg-6 col-md-6 col-xs-12 block-right">
		                                                        <div class="product-stock">
		                                                            <div>
		                                                                <span class="stock">
		                                                                    <span class="product-availability">
		                                                                    	<c:choose>
																	        		<c:when test="${notification.status.code eq 'READ'}">
																	        			<span class="green">
		                                                                              <span class="icon icon-alert"></span>
		                                                                            	<span class="text text-uppercase">Message Type</span>
		                                                                            	</span>
																	        		</c:when>
																	        		<c:otherwise>
																	        			<span class="red">
		                                                                              <span class="icon icon-alert"></span>
		                                                                            	<span class="text text-uppercase">Message Type</span>
		                                                                            	</span>
																	        		</c:otherwise>
																        		</c:choose>
		                                                                    </span>
		                                                                </span>
		                                                            </div>
		                                                        </div>
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            	  </div>
                        				</div>
                        			</div>
                			</c:forEach>
                		</c:if>
                
                
                    
                </div>
            </div>
            <!-- 
            <div class="account-orderhistory-pagination">
				<nav:pagination top="false" msgKey="text.account.orderHistory.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}"  numberPagesShown="${numberPagesShown}"/>
			</div>
             -->
        </div>
    </div>
</div>