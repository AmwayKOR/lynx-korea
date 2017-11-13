ACC.productdetail = {

	_autoload: [
        "bindproduct",
        "bindLearningOpportunitiesCarousel",
        "bindProductReferencesCarousel",
        "bindRecentlyViewedProductsCarousel"
	],
	
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
	},
	
	bindproduct: function () {
		function dialogPos() {
            var W = $(window).width();
            var cW = $(".cbox").width();
            $(".cbox").css("left", (W - cW) / 2);
        }
        $(function() {
            function dialogPos() {
                var W = $(window).width();
                var cW = $(".cbox").width();
                $(".cbox").css("left", (W - cW) / 2);
            }
            $('#addToDitto').click(function(event) {
                event.preventDefault();
                $(".cboxAddToYourDitto,.overlay").show();
                dialogPos();
            });
            $(".cart-popup__close,.closeCbox").click(function() {
                $(".cbox,.overlay").hide();
            });
            $('#creatNewDitto').click(function(event) {
                event.preventDefault();
                $(".cboxCreateNewDitto,.overlay").show();
                dialogPos();
            });

            $('#searchContacts').click(function(event) {
                event.preventDefault();
                $(".cboxCreateNewDittoNext,.overlay").show();
                dialogPos();
            });

            $('#backPrevious').click(function(event) {
                event.preventDefault();
                $(".cboxCreateNewDittoNext").hide();
                $(".creatNewDitto").show();
                dialogPos();
            });
        });

        $(function() {
            function dialogPos() {
                var W = $(window).width();
                var cW = $(".cbox").width();
                $(".cbox").css("left", (W - cW) / 2);
            }
        $(".select2-hidden-accessible").select2();
            $(window).resize(function() {
                dialogPos();
            });
            $(".cart-popup__close,.closeCbox").click(function() {
                $(".cbox,.overlay").hide();
            });

            $(".btn-calculate").click(function() {
                $(this).addClass("btn-gray");
                $(".usage-content-hide").show();
            });
            $(".btn-calculate-close").click(function() {
                $(".btn-calculate").removeClass("btn-gray");
                $(".usage-content-hide").hide();
            })
        });


        $(function() {

            //add to shopping list event
            $(".js-add-list-shopping-button").click(function(){

                if($(".add-to-shopping-list-component-container").hasClass("display-none")){
                    $(".add-to-shopping-list-component-container").removeClass("display-none");
                }else{
                    $(".add-to-shopping-list-component-container").addClass("display-none");
                }
            });

            //reviews event
            $(".description-number,.description-reviews").click(function(){
                $("#reviewsbody.accordion-panel-collapse.collapse").addClass("in");
                $("#reviewsbody.accordion-panel-collapse.collapse").css("height","auto");
                $("#reviews .accordion-toggle.collapsed").removeClass("collapsed");
            });

            $('#addToCartButton').click(function(event) {
                event.preventDefault();
                $(".addToYourShoppingCart,.overlay").show();
                dialogPos();
            });


			$('.usageCalculatorLink').click(function(event) {
                event.preventDefault();
                $(".cbox2,.overlay").show();
                dialogPos();
            });
            
        })
	}
};
