<%@ tag language="java" pageEncoding="ISO-8859-1"%>


<div id="shippingdiv" class="shipping-delivery-ship">
    <p class="shipping-delivery-shipping-header">this order will ship to</p>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1" />
        <label for="radio1" data-toggle="collapse" href="#collapseOne" data-parent="#shippingdiv">Address on File</label>
        <div id="collapseOne" class="shipping-delivery-radio-body panel-collapse collapse in">
            <p class="shipping-delivery-address-name">Yui Mori</p>
            <p class="shipping-delivery-address-detail"> 120 - 0023<br /> Tokyo-to <br /> 3-5-10, Taito, Taito-Ku, Tokyo<br /> RM 102-1,1F<br /> 03 - 5739 - 3341<br /> yui.mori@email.com <br /> </p>
            <a href="#" class="shipping-delivery-address-edit"> Edit Address </a>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio2" />
        <label for="radio2" class="collapsed" data-toggle="collapse" href="#collapseTwo" data-parent="#shippingdiv">Enter New Address</label>
        <div id="collapseTwo" class="shipping-delivery-radio-body panel-collapse collapse">
            <form action="" method="post" name="editAddress">
                <fieldset>
                    <div class="form-group">
                        <label for="addName">Name</label>
                        <input name="addName" type="text" value="Aiko Fukui" />
                    </div>
                    <div class="form-group">
                        <label for="zipCode">zip code</label>
                        <input name="zipCode" value="120" />
                        <div class="shipping-delivery-dash"></div>
                        <input name="zipCode2" type="text" value="0023" />
                        <button class="btn btn-block">Search Zip Code</button>
                    </div>
                    <div class="form-group">
                        <label for="perfecture">PREFECTURE</label>
                        <select class="shipping-delivery-size" id="perfecture"> <option>Tokyo-to</option> <option selected="">Tokyo-to</option> <option>Tokyo-to</option> </select>
                    </div>
                    <div class="form-group">
                        <label for="city">city, WORD (ISLAND)</label>
                        <input name="city" type="text" value="3-5-10, Taito, Taito-Ku, Tokyo" />
                    </div>
                    <div class="form-group">
                        <label for="rest">REST OF ADDRESS</label>
                        <input name="rest" type="text" value="RM 102-1, 6F" />
                    </div>
                    <div class="form-group">
                        <label for="email">email</label>
                        <input name="email" type="text" value="aiko.fukui@email.com" />
                    </div>
                    <div class="shipping-delivery-options-container">
                        <input class="login-form-remember" id="makePrimary" name="makePrimary" type="checkbox" />
                        <label for="makePrimary">Make Primary Shipping Address</label>
                        <a name="savebutton" href="#" class="shipping-delivery-save btn-blue-white">save</a>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio3" />
        <label for="radio3" class="collapsed" data-toggle="collapse" href="#collapseThree" data-parent="#shippingdiv">Use Alternate Address on File</label>
        <div id="collapseThree" class="shipping-delivery-radio-body panel-collapse collapse">
            <div class="panel-body">
                Alternate Address
            </div>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio4" />
        <label for="radio4" class="collapsed" data-toggle="collapse" href="#collapseFour" data-parent="#shippingdiv">Select an IBO / Customer</label>
        <div id="collapseFour" class="shipping-delivery-radio-body panel-collapse collapse">
            <div class="panel-body">
                IBO / Customer
            </div>
        </div>
    </div>
</div>