ACC.categorypage = {

    _autoload: [
        "bindShowMoreButton",
        "bindFacetExpansion",
        "bindFilterFacetButton",
        "bindQuickViewPopup",
        "bindPopupCloseButton",
        "bindFacetApplyButton",
        "bindOthers"
        ],

    
    bindShowMoreButton : function(){
    	$(document).on("click", "#show-more", function(){
    		var listingDiv = $(this).closest(".product-list-right-component").find(".product__list--item").last();
    		var seeMoreDiv = $(this).closest(".show-more-products-wrapper");
    		$form = $(this).closest(".show-more-products-wrapper").find("form");
    		
    		var showMoreUrl = ACC.config.encodedContextPath + $form.attr("action");
    		if(!(showMoreUrl.endsWith("results-display"))){
    			showMoreUrl = showMoreUrl + "/results-display";
    		}
    		
    		$.ajax({
  			  url:showMoreUrl,
  			  data:$form.serialize(),
  			  type: "GET",
  			  success: function(data) {
  				var listingReplacementDiv = $(data).find(".product-listing-section").html();
  				$(listingReplacementDiv).insertAfter($(listingDiv));
  				seeMoreDiv.html($(data).find(".see-more-section").html());
  			  },
			  error: function(){
				  
			  }
  		  });
    	});
    },
    
    bindFacetExpansion : function(){
    	$(".js-facet-checkbox").each(function(){
    		if($(this).prop("checked")){
    			if($(this).closest(".panel").find(".collapse-button").hasClass("collapsed")){
    				$(this).closest(".panel").find(".collapse-button").trigger("click");
    			}
    		}
    	});
    },
    
    bindFilterFacetButton : function(){
    	$(document).on("click", ".filter-facet-button", function(e){
    		e.preventDefault();
            if(!$(".new-plp .pagination-wrapper .product__facet").hasClass("active")){
                $(".new-plp .filter-facet-button").addClass("active");
                $(".new-plp .pagination-wrapper .product__facet").addClass("active");
            }else{
                $(".new-plp .pagination-wrapper .product__facet").removeClass("active");
                $(".new-plp .filter-facet-button").removeClass("active");
            }
    	});
    },
    
    bindQuickViewPopup : function(){
    	$(window).resize(function() {
    		ACC.categorypage.dialogPos();
    		ACC.categorypage.dialogPos3();
        });
    	
    	$(document).on("click", ".cart-popup__close.quick-view-close", function(){
    		$(".view-box,.overlay").hide();
    	});
      
        $('.quick-view-btn').click(function(event) {
            event.preventDefault();
            var productCode = $(this).data("productCode");
            $.ajax({
    			  url:ACC.config.contextPath + "/p/" + productCode + "/quickView",
    			  type: "GET",
    			  success: function(data) {
    				  $(".view-box").html(data);
    				  $(".view-box,.overlay").show();
    		          ACC.categorypage.dialogPos();
    			  }
    		  });
        });
    },
    
    bindPopupCloseButton : function(){
    	$(".cart-popup__close").click(function() {
    		$("#add-to-cart-box.cbox,.overlay").hide();
    	});
    },
    
    
    bindFacetApplyButton : function(){
    	$(document).on("click", ".facet-button-apply", function(){
    		var facetQuery = $(this).closest(".js-facet").data("currentQuery");
    		
    		$(this).closest(".panel-body").find(".js-facet-mobile-checkbox:checked").each(function(){
    			var enodedQuery = ":" + $(this).data("facetCode") + ":" + encodeURIComponent($(this).data("facetValueCode"));
    			if(!facetQuery.includes(enodedQuery)){
    				facetQuery = facetQuery + enodedQuery;
    			}
        	});
    		
    		$(this).closest(".panel-body").find(".js-facet-mobile-checkbox:not(:checked)").each(function(){
    			var enodedQuery = ":" + $(this).data("facetCode") + ":" + encodeURIComponent($(this).data("facetValueCode"));
    			if(facetQuery.includes(enodedQuery)){
    			facetQuery = facetQuery.replace(enodedQuery,"");
    			}
        	});
    		
    		$form = $(this).closest(".mob-product-facet").find("#mobile-facet-apply-form");
    		$form.find("input").val(facetQuery);
    		$form.submit();
    	});
    },
    
    bindOthers : function(){
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
    	$('.product-list__item-link .btn-blue-white').click(function(event) {
    		event.preventDefault();
    		$("#add-to-cart-box.cbox,.overlay").show();
    		$("#colorbox").hide();
    		ACC.categorypage.dialogPos3();
    	});
    },
    
    dialogPos : function(){
        var W = $(window).width();
        var cW = $(".view-box").width();
        $(".view-box").css("left", (W - cW) / 2);
    },
    
    dialogPos3 : function(){
        var W = $(window).width();
        var cW = $("#add-to-cart-box.cbox").width();
        $("#add-to-cart-box.cbox").css("left", (W - cW) / 2);
    }
    
    
};