<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<template:page pageTitle="${pageTitle}">

	<div class="container-fluid main-container">
		<div class="row">
			<div class="searchEmptyPageMiddle">
               <div class=" searchEmptyPageMiddle-component">

                           
               <div class="content">
				<cms:pageSlot position="MiddleContent" var="comp">
						<div class="new-search-icon">
							<img src="images/new-search-icon.png" />
						</div>
						<cms:component component="${comp}" />
					</cms:pageSlot>
				</div>
			</div></div>
		</div>
	</div>



	<div class="searchEmptyPageMiddle-component">
		<div class="ui-front">
			<form name="search_form_SearchBox" method="get">
				<!-- <div class="input-group">
					<input type="text" class="form-control js-site-search-input"
						name="text" value="" maxlength="100"
						placeholder="Search by name,SKU,or brand" /> <span
						class="input-group-btn">
						<button class="btn btn-link" type="submit">
							<span class="icon-search"></span>
						</button>
					</span>
				</div> -->
				<div class="input-group">
					<input type="text"
						class="form-control js-site-search-input ui-autocomplete-input"
						name="text" value="" maxlength="100" placeholder="Search"
						data-options="{&quot;autocompleteUrl&quot; : &quot;/lynxstorefront/lynx/en/search/autocomplete/SearchBox&quot;,&quot;minCharactersBeforeRequest&quot; : &quot;2&quot;,&quot;waitTimeBeforeRequest&quot; : &quot;500&quot;,&quot;displayProductImages&quot; : true}"
						autocomplete="off" /> <span class="input-group-btn"> <a
						class="btn btn-link" type="submit"> <span class="icon-search"></span>
					</a>
					</span>
				</div>
			</form>
		</div>
	</div>

	<div class="amway-suggest col-md-12">
		<h1 class="amway-suggest__title">BEST SELLERS</h1>
		<div id="productSuggestListTabs" class="amway-suggest__list">
			<div class="amway-suggest__item-container">
				<div class="amway-suggest__item">
					<!--<img src="images/limited_stock.png" class="amway-suggest__flag" alt="limited stock">-->
					<div class="plp-item-label limit-stock">
						Limited Stock<span class="right-white"></span>
					</div>
					<div class="amway-suggest__item-content amwahover">
						<img src="images/product_list_item1.png"
							class="amway-suggest__thumbnail" alt="product">
						<div class="amway-suggest__item-detail">
							<p class="amway-suggest__item-title">Nutrilite® Twist Tubes
								2GO™ - Immunity Health Lorem Ipsum Dolor Sit Amet Consectetur...</p>
							<p class="amway-suggest__item-count">30 Count</p>
							<p class="amway-suggest__item-number">Item #: 116872</p>
							<div
								class="amway-suggest__item-title amway-suggest__item-aboprice">
								<span class="amway-suggest__item-abolabel">ABO Cost:</span> <span
									class="amway-suggest__item-abovalue">$16.62</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">Retail Price:</span>
								<span class="amway-suggest__item-abovalue">$25.55</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">PV / BV:</span> <span
									class="amway-suggest__item-abovalue">4.50 / 14.21</span>
							</div>
						</div>
					</div>
					<div class="amway-suggest__item-link">
						<a href="#" class="btn-blue-white" data-toggle="modal"
							data-target="#cart-modal" tabindex="0">add to cart</a>
					</div>
				</div>
			</div>
			<div class="amway-suggest__item-container">
				<div class="amway-suggest__item">
					<div class="amway-suggest__item-content amwahover">
						<img src="images/product_list_item2.png"
							class="amway-suggest__thumbnail" alt="product">
						<div class="amway-suggest__item-detail">
							<p class="amway-suggest__item-title">Nutrilite® Heart Health
								Pack Has a Long Name – Wraps to Two Lines</p>
							<p class="amway-suggest__item-count"></p>
							<p class="amway-suggest__item-number">Item #: 116990</p>
							<div
								class="amway-suggest__item-title amway-suggest__item-aboprice">
								<span class="amway-suggest__item-abolabel">ABO Cost:</span> <span
									class="amway-suggest__item-abovalue">$43.25</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">Retail Price:</span>
								<span class="amway-suggest__item-abovalue">$47.75</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">PV / BV:</span> <span
									class="amway-suggest__item-abovalue">3.07 / 2.07</span>
							</div>
						</div>
					</div>
					<div class="amway-suggest__item-link">
						<a href="#" class="btn-blue-white" tabindex="0">add to cart</a>
					</div>
				</div>
			</div>
			<div class="amway-suggest__item-container">
				<div class="amway-suggest__item">
					<!--<img src="images/bundle_save.png" class="amway-suggest__flag" alt="bundle save">-->
					<div class="plp-item-label bundle-save">
						Bundle & Save<span class="right-white"></span>
					</div>
					<div class="amway-suggest__item-content amwahover">
						<img src="images/product_list_item3.png"
							class="amway-suggest__thumbnail" alt="product">
						<div class="amway-suggest__item-detail">
							<p class="amway-suggest__item-title">Nutrilite® Phyto2GO™
								Immunity Drink Twist Cap Starter Pack</p>
							<p class="amway-suggest__item-count">60 Count</p>
							<p class="amway-suggest__item-number">Item #: 119390</p>
							<div
								class="amway-suggest__item-title amway-suggest__item-aboprice">
								<span class="amway-suggest__item-abolabel">ABO Cost:</span> <span
									class="amway-suggest__item-abovalue">$26.00</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">Retail Price:</span>
								<span class="amway-suggest__item-abovalue">$46.15</span> <span
									class="amway-suggest__item-abovalue">$31.00</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">PV / BV:</span> <span
									class="amway-suggest__item-abovalue">3.07 / 2.07</span>
							</div>
						</div>
					</div>
					<div class="amway-suggest__item-link">
						<a href="#" class="btn-blue-white" tabindex="0">add to cart</a>
					</div>
				</div>
			</div>
			<div class="amway-suggest__item-container">
				<div class="amway-suggest__item">
					<div class="amway-suggest__item-content amwahover">
						<img src="images/product_list_item4.png"
							class="amway-suggest__thumbnail" alt="product">
						<div class="amway-suggest__item-detail">
							<p class="amway-suggest__item-title">Nutrilite® Twist Tubes
								2GO™ - Immunity Health Lorem Ipsum Dolor Sit Amet Consectetur...</p>
							<p class="amway-suggest__item-count">30 Count</p>
							<p class="amway-suggest__item-number">Item #: 116872</p>
							<div
								class="amway-suggest__item-title amway-suggest__item-aboprice">
								<span class="amway-suggest__item-abolabel">ABO Cost:</span> <span
									class="amway-suggest__item-abovalue">$16.62</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">Retail Price:</span>
								<span class="amway-suggest__item-abovalue">$25.55</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">PV / BV:</span> <span
									class="amway-suggest__item-abovalue">4.50 / 14.21</span>
							</div>
						</div>
					</div>
					<div class="amway-suggest__item-link">
						<a href="#" class="btn-blue-white" tabindex="0">add to cart</a>
					</div>
				</div>
			</div>
			<div class="amway-suggest__item-container">
				<div class="amway-suggest__item">
					<div class="amway-suggest__item-content amwahover">
						<img src="images/product_list_item4.png"
							class="amway-suggest__thumbnail" alt="product">
						<div class="amway-suggest__item-detail">
							<p class="amway-suggest__item-title">Nutrilite® Twist Tubes
								2GO™ - Immunity Health Lorem Ipsum Dolor Sit Amet Consectetur...</p>
							<p class="amway-suggest__item-count">30 Count</p>
							<p class="amway-suggest__item-number">Item #: 116872</p>
							<div
								class="amway-suggest__item-title amway-suggest__item-aboprice">
								<span class="amway-suggest__item-abolabel">ABO Cost:</span> <span
									class="amway-suggest__item-abovalue">$16.62</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">Retail Price:</span>
								<span class="amway-suggest__item-abovalue">$25.55</span>
							</div>
							<div class="amway-suggest__item-retailprice">
								<span class="amway-suggest__item-abolabel">PV / BV:</span> <span
									class="amway-suggest__item-abovalue">4.50 / 14.21</span>
							</div>
						</div>
					</div>
					<div class="amway-suggest__item-link">
						<a href="#" class="btn-blue-white" tabindex="-1">add to cart</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%-- <c:url value="/" var="homePageUrl" />
	<cms:pageSlot position="SideContent" var="feature" element="div" class="side-content-slot cms_disp-img_slot searchEmptyPageTop">
		<cms:component component="${feature}" element="div" class="no-space yComponentWrapper searchEmptyPageTop-component"/>
	</cms:pageSlot>
	
	<div class="search-empty">
		<div class="headline">
			<spring:theme code="search.no.results" arguments="${searchPageData.freeTextSearch}"/> 
		</div>
		<a class="btn btn-default  js-shopping-button" href="${homePageUrl}">
			<spring:theme code="general.continue.shopping" text="Continue Shopping"/>
		</a>
	</div>
	
	<cms:pageSlot position="MiddleContent" var="comp" element="div" class="searchEmptyPageMiddle">
		<cms:component component="${comp}" element="div" class="yComponentWrapper searchEmptyPageMiddle-component"/>
	</cms:pageSlot>

	<cms:pageSlot position="BottomContent" var="comp" element="div" class="searchEmptyPageBottom">
		<cms:component component="${comp}" element="div" class="yComponentWrapper searchEmptyPageBottom-component"/>
	</cms:pageSlot> --%>

</template:page>