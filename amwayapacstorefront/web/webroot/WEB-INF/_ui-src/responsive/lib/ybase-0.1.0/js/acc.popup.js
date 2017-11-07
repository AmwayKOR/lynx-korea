ACC.popup = {

    _autoload: [
    	"bindResetPopupOnResize",
    	"bindClosePopup"
    ],
    
    resetPopup: function() {
        var W = $(window).width();
        var cW = $("#modal-popup-container").children().first().width();
        $("#modal-popup-container").children().first().css("left", (W - cW) / 2);
    },

	bindResetPopupOnResize: function() {
		$(window).resize(function() {
			ACC.popup.resetPopup();
        });
	},
	
	showPopup: function(popup) {
		$("#modal-popup-container").html(popup);
		$("#modal-popup-container").children().first().show();
		$("div.overlay").show();
		ACC.popup.resetPopup();
	},
	
	bindClosePopup: function() {
		$("#modal-popup-container").on("click", ".cart-popup__close,.closeCbox", function() {
			$("div.overlay").hide();
			$("#modal-popup-container").children().first().hide();
			$("#modal-popup-container").html("");
		});
	}
};


