package com.amway.core.payment.service;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.PaymentModeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.model.AmwayPaymentConfigModel;
import com.amway.core.order.data.AmwayPaymentModeData;


/**
 * Service around the {@link PaymentModeModel}. {@link PaymentModeModel} is used to store informations about payment
 * mode on order.
 */
public interface AmwayPaymentModeService extends PaymentModeService
{
	/**
	 * Gets all Supported payment modes
	 *
	 * @param cart
	 * @return Set<AmwayPaymentModeData>
	 */
	Set<AmwayPaymentModeData> getSupportedPaymentModes(AbstractOrderModel cart);

	/**
	 * Gets all Supported payment modes
	 *
	 * @param cart
	 * @param amwayProfileData
	 * @return List<PaymentModeModel>
	 */
	List<PaymentModeModel> getSupportedPaymentModes(final CartModel cart, final AmwayProfileResponseData amwayProfileData);


	/**
	 * Gets all Supported split payment modes configured in system
	 *
	 * @param cart
	 * @param amwayProfileData
	 * @return Set<AmwayPaymentModeData>
	 */

	Set<AmwayPaymentModeData> getSupportedSplitPaymentModes(final CartModel cart, final AmwayProfileResponseData amwayProfileData);


	/**
	 * Gets all Supported split payment modes combinations configured in system
	 *
	 * @param cart
	 * @param amwayProfileData
	 * @param skipSelectedMode
	 * @return Map<String, List<AmwayPaymentModeData>>
	 */
	Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(final AbstractOrderModel cart,
			final AmwayProfileResponseData amwayProfileData, boolean skipSelectedMode, final String currentPaymentMode);

	/**
	 * To check card with unique alias
	 *
	 * @param cart
	 * @param cardAlias
	 * @return boolean
	 */
	boolean isCardAliasExistsInCart(CartModel cart, String cardAlias);

	/**
	 * @param cart
	 * @return Map<String, List<AmwayPaymentModeData>>
	 */
	Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(AbstractOrderModel cart);
	
	/**
	 * Method to get selected payment modes for the specified cart
	 *
	 * @param abstractOrder
	 *           the abstract order
	 * @return the selected payment modes
	 */
	HashMap<String, Integer> getSelectedPaymentModesForCart(final AbstractOrderModel abstractOrder);
	
	
	/**
	 * Method to get payment config for specified code.
	 * 
	 * @param code
	 *           the code
	 * @return the amway payment config model
	 */
	AmwayPaymentConfigModel getPaymentConfigForCode(final String code);


}
