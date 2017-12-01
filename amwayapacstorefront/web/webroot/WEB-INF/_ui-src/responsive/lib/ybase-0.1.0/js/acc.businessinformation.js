ACC.businessinformation = {

	_autoload : [
		"bindBusinessInformation",
		"updateBusinessInformation"
	],

	bindBusinessInformation : function() {
		$(".businees-action-btn").click(function(e) {
			e.preventDefault();
			if ($(".photo-edit").css('display') == 'none') {
				$(".photo-edit").show();
				$(".businees-action-btn").text("save")
				$("#mission-statement-text").hide();
				$("#mission-statement-textarea").show();
			} else {
				$(".photo-edit").hide();
				$(".businees-action-btn").text("EDIT")
				$("#mission-statement-text").show();
				$("#mission-statement-textarea").hide();

			}
		})
	},

	updateBusinessInformation : function() {
		if ($("#business-page-accordion").length) {
			var data = ACC.businessinformation.loadData();
			ACC.businessinformation.loadTemplates(data);
		}
	},

	loadTemplates : function(data) {
		$("#business-page-accordion #business-information-template").tmpl(data.business_information).appendTo("#business-page-accordion #business-information-container");
		$("#business-page-accordion #co-applicants-template").tmpl(data.co_applicants).appendTo("#business-page-accordion #co-applicants-container");
		$("#business-page-accordion #upline-template").tmpl(data.upline).appendTo("#business-page-accordion #upline-container");
	},

	loadData : function() {
		var obj = {
			business_information : {
				name : "Jennifer Industries",
				tax_id : "Tax ID on File",
				mission_statement : "Despacito Quiero respirar tu cuello despacito Deja que te diga cosas al oído Para que te acuerdes si no estás conmigo Despacito Quiero desnudarte a besos despacito Firmo en las paredes de tu laberinto Y hacer de tu cuerpo todo un manuscrito",
				photo : "no_photo.jpg"
			},
			co_applicants : [
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				},
				{
					name : "Tony Martin",
					phone : "(555) 123-4567",
					secondary_phone : "(555) 456-7890",
					email : "tony_martin_amway@longdomainname.com"
				}
			],
			upline : [
				{
					level : "SPONSOR",
					abo_id : "IBO # 559",
					name : "(555) 456-7890",
					phone : "(904) 471-0066",
					email : "JDMARSH21@AOL.COM"
				},
				{
					level : "SPONSOR",
					abo_id : "IBO # 559",
					name : "(555) 456-7890",
					phone : "(904) 471-0066",
					email : "JDMARSH21@AOL.COM"
				},
				{
					level : "DIAMOND",
					abo_id : "IBO # 559",
					name : "(555) 456-7890",
					phone : "(904) 471-0066",
					email : "JDMARSH21@AOL.COM"
				}
			]
		}
		return obj;
	}
};
