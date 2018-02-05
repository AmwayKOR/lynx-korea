<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user"%>

<c:url value="/j_spring_security_check" var="loginActionUrl" />

<div class="main-title">
  <h1>Amway</h1>
  <h3>Returning Customer</h3>
  <span>Already have an account? Sign in to retrieve your account setting.</span>
</div>
<div class="login-section">
	<user:login actionNameKey="login.login" action="${loginActionUrl}" />
</div>
