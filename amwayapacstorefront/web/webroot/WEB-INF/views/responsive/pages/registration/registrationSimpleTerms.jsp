<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="registerHomeUrl" value="simple-terms" />
<c:set var="hasVerifiedFlagSection" value="false" />
<template:page pageTitle="${pageTitle}">
	<c:set var="radioCount" scope="request" value="${0}" />
	<cms:pageSlot position="TermsBanner" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<div class="container-fluid main-container">
		<form:form method="post" modelAttribute="termForm" commandName="termForm" action="validate-simple-terms">
			<ul id="terms" class="box-sizing">
				<cms:pageSlot position="Terms1" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
			</ul>
			<div class="next-control">
				<button type="submit" class="btn-blue-white">NEXT</button>
				<button type="button" class="btn-cancel" onclick="window.location='${registerHomeUrl}'">Cancel</button>
			</div>
		</form:form>
	</div>
	<div class="popup box-sizing" id="pop-terms"></div>
</template:page>