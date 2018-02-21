ACC.businessInformation = {

	_autoload : [
		"loadModels",
		"bindEvents"
	],

	bindEvents : function(){
		$(".businees-action-btn").click(function(e) {
			e.preventDefault();
			if ($(".photo-edit").css('display') == 'none') {
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
		});
		
		$(".applicant-row").slice(0,4).css("display", "block");
		$(".btn-show-more-business-information").on('click', function(e){
			e.preventDefault();
			$(".applicant-row:hidden").slice(0,2).slideDown();
		});
		
		$(".btn-photo-edit").on('click', function(e){
			e.preventDefault();
			e.stopPropagation();
			$("#input-photo-edit").trigger("click");
		});
		
	},
		
	loadModels : function() {
		var element = $("#business-page-accordion");
		if(element.length){
			ACC.businessInformation.businessInformationVM.getVM();
		}
	},

	businessInformationVM : (function(){
		var vm;
		return {
			getVM: function(){
	            if (!vm) 
	            {
	                vm = new Vue({
	            	    el: '#business-page-accordion',
	            	    data: ACC.businessInformation.data(),
	            	    methods: {
	            	    	upload: function(e) {
								ACC.businessInformation.upload(e);
	            	        },
	            	        save: function(e) {
	            	        	var mode = e.target.textContent || e.target.innerText;
	            	        	if(mode.toUpperCase() === 'SAVE'){
	            	        		ACC.businessInformation.submit(e, this);
	            	        	}
	            	        }
	            	    },
	            	    created: function () {
	            	    	Object.assign(this.$data, JSON.parse(ACC.businessInformation.loadData().jsonData));
	            	    },
	            	    computed: {

	            	    }
	            		});
	            }
				return vm;
			}
		};
	}()),
	
	data : function(obj) {
		var data = 
		{
	    	businessInformation : 
	    	{
		    	name : '',
		    	taxId : '',
		    	missionStatement : '',
		    	photo : ''
	    	},
	    	coApplicants : 
	    	[{
		    	name : '',
		    	phone : '',
		    	secondaryPhone : '',
		    	email : ''
	    	}],
	    	uplines : 
	    	[{
		    	name : '',
		    	phone : '',
		    	email : '',
		    	aboId : '',
		    	level : ''
	    	}]
	    };
		return data;
	},
	
    submit: function(e, data) {
    	e.preventDefault();
    	var url = e.target.href;
    	$.get(url, function(data){
    		return data;
    	});
    },
    
    upload: function(e) {
		var files = e.target.files || e.dataTransfer.files;
		var vm = ACC.businessInformation.businessInformationVM.getVM();
		if (!files.length){
			return;
		}
	    var reader = new FileReader();
	    reader.onload = (e) => {
	    	vm.businessInformation.photo = e.target.result;
	    	vm.$forceUpdate();
	    };
	    reader.readAsDataURL(files[0]);
    },
	
	//mock data
	loadData : function() {
		var json = '{'+
			'"businessInformation" : {'+
				'"name" : "Jennifer Industries",'+
				'"taxId" : "Tax ID on File",'+
				'"photo" : "no_photo.jpg",'+
				'"missionStatement" : "Despacito Quiero respirar tu cuello despacito Deja que te diga cosas al oído Para que te acuerdes si no estás conmigo Despacito Quiero desnudarte a besos despacito Firmo en las paredes de tu laberinto Y hacer de tu cuerpo todo un manuscrito"'+
			'},'+
			'"coApplicants" : ['+
				'{'+
					'"name" : "Tony Martin1",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin2",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin3",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin4",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'},'+
				'{'+
					'"name" : "Tony Martin",'+
					'"phone" : "(555) 123-4567",'+
					'"secondaryPhone" : "(555) 456-7890",'+
					'"email" : "tony_martin_amway@longdomainname.com"'+
				'}'+
			'],'+
			'"uplines" : ['+
				'{'+
				'"level" : "SPONSOR",'+
				'"aboId" : "IBO # 559",'+
				'"name" : "(555) 456-7890",'+
				'"phone" : "(904) 471-0066",'+
				'"email" : "JDMARSH21@AOL.COM"'+
				'},'+
				'{'+
					'"level" : "SPONSOR",'+
					'"aboId" : "IBO # 559",'+
					'"name" : "(555) 456-7890",'+
					'"phone" : "(904) 471-0066",'+
					'"email" : "JDMARSH21@AOL.COM"'+
				'},'+
				'{'+
					'"level" : "DIAMOND",'+
					'"aboId" : "IBO # 559",'+
					'"name" : "(555) 456-7890",'+
					'"phone" : "(904) 471-0066",'+
					'"email" : "JDMARSH21@AOL.COM"'+
				'}'+
			']'+
		'}';
		return {jsonData : json};
	}
};
