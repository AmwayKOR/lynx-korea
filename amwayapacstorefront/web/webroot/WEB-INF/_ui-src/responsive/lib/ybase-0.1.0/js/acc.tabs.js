ACC.tabs = {

	_autoload: [
		"bindTabs",
	],

	bindTabs: function(){

		$e = $(".js-tabs");
		$e.on("click","#tabreview",function(e){
			e.preventDefault();
			ACC.tabs.showReviewsAction("reviews");
			ACC.tabs.showReviewBtn(".all-reviews-btn");
		});
		
		$e.on("click",".all-reviews-btn",function(e){
			e.preventDefault();
			ACC.tabs.showReviewsAction("allreviews");
			ACC.tabs.hideReviewBtn(".all-reviews-btn");
			ACC.tabs.showReviewBtn(".less-reviews-btn");
		});
		
		$e.on("click",".less-reviews-btn",function(e){
			e.preventDefault();
			ACC.tabs.showReviewsAction("reviews");
			ACC.tabs.hideReviewBtn(".less-reviews-btn");
			ACC.tabs.showReviewBtn(".all-reviews-btn");
		});
		
		$(document).on("click", '.js-writeReviewTab', function(e){
			e.preventDefault();
			$(".js-review-write").show();
			$('#reviewForm input[name=headline]').focus();
		});
		
		$(document).on("click",".js-review-write-toggle",function(e){
			e.preventDefault();
			if($(".js-review-write:visible").length<1){
				$(".js-review-write").show();

			}else{
				$(".js-review-write").hide();
			}		
		});
	},
	
	showReviewsAction: function (s)
	{
		$.get($("#reviews").data(s), function (result){
			$('#reviews').html(result);
			if($(".js-ratingCalc").length > 0){
				ACC.ratingstars.bindRatingStars();
				ACC.tabs.showingAllReviews();
			}
		});
	},
	
	hideReviewBtn: function (btnClass){
		$(btnClass).hide();		
	},
	
	showReviewBtn: function (btnClass){
		$(btnClass).show();
	},
	
	showingAllReviews: function()
	{
		var isShowingAllReviews = $("#showingAllReviews").data("showingallreviews");
		if(isShowingAllReviews){
			ACC.tabs.hideReviewBtn(".all-reviews-btn");
		}
	}
};