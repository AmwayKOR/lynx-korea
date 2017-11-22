<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="collapse mini-cart-items-container js-mini-cart-items-container mini-cart-wrapper"
	id="shoppingcar-drop-content">
	<div class="arrow" style=""></div>
	<ul class="nav nav-tabs">
		<li class="active">
			<a data-toggle="tab" href="#miniShoppingCartPopupContentPC">
				Shopping Cart (
				<span id="minicartTabTotalItems">18</span>
				)
			</a>
		</li>
		<li>
			<a data-toggle="tab" href="#dittoPopupContentPC">My Ditto</a>
		</li>
	</ul>
	<div class="tab-content js-tab-content">
		<div id="miniShoppingCartPopupContentPC" class="tab-pane fade in active">
			<div class="mini-cart js-mini-cart">
				<div class="mini-cart-body">
					<ol class="mini-cart-list">
						<li class="mini-cart-item">
							<div class="row product-wrapper">
								<div class="product-item-element list-item-image col-xs-3">
									<div class="thumb">
										<a href="">
											<img src="${themeResourcePath}/images/heart-health-65.png" alt="Lip Gloss Creme" title="Lip Gloss Creme" />
										</a>
									</div>
								</div>
								<div class="product-details col-xs-9">
									<div class="name-wrapper">
										<a class="name" href="">Lip Gloss Creme</a>
									</div>
									<div class="product-item-element list-item-ibo-price">
										<div>
											<div class="first-price">
												<span class="label-wrapper">IBO Cost:</span>
												<span class="value-wrapper">$24.80</span>
												<div class="retail-price">
													<s>
														<span class="retail-price-strike-out">$24.99</span>
													</s>
												</div>
											</div>
											<div>
												<span class="label-wrapper">Retail price:</span>
												<span class="value-wrapper">$25.99</span>
											</div>
										</div>
									</div>
									<div class="product-item-element list-item-pv-bv">
										<span class="total-pv-bv-label">PV/BV:</span>
										<span class="value-wrapper">4.0/3.0</span>
									</div>
									<div class="qty">
										<span class="qty-label">Qty:</span>
										<span class="qty-value">1</span>
									</div>
								</div>
							</div>
						</li>
						<li class="mini-cart-item">
							<div class="row product-wrapper">
								<div class="product-item-element list-item-image col-xs-3">
									<div class="thumb">
										<a href="">
											<img src="${themeResourcePath}/images/heart-health-65.png" alt="Spray Bottle" title="Spray Bottle" />
										</a>
									</div>
								</div>
								<div class="product-details col-xs-9">
									<div class="name-wrapper">
										<a class="name" href="">Spray Bottle</a>
									</div>
									<div class="product-item-element list-item-ibo-price">
										<div>
											<div class="first-price">
												<span class="label-wrapper">IBO Cost:</span>
												<span class="value-wrapper">$1.99</span>
												<div class="retail-price">
													<s>
														<span class="retail-price-strike-out">$2.00</span>
													</s>
												</div>
											</div>
											<div>
												<span class="label-wrapper">Retail price:</span>
												<span class="value-wrapper">$3.00</span>
											</div>
										</div>
									</div>
									<div class="product-item-element list-item-pv-bv">
										<span class="total-pv-bv-label">PV/BV:</span>
										<span class="value-wrapper">4.0/3.0</span>
									</div>
									<div class="qty">
										<span class="qty-label">Qty:</span>
										<span class="qty-value">1</span>
									</div>
								</div>
							</div>
						</li>
						<li class="mini-cart-item">
							<div class="row product-wrapper">
								<div class="product-item-element list-item-image col-xs-3">
									<div class="thumb">
										<a href="">
											<img src="${themeResourcePath}/images/heart-health-65.png" alt="G&amp;H Refresh+&amp;trade; Body Wash - Gel"
												title="G&amp;H Refresh+&amp;trade; Body Wash - Gel" />
										</a>
									</div>
								</div>
								<div class="product-details col-xs-9">
									<div class="name-wrapper">
										<a class="name" href="">G&amp;H Refresh+™ Body Wash - Gel</a>
									</div>
									<div class="product-item-element list-item-ibo-price">
										<div>
											<div class="first-price">
												<span class="label-wrapper">IBO Cost:</span>
												<span class="value-wrapper">$8.73</span>
												<div class="retail-price">
													<s>
														<span class="retail-price-strike-out">$8.80</span>
													</s>
												</div>
											</div>
											<div>
												<span class="label-wrapper">Retail price:</span>
												<span class="value-wrapper">$10.14</span>
											</div>
										</div>
									</div>
									<div class="product-item-element list-item-pv-bv">
										<span class="total-pv-bv-label">PV/BV:</span>
										<span class="value-wrapper">4.0/3.0</span>
									</div>
									<div class="qty">
										<span class="qty-label">Qty:</span>
										<span class="qty-value">1</span>
									</div>
								</div>
							</div>
						</li>
					</ol>
					<div class="banner__component banner"></div>
					<div class="summary-block">
						<span>
							There are
							<span class="bold">18 items</span>
							in your cart
						</span>
					</div>
					<div class="mini-cart-totals">
						<div class="key">ESTIMATED TOTAL</div>
						<div class="value">$1,616.79</div>
						<div class="key">TOTAL PV/BV</div>
						<div class="value">82.0 / 64.0</div>
					</div>
					<div class="links">
						<div>
							<div class="cartPopupButtons"></div>
						</div>
					</div>
					<a href="shopping-cart.html" class="btn btn-primary btn-block mini-cart-checkout-button view-cart-button">VIEW
						CART</a>
				</div>
			</div>
		</div>
		<div id="dittoPopupContentPC" class="tab-pane fade">
			<div class="ditto-content">
				<p class="tip-gray">Your DITTO schedules are sorted by Drop Date,which is the date your order will arrive.</p>
				<ul>
					<li>
						<h5>
							<a href="">Jennifer Jones'First DITTO(20)</a>
							<label class="label-date">11/15/16</label>
						</h5>
						<span>180.22PV/570.00BV</span>
						<span>Subtotal:$500.00</span>
					</li>
					<li>
						<h5>
							<a href="">Second DITTO List Name(54)</a>
							<label class="label-date">12/15/15</label>
						</h5>
						<span>180.22PV/570.00BV</span>
						<span>Subtotal:$500.00</span>
					</li>
					<li>
						<h5>
							<a href="">My Other DITTO List Has a Long Name(999)</a>
							<label class="label-date">05/12/10</label>
						</h5>
						<span>180.22PV/570.00BV</span>
						<span>Subtotal:$500.00</span>
					</li>
				</ul>
				<div class="bottom-total">
					<a href="">
						You have
						<b>5</b>
						DITTO schedules.
					</a>
				</div>
				<div class="bottom-button">
					<button class="btn-blue-white">VIEW ALL DITTO SCHEDULES</button>
				</div>
			</div>
		</div>
	</div>
</div>
