ACC.minicart = {

	_autoload : [
		"bindMiniCart",
		"updateMiniCartDisplay",
		"bindMinicartClick",
		"bindMinicartFocusOut"
	],

	bindMiniCart : function() {

		$(document).on("click", ".mini-cart-link", function(e) {
			var miniCartUrl = $(this).data("mini-cart-url");
			var isAbo = $(this).hasClass("is-abo");
			var isMinicartOpen = $(".main-header").hasClass("mincart-open");
			if (isAbo && isMinicartOpen && typeof isMinicartOpen !== "undefined") {
				$.ajax({
					url : miniCartUrl,
					cache : false,
					type : 'GET',
					success : function(result) {
						$("#miniShoppingCartPopupContentPC").html(result);
					}
				});
			} else {
				e.stopPropagation();
			}
		});
	},

	updateMiniCartDisplay : function() {
		var miniCartRefreshUrl = $(".js-mini-cart-link").data("mini-cart-item-url");
		var isAbo = $(".js-mini-cart-link").hasClass("is-abo");
		if (typeof miniCartRefreshUrl !== "undefined" && isAbo) {
			$.ajax({
				url : miniCartRefreshUrl,
				cache : false,
				type : 'GET',
				success : function(jsonData) {
					if (jsonData !== "undefined") {
						$(".nav-mini-cart .mini-cart-link .js-mini-cart-count").html('<span class="nav-items-total">' + jsonData.miniCartCount + '</span>');
						$(".mobile-cart-container .mini-cart-link .mini-cart-icon").html('<span class="nav-items-total">' + jsonData.miniCartCount + '</span><span class="icon-shopping-cart"></span>');
					}
				}
			});
		}
	},

	bindMinicartClick : function() {
		$(".js-mini-cart-link").click(function(event) {
			$('header').hasClass('mincart-open') ? ACC.minicart.hideMinicart() : ACC.minicart.showMinicart();
		});
	},

	showMinicart : function() {
		$('header').addClass('mincart-open');
		$('.top-search').removeClass('search-open');
		$('header').removeClass('userinfo-open');
	},

	hideMinicart : function() {
		$('header').removeClass('mincart-open');
		$('.top-search').removeClass('search-open');
		$('header').removeClass('userinfo-open');
	},

	bindMinicartFocusOut : function() {
		$(document).on("click", function(event) {
			if ($('header').hasClass('mincart-open')) {
				var minicartPopup = $(event.target).closest(".mini-cart-wrapper");
				var minicartIcon = $(event.target).closest(".nav-mini-cart");
				// if the click is outside popup then close it
				if ((minicartPopup.length + minicartIcon.length) <= 0) {
					ACC.minicart.hideMinicart();
				}
			}
		});
	}

};