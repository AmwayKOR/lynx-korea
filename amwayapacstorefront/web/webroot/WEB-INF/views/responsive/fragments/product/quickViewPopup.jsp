 <div class="cart-popup__dialog">
     <div class="cart-popup__header">
         <span class="cart-popup__header-text">QUICK VIEW
             <img class="cart-popup__close quick-view-close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
     <div class="cart-popup__content">
         <div class="cart-popup__item-info amwahover">
             <img src="${themeResourcePath}/images/product_list_item2.png" class="cart-popup__thumbnail" alt="product">
             <div class="cart-popup__item-detail">
                 <p class="cart-popup__item-title">${product.name}</p>
                 <span class="cart-popup__item-number">Item #: ${product.code}</span>
                 <div class="cart-popup__item-title cart-popup__item-aboprice">
                     <span class="product-list__item-abovalue new-product-list__item-abovalue">${product.price.formattedValue}</span>
                     <span>ABO Price:</span></div>
                 <div class="cart-popup__item-retailprice">
                     <span>Retail Price:</span>
                     <span class="product-list__item-abovalue Retail-product-list__item-abovalue">${product.retailPrice.formattedValue}</span>
                     <span class="view-box-divider">|</span>
                     <span>PV / BV:</span>
                     <span class="product-list__item-abovalue PV-product-list__item-abovalue">${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span></div>
                 <div class="cart-popup__quantity cart-popup__item-retailprice">
                     <div class="amway-theme qty-selector js-qty-selector control-group">
                         <div class="row">
                             <div class="size-selector-container">
                                 <label class="control-label" for="pdpAddtoCartInput">Size</label>
                                 <select class="size-select text-center js-qty-selector-input form-control" value="60 Packets">
                                     <option>60 Packets</option>
                                     <option>30 Packets</option></select>
                             </div>
                             <div class="qty-selector-container">
                                 <label class="control-label" for="pdpAddtoCartInput">Qty</label>
                                 <input type="text" maxlength="3" class="text-center js-qty-selector-input" size="1" value="1" data-max="FORCE_IN_STOCK" data-min="1" name="pdpAddtoCartInput" id="pdpAddtoCartInput">
                                 <div class="product-stock">
                                     <span class="stock">
                                         <span class="product-availability">
                                             <span class="green">
                                                 <span class="icon icon-check-bold"></span>
                                                 <span class="text text-uppercase">In Stock</span></span>
                                         </span>
                                     </span>
                                 </div>
                             </div>
                             <a href="#">
                                 <div class="view-more-details">View More Details</div></a>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <div class="cart-popup__item-link">
             <div class="cart-popup__item-link-content">
                 <button id="addToCartButton" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">Add to cart</button>
                 <button id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">BUY NOW</button></div>
         </div>
     </div>
 </div>
