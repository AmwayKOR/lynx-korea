<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row shopping-cart-total-wrapper shoppingListDetail-wrapper">
    <div class="shopping-cart-total">
        <div class="order-block">
            <div class="order-block-header">
                <span>Order Summary</span></div>
            <div class="cart-totals">
                <div class="js-cart-totals js-cart-totals-order-summary">
                    <div class="wrapper row auto-subtotal">
                        <div class="col-xs-6 cart-totals-left">Item(s) Subtotal</div>
                        <div class="col-xs-6 cart-totals-right text-right">$58.20</div></div>
                    <div class="wrapper wrapper-borders row auto-total">
                        <div class="col-xs-6 cart-totals-left">Total</div>
                        <div class="col-xs-6 cart-totals-right text-right">$46.20</div></div>
                    <div class="wrapper row auto-total noTop">
                        <div class="col-xs-6 cart-totals-left">TOTAL PV/ BV</div>
                        <div class="col-xs-6 cart-totals-right text-right">222.45 / 699.63</div></div>
                </div>
            </div>
        </div>
    </div>
</div>
