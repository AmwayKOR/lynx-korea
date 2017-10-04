ACC.categorypage = {

    _autoload: [
        "bindShowMoreButton"
        ],

    
    bindShowMoreButton : function(){
    	$(document).on("click", "#show-more", function(){
    		var listingDiv = $(this).closest(".product__listing").find(".product-list-container.pageBlank");
    		var seeMoreDiv = $(this).closest(".product__listing").find(".show-more-products-wrapper");
    		$form = $(this).closest(".show-more-products-wrapper").find("form");
    		$.ajax({ 
  			  url:$form.attr("action")+"/results-display",
  			  data:$form.serialize(),
  			  type: "GET",
  			  success: function(data) {
  				var listingReplacementDiv = $(data).find(".product-listing-section").html();
  				listingDiv.removeClass("pageBlank");
  				listingDiv.html(listingReplacementDiv);
  				seeMoreDiv.html($(data).find(".see-more-section").html());
  			  },
  				error: function() 
  				{}
  		  });
    	});
    }
};