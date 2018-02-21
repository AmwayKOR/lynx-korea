<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="col-md-9 cart-items-wrapper">
    <div class="order-entry-pagination">
        <div class="pagination-bar top">
            <div class="print-hide pagination-toolbar">
                <div class="helper clearfix hidden-md hidden-lg"></div>
                <div class="sort-refine-bar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-7 col-md-7 pagination-controls-wrapper">
                            <div class="form-group">
                                <c:url value="/cart/content" var="cartSortFormAction" />
                                <form:form id="sortCartForm" action="${cartSortFormAction}" method="post" >
                                    <label class="control-label cart-detail__label" for="sortForm1">Sort by</label>
                                    <select class="cart-detail__size" name="sortBy" >
                                    	<c:forEach items="${sortOptions}" var="sortOption">
                                    		<option value="${sortOption}" <c:if test="${selectedSort eq sortOption}">selected="selected"</c:if>><spring:theme code="cart.page.sort.${sortOption}" /></option>
                                    	</c:forEach>
                                    </select>
                                    <a class="btn-blue-white js-sort-cart-submit" href="#"><spring:theme code="cart.page.sort.button.apply" /></a>
                                </form:form>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-5 col-md-5 pagination-wrap">
                            <div class="add-to-component-container">
                                <div class="pull-right action-panel-wrapper add-to-dropdown">
                                    <div class="cart-detail__dropdown dropdown dropdown-accordion" data-accordion="#addtoAccordion">
                                        <div class="cart-detail__addto dropdown-toggle"><spring:theme code="text.shoppinglist.addtolistcartpopup.addto" /></div>
                                        <ul class="cart-detail__dropdown-menu dropdown-menu" role="menu" aria-labelledby="dLabel">
                                            <div class="cart-detail__div-short"></div>
                                            <div id="addtoAccordion" class=" clearfix">
                                                <div class="cart-detail__addto-item panel">
                                                    <h5 class="product-category__item-header collapsed" href="#addtoCollapse1" data-toggle="collapse" data-parent="#addtoAccordion" aria-expanded="false">ADD TO DITTO</h5>
                                                    <div class="panel-collapse collapse" id="addtoCollapse1" aria-expanded="false" style="height: 0px;">
                                                        <div class="cart-detail__panel-body">
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
												<div class="cart-detail__addto-item panel">
													<c:url var="addToShoppingListCartPageUrl" value="/shopping-lists/cart/data/all" />
													<c:set var="productCode" value="${product.code}" />
													<h5 class="product-category__item-header collapsed updateToList" data-product-code="${productCode}"
														data-add-to-shopping-list-url="${addToShoppingListCartPageUrl}" href="#addtoCollapse2" data-toggle="collapse" data-parent="#addtoAccordion" aria-expanded="false">ADD TO SHOPPING LIST</h5>
													<div id="addtoCollapse2" class="panel-collapse collapse" aria-expanded="false"></div>
													
												</div>

												<div class="cart-detail__addto-item panel">
                                                    <h5 class="product-category__item-header collapsed" href="#addtoCollapse3" data-toggle="collapse" data-parent="#addtoAccordion" aria-expanded="false">ADD TO RECEIPT</h5>
                                                    <div class="panel-collapse collapse" id="addtoCollapse3" aria-expanded="false" style="height: 0px;">
                                                        <div class="cart-detail__panel-body">
                                                            <input class="" id="toReceipt" name="toReceipt" type="checkbox">
                                                            <label class="cart-detail__addto-options" for="toReceipt">Add to Receipt</label></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="pull-right action-panel-wrapper cartlist__cancelorder cart-detail__update-cart">
                                <div class="text-action-btn">
                                    <span>Update Cart</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <cart:cartItems cartData="${cartData}"/>
</div>