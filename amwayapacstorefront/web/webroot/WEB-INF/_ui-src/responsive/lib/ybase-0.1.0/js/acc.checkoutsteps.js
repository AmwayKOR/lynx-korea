ACC.checkoutsteps = {

	_autoload: [
		"permeateLinks",
		"bindEvent",
		"loadAccordion"
	],
			
	permeateLinks: function() {
	
		$(document).on("click",".js-checkout-step",function(e){
			e.preventDefault();
			window.location=$(this).closest("a").attr("href")
		})		
	},
	
	bindEvent: function(){
        $(document).on("click", ".address-form-edit-label", ACC.checkoutsteps.toggleUpdate);
		$(document).on("click", ".shipping-delivery-save", ACC.checkoutsteps.updateAddress);
		$(document).on("click", ".shipping-delivery-use", ACC.checkoutsteps.updateAddress);
		$(document).on("click", ".shipping-delivery-remove", ACC.checkoutsteps.updateAddress);
	},
	
	// bugfix for bootstrap accordion
	// link: https://stackoverflow.com/questions/17750907/bootstrap-collapse-doesnt-toggle-after-you-show-hide-or-toggle-from-code
	loadAccordion: function(){
		$("#collapseOne").collapse({"toggle": false, 'parent': '#shippingdiv'});
		$("#collapseTwo").collapse({"toggle": false, 'parent': '#shippingdiv'});
		$("#collapseThree").collapse({"toggle": false, 'parent': '#shippingdiv'});
		$("#collapseFour").collapse({"toggle": false, 'parent': '#shippingdiv'});
	},
	
	updateAddress: function(e) {
		e.preventDefault();
		var target = $(e.target);
		var form = target.closest("form");
		var url = form.attr("action");
		var type = form.attr("method");		
		$.ajax({
			url : url,
			data : form.serialize()+"&ajax=true",
			cache : false,
			type : type,
			success : (data) => {
				if(data){
					var result = $(data);
					if(result.filter("#response").val())
					{
						var primaryAddress = result.filter("div.content-wrapper").find("div.shipping-delivery-primary").find("div.shipping-delivery-edit-container").html();
						$("div.shipping-delivery-main-container").html(primaryAddress);
						$("div.shipping-delivery-list-container").html(result.filter("div.content-wrapper").html());
						
						var defaultAddress = target.closest("div").find("[type=checkbox]").is(":checked");
						
						if(!target.hasClass("update") && !target.hasClass("delete") || defaultAddress)
						{
							ACC.checkoutsteps.toggleSave();
							ACC.checkoutsteps.resetForm(e);
						}
					}
					else
					{
						if(result.find("div.alert").length > 0){
							//error response:
							ACC.global.findAndUpdateGlobalMessages(result.filter("div.messages-wrapper").html());
						}
					}
				}
			},
			error : (request, status, error)=>{
				ACC.global.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, request.responseText);
				
			}
		});
	},
	
	toggleUpdate: function(e){
		$(e.target).parent("div").next("div").toggleClass("hidden");
		$(e.target).parent("div").toggleClass("hidden");
	},
	
	toggleSave: function(){
		$("#collapseOne").collapse("show");
	},
	
	resetForm: function(e){
		$(e.target).closest("form")[0].reset();
	}
}