<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
	<div class="title-product-replacement-container">
		<div class="col-sm-12 col-md-12 pdp-info page-title-container">
			<div class="product-details page-title">
				<div class="name">${fn:escapeXml(product.name)}</div>
				<div class="item-code">
					<span><spring:theme code="product.item.number.label" /></span> <span
						class="code">${fn:escapeXml(product.code)}</span>
				</div>
			</div>
		</div>
	</div>
	<div class="img-product">
		<product:productImagePanel galleryImages="${galleryImages}" />
		<div class="clearfix hidden-sm hidden-md hidden-lg print-hide"></div>
		<div class="col-sm-12 col-md-6 pdp-info">
			<div class="product-main-info">
				<div class="row">
					<div class="col-md-12">
						<div class="description">${product.summary}</div>
						<cms:pageSlot position="ColorVariantSelector" var="component">
							<cms:component component="${component}" />
						</cms:pageSlot>
						<hr class="description__line">
						<product:productReviewSummary product="${product}"
							showLinks="true" />
						<product:productPricePanel product="${product}" />
					</div>
					<div class="col-sm-12 col-md-12">
						<div class="page-details-variants-select">
							<div class="page-details-add-to-cart-component">
								<div class="addtocart-component">
									<div class="amway-theme qty-selector js-qty-selector control-group">
										<cms:pageSlot position="VariantSelector" var="component">
											<cms:component component="${component}" />
										</cms:pageSlot>
										<div class="row">
												<div class="pull-left qty-selector-container">
													<label class="control-label" for="pdpAddtoCartInput"><spring:theme code="product.product.details.future.qty"/></label>
													<input type="text" maxlength="3" class="text-center js-qty-selector-input" size="1" value="1" data-max="FORCE_IN_STOCK" data-min="1" name="pdpAddtoCartInput" id="pdpAddtoCartInput" /></div>
												<div class="pull-left usage-calc-pdp">
													<a class="usageCalculatorLink" href="javascript:void(0);" title="How Much Do I Need?">
														<span class="icon icon-calculator"></span></span><spring:theme code="product.volumePrices.column.howMuch"/></a>
												</div>
											</div>
										</div>
										<div class="stock-wrapper clearfix">
											<product:productStockStatusDisplay product="${product}" />
										</div>
									<div class="actions-wrapper">
											<div class="actions">
										<cms:pageSlot position="AddToCart" var="component">
											<cms:component component="${component}" />
										</cms:pageSlot>
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
</div>