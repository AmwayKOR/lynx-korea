package com.amway.facades.checkout;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.exceptions.BusinessException;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.validation.Errors;

import com.amway.core.order.data.AmwayPaymentModeData;


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
	 * Gets all Supported payment modes combinations configured in system
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

	/**
	 * Method to validate the cart.
	 *
	 * @param cartModel
	 *           the cart
	 * @param errors
	 *           the errors
	 * @throws BusinessException
	 *            the business exception
	 */
	void validateCart(CartModel cartModel, Errors errors) throws BusinessException;
}
