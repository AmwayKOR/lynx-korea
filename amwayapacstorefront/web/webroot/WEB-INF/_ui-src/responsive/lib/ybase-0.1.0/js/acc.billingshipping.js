ACC.billingshipping = {

	_autoload: [
        "bindBillingShipping"
	],
	
	submitFormForEditAddress: function (e)
	{
		var addressEditHtml = $(e).parents(".mailing-address-edit");
		var addressHtml = addressEditHtml.prevAll(".mailing-address");
		var addressErrorHtml = addressEditHtml.prevAll(".error-mailing-address");
		var addressId = $(e).attr('data-address-id');
		var isPrimaryAddress = $(e).attr('data-address-default');
		var setDefault = $('#shipping-address-form-'+addressId+'').find('input[id="address.defaultAddress-'+addressId+'"]').is(':checked');
		var shippingAddressBodyHtml = $(e).parents(".primary-alternate-body");
        var options = {
    			url: $('#shipping-address-form-'+addressId+'').attr( "action"),
    			data: $('form[id=shipping-address-form-'+addressId+']').serialize() +"&primaryAddress="+isPrimaryAddress,
    			type: 'POST',
    			success: function (data)
    			{
    				var result = $(data);
    				if(result.filter("div.alert").length > 0){
    					ACC.global.findAndUpdateGlobalMessages(result);
    					addressErrorHtml.html(result)
    				}else{
    					addressErrorHtml.empty();
    					if(setDefault){
        					shippingAddressBodyHtml.html(result);
        				}else{
        					addressEditHtml.hide();
            				addressHtml.html(result);
            				addressHtml.show();
        				}
    				}
    				
    			}
    		};

    		$.ajax(options);
	},
	submitFormForAddAddress: function (e)
	{
		var shippingAddressBodyHtml = $(e).parents(".add-alternate-address").prev(".primary-alternate-body");
		var addressErrorHtml = $(e).parents(".new-alternate-address").find(".error-mailing-address")
		var alternateAddressForm = $('#shipping-address-form-new');
		var addNewAlternate = $(".add-new-alternate-address");
		var newAlternateAddress = $(".new-alternate-address");
        var options = {
    			url: $('form[id=shipping-address-form-new]').attr( "action"),
    			data: $('form[id=shipping-address-form-new]').serialize(),
    			type: 'POST',
    			success: function (data)
    			{
    				var result = $(data);
    				if(result.filter("div.alert").length > 0){
    					ACC.global.findAndUpdateGlobalMessages(result);
    					addressErrorHtml.html(result)
    				}else{
    					addressErrorHtml.empty();
    					alternateAddressForm.trigger("reset");
        				addNewAlternate.show();
        				newAlternateAddress.hide();
        				shippingAddressBodyHtml.html(result);
    				}
    				
    			}
    		};

    		$.ajax(options);
	},
	populateFormUponEditAddress: function (e)
	{
		$(e).parents(".mailing-address").hide();
        
        var addressEditHtml = $(e).parents(".mailing-address").nextAll(".mailing-address-edit");
        //$(this).parents('.addressEdit').html('<p>asdasdasd</p>');
        var addressId = $(e).attr('data-address-id');
        var countryISOCode = $(e).attr('data-address-countryISOCode');
        var options = {
    			url: '/amwayapacstorefront/my-account/addressform',
    			data: {addressCode: addressId, countryIsoCode: countryISOCode},
    			type: 'GET',
    			success: function (data)
    			{
    				//$('.addressEdit').html(data);
    				addressEditHtml.html(data);
    			}
    		};

    		$.ajax(options);
    		
    		$(e).parents(".mailing-address").nextAll(".mailing-address-edit").show();
	},

	bindBillingShipping: function () {
		
		$(".edit").on('click', function() {
       	 
			var father = $(this).parents('.account-paymentdetails');
	        if(father.next("#editPay").is(":visible")) {
	            father.slideDown();
	            father.next("#editPay").slideUp();
	        } else {
	            father.next("#editPay").slideDown();
	            father.slideUp();
	        }
	        
	    });
        
        $(".btn-edit").click(function () {
        		ACC.billingshipping.populateFormUponEditAddress($(this));
        });

        
        $("#editFormSubmit, #editCancel").click(function() {
            var father = $(this).parents('#editPay');
            if(father.prev(".account-paymentdetails").is(":visible")) {
                father.slideDown();
                father.prev(".account-paymentdetails").slideUp();
            } else {
                father.prev(".account-paymentdetails").slideDown();
                father.slideUp();
            }
        })
        
        $(".new-alternate-address").hide();
        $(".add-alternate").click(function () {
        		$(".new-alternate-address").show();
            $(".add-new-alternate-address").hide();
            $(".new-alternate-address").next(".error-mailing-address").empty();

        }); 
	}
};