<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>

<div class="col-md-3">
    <div class="row shopping-cart-total-wrapper">
        <div class="shopping-cart-total">
            <div class="order-block">
                <div class="order-block-header">
                    <span>Order Summary</span></div>
                <div class="cart-totals">
                    <cart:cartTotals cartData="${cartData}" showTax="false"/>
                    <div class="cart-voucher new-cart-voucher">
                        <cart:cartVoucher cartData="${cartData}"/>
                        <div class="proceed-to-checkout-container">
                            <button class="btn btn-primary btn-block btn--continue-checkout js-continue-checkout-button" data-checkout-url="#" onclick="javascript:location.href='Checkout-1-shippingAndDelivery.html'">Proceed to Checkout</button></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="express-checkout-block">
            <div class="info-block">Check out in one click with your saved information.</div>
            <button class="btn btn-block">Express Checkout</button></div>
    </div>
</div>