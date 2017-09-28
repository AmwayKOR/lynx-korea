<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container-fluid main-container">
    <div class="print-hide breadcrumb-section">
        <ol class="breadcrumb">
            <li>
                <a href="homepage.html">Home</a></li>
            <!--<li>-->
                <!--<a href="categoryPage.html">Amway Catalog</a></li>-->
            <li>
                <a href="Vitamins_and_Supplements_Product_listing_page.html">Beauty</a></li>
            <li>
                <a href="Vitamins_and_Supplements_Product_listing_page.html">Makeup</a></li>
            <li>Lips</li></ol>
    </div>
</div>
<div class="header-margin hidden-sm hidden-xs"></div>
<div class="container-fluid main-container">
    <div class="row">
        <div class="title-product-replacement-container">
            <div class="col-sm-12 col-md-12 pdp-info page-title-container">
                <div class="product-details page-title">
                    <div class="name">${fn:escapeXml(product.name)}</div>
                    <div class="item-code">
                        <span>Item #</span>
                        <span class="code">${fn:escapeXml(product.code)}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="mg-product">
            <div class="col-sm-12 col-md-6 product-image-gallery js-product-image-gallery" style="opacity: 1;">
                <div class="image-gallery js-gallery">
                    <div class="image-tab-content">
                        <div id="lips-1" class="tab-pane fade">
                            <img class="product-description__img-main" alt="lips-1" src="images/lip-gloss-swatches.png"></div>
                        <div id="lips-2" class="tab-pane fade">
                            <img class="product-description__img-main" alt="lips-2" src="images/lip-gloss-swatches-pink.png"></div>
                        <div id="lips-3" class="tab-pane fade in active">
                            <img class="product-description__img-main" alt="lips-3" src="images/lip-gloss-swatches.png"></div>
                    </div>
                </div>
                <div class="buttons zoom-center js-zoom-center">
                    <button class="enlarge2 btn btn-link" data-zoom-popup-title="" data-toggle="modal" data-target="#product-zoom-image-panel">
                        <span class="glyphicon glyphicon-zoom-in"></span>
                        <span>Enlarge</span></button>
                </div>
            </div>
            <div class="clearfix hidden-sm hidden-md hidden-lg print-hide"></div>
            <div class="col-sm-12 col-md-6 pdp-info">
                <div class="product-main-info">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="description">${product.summary}
                                <a href="#">Read More</a></div>
                            <div class="lip-color-choose">
                                <p class="lip-color-choose-message">Color: Glitz (Cool, Shimmer)</p>
                                <ul class="lip-color-choose__nav">
                                    <li>
                                        <img data-toggle="tab" href="#lips-1" src="images/glitz-1.png" alt="color-1"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-2" src="images/glitz-2.png" alt="color-2"></li>
                                    <li class="active">
                                        <img data-toggle="tab" href="#lips-3" src="images/glitz-3.png" alt="color-3"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-4" src="images/glitz-4.png" alt="color-4"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-5" src="images/glitz-5.png" alt="color-5"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-6" src="images/glitz-6.png" alt="color-6"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-7" src="images/glitz-7.png" alt="color-7"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-8" src="images/glitz-8.png" alt="color-8"></li>
                                    <li>
                                        <img data-toggle="tab" href="#lips-9" src="images/glitz-9.png" alt="color-9"></li>
                                </ul>
                            </div>
                            <hr class="description__line">
                            <div class="description-vote">
                                <img src="images/five_star.png" alt="star">
                                <a href="#reviewsbody">
                                <span class="description-number">4.0</span>
                                <span class="description-reviews">(32 Reviews)</span>
                                </a>
                            </div>
                            <product:productPricePanel product="${product}" />
                        </div>
                        <div class="col-sm-12 col-md-12">
                            <div class="page-details-variants-select">
                                <div class="page-details-add-to-cart-component">
                                    <div class="addtocart-component">
                                        <div class="amway-theme qty-selector js-qty-selector control-group">
                                            <div class="row">
                                                <div class="size-selector-container">
                                                    <label class="control-label" for="pdpAddtoCartInput">Size</label>
                                                    <select class="size-select text-center js-qty-selector-input form-control" value="60 Packets">
                                                        <option>60 Packets</option>
                                                        <option>30 Packets</option></select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="pull-left qty-selector-container">
                                                    <label class="control-label" for="pdpAddtoCartInput">Qty</label>
                                                    <input type="text" maxlength="3" class="text-center js-qty-selector-input" size="1" value="1" data-max="FORCE_IN_STOCK" data-min="1" name="pdpAddtoCartInput" id="pdpAddtoCartInput" /></div>
                                                <div class="pull-left usage-calc-pdp">
                                                    <a class="usageCalculatorLink" href="javascript:void(0);" title="How Much Do I Need?">
                                                        <span class="icon icon-calculator"></span>How Much Do I Need?</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="stock-wrapper clearfix">
                                            <div class="product-stock">
                                                <div>
                                                    <span class="stock">
                                                        <span class="product-availability">
                                                            <span class="green">
                                                                <span class="icon icon-check-bold"></span>
                                                                <span class="text text-uppercase">In Stock</span></span>
                                                        </span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="actions-wrapper">
                                            <div class="actions">
                                                <div class="AddToCart-AddToCartAction" data-index="1">
                                                    <div id="addToCartTitle" class="display-none">Added to Your Shopping Cart</div>
                                                    <form id="addToCartForm" class="add_to_cart_form col-md-12" action="#" method="post">
                                                        <input type="hidden" maxlength="3" size="1" id="qty" name="qty" class="qty js-qty-selector-input" value="1" />
                                                        <input type="hidden" name="productCodePost" value="119889" />
                                                        <button id="addToCartButton" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">Add to cart</button>
                                                        <button id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">BUY NOW</button>
                                                        <div>
                                                            <input type="hidden" name="CSRFToken" value="1bcd7a5f-3229-4b1e-8f3f-1cbfa88e4768" /></div>
                                                    </form>
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
                                                        <div class="add-button-wrap add-to-wish-list-conatiner">
                                                            <div>
                                                                <button type="button" class="btn btn-link btn-block add-to-ditto js-add-to-ditto-button" id="119889">
                                                                    <span class="icon-add-wish-list"></span>Add to Wish List</button>
                                                                <div class="display-none add-to-ditto-component-container js-add-to-ditto-component-container">
                                                                    <div class="panel-content">
                                                                        <div class="js-add-to-component">
                                                                            <ul class="panel-list add-to-list js-existing-ditto-list"></ul>
                                                                            <div class="panel-action-wrapper">
                                                                                <div class="panel-action-btn js-add-to-ditto-component-link">Add to List</div>
                                                                                <div class="panel-action-text-btn pull-right js-add-new-ditto-schedule-link-component">Create new list</div></div>
                                                                            <input type="hidden" class="js-add-to-component-code-input" value="119889" />
                                                                            <input type="hidden" class="js-add-to-component-quantity-input" value="1" />
                                                                            <div class="hidden">
                                                                                <div id="createNewDittoModal" class="amway-theme create-new-ditto-modal js-create-new-ditto-modal js-create-new-modal">
                                                                                    <form id="saveDittoScheduleForm" class="create-ditto-schedule-form" action="https://uat-160829146.us-east-1.elb.amazonaws.com/lynxstorefront/lynx/en/my-account/ditto/save" method="post" autocomplete="off">
                                                                                        <div class="row new-ditto-row">
                                                                                            <div class="col-sm-12 col-md-4 col-lg-3 create-ditto-schedule-form-element-lebel">
                                                                                                <label for="saveDittoName">
                                                                                                    <h6>Ditto Name</h6></label>
                                                                                            </div>
                                                                                            <div class="col-sm-12 col-md-6 col-lg-5">
                                                                                                <input id="saveDittoName" name="name" class="create-ditto-input js-create-new-ditto-input" type="text" value="" maxlength="255" /></div>
                                                                                        </div>
                                                                                        <div class="row new-ditto-row">
                                                                                            <div class="col-xs-12 col-md-12">
                                                                                                <div class="new-ditto-button-wrapper">
                                                                                                    <button type="submit" class="btn primary small js-create-new-ditto-btn" id="saveDittoButton" disabled="">Create Ditto</button></div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div>
                                                                                            <input type="hidden" name="CSRFToken" value="1bcd7a5f-3229-4b1e-8f3f-1cbfa88e4768" /></div>
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                            <div class="hidden">
                                                                                <div id="dittoMultiAddingSuccessModal" class="ditto-multi-adding-success-modal js-ditto-multi-adding-success-modal add-to-cart-multi-success-modal"></div>
                                                                            </div>
                                                                            <div class="hidden">
                                                                                <div id="dittoAddingSuccessModal" class="ditto-adding-success-modal js-ditto-adding-success-modal"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
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
</div>