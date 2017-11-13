<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<div class="cbox cbox2">
	<div class="cart-popup__dialog need-dialog">
		<div class="cart-popup__header">
			<span class="cart-popup__header-text">
				HOW MUCH DO I NEED?
				<img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close"
					aria-hidden="true">
			</span>
		</div>
		<div class="cart-popup__content">
			<div class="car-popup-item-des">
				<p>The use calculator helps you determine how much of a product your household will consume over time - so you
					can order what you need, when you need it. A great tool for setting up DITTO scheduled orders.</p>
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
						<span>
							<b>Retail Cost Per Use</b>
						</span>
						<span class="product-list__item-abovalue">
							<b>$0.13</b>
						</span>
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
						Your Purchase Quantity:
						<span>3 per year</span>
						<button class="btn-blue-white btn-calculate-close">CLOSE</button>
					</div>

				</div>
			</div>

		</div>
	</div>
</div>
