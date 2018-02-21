package com.amway.facades.checkout.impl;

import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.checkout.services.AmwayCommerceCheckoutService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.session.MagicSessionDataHelper;
import com.amway.core.util.AmwayCartHelper;
import com.amway.facades.checkout.AmwayCheckoutFacade;
import com.amway.core.order.data.PaymentModeData;


/**
 * Default Implementation
 */
public class DefaultAmwayCheckoutFacade extends DefaultAcceleratorCheckoutFacade implements AmwayCheckoutFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayCheckoutFacade.class);
	private Converter<PaymentModeModel, PaymentModeData> paymentModeConverter;
	private AmwayPaymentModeService paymentModeService;
	private AmwayCommerceCheckoutService amwayCommerceCheckoutService;
	private PaymentService paymentService;
	private MagicSessionDataHelper magicSessionDataHelper;
	private AmwayAccountBalanceService amwayAccountBalanceService;
	private Map<String, List<AmwayPlaceOrderPaymentsValidator>> placeOrderPaymentsValidatorsMap;
	private BaseSiteService baseSiteService;

	/**
	 * To set delivery address for app.
	 *
	 * @param addressData
	 */
	@Override
	public boolean setDeliveryAddressForApp(final AddressData addressData)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			AddressModel address = getDeliveryAddressModelForCode(addressData.getId());
			if (address == null)
			{
				address = getModelService().create(AddressModel.class);
				address.setOwner(getCurrentUserForCheckout());
			}
			else
			{
				//cloning address
				address = getModelService().clone(address);
				address.setOwner(cartModel);
			}
			address.setContactPointName(addressData.getContactPointName());
			getModelService().save(address);
			cartModel.setDeliveryAddress(address);
			getModelService().save(cartModel);
			getModelService().refresh(cartModel);
			return true;
		}
		return false;
	}

	/**
	 * To get supported payment modes.
	 */
	@Override
	public Set<AmwayPaymentModeData> getSupportedPaymentModes()
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			final Set<AmwayPaymentModeData> paymentModes = getPaymentModeService().getSupportedPaymentModes(cartModel);
			return paymentModes;
		}
		return SetUtils.EMPTY_SET;
	}

	/**
	 * Gets all Supported payment modes combinations configured in system
	 */
	@Override
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombinations()
	{
		final CartModel cartModel = getCart();
		final AmwayProfileResponseData amwayProfileData = new AmwayProfileResponseData();

		final GetBalanceResponseData balanceResponseData = getAmwayAccountBalanceService().getAccountBalance(cartModel);

		amwayProfileData.setAccountBalance(balanceResponseData.getAccountBalance());

		if (cartModel != null)
		{
			final Map<String, List<AmwayPaymentModeData>> paymentModes = getPaymentModeService()
					.getSupportedPaymentModesCombination(cartModel, amwayProfileData, true, StringUtils.EMPTY);
			return paymentModes;
		}
		return MapUtils.EMPTY_MAP;
	}

	/**
	 * @return paymentModeConverter
	 */
	public Converter<PaymentModeModel, PaymentModeData> getPaymentModeConverter()
	{
		return paymentModeConverter;
	}

	/**
	 * @param paymentModeConverter
	 *           the paymentModeConverter to set
	 */
	public void setPaymentModeConverter(final Converter<PaymentModeModel, PaymentModeData> paymentModeConverter)
	{
		this.paymentModeConverter = paymentModeConverter;
	}

	/**
	 * @return paymentModeService
	 */
	public AmwayPaymentModeService getPaymentModeService()
	{
		return paymentModeService;
	}

	/**
	 * @param paymentModeService
	 *           the paymentModeService to set
	 */
	public void setPaymentModeService(final AmwayPaymentModeService paymentModeService)
	{
		this.paymentModeService = paymentModeService;
	}

	/**
	 * SalesApplication based place order
	 *
	 * @param application
	 * @throws BusinessException
	 */
	@Override
	public OrderData placeOrder(final SalesApplication application) throws BusinessException
	{
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		final CartModel cartModel = getCart();
		parameter.setCart(cartModel);
		parameter.setSalesApplication(application);
		for (final PaymentInfoModel paymentInfoModel : AmwayCartHelper.getPaymentInfoList(cartModel))
		{
			if (paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel)
			{
				final PaymentTransactionEntryModel authPaymentTxnEntryModel = getAmwayCommerceCheckoutService()
						.authorizeAccountBalance(cartModel,
								BigDecimal.valueOf(AmwayCartHelper.getAmountForPayment(cartModel, paymentInfoModel)),
								AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY, paymentInfoModel);
				if (authPaymentTxnEntryModel != null
						&& authPaymentTxnEntryModel.getTransactionStatus().equals(TransactionStatus.ACCEPTED.name()))
				{
					final PaymentTransactionEntryModel capturePaymentTxnEntryModel = getPaymentService()
							.capture(authPaymentTxnEntryModel.getPaymentTransaction());
					if (capturePaymentTxnEntryModel == null
							|| !capturePaymentTxnEntryModel.getTransactionStatus().equals(TransactionStatus.ACCEPTED.name()))
					{
						throw new BusinessException("Unable to Capture AmwayMonetaryPaymentInfo for cart code " + cartModel.getCode());
					}
				}
				else
				{
					throw new BusinessException("Unable to Authorize AmwayMonetaryPaymentInfo for cart code " + cartModel.getCode());
				}
			}
		}

		final CommerceOrderResult commerceOrderResult = getCommerceCheckoutService().placeOrder(parameter);
		return getOrderConverter().convert(commerceOrderResult.getOrder());
	}
	
	@Override
	public void validateCart(final CartModel cartModel, final Errors errors) throws BusinessException
	{
		final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap = createOrderPaymentsMap(cartModel);

		final BaseSiteModel currentBaseSite = baseSiteService.getCurrentBaseSite();
		final List<AmwayPlaceOrderPaymentsValidator> posPlaceOrderPaymentsValidators = placeOrderPaymentsValidatorsMap
				.get(currentBaseSite.getUid()) == null
						? placeOrderPaymentsValidatorsMap.get(Config.getParameter(AmwaycoreConstants.DEFAULT_BASE_SITE_ID))
						: placeOrderPaymentsValidatorsMap.get(currentBaseSite.getUid());

		for (final AmwayPlaceOrderPaymentsValidator posPlaceOrderPaymentsValidator : posPlaceOrderPaymentsValidators)
		{
			if (!posPlaceOrderPaymentsValidator.validate(paymentTransactionsMap, cartModel, errors))
			{
				break;
			}
		}
	}

	/**
	 * Method to create a map of the payment mode and list of payment transactions done with that method.
	 * 
	 * @param abstractOrderModel
	 *           the abstract order model
	 * @return the payment transaction map
	 */
	private Map<PaymentModeModel, List<PaymentTransactionModel>> createOrderPaymentsMap(
			final AbstractOrderModel abstractOrderModel)
	{
		// used the size of payment transactions as that provides upper bound's the size of the map
		final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap = new HashMap<>(
				abstractOrderModel.getPaymentTransactions().size());

		for (final PaymentTransactionModel paymentTransactionModel : AmwayCartHelper.getCapturedTransactions(abstractOrderModel))
		{
			final PaymentModeModel paymentModeModel = paymentTransactionModel.getPaymentMode();
			if (null != paymentModeModel && null != paymentTransactionsMap.get(paymentModeModel))
			{
				paymentTransactionsMap.get(paymentModeModel).add(paymentTransactionModel);
			}
			else
			{
				//max size of this list can be the size of total payment transactions
				final List<PaymentTransactionModel> paymentTransactionModels = new ArrayList<>(
						abstractOrderModel.getPaymentTransactions().size());
				paymentTransactionModels.add(paymentTransactionModel);
				paymentTransactionsMap.put(paymentTransactionModel.getPaymentMode(), paymentTransactionModels);
			}
		}
		return paymentTransactionsMap;
	}

	public MagicSessionDataHelper getMagicSessionDataHelper()
	{
		return magicSessionDataHelper;
	}

	public void setMagicSessionDataHelper(final MagicSessionDataHelper magicSessionDataHelper)
	{
		this.magicSessionDataHelper = magicSessionDataHelper;
	}

	/**
	 * @return amwayCommerceCheckoutService
	 */
	public AmwayCommerceCheckoutService getAmwayCommerceCheckoutService()
	{
		return amwayCommerceCheckoutService;
	}

	/**
	 * @param amwayCommerceCheckoutService
	 *           the amwayCommerceCheckoutService to set
	 */
	public void setAmwayCommerceCheckoutService(final AmwayCommerceCheckoutService amwayCommerceCheckoutService)
	{
		this.amwayCommerceCheckoutService = amwayCommerceCheckoutService;
	}


	/**
	 * @return paymentService
	 */
	public PaymentService getPaymentService()
	{
		return paymentService;
	}

	/**
	 * @param paymentService
	 *           the paymentService to set
	 */
	public void setPaymentService(final PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

	ModelService modelService;


	/**
	 * @return modelService
	 */
	@Override
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return amwayAccountBalanceService
	 */
	public AmwayAccountBalanceService getAmwayAccountBalanceService()
	{
		return amwayAccountBalanceService;
	}

	/**
	 * @param amwayAccountBalanceService
	 *           the amwayAccountBalanceService to set
	 */
	public void setAmwayAccountBalanceService(final AmwayAccountBalanceService amwayAccountBalanceService)
	{
		this.amwayAccountBalanceService = amwayAccountBalanceService;
	}

	public Map<String, List<AmwayPlaceOrderPaymentsValidator>> getPlaceOrderPaymentsValidatorsMap()
	{
		return placeOrderPaymentsValidatorsMap;
	}

	@Required
	public void setPlaceOrderPaymentsValidatorsMap(
			final Map<String, List<AmwayPlaceOrderPaymentsValidator>> placeOrderPaymentsValidatorsMap)
	{
		this.placeOrderPaymentsValidatorsMap = placeOrderPaymentsValidatorsMap;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}
}
