ACC.myaccount = {

	_autoload: [
        "bindMyAccount"
	],
	
	bindMyAccount : function() {
		
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

		    $(".js-expand-order").click(function(){
		        if(!$(this).hasClass("open")){
		            $(this).addClass("open");
		            $(this).next(".js-order-details-block").addClass("expanded");
		        }else{
		            $(this).removeClass("open");
		            $(this).next(".js-order-details-block").removeClass("expanded");
		        }
		    });
		       


		        $(".businees-action-btn").click(function (e) {
		            e.preventDefault();
		            if($(".photo-edit").css('display')=='none'){
		                $(".photo-edit").show();
		                $(".businees-action-btn").text("save")
		                $("#mission-statement-text").hide();
		                $("#mission-statement-textarea").show();
		            }else{
		                $(".photo-edit").hide();
		                $(".businees-action-btn").text("EDIT")
		                $("#mission-statement-text").show();
		                $("#mission-statement-textarea").hide();

		            }



		        })
		
	}

};
