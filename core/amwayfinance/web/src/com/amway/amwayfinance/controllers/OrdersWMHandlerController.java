package com.amway.amwayfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amway.amwayfinance.facades.AmwayOrderExternalFinanceChangesFacade;
import com.amway.amwayfinance.order.dto.PaymentEvent;


/**
 * Order modifications from WM.
 */
@Controller
@RequestMapping(value = "/{baseStoreId:.+}/orders")
public class OrdersWMHandlerController
{
	@Autowired
	private AmwayOrderExternalFinanceChangesFacade orderExternalFinanceChangesFacade;

	/**
	 * Endpoint to put order payment information.
	 *
	 * @param orderCode order code of the order to be update.
	 * @param paymentEvent payment transaction information.
	 */
	@Secured({ "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/{orderCode:.+}/payment", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void addOrderPayment(@PathVariable final String baseStoreId,
			@PathVariable final String orderCode,
			@RequestBody PaymentEvent paymentEvent)
	{
		orderExternalFinanceChangesFacade.applyExternalPayment(baseStoreId, orderCode, paymentEvent);
		orderExternalFinanceChangesFacade.notifyAboutExternalPayment(baseStoreId, orderCode);
	}

}
