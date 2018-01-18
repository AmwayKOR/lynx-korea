ACC.productReview = {

	_autoload: [
		"loadVM"
	],

	loadVM: function(){
		
		var productReviewVM = new Vue({
		    el: '#product-review',
		    data:{
		    	headline:"",
		    	comment:"",
		    	rating:0,
		    	alias:"",
		    	message : {
		    		headline : { error: false, message: ""},
		    		comment : { error: false, message: ""},
		    		rating : { error: false, message: ""}
		    	}
		    },
		    methods: {
		        submit: function(e) {
		        	e.preventDefault();
		        	var self = this;
		        	var url = e.target.href;
		            var payload =
		            {
		            	headline: this.headline, 
		            	comment: this.comment, 
		            	rating: this.rating, 
		            	alias: this.$refs.alias.value
			        };
		            $.ajax({
		    			url : url,
		    			data : payload,
		    			cache : false,
		    			type : 'GET',
		    			success : function(data) 
		    			{
	    					self.message.headline.error = false;
	    					self.message.comment.error = false;
	    					self.message.rating.error = false;
	    					
		    				if(data.success){
		    					ACC.productReview.openPrompt();
		    				}
		    				else
		    				{
		    					data.message.forEach(function(value, index) {
		    						if(value.field == "headline"){
		    							self.message.headline.error = true;
		    							self.message.headline.message = value.message;
		    						}else if(value.field == "comment"){
		    							self.message.comment.error = true;
		    							self.message.comment.message = value.message;
		    						}else if(value.field == "rating"){
		    							self.message.rating.error = true;
		    							self.message.rating.message = value.message;
		    						}
		    			        });
		    				}
		    			},
		    			error : function (request, status, error){
		    			}
		    		});
		        }
		    }
		});
		
		$('#star').raty({ 
			  path: ACC.config.themeResourcePath+'/images',
		      size      : 5,
		      starOff   : 'star-empty.png',
		      starOn    : 'star-filled.png',
		      scoreName : 'rating',
		      click: function (score, e) {
		    	  productReviewVM.rating = score;
			  }
		});
		
        $(".cart-popup__close, .closeCbox").click(function() {
            $(".cbox,.overlay").hide();
        });
        
        $(".product-collapse__review").click(function() {
			productReviewVM.message.headline.error = false;
			productReviewVM.message.comment.error = false;
			productReviewVM.message.rating.error = false;
        	productReviewVM.headline = '';
        	productReviewVM.comment = '';
        	productReviewVM.rating = 0;
        	$("#star").raty("score", 0);
        	
        });

	    $('.product-description__readmore').click(function(event) {
	        event.preventDefault();
	        $(".review, .overlay").show();
	    });
	    
	    $('.js-tabs #tabreview').click(function(event) {
	        event.preventDefault();
	        ACC.productReview.populateGraph();
	    });

	},

	openPrompt: function(){
	    $(".review").hide();
	    $(".prompt").slideDown();
	},
	
	maxValue: function(arra){
        var max = 0;
        for (var i in arra){
            if ( arra[i] > max ) {
                max = arra[i];
            }
        }
        return max;
    },
    
    yellowGraph: function(arra) {
        var max = ACC.productReview.maxValue(arra);
        $(".comuserinfo dl").each(function(index, element){
            var percent = arra[(5 - index) + 'star']/max*100;
            $(element).find("div").css('width', percent + "%" );
            $(element).find("span").html(arra[(5 - index) + 'star']);
        });
    },
    
    populateGraph: function() {
        $.ajax({
			url : $("#reviewgraph").data("url"),
			cache : true,
			success : function(response) 
			{
				if(response.success){
					var reviewArray = response.data;
				    ACC.productReview.yellowGraph(reviewArray);
				}
			},
			error : function (request, status, error){
			}
		});
    }
};