ACC.footer = {

	_autoload: [
        "bindFooter"
	],
	
	bindFooter: function () {
		function linkToggle () {
            $(".quick-links-item").click(function () {
                if($(this).hasClass("switcher")){
                    $(this).removeClass("switcher");
                }else{
                    $(this).addClass("switcher");
                    $(this).siblings().removeClass("switcher");

                }
            });
        }
        linkToggle();
    }

};
