ACC.registerterms = {
		
	_autoload: [
        "bindRegisterTerms"
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
	}
};
