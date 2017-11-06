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
        <div class="account-section">
            <cms:pageSlot position="BodyContent" var="feature" element="div" class="account-section-content">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
    </div>
</template:page>
