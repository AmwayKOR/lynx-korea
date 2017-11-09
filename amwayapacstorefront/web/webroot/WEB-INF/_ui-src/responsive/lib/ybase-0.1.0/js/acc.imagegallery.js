ACC.imagegallery = {

	_autoload: [
		"bindGalleryImagesThumbnailCarousel",
		"bindImageEnlarge",
		"bindGalleryImageClickImageUpdate"
	],

	bindGalleryImageClickImageUpdate: function() {
		$(document).on("click", "div.page-productDetails img.js-product-thumnail-image", function() {
			var productImageUrl = $(this).data("productUrl");
			var zoomImageUrl = $(this).data("zoomUrl");
			if (productImageUrl) {
				$(this).closest("div.product-image-gallery").find("img.product-main-image").attr("src", productImageUrl);
				$(this).closest("div.product-image-gallery").find("img.product-zoom-image").attr("src", zoomImageUrl);
			}
		})
	},
	
	bindImageEnlarge: function() {
		$(document).on("click", "div.js-zoom-center button.enlarge2", function() {
			if ($(this).closest("div.js-product-image-gallery").find("div.enlarge-content-wrapper img").attr("src")) {
				$("#product-zoom-image-panel").html($(this).closest("div.js-product-image-gallery").find("div.enlarge-content-wrapper").html());
				$("#product-zoom-image-panel").modal("show");
				ACC.imagegallery.bindProductZoomMovement();
			}
		})
	},

	bindGalleryImagesThumbnailCarousel: function() {
		$('#productListTab').owlCarousel({
            loop: true,
            nav: true,
            dots: false,
            margin: 0,
            items: 1,
            responsiveClass: true,
            responsive: {
                768 : {
                    items: 3
                },
                1200 : {
                    items: 5
                }
            }
        });
	},
	
	bindProductZoomMovement: function() {
	     //enlarge picture move by mouse
       $("div#product-zoom-image-panel #top-image").mousemove(function(e) {
           var imageHeight = $("div#product-zoom-image-panel #top-image").height();
           var winHeight = $(window).height();
           var newvalueY = (e.pageY - $(window).scrollTop()) * (imageHeight - winHeight) / winHeight;
           $("div#product-zoom-image-panel #top-image").css("top", "-" + newvalueY + "px");
       });
	}
};