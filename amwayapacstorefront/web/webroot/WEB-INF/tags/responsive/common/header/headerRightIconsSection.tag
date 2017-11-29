<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="country-language-container header-content">
	<div class="header-element-content">
		<div class="site-logo">
			<cms:pageSlot position="SiteLogo" var="component" element="div">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</div>

		<span class="dropdown">
			<cms:pageSlot position="CountrySelector" var="component">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</span>
		<div class="nav-links-container pos-relative">
			<ul class="nav-list clearfix">
				<c:choose>
					<c:when test="${isUserAbo}">
						<li class=" nav-item">
							<a href="javascript:void(0);" data-toggle="collapse" data-target="#login-drop-content"
								class="pos-relative img-nav-link js-my-account-menu opened">
								<img src="${themeResourcePath}/images/yui.png" alt="" width="34px">
								<span class="message-num-1">24</span>
								<span class="user-name">Yui Mori</span>
								<span class="icon icon-arrow-dropdown"></span>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="yCmsComponent nav-item">
							<a href="#" data-toggle="collapse" data-target="#login-drop-content"
								class="pos-relative img-nav-link js-my-account-menu opened">
								<img src="${themeResourcePath}/images/user_avatar_pre_login.png" alt="" width="34px">
							</a>
						</li>
					</c:otherwise>
				</c:choose>
				<cms:pageSlot position="MiniCart" var="component">
					<cms:component component="${component}"/>
				</cms:pageSlot>
			</ul>
		</div>
	</div>
</div>