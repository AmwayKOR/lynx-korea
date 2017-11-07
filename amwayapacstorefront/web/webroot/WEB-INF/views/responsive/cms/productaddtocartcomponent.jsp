<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<spring:htmlEscape defaultHtmlEscape="true" />
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
	<div class="product-stock">
		<div>
			<span class="stock">
				<span class="product-availability">
					<span class="green">
						<span class="icon icon-check-bold"></span>
						<span class="text text-uppercase">In Stock</span></span>
				</span>
			</span>
		</div>
	</div>
</div>
<div class="actions-wrapper">
	<div class="actions">
		<div class="AddToCart-AddToCartAction" data-index="1">
			<action:actions element="div"  parentComponent="${component}"/>