ACC.messagecenter = {
    
    _autoload: [
        
        "bindLoadResults",
        "bindShowMoreResults",
        "bindShowMessage"
    ],

    currentPath: window.location.pathname,
    currentPage: 0,
    numberOfPages: $('.js-message-center-number-of-pages').val() || Number.MAX_VALUE,
    
    bindLoadResults: function () {
    		if($("#message-centers").length){
    			ACC.messagecenter.messageCenterVM = new Vue({
			    el: '#message-centers',
			    data:{
			    		hiddenTotalNumberPage:0,
			    		pagination : {
			    			pageSize : "",
			    			currentPage : "",
			    			sort : "",
			    			numberOfPages : "",
			    			totalNumberOfResults : ""
				    	},
				    	results : [{
				    		shortDescription : "",
				    		longDescription : "",
				    		publishDate : "",
				    		cmsComponentId : "",
				    		status : "",
				    		code : ""
				    	}]
				    
			    },
			    methods: {
			    		dataMessageNumber: function(index){
			    			return this.pagination.currentPage * this.pagination.pageSize + index;
			    		},
			    		messageCenterPublishDate: function(date){
			    			return moment(date,'DD/MM/YYYY',true).format('MM/DD/YYYY, h:mm a');
			    		},
			    		viewMore: function(currentPageSize){
			    			
			    			var individual = this;
			    			if (currentPageSize < individual.pagination.numberOfPages) {
			    				
			    				var resultList = $('.js-message-center-search-result');
			    		        var messageType = $("#messageType").val();
			    		        var params = '?page=' + currentPageSize + 1;
			    				
			    		        if (resultList.length > 0) {
			    		            $.ajax({
			    		                url: window.location.pathname+"/messagelist-data" + params,
			    		                success: function (data) {
			    		                    //individual.results.push(data.results);
			    		                		for (var i = 0; i < data.results.length; i++) {
			    		                			individual.results.push( data.results[i]
			    		                        )
			    		                    }
			    		                		individual.pagination = data.pagination;
			    		                		individual.hiddenTotalNumberPage = data.pagination.numberOfPages + (data.pagination.numberOfPages % 1 == 0 ? 0 : 0.5);
			    		                    
			    		                    
			    		                    //update Pagination Infos();
			    		                		individual.pagination.currentPage++;
			    		                    if (individual.pagination.currentPage >= individual.pagination.numberOfPages - 1) {
			    		                        $('.js-message-center-show-more').parent().addClass("display-none");
			    		                    }
			    		                }
			    		            });
			    		        }
			    				
			    	        }
			    			if (currentPageSize < this.pagination.numberOfPages - 1) {
			    	            $('.js-message-center-show-more').parent().removeClass("display-none");
			    	        }
			    		}
			    },
			    created: function(e){
			        
			        var individual = this;
			        
			        $.ajax({
			    			url : window.location.pathname+"/messagelist-data",
			    			data : "",
			    			cache : false,
			    			type : 'GET',
			    			success : function(data) 
			    			{
		    					individual.results = data.results;
		    					individual.pagination = data.pagination;
		    					individual.hiddenTotalNumberPage = data.pagination.numberOfPages + (data.pagination.numberOfPages % 1 == 0 ? 0 : 0.5);
		    					
			    			},
			    			error : function (request, status, error){
			    			}
			    		});
			        
			    }
    			});
    		}
		
	},
    
    bindShowMoreResults: function () {
        $('.js-message-center-show-more').on('click', function () {
            ACC.messagecenter.triggerLoadMoreResults();
        });
        if (ACC.messagecenter.currentPage < ACC.messagecenter.numberOfPages - 1) {
            $('.js-message-center-show-more').parent().removeClass("display-none");
        }
    },

    navigate(button) {
        var totalMessages = $(".js-account-message-center-total-messages").data("total-messages");
        var pageNumber = button.data("message-page-number");
        var previousButtonSelector = ".js-account-message-previous";
        var nextButtonSelector = ".js-account-message-next";

        if(pageNumber === 0) {
           ACC.messagecenter.disableButton(previousButtonSelector);
        } else {
            ACC.messagecenter.enableButton(previousButtonSelector);
        }
        if (totalMessages - pageNumber <= 1) {
            ACC.messagecenter.disableButton(nextButtonSelector);
        } else {
           ACC.messagecenter.enableButton(nextButtonSelector);
        }
    },

    disableButton(buttonSelector) {
       $(buttonSelector).attr('disabled', 'disabled');
    },

    enableButton(buttonSelector) {
       $(buttonSelector).removeAttr('disabled');
    },

    
};
