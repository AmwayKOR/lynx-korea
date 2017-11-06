<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="row">
            <div class="account-landing-page">
                <div class="container-fluid">
                    <div class="row user-account-options">
                        <div class="container">
                            <div class="row">
                            		<div class="option-item-container col-xs-6 col-md-4">
                                    <a href="BusinessInformation.html">
                                    		<div class="option-item">
                                    			<cms:pageSlot position="Section1A" var="component">
												<cms:component component="${component}"/>
											</cms:pageSlot>
                                    		</div>
                                    </a>
                                 </div>
                                 <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="Order-History-Personal-Orders-expanded.html">
                                    		<div class="option-item">
                                    			<cms:pageSlot position="Section1B" var="component">
												<cms:component component="${component}"/>
											</cms:pageSlot>
											<div class="option-item-footer">
	                                             <span>You have <span class="option-item-footer-digit">${ordersAmount}</span> recent orders</span>
	                                         </div>
                                    		</div>
                                    </a>
                                 </div>
                                 <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="aaron/billingShipping">
                                    		<div class="option-item">
                                    			<cms:pageSlot position="Section1C" var="component">
												<cms:component component="${component}"/>
											</cms:pageSlot>
                                    		</div>
                                    </a>
                                 </div>
                                 <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="Bonus-Payment-Preference.html">
                                    		<div class="option-item">
                                    			<cms:pageSlot position="Section2A" var="component">
												<cms:component component="${component}"/>
											</cms:pageSlot>
											<div class="option-item-footer">
	                                            <span>PAPER CHECK<c:choose><c:when test="${bonusPaperCheck == true}"><span class="icon-check-bold"></span><span class="active-green">active</span></c:when><c:otherwise><span class="icon-check-bold"></span><span class="active-green">Inactive</span></c:otherwise></c:choose></span>
	                                        </div>
                                    		</div>
                                    </a>
                                 </div>
                                 <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="Auto-Renewal.html">
                                    		<div class="option-item">
                                    			<cms:pageSlot position="Section2B" var="component">
												<cms:component component="${component}"/>
											</cms:pageSlot>
											<div class="option-item-footer">
	                                            <span>Status<c:choose><c:when test="${autoRenewal == true}"><span class="icon-check-bold"></span><span class="active-green">active</span></c:when><c:otherwise><span class="icon-check-bold"></span><span class="active-green">Inactive</span></c:otherwise></c:choose></span>
	                                        </div>
                                    		</div>
                                    </a>
                                 </div>
                                 <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="BusinessInformation.html">
                                    		<div class="option-item">
                                    			<cms:pageSlot position="Section2C" var="component">
												<cms:component component="${component}"/>
											</cms:pageSlot>
											<div class="option-item-footer">
	                                            <span>You have <span class="option-item-footer-digit">${contractsAmount}</span> contracts to complete</span>
	                                        </div>
                                    		</div>
                                    </a>
                                 </div>
                            		
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>