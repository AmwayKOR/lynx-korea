ACC.registerterms = {
		
	_autoload: [
        "bindRegisterTerms",
        "bindTooltipInfoDisplay"
	],
		
	bindRegisterTerms:function(){

		 $(document).on('click', '.show-terms', function(){
			$('#pop-terms').html($(this).closest("li").find("div:hidden").html());
			$('#pop-terms').fadeIn();
		});
		
	    $(document).on('click', '#submit-btn', function(e){
	        e.preventDefault();
	        var checkedRadio = $(this).closest(".pop-box").find("input:checked");
	        var checkedRadioVal = checkedRadio.val();
	        var radioName = checkedRadio.data("radioCount");
	        var pageRadioToBeChecked = $(document).find("input."+radioName+"[value='"+checkedRadioVal+"']");
	        pageRadioToBeChecked.trigger("click");
	        $('.popup').fadeOut();
	    });
	    
	    $(document).on('click', '.pop-close, #cancel-btn', function(e){
	        e.preventDefault();
	        $('.popup').fadeOut();
	    });
        
	    $(document).on('click', '.view-all', function(){
	        var parent = $(this).parent();
	        if (parent.hasClass('expand')) {
	            parent.removeClass('expand');
	        } else {
	            parent.addClass('expand');
	        }
	    });
	},


	bindTooltipInfoDisplay : function(){
		$(".upTips").on("click", function(e){
	        e.preventDefault();
	        var flag = $(this).prev(".toolTips").is(":hidden");
	        if(flag) {
	            $(".toolTips").fadeOut();
	            $(this).prev(".toolTips").fadeIn();
	        } else {
	            $(this).prev(".toolTips").fadeOut();
	        }
	    });
	}
};
