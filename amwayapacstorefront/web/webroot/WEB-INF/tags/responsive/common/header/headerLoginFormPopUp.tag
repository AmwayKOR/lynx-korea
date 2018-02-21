<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="popover fade bottom extraclass1 no-login collapse <c:if test="${loginError}">in</c:if>" role="tooltip"
	id="login-drop-content" style="margin-top: 31px;">
	<div class="popover-arrow arrow" style="margin-left: 80px;"></div>
	<div class="popover-content">
		<i class="mobile-popover-close"></i>
		<c:url value="/j_spring_security_check" var="login_auth_url" />
		<c:url value="/register-landing-page" var="register_page_url" />
		<form class="topbar__popover login-form authentication-js" action="${login_auth_url}" method="post">
			<h1 class="login-form__title">
				<spring:theme code="text.header.loginform.title" />
			</h1>
			<p class="login-form__sub-text">
				<spring:theme code="text.header.loginform.sub-text" />
			</p>
			<c:if test="${loginError}">
				<p class="red">
					<spring:theme code="text.header.loginform.incorrectcredentials" />
				</p>
			</c:if>
			<input id="j_username" name="j_username" type="text" placeholder="Username or ABO ID" class="login-form__username">
			<input id="j_password" name="j_password" type="password" placeholder="Password" class="login-form__password">
			<input type="hidden" name="CSRFToken" value="${CSRFToken.token}" />
			<input class="login-form__remember" id="rememberMe" name="rememberMe" type="checkbox">
			<label for="rememberMe" class="remember-me">
				<spring:theme code="text.header.loginform.remember" />
			</label>
			<button class="btn-primary button login-form__submit" type="submit" value="submit">
				<spring:theme code="text.header.loginform.submit" />
			</button>
			<a class="login-form__link" href="#">
				<spring:theme code="text.header.loginform.forgotpassword" />
			</a>
			<a class="login-form__link" href="#">
				<spring:theme code="text.header.loginform.firstlogging" />
			</a>
			<hr>
			<p class="login-form__owner">
				<spring:theme code="text.header.loginform.owner" />
			</p>
			<a href="${register_page_url}">
				<input class="button login-form__register" type="button" value="register here">
			</a>
		</form>
	</div>
</div>