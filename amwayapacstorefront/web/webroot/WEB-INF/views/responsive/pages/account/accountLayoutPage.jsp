<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>

<template:page pageTitle="${pageTitle}">    
    <div class="container-fluid my-count">
        <div class="row">
            <div class="container-lg">
                <account:accountProfileBar />
            </div>
        </div>
        <div class="account-section">
            <cms:pageSlot position="BodyContent" var="feature" element="div">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
    </div>
</template:page>
