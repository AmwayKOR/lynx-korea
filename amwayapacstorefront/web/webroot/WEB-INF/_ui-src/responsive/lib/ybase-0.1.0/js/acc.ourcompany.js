ACC.ourcompany = {

	_autoload: [
        "bindOurCompany"
	],
	
	

	bindOurCompany: function () {
		
		$('.timelineMain').owlCarousel({
            loop: true,
            nav: true,
            dots: true,
            margin: 0,
            items: 1
        });
	}
};