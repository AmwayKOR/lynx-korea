ACC.myaccount = {

	_autoload: [
        "bindMyAccount"
	],
	
	bindMyAccount : function() {
		
		$(".js-jump-to-selection-btn").click(function(e){
	        e.preventDefault();
	        var value =$(".js-jump-to-selection").val()
	        window.location.href = value;
	    });
		
	}

};
