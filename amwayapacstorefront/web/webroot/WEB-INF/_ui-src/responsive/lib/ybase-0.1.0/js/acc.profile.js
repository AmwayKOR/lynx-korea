ACC.profile = {

	_autoload : [
		"bindProfile",
		"updateProfileInformation"
	],

	bindProfile : function() {	
	    $("#collapsePI .accountActions button").click(function(e){
	        e.preventDefault();
	        $("#collapsePI").removeClass("editing");
	    })
	    $("#collapsePI .edit-button").click(function(e){
	        e.preventDefault();
	        $("#collapsePI").addClass("editing");
	    })
	    $("#editPw").click(function(e){
	         e.preventDefault();
	         $(this).parent().hide();
	         $(this).parent().next().show();
	    })
	    $("#editPw-sv").click(function(e){
	         e.preventDefault();
	         $(this).parents(".account-profile__editor").hide();
	         $(this).parents(".account-profile__editor").prev().show();
	    })
	},

	updateProfileInformation : function() {
		if ($("#profileEditAccordion").length) {
			var data = ACC.profile.loadData().jsonData;
			ACC.profile.loadTemplates(JSON.parse(data));
		}
	},

	loadTemplates : function (data) {
		$("#profileEditAccordion #personal-information-template").tmpl(data.personalInformation).appendTo("#profileEditAccordion #personal-information-container");
		$("#profileEditAccordion #manage-opt-ins-template").tmpl(data.manageOptIns).appendTo("#profileEditAccordion #manage-opt-ins-container");
		$("#profileEditAccordion #email-subscription-template").tmpl(data.emailSubscriptions).appendTo("#profileEditAccordion #email-subscription-container");
	},

	loadData : function() {
		var json = '{'+
					  '"personalInformation": {'+
					    '"name": "Tester QaLOS",'+
					    '"preferredName": "My preferred name",'+
					    '"phone": "(1) 202-25550153",'+
					    '"alternatePhone": "(1) 252-25846586",'+
					    '"email": "kseniia_zaitseva@epam.com",'+
					    '"dateOfBirth": "January 01",'+
					    '"preferredLanguage": "English",'+
					    '"taxIdType": "Tax ID on File",'+
					    '"taxId": "Tax ID on File",'+
					    '"profilePhoto": "no_photo.jpg"'+
					  '},'+
					  '"manageOptIns": {'+
					    '"textMessageNotification": false,'+
					    '"amwayWeeklyUpdate": true'+
					  '},'+
					  '"emailSubscriptions": {'+
					    '"amwayWeeklyUpdate": false,'+
					    '"myIBOBusinessInfo": false,'+
					    '"iboAINewsletter": true,'+
					    '"amwayUpdates": false,'+
					    '"campaignUpdates": false,'+
					    '"amwayMarketResearch": false'+
					  '}'+
				    '}';
		return {jsonData : json};
	}
};
