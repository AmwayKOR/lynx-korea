ACC.searchbox = {

	_autoload : [
		"bindHeaderAutoSearchEvent",
		"bindAutoSearchMenuCloseButton",
		"bindShoppingListQuickShopSearchEvent",
		"bindShoppingListSearchSelection",
		"bindShoppingCartQuickShopSearchEvent",
		"bindShoppingCartSearchSelection",
		"bindSearchBoxFocusOut"
	],

	bindAutoSearchMenuCloseButton : function() {
		$(document).on("click", ".search-results-close", function() {
			$('.auto-suggestion-popover').fadeOut();
		});
	},

	bindHeaderAutoSearchEvent : function() {
		$(document).on('keyup', 'header .ui-autocomplete-input', function() {
			var $this = $(this);
			var $searchResult = $this.closest('header').find('.auto-suggestion-popover');
			if ($this.val().length >= 3) {
				$searchResult.fadeIn();
			} else {
				$searchResult.fadeOut();
			}
		});
	},

	bindShoppingListQuickShopSearchEvent : function() {
		$(document).on('keyup', '#quickShopForm .ui-autocomplete-input', function() {
			var $this = $(this);
			var $searchResult = $this.closest('#quickShopForm').find('.auto-suggestion-popover');
			if ($this.val().length >= 3) {
				$searchResult.fadeIn();
			} else {
				$searchResult.fadeOut();
			}
		});
	},

	bindShoppingListSearchSelection : function() {
		$(document).on("click", "#quickShopForm .shopping-list-dropdown", function(event) {
			event.preventDefault();
			var productCode = $(this).data("productCode");
			$(this).closest("#quickShopForm").find("#js-shopping-list-search-input").val(productCode);
			$(this).closest('.auto-suggestion-popover').fadeOut();
		});
	},

	bindShoppingCartQuickShopSearchEvent : function() {
		$(document).on('keyup', '#quickShopCartForm .ui-autocomplete-input', function() {
			var $this = $(this);
			var $searchResult = $this.closest('#quickShopCartForm').find('.auto-suggestion-popover');
			if ($this.val().length >= 3) {
				$searchResult.fadeIn();
			} else {
				$searchResult.fadeOut();
			}
		});
	},

	bindShoppingCartSearchSelection : function() {
		$(document).on("click", "#quickShopCartForm .shopping-cart-dropdown", function(event) {
			event.preventDefault();
			var productCode = $(this).data("productCode");
			$(this).closest("#quickShopCartForm").find("#js-shopping-cart-search-input").val(productCode);
			$(this).closest('.auto-suggestion-popover').fadeOut();
		});
	},

	bindSearchBoxFocusOut : function() {
		$(document).on("click", function(event) {
			var searchResultPopup = $(event.target).closest(".auto-suggestion-popover");
			// if the click is outside popup then close it
			if (!searchResultPopup.length) {
				$('.auto-suggestion-popover').fadeOut();
			}
		});
	}

};
