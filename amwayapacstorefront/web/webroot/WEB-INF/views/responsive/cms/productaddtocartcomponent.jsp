<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="isForceInStock" value="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}"/>
<c:choose>
  <c:when test="${isForceInStock}">
    <c:set var="maxQty" value="FORCE_IN_STOCK"/>
  </c:when>
  <c:otherwise>
    <c:set var="maxQty" value="${product.stock.stockLevel}"/>
  </c:otherwise>
</c:choose>

<c:set var="qtyMinus" value="1" />
<c:url value="/cart" var="cartUrl"/>
                    <div class="row">
                            <div class="pull-left qty-selector-container">
                                <label class="control-label" for="pdpAddtoCartInput"><spring:theme code="product.product.details.future.qty"/></label>
                                <input type="text" maxlength="3" class="text-center js-qty-selector-input" size="1" value="${qtyMinus}" data-max="${maxQty}" data-min="1" name="pdpAddtoCartInput"  id="pdpAddtoCartInput" /></div>
                            <div class="pull-left usage-calc-pdp">
                                <a class="usageCalculatorLink" href="javascript:void(0);" title="How Much Do I Need?">
                                    <span class="icon icon-calculator"></span><spring:theme code="product.volumePrices.column.howMuch"/></a>
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

                            <action:actions element="div"  parentComponent="${component}"/>
                            
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
                                    <img href="#" src="${themeResourcePath}/images/new-twitter2.png" alt="twitter"></a></div>
                                <div>
                                    <a href="">
                                    <img href="#" src="${themeResourcePath}/images/new-f.png" alt="facebook"></a></div>
                                <div>
                                    <a href="">
                                    <img href="#" src="${themeResourcePath}/images/new-line.png" alt="line"></a></div>
                                <div>
                                    <a href="">
                                    <img href="#" src="${themeResourcePath}/images/new-tel.png" alt="tel"></a></div>
                                <div>
                                    <a href="">
                                    <img href="#" src="${themeResourcePath}/images/new-talk.png" alt="talk"></a></div>
                            </div>
                        </div>
                    </div>
                </div>