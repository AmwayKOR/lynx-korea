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
                                                            <form id="filterForm" action="" method="POST">
                                                                <select id="date" name="date" class="js-filter-date select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                                                                    <option value="last30Days">Last 30 Days</option>
                                                                    <option value="2017-07">July 2017</option>
                                                                    <option value="2017-06">June 2017</option>
                                                                    <option value="2017-05">May 2017</option>
                                                                    <option value="2017-04">April 2017</option>
                                                                    <option value="2017-03">March 2017</option>
                                                                    <option value="2017-02">February 2017</option>
                                                                    <option value="2017-01">January 2017</option>
                                                                    <option value="2016-12">December 2016</option>
                                                                    <option value="2016-11">November 2016</option>
                                                                    <option value="2016-10">October 2016</option>
                                                                    <option value="2016-09">September 2016</option>
                                                                    <option value="2016-08">August 2016</option>
                                                                    <option value="2016-07">July 2016</option>
                                                                    <option value="2016-06">June 2016</option>
                                                                    <option value="2016-05">May 2016</option>
                                                                </select>
                                                                <select id="type" name="type" class="js-filter-type select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                                                                    <option value="SHOW_ALL">Show All</option>
                                                                    <option value="PARTNER_STORES">Partner Stores &amp; Services</option>
                                                                    <option value="ACTIVE">Active orders</option>
                                                                    <option value="WEB">Web orders</option>
                                                                    <option value="DITTO">Ditto orders</option>
                                                                    <option value="TELEPHONE">Telephone orders</option>
                                                                    <option value="MAIL">Mail orders</option>
                                                                    <option value="SERVICE_CENTER">Service Center orders</option>
                                                                    <option value="AUTO_RENEW">Renewal orders</option>
                                                                    <option value="REPLACEMENT">Replacement orders</option>
                                                                </select>
                                                                <button class="primary small js-automation-order-history-sort-apply" type="submit"><span>Apply</span></button>
                                                            </form>

                                                        </div>
                                                        <div class="form-wrapper search-form-wrapper col-md-5 col-sm-12 hidden-sm hidden-xs js-search-form-wrapper">
                                                             <form id="searchForm" action="" method="POST">
                                                                <input id="searchData" name="searchData" placeholder="Search by order# or Item#" type="text" value="" />
                                                                <button class="primary small js-automation-order-history-search-btn" type="submit">
                                                                    <span class="hidden-sm hidden-xs"> Search</span>
                                                                    <span class="icon-search hidden-md hidden-lg"></span>
                                                                </button>
                                                            </form>

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

