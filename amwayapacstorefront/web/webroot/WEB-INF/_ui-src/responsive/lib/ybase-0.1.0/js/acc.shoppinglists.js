ACC.shoppinglists = {

    _autoload: [
    	"bindCreateShoppingList",
    	"bindShoppingListFormSubmit"
    ],
    
    bindShoppingListFormSubmit: function() {
    	if ($(".page-content-wrapper.page-shopping-lists").length > 0) {
    		$(".page-content-wrapper.page-shopping-lists").on("click", "button#createShoppingList", function() {
    			$(this).closest("form#createShoppingListForm").submit();
    		});
    	}
    },
    
    refreshAllShoppingListsPage: function(data) {
    	// data contains the shopping list page content
    	if ($(data).filter("div.error-message").length > 0) {
    		var globalErrorMessage = {};
			globalErrorMessage.globalMessageType = ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER; 
			globalErrorMessage.message = $(data).filter("div.error-message").html(); 
			$(".global-alerts").appendGlobalMessages([globalErrorMessage]);
			$('body, html').animate({scrollTop:0}, 'slow');	
    	}
    	if ($(data).filter("div.success-message").length > 0) {
    		var globalErrorMessage = {};
			globalErrorMessage.globalMessageType = ACC.globalMessageTypes.CONF_MESSAGES_HOLDER; 
			globalErrorMessage.message = $(data).filter("div.success-message").html(); 
			$(".global-alerts").appendGlobalMessages([globalErrorMessage]);
			$('body, html').animate({scrollTop:0}, 'slow');	
    	}
    	if ($(data).filter("div.page-content").length > 0) {
    		$(".page-content-wrapper.page-shopping-lists").html($(data).filter("div.page-content").html());
    	}

    },
    
    bindCreateShoppingList : function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-lists").length > 0) {
    		$(".page-content-wrapper.page-shopping-lists").on("submit", "form#createShoppingListForm", function() {
    			
    			var $form = $(this);
    			var method = $form.attr("method") ? $form.attr("method").toUpperCase() : "GET";
    			var shoppingListName = $form.find("input#newShoppingListName").val();
    			
    			if (!ACC.global.isNullOrWhiteSpace(shoppingListName)) {
					$.ajax({
						url: $form.attr("action"),
						data: {"shoppingListName" : shoppingListName},
						type: method,
						success: function(data) 
						{
							ACC.shoppinglists.refreshAllShoppingListsPage(data);
						},
						error: function() 
						{
							var globalErrorMessage = {};
			    			globalErrorMessage.globalMessageType = ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER; 
			    			globalErrorMessage.message = ACC.messages.shoppingListCreationError; 
			    			$(".global-alerts").appendGlobalMessages([globalErrorMessage]);
			    			$('body, html').animate({scrollTop:0}, 'slow');		
						}
					});
    			} else {
					var globalErrorMessage = {};
	    			globalErrorMessage.globalMessageType = ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER; 
	    			globalErrorMessage.message = ACC.messages.shoppingListNameEmptyErrorMessage; 
	    			$(".global-alerts").appendGlobalMessages([globalErrorMessage]);
	    			$('body, html').animate({scrollTop:0}, 'slow');		
    			}
    			
    			return false;
    		});
    	}
    }
}