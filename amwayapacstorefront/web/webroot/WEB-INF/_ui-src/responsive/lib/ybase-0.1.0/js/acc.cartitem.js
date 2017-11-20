ACC.cartitem = {

	_autoload: [
		"bindCartItem"
	],

	submitTriggered: false,

	bindCartItem: function ()
	{

		$(document).on("click", '.js-execute-entry-action-button', function ()
		{
			var entryAction = $(this).data("entryAction");
			var entryActionUrl =  $(this).data("entryActionUrl");
			var entryProductCode =  $(this).data("entryProductCode");
			var entryInitialQuantity =  $(this).data("entryInitialQuantity");
			var actionEntryNumbers =  $(this).data("actionEntryNumbers");

			if(entryAction == 'REMOVE')
			{
				ACC.track.trackRemoveFromCart(entryProductCode, entryInitialQuantity);
			}

			var cartEntryActionForm = $("#cartEntryActionForm");
			var entryNumbers = actionEntryNumbers.toString().split(';');
			entryNumbers.forEach(function(entryNumber) {
				var entryNumbersInput = $("<input>").attr("type", "hidden").attr("name", "entryNumbers").val(entryNumber);
				cartEntryActionForm.append($(entryNumbersInput));
			});

            var entryNumbers = cartEntryActionForm.find('input[name=entryNumbers]').val();
			$.ajax({
                url: entryActionUrl,
                data: {"entryNumbers" : entryNumbers},
                type: cartEntryActionForm.attr('method'),
                success: function(data)
                {
                    $('#cartContent').html($(data).filter("div#cartContentDiv").html());
                    ACC.global.findAndUpdateGlobalMessages(data);
                },
                error: function(request, status, error)
                {
                    ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, request.responseText);
                }

            });
		});

		$(document).on("blur", '.js-update-entry-quantity-input', function (e)
		{
			ACC.cartitem.handleUpdateQuantity(this, e);

		}).on("keyup",'.js-update-entry-quantity-input',  function (e)
		{
			return ACC.cartitem.handleKeyEvent(this, e);
		}
		).on("keydown",'.js-update-entry-quantity-input',  function (e)
		{
			return ACC.cartitem.handleKeyEvent(this, e);
		});

        $(document).on("click", '#quickShopCartForm .js-quick-shop-submit', function (e)
        {
            ACC.cartitem.handleQuickShop(e);
        });
	},

	handleKeyEvent: function (elementRef, event)
	{
		//console.log("key event (type|value): " + event.type + "|" + event.which);

		if (event.which == 13 && !ACC.cartitem.submitTriggered)
		{
			ACC.cartitem.submitTriggered = ACC.cartitem.handleUpdateQuantity(elementRef, event);
			return false;
		}
		else
		{
			// Ignore all key events once submit was triggered
			if (ACC.cartitem.submitTriggered)
			{
				return false;
			}
		}

		return true;
	},

	handleUpdateQuantity: function (elementRef, event)
    {

        var form = $(elementRef).closest('form');

        var productCode = form.find('input[name=productCode]').val();
        var initialCartQuantity = form.find('input[name=initialQuantity]').val();
        var newCartQuantity = form.find('input[name=quantity]').val();
        var entryNumber = form.find('input[name=entryNumber]').val();

        if(initialCartQuantity != newCartQuantity)
        {
            //ACC.track.trackUpdateCart(productCode, initialCartQuantity, newCartQuantity);
            //form.submit();

            $.ajax({
                url: form.attr('action'),
                data: {"entryNumber" : entryNumber, "quantity" : newCartQuantity},
                type: form.attr('method'),
                success: function(data)
                {
                    $('#cartContent').html($(data).filter("div#cartContentDiv").html());
                    ACC.global.findAndUpdateGlobalMessages(data);
                },
                error: function(request, status, error)
                {
                    ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, request.responseText);
                }

            });
            return true;
        }

        return false;
    },

    handleQuickShop: function(event){
    	event.preventDefault();
        var quickShopForm = $('#quickShopForm');

        var productCode = quickShopForm.find('input[name=productCode]').val();
        var quantity = quickShopForm.find('input[name=quantity]').val();
        $.ajax({
            url: quickShopForm.attr('action'),
            data: {"productCode" : productCode, "quantity" : quantity},
            type: quickShopForm.attr('method'),
            success: function(data)
            {
                $('#cartContent').html($(data).filter("div#cartContentDiv").html());
                ACC.global.findAndUpdateGlobalMessages(data);
            },
            error: function(request, status, error)
            {
                ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, request.responseText);
            }
        });
    }
};

