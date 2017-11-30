ACC.businessinformation = {

	_autoload: [
        "bindBusinessInformation",
        "updateBusinessInformation"
	],
	
	bindBusinessInformation: function () {
		$(".businees-action-btn").click(function (e) {
	        e.preventDefault();
	        if($(".photo-edit").css('display')=='none'){
	            $(".photo-edit").show();
	            $(".businees-action-btn").text("save")
	            $("#mission-statement-text").hide();
	            $("#mission-statement-textarea").show();
	        }else{
	            $(".photo-edit").hide();
	            $(".businees-action-btn").text("EDIT")
	            $("#mission-statement-text").show();
	            $("#mission-statement-textarea").hide();
	
	        }
	    })
	},

    updateBusinessInformation: function(){
		var data = ACC.businessinformation.loadData();
		ACC.businessinformation.loadTemplates(data);
    },
    
    loadTemplates: function(data){
		$("#business-information-template").tmpl(data.business_information).appendTo("#business-information-container");
		$("#co-applicants-template").tmpl(data.co_applicants).appendTo("#co-applicants-container");
		$("#upline-template").tmpl(data.upline).appendTo("#upline-container");
    },
    
    loadData: function(){
    	var obj = 
    	{
			  business_information: {
				    name: "Jennifer Industries",
				    tax_id: "Tax ID on File",
				    mission_statement: "Despacito Quiero respirar tu cuello despacito Deja que te diga cosas al oído Para que te acuerdes si no estás conmigo Despacito Quiero desnudarte a besos despacito Firmo en las paredes de tu laberinto Y hacer de tu cuerpo todo un manuscrito",
				    photo: "no_photo.jpg"
			  },
			  co_applicants: [
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    },
			    {
				      name: "Tony Martin",
				      phone: "(555) 123-4567",
				      secondary_phone: "(555) 456-7890",
				      email: "tony_martin_amway@longdomainname.com"
			    }
			  ],
			  upline: [
			    {
				      level: "SPONSOR",
				      abo_id: "IBO # 559",
				      name: "(555) 456-7890",
				      phone: "(904) 471-0066",
				      email: "JDMARSH21@AOL.COM"
			    },
			    {
				      level: "SPONSOR",
				      abo_id: "IBO # 559",
				      name: "(555) 456-7890",
				      phone: "(904) 471-0066",
				      email: "JDMARSH21@AOL.COM"
			    },
			    {
				      level: "DIAMOND",
				      abo_id: "IBO # 559",
				      name: "(555) 456-7890",
				      phone: "(904) 471-0066",
				      email: "JDMARSH21@AOL.COM"
			    }
			  ]
			}
    	return obj;
    }
};
