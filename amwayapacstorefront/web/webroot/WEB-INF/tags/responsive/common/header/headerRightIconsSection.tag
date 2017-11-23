<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="isUserAbo" value="false" />
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
</sec:authorize>
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
				<li class="componentContainer liOffcanvas nav-item nav-mini-cart">
					<span class="">
						<div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">
							<a href="javascript:void(0);" class="pos-relative mini-cart-link js-mini-cart-link clearfix "
								data-toggle="collapse" data-target="#shoppingcar-drop-content">
								<div class="mini-cart-icon">
									<span class="icon-shopping-cart"></span>
								</div>
								<div class="mini-cart-count js-mini-cart-count">
									<span class="nav-items-total">16</span>
								</div>
								<div class="cart-icon-wrapper">
									<div class="mini-cart-arrow"></div>
								</div>
							</a>
							<div class="mini-cart-items-container js-mini-cart-items-container">
								<ul class="nav nav-tabs">
									<li class="active">
										<a data-toggle="tab" href="#miniShoppingCartPopupContent">
											Shopping Cart (
											<span id="minicartTabTotalItems">0</span>
											)
										</a>
									</li>
									<li>
										<a data-toggle="tab" href="#dittoPopupContent">My Ditto</a>
									</li>
								</ul>
								<div class="tab-content js-tab-content">
									<div id="miniShoppingCartPopupContent" class="tab-pane fade in active"></div>
									<div id="dittoPopupContent" class="tab-pane fade"></div>
								</div>
							</div>
						</div>
						<div class="mini-cart-container js-mini-cart-container"></div>
					</span>
				</li>
			</ul>
		</div>
	</div>
</div>