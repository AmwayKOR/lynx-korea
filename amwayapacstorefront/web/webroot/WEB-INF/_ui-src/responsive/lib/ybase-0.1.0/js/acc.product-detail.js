ACC.productdetail = {

	_autoload: [
        "bindLearningOpportunitiesCarousel",
        "bindProductReferencesCarousel",
        "bindRecentlyViewedProductsCarousel",
        "bindReviewSummaryShowReviews",
        "bindCalculateUsage",
        "bindAddToDitto",
        "bindCreateNewDitto",
        "bindContactsPopup",
        "bindPreviousScreenPopup",
        "bindAddToCart",
        "bindAddToShoppingListForm",
        "bindNewList"
	],
	
	bindNewList: function() {
		 $(document).on("click", '#newlist', function(event) {
			 var a = $(this);
			 $.ajax({
    				success: function(data) 
       				{
       				 window.location='shopping-lists/all';
       			    },
       				error: function(error) 
       				{
       					console.log(error);
       		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListAddProductError);
       				}
		 });
		 })
	},
	
	bindAddToShoppingListForm: function() {
		
            $('#multiShoppingListUpdateForm').ajaxForm({
  				success: function(data) 
  				{
  					// data contains the shopping list page content
  			    	if ($(data).filter("div.page-content").length > 0) {
  			    		ACC.popup.showPopup($(data).filter("div.page-content").html());
  			    		ACC.global.findAndUpdateGlobalMessages(data, false);
  			    	} else {
  			    		ACC.global.findAndUpdateGlobalMessages(data);
  			    	}
  			    },
  				error: function(error) 
  				{
  					console.log(error);
  		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListAddProductError);
  				}
  			});           
   		 },
	
	bindAddToCart: function() {
	            	 $(document).on("click", '.addToList, #addToList', function(event) {
	            		 if($(".cart-detail__dropdown-menu.dropdown-menu.shopping-list-popup-wrapper").css('display')=='none'){
		                  event.preventDefault();
		                  var button = $(this);
		                  var productcode= button.data("productCode");
		                  var childelement = button.parent().children(".cart-detail__dropdown-menu.dropdown-menu.shopping-list-popup-wrapper");
		                  console.log(childelement.length);
		                  $.ajax({ 
		       				url: button.data("addToShoppingListUrl"),
		       				type: "GET",
		       				success: function(data) 
		       				{
		       			
		       					childelement.html(data);
		       					$(".js-populated-product-code").val(productcode);
		       					childelement.show();
		       			        ACC.productdetail.bindAddToShoppingListForm();
		       			     	
		       			    },
		       				error: function(error) 
		       				{
		       					console.log(error);
		       		    		ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.shoppingListAddProductError);
		       				}
		       			});           
	            		 }else{
	            			 $(".cart-detail__dropdown-menu.dropdown-menu.shopping-list-popup-wrapper").hide();
	            		 }
		              });
	},

	bindAddToDitto: function() {
		 $(document).on("click", '#addToDitto', function(event) {
             event.preventDefault();
             ACC.popup.showPopup($("#added-to-ditto-wrapper").html());
         });
	},

	bindCreateNewDitto: function() {
		$(document).on("click", '#creatNewDitto', function(event) {
            event.preventDefault();
            ACC.popup.showPopup($("#create-new-ditto-wrapper").html());
        });
	},

	bindContactsPopup: function() {
		 $(document).on("click", '#searchContacts', function(event) {
             event.preventDefault();
			 ACC.popup.showPopup($("#search-contacts-ditto-wrapper").html());
         });
	},

	bindPreviousScreenPopup: function() {
		$(document).on("click", '#backPrevious', function(event) {
            event.preventDefault();
            ACC.popup.closePopup();
            ACC.popup.showPopup($("#create-new-ditto-wrapper").html());
        });
	},

	bindCalculateUsage: function() {
        $(document).on("click", ".btn-calculate", function() {
            $(this).addClass("btn-gray");
            $(".usage-content-hide").show();
        });
        $(document).on("click", ".btn-calculate-close", function() {
            $(".btn-calculate").removeClass("btn-gray");
            $(".usage-content-hide").hide();
        })
	},

	bindReviewSummaryShowReviews: function(){
        $(".description-number,.description-reviews").click(function(){
            $("#reviewsbody.accordion-panel-collapse.collapse").addClass("in");
            $("#reviewsbody.accordion-panel-collapse.collapse").css("height","auto");
            $("#tabreview .accordion-toggle.collapsed").removeClass("collapsed");
			ACC.tabs.showReviewsAction("reviews");
			ACC.tabs.showReviewBtn(".all-reviews-btn");
        });
	},

	bindRecentlyViewedProductsCarousel: function() {
		  $('#recentlyViewedListTab').owlCarousel({
              loop: true,
              nav: true,
              dots: false,
              margin: 0,
              items: 1,
              responsiveClass: true,
              responsive: {
                  480 : {
                      items: 2
                  },
                  768 : {
                      items: 4
                  },
                  960 : {
                      items: 5
                  },
                  1200 : {
                      items: 7
                  }
              }
          });
	},

	bindProductReferencesCarousel: function() {
		 $('#productSuggestListTabs').owlCarousel({
             loop: true,
             nav: true,
             dots: false,
             margin: 0,
             items: 1,
             responsiveClass: true,
             responsive: {
                 480 : {
                     items: 1
                 },
                 768 : {
                     items: 2
                 },
                 960 : {
                     items: 3
                 },
                 1200 : {
                     items: 4
                 }
             }
         });
	},

	bindLearningOpportunitiesCarousel: function() {
		 $('#productLearningListTabs').owlCarousel({
             loop: true,
             nav: true,
             dots: false,
             margin: 0,
             items: 1,
             responsiveClass: true,
             responsive: {
                 480 : {
                     items: 1
                 },
                 960 : {
                     items: 2
                 },
                 1200 : {
                     items: 3
                 }
             }
         });
	}
};
