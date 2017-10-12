<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">    
    <div class="container-fluid main-container">
        <div class="print-hide breadcrumb-section">
            <ol class="breadcrumb">
                <li>
                    <a href="homepage.html">Home</a></li>
                <li>My Account</li></ol>
        </div>
    </div>
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
                                <div class="go-block col-sm-8">
                                    <div class="amway-theme">
                                        <div class="jump-to">
                                            <span>Jump to</span>
                                        </div>
                                        <div class="jump-to-wrapper">
                                            <select class="js-jump-to-selection hide select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                                                <option value="/my-account" class="js-account-option-link text-capitalize" selected>My Account</option>
                                                <option value="/my-account/business-information" class="js-account-option-link text-capitalize">Business Information &amp; Upline</option>
                                                <option value="/my-account/orders" class="js-account-option-link text-capitalize">Order History</option>
                                                <option value="/my-account/billing-shipping" class="js-account-option-link text-capitalize">Billing &amp; Shipping</option>
                                                <option value="/my-account/bonus-payment" class="js-account-option-link text-capitalize">Bonus Payment Preferences</option>
                                                <option value="/my-account/auto-renewal" class="js-account-option-link text-capitalize">Auto Renewal</option>
                                                <option value="/my-account/contracts" class="js-account-option-link text-capitalize">Contracts</option>
                                                <option value="/my-account/update-profile" class="js-account-option-link text-capitalize">Edit Profile</option>
                                            </select>
                                            <input type="button" class="js-jump-to-selection-btn" value="GO">
                                        </div>
                                    </div>
                                </div>
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
                            		<cms:pageSlot position="Section1A" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
                                 <cms:pageSlot position="Section1B" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
								<cms:pageSlot position="Section1C" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
								<cms:pageSlot position="Section2A" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
								<cms:pageSlot position="Section2B" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
								<cms:pageSlot position="Section2C" var="component">
									<cms:component component="${component}"/>
								</cms:pageSlot>
                                
                                
                                <!-- 
                                <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="Bonus-Payment-Preference.html">
                                    <div class="option-item">
                                        <div class="icon icon-credit">
                                        </div>
                                        <span class="option-title">Bonus Payment Preferences</span>
                                        <span class="option-description">Select how you'd like to receive your bonuses</span>
                                        <div class="option-item-footer">
                                            <span>PAPER CHECK<span class="icon-check-bold"></span><span class="active-green">active</span></span>
                                        </div>
                                    </div>
                                    </a>
                                </div>
                                <div class="option-item-container col-xs-6 col-md-4">
                                    <a href="Auto-Renewal.html">
                                    <div class="option-item">
                                        <div class="icon icon-cycle">
                                        </div>
                                        <span class="option-title">Auto renewal</span>
                                        <span class="option-description">Manage your renewal preferences here</span>
                                        <div class="option-item-footer">
                                            <span>Status<span class="icon-check-bold"></span><span class="active-green">active</span></span>
                                        </div>
                                    </div>
                                    </a>
                                </div>
                                <div class="option-item-container col-xs-6 col-md-4">
                                    <div class="option-item">
                                        <div class="icon icon-contract">
                                        </div>
                                        <span class="option-title">Contracts</span>
                                        <span class="option-description">View contracts and other documents here</span>
                                        <div class="option-item-footer">
                                            <span>You have <span class="option-item-footer-digit">4</span> contracts to complete</span>
                                        </div>
                                    </div>
                                </div>
                                 -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template:page>