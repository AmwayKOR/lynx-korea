ACC.registerterms = {
		
	_autoload: [
        "bindRegisterTerms",
        "bindTooltipInfoDisplay"
	],
		
	bindRegisterTerms:function(){

		$('.show-terms').click(function (){
			$('#pop-terms').html($(this).closest("li").find("div:hidden").html());
			$('#pop-terms').fadeIn();
		});
		
	    $('.pop-close, #submit-btn, #cancel-btn').click(function (e) {
	        e.preventDefault();
	        $('.popup').fadeOut();
	    });
        
	    $('.view-all').click(function () {
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
