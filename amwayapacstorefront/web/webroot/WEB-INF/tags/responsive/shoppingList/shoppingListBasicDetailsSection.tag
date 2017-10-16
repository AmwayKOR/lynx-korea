<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="shoppingListDetail" class="account-section shopping-cart-quick-shop-wrapper" data-grid-confirm-message="">
    <div class="quick-shop-container">
        <div id="shoppingListDetailAccordion" class="panel-group accordion-custom" role="tablist" aria-multiselectable="true">
            <div class="panel">
                <div class="panel-heading" role="tab" id="ShopListDetail">
                    <h4 class="panel-title">
                        <div role="button" data-toggle="collapse" data-parent="#shoppingListDetailAccordion" href="#ShopListDetailBody" aria-controls="ShopListDetailBody" class="collapsed">
                            <img src="${themeResourcePath}/images/MICRO_SITE_PAGE.png" alt="cart" class="cart-header__cart-icon" />
                            <span class="text-uppercase accordion-header-text"><spring:theme code="shopping.list.page.basic.section.header" /></span>
                            <span class="pull-right icon-minus"></span>
                        </div>
                    </h4>
                </div>
                <div id="ShopListDetailBody" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
                    <div class="panel-body row">
                        <div id="ShopListDetailForm" class="col-xs-12 col-md-12 col-lg-12">
                            <div class="detail-content col-xs-12 col-md-6 col-lg-6">
                                <div class="col-md-12 col-lg-12">
                                    <span class="col-xs-6 col-md-6 col-lg-6 left-content"><spring:theme code="shopping.list.page.basic.section.created.time.label" /></span>
                                    <span class="col-xs-6 col-md-6 col-lg-6"><fmt:formatDate value="${shoppingListData.lastUpdated}"/></span></div>
                                <div class="col-md-12 col-lg-12">
                                    <span class="col-xs-6 col-md-6 col-lg-6 left-content"><spring:theme code="shopping.list.page.basic.section.updated.time.label" /></span>
                                    <span class="col-xs-6 col-md-6 col-lg-6"><fmt:formatDate value="${shoppingListData.creationTime}"/></span></div>
                                <div class="col-md-12 col-lg-12">
                                    <span class="col-xs-6 col-md-6 col-lg-6 left-content"><spring:theme code="shopping.list.page.basic.section.items.label" /></span>
                                    <span class="col-xs-6 col-md-6 col-lg-6">${fn:length(shoppingListData.entries)}</span></div>
                            </div>
                            <div class="detail-content col-xs-12 col-md-6 col-lg-6">
                                <div class="col-md-12 col-lg-12">
                                    <span class="col-xs-6 col-md-6 col-lg-6 left-content"><spring:theme code="shopping.list.page.basic.created.for.label" /></span>
                                    <span class="col-xs-6 col-md-6 col-lg-6">${shoppingListData.user.name}</span></div>
                                <div class="col-md-12 col-lg-12">
                                    <span class="col-xs-6 col-md-6 col-lg-6 left-content"><spring:theme code="shopping.list.page.basic.created.by.label" /></span>
                                    <span class="col-xs-6 col-md-6 col-lg-6">${shoppingListData.user.name}</span></div>
                                <div class="col-md-12 col-lg-12">
                                    <span class="col-xs-6 col-md-6 col-lg-6 left-content"><spring:theme code="shopping.list.page.basic.permission.label" /></span>
                                    <span class="col-xs-6 col-md-6 col-lg-6">N None</span></div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="js-quick-shop-message quick-shop-message">
                                        <span class="icon-check-bold"></span>
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