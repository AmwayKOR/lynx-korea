ACC.productreview = {

	_autoload: [
		"loadVM",
	],

	loadVM: function(){
		
		var productReview = new Vue({
		    el: '#product-review',
		    data: {
		    	headline: '',
		    	comment: '',
		    	rating: 0,
		    	alias: '',
		        response: null,
		        headlineError: false,
		        commentError: false,
		        ratingError: false,
		        headlineMessage: '',
		        commentMessage: '',
		        ratingMessage: ''
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
	    					self.headlineError = false;
	    					self.commentError = false;
	    					self.ratingError = false;
	    					
		    				if(data.success){
		    					ACC.productreview.openPrompt();
		    				}
		    				else
		    				{
		    					data.message.forEach(function(value, index) {
		    						if(value.field == 'headline'){
		    							self.headlineError = true;
		    							self.headlineMessage = value.message;
		    						}else if(value.field == 'comment'){
		    							self.commentError = true;
		    							self.commentMessage = value.message;
		    						}else if(value.field == 'rating'){
		    							self.ratingError = true;
		    							self.ratingMessage = value.message;
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
		    	  productReview.rating = score;
			  }
		});
		
        $(".cart-popup__close, .closeCbox").click(function() {
            $(".cbox,.overlay").hide();
        });
        
        $(".product-collapse__review").click(function() {
        	
        	productReview.headlineError = false;
        	productReview.commentError = false;
        	productReview.ratingError = false;
        	productReview.headline = '';
        	productReview.comment = '';
        	$('#star').raty('set', { score: 0 });
        	
        });

	    $('.product-description__readmore').click(function(event) {
	        event.preventDefault();
	        $(".review, .overlay").show();
	    });
		
	},

	openPrompt: function(){
	    $(".review").hide();
	    $(".prompt").slideDown();
	}
};