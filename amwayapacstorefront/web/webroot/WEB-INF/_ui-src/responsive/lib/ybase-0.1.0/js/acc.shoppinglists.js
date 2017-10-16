ACC.shoppinglists = {

    _autoload: [
    	"bindCreateShoppingList",
    	"bindAddProductToShoppingListForm"
    ],
    
    displayGlobalMessagesBasedOnAjaxResponse: function(data) {
    	if ($(data).filter("div.error-message").length > 0) {
    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, $(data).filter("div.error-message").html());
    	}
    	if ($(data).filter("div.success-message").length > 0) {
    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.CONF_MESSAGES_HOLDER, $(data).filter("div.success-message").html());
    	}
    },
    
    refreshAllShoppingListsPage: function(data) {
    	
    	ACC.shoppinglists.displayGlobalMessagesBasedOnAjaxResponse(data);
    	// data contains the shopping list page content
    	if ($(data).filter("div.page-content").length > 0) {
    		$(".page-content-wrapper.page-shopping-lists").html($(data).filter("div.page-content").html());
    	}
    },
    
    refreshShoppingListDetailsPage: function(data) {
    	ACC.shoppinglists.displayGlobalMessagesBasedOnAjaxResponse(data);

    	// data contains the shopping list details page content as well
    	if ($(data).filter("div.page-content").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").html($(data).filter("div.page-content").html());
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
				    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListCreationError);
						}
					});
    			} else {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListNameEmptyErrorMessage);
    			}
    			
    			return false;
    		});
    	}
    },
    
    bindAddProductToShoppingListForm: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("submit", "form#addProductToShoppingListForm", function() {
    			
    			var $form = $(this);
    			var method = $form.attr("method") ? $form.attr("method").toUpperCase() : "POST";
    			var shoppingListUid = $form.find("input[name=shoppingListUid]").val();
    			var productCode = $form.find("input[name=productCode]").val();
    			
    			if (ACC.global.isNullOrWhiteSpace(productCode)) {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.productCodeEmptyErrorMessage);
    			} else if (ACC.global.isNullOrWhiteSpace(shoppingListUid)) {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListUidEmptyErrorMessage);
    			} else {
					$.ajax({
						url: $form.attr("action"),
						data: {"shoppingListUid" : shoppingListUid, "productCode" : productCode},
						type: method,
						success: function(data) 
						{
							ACC.shoppinglists.refreshShoppingListDetailsPage(data);
					    },
						error: function() 
						{
				    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListAddProductError);
						}
					});
    			}    			
    			return false;
    		});
    	}
    }
}