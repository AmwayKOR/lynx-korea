<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>

<template:page pageTitle="${pageTitle}">
        <div class="container-fluid main-container">
            <product:productDetailsPanel />
            <product:productPageTabs />
            <div class="recommendation swatches-description__margin-minus">
                <img class="recommendation__image" src="images/b_spot_swatch.png">
                <div class="recommendation__message">
                    <blockquote class="recommendation__message-main">Selling Artistry's top-of-the-
                        <br>line bundles helped drive my
                        <br>business - and helped make
                        <br>me reach Platinum.</blockquote>
                    <p class="recommendation__message-signature">Platinum ABO: Karen Martin</p>
                    <a class="recommendation__message-link" href="#">learn more
                        <span class="recommendation__message-icon glyphicon glyphicon-menu-right"></span></a>
                </div>
            </div>
		<div class="row simpleimagecomponent pcp-banner new-advice block">
			<div class="amway-theme col-xs-12">
				<div class="three-tile-component">
					<cms:pageSlot position="ToolsAndAdviceProductDetailSlot"
						var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
					<div class="row">
						<div class="item col-xs-12 col-md-4">
							<cms:pageSlot position="ToolsAndAdviceBannerProductDetailSlot1"
								var="component1">
								<cms:component component="${component1}" />
							</cms:pageSlot>
						</div>
						<div class="item col-xs-12 col-md-4">
							<cms:pageSlot position="ToolsAndAdviceBannerProductDetailSlot2"
								var="component2">
								<cms:component component="${component2}" />
							</cms:pageSlot>
						</div>
						<div class="item col-xs-12 col-md-4">
							<cms:pageSlot position="ToolsAndAdviceBannerProductDetailSlot3"
								var="component3">
								<cms:component component="${component3}" />
							</cms:pageSlot>
						</div>
					</div>
				</div>
			</div>
		</div>

		<cms:pageSlot position="UpSelling" var="comp">
			<cms:component component="${comp}" />
		</cms:pageSlot>
		
		<div class="row simpleimagecomponent pcp-banner">
			<div class="amway-theme col-xs-12">
				<div class="three-tile-component">
					<cms:pageSlot position="LearningOpportunitiesProductDetailSlot"
						var="component4">
						<cms:component component="${component4}" />
					</cms:pageSlot>
					

							<cms:pageSlot
								position="LearningOpportunitiesBannerProductDetailSlot1"
								var="component5">
								<cms:component component="${component5}" />
							</cms:pageSlot>
				</div>
			</div>
		</div>



		<div class="row">
                <div class="amway-recentlyviewed col-md-12">
                    <h2 class="amway-learning__title">
                        <span class="mH">Your</span>Recently Viewed
                        <span class="mH">Items</span></h2>
                    <div class="product-recentlyviewed__imagelist" id="recentlyViewedListTab">
                        <div>
                            <img class="product-recentlyviewed__img" src="images/vitamin-c.png" alt="vitamin-c"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/lip-gloss.png" alt="lip-gloss"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/protein-powder-sm.png" alt="protein-powder-sm"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/truvivity.png" alt="truvivity"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/perfect-pack.png" alt="perfect-pack"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/cleanser.png" alt="cleanser"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/blush.png" alt="blush"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/blush.png" alt="blush"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/perfect-pack.png" alt="perfect-pack"></div>
                        <div>
                            <img class="product-recentlyviewed__img" src="images/lip-gloss.png" alt="lip-gloss"></div>
                    </div>
                </div>
            </div>

        </div>
    <div id="add-to-cart-box"class="cbox">
        <div class="cart-popup__dialog">
            <div class="cart-popup__header">
                <span class="cart-popup__header-text">added to your shopping cart
                    <img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
            <div class="cart-popup__content">
                <div class="cart-popup__item-info amwahover">
                    <img src="images/lo-c-bundle.png" class="cart-popup__thumbnail" alt="product">
                    <div class="cart-popup__item-detail">
                        <p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name – Wraps to Two Lines</p>
                        <p class="cart-popup__item-count">60 Count</p>
                        <span class="cart-popup__item-number">Item #: 116991</span>
                        <div class="cart-popup__item-title cart-popup__item-aboprice">
                            <span>ABO Price:</span>
                            <span class="product-list__item-abovalue">$16.62</span></div>
                        <div class="cart-popup__item-retailprice">
                            <span>Retail Price:</span>
                            <span class="product-list__item-abovalue">$25.55</span></div>
                        <div class="cart-popup__item-retailprice">
                            <span>PV / BV:</span>
                            <span class="product-list__item-abovalue">4.50 / 14.21</span></div>
                        <div class="cart-popup__quantity cart-popup__item-retailprice">
                            <span class="cart-popup__qty">Qty: 1</span>
                            <a>Edit</a>
                        </div>
                    </div>
                </div>
                <div class="cart-popup__item-link">
                    <a href="Checkout-1-shippingAndDelivery.html" class="btn-blue-white">check out</a>
                    <a class="cart-popup__item-link-text" href="#">Continue Shopping</a></div>
            </div>
        </div>
    </div>
    <div id="colorbox" class="lynx-modal-window add-to-shopping-list-box" role="dialog" tabindex="-1">
        <div id="cboxWrapper">
            <div>
                <div id="cboxTopLeft"></div>
                <div id="cboxTopCenter"></div>
                <div id="cboxTopRight"></div>
            </div>
            <div style="clear: left;">
                <div id="cboxMiddleLeft"></div>
                <div id="cboxContent">
                    <div id="cboxLoadedContent">
                        <div id="createNewShoppingListModal" class="amway-theme create-new-shopping-list-modal js-create-new-shopping-list-modal js-create-new-modal">
                            <div class="amway-theme">
                                <form id="saveShoppingListForm" class="create-shopping-list-form" action="" method="post">
                                    <div class="row new-shopping-list-row">
                                        <div class="description">
                                            You can create shopping lists for yourself, your uplines, or your downlines. It's a simple way to keep track of your customers' favorite products, manage your orders, and share your lists.
                                        </div>
                                    </div>
                                    <div class="row new-shopping-list-row">
                                        <div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
                                            <label for="newShoppingListName"> <h6>New List Name</h6> </label>
                                        </div>
                                        <div class="col-sm-12 col-md-8 col-lg-8">
                                            <input id="newShoppingListName" name="name" type="text" class="shopping-list-input js-create-shopping-list-input" required="required"   value="" />
                                        </div>
                                    </div>
                                    <div class="row new-shopping-list-row">
                                        <div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
                                            <h6>List Recipient</h6>
                                        </div>
                                        <div class="col-sm-12 col-md-8 col-lg-8">
                                            <div class="radio-wrapper">
                                                <label class="amw-radio-wrap radio-label"> <input type="radio" id="me" value="Me" name="ownerType" checked="checked" class="js-me-button amw-global-radio" required="required" /> <span class="amw-radio-overlay"></span><span class="amw-label-radio-text">Me</span> </label>
                                            </div>
                                            <div class="radio-wrapper">
                                                <label class="amw-radio-wrap radio-label"> <input type="radio" id="mySponsor" value="Sponsor" name="ownerType" class="js-my-sponsor-button amw-global-radio" required="required" /> <span class="amw-radio-overlay"></span><span class="amw-label-radio-text">My Sponsor [Sponsor Name Goes Here]</span> </label>
                                            </div>
                                            <div class="radio-wrapper los-search-wrap">
                                                <label class="amw-radio-wrap radio-label"> <input type="radio" id="personalySponsored" value="Downline" name="ownerType" class="js-downlines-button amw-global-radio" required="required" /> <span class="amw-radio-overlay"></span><span class="amw-label-radio-text">Customs List from My Contacts</span> </label>
                                                <div class="new-ditto-search-contacts amway-theme">
                                                    <span class="new-ditto-link-wrap"> <a href="" class="losSearchLink new-ditto-link" title="Search: My contacts" data-single-select=""> <span class="new-ditto-icon icon-add-user"></span> <span class="link-text">Search Contacts</span> </a> </span>
                                                    <input id="los-contacts" name="ownerIDs" type="hidden" />
                                                    <input id="los-contacts-type" name="ownerType" type="hidden" />
                                                    <input id="los-contacts-name" name="ownerName" type="hidden" />
                                                    <span class="new-ditto-link-wrap"> <a href="" class="losSearchLink new-ditto-link disabled" title="Search: My contacts" data-single-select=""> <span class="link-text js-los-search-total-value">(
                                                     <t id="js-los-search-total">0</t>)Total</span> </a> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="radio" id="me" value="Me" name="ownerType" checked="checked" required="required" hidden="hidden" />
                                    <hr>
                                    <div class="row new-shopping-list-row">
                                        <div class="col-xs-12 col-md-12">
                                            <div class="shopping-list-row-button-wrapper">
                                                <button type="submit" class="btn btn-primary small js-create-shopping-list-btn" id="createShoppingList"> Create List </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <input type="hidden" name="CSRFToken" value="de7f6334-b3eb-4b8e-be75-47acb3226fbe" />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="cboxTitle">
                        <div class="headline">
                            <span class="headline-text">Create New Shopping List</span>
                        </div>
                    </div>
                    <div id="cboxCurrent"></div>
                    <!--<button type="button" id="cboxPrevious" style="display: none;"></button>-->
                    <!--<button type="button" id="cboxNext" style="display: none;"></button>-->
                    <!--<button id="cboxSlideshow" style="display: none;"></button>-->
                    <!--<div id="cboxLoadingOverlay" style="float: left; display: none;"></div>-->
                    <!--<div id="cboxLoadingGraphic" style="float: left; display: none;"></div>-->
                    <button type="button" id="cboxClose"><span class="modal-close-icon">&times;</span></button>
                </div>
                <div id="cboxMiddleRight"></div>
            </div>
            <div style="clear: left;">
                <div id="cboxBottomLeft"></div>
                <div id="cboxBottomCenter"></div>
                <div id="cboxBottomRight"></div>
            </div>
        </div>
        <div style="position: absolute; width: 9999px; visibility: hidden; max-width: none; display: none;"></div>
    </div>
    <div id="add-to-shopping-list" class="cbox">
        <div class="cart-popup__dialog">
            <div class="cart-popup__header">
                <span class="cart-popup__header-text">added to shopping list
                    <img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
            <div class="cart-popup__content">
                <div class="cart-popup__item-info amwahover">
                    <img src="images/lip-gloss-swatches.png" class="cart-popup__thumbnail" alt="product">
                    <div class="cart-popup__item-detail">
                        <p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name – Wraps to Two Lines</p>
                        <p class="cart-popup__item-count">60 Count</p>
                        <span class="cart-popup__item-number">Item #: 116991</span>
                        <div class="cart-popup__item-title cart-popup__item-aboprice">
                            <span>ABO Price:</span>
                            <span class="product-list__item-abovalue">$16.62</span></div>
                        <div class="cart-popup__item-retailprice">
                            <span>Retail Price:</span>
                            <span class="product-list__item-abovalue">$25.55</span></div>
                        <div class="cart-popup__item-retailprice">
                            <span>PV / BV:</span>
                            <span class="product-list__item-abovalue">4.50 / 14.21</span></div>
                        <div class="cart-popup__quantity cart-popup__item-retailprice">
                            <span class="cart-popup__qty">Qty: 1</span>
                            <!--<a>Edit</a>-->
                        </div>
                    </div>
                </div>
                <div class="cart-popup__item-link">
                    <a href="shopping-list-landing.html" class="btn-blue-white">go to shopping list</a>
                    <a class="cart-popup__item-link-text" href="#" onclick="javascript:location.reload()">Continue Shopping</a></div>
            </div>
        </div>
    </div>
    <div class="cbox cbox2">
        <div class="cart-popup__dialog need-dialog">
            <div class="cart-popup__header">
                <span class="cart-popup__header-text">HOW MUCH DO I NEED?
                    <img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
            <div class="cart-popup__content">
                <div class="car-popup-item-des">
                    <p>
                        The use calculator helps you determine how much of a product your household will consume over time - so you can order what you need, when you need it. A great tool for setting up DITTO scheduled orders.
                    </p>
                </div>
                <div class="cart-popup__item-info amwahover">
                    <img src="${themeResourcePath}/images/heart-health180.png" class="cart-popup__thumbnail" alt="product">
                    <div class="cart-popup__item-detail">
                        <p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name</p>
                        <hr>

                        <div class="cart-popup__item-retailprice">
                            <span>Usage Rate:</span>
                            <span class="product-list__item-abovalue">181Use(s) Per Packet</span>
                        </div>
                        <div class="cart-popup__item-retailprice">
                            <span>Recommended Use:</span>
                            <span class="product-list__item-abovalue">2 per day</span>
                        </div>
                        <div class="cart-popup__item-retailprice">
                            <span><b>Retail Cost Per Use</b></span>
                            <span class="product-list__item-abovalue"><b>$0.13</b></span>
                        </div>
                        <hr>
                        <p class="cart-popup__item-title ">FREQUENCY OF USAGE</p>
                        <div class="usage-content">
                            <input type="text" class="form-control">
                            <label>Times Per</label>
                            <select id="time-unit" class="prw-jump-to-selection select2-hidden-accessible" tabindex="-2" aria-hidden="true">
                                <option>day</option>
                                <option>week</option>
                                <option>month</option>
                                <option>year</option>
                            </select>
                        </div>
                        <button class="btn-blue-white btn-calculate">CALCULATE USAGE</button>
                        <div class="usage-content-hide">
                            <p>Help / directional text will go here laoreet dolore magna aliquam</p>
                            Your Purchase Quantity: <span>3 per year</span>
                            <button class="btn-blue-white btn-calculate-close">CLOSE</button>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="cbox cboxAddToYourDitto">
        <div class="cart-popup__dialog">
            <div class="cart-popup__header">
                <span class="cart-popup__header-text">added to your ditto
                    <img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
            <div class="cart-popup__content">
                <div class="car-popup-item-des">
                    <b>1</b>item added to 3 DITTO lists
                </div>
                <div class="cart-popup__item-info amwahover">
                    <div class="item-list-module in-dialog">
                        <ul>
                            <li>
                                <h5><a href="">Jennifer Jones'First DITTO(20)</a><label class="label-date">Order Drop Date 11/15/16</label></h5>
                                <span>Created for Jennifer Jones</span>
                            </li>
                            <li>
                                <h5><a href="">Second DITTO List Name(54)</a><label class="label-date">Order Drop Date 12/15/15</label></h5>
                                <span>Created for Jennifer Jones</span>
                            </li>
                            <li>
                                <h5><a href="">My Other DITTO List Has a Long Name(999)</a><label class="label-date">Order Drop Date 05/12/10</label></h5>
                                <span>Created for Jennifer Jones</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="cart-popup__item-link">
                    <a href="Ditto-Site.html" class="btn-blue-white">GO TO DITTO</a>
                    <a class="cart-popup__item-link-text closeCbox" href="javascript:void(0);">Continue Shopping</a></div>
            </div>
        </div>
    </div>
    <div class="cbox cboxCreateNewDitto">
        <div class="cart-popup__dialog">
            <div class="cart-popup__header">
                <span class="cart-popup__header-text">Create a new ditto schedule
                    <img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
            <div class="cart-popup__content">

                <div class="cart-popup__item-info amwahover">
                    <div class="dialog-form-module">
                        <div class="row">
                            <div class="col-md-4 form-name">Ditto name</div>
                            <div class="col-md-8 form-opera"><input type="text" class="form-control"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 form-name">order recipient</div>
                            <div class="col-md-8 form-opera">
                                <input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1">
                                <label for="radio1" data-parent="#order1">Me</label>
                                <input class="amwa-radio" type="radio" name="optradio" id="radio2" value="radio2">
                                <label for="radio2" data-parent="#order1">My Sponsor[Sponsor Name Goes Here]</label>
                                <input class="amwa-radio" type="radio" name="optradio" id="radio3" value="radio3">
                                <label for="radio3" data-parent="#order1">Custom List from My Contacts</label>
                                <div class="search-contacts">
                                    <img src="images/add_new_people.png"><a href="javascript:void(0);" id="searchContacts">Search Contacts</a>|&nbsp;(0)Total
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cart-popup__item-link">
                    <a href="javascript:void(0);" class="closeCbox btn-blue-white">CREATE DITTO</a>
                    <a class="cart-popup__item-link-text closeCbox" href="javascript:void(0);">Cancel</a></div>
            </div>
        </div>
    </div>
    <div class="cbox cboxCreateNewDittoNext">
        <div class="cart-popup__dialog">
            <div class="cart-popup__header">
                <span class="cart-popup__header-text">Create a new ditto schedule
                    <img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
            <div class="cart-popup__content">

                <div class="cart-popup__item-info amwahover height-auto">
                    <div class="dialog-form-module">
                        <a href="#" id="backPrevious"> Go Back to Previous Screen</a>
                        <h6>QUICKFIND:<label>Enter the Name or ID Number of the IBO/Customer</label></h6>
                        <div class="search-wrap">
                            <input class="form-control" id="contacts-search">
                            <div class="contacts-auto-suggestion">
                                <div class="suggestion-header container-fluid">
                                    <div class="suggestion-row row">
                                        <div class="col-md-6">NAME</div>
                                        <div class="col-md-3">TYPE</div>
                                        <div class="col-md-3">ID NUMBER</div>
                                    </div>
                                </div>
                                <div class="suggestion-body container-fluid">
                                    <div class="suggestion-row row">
                                        <div class="suggestion-name col-md-6">Andrew Allen</div>
                                        <div class="col-md-3">IBO</div>
                                        <div class="col-md-3">123124124124</div>
                                    </div>
                                    <div class="suggestion-row row">
                                        <div class="suggestion-name col-md-6">Andrew Hackman</div>
                                        <div class="col-md-3">IBO</div>
                                        <div class="col-md-3">123124124124</div>
                                    </div>
                                    <div class="suggestion-row row">
                                        <div class="suggestion-name col-md-6">Andrew Frisch</div>
                                        <div class="col-md-3">IBO</div>
                                        <div class="col-md-3">123124124124</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h6>SELECT CUSTOMERS/IBO:</h6>
                        <div class="table container-fluid">
                            <div class="table-header row">
                                <div class="col-md-7">NAME<i class="glyphicon glyphicon-menu-down"></i></div>
                                <div class="col-md-2">TYPE<i class="glyphicon glyphicon-menu-down"></i></div>
                                <div class="col-md-3">ID NUMBER</div>
                            </div>
                            <div class="table-body container-fluid">
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category1" name="category1" type="checkbox">
                                        <label for="category1">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category2" name="category1" type="checkbox">
                                        <label for="category2">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category3" name="category1" type="checkbox">
                                        <label for="category3">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category5" name="category1" type="checkbox">
                                        <label for="category5">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category4" name="category1" type="checkbox">
                                        <label for="category4">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category6" name="category1" type="checkbox">
                                        <label for="category6">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-7">
                                        <input class="login-form__remember" id="category7" name="category1" type="checkbox">
                                        <label for="category7">Aaron Ackerman</label>
                                    </div>
                                    <div class="col-md-2">
                                        IBO
                                    </div>
                                    <div class="col-md-3">1231241241</div>
                                </div>
                            </div>
                        </div>
                        <h6>RECIPIENT LIST:<span>(5)Total</span></h6>
                        <div class="border-from">
                            <div>Aaron Ackerman<i class="glyphicon glyphicon-remove"></i></div>
                            <div>Aaron Ackerman<i class="glyphicon glyphicon-remove"></i></div>
                            <div>Aaron Ackerman<i class="glyphicon glyphicon-remove"></i></div>
                            <div>Aaron Ackerman<i class="glyphicon glyphicon-remove"></i></div>
                            <div>Aaron Ackerman<i class="glyphicon glyphicon-remove"></i></div>
                            <div>Aaron Ackerman<i class="glyphicon glyphicon-remove"></i></div>
                        </div>
                    </div>
                </div>
                <div class="cart-popup__item-link">
                    <a href="javascript:void(0);" class="btn-blue-white closeCbox">CONFIRM</a>
                </div>
            </div>
        </div>
    </div>
</template:page>