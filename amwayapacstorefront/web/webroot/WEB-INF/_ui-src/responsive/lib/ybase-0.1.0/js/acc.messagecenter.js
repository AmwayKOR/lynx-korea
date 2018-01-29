ACC.messagecenter = {
    
    _autoload: [
        "bindShowMoreResults",
        "bindShowMessage"
    ],

    currentPath: window.location.pathname,
    currentPage: 0,
    numberOfPages: $('.js-message-center-number-of-pages').val() || Number.MAX_VALUE,

    bindShowMoreResults: function () {
        $('.js-message-center-show-more').on('click', function () {
            ACC.messagecenter.triggerLoadMoreResults();
        });
        if (ACC.messagecenter.currentPage < ACC.messagecenter.numberOfPages - 1) {
            $('.js-message-center-show-more').parent().removeClass("display-none");
        }
    },

    bindShowMessage: function() {
       $(".js-account-show-detailed-message").on("click", function(e) {
            e.preventDefault();

            var messageTitle = $(this).data("message-title");
            var button = $(this);

//            ACC.colorbox.open('', {
//                inline: true,
//                height: "100%",
//                width: "100%",
//                className: "account-detailed-message",
//                href: "#accountMessageBlockId",
//                onComplete: function() {
//                    ACC.messagecenter.updateMessageView(button, $(this).colorbox);
//                }
//            });
        });
    },


    navigate(button) {
        var totalMessages = $(".js-account-message-center-total-messages").data("total-messages");
        var pageNumber = button.data("message-page-number");
        var previousButtonSelector = ".js-account-message-previous";
        var nextButtonSelector = ".js-account-message-next";

        if(pageNumber === 0) {
           ACC.messagecenter.disableButton(previousButtonSelector);
        } else {
            ACC.messagecenter.enableButton(previousButtonSelector);
        }
        if (totalMessages - pageNumber <= 1) {
            ACC.messagecenter.disableButton(nextButtonSelector);
        } else {
           ACC.messagecenter.enableButton(nextButtonSelector);
        }
    },

    disableButton(buttonSelector) {
       $(buttonSelector).attr('disabled', 'disabled');
    },

    enableButton(buttonSelector) {
       $(buttonSelector).removeAttr('disabled');
    },

    triggerLoadMoreResults: function () {
        if (ACC.messagecenter.currentPage < ACC.messagecenter.numberOfPages) {
            ACC.messagecenter.loadMoreResults(ACC.messagecenter.currentPage + 1);
        }
    },

    loadMoreResults: function (page) {
        var resultList = $('.js-message-center-search-result');
        var messageType = $("#messageType").val();
        var params = '?page=' + page;

        if(messageType) {
            params = params + "&type=" + messageType;
        }

        if (resultList.length > 0) {
            $.ajax({
                url: ACC.messagecenter.currentPath + params,
                success: function (data) {
                    resultList.append(data);
                    ACC.messagecenter.updatePaginationInfos();
                    ACC.messagecenter.bindShowMessage();
                }
            });
        }
    },

    updatePaginationInfos: function () {
        ACC.messagecenter.currentPage++;
        if (ACC.messagecenter.currentPage >= ACC.messagecenter.numberOfPages - 1) {
            $('.js-message-center-show-more').parent().addClass("display-none");
        }
    }
};
