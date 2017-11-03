<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="title-product-replacement-container">
        <div class="col-sm-12 col-md-12 pdp-info page-title-container">
            <div class="product-details page-title">
                <div class="name">${fn:escapeXml(product.name)}</div>
                <div class="item-code">
                    <span><spring:theme code="product.item.number.label" /></span>
                    <span class="code">${fn:escapeXml(product.code)}</span></div>
                </div>
        </div>
    </div>
    <div class="mg-product">
        <product:productImagePanel galleryImages="${galleryImages}" />
        <div class="clearfix hidden-sm hidden-md hidden-lg print-hide"></div>
        <div class="col-sm-12 col-md-6 pdp-info">
            <div class="product-main-info">
                <div class="row">
                    <div class="col-md-12">
                        <div class="description">${product.summary}</div>
                        <cms:pageSlot position="ColorVariantSelector" var="component">
                            <cms:component component="${component}" />
                        </cms:pageSlot>
                        <hr class="description__line">
                        <product:productReviewSummary product="${product}" showLinks="true"/>
                        <product:productPricePanel product="${product}" />
                    </div>
                    <div class="col-sm-12 col-md-12">
                        <div class="page-details-variants-select">
                            <div class="page-details-add-to-cart-component">
                                <div class="addtocart-component">
                                    <div class="amway-theme qty-selector js-qty-selector control-group">
                                        <cms:pageSlot position="VariantSelector" var="component">
                                            <cms:component component="${component}" />
                                        </cms:pageSlot>
                                        <cms:pageSlot position="AddToCart" var="component" >
                                            <cms:component component="${component}" />
                                        </cms:pageSlot>
                                                <div class="row add-to-cart-extra-actions col-xs-12 col-sm-12 col-md-12 js-add-to-actions-wrapper">
                                                    <div class="add-button-wrap add-to-shopping-list-container new-add-to-shopping-list-container">
                                                        <button type="button" class="btn btn-link btn-block js-add-list-shopping-button" data-toggle="dropdown" id="">
                                                            <span class="icon-add-shopping-list"></span>
                                                            <span class="shopping-list-button-text">Add To Shopping List</span></button>
                                                        <div class="cart-detail__dropdown-menu dropdown-menu" role="menu">
                                                            <ul>
                                                                <li>
                                                                    <input class="" id="toSL1" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toSL1"></label><span>1</span>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toSL2" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toSL2"></label><span>TEST2</span>

                                                                </li>
                                                                <li>
                                                                    <input class="" id="toSL3" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toSL3"></label><span>test1</span>

                                                                </li>
                                                                <li>
                                                                    <input class="" id="toSL4" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toSL4"></label><span>GG</span>

                                                                </li>
                                                                <li>
                                                                    <input class="" id="toSL5" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toSL5"></label><span>GG8</span>

                                                                </li>

                                                            </ul>
                                                            <div class="dropdown-menu-bottom">
                                                                <button id="addToLIST" class="btn btn-primary">ADD TO LIST</button>
                                                                <a href="javascript:void(0);" id="">Create New List</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="add-button-wrap add-to-ditto-container">
                                                        <button type="button" class="btn btn-link btn-block add-to-ditto js-add-to-ditto-button" data-toggle="dropdown">
                                                            <span class="icon-add-to-ditto"></span>Add to DITTO
                                                        </button>
                                                        <div class="cart-detail__dropdown-menu dropdown-menu" role="menu">
                                                            <ul>
                                                                <li>
                                                                    <input class="" id="toDitto1" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto1"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toDitto2" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto2"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toDitto3" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto3"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toDitto4" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto4"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toDitto5" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto5"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toDitto6" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto6"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                                <li>
                                                                    <input class="" id="toDitto7" name="toDitto" type="checkbox">
                                                                    <label class="cart-detail__addto-options" for="toDitto7"></label><span>JeniferJones'First DITTO</span>
                                                                    <p>Created for Jenifer Jones</p>
                                                                </li>
                                                            </ul>
                                                            <div class="dropdown-menu-bottom">
                                                                <button id="addToDitto" class="btn btn-primary">ADD TO DITTO</button>
                                                                <a id="creatNewDitto" href="javascript:void(0);">Create New DITTO</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row add-to-cart-extra-actions col-xs-12 col-sm-12 col-md-12 js-add-to-actions-wrapper link-line">
                                                    <div>
                                                        <a href="">
                                                        <img href="#" src="images/new-twitter2.png" alt="twitter"></a></div>
                                                    <div>
                                                        <a href="">
                                                        <img href="#" src="images/new-f.png" alt="facebook"></a></div>
                                                    <div>
                                                        <a href="">
                                                        <img href="#" src="images/new-line.png" alt="line"></a></div>
                                                    <div>
                                                        <a href="">
                                                        <img href="#" src="images/new-tel.png" alt="tel"></a></div>
                                                    <div>
                                                        <a href="">
                                                        <img href="#" src="images/new-talk.png" alt="talk"></a></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>