ACC.autocomplete = {

	_autoload: [
		"bindHeaderSearchAutocomplete",
        "bindDisableSearch",
        "bindShoppingListQuickShopAutoComplete",
        "bindShoppingCartQuickShopAutoComplete",
	],

	bindHeaderSearchAutocomplete: function ()
	{
		// extend the default autocomplete widget, to solve issue on multiple instances of the searchbox component
		$.widget( "custom.yautocomplete", $.ui.autocomplete, {
			_create:function(){
				
				// get instance specific options form the html data attr
				var option = this.element.data("options");
				// set the options to the widget
				this._setOptions({
					minLength: option.minCharactersBeforeRequest,
					displayProductImages: option.displayProductImages,
					delay: option.waitTimeBeforeRequest,
					autocompleteUrl: option.autocompleteUrl,
					source: this.source
				});
				
				// call the _super()
				$.ui.autocomplete.prototype._create.call(this);
				
			},
			options:{
				cache:{}, // init cache per instance
				focus: function (){return false;}, // prevent textfield value replacement on item focus
				select: function (event, ui){
                    window.location.href = ui.item.url;
                }
			},
			_renderMenu: function( ul, items ) {
				  var searchUrl = ACC.config.encodedContextPath + "/search?text=" + items[0].searchTerm;
				  var ulUpdated = $(ul).closest("header").find(".auto-suggestion-popover ul");
				  $(ulUpdated).find(".suggested-words-wrapper").html("");
				  $(ulUpdated).find(".products-wrapper .header.row").siblings("li").remove();
				  ulUpdated.find("a.js-products-view-all-results").attr("href",searchUrl);
				  var that = this;
				  $.each( items, function( index, item ) {
				    that._renderItemData( ulUpdated, item );
				  });
				},
			_renderItem : function (ul, item){
				
				if (item.type == "autoSuggestion"){
					var divForSuggestions = $(ul).find(".suggested-words-wrapper");
					var renderHtml = "<a href='"+ item.url + "' ><div class='name'><span class='js-name'></span><span class='js-start-of-name'></span><span class='bold js-highlited-part-of-name'>" + item.searchTerm + "</span><span class='js-rest-of-name'>"+ item.addition +"</span></div></a>";
					return $("<li class='ui-menu-item'>")
							.data("item.autocomplete", item)
							.append(renderHtml)
							.appendTo(divForSuggestions);
				}
				else if (item.type == "productResult"){
					var divForProducts = $(ul).find(".products-wrapper");
					var renderHtml = "<a href='" + item.url + "' >";

					if (item.image != null){
						renderHtml += "<div class='thumb'><img src='" + item.image + "'  /></div>";
					}

					renderHtml += 	"<div class='name'>" + item.value +"</div>";
					renderHtml += 	"</a>";

					return $("<li class='ui-menu-item'>").data("item.autocomplete", item).append(renderHtml).appendTo(divForProducts);
				}
			},
			source: function (request, response)
			{
				var self=this;
				var term = request.term.toLowerCase();
				if (term in self.options.cache)
				{
					return response(self.options.cache[term]);
				}

				$.getJSON(self.options.autocompleteUrl, {term: request.term}, function (data)
				{
					var autoSearchData = [];
					if(data.suggestions != null){
						$.each(data.suggestions, function (i, obj)
						{
							autoSearchData.push({
								value: obj.term,
								url: ACC.config.encodedContextPath + "/search?text=" + obj.term,
								type: "autoSuggestion",
								searchTerm: request.term,
								addition: (obj.term).substr((request.term).length)
							});
						});
					}
					if(data.products != null){
						$.each(data.products, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.autocomplete.escapeHTML(obj.name),
								code: obj.code,
								desc: obj.description,
								manufacturer: obj.manufacturer,
								url:  ACC.config.encodedContextPath + obj.url,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? ACC.autocomplete.getSuitableImageForAutoComplete(obj.images).url : null, // prevent errors if obj.images = null
								searchTerm: request.term
							});
						});
					}
					self.options.cache[term] = autoSearchData;
					return response(autoSearchData);
				});
			}

		});

	
		$search = $(".js-site-search-input");
		if($search.length>0){
			$search.yautocomplete()
		}

	},
	
	getSuitableImageForAutoComplete: function(images){
		for(var count = 0; count<images.length; count++){
			if(images[count].format == "autoComplete"){
				return images[count];
			}
		}
	},

	bindDisableSearch: function ()
    {
        $('#js-site-search-input').keyup(function(){
        	$('#js-site-search-input').val($('#js-site-search-input').val().replace(/^\s+/gm,''));
            $('.js_search_button').prop('disabled', this.value == "" ? true : false);
        })
    },

	escapeHTML: function (input) {
		return input.replace(/&/g,'&amp;')
				.replace(/</g,'&lt;')
				.replace(/>/g,'&gt;');
	},
	
	
	bindShoppingListQuickShopAutoComplete: function(){
		// extend the default autocomplete widget, to solve issue on multiple instances of the searchbox component
		$.widget( "custom.yautocomplete", $.ui.autocomplete, {
			_create:function(){
				
				// get instance specific options form the html data attr
				var option = this.element.data("options");
				// set the options to the widget
				this._setOptions({
					minLength: option.minCharactersBeforeRequest,
					displayProductImages: option.displayProductImages,
					delay: option.waitTimeBeforeRequest,
					autocompleteUrl: option.autocompleteUrl,
					source: this.source
				});
				
				// call the _super()
				$.ui.autocomplete.prototype._create.call(this);
				
			},
			options:{
				cache:{}, // init cache per instance
				focus: function (){return false;}, // prevent textfield value replacement on item focus
				select: function (event, ui){
                    window.location.href = ui.item.url;
                }
			},
			_renderMenu: function( ul, items ) {
				  var ulUpdated = $(ul).siblings(".auto-suggestion-popover").find("ul");
				  $(ulUpdated).find(".products-wrapper .header.row").siblings("li").remove();
				  var that = this;
				  $.each( items, function( index, item ) {
				    that._renderItemData( ulUpdated, item );
				  });
				},
			_renderItem : function (ul, item){
				if (item.type == "productResult"){
					var divForProducts = $(ul).find(".products-wrapper");
					var renderHtml = "<a href='" + item.url + "' >";

					if (item.image != null){
						renderHtml += "<div class='thumb'><img src='" + item.image + "'  /></div>";
					}

					renderHtml += 	"<div class='name'>" + item.value +"</div>";
					renderHtml += 	"</a>";

					return $(("<li class='ui-menu-item shopping-list-dropdown' data-product-code='")+ item.code +("'> ")).data("item.autocomplete", item).append(renderHtml).appendTo(divForProducts);
				}
			},
			source: function (request, response)
			{
				var self=this;
				var term = request.term.toLowerCase();
				if (term in self.options.cache)
				{
					return response(self.options.cache[term]);
				}

				$.getJSON(self.options.autocompleteUrl, {term: request.term}, function (data)
				{
					var autoSearchData = [];
					if(data.products != null){
						$.each(data.products, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.autocomplete.escapeHTML(obj.name),
								code: obj.code,
								desc: obj.description,
								manufacturer: obj.manufacturer,
								url:  ACC.config.encodedContextPath + obj.url,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? ACC.autocomplete.getSuitableImageForAutoComplete(obj.images).url : null, // prevent errors if obj.images = null
								searchTerm: request.term
							});
						});
					}
					self.options.cache[term] = autoSearchData;
					return response(autoSearchData);
				});
			}

		});

	
		$search = $(".js-shopping-list-search-input");
		if($search.length>0){
			$search.yautocomplete()
		}

	
		
	},
	
	
	bindShoppingCartQuickShopAutoComplete: function(){
		// extend the default autocomplete widget, to solve issue on multiple instances of the searchbox component
		$.widget( "custom.yautocomplete", $.ui.autocomplete, {
			_create:function(){
				
				// get instance specific options form the html data attr
				var option = this.element.data("options");
				// set the options to the widget
				this._setOptions({
					minLength: option.minCharactersBeforeRequest,
					displayProductImages: option.displayProductImages,
					delay: option.waitTimeBeforeRequest,
					autocompleteUrl: option.autocompleteUrl,
					source: this.source
				});
				
				// call the _super()
				$.ui.autocomplete.prototype._create.call(this);
				
			},
			options:{
				cache:{}, // init cache per instance
				focus: function (){return false;}, // prevent textfield value replacement on item focus
				select: function (event, ui){
                    window.location.href = ui.item.url;
                }
			},
			_renderMenu: function( ul, items ) {
				  var ulUpdated = $(ul).siblings(".auto-suggestion-popover").find("ul");
				  $(ulUpdated).find(".products-wrapper .header.row").siblings("li").remove();
				  var that = this;
				  $.each( items, function( index, item ) {
				    that._renderItemData( ulUpdated, item );
				  });
				},
			_renderItem : function (ul, item){
				if (item.type == "productResult"){
					var divForProducts = $(ul).find(".products-wrapper");
					var renderHtml = "<a href='" + item.url + "' >";

					if (item.image != null){
						renderHtml += "<div class='thumb'><img src='" + item.image + "'  /></div>";
					}

					renderHtml += 	"<div class='name'>" + item.value +"</div>";
					renderHtml += 	"</a>";

					return $(("<li class='ui-menu-item shopping-cart-dropdown' data-product-code='")+ item.code +("'> ")).data("item.autocomplete", item).append(renderHtml).appendTo(divForProducts);
				}
			},
			source: function (request, response)
			{
				var self=this;
				var term = request.term.toLowerCase();
				if (term in self.options.cache)
				{
					return response(self.options.cache[term]);
				}

				$.getJSON(self.options.autocompleteUrl, {term: request.term}, function (data)
				{
					var autoSearchData = [];
					if(data.products != null){
						$.each(data.products, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.autocomplete.escapeHTML(obj.name),
								code: obj.code,
								desc: obj.description,
								manufacturer: obj.manufacturer,
								url:  ACC.config.encodedContextPath + obj.url,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? ACC.autocomplete.getSuitableImageForAutoComplete(obj.images).url : null, // prevent errors if obj.images = null
								searchTerm: request.term
							});
						});
					}
					self.options.cache[term] = autoSearchData;
					return response(autoSearchData);
				});
			}

		});

	
		$search = $(".js-shopping-cart-search-input");
		if($search.length>0){
			$search.yautocomplete()
		}

	
		
	}
};