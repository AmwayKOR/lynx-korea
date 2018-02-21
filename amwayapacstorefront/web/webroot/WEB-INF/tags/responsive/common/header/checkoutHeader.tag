<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<header class="js-mainHeader main-header general-header">
	<div></div>
	<div class="amway-theme">
		<nav class="header-container header-style-checkout">
			<div class="header-wrapper">
				<div class="country-language-container header-content">
					<div>
						<div class="site-logo">
							<cms:pageSlot position="SiteLogo" var="component" element="div">
								<cms:component component="${component}" />
							</cms:pageSlot>
						</div>
						<c:url var="cartPageUrl" value="/cart" />
						<a href="${cartPageUrl}" class="checkout-topbar-back">
							<span class="glyphicon glyphicon-menu-left"></span>
							BACK TO
							<label class="mobile-hidden">SHOPPING</label>
							CART
						</a>
						<form id="lang-form" class="hidden" action="#" method="post">
							<div class="form-group">
								<select name="code" id="lang-selector" class="form-control select2-hidden-accessible" tabindex="-1"
									aria-hidden="true">
									<option value="en" lang="en">English</option>
									<option value="es" lang="es">Español</option>
								</select>
								<span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100px;">
									<span class="selection">
										<span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true"
											aria-expanded="false" tabindex="0" aria-labelledby="select2-lang-selector-container">
											<span class="select2-selection__rendered" id="select2-lang-selector-container" title="English">English</span>
											<span class="select2-selection__arrow" role="presentation">
												<b role="presentation"></b>
											</span>
										</span>
									</span>
									<span class="dropdown-wrapper" aria-hidden="true"></span>
								</span>
							</div>
							<div>
								<input type="hidden" name="CSRFToken" value="a2a5ec40-53df-43c9-87ae-c3e661dbd64d">
							</div>
						</form>
						<div class="checkout-topbar-user">
							<div class="checkout-topbar-member">
								<img class="checkout-topbar-avatar" src="${themeResourcePath}/images/logged_in_user.jpg" alt="avatar">
								<span class="checkout-topbar-membername">Yui Mori</span>
							</div>
							<div class="checkout-topbar-question">
								<span class="checkout-topbar-question-icon"></span>
								<span class="checkout-topbar-service">CUSTOMER SERVICE</span>
							</div>
						</div>
					</div>
				</div>
				<div class="">
					<div class="nav-links-container">
						<ul class="nav-list clearfix"></ul>
					</div>
				</div>
			</div>
		</nav>
	</div>

	<!--search suggest-->
	<div class="popover auto-suggestion-popover fade bottom in" role="tooltip" id="popover698389">
		<div class="popover-arrow arrow" style="left: 71.9353%;"></div>
		<h3 class="popover-title" style="display: none;"></h3>
		<div class="popover-content">
			<button class="search-results-close">x</button>
			<ul>
				<div
					class="category-wrapper js-category-wrapper suggested-words-wrapper js-suggested-words-wrapper js-search-words-wrapper">
					<li class="ui-menu-item">
						<a href="">
							<div class="name">
								<span class="js-name"></span>
								<span class="js-start-of-name"></span>
								<span class="bold js-highlited-part-of-name">nutri</span>
								<span class="js-rest-of-name">cion</span>
							</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="name">
								<span class="js-name"></span>
								<span class="js-start-of-name"></span>
								<span class="bold js-highlited-part-of-name">nutri</span>
								<span class="js-rest-of-name">lite</span>
							</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="name">
								<span class="js-name"></span>
								<span class="js-start-of-name"></span>
								<span class="bold js-highlited-part-of-name">nutri</span>
								<span class="js-rest-of-name">plant</span>
							</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="name">
								<span class="js-name"></span>
								<span class="js-start-of-name"></span>
								<span class="bold js-highlited-part-of-name">nutri</span>
								<span class="js-rest-of-name">tion</span>
							</div>
						</a>
					</li>
				</div>
				<div class="category-wrapper js-category-wrapper products-wrapper hidden-xs hidden-sm">
					<div class="header row">
						<div class="title col-md-6">
							<span>Products</span>
						</div>
						<div class="view-all-container col-md-6">
							<a class="js-products-view-all-results" href="">View all results</a>
						</div>
					</div>
					<li class="ui-menu-item">
						<a href="">
							<div class="thumb">
								<img src="images/1.jpeg" />
							</div>
							<div class="name">Nutrilite Vitamin C</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="thumb">
								<img src="images/2.jpeg" />
							</div>
							<div class="name">Nutrilite Women’s Pack</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="thumb">
								<img src="images/3.jpeg" />
							</div>
							<div class="name">Nutrilite Cal Mag D</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="thumb">
								<img src="images/4.jpeg" />
							</div>
							<div class="name">Nutrilite Double X 31</div>
						</a>
					</li>
					<li class="ui-menu-item">
						<a href="">
							<div class="thumb">
								<img src="images/5.jpeg" />
							</div>
							<div class="name">Nutrilite dietary</div>
						</a>
					</li>
				</div>
			</ul>
		</div>
	</div>
	<!--search end-->
</header>