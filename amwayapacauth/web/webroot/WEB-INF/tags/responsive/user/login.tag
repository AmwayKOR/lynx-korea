<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="hideDescription" value="checkout.login.loginAndCheckout" />

<%-- <div class="login-page__headline">
    <spring:theme code="login.title" />
</div> --%>

<c:if test="${actionNameKey ne hideDescription}">
   <%--  <p>
        <spring:theme code="login.description" />
    </p> --%>
</c:if>
<!-- Form Module-->
<div class="module form-module">
    <div class="form">
        <h2>Login to your account</h2>
        <form:form action="${action}" method="post" commandName="loginForm">
            <c:if test="${not empty message}">
                <span class="has-error"> <spring:theme code="${message}" />
                </span>
            </c:if>

            <formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" mandatory="true" />
            <formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="form-control"
                mandatory="true" />

            <input type="hidden" name="response_type" id="response_type" value="${response_type}">
            <input type="hidden" name="client_id" id="client_id" value="${client_id}">
            <input type="hidden" name="redirect_uri" id="redirect_uri" value="${redirect_uri}">
            <input type="hidden" name="response_mode" id="response_mode" value="${response_mode}">
            <input type="hidden" name="scope" id="scope" value="${scope}">
            <input type="hidden" name="state" id="state" value="${state}">
            <input type="hidden" name="nonce" id="nonce" value="${nonce}">

            <ycommerce:testId code="loginAndCheckoutButton">
                <button type="submit" class="btn btn-primary btn-block">
                    <spring:theme code="${actionNameKey}" />
                </button>
            </ycommerce:testId>


            <c:if test="${expressCheckoutAllowed}">
                <button type="submit" class="btn btn-default btn-block expressCheckoutButton">
                    <spring:theme code="text.expresscheckout.header" />
                </button>
                <input id="expressCheckoutCheckbox" name="expressCheckoutEnabled" type="checkbox"
                    class="form left doExpressCheckout display-none" />
            </c:if>

        </form:form>
    </div>
    <div class="forgot-link">
        <ycommerce:testId code="login_forgotPassword_link">
            <a href="#" data-link="<c:url value='/login/pw/request'/>" class="js-password-forgotten"
                data-cbox-title="<spring:theme code="forgottenPwd.title"/>"> <spring:theme code="login.link.forgottenPwd" />
            </a>
        </ycommerce:testId>
    </div>
</div>
<!-- <footer>&copy; Copyright 2017 Amway</footer> -->