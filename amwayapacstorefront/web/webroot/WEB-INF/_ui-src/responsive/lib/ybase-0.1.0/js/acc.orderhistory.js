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
	    		
	    	ACC.orderhistory.populateOrderDetails($(this));
	        
	    });
	},
	populateOrderDetails: function (e)
	{
		var orderExpand = $(e);
		var orderID = $(e).attr('data-order-id');
		var orderParticular = $('#orderDetailsExpand-'+orderID);
		
		if(!orderExpand.hasClass("open")){
			
			
			var options = {
				url: '/amwayapacstorefront/my-account/order/'+orderID,
				type: 'GET',
				success: function (data)
				{
					orderParticular.html(data);
					orderExpand.addClass("open");
					orderExpand.next(".js-order-details-block").addClass("expanded");
					orderExpand.siblings(".js-expand-order").removeClass("open");
					orderExpand.next(".js-order-details-block").siblings(".js-order-details-block").removeClass("expanded");
			        
				}
			};
			$.ajax(options);
		}else{
			$(e).removeClass("open");
            $(e).next(".js-order-details-block").removeClass("expanded");
		}
		
	},
};
