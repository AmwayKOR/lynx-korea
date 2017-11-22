<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<c:set var="isUserAbo" value="false" />
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
</sec:authorize>

<header class="js-mainHeader main-header general-header <c:if test="${loginError}">userinfo-open</c:if>">
	<div class="amway-theme">
		<nav class="header-container">
			<div class="header-wrapper pos-relative">
				<c:choose>
					<c:when test="${isUserAbo}">
						<header:headerLoggedInAccountSection />
					</c:when>
					<c:otherwise>
						<header:headerLoginForm />
					</c:otherwise>
				</c:choose>
				<header:headerMiniCart />
				<header:headerRightIconsSection />
				<div class="">
					<div class="nav-links-container">
						<ul class="nav-list clearfix"></ul>
					</div>
				</div>
			</div>
		</nav>
	</div>
	<div class="amway-theme">
		<nav class="print-hide amw-navigation-menu js-navigation--middle">
			<div class="navigation-menu-container">
				<div class="mobile-container">
					<div class="mobile-wrapper">
						<div class="mobile-header-cell navigation-menu-toggle-wrapper">
							<a role="button" data-toggle="collapse" href="#mobile-menu-accordion" aria-expanded="false"
								aria-controls="mobile-menu-accordion" class="overlay-menu-toggle-mobile js-overlay-menu-toggle-mobile collapsed">
								<span class="hamburger-icon icon-Icon-Hamburger-01"></span>
							</a>
							<div class="panel-group collapse overlay-mobile-menu js-overlay-mobile-menu js-remove-overlay-desktop-menu"
								id="mobile-menu-accordion" role="tablist" aria-multiselectable="true">

								<cms:pageSlot position="MobileNavigationBar" var="component">
									<cms:component component="${component}" />
								</cms:pageSlot>

								<li role="presentation" class=" nav-list-element">
									<div class="overlay-menu-component-mobile-wrapper country">
										<div class="panel-group" id="mobile-menu-category-accordion" role="tablist" aria-multiselectable="true">
											<div class="panel panel-default overlay-menu-mobile__panel">
												<div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading4">
													<h4 class="panel-title">
														<a class="panel-toggle collapsed" role="button" data-toggle="collapse"
															data-parent="#mobile-menu-category-accordion" href="#navMenuMobCollapse4" aria-expanded="true"
															aria-controls="navMenuMobCollapse4">
															<div id="closeHead">
																<span class="title-element tab-image">
																	<img src="${themeResourcePath}/images/jp.jpg" />
																</span>
																<span class="title-element active-parent-icon">
																	<span class="icon-chevron-left"></span>
																</span>
																<span class="title-element title-text">Japan</span>
																<span class="title-element accordion-icon-wrapper">
																	<span class="pull-right icon-minus"></span>
																</span>
															</div>
															<div id="openHead">
																<span class="title-element title-text">COUNTRY</span>
															</div>
														</a>
													</h4>
												</div>
												<div id="navMenuMobCollapse4" class="panel-collapse collapse overlay-menu-mobile__panel__content"
													role="tabpanel" aria-labelledby="navMenuMobHeading4">
													<div class="panel-body">
														<div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
															<!--<h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="Vitamins_and_Supplements_Product_listing_page.html" onclick="javascript:location.href='Vitamins_and_Supplements_Product_listing_page.html'" aria-expanded="false" aria-controls="navMenuMobCountry">
                                                                    <span class="subcategory-title">COUNTRY</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>-->
															<ul class="subcategory-list countryBox" id="navMenuMobCountry">

																<li class="content-item">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/cn.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">China</span>
																	</a>
																</li>
																<li class="content-item">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/my.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">Malaysia</span>
																	</a>
																</li>
																<li class="content-item">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/ph.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">Philippines</span>
																	</a>
																</li>
																<li class="content-item">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/kr.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">Korea</span>
																	</a>
																</li>
																<li class="content-item">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/th.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">Thailand</span>
																	</a>
																</li>
																<li class="content-item">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/sg.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">Singapore</span>
																	</a>
																</li>
																<li class="content-item current-country">
																	<a class="country-item-link" href="">
																		<span class="flag-icon">
																			<img class="topbar__flag" src="${themeResourcePath}/images/jp.jpg" alt="flag">
																		</span>
																		<span class="country-item-text">Japan</span>
																	</a>
																</li>

															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>
							</div>
							<div class="overlay-mobile-menu-background js-overlay-mobile-menu-background"></div>
						</div>

						<div class="mobile-header-cell mobile-nav-row-btn-logo">
							<div class=" yComponentWrapper">
								<div class="banner__component banner">
									<a href="homepage.html">
										<img title="" alt="" src="${themeResourcePath}/images/logo2.png" />
									</a>
								</div>
							</div>
						</div>
						<div class="mobile-header-cell search-btn-container">
							<a class="mobile-search-btn js-toggle-xs-search print-hide">
								<span class="icon icon-search"></span>
							</a>
						</div>
						<div class="mobile-header-cell user-info-container">
							<button class="mobile-user-btn js-toggle-xs-search print-hide" type="button">
								<img src="${themeResourcePath}/images/yui.png" alt="" width="34px">
								<span class="message-num-1">24</span>
								<!--<span class="icon icon-user"></span>-->
							</button>
						</div>
						<div class=" miniCartSlot componentContainer mobile-header-cell mobile-cart-container">
							<div class="">
								<div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">
									<a href="#" class="mini-cart-link js-mini-cart-link clearfix " data-mini-cart-url=""
										data-mini-cart-refresh-url="" data-mini-cart-name="Shopping Cart" data-mini-cart-empty-name="Empty Cart">
										<div class="mini-cart-icon ">
											<span class="nav-items-total">16</span>
											<span class="icon-shopping-cart"></span>

										</div>

										<div class="cart-icon-wrapper">
											<div class="mini-cart-arrow"></div>
										</div>
									</a>
									<!--remove min-cart-->
								</div>
								<div class="mini-cart-container js-mini-cart-container"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="desktop-nav-menu clearfix">
					<div class="col-sm-12 col-sm-8 top-menu">
						<div class="top-menu-links pull-left">
							<nav:topNavigation />
						</div>
					</div>
					<div class="col-sm-12 col-md-4 top-search print-hide">
						<div class="site-search">
							<cms:pageSlot position="SearchBox" var="comp1">
								<cms:component component="${comp1}" />
							</cms:pageSlot>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</div>

	<header:headerSearchResultSection />

</header>
<common:globalMessages />
<common:globalMessagesTemplates />
<cms:pageSlot position="BreadCrumb" var="component">
	<cms:component component="${component}" />
</cms:pageSlot>
