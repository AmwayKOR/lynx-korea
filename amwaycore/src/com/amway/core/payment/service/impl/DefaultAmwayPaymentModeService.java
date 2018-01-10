/**
 *
 */
package com.amway.core.payment.service.impl;


import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.impl.DefaultPaymentModeService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.model.AmwayPaymentConfigModel;
import com.amway.core.model.AmwayPaymentTypeConfigModel;
import com.amway.core.order.dao.AmwayPaymentModeDao;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.util.AmwayCartHelper;
import com.amway.core.util.AmwayCustomerHelper;



/**
 * Default imlementation for amway payment modes.
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class DefaultAmwayPaymentModeService extends DefaultPaymentModeService implements AmwayPaymentModeService
{
	private AmwayPaymentModeDao paymentModeDao;
	@Autowired
	private SessionService sessionService;

	/**
	 * Gets all Supported split payment modes configured in system
	 *
	 * @param cart
	 * @param amwayProfileData
	 */
	@Override
	public Set<AmwayPaymentModeData> getSupportedSplitPaymentModes(final CartModel cart,
			final AmwayProfileResponseData amwayProfileData)
	{
		return new HashSet<AmwayPaymentModeData>();
	}

	/**
	 * Gets all Supported split payment modes combinations configured in system
	 *
	 * @param cart
	 * @param amwayProfileData
	 * @param skipSelectedMode
	 */
	@Override
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(final AbstractOrderModel cart,
			final AmwayProfileResponseData amwayProfileData, final boolean skipSelectedMode, final String currentPaymentMode)
	{


		final Map<String, BigDecimal> payModeLimits = new HashMap<>();
		payModeLimits.put(AmwaycoreConstants.PaymentMode.ARCREDIT, AmwayCustomerHelper.getARCreditLimit(amwayProfileData));
		return getSupportedPaymentModesCombination(cart, payModeLimits, skipSelectedMode, currentPaymentMode);

	}

	/**
	 * Gets all Supported split payment modes combinations configured in system
	 *
	 * @param cart
	 * @param payModeLimits
	 * @param skipSelectedMode
	 */
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(final AbstractOrderModel cart,
			final Map<String, BigDecimal> payModeLimits, final boolean skipSelectedMode, final String currentPaymentMode)
	{

		final Map<String, List<AmwayPaymentModeData>> supportedConfig = new HashMap<>();
		final BigDecimal cartTotal = BigDecimal.valueOf(cart.getTotalPrice().doubleValue());

		final SalesApplication currentChannel = this.sessionService.getCurrentSession().getAttribute("currentChannel");

		final HashMap<String, Integer> selectedPayModes = skipSelectedMode ? new HashMap<>() : getSelectedPaymentModesForCart(cart);

		if (StringUtils.isNotEmpty(currentPaymentMode))
		{
			addSelectedPayModeToHashMap(selectedPayModes, currentPaymentMode);
		}

		final List<AmwayPaymentConfigModel> configList = getPaymentModeDao().getSupportedSplitCombinations(currentChannel,
				cart.getAccount().getBusinessNature(), cart, selectedPayModes);

		for (final AmwayPaymentConfigModel apcModel : configList)
		{
			final String configCode = apcModel.getCode();
			final List<AmwayPaymentModeData> configListForCode = getConfigForCode(apcModel, payModeLimits, selectedPayModes,
					cartTotal);
			if (CollectionUtils.isNotEmpty(configListForCode))
			{
				supportedConfig.put(configCode, configListForCode);
			}
		}

		//supportedConfig will be used at the time of place order, so that validation will happen
		//will also be used to send it to POS

		return supportedConfig;

	}



	/**
	 * To get supported payment modes.
	 *
	 * @param cart
	 */
	@Override
	public Set<AmwayPaymentModeData> getSupportedPaymentModes(final AbstractOrderModel cart)
	{
		//HPOS-40 and HPOS-33 fix (see Athena code)  this is demo code

		final Map<String, List<AmwayPaymentModeData>> supportedConfig = getSupportedPaymentModesCombination(cart);
		final Set<AmwayPaymentModeData> uniqueConfigset = new HashSet<>();
		for (final List<AmwayPaymentModeData> configListForCode : supportedConfig.values())
		{
			for (final AmwayPaymentModeData modeData : configListForCode)
			{
				uniqueConfigset.add(modeData);
			}
		}
		//uniqueConfigset will be used to send to payment page on web and cscockpit to display the remaining available payment modes
		return uniqueConfigset;
	}

	/**
	 * To get Supported payment modes.
	 *
	 * @param cart
	 * @param amwayProfileData
	 * @return List<PaymentModeModel>
	 */
	@Override
	public List<PaymentModeModel> getSupportedPaymentModes(final CartModel cart, final AmwayProfileResponseData amwayProfileData)
	{
		return Collections.emptyList();
	}

	/**
	 * To check card with unique alias
	 *
	 * @param cart
	 * @param cardAlias
	 */
	@Override
	public boolean isCardAliasExistsInCart(final CartModel cart, final String cardAlias)
	{
		// YTODO Auto-generated method stub
		return false;
	}


	/**
	 * Gets all Supported split payment modes combinations configured in system
	 *
	 * @param cart
	 * @return Map<String, List<AmwayPaymentModeData>>
	 */
	@Override
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(final AbstractOrderModel cart)
	{

		final Map<String, BigDecimal> payModeLimits = new HashMap<>();
		return getSupportedPaymentModesCombination(cart, payModeLimits, false, StringUtils.EMPTY);

	}

	@Override
	public HashMap<String, Integer> getSelectedPaymentModesForCart(final AbstractOrderModel abstractorder)
	{
		final HashMap<String, Integer> selectedPayModes = new HashMap<>();
		AmwayCartHelper.getPaymentTransactionList(abstractorder).forEach(
				paymentTransaction -> addSelectedPayModeToHashMap(selectedPayModes, paymentTransaction.getPaymentMode().getCode()));
		return selectedPayModes;
	}

	private void addSelectedPayModeToHashMap(final HashMap<String, Integer> selectedPayModes, final String PaymentModeValue)
	{
		selectedPayModes.put(PaymentModeValue, selectedPayModes.containsKey(PaymentModeValue)
				? new Integer(selectedPayModes.get(PaymentModeValue).intValue() + 1) : new Integer(1));
	}

	/**
	 * @param apcModel
	 * @param cartTotal
	 * @return
	 */
	private List<AmwayPaymentModeData> getConfigForCode(final AmwayPaymentConfigModel apcModel,
			final Map<String, BigDecimal> payModeLimits, final HashMap<String, Integer> selectedPayModes, final BigDecimal cartTotal)
	{
		List<AmwayPaymentModeData> configListForCode = new ArrayList<>();

		for (final AmwayPaymentTypeConfigModel config : apcModel.getTypeConfigs())
		{
			final String modeCode = config.getPaymentMode().getCode();

			//if there is a limit specified for a payment mode (payment mode is in payment mode map, and value is > 0 ),
			//then return an empty list.
			if (payModeLimits.containsKey(modeCode) && (payModeLimits.get(modeCode).compareTo(BigDecimal.ZERO) < 1))
			{
				configListForCode = Collections.emptyList();
				break;
			}

			if (selectedPayModes.containsKey(modeCode) && selectedPayModes.get(modeCode) == config.getRepeatableCount()
					&& (StringUtils.equals(modeCode, AmwaycoreConstants.PaymentMode.ARCREDIT) && payModeLimits.containsKey(modeCode)
							&& payModeLimits.get(modeCode).compareTo(cartTotal) > 0))
			{
				configListForCode = Collections.emptyList();
				break;
			}
			//else if selected payment mode includes credit card and the current payment mode configuration is AR Credit,
			//and if the payment mode limit for AR Credit is greater than the cart total, then skip
			else if (selectedPayModes.containsKey(AmwaycoreConstants.PaymentMode.CREDITCARD)
					&& StringUtils.equals(modeCode, AmwaycoreConstants.PaymentMode.ARCREDIT)
					&& payModeLimits.get(AmwaycoreConstants.PaymentMode.ARCREDIT).compareTo(cartTotal) > 0)
			{
				//do nothing; continue loop
			}
			else
			{
				//Assemble details about the payment mode (code, repeatable count, max payment amount with this payment mode)
				//and add to the list of payment modes
				final AmwayPaymentModeData modedata = new AmwayPaymentModeData();
				modedata.setCode(modeCode);
				modedata.setRepeatableCount(config.getRepeatableCount());
				modedata.setUsedCount(selectedPayModes.containsKey(modeCode) ? selectedPayModes.get(modeCode).intValue() : 0);
				modedata.setAmount(
						payModeLimits.containsKey(modeCode) ? payModeLimits.get(modeCode).doubleValue() : cartTotal.doubleValue());
				modedata.setAllowOverpay(BooleanUtils.toBoolean(config.getPaymentMode().getAllowOverpay()));
				modedata.setOverpaymentThreshold(config.getPaymentMode().getOverpaymentThreshold());
				modedata.setName(config.getPaymentMode().getName());
				configListForCode.add(modedata);
			}
		}
		return configListForCode;
	}

	@Override
	public AmwayPaymentConfigModel getPaymentConfigForCode(final String code)
	{
		final SalesApplication currentChannel = this.sessionService.getCurrentSession().getAttribute("currentChannel");
		final List<AmwayPaymentConfigModel> paymentConfigList = getPaymentModeDao().getPaymentConfigForCode(code, currentChannel);
		ServicesUtil.validateIfSingleResult(paymentConfigList, "No payment config found for code : " + code,
				"More than one results found for config with code : " + code);
		return paymentConfigList.get(0);
	}

	public AmwayPaymentModeDao getPaymentModeDao()
	{
		return paymentModeDao;
	}

	public void setPaymentModeDao(final AmwayPaymentModeDao paymentModeDao)
	{
		this.paymentModeDao = paymentModeDao;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
