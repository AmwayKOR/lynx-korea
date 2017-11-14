ACC.productdetail = {

	_autoload: [
        "bindLearningOpportunitiesCarousel",
        "bindProductReferencesCarousel",
        "bindRecentlyViewedProductsCarousel",
        "bindReviewSummaryShowReviews",
        "bindUsageCalculator",
        "bindCalculateUsage",
        "bindAddToDitto",
        "bindCreateNewDitto",
        "bindContactsPopup",
        "bindPreviousScreenPopup"
	],
	
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
	
	bindUsageCalculator: function() {
		$('.usageCalculatorLink').click(function(event) {
            event.preventDefault();
            ACC.popup.showPopup($("#usage-calculator-poup-wrapper").html());
        });
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
