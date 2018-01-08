<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="order-entry-pagination shopping-list-entry-pagination">
	<div class="pagination-bar top new-pagination-bar">
		<div class="print-hide pagination-toolbar">
			<div class="helper clearfix hidden-md hidden-lg"></div>
			<div class="sort-refine-bar">
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 pagination-controls-wrapper">
						<div class="form-group">
							<label class="control-label cart-detail__label" for="sortForm1">
								<spring:theme code="shoppinglist.items.section.sortby.label" />
							</label>
							<select class="cart-detail__size" id="sortBy">
								<option><spring:theme code="shoppinglist.items.section.sortby.lastitemadded.label" /></option>
								<option><spring:theme code="shoppinglist.items.section.sortby.price.label" /></option>
								<option><spring:theme code="shoppinglist.items.section.sortby.ascending.label" /></option>
								<option><spring:theme code="shoppinglist.items.section.sortby.decending.label" /></option>
							</select>
							<a class="payment-forms__apply btn-blue-white cart-detail__mob-hide" href="#">
								<spring:theme code="shoppinglist.items.section.sortby.apply.button.label" />
							</a>
						</div>
					</div>
					<div class="col-xs-12 col-sm-5 col-md-5 pagination-wrap">
						<div class="add-to-component-container">
							<div class="pull-right action-panel-wrapper add-to-dropdown">
								<div class="cart-detail__dropdown dropdown dropdown-accordion" data-accordion="#addtoAccordion">
									<div class="cart-detail__addto dropdown-toggle">
										<spring:theme code="shoppinglist.items.section.addto.label" />
									</div>
									<ul class="cart-detail__dropdown-menu dropdown-menu" role="menu" aria-labelledby="dLabel">
										<div class="cart-detail__div-short"></div>
										<div id="addtoAccordion" class=" clearfix">
											<div class="cart-detail__addto-item panel">
												<h5 class="product-category__item-header collapsed" href="#addtoCollapse4" data-toggle="collapse"
													data-parent="#addtoAccordion" aria-expanded="false">
													<spring:theme code="shopping.list.items.add.to.cart.label" />
												</h5>
												<div class="panel-collapse collapse" id="addtoCollapse4" aria-expanded="false" style="height: 0px;">
													<div class="cart-detail__panel-body shopping-list-add-to-cart-wrapper">
														<a href="#" class="shopping-list-add-to-cart">
															<spring:theme code="shopping.list.items.add.to.cart.label" />
														</a>
													</div>
												</div>
											</div>
										</div>
									</ul>
								</div>
							</div>
						</div>
						<div class="pull-right action-panel-wrapper cartlist__cancelorder cart-detail__update-cart">
							<div class="text-action-btn">
								<span>
									<spring:theme code="shoppinglist.items.section.update.cart.label" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>