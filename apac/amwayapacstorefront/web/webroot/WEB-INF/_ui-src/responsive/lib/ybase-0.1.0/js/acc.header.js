ACC.header = {

	_autoload: [
        "bindHeader",
        "bindHeader2",
        "bindHeaderProfilePopup",
        "bindLoginRegisterButton",
        "bindOpenLoginDrop"
	],
	
	bindHeaderProfilePopup: function() {
        $(".js-my-account-menu").click(function () {
            ACC.header.openLoginDrop();
        });
	},
	
	bindOpenLoginDrop : function(){
		$(document).on('shown.bs.collapse', '#login-drop-content', function(){
			$('header').addClass('userinfo-open');
			$('.top-search').removeClass('search-open');
	        $('header').removeClass('mincart-open');
		});
		$(document).on('hidden.bs.collapse', '#login-drop-content', function(){
			$('header').removeClass('userinfo-open');
			$('.top-search').removeClass('search-open');
	        $('header').removeClass('mincart-open');
		});
	},
	
	bindLoginRegisterButton :  function(){
		$(document).on("click", ".sign-in-register", function(){
			ACC.popup.closePopup();
			ACC.header.openLoginDrop();
		});
	},
	
	bindHeader: function () {
        function mobileEvent () {
            $(".mobile-user-btn").click(function () {
                $('header').hasClass('userinfo-open') ? $('header').removeClass('userinfo-open') : $('header').addClass('userinfo-open');
                $('.top-search').removeClass('search-open');
                $('header').removeClass('mincart-open');
            });
            $(".mobile-search-btn").click(function () {
                $('.top-search').hasClass('search-open') ? $('.top-search').removeClass('search-open') : $('.top-search').addClass('search-open');
                $('header').removeClass('userinfo-open');
                $('header').removeClass('mincart-open');
            });
            $(".js-mini-cart-link").click(function () {
                $('header').hasClass('mincart-open') ? $('header').removeClass('mincart-open') : $('header').addClass('mincart-open');
                $('.top-search').removeClass('search-open');
                $('header').removeClass('userinfo-open');
            });

            $(".mobile-popover-close").click(function () {
                $('header').removeClass('userinfo-open');

            });

            $(".mobile-popover-close2").click(function () {
                $('header').removeClass('mincart-open');
            });
        }
        mobileEvent();
    },
	bindHeader2 : function () {
        
        $(window).resize(navToggling);

        function navToggling() {
            if (window.innerWidth < 768) {
                $('.overlay-menu-mobile__panel__heading').click(function () {
                    if($(this).hasClass('active')){
                        $(this).removeClass('active');
                        $(this).next('.panel-collapse').removeClass('in');
                        $(this).parent('.panel').siblings('.panel').show();
                        $(this).parents('.nav-list-element').siblings('.nav-list-element').show();
                    }else{
                        $(this).addClass('active');
                        $(this).parent('.panel').siblings('.panel').hide();
                         $(this).parents('.nav-list-element').siblings('.nav-list-element').hide();
                    }
                });
            } else {
            }
        }
        navToggling();
    }

};
