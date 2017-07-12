ACC.amwaycontentsolrsearchaddon = {

	bindAll: function ()
	{
		var breadcrumb = $('#breadcrumb');
		var totalResults = $('.totalResults');
		if (breadcrumb.html() != null && totalResults != null)
		{
			$.urlParam = function(name){
			    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
			    if (results==null){
			       return null;
			    }
			    else{
			       return results[1] || 0;
			    }
			}
			
			var text =  $.urlParam('text');
			if (text == null || text == 0)
			{
				text =  $.urlParam('q');
				if (text != 0 && text != null)
				{
					text = text.split('%3A')[0];
				}
			}
			breadcrumb.after( '<div class="clearfix"></div>' );
			breadcrumb.after( '<div class="search tab content"><a href="'+ACC.config.contextPath+'/rlcontentsearch?text='+text+'">Contents Result</div>' );
			breadcrumb.after( '<div class="search tab product"><a href="'+ACC.config.contextPath+'/search?text='+text+'">Products Result</a></div>' );
		}
	}
}

$(document).ready(function ()
{
	ACC.amwaycontentsolrsearchaddon.bindAll();
});
