ACC.businessinformation = {

	_autoload: [
        "bindBusinessInformation"
	],
	
	bindBusinessInformation: function () {
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
