ACC.global = {

    _autoload: [
        ["passwordStrength", $('.password-strength').length > 0],
        "bindToggleOffcanvas",
        "bindToggleXsSearch",
        "bindHoverIntentMainNavigation",
        "initImager",
        "backToHome",
        "ajaxBlockUI",
        "formatPrice",
        "isNatural"
    ],
    
    isNatural: function(value) {
    	var number = Number(value);
    	return (value) && (Math.floor(number) == number) && (number >= 0.0) && (number !== Infinity);
    },
    
    formatPrice: function(value, currency){
    	  var formattedValue = "0.00"
		  if (value) {
		        var parts = value.toString().split(".");
		        parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		        if (!parts[1]) {
		            parts[1] = "00";
		        }
		        if (parts[1].length == 1) {
		            parts[1] = parts[1] + '0';
		        }
		        formattedValue = parts.join(".");
		  }
    	  if (currency) {
    		  formattedValue = currency + formattedValue;
    	  }
    	  return formattedValue;
    },
    
    findAndUpdateGlobalMessages: function(data, hideMessage = true) {
    	if (($(data).filter(".global-alerts").length > 0) && ($(data).filter(".global-alerts").html().trim() != "")) {
    		$('html').animate({scrollTop:0}, 'slow', function() {
    			$(".global-alerts").append($(data).filter(".global-alerts").html());
    		});	
    		if (hideMessage) {
    			setTimeout(function(){ $(".global-alerts").html(""); }, 5000);
    		}
    	}
    },
    
    appendGlobalMessage : function(globalMessageType, message) {
		var globalErrorMessage = {};
		globalErrorMessage.globalMessageType = globalMessageType; 
		globalErrorMessage.message = message; 
		$('html').animate({scrollTop:0}, 'slow', function() {
			$(".global-alerts").appendGlobalMessages([globalErrorMessage]);
		});	
    },
    
    ajaxBlockUI: function() {
    	$(document).ajaxStart(function() {
    		$.blockUI({ message: ACC.common.processingMessage, css: ACC.common.processingImageCSS, overlayCSS:ACC.common.processingImageOverlayCSS });
    	}).ajaxStop(function() {
    		$.unblockUI();
    	});
    },
    
    isNullOrWhiteSpace: function(str) {
    	  return (!str || str.length === 0 || /^\s*$/.test(str))
    },

    passwordStrength: function () {
        $('.password-strength').pstrength({
            verdicts: [ACC.pwdStrengthTooShortPwd,
                ACC.pwdStrengthVeryWeak,
                ACC.pwdStrengthWeak,
                ACC.pwdStrengthMedium,
                ACC.pwdStrengthStrong,
                ACC.pwdStrengthVeryStrong],
            minCharText: ACC.pwdStrengthMinCharText
        });
    },

    bindToggleOffcanvas: function () {
        $(document).on("click", ".js-toggle-sm-navigation", function () {
            ACC.global.toggleClassState($("main"), "offcanvas");
            ACC.global.toggleClassState($("html"), "offcanvas");
            ACC.global.toggleClassState($("body"), "offcanvas");
            ACC.global.resetXsSearch();
        });
    },

    bindToggleXsSearch: function () {
        $(document).on("click", ".js-toggle-xs-search", function () {
            ACC.global.toggleClassState($(".site-search"), "active");
            ACC.global.toggleClassState($(".js-mainHeader .navigation--middle"), "search-open");
        });
    },

    resetXsSearch: function () {
        $('.site-search').removeClass('active');
        $(".js-mainHeader .navigation--middle").removeClass("search-open");
    },

    toggleClassState: function ($e, c) {
        $e.hasClass(c) ? $e.removeClass(c) : $e.addClass(c);
        return $e.hasClass(c);
    },

    bindHoverIntentMainNavigation: function () {

        enquire.register("screen and (min-width:" + screenMdMin + ")", {

            match: function () {
                // on screens larger or equal screenMdMin (1024px) calculate position for .sub-navigation
                $(".js-enquire-has-sub").hoverIntent(function () {
                    var $this = $(this),
                        itemWidth = $this.width();
                    var $subNav = $this.find('.js_sub__navigation'),
                        subNavWidth = $subNav.outerWidth();
                    var $mainNav = $('.js_navigation--bottom'),
                        mainNavWidth = $mainNav.width();

                    console.log($subNav);

                    // get the left position for sub-navigation to be centered under each <li>
                    var leftPos = $this.position().left + itemWidth / 2 - subNavWidth / 2;
                    // get the top position for sub-navigation. this is usually the height of the <li> unless there is more than one row of <li>
                    var topPos = $this.position().top + $this.height();

                    if (leftPos > 0 && leftPos + subNavWidth < mainNavWidth) {
                        // .sub-navigation is within bounds of the .main-navigation
                        $subNav.css({
                            "left": leftPos,
                            "top": topPos,
                            "right": "auto"
                        });
                    } else if (leftPos < 0) {
                        // .suv-navigation can't be centered under the <li> because it would exceed the .main-navigation on the left side
                        $subNav.css({
                            "left": 0,
                            "top": topPos,
                            "right": "auto"
                        });
                    } else if (leftPos + subNavWidth > mainNavWidth) {
                        // .suv-navigation can't be centered under the <li> because it would exceed the .main-navigation on the right side
                        $subNav.css({
                            "right": 0,
                            "top": topPos,
                            "left": "auto"
                        });
                    }
                    $this.addClass("show-sub");
                }, function () {
                    $(this).removeClass("show-sub")
                });
            },

            unmatch: function () {
                // on screens smaller than screenMdMin (1024px) remove inline styles from .sub-navigation and remove hoverIntent
                $(".js_sub__navigation").removeAttr("style");
                $(".js-enquire-has-sub").hoverIntent(function () {
                    // unbinding hover
                });
            }

        });
    },

    initImager: function (elems) {
        elems = elems || '.js-responsive-image';
        this.imgr = new Imager(elems);
    },

    reprocessImages: function (elems) {
        elems = elems || '.js-responsive-image';
        if (this.imgr == undefined) {
            this.initImager(elems);
        } else {
            this.imgr.checkImagesNeedReplacing($(elems));
        }
    },

    // usage: ACC.global.addGoogleMapsApi("callback function"); // callback function name like "ACC.global.myfunction"
    addGoogleMapsApi: function (callback) {
        if (callback != undefined && $(".js-googleMapsApi").length == 0) {
            $('head').append('<script class="js-googleMapsApi" type="text/javascript" src="//maps.googleapis.com/maps/api/js?key=' + ACC.config.googleApiKey + '&sensor=false&callback=' + callback + '"></script>');
        } else if (callback != undefined) {
            eval(callback + "()");
        }
    },

    backToHome: function () {
        $(".backToHome").on("click", function () {
            var sUrl = ACC.config.contextPath;
            window.location = sUrl;
        });
    }

};