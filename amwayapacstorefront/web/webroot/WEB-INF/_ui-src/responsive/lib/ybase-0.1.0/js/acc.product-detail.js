ACC.productdetail = {

	_autoload: [
        "bindproduct0",
        "bindproduct1",
        "bindproduct2",
        "bindproduct3",
        "bindproduct4",
	],
	bindproduct0: function () {
		function dialogPos() {
            var W = $(window).width();
            var cW = $(".cbox").width();
            $(".cbox").css("left", (W - cW) / 2);
        }
	},
	bindproduct1: function () {
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
    },
    bindproduct2: function () {
	    	$(function() {
		        $('.usageCalculatorLink').click(function() {
		            $(".cbox2,.overlay").show();
		            var W = $(window).width();
		            var cW = $(".cbox2").width();
		            $(".cbox2").css("left", (W - cW) / 2);
		        });
		        //$(".select2-hidden-accessible").select2();
		
		        $(".cart-popup__close").click(function() {
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
		
		
		        $(window).resize(function() {
		            dialogPos();
		            dialogPos3();
		            dialogPos2();
		            });
		        });
    },
    bindproduct3: function () {
	    	$(function() {
	    		
	            //reviews event
	            $(".description-number,.description-reviews").click(function(){
	                $("#reviewsbody.accordion-panel-collapse.collapse").addClass("in");
	                $("#reviewsbody.accordion-panel-collapse.collapse").css("height","auto");
	                $("#reviews .accordion-toggle.collapsed").removeClass("collapsed");
	            });
	
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
	            //enlarge picture move by mouse
	            $("#top-image").mousemove(function(e) {
	                var imageHeight = document.getElementById('top-image').height;
	                var winHeight = $(window).height();
	                var newvalueY = e.pageY * (imageHeight - winHeight) / winHeight;
	                $('#top-image').css("top", "-" + newvalueY + "px");
	            });
	
	            //search box click event
	            $(".icon-search").click(function(e) {
	                e.preventDefault();
	                var value = $(".form-control.js-site-search-input.ui-autocomplete-input[placeholder]").val();
	                console.log(value);
	                if (value == 'did you mean') {
	                    window.location.href = "Did-you-mean.html";
	                } else if (value == 'artistry') {
	                    window.location.href = "search-result-page.html";
	                } else {
	                    window.location.href = "search-no-result.html";
	                }
	            });
	
	            //  add to shopping cart
	            $('#addToCartButton').click(function(event) {
	                event.preventDefault();
	                $("#add-to-cart-box.cbox,.overlay").show();
	                $("#colorbox").hide();
	                dialogPos3();
	            });
	            $('#addToDitto').click(function(event) {
	                event.preventDefault();
	                $(".cbox3,.overlay").show();
	                dialogPos();
	            });
	            $(".cart-detail__dropdown-menu").on("click",function(e){
	                e.stopPropagation();
	            });
	
	            $(".cart-popup__close").click(function() {
	                $("#add-to-cart-box.cbox,.overlay").hide();
	            })
	            //add to shopping list event
	            $(".js-add-list-shopping-button").click(function(){
	
	                if($(".add-to-shopping-list-component-container").hasClass("display-none")){
	                    $(".add-to-shopping-list-component-container").removeClass("display-none");
	                }else{
	                    $(".add-to-shopping-list-component-container").addClass("display-none");
	                }
	//                if($(this).parent(".add-to-shopping-list-container").children("div:first").hasClass("display-none")){
	//                    $(this).parent(".add-to-shopping-list-container").children("div:first").removeClass("display-none")
	//                }else{
	//                    $(this).parent(".add-to-shopping-list-container").children("div:first").addClass("display-none")
	//                }
	            });
	
	            $("#colorbox.add-to-shopping-list-box #cboxClose").click(function() {
	                $("#colorbox.add-to-shopping-list-box,.overlay").hide();
	            });
	
	            $('.panel-action-text-btn.pull-right.js-add-new-shopping-list-link-component').click(function(event) {
	                event.preventDefault();
	                $("#colorbox.add-to-shopping-list-box,.overlay").css("display","block");
	                $("#colorbox.add-to-shopping-list-box").css("visibility","visible")
	                $(".add-to-shopping-list-component-container").addClass("display-none");
	                dialogPos();
	            });
	            $('#newShoppingListName').bind('input propertychange', function() {
	                if($(this).val().length > 0){
	                    $("#colorbox.add-to-shopping-list-box #createShoppingList").removeAttr("disabled");
	                }else{
	                    $("#colorbox.add-to-shopping-list-box #createShoppingList").attr("disabled","true");
	                };
	            });
	
	            $("#createShoppingList").click(function(event){
	                event.preventDefault();
	                $("#add-to-shopping-list,.overlay").css("display","block");
	                $("#add-to-shopping-list").css("visibility","visible")
	                $("#colorbox.add-to-shopping-list-box").css("display","none");
	                $("#colorbox.add-to-shopping-list-box").css("visibility","hidden")
	                dialogPos2();
	
	            });
	            $("#add-to-shopping-list .cart-popup__close").click(function() {
	                $("#add-to-shopping-list,.overlay").hide();
	            });
	        })
    },
    bindproduct4: function () {
    		
        
        function dialogPos() {
	        var W = $(window).width();
	        var cW = $("#colorbox.add-to-shopping-list-box").width();
	        $("#colorbox.add-to-shopping-list-box").css("left", (W - cW) / 2);
	    }
	    function dialogPos2() {
	        var W = $(window).width();
	        var cW = $("#add-to-shopping-list").width();
	        $("#add-to-shopping-list").css("left", (W - cW) / 2);
	    }
	    function dialogPos3() {
	        var W = $(window).width();
	        var cW = $("#add-to-cart-box.cbox").width();
	        $("#add-to-cart-box.cbox").css("left", (W - cW) / 2);
	    }
    }

};
