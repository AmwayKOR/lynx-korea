package com.amway.core.checkout.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCheckoutService;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.account.restrictions.impl.AccountOrderRestriction;
import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.checkout.services.AmwayCommerceCheckoutService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayCreditPaymentInfoModel;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.core.util.AmwayCartHelper;



/**
 * Default Implementation of Amway Commerce Checkout Service
 */
public class DefaultAmwayCommerceCheckoutService extends DefaultCommerceCheckoutService implements AmwayCommerceCheckoutService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayCommerceCheckoutService.class);
	private AmwayAccountBalanceService amwayAccountBalanceService;
	private AmwayAccountCommerceService amwayAccountCommerceService;
	private AccountOrderRestriction accountOrderRestriction;
	private SessionService sessionService;

	/**
	 * {@link #createARCreditPaymentInfo(de.hybris.platform.core.model.user.CustomerModel, com.amway.core.model.AmwayMonetaryPaymentInfoModel, de.hybris.platform.core.model.order.CartModel)}
	 */
	@Override
	public AmwayMonetaryPaymentInfoModel createARCreditPaymentInfo(final CustomerModel customerModel, final double arAmount,
			final CartModel cartModel)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "customer model cannot be null");

		final PaymentInfoModel paymentInfoModel = cartModel.getPaymentInfo();
		final AmwayMonetaryPaymentInfoModel arCreditPaymentInfoModel;

		if (paymentInfoModel != null && paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel)
		{
			arCreditPaymentInfoModel = (AmwayMonetaryPaymentInfoModel) paymentInfoModel;
		}
		else
		{
			arCreditPaymentInfoModel = getModelService().create(AmwayMonetaryPaymentInfoModel.class);
			arCreditPaymentInfoModel.setUser(customerModel);
			arCreditPaymentInfoModel.setCode(UUID.randomUUID().toString());
		}
		arCreditPaymentInfoModel.setBillingAddress(cartModel.getPaymentAddress());
		cartModel.setPaymentInfo(arCreditPaymentInfoModel);
		getModelService().saveAll(new Object[] { arCreditPaymentInfoModel, cartModel });

		adjustAmountInInfo(String.valueOf(arAmount), cartModel, arCreditPaymentInfoModel, StringUtils.EMPTY, StringUtils.EMPTY);
		return arCreditPaymentInfoModel;
	}

	// replacemet order payment ARCreditPaymentInfo

	/**
	 * {@link #createARCreditPaymentInfo(de.hybris.platform.core.model.user.CustomerModel, com.amway.facades.order.data.ArCreditPaymentInfoData, de.hybris.platform.core.model.order.AbstractOrderModel)}
	 */
	@Override
	public AmwayMonetaryPaymentInfoModel createARCreditPaymentInfo(final CustomerModel customerModel,
			final AbstractOrderModel cartModel)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "customer model cannot be null");

		final PaymentInfoModel paymentInfoModel = cartModel.getPaymentInfo();
		final AmwayMonetaryPaymentInfoModel arCreditPaymentInfoModel;

		if (paymentInfoModel != null && paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel)
		{
			arCreditPaymentInfoModel = (AmwayMonetaryPaymentInfoModel) paymentInfoModel;
		}
		else
		{
			arCreditPaymentInfoModel = getModelService().create(AmwayMonetaryPaymentInfoModel.class);
			arCreditPaymentInfoModel.setUser(customerModel);
			arCreditPaymentInfoModel.setCode(UUID.randomUUID().toString());
		}
		arCreditPaymentInfoModel.setBillingAddress(cartModel.getPaymentAddress());
		cartModel.setPaymentInfo(arCreditPaymentInfoModel);
		getModelService().saveAll(new Object[] { arCreditPaymentInfoModel, cartModel });

		return arCreditPaymentInfoModel;
	}

	/**
	 * {@link #authorizeAccountBalance(de.hybris.platform.core.model.order.CartModel, java.math.BigDecimal, java.lang.String, de.hybris.platform.core.model.order.payment.PaymentInfoModel)}
	 */
	@Override
	public PaymentTransactionEntryModel authorizeAccountBalance(final CartModel cartModel, final BigDecimal amount,
			final String balanceTypeCd, final PaymentInfoModel info) throws BusinessException
	{

		ServicesUtil.validateParameterNotNull(cartModel, "cart model cannot be null");
		ServicesUtil.validateParameterNotNull(balanceTypeCd, "balanceTypeCd cannot be null");

		final PaymentTransactionModel transactionModel = getModelService().create(PaymentTransactionModel.class);
		transactionModel.setOrder(cartModel);
		transactionModel.setCode(UUID.randomUUID().toString());
		transactionModel.setCurrency(cartModel.getCurrency());
		transactionModel.setInfo(info);
		//auth if entered credit is valid or not
		final PaymentTransactionEntryModel authTransactionEntryModel = authAccountBalance(cartModel, amount, balanceTypeCd);
		authTransactionEntryModel.setPaymentTransaction(transactionModel);
		transactionModel.setPlannedAmount(amount);
		transactionModel.setPaymentProvider("Magic");
		getModelService().save(authTransactionEntryModel);

		return authTransactionEntryModel;
	}

	/**
	 * {@link #authorizeAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String, de.hybris.platform.core.model.order.payment.PaymentInfoModel)}
	 */
	@Override
	public PaymentTransactionEntryModel authorizeAccountBalance(final AbstractOrderModel cartModel, final BigDecimal amount,
			final String balanceTypeCd, final PaymentInfoModel info) throws BusinessException
	{

		ServicesUtil.validateParameterNotNull(cartModel, "cart model cannot be null");
		ServicesUtil.validateParameterNotNull(balanceTypeCd, "balanceTypeCd cannot be null");

		final PaymentTransactionModel transactionModel = getModelService().create(PaymentTransactionModel.class);
		transactionModel.setOrder(cartModel);
		transactionModel.setCode(UUID.randomUUID().toString());
		transactionModel.setCurrency(cartModel.getCurrency());
		transactionModel.setInfo(info);
		//auth if entered credit is valid or not
		final PaymentTransactionEntryModel authTransactionEntryModel = authAccountBalance(cartModel, amount, balanceTypeCd);
		authTransactionEntryModel.setPaymentTransaction(transactionModel);
		transactionModel.setPlannedAmount(amount);
		transactionModel.setPaymentProvider("Magic");
		getModelService().save(authTransactionEntryModel);

		return authTransactionEntryModel;
	}



	/**
	 * {@link #authAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String)}
	 */
	@Override
	public PaymentTransactionEntryModel authAccountBalance(final AbstractOrderModel cartModel, final BigDecimal amount,
			final String balanceTypeCd) throws BusinessException
	{
		// check entered credit is valid or not
		final GetBalanceResponseData response = getAmwayAccountBalanceService().getAccountBalance(cartModel);
		final PaymentTransactionEntryModel paymentTransactionEntryModel = getModelService()
				.create(PaymentTransactionEntryModel.class);
		paymentTransactionEntryModel.setCode(UUID.randomUUID().toString());

		if (response.getReturnCd() == 1 && validateARLimit(response.getAccountBalance(), amount, balanceTypeCd))
		{
			paymentTransactionEntryModel.setAmount(amount);
			paymentTransactionEntryModel.setCurrency(cartModel.getCurrency());
			paymentTransactionEntryModel.setTime(new Date());
			paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			paymentTransactionEntryModel.setTransactionStatusDetails("SUCCESFULL");
			paymentTransactionEntryModel.setType(PaymentTransactionType.AUTHORIZATION);
			paymentTransactionEntryModel.setRequestToken(balanceTypeCd);

			getModelService().save(paymentTransactionEntryModel);
			return paymentTransactionEntryModel;
		}
		else
		{
			AmwayCartHelper.createCancelTransction(cartModel);
			paymentTransactionEntryModel.setAmount(new BigDecimal(0));
			paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.REJECTED.name());
			paymentTransactionEntryModel.setTransactionStatusDetails("REJECTED");
			paymentTransactionEntryModel.setType(PaymentTransactionType.AUTHORIZATION);
			getModelService().save(paymentTransactionEntryModel);

			throw new BusinessException("Failed to Authorize Amway Credit - insufficient balance ");
		}

	}

	private boolean validateARLimit(final List<AccountBalanceData> accBals, final BigDecimal amount, final String balanceTypeCd)
	{
		for (final AccountBalanceData accBal : accBals)
		{
			if (StringUtils.equalsIgnoreCase(accBal.getBalanceTypeCd(), balanceTypeCd))
			{
				if (Double.valueOf(accBal.getBalanceAmount()).doubleValue() >= amount.doubleValue())
				{
					return true;
				}
			}
		}
		return false;
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



	/**
	 * {@link #isPlaceOrderAllowed(de.hybris.platform.core.model.order.CartModel)}
	 */
	@Override
	public boolean isPlaceOrderAllowed(final CartModel cart)
	{
		//checking for order block on Account before place order
		final AmwayAccountModel amwayAccount = amwayAccountCommerceService.getCurrentAccount();
		return !getAccountOrderRestriction().evaluate(cart, amwayAccount);
	}



	/**
	 * {@link #adjustAmountInInfo(java.lang.String, de.hybris.platform.core.model.order.CartModel, de.hybris.platform.core.model.order.payment.PaymentInfoModel, java.lang.String, java.lang.String)}
	 */
	@Override
	public void adjustAmountInInfo(final String amount, final CartModel cartModel, final PaymentInfoModel paymentInfo,
			final String firstCCinstallMent, final String secondCCinstallMent)
	{
		final Map<String, Object> paymentDetails = AmwayCartHelper.getPaymentDetails(cartModel);
		final Set<PaymentInfoModel> paymentInfoSet = AmwayCartHelper.getPaymentInfoList(cartModel);

		if (paymentDetails.isEmpty())
		{
			paymentDetails.put(paymentInfo.getPk().toString(), getNewPaymenInfoMap(paymentInfo, amount, firstCCinstallMent));
			cartModel.setPaymentDetails(paymentDetails);
		}
		else
		{
			// get total entered amount
			final Double enteredAmount = AmwayCartHelper.getTotalPaidAmount(cartModel);

			// entered + amount = total
			final double total = enteredAmount.doubleValue() + Double.valueOf(amount).doubleValue();

			// cartTotal - total = difference
			double diff = cartModel.getTotalPrice().doubleValue() - total;
			diff = Math.round(diff * 100.0) / 100.0;


			for (final PaymentInfoModel info : cartModel.getPaymentInfos())
			{
				if (info instanceof AmwayMonetaryPaymentInfoModel || paymentInfo.getDuplicate().booleanValue())
				{
					continue;
				}
				if (cartModel.getTotalPrice() != enteredAmount && !amount.equals("0"))
				{
					final Map<String, String> paymentDetailsOfFirstCard = new HashMap<String, String>(
							(Map<String, String>) paymentDetails.get(info.getPk().toString()));

					final double finalTotalVal =
							Double.valueOf(paymentDetailsOfFirstCard.get(AmwaycoreConstants.PaymentDetailsMap.AMOUNT)).doubleValue()
									+ diff;
					final String finalTotal = String.format("%.2f", Double.valueOf(finalTotalVal));
					paymentDetailsOfFirstCard.put(AmwaycoreConstants.PaymentDetailsMap.AMOUNT, String.valueOf(finalTotal));
					if (info instanceof CreditCardPaymentInfoModel)
					{
						paymentDetailsOfFirstCard.put(AmwaycoreConstants.PaymentDetailsMap.CC_INSTALLMENTS,
								secondCCinstallMent != StringUtils.EMPTY ? firstCCinstallMent : "1");
						paymentDetailsOfFirstCard.put(AmwaycoreConstants.PaymentDetailsMap.CC_CPFNUMBER,
								paymentDetailsOfFirstCard.get(AmwaycoreConstants.PaymentDetailsMap.CC_CPFNUMBER));
					}

					paymentDetails.put(info.getPk().toString(), paymentDetailsOfFirstCard);

					paymentDetails.put(paymentInfo.getPk().toString(),
							getNewPaymenInfoMap(paymentInfo, amount.equals("0") ? String.valueOf(diff) : amount,
									secondCCinstallMent != StringUtils.EMPTY ? secondCCinstallMent : firstCCinstallMent));
					cartModel.setPaymentDetails(paymentDetails);
					break;
				}
			}
			if (!cartModel.getPaymentInfos().contains(paymentInfo))
			{
				paymentDetails.put(paymentInfo.getPk().toString(),
						getNewPaymenInfoMap(paymentInfo, amount.equals("0") ? String.valueOf(diff) : amount,
								secondCCinstallMent != StringUtils.EMPTY ? secondCCinstallMent : firstCCinstallMent));
				cartModel.setPaymentDetails(paymentDetails);
			}
		}
		paymentInfoSet.add(paymentInfo);
		cartModel.setPaymentInfos(paymentInfoSet);
		cartModel.setPaymentInfo(paymentInfo);
		getModelService().save(cartModel);
		getModelService().refresh(cartModel);
	}

	private Map<String, String> getNewPaymenInfoMap(final PaymentInfoModel paymentInfo, final String amount2,
			final String installment)
	{
		final Map<String, String> paymentDetails = new HashMap<String, String>();
		if (paymentInfo instanceof CreditCardPaymentInfoModel)
		{
			paymentDetails.put(AmwaycoreConstants.PaymentDetailsMap.CC_CPFNUMBER,
					((CreditCardPaymentInfoModel) paymentInfo).getCustomerTaxId());
			paymentDetails.put(AmwaycoreConstants.PaymentDetailsMap.CC_INSTALLMENTS, installment);
		}
		paymentDetails.put(AmwaycoreConstants.PaymentDetailsMap.AMOUNT, amount2);

		return paymentDetails;
	}


	/**
	 * @return amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService the amwayAccountCommerceService to set
	 */
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return accountOrderRestriction
	 */
	public AccountOrderRestriction getAccountOrderRestriction()
	{
		return accountOrderRestriction;
	}

	/**
	 * @param accountOrderRestriction the accountOrderRestriction to set
	 */
	public void setAccountOrderRestriction(final AccountOrderRestriction accountOrderRestriction)
	{
		this.accountOrderRestriction = accountOrderRestriction;
	}

	/**
	 * @return sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}


}
