package com.amway.facades.checkout;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amway.core.order.data.AmwayPaymentModeData;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.exceptions.BusinessException;


/**
 * Interface for Amway checkout.
 */
public interface AmwayCheckoutFacade extends AcceleratorCheckoutFacade
{

	/**
	 * To get Supported payment modes
	 *
	 * @return AmwayPaymentModeData
	 */
	Set<AmwayPaymentModeData> getSupportedPaymentModes();

	/**
	 * Gets all Supported  payment modes combinations configured in system
	 *
	 * @return Map<String, List<AmwayPaymentModeData>>
	 */
	Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombinations();

	/**
	 * SalesApplication based place order
	 *
	 * @param application
	 * @return OrderData
	 * @throws InvalidCartException
	 * @throws BusinessException
	 */
	OrderData placeOrder(SalesApplication application) throws InvalidCartException, BusinessException;



	/**
	 * @param addressData
	 * @return boolean
	 */
	boolean setDeliveryAddressForApp(AddressData addressData);
}
