ACC.billingshipping = {

	_autoload: [
        "bindBillingShipping"
	],
	
	submitFormForEditAddress: function (e)
	{
		var addressEditHtml = $(e).parents(".mailing-address-edit");
		var addressHtml = addressEditHtml.prev(".mailing-address");
		var addressId = $(e).attr('data-address-id');
		var setDefault = $('#shipping-address-form-'+addressId+'').find('input[id="address.defaultAddress-'+addressId+'"]').is(':checked');
		var shippingAddressBodyHtml = $(e).parents(".primary-alternate-body");
        var options = {
    			url: $('#shipping-address-form-'+addressId+'').attr( "action"),
    			data: $('form[id=shipping-address-form-'+addressId+']').serialize(),
    			type: 'POST',
    			success: function (data)
    			{
    				if(setDefault){
    					shippingAddressBodyHtml.html(data);
    				}else{
    					addressEditHtml.hide();
        				addressHtml.html(data);
        				addressHtml.show();
    				}
    				
    			}
    		};

    		$.ajax(options);
	},
	submitFormForAddAddress: function (e)
	{
		var shippingAddressBodyHtml = $(e).parents(".add-alternate-address").prev(".primary-alternate-body");
		var alternateAddressForm = $('#shipping-address-form-new');
		var addNewAlternate = $(".add-new-alternate-address");
		var newAlternateAddress = $(".new-alternate-address");
        var options = {
    			url: $('form[id=shipping-address-form-new]').attr( "action"),
    			data: $('form[id=shipping-address-form-new]').serialize(),
    			type: 'POST',
    			success: function (data)
    			{
    				alternateAddressForm.trigger("reset");
    				addNewAlternate.show();
    				newAlternateAddress.hide();
    				shippingAddressBodyHtml.html(data);
    			}
    		};

    		$.ajax(options);
	},
	populateFormUponEditAddress: function (e)
	{
		$(e).parents(".mailing-address").hide();
        
        var addressEditHtml = $(e).parents(".mailing-address").next(".mailing-address-edit");
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
    		
        $(e).parents(".mailing-address").next(".mailing-address-edit").show();
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

        $(".btn-save").click(function () {
            $(this).parents(".mailing-address-edit").hide();
            $(this).parents(".mailing-address-edit").prev(".mailing-address").show();

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
            $(".add-new-alternate-address").hide();
            $(".new-alternate-address").show();

        }); 
	}

};
