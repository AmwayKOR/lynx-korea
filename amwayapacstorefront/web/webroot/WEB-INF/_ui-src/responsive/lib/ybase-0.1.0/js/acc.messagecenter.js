ACC.messagecenter = {
    _autoload: [
        
        "bindLoadResults",
        "bindShowMoreResults"
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
			    		hiddenMessageTypeFilter : "",
			    		isShow : true,
			    		isShowPopup : false,
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
			    		getDetailedDescription: function(code){
			    			//$(".message-center-pop1").addClass("display-block");
			    			this.isShowPopup = true;
			    			$(".message-center-pop1,.overlay").show();
			    			var contentBox = $(".message-center-pop-content");
			    			contentBox.empty();
			    			
			    			$.ajax({
				    			url : window.location.pathname+"/dummyDetail",
				    			data : "",
				    			cache : false,
				    			type : 'GET',
				    			success : function(data) 
				    			{
				    				contentBox.html(data);
				    			},
				    			error : function (request, status, error){
				    			}
				    		});
			    			
			    		},
			    		viewMoreChecking: function(individual){
			    			if (individual.pagination.currentPage < individual.pagination.numberOfPages - 1) {
			    				individual.isShow = true;
			    	        }
			    		},
			    		filterByMessageType: function(messageType){
			    			var individual = this;
					    $.ajax({
				    			url : window.location.pathname+"/messagelist-data?messageType="+messageType,
				    			data : "",
				    			cache : false,
				    			type : 'GET',
				    			success : function(data) 
				    			{
			    					individual.results = data.results;
			    					individual.pagination = data.pagination;
			    					individual.hiddenTotalNumberPage = data.pagination.numberOfPages + (data.pagination.numberOfPages % 1 == 0 ? 0 : 0.5);
			    					individual.hiddenMessageTypeFilter = messageType;
			    					individual.viewMoreChecking(individual);
				    			},
				    			error : function (request, status, error){
				    			}
				    		});
					    
			    		},
			    		viewMore: function(currentPageSize){
			    			
			    			var individual = this;
			    			if (individual.pagination.currentPage < individual.pagination.numberOfPages) {
			    				
			    				var resultList = $('.js-message-center-search-result');
			    		        var messageType = $("#messageType").val();
			    		        var pageNum = individual.pagination.currentPage + 1;
			    		        var params = '?page=' + pageNum;
			    		        if(individual.hiddenMessageTypeFilter != ""){
			    		        		params+="&messageType=" +individual.hiddenMessageTypeFilter;
			    		        }
			    				
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
			    		                    
			    		                    if (individual.pagination.currentPage >= individual.pagination.numberOfPages-1) {
			    		                    		individual.isShow = false;
			    		                    }
			    		                }
			    		            });
			    		        }
			    				
			    	        }
			    			individual.viewMoreChecking(individual);
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
		    					individual.viewMoreChecking(individual);
			    			},
			    			error : function (request, status, error){
			    			}
			    		});
			        
			    }
    			});
    		}
    		
    		$(".message-center-pop1 #cboxClose").click(function() {
                $(".message-center-pop1,.overlay").hide();
        });
		
	},
	
    bindShowMoreResults: function () {
        $('.js-message-center-show-more').on('click', function () {
            ACC.messagecenter.triggerLoadMoreResults();
        });
        if (ACC.messagecenter.currentPage < ACC.messagecenter.numberOfPages - 1) {
            $('.js-message-center-show-more').parent().removeClass("display-none");
        }
    },

    navigate: function(button) {
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

    disableButton: function(buttonSelector) {
       $(buttonSelector).attr('disabled', 'disabled');
    },

    enableButton: function(buttonSelector) {
       $(buttonSelector).removeAttr('disabled');
    }

    
};
