// $(function(){
//     $(window).resize(function () {
//         dialogPos();
//     });
//     $(".cboxClose").click(function () {
//         $(".cbox,.overlay").hide();
//     })
// });
// function dialogPos() {
//     var W = $(window).width();
//     var cW = $(".cbox").width();
//     $(".cbox").css("left",(W-cW)/2);
// }
//
// function openDialog() {
//     $(".cbox,.overlay").show();
//     dialogPos();
// }

$(function(){
    $("#header").load("header.html");
    $("#quick").load("quicklink.html");
    $("#footer").load("footer.html");
 });


// (function () {
//     var SPEED = 'slow';
//     function showSearchResults() {
//         var $this = $(this);
//
//         var $searchResult = $('.auto-suggestion-popover');
//         if ($this.val().length >= 3) {
//             $searchResult.fadeIn(SPEED);
//         } else {
//             $searchResult.fadeOut(SPEED);
//         }
//     }
//
//     function closeSearchResults() {
//         var $searchResult = $('.auto-suggestion-popover').fadeOut(SPEED);
//         $('.ui-autocomplete-input').val('');
//     }
//
//     function registerEvents() {
//         $('.ui-autocomplete-input').on('keyup', showSearchResults);
//         $('.search-results-close').on('click', closeSearchResults);
//     }
//
//     function init() {
//         registerEvents();
//     }
//     init();
// })();

(function () {
    'use strict';

    $(window).resize(handleToggling);
    
    function handleToggling() {
        if (window.innerWidth < 768) {

            $('.quick-links-js-header').addClass('collapsed');
            $('.quick-links-js-header').attr('data-toggle', 'collapse');
            $("ul[id^='quick-links-collapse']").removeClass('in');
            $("ul[id^='quick-links-collapse']").addClass('padding-left-20');
        } else {

            $('.quick-links-js-header').removeAttr('data-toggle');
            $("ul[id^='quick-links-collapse']").addClass('in');
            $("ul[id^='quick-links-collapse']").removeClass('padding-left-20');
        }
    }

    handleToggling();

    $(".cartlist .cartlist-header").click(function(){
        if($("#cartlistContent").is(':hidden')) {
            $("#cartlistContent").slideDown(300);
        } else {
            $("#cartlistContent").slideUp(300);
        }
        
    })
})();



(function () {
    var SPEED = 'slow';
    function showSearchResults() {
        var $this = $(this);
        var $searchResult = $('.contacts-auto-suggestion');
        if ($this.val().length >= 3) {
            $searchResult.show();
        } else {
            $searchResult.hide();
        }
    }

    function closeSearchResults() {
        var $searchResult = $('.contacts-auto-suggestion').hide();
    }

    function contactsEvents() {
        $('#contacts-search').on('keyup', showSearchResults);
        $('#contacts-search').on('blur', closeSearchResults);
    }

    function init() {
        contactsEvents();
    }
    init();
})();


// (function () {
//     $(window).resize(navToggling);
//
//     function navToggling() {
//         if (window.innerWidth < 768) {
//             $('.overlay-menu-mobile__panel__heading').click(function () {
//                 if($(this).hasClass('active')){
//                     $(this).removeClass('active');
//                     $(this).next('.panel-collapse').removeClass('in');
//                     $(this).parent('.panel').siblings('.panel').show();
//                     $('.nav-list-element').show();
//                 }else{
//                     $(this).addClass('active');
//                     $(this).parent('.panel').siblings('.panel').hide();
//                     $('.nav-list-element').hide();
//                 }
//             });
//         } else {
//         }
//     }
//
//     navToggling();
// })();
$(function(){
    //my account jump to event
    $(".js-jump-to-selection-btn").click(function(e){
        e.preventDefault();
        var value =$(".js-jump-to-selection").val()
        if (value == '/my-account') {
            window.location.href = "MyAccount.html";
        } else if (value == '/my-account/business-information') {
            window.location.href = "BusinessInformation.html";
        } else if (value == '/my-account/bonus-payment') {
            window.location.href = "Bonus-Payment-Preference.html";
        } else if(value == '/my-account/orders'){
            window.location.href = "Order-History-Personal-Orders-expanded.html";
        }else if(value == '/my-account/billing-shipping'){
            window.location.href = "AddNewPaymentMethod.html";
        }else if(value == '/my-account/bonus-payment'){
            window.location.href = "Bonus-Payment-Preference.html";
        } else if(value == '/my-account/auto-renewal'){
            window.location.href = "Auto-Renewal.html";
        }
    });

    //business center jump to event
    $(".dashboard-bar__go").click(function(e){
        e.preventDefault();
        var value =$("#dashBarFilter").val()
        if (value == '/my-business-center') {
            window.location.href = "dashboard.html";
        }else if(value == '/my-business-center/action-reports'){
            window.location.href = "action-reports.html";
        }else if(value == '/my-business-center/action-reports-overview'){
            window.location.href = "action-reports-overview.html";
        }else if(value == '/my-business-center/action-reports-birthday'){
            window.location.href = "action-reports-birthday.html";
        }else if(value == '/my-business-center/prw-landing'){
            window.location.href = "PRW-Landing-Lynx-Site.html";
        }else if(value == '/my-business-center/LOS-TreeView'){
            window.location.href = "LOS-TreeView.html";
        }else if(value == '/my-business-center/Content-Article-Pages'){
            window.location.href = "ContentArticlePages.html";
        }
    });

    //action reports jump to event
    $(".pos-vertical-center,.prw-jump-to-button").click(function(e){
        e.preventDefault();
        var value =$(".select-control").val()
        if (value == '/my-business-center') {
            window.location.href = "dashboard.html";
        }else if(value == '/my-business-center/action-reports-overview'){
            window.location.href = "action-reports-overview.html";
        }else if(value == '/my-business-center/LOS-TreeView'){
            window.location.href = "LOS-TreeView.html";
        }
    });
});
