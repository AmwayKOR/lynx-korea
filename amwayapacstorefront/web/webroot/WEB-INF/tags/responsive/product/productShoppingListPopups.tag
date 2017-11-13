<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<div id="colorbox" class="lynx-modal-window add-to-shopping-list-box" role="dialog" tabindex="-1">
	<div id="cboxWrapper">
		<div>
			<div id="cboxTopLeft"></div>
			<div id="cboxTopCenter"></div>
			<div id="cboxTopRight"></div>
		</div>
		<div style="clear: left;">
			<div id="cboxMiddleLeft"></div>
			<div id="cboxContent">
				<div id="cboxLoadedContent">
					<div id="createNewShoppingListModal"
						class="amway-theme create-new-shopping-list-modal js-create-new-shopping-list-modal js-create-new-modal">
						<div class="amway-theme">
							<form id="saveShoppingListForm" class="create-shopping-list-form" action="" method="post">
								<div class="row new-shopping-list-row">
									<div class="description">You can create shopping lists for yourself, your uplines, or your downlines.
										It's a simple way to keep track of your customers' favorite products, manage your orders, and share your
										lists.</div>
								</div>
								<div class="row new-shopping-list-row">
									<div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
										<label for="newShoppingListName">
											<h6>New List Name</h6>
										</label>
									</div>
									<div class="col-sm-12 col-md-8 col-lg-8">
										<input id="newShoppingListName" name="name" type="text"
											class="shopping-list-input js-create-shopping-list-input" required="required" value="" />
									</div>
								</div>
								<div class="row new-shopping-list-row">
									<div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
										<h6>List Recipient</h6>
									</div>
									<div class="col-sm-12 col-md-8 col-lg-8">
										<div class="radio-wrapper">
											<label class="amw-radio-wrap radio-label">
												<input type="radio" id="me" value="Me" name="ownerType" checked="checked"
													class="js-me-button amw-global-radio" required="required" />
												<span class="amw-radio-overlay"></span>
												<span class="amw-label-radio-text">Me</span>
											</label>
										</div>
										<div class="radio-wrapper">
											<label class="amw-radio-wrap radio-label">
												<input type="radio" id="mySponsor" value="Sponsor" name="ownerType"
													class="js-my-sponsor-button amw-global-radio" required="required" />
												<span class="amw-radio-overlay"></span>
												<span class="amw-label-radio-text">My Sponsor [Sponsor Name Goes Here]</span>
											</label>
										</div>
										<div class="radio-wrapper los-search-wrap">
											<label class="amw-radio-wrap radio-label">
												<input type="radio" id="personalySponsored" value="Downline" name="ownerType"
													class="js-downlines-button amw-global-radio" required="required" />
												<span class="amw-radio-overlay"></span>
												<span class="amw-label-radio-text">Customs List from My Contacts</span>
											</label>
											<div class="new-ditto-search-contacts amway-theme">
												<span class="new-ditto-link-wrap">
													<a href="" class="losSearchLink new-ditto-link" title="Search: My contacts" data-single-select="">
														<span class="new-ditto-icon icon-add-user"></span>
														<span class="link-text">Search Contacts</span>
													</a>
												</span>
												<input id="los-contacts" name="ownerIDs" type="hidden" />
												<input id="los-contacts-type" name="ownerType" type="hidden" />
												<input id="los-contacts-name" name="ownerName" type="hidden" />
												<span class="new-ditto-link-wrap">
													<a href="" class="losSearchLink new-ditto-link disabled" title="Search: My contacts" data-single-select="">
														<span class="link-text js-los-search-total-value">
															(
															<t id="js-los-search-total">0</t>
															)Total
														</span>
													</a>
												</span>
											</div>
										</div>
									</div>
								</div>
								<input type="radio" id="me" value="Me" name="ownerType" checked="checked" required="required" hidden="hidden" />
								<hr>
								<div class="row new-shopping-list-row">
									<div class="col-xs-12 col-md-12">
										<div class="shopping-list-row-button-wrapper">
											<button type="submit" class="btn btn-primary small js-create-shopping-list-btn" id="createShoppingList">
												Create List</button>
										</div>
									</div>
								</div>
								<div>
									<input type="hidden" name="CSRFToken" value="de7f6334-b3eb-4b8e-be75-47acb3226fbe" />
								</div>
							</form>
						</div>
					</div>
				</div>
				<div id="cboxTitle">
					<div class="headline">
						<span class="headline-text">Create New Shopping List</span>
					</div>
				</div>
				<div id="cboxCurrent"></div>
				<!--<button type="button" id="cboxPrevious" style="display: none;"></button>-->
				<!--<button type="button" id="cboxNext" style="display: none;"></button>-->
				<!--<button id="cboxSlideshow" style="display: none;"></button>-->
				<!--<div id="cboxLoadingOverlay" style="float: left; display: none;"></div>-->
				<!--<div id="cboxLoadingGraphic" style="float: left; display: none;"></div>-->
				<button type="button" id="cboxClose">
					<span class="modal-close-icon">&times;</span>
				</button>
			</div>
			<div id="cboxMiddleRight"></div>
		</div>
		<div style="clear: left;">
			<div id="cboxBottomLeft"></div>
			<div id="cboxBottomCenter"></div>
			<div id="cboxBottomRight"></div>
		</div>
	</div>
	<div style="position: absolute; width: 9999px; visibility: hidden; max-width: none; display: none;"></div>
</div>
<div id="add-to-shopping-list" class="cbox">
	<div class="cart-popup__dialog">
		<div class="cart-popup__header">
			<span class="cart-popup__header-text">
				added to shopping list
				<img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close"
					aria-hidden="true">
			</span>
		</div>
		<div class="cart-popup__content">
			<div class="cart-popup__item-info amwahover">
				<img src="images/lip-gloss-swatches.png" class="cart-popup__thumbnail" alt="product">
				<div class="cart-popup__item-detail">
					<p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name – Wraps to Two Lines</p>
					<p class="cart-popup__item-count">60 Count</p>
					<span class="cart-popup__item-number">Item #: 116991</span>
					<div class="cart-popup__item-title cart-popup__item-aboprice">
						<span>ABO Price:</span>
						<span class="product-list__item-abovalue">$16.62</span>
					</div>
					<div class="cart-popup__item-retailprice">
						<span>Retail Price:</span>
						<span class="product-list__item-abovalue">$25.55</span>
					</div>
					<div class="cart-popup__item-retailprice">
						<span>PV / BV:</span>
						<span class="product-list__item-abovalue">4.50 / 14.21</span>
					</div>
					<div class="cart-popup__quantity cart-popup__item-retailprice">
						<span class="cart-popup__qty">Qty: 1</span>
						<!--<a>Edit</a>-->
					</div>
				</div>
			</div>
			<div class="cart-popup__item-link">
				<a href="shopping-list-landing.html" class="btn-blue-white">go to shopping list</a>
				<a class="cart-popup__item-link-text" href="#" onclick="javascript:location.reload()">Continue Shopping</a>
			</div>
		</div>
	</div>
</div>
