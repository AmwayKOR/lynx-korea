ACC.shoppinglists = {

    _autoload: [
    	"bindCreateShoppingList",
    	"bindAddProductToShoppingListForm",
    	"bindEditShoppingListNameLink",
    	"bindCancelEditShoppingListNameLink",
    	"bindSubmitShoppingListNameFormLink",
    	"bindUpdateShoppingListNameForm",
    	"bindUpdateShoppingListNameInputEscape",
    	"bindRemoveProductFromShoppingListLink",
    	"bindRemoveShoppingListLink"
    ],
    
    bindRemoveShoppingListLink: function() {
        
        // only bind if the current page is shopping lists page
        if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
            $(".page-content-wrapper.page-shopping-list-details").on("click", "div#shopping-list-name-section span.delete-shopping-list-link", function() {
                $(this).closest("form#removeShoppingListForm").submit();
            });
        }
    },

    bindRemoveProductFromShoppingListLink: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("click", "ul.shopping-cart-item-list div.list-item-remove span.remove-product-from-shopping-list", function() {

    			var method = "POST";
    			var shoppingListUid = $(this).data("shoppingListUid");
    			var productCode = $(this).data("productCode");
    			var url = $(this).data("removeUrl");
    			
    			if (ACC.global.isNullOrWhiteSpace(productCode)) {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.productCodeEmptyErrorMessage);
    			} else if (ACC.global.isNullOrWhiteSpace(shoppingListUid)) {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListUidEmptyErrorMessage);
    			} else {
					$.ajax({
						url: url,
						data: {"shoppingListUid" : shoppingListUid, "productCode" : productCode},
						type: method,
						success: function(data) 
						{
							ACC.shoppinglists.refreshShoppingListDetailsPage(data);
					    },
						error: function() 
						{
				    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListRemoveProductError);
						}
					});
    			}    			
    			return false;
    		});
    	}
    
    },
    
    bindUpdateShoppingListNameInputEscape: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("keydown", "form#updateShoppingListNameForm input[name=shoppingListName]", function(event) {
    			if (event.keyCode == 27) { 
    				$(this).closest("form#updateShoppingListNameForm").find("a.cancel-name-update-form").trigger("click");
    		    }
    		});
    	}
    },
    
    bindSubmitShoppingListNameFormLink: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("click", "#name-update-form a.submit-name-update-form", function(event) {
    			event.preventDefault();
    			$(this).closest("form#updateShoppingListNameForm").submit();
    		});
    	}
    },
    
    bindUpdateShoppingListNameForm: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("submit", "form#updateShoppingListNameForm", function() {
    			
    			var $form = $(this);
    			var method = $form.attr("method") ? $form.attr("method").toUpperCase() : "POST";
    			var shoppingListUid = $form.find("input[name=shoppingListUid]").val();
    			var shoppingListName = $form.find("input[name=shoppingListName]").val();
    			
    			if (ACC.global.isNullOrWhiteSpace(shoppingListName)) {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListNameEmptyErrorMessage);
    			} else if (ACC.global.isNullOrWhiteSpace(shoppingListUid)) {
		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListUidEmptyErrorMessage);
    			} else if ($form.find("input[name=shoppingListName]").val() == $form.find("input[name=shoppingListName]").data("originalName")) {
        			$(this).closest("div#name-update-form").hide();
        			$(this).closest("div#name-update-form").find("form#updateShoppingListNameForm input.shopping-list-name").val($(this).closest("div#name-update-form").find("form#updateShoppingListNameForm input.shopping-list-name").data("originalName"));
        			$(this).closest("div#shopping-list-name-section").find("div#name-display-section").show();
    			} else {
					$.ajax({
						url: $form.attr("action"),
						data: {"shoppingListUid" : shoppingListUid, "shoppingListName" : shoppingListName},
						type: method,
						success: function(data) 
						{
							ACC.shoppinglists.displayGlobalMessagesBasedOnAjaxResponse(data);
							if ($(data).filter("div.shopping-list-name-display-section").length > 0) {
								var shoppingListNameInput = $form.find("input[name=shoppingListName]");
								shoppingListNameInput.data("originalName", shoppingListName);
								shoppingListNameInput.closest("div#shopping-list-name-section").find("div#name-display-section").html($(data).filter("div.shopping-list-name-display-section").html());
				    			shoppingListNameInput.closest("div#name-update-form").hide();
				    			shoppingListNameInput.closest("div#shopping-list-name-section").find("div#name-display-section").show();
					    	}
					    },
						error: function() 
						{
				    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListNameUpdateError);
						}
					});
    			}    			
    			return false;
    		});
    	}
    },
    
    bindCancelEditShoppingListNameLink: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("click", "#name-update-form a.cancel-name-update-form", function(event) {
    			event.preventDefault();
    			$(this).closest("div#name-update-form").hide();
    			$(this).closest("div#name-update-form").find("form#updateShoppingListNameForm input.shopping-list-name").val($(this).closest("div#name-update-form").find("form#updateShoppingListNameForm input.shopping-list-name").data("originalName"));
    			$(this).closest("div#shopping-list-name-section").find("div#name-display-section").show();
    		});
    	}
    },
    
    bindEditShoppingListNameLink: function() {

    	// only bind if the current page is shopping lists page
    	if ($(".page-content-wrapper.page-shopping-list-details").length > 0) {
    		$(".page-content-wrapper.page-shopping-list-details").on("click", "#name-display-section a.name-edit-link", function(event) {
    			event.preventDefault();
    			$(this).closest("div#name-display-section").hide();
    			$(this).closest("div#shopping-list-name-section").find("div#name-update-form").show();
    			$(this).closest("div#shopping-list-name-section").find("form#updateShoppingListNameForm input[name=shoppingListName]").focus();
    		});
    	}
    },
    
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
    		ACC.shoppinglisttotals.refreshShoppingListAllTotals();
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