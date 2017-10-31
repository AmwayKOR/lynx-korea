ACC.shoppinglisttotals = {

	_autoload: [
		"bindShoppingListItemInput",
		"refreshShoppingListAllTotals"
	],

	bindShoppingListItemInput: function ()
 	{
		// only bind if the current page is shopping lists page
		if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
			$(".page-content-wrapper.page-shopping-list-details").on("blur", ".js-update-shopping-list-entry-quantity-input", function(event) {
				if (!ACC.global.isNatural($(this).val())) {
					$(this).val(1);
				}
				ACC.shoppinglisttotals.calculateShoppingListEntryTotals($(this).closest("li.shopping-list-entry"));
			});
		}
	},

	calculateShoppingListEntryTotals: function (elementRef)
	{
		var priceValue = parseFloat(elementRef.find('span.price-value-wrapper').data("price"));
		var pv = parseFloat(elementRef.find('span.pv-bv-value-wrapper').data("pv"));
		var bv = parseFloat(elementRef.find('span.pv-bv-value-wrapper').data("bv"));
		
		var quantity = parseInt(elementRef.find('input.js-update-shopping-list-entry-quantity-input').val());
		
		if (quantity) {
			
			if (priceValue) {
				var totalPrice = quantity * priceValue
				elementRef.find('span.price-value-wrapper').html(ACC.global.formatPrice(totalPrice.toFixed(2), ACC.config.currentCurrecySymbol));
				elementRef.find('span.price-value-wrapper').data("totalPrice", totalPrice.toFixed(2));
			}
			
			if ((pv) && (bv)) {
				var totalPV = quantity * pv;
				var totalBV = quantity * bv;
				elementRef.find('span.pv-bv-value-wrapper').html(totalPV.toFixed(2).toString() + "/" + totalBV.toFixed(2).toString());
				elementRef.find('span.pv-bv-value-wrapper').data("totalPv", totalPV.toFixed(2));
				elementRef.find('span.pv-bv-value-wrapper').data("totalBv", totalBV.toFixed(2));
			}
			
			ACC.shoppinglisttotals.calculateShoppingListOverviewTotals();
		}
	},
	
	refreshShoppingListAllTotals: function() {

		// only bind if the current page is shopping lists page
		if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
	    	
    		$(".page-content-wrapper.page-shopping-list-details div.shoppingListDetail-content").find("li.shopping-list-entry").each(function() {
    			ACC.shoppinglisttotals.calculateShoppingListEntryTotals($(this));
    		});	
    		ACC.shoppinglisttotals.calculateShoppingListOverviewTotals();
		}
    
	},
	
    calculateShoppingListOverviewTotals: function() {

		// only bind if the current page is shopping lists page
		if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
	    	var totalPrice = 0.00;
	    	var totalPV = 0.00;
	    	var totalBV = 0.00;
	    	
    		$(".page-content-wrapper.page-shopping-list-details div.shoppingListDetail-content").find("li.shopping-list-entry").each(function() {
    			totalPrice += parseFloat($(this).find('span.price-value-wrapper').data("totalPrice"));
    			totalPV += parseFloat($(this).find('span.pv-bv-value-wrapper').data("totalPv"));
    			totalBV += parseFloat($(this).find('span.pv-bv-value-wrapper').data("totalBv"));
    		});	
	    	
	    	$(".page-content-wrapper.page-shopping-list-details div.shoppingListDetail-content div.shopping-list-subtotal-wrapper").html(ACC.global.formatPrice(totalPrice.toFixed(2), ACC.config.currentCurrecySymbol));
	    	$(".page-content-wrapper.page-shopping-list-details div.shoppingListDetail-content div.shopping-list-total-wrapper").html(ACC.global.formatPrice(totalPrice.toFixed(2), ACC.config.currentCurrecySymbol));
	    	$(".page-content-wrapper.page-shopping-list-details div.shoppingListDetail-content div.shopping-list-pv-bv-wrapper").html(totalPV.toFixed(2).toString() + "/" + totalBV.toFixed(2).toString());
		}
    }
};