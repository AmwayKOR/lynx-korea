<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="businessCenter" tagdir="/WEB-INF/tags/responsive/businesscenter"%>

<template:page pageTitle="${pageTitle}">    
    <div class="container-fluid my-count">
    		<div class="row">
            <businessCenter:navigationBusinessCenter />
        </div>
        <div class="row">
            <cms:pageSlot position="Body" var="feature" element="div">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
    </div>
</template:page>
