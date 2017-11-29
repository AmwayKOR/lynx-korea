<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="collapse mini-cart-items-container js-mini-cart-items-container mini-cart-wrapper"
	id="shoppingcar-drop-content">
	<div class="arrow" style=""></div>
	<ul class="nav nav-tabs">
		<li class="active">
			<a data-toggle="tab" href="#miniShoppingCartPopupContentPC"> Shopping Cart </a>
		</li>
		<li>
			<a data-toggle="tab" href="#dittoPopupContentPC">My Ditto</a>
		</li>
	</ul>
	<div class="tab-content js-tab-content">
		<div id="miniShoppingCartPopupContentPC" class="tab-pane fade in active"></div>
		<div id="dittoPopupContentPC" class="tab-pane fade">
			<div class="ditto-content">
				<p class="tip-gray">Your DITTO schedules are sorted by Drop Date,which is the date your order will arrive.</p>
				<ul>
					<li>
						<h5>
							<a href="">Jennifer Jones'First DITTO(20)</a>
							<label class="label-date">11/15/16</label>
						</h5>
						<span>180.22PV/570.00BV</span>
						<span>Subtotal:$500.00</span>
					</li>
					<li>
						<h5>
							<a href="">Second DITTO List Name(54)</a>
							<label class="label-date">12/15/15</label>
						</h5>
						<span>180.22PV/570.00BV</span>
						<span>Subtotal:$500.00</span>
					</li>
					<li>
						<h5>
							<a href="">My Other DITTO List Has a Long Name(999)</a>
							<label class="label-date">05/12/10</label>
						</h5>
						<span>180.22PV/570.00BV</span>
						<span>Subtotal:$500.00</span>
					</li>
				</ul>
				<div class="bottom-total">
					<a href="">
						You have
						<b>5</b>
						DITTO schedules.
					</a>
				</div>
				<div class="bottom-button">
					<button class="btn-blue-white">VIEW ALL DITTO SCHEDULES</button>
				</div>
			</div>
		</div>
	</div>
</div>
