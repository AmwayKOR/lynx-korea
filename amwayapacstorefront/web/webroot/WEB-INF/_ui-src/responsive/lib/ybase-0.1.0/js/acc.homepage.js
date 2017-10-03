ACC.homepage = {

	_autoload: [
        "bindHomePageCarousel"
	],
	
	bindHomePageCarousel : function() {
		$('#banner_list').owlCarousel({
            loop: true,
            nav: true,
            dots: true,
            margin: 0,
            items: 1
        });
        $('#sub_banner_list').owlCarousel({
            loop: true,
            nav: true,
            dots: false,
            margin: 0,
            items: 1

        });
        $('#featured_brandsList').owlCarousel({
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
                    items: 3
                },
                1200 : {
                    items: 5
                }
            }
        });
          $(".icon-search").click(function(e) {
              e.preventDefault();
              var value = $(".form-control.js-site-search-input.ui-autocomplete-input[placeholder]").val();
              //console.log(value);
              if (value == 'did you mean') {
                  window.location.href = "Did-you-mean.html";
              } else if (value == 'artistry') {
                  window.location.href = "search-result-page.html";
              } else {
                  window.location.href = "search-no-result.html";
              }
          });
	}

};
