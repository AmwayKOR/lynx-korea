<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:url value="/my-account/orders" var="ordersUrl"/>
   
    <div class="container-fluid main-container my-count">
        <div class="account-section">
            <div class="account-section-content order-history-account-section-content">
                
                <div class="row">
                    <div class="container-lg">
                        <div class="account-landing-page">
                            <div class=" order-history-box">
                                <div class="amway-theme">
                                    <div class="order-history-account-section-header clearfix">
                                        <span class="history-header-icon icon-back-in-time-new"></span>
                                        <h5 class="order-history-header">Order History</h5>
                                    </div>
                                </div>
                                <div class="account-section-content order-history-account-section-content">
                                    <div class="account-orderhistory">
                                        <div class="order-history-container js-order-modify-view-container display-none"></div>
                                        <div class="amway-theme">
                                            <div class="navigation-tabs-container order-history-container js-order-history-container">
                                                <ul class="tabs-toggles">
                                                    <li class="tab-toggle-wrap active"> <a class="tab-toggle" data-toggle="tab" href="#personalOrdersContent"> <h6 class="toggle-text">Personal Orders</h6> </a> </li> <li class="tab-toggle-wrap"> <a class="tab-toggle" data-toggle="tab" href="#customerOrdersContent"> <h6 class="toggle-text">Customer Orders</h6> </a> </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="order-history-search-wrapper amway-theme row">
                                                        <div class="order-history-view-orders-from hidden-xs hidden-sm">View Orders From:</div>
                                                        <div class="hidden-md hidden-lg toggle-buttons-container row">
                                                            <button class="col-sm-6 js-order-history-filters"> <span class="icon-Humburger_list"></span><span>Filters</span> </button>
                                                            <button class="col-sm-6 js-order-history-search"> <span class="icon-search"></span> <span>Search</span> </button>
                                                        </div>
                                                        <div class="form-wrapper col-md-7 col-sm-12 hidden-sm hidden-xs js-filter-form-wrapper">
                                                            
                                                     
                                                            <form:form method = "POST" action = "${ordersUrl}" modelAttribute="filterForm">
								                                <form:select class="js-filter-date select2-hidden-accessible" path="date">
								                                    <form:option value="${orderDateDefaultOption}">
								                                    	<spring:theme code="text.account.orderHistory.orderDate.${orderDateDefaultOption}"/>
								                                    </form:option>
								                                    <c:forEach items="${orderDateOptions}" var="orderDateOption">
								                                        <fmt:parseDate value="${orderDateOption}" pattern="yyyy-MM" var="parsedDate"/>
								                                        <fmt:formatDate value='${parsedDate}' pattern="MMMM yyyy" var="formattedParsedDate"/>
								                                        <form:option value="${orderDateOption}" label="${formattedParsedDate}"></form:option>
								                                    </c:forEach> 
								                                </form:select>
								                                <form:select class="js-filter-type select2-hidden-accessible" path="type">
								                                    <c:forEach items="${orderTypeOptions}" var="orderTypeOption">
								                                        <form:option value="${orderTypeOption}"><spring:theme code="text.account.orderHistory.orderType.${orderTypeOption}"/></form:option>
								                                    </c:forEach>
								                                </form:select>
								                                <button class="primary small js-automation-order-history-sort-apply" type="submit">
								                                    <span>
								                                        <spring:theme code='text.account.orderHistory.filters.apply'/>
								                                    </span>
								                                </button>
								                                <input type="hidden" name="filterBy" value="true"/>
								                            </form:form>

                                                        </div>
                                                        <div class="form-wrapper search-form-wrapper col-md-5 col-sm-12 hidden-sm hidden-xs js-search-form-wrapper">
                                                             <form:form method = "POST" action = "${ordersUrl}" modelAttribute="searchForm">
								                                <spring:theme code='text.account.orderHistory.search.placeholder' var="searchPlaceholder"/>
								                                <form:input path="searchData" placeholder="${searchPlaceholder}"/>
								                                <button class="primary small js-automation-order-history-search-btn" type="submit">
								                                    <span class="hidden-sm hidden-xs">
								                                        <spring:theme code='text.account.orderHistory.search'/>
								                                    </span>
								                                    <span class="icon-search hidden-md hidden-lg"></span>
								                                </button>
								                                <input type="hidden" name="searchBy" value="true"/>
								                            </form:form>
                                                        </div>

                                                    </div>
                                                    
                                                    <div id="personalOrdersContent" class="tab-pane fade active in">
                                                    
                                                    		<account:accountOrderHistoryOrdersSection orders="${personalOrder}" category="personal" />
                                                        
                                                        
                                                    </div>
                                                    
                                                    <div id="customerOrdersContent" class="tab-pane fade">
                                                        <div class="account-section-content order-history-account-section-content content-empty amway-theme">
                                                            <div class="no-orders-found-container">
                                                                <div class="no-orders-found-title-container">
                                                                    <h3 class="no-orders-found-title">You currently have no customer Orders</h3>
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
                    </div>
                </div>
            </div>
        </div>
    </div>

