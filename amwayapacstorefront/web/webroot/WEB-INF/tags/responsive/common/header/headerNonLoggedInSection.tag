<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="popover fade bottom extraclass1 no-login collapse <c:if test="${loginError}">in</c:if>" role="tooltip" id="login-drop-content" style="margin-top: 31px;">
    <div class="popover-arrow arrow" style="margin-left: 80px;"></div>
    <div class="popover-content">
        <i class="mobile-popover-close"></i>
        <c:url value="/j_spring_security_check" var="login_auth_url"/>
        <%-- <form:form method="post" action="${login_auth_url}" cssClass="topbar__popover login-form authentication-js" commandName="loginForm">
        	<h1 class="login-form__title">welcome to amway!</h1>
            <p class="login-form__sub-text">Log into your account or sign up to create one.</p>
            <c:if test="${loginError}"><p class="red">Your username or password was incorrect.</p></c:if>
            <formElement:formInputBox labelKey="j_username" idKey="j_username" path="j_username" placeholder="Username or ABO ID" inputCSS="login-form__username"/>
            <formElement:formInputBox labelKey="j_password" idKey="j_password" path="j_password" placeholder="Password" inputCSS="login-form__password"/>
            <input class="login-form__remember" id="remember" name="remember" type="checkbox">
            <label for="remember" class="remember-me">Remember me</label>
            <button class="btn-primary button login-form__submit" type="submit" value="submit">submit</button>
            <a class="login-form__link" href="#">Forgot your password?</a>
            <a class="login-form__link" href="#">First time loging in?</a>
            <hr>
            <p class="login-form__owner">Become an Amway Business Owner</p>
            <a href="register-landing.html">
            	<input class="button login-form__register" type="button" value="register here">
            </a>
        </form:form> --%>
        
        <form class="topbar__popover login-form authentication-js" action="${login_auth_url}" method="post" >
            <h1 class="login-form__title">welcome to amway!</h1>
            <p class="login-form__sub-text">Log into your account or sign up to create one.</p>
            <c:if test="${loginError}"><p class="red">Your username or password was incorrect.</p></c:if>
            <input id="j_username" name="j_username" type="text" placeholder="Username or ABO ID" class="login-form__username">
            <input id="j_password" name="j_password" type="text" placeholder="Password" class="login-form__password">
            <input type="hidden" name="CSRFToken" value="${CSRFToken.token}" />
            <input class="login-form__remember" id="remember" name="remember" type="checkbox">
            <label for="remember" class="remember-me">Remember me</label>
            <button class="btn-primary button login-form__submit" type="submit" value="submit">submit</button>
            <a class="login-form__link" href="#">Forgot your password?</a>
            <a class="login-form__link" href="#">First time loging in?</a>
            <hr>
            <p class="login-form__owner">Become an Amway Business Owner</p>
            <a href="register-landing.html">
            	<input class="button login-form__register" type="button" value="register here">
            </a>
        </form>

    </div>
</div>