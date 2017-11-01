<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<spring:url value="/my-account/update-profile" var="updateProfileUrl"/>
<spring:url value="/my-account/update-password" var="updatePasswordUrl"/>
<spring:url value="/my-account/update-email" var="updateEmailUrl"/>
<spring:url value="/my-account/address-book" var="addressBookUrl"/>
<spring:url value="/my-account/payment-details" var="paymentDetailsUrl"/>
<spring:url value="/my-account/orders" var="ordersUrl"/>


<template:page pageTitle="${pageTitle}">    
    
    
    <div class="container-fluid my-count">
        <div class="row">
            <div class="container-lg">
                <div class="account-landing-page">
                    <div class="user-account-header">
                        <div class="container">
                            <div class="row">
                                <div class="user-block col-sm-4">
                                    <div class="user-image-container">
                                        <img class="user-image" src="${themeResourcePath}/images/yui.png">
                                        <div class="icon icon-pencil">
                                        </div>
                                    </div>
                                    <div class="user-info">
                                        <span class="user-name">Yui Mori</span>
                                        <span class="user-id">IBO # 1234567</span>
                                    </div>
                                </div>
                                <cms:pageSlot position="ProfileBarJumpTo" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                                    <a href="AddNewPaymentMethod.html">
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
    </div>
</template:page>
