ACC.categorypage = {

    _autoload: [
        "bindShowMoreButton",
        "bindFacetExpansion",
        "bindFilterFacetButton",
        "bindFacetApplyButton",
        "bindOtherSearchPageTabs"
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
				  ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.showMoreProductsError);
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
    
    bindOtherSearchPageTabs : function(){
    	$('.content-button').click(function(e) {
            e.preventDefault();
            $('.content-button').add('active');
            $('.product-button').remove('active');
            $('.product-search').hide();
            $('.content-search').show();
        });
        $('.product-button').click(function(e) {
            e.preventDefault();
            $('.product-button').add('active');
            $('.content-button').remove('active');
            $('.content-search').hide();
            $('.product-search').show();
        });
        /*//fillters checkbox
        $(".facet__list__label").click(function(e) {
            e.preventDefault();
            if ($(this).prev().prop("checked") == false) {
                $(this).prev().prop("checked", true);
            } else {
                $(this).prev().prop("checked", false);
            }
        });
       */
    }
};