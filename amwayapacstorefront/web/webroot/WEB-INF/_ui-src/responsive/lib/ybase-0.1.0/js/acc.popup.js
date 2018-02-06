ACC.popup = {

    _autoload: [
    	"bindResetPopupOnResize",
    	"bindClosePopup"
    ],

	bindResetPopupOnResize: function() {
		$(window).resize();
	},
	
	showPopup: function(popup) {
		$("#modal-popup-container").html(popup);
		$("#modal-popup-container").children().first().show();
		$("div.overlay").show();
	},
	
	bindClosePopup: function() {
		$("#modal-popup-container").on("click", ".cart-popup__close,.closeCbox", function() {
			ACC.popup.closePopup();
		});
	},
	
	refreshPopupContent: function(newContent) {
		$("#modal-popup-container").html(newContent);
		$("#modal-popup-container").children().first().show();
	},
	
	closePopup : function(){
		$("div.overlay").hide();
		$("#modal-popup-container").children().first().hide();
		$("#modal-popup-container").html("");
	}
};


