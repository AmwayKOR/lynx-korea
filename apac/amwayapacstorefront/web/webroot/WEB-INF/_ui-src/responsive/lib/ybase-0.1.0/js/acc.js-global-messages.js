(function($){
	$.extend($.fn, {
		addGlobalMessages : function(globalMessages) {
			var allMessages = $.extend([], globalMessages);
			return this.each(function(){
				if(allMessages.length) {
					var wrapperDiv = $(this);
					wrapperDiv.html("");
					$.each(allMessages, function(index, item) {
						if ((item.message != null) && (item.message.trim().length > 0)) {
							if (item.globalMessageType == ACC.globalMessageTypes.CONF_MESSAGES_HOLDER) {
								wrapperDiv.append($("script#global-alert-info-template").tmpl({message : item.message}));
							} else if (item.globalMessageType == ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER) {
								wrapperDiv.append($("script#global-alert-danger-template").tmpl({message : item.message}));
							} else if (item.globalMessageType == ACC.globalMessageTypes.WARNING_MESSAGES_HOLDER) {
								wrapperDiv.append($("script#global-alert-warning-template").tmpl({message : item.message}));
							}
							setTimeout(function(){ wrapperDiv.html(""); }, 5000);
						}
					});
				}
			});
		},
		appendGlobalMessages : function(globalMessages) {
			var allMessages = $.extend([], globalMessages);
			return this.each(function(){
				if(allMessages.length) {
					var wrapperDiv = $(this);
					$.each(allMessages, function(index, item) {
						if ((item.message != null) && (item.message.trim().length > 0)) {
							if (item.globalMessageType == ACC.globalMessageTypes.CONF_MESSAGES_HOLDER) {
								wrapperDiv.append($("script#global-alert-info-template").tmpl({message : item.message}));
							} else if (item.globalMessageType == ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER) {
								wrapperDiv.append($("script#global-alert-danger-template").tmpl({message : item.message}));
							} else if (item.globalMessageType == ACC.globalMessageTypes.WARNING_MESSAGES_HOLDER) {
								wrapperDiv.append($("script#global-alert-warning-template").tmpl({message : item.message}));
							}
							setTimeout(function(){ wrapperDiv.html(""); }, 5000);
						}
					});
				}
			});
		}
	});
})(jQuery);