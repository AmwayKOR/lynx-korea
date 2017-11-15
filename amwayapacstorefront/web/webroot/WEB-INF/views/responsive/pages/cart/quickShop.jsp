<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="account-section-content new-account-section-content">
    <div id="quickOrder" class="account-section shopping-cart-quick-shop-wrapper" data-grid-confirm-message="">
        <div class="quick-shop-container">
            <div id="quickShopAccordion" class="panel-group accordion-custom" role="tablist" aria-multiselectable="true">
                <div class="panel">
                    <div class="panel-heading" role="tab" id="quickShop">
                        <h4 class="panel-title">
                            <div role="button" data-toggle="collapse" data-parent="#quickShopAccordion" href="#quickShopBody" aria-controls="quickShopBody" class="collapsed">
                                <img src="${themeResourcePath}/images/shopping-cart-icon.png" alt="cart" class="cart-header__cart-icon" />
                                <span class="text-uppercase accordion-header-text"><spring:theme code="basket.page.quickShop.heading"/></span>
                                <span class="pull-right icon-minus"></span>
                            </div>
                        </h4>
                    </div>
                    <div id="quickShopBody" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
                        <div class="panel-body">
                            <div id="quickShopCartForm">
                                <c:url value="/cart/quickShop" var="quickShopFormAction" />
                                   <form:form id="quickShopForm" action="${quickShopFormAction}" method="post">
                                      <div class="item-name-container">
                                          <label class="js-sku-input-field-label sku-input-field-label"><spring:theme code="basket.page.quickShop.itemNameOrNumber"/></label>
                                          <input class="js-quick-shop-name quick-shop-name js-name-input-field name-input-field" type="text" name="productCode" />
                                          <input class="js-hidden-sku-field" type="hidden"></div>
                                      <div class="qty-container">
                                          <label><spring:theme code="basket.page.quickShop.qty"/></label>
                                          <input class="js-quick-shop-qty quick-shop-qty" type="text" name="quantity" />
                                      </div>
                                      <div class="add-item-container">
                                          <button class="btn btn-primary btn-block js-quick-shop-submit"><spring:theme code="basket.page.quickShop.addItem"/></button></div>
                                   </form:form>
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