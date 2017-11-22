ACC.orderhistory = {

	_autoload: [
        "bindOrderHistory"
	],
	
	bindOrderHistory: function () {
		
		$(".select2-hidden-accessible").select2();
		
		$(".js-order-history-filters").click(function(){
	        $(".js-search-form-wrapper").addClass('hidden-sm hidden-xs');
	        if($(".js-filter-form-wrapper").hasClass('hidden-sm hidden-xs')){
	            $(".js-filter-form-wrapper").removeClass('hidden-sm hidden-xs');
	        }else{
	            $(".js-filter-form-wrapper").addClass('hidden-sm hidden-xs');
	        }
	    });

	    $(".js-order-history-search").click(function(){
	        $(".js-filter-form-wrapper").addClass('hidden-sm hidden-xs');
	        if($(".js-search-form-wrapper").hasClass('hidden-sm hidden-xs')){
	            $(".js-search-form-wrapper").removeClass('hidden-sm hidden-xs');
	        }else{
	            $(".js-search-form-wrapper").addClass('hidden-sm hidden-xs');
	        }
	    });
	    
		//record collapse
	    $(".js-expand-order").click(function(){
	        if(!$(this).hasClass("open")){
	            $(this).addClass("open");
	            $(this).next(".js-order-details-block").addClass("expanded");
	            $(this).siblings(".js-expand-order").removeClass("open");
	            $(this).next(".js-order-details-block").siblings(".js-order-details-block").removeClass("expanded");
	        }else{
	            $(this).removeClass("open");
	            $(this).next(".js-order-details-block").removeClass("expanded");
	        }
	    });
	}
};
