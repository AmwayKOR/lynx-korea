ACC.quickview = {
	_autoload: [
		"bindQuickViewPopup",
        "bindColorVariantSelection",
        "bindVariantAttribute2Selection",
	],
		
	bindQuickViewPopup : function(){
        $(document).on('click', '.quick-view-btn', function(event) {
            event.preventDefault();
            var productCode = $(this).data("productCode");
            $.ajax({
    			  url:ACC.config.contextPath + "/p/" + productCode + "/quickView",
    			  type: "GET",
    			  success: function(data) {
    				  ACC.popup.showPopup(data);
    			  },
    			  error: function() {
    				  ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.quickViewFetchError);
    			  }
    		  });
        });
    },

	bindColorVariantSelection : function(){
		$("#modal-popup-container").on("click", ".view-box .cart-popup__dialog .lip-color-choose__nav a", function(event){
			event.preventDefault();
			var productCode = $(this).closest("li").data("productCode");
			ACC.categorypage.replaceQuickViewDisplayContent(productCode);
		});
	},
	
	bindVariantAttribute2Selection : function(){
		$("#modal-popup-container").on("change", ".view-box .cart-popup__dialog .size-selector-container select", function(event){
			var productCode = $(this).find(":selected").data("productCode");
			ACC.categorypage.replaceQuickViewDisplayContent(productCode);
		});
	},
	
	replaceQuickViewDisplayContent : function(productCode) {
		if (productCode) {
			$.ajax({
				  url:ACC.config.contextPath + "/p/" + productCode + "/quickView",
				  type: "GET",
				  success: function(data) {
					  ACC.popup.refreshPopupContent(data);
				  },
				  error: function() {
					  ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.quickViewFetchError);
				  }
			 });
		}
	}
};