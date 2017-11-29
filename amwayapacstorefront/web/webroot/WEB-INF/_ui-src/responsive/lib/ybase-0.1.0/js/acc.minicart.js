ACC.minicart = {

	_autoload: [
		"bindMiniCart",
		"updateMiniCartDisplay"
	],

	bindMiniCart: function(){

        $(document).on("click",".mini-cart-link" , function(e){
            var miniCartUrl = $(".js-mini-cart-link").data("mini-cart-url");
            var isAbo = $(".js-mini-cart-link").hasClass("is-abo");
            var isMinicartOpen = $(".main-header").hasClass("mincart-open");
            if(isAbo && isMinicartOpen && typeof isMinicartOpen !== "undefined")
            {
                $.ajax({
                    url: miniCartUrl,
                    cache: false,
                    type: 'GET',
                    success: function(result){
                        $("#miniShoppingCartPopupContentPC").html(result);
                    }
                });
            }else
            {
            	e.stopPropagation();
            }
        });
    },

    updateMiniCartDisplay: function(){
        var miniCartRefreshUrl = $(".js-mini-cart-link").data("mini-cart-item-url");
        var isAbo = $(".js-mini-cart-link").hasClass("is-abo");
        if(typeof miniCartRefreshUrl !== "undefined" && isAbo)
        {
	        $.ajax({
	            url: miniCartRefreshUrl,
	            cache: false,
	            type: 'GET',
	            success: function(jsonData){
	            	if(jsonData !== "undefined")
	            	{
		                $(".nav-mini-cart .mini-cart-link .js-mini-cart-count").html('<span class="nav-items-total">' + jsonData.miniCartCount + '</span>');
		                $(".mobile-cart-container .mini-cart-link .mini-cart-icon").html('<span class="nav-items-total">' + jsonData.miniCartCount + '</span><span class="icon-shopping-cart"></span>');
	            	}
	            }
	        });
        }
    }

};