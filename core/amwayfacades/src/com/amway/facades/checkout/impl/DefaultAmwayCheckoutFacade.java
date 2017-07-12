package com.amway.facades.checkout.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.model.AmwayAccountModel;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.log4j.Logger;

import com.amway.core.checkout.services.AmwayCommerceCheckoutService;
import com.amway.core.session.MagicSessionDataHelper;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.util.AmwayCartHelper;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.facades.checkout.AmwayCheckoutFacade;
import com.amway.facades.order.data.PaymentModeData;

import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.model.ModelService;


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
	 * Gets all Supported  payment modes combinations configured in system
	 */
	@Override
	public Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombinations()
	{
		final CartModel cartModel = getCart();
		AmwayProfileResponseData amwayProfileData = new AmwayProfileResponseData();

		GetBalanceResponseData balanceResponseData = getAmwayAccountBalanceService().getAccountBalance(cartModel);

		amwayProfileData.setAccountBalance(balanceResponseData.getAccountBalance());

			if (cartModel != null) {
			final Map<String, List<AmwayPaymentModeData>> paymentModes = getPaymentModeService()
					.getSupportedPaymentModesCombination(cartModel, amwayProfileData, true);
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
	 * @param paymentModeConverter the paymentModeConverter to set
	 */
	public void setPaymentModeConverter(Converter<PaymentModeModel, PaymentModeData> paymentModeConverter)
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
	 * @param paymentModeService the paymentModeService to set
	 */
	public void setPaymentModeService(AmwayPaymentModeService paymentModeService)
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
	public OrderData placeOrder(SalesApplication application) throws BusinessException
	{
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		final CartModel cartModel = getCart();
		parameter.setCart(cartModel);
		parameter.setSalesApplication(application);
		for (PaymentInfoModel paymentInfoModel : AmwayCartHelper.getPaymentInfoList(cartModel))
		{
			if (paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel)
			{
				final PaymentTransactionEntryModel authPaymentTxnEntryModel = getAmwayCommerceCheckoutService()
						.authorizeAccountBalance(cartModel,
								BigDecimal.valueOf(AmwayCartHelper.getAmountForPayment(cartModel, paymentInfoModel)),
								AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY, paymentInfoModel);
				if (authPaymentTxnEntryModel != null && authPaymentTxnEntryModel.getTransactionStatus()
						.equals(TransactionStatus.ACCEPTED.name()))
				{
					final PaymentTransactionEntryModel capturePaymentTxnEntryModel = getPaymentService()
							.capture(authPaymentTxnEntryModel.getPaymentTransaction());
					if (capturePaymentTxnEntryModel == null || !capturePaymentTxnEntryModel.getTransactionStatus()
							.equals(TransactionStatus.ACCEPTED.name()))
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

	public MagicSessionDataHelper getMagicSessionDataHelper() {
				return magicSessionDataHelper;
			}

	public void setMagicSessionDataHelper(MagicSessionDataHelper magicSessionDataHelper) {
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
	 * @param amwayCommerceCheckoutService the amwayCommerceCheckoutService to set
	 */
	public void setAmwayCommerceCheckoutService(AmwayCommerceCheckoutService amwayCommerceCheckoutService)
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
	 * @param paymentService the paymentService to set
	 */
	public void setPaymentService(PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

	ModelService modelService;
	
	
	/**
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(ModelService modelService)
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
	 * @param amwayAccountBalanceService the amwayAccountBalanceService to set
	 */
	public void setAmwayAccountBalanceService(final AmwayAccountBalanceService amwayAccountBalanceService)
	{
		this.amwayAccountBalanceService = amwayAccountBalanceService;
	}
}	
