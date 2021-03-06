ACC.registerterms = {
		
	_autoload: [
        "bindRegisterTermsPopup",
        "bindTooltipInfoDisplay"
	],
		
	bindRegisterTermsPopup:function(){
		 $(document).on('click', '.show-terms', function(){
			var checkedRadio = $(this).closest("li").find(".page-radio-wrapper input:checked");
			var checkedRadioVal = checkedRadio.val();
			$('#pop-terms').html($(this).closest("li").find("div:hidden").html());
			$('#pop-terms').find("input[value='"+checkedRadioVal+"']").prop("checked",true);
			$('#pop-terms').fadeIn();
		});
		
	    $(document).on('click', '#submit-btn', function(e){
	        e.preventDefault();
	        var checkedRadio = $(this).closest(".pop-box").find("input:checked");
	        var checkedRadioVal = checkedRadio.val();
	        var radioName = checkedRadio.data("radioCount");
	        var pageRadioToBeChecked = $(document).find(".page-radio-wrapper input."+radioName+"[value='"+checkedRadioVal+"']");
	        pageRadioToBeChecked.prop("checked",true);
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
