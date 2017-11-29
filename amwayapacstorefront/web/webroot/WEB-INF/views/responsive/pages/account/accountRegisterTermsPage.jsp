<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="registerHomeUrl" value="/register-landing-page" />
<template:page pageTitle="${pageTitle}">
	<c:set var="radioCount" scope="request" value="${0}" />
	<cms:pageSlot position="TermsBanner" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<div class="container-fluid main-container">
		<form:form method="post" modelAttribute="termForm" commandName="termForm" action="multiple-terms">
			<ul id="terms" class="box-sizing">
				<cms:pageSlot position="Terms1" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
				<cms:pageSlot position="Terms2" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
				<cms:pageSlot position="Terms3" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
				<cms:pageSlot position="Terms4" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
				<cms:pageSlot position="Terms5" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
				<cms:pageSlot position="Terms6" var="component">
					<cms:component component="${component}" />
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
				</cms:pageSlot>
				<cms:pageSlot position="Terms7" var="component">
					<c:if test="${not empty component}">
						<li>
							<div class="agreement-box">
								<cms:component component="${component}" />
							</div>
							<div class="action amway-theme">
								<spring:bind path="verified">
									<input id="verified" name="verified" type="checkbox" />
								</spring:bind>
								<label for="verified" class="normal">Verified</label>
							</div>
						</li>
					</c:if>
					<c:set var="radioCount" scope="request" value="${radioCount+1}" />
					<input type="hidden" name="radioCount" value="${radioCount}" />
				</cms:pageSlot>
			</ul>
			<div class="next-control">
				<button type="submit" class="btn-blue-white">
					<spring:theme code="register.terms.next.step.button" />
				</button>
				<button type="button" class="btn-cancel" onclick="window.location='${registerHomeUrl}'">
					<spring:theme code="register.terms.cancel.link" />
				</button>
			</div>
		</form:form>
	</div>
	<div class="popup box-sizing" id="pop-terms"></div>
</template:page>