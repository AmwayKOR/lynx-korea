<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<div class="cbox cboxAddToYourDitto">
	<div class="cart-popup__dialog">
		<div class="cart-popup__header">
			<span class="cart-popup__header-text">
				added to your ditto
				<img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close"
					aria-hidden="true">
			</span>
		</div>
		<div class="cart-popup__content">
			<div class="car-popup-item-des">
				<b>1</b>
				item added to 3 DITTO lists
			</div>
			<div class="cart-popup__item-info amwahover">
				<div class="item-list-module in-dialog">
					<ul>
						<li>
							<h5>
								<a href="">Jennifer Jones'First DITTO(20)</a>
								<label class="label-date">Order Drop Date 11/15/16</label>
							</h5>
							<span>Created for Jennifer Jones</span>
						</li>
						<li>
							<h5>
								<a href="">Second DITTO List Name(54)</a>
								<label class="label-date">Order Drop Date 12/15/15</label>
							</h5>
							<span>Created for Jennifer Jones</span>
						</li>
						<li>
							<h5>
								<a href="">My Other DITTO List Has a Long Name(999)</a>
								<label class="label-date">Order Drop Date 05/12/10</label>
							</h5>
							<span>Created for Jennifer Jones</span>
						</li>
					</ul>
				</div>
			</div>
			<div class="cart-popup__item-link">
				<a href="#" class="btn-blue-white closeCbox">GO TO DITTO</a>
				<a class="cart-popup__item-link-text closeCbox" href="javascript:void(0);">Continue Shopping</a>
			</div>
		</div>
	</div>
</div>
<div class="cbox cboxCreateNewDitto">
	<div class="cart-popup__dialog">
		<div class="cart-popup__header">
			<span class="cart-popup__header-text">
				Create a new ditto schedule
				<img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close"
					aria-hidden="true">
			</span>
		</div>
		<div class="cart-popup__content">

			<div class="cart-popup__item-info amwahover">
				<div class="dialog-form-module">
					<div class="row">
						<div class="col-md-4 form-name">Ditto name</div>
						<div class="col-md-8 form-opera">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 form-name">order recipient</div>
						<div class="col-md-8 form-opera">
							<input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1">
							<label for="radio1" data-parent="#order1">Me</label>
							<input class="amwa-radio" type="radio" name="optradio" id="radio2" value="radio2">
							<label for="radio2" data-parent="#order1">My Sponsor[Sponsor Name Goes Here]</label>
							<input class="amwa-radio" type="radio" name="optradio" id="radio3" value="radio3">
							<label for="radio3" data-parent="#order1">Custom List from My Contacts</label>
							<div class="search-contacts">
								<img src="images/add_new_people.png">
								<a href="javascript:void(0);" id="searchContacts">Search Contacts</a>
								|&nbsp;(0)Total
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="cart-popup__item-link">
				<a href="javascript:void(0);" class="closeCbox btn-blue-white">CREATE DITTO</a>
				<a class="cart-popup__item-link-text closeCbox" href="javascript:void(0);">Cancel</a>
			</div>
		</div>
	</div>
</div>
<div class="cbox cboxCreateNewDittoNext">
	<div class="cart-popup__dialog">
		<div class="cart-popup__header">
			<span class="cart-popup__header-text">
				Create a new ditto schedule
				<img class="cart-popup__close" src="images/close.png" alt="close" data-dismiss="modal" aria-label="Close"
					aria-hidden="true">
			</span>
		</div>
		<div class="cart-popup__content">

			<div class="cart-popup__item-info amwahover height-auto">
				<div class="dialog-form-module">
					<a href="#" id="backPrevious"> Go Back to Previous Screen</a>
					<h6>
						QUICKFIND:
						<label>Enter the Name or ID Number of the IBO/Customer</label>
					</h6>
					<div class="search-wrap">
						<input class="form-control" id="contacts-search">
						<div class="contacts-auto-suggestion">
							<div class="suggestion-header container-fluid">
								<div class="suggestion-row row">
									<div class="col-md-6">NAME</div>
									<div class="col-md-3">TYPE</div>
									<div class="col-md-3">ID NUMBER</div>
								</div>
							</div>
							<div class="suggestion-body container-fluid">
								<div class="suggestion-row row">
									<div class="suggestion-name col-md-6">Andrew Allen</div>
									<div class="col-md-3">IBO</div>
									<div class="col-md-3">123124124124</div>
								</div>
								<div class="suggestion-row row">
									<div class="suggestion-name col-md-6">Andrew Hackman</div>
									<div class="col-md-3">IBO</div>
									<div class="col-md-3">123124124124</div>
								</div>
								<div class="suggestion-row row">
									<div class="suggestion-name col-md-6">Andrew Frisch</div>
									<div class="col-md-3">IBO</div>
									<div class="col-md-3">123124124124</div>
								</div>
							</div>
						</div>
					</div>
					<h6>SELECT CUSTOMERS/IBO:</h6>
					<div class="table container-fluid">
						<div class="table-header row">
							<div class="col-md-7">
								NAME
								<i class="glyphicon glyphicon-menu-down"></i>
							</div>
							<div class="col-md-2">
								TYPE
								<i class="glyphicon glyphicon-menu-down"></i>
							</div>
							<div class="col-md-3">ID NUMBER</div>
						</div>
						<div class="table-body container-fluid">
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category1" name="category1" type="checkbox">
									<label for="category1">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category2" name="category1" type="checkbox">
									<label for="category2">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category3" name="category1" type="checkbox">
									<label for="category3">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category5" name="category1" type="checkbox">
									<label for="category5">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category4" name="category1" type="checkbox">
									<label for="category4">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category6" name="category1" type="checkbox">
									<label for="category6">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
							<div class="row">
								<div class="col-md-7">
									<input class="login-form__remember" id="category7" name="category1" type="checkbox">
									<label for="category7">Aaron Ackerman</label>
								</div>
								<div class="col-md-2">IBO</div>
								<div class="col-md-3">1231241241</div>
							</div>
						</div>
					</div>
					<h6>
						RECIPIENT LIST:
						<span>(5)Total</span>
					</h6>
					<div class="border-from">
						<div>
							Aaron Ackerman
							<i class="glyphicon glyphicon-remove"></i>
						</div>
						<div>
							Aaron Ackerman
							<i class="glyphicon glyphicon-remove"></i>
						</div>
						<div>
							Aaron Ackerman
							<i class="glyphicon glyphicon-remove"></i>
						</div>
						<div>
							Aaron Ackerman
							<i class="glyphicon glyphicon-remove"></i>
						</div>
						<div>
							Aaron Ackerman
							<i class="glyphicon glyphicon-remove"></i>
						</div>
						<div>
							Aaron Ackerman
							<i class="glyphicon glyphicon-remove"></i>
						</div>
					</div>
				</div>
			</div>
			<div class="cart-popup__item-link">
				<a href="javascript:void(0);" class="btn-blue-white closeCbox">CONFIRM</a>
			</div>
		</div>
	</div>
</div>