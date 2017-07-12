/**
 *
 */
package com.amway.core.payment.service.impl;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.order.impl.DefaultPaymentModeService;


import java.util.*;
import java.math.BigDecimal;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.core.model.AmwayCashPaymentInfoModel;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.model.AmwayPaymentConfigModel;
import com.amway.core.model.AmwayPaymentTypeConfigModel;

import com.amway.core.order.dao.AmwayPaymentModeDao;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Default imlementation for amway payment modes.
 */
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
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(final CartModel cart,
			final AmwayProfileResponseData amwayProfileData, final boolean skipSelectedMode)
	{
		final Map<String, List<AmwayPaymentModeData>> supportedConfig = new HashMap<>();

		final BigDecimal cartTotal = BigDecimal.valueOf(cart.getTotalPrice().doubleValue());
		final Map<String, BigDecimal> payModeLimits = new HashMap<>();
		payModeLimits.put(AmwaycoreConstants.PaymentMode.ARCREDIT, AmwayCustomerHelper.getARCreditLimit(amwayProfileData));
		final SalesApplication currentChannel = this.sessionService.getCurrentSession().getAttribute("currentChannel");

		final HashMap<String, Integer> selectedPayModes = skipSelectedMode ? new HashMap<>()
				: getSelectedPaymentModesForCart(cart);

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
	public Set<AmwayPaymentModeData> getSupportedPaymentModes(final CartModel cart)
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
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombination(final CartModel cart)
	{
		//HPOS-40 and HPOS-33 fix (see Athena code)  this is demo code
		final Map<String, List<AmwayPaymentModeData>> supportedConfig = new HashMap<>();
		final List<AmwayPaymentModeData> ccConfigListForCode = new ArrayList<>();

		final AmwayPaymentModeData cc = new AmwayPaymentModeData();
		cc.setCode(AmwaycoreConstants.PaymentMode.CREDITCARD);
		ccConfigListForCode.add(cc);
		supportedConfig.put(AmwaycoreConstants.PaymentMode.CREDITCARD, ccConfigListForCode);

		final List<AmwayPaymentModeData> cashConfigListForCode = new ArrayList<>();
		final AmwayPaymentModeData cash = new AmwayPaymentModeData();
		cash.setCode(AmwaycoreConstants.PaymentMode.CASH);
		cashConfigListForCode.add(cash);
		supportedConfig.put(AmwaycoreConstants.PaymentMode.CASH, cashConfigListForCode);

		return supportedConfig;

	}

	/**
	 * @param cart
	 * @return
	 */
	private HashMap<String, Integer> getSelectedPaymentModesForCart(final CartModel cart)
	{
		final Collection<PaymentInfoModel> cartPaymentInfoList = cart.getPaymentInfos();
		final HashMap<String, Integer> selectedPayModes = new HashMap<>();

		for (final PaymentInfoModel pim : cartPaymentInfoList)
		{
			switch (pim.getItemtype())
			{
				case CreditCardPaymentInfoModel._TYPECODE:
					addSelectedPayModeToHashMap(selectedPayModes, AmwaycoreConstants.PaymentMode.CREDITCARD);
					break;

				case AmwayMonetaryPaymentInfoModel._TYPECODE:
					addSelectedPayModeToHashMap(selectedPayModes, AmwaycoreConstants.PaymentMode.ARCREDIT);
					break;

				case AmwayCashPaymentInfoModel._TYPECODE:
					addSelectedPayModeToHashMap(selectedPayModes, AmwaycoreConstants.PaymentMode.CASH);
					break;

				default:
					break;
			}
		}

		return selectedPayModes;
	}

	private void addSelectedPayModeToHashMap(HashMap<String, Integer> selectedPayModes, String PaymentModeValue) {

		selectedPayModes.put(PaymentModeValue,
				selectedPayModes.containsKey(PaymentModeValue)
						? new Integer(selectedPayModes.get(PaymentModeValue) + 1)
						: new Integer(1));
	}

	/**
	 * @param apcModel
	 * @param cartTotal
	 * @return
	 */
	private List<AmwayPaymentModeData> getConfigForCode(final AmwayPaymentConfigModel apcModel,
														final Map<String, BigDecimal> payModeLimits,
														final HashMap<String, Integer> selectedPayModes,
														final BigDecimal cartTotal)
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

			if (selectedPayModes.containsKey(modeCode)
					&& selectedPayModes.get(modeCode) == config.getRepeatableCount()
						&& (StringUtils.equals(modeCode, AmwaycoreConstants.PaymentMode.ARCREDIT)
						&& payModeLimits.containsKey(modeCode)
						&& payModeLimits.get(modeCode).compareTo(cartTotal) > 0 ))
			{
				configListForCode = Collections.emptyList();
				break;
			}
			//else if selected payment mode includes credit card and the current payment mode configuration is AR Credit,
			//and if the payment mode limit for AR Credit is greater than the cart total, then skip
			else if ( selectedPayModes.containsKey(AmwaycoreConstants.PaymentMode.CREDITCARD)
					&& StringUtils.equals(modeCode, AmwaycoreConstants.PaymentMode.ARCREDIT)
					&& payModeLimits.get(AmwaycoreConstants.PaymentMode.ARCREDIT).compareTo(cartTotal) > 0 )
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
				modedata.setAmount(payModeLimits.containsKey(modeCode) ? payModeLimits.get(modeCode).doubleValue() : cartTotal.doubleValue());
				configListForCode.add(modedata);
			}
		}
		return configListForCode;
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
