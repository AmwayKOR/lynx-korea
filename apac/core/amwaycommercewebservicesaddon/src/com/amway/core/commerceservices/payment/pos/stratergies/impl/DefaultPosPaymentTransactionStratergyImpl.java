package com.amway.core.commerceservices.payment.pos.stratergies.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.cart.data.CCPosPaymentInfoData;
import com.amway.core.cart.data.CashPosPaymentInfoData;
import com.amway.core.cart.data.DCPosPaymentInfoData;
import com.amway.core.cart.data.PosPaymentInfoData;
import com.amway.core.commerceservices.payment.pos.stratergies.PosPaymentTransactionStratergy;
import com.amway.core.model.AmwayCashPaymentInfoModel;
import com.amway.core.model.AmwayDebitCardPaymentInfoModel;
import com.amway.core.util.AmwayDateHelper;


/**
 * 
 * Default implementation for pos payment transaction strategy
 *
 */
public class DefaultPosPaymentTransactionStratergyImpl implements PosPaymentTransactionStratergy
{
	private static final Logger LOG = Logger.getLogger(DefaultPosPaymentTransactionStratergyImpl.class);

	private final SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	private ModelService modelService;
	private ConfigurationService configurationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.amway.core.commerceservices.payment.pos.stratergies.PosPaymentTransactionStratergy#savePaymentTransaction(
	 * de.hybris.platform.core.model.user.CustomerModel, de.hybris.platform.core.model.order.OrderModel, java.util.List)
	 */
	@Override
	public PaymentTransactionModel savePaymentTransaction(final CustomerModel customerModel, final AbstractOrderModel orderModel,
			final PosPaymentInfoData paymentInfoData)
	{
		validateParameterNotNull(paymentInfoData, "paymentInfoData cannot be null");
		validateParameterNotNull(paymentInfoData.getTransactionid(), "transaction id for payment info cannot be null");
		validateParameterNotNull(paymentInfoData.getAmount(), "amount for payment info cannot be null");

		final PaymentTransactionModel transaction = getModelService().create(PaymentTransactionModel.class);

		final PaymentTransactionType paymentTransactionType = PaymentTransactionType.EXTERNAL_CAPTURE;
		final String code =  (customerModel.getUid() + "_" + UUID.randomUUID());
		transaction.setCode(code);
		transaction.setOrder(orderModel);
		transaction.setCurrency(orderModel.getCurrency());
		transaction.setPlannedAmount(BigDecimal.valueOf(paymentInfoData.getAmount().doubleValue()));
		transaction.setRequestId(paymentInfoData.getTransactionid());
		transaction.setRequestToken(paymentInfoData.getRequesttoken());
		transaction.setPaymentProvider(getConfigurationService().getConfiguration().getString("lynx.pos.paymentprovider",
				"microsega"));
		getModelService().save(transaction);

		final PaymentTransactionEntryModel transactionEntry = getModelService().create(PaymentTransactionEntryModel.class);
		transactionEntry.setCode(code);
		transactionEntry.setType(paymentTransactionType);
		transactionEntry.setRequestId(paymentInfoData.getTransactionid());
		transactionEntry.setRequestToken(paymentInfoData.getRequesttoken());
		transactionEntry.setTime(new Date());
		transactionEntry.setPaymentTransaction(transaction);
		transactionEntry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		transactionEntry.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL.name());
		transactionEntry.setCurrency(orderModel.getCurrency());
		transactionEntry.setAmount(BigDecimal.valueOf(paymentInfoData.getAmount().doubleValue()));
		getModelService().save(transactionEntry);
		final Calendar cal = AmwayDateHelper.getCalendarForSiteTimeZone();
		//credit card or debit card
		if (paymentInfoData instanceof CCPosPaymentInfoData || paymentInfoData instanceof DCPosPaymentInfoData)
		{
			final CreditCardPaymentInfoModel ccPaymentInfoModel = getModelService().create(
					paymentInfoData instanceof DCPosPaymentInfoData ? AmwayDebitCardPaymentInfoModel.class
							: CreditCardPaymentInfoModel.class);
			Date todate = new Date();
			Date fromdate = new Date();
			try
			{
				todate = DATEFORMATTER.parse(((CCPosPaymentInfoData) paymentInfoData).getValidthru());
				fromdate = DATEFORMATTER.parse(((CCPosPaymentInfoData) paymentInfoData).getValidfrom());
			}
			catch (final ParseException e)
			{
				LOG.error(e.getMessage());
			}
			cal.setTime(todate);
			ccPaymentInfoModel.setValidToMonth(String.valueOf(cal.get(Calendar.MONTH)));
			ccPaymentInfoModel.setValidToYear(String.valueOf(cal.get(Calendar.YEAR)));
			cal.setTime(fromdate);
			ccPaymentInfoModel.setValidFromMonth(String.valueOf(cal.get(Calendar.MONTH)));
			ccPaymentInfoModel.setValidFromYear(String.valueOf(cal.get(Calendar.YEAR)));
			ccPaymentInfoModel.setCcOwner(customerModel.getName());
			ccPaymentInfoModel.setUser(customerModel);
			String maskedcardnumber = ((CCPosPaymentInfoData) paymentInfoData).getMaskedcardnumber();
			maskedcardnumber = StringUtils.length(maskedcardnumber) < 13 ? StringUtils.leftPad(maskedcardnumber,
					13 - StringUtils.length(maskedcardnumber), "*") : maskedcardnumber;
			ccPaymentInfoModel.setNumber(maskedcardnumber);
			ccPaymentInfoModel.setType(CreditCardType.valueOf(((CCPosPaymentInfoData) paymentInfoData).getCardtype()));
			ccPaymentInfoModel.setCode(orderModel.getCode() + UUID.randomUUID().toString());
			ccPaymentInfoModel.setBillingAddress(orderModel.getPaymentAddress());
			getModelService().save(ccPaymentInfoModel);

			final Set<PaymentInfoModel> paymentInfoList = new LinkedHashSet<PaymentInfoModel>(orderModel.getPaymentInfos());
			paymentInfoList.add(ccPaymentInfoModel);
			orderModel.setPaymentInfos(paymentInfoList);
			orderModel.setPaymentInfo(ccPaymentInfoModel);
			orderModel.setPaymentStatus(PaymentStatus.PAID);
			getModelService().save(orderModel);

			transaction.setInfo(ccPaymentInfoModel);
			getModelService().save(transaction);
		}
		else if (paymentInfoData instanceof CashPosPaymentInfoData)
		{
			final AmwayCashPaymentInfoModel cashPaymentInfoModel = getModelService().create(AmwayCashPaymentInfoModel.class);
			cashPaymentInfoModel.setUser(customerModel);
			cashPaymentInfoModel.setCode(orderModel.getCode() + UUID.randomUUID().toString());
			cashPaymentInfoModel.setBillingAddress(orderModel.getPaymentAddress());
			getModelService().save(cashPaymentInfoModel);

			orderModel.setPaymentInfo(cashPaymentInfoModel);

			final Set<PaymentInfoModel> paymentInfoList = new LinkedHashSet<PaymentInfoModel>(orderModel.getPaymentInfos());
			paymentInfoList.add(cashPaymentInfoModel);
			orderModel.setPaymentInfos(paymentInfoList);
			orderModel.setPaymentInfo(cashPaymentInfoModel);
			orderModel.setPaymentStatus(PaymentStatus.PAID);
			getModelService().save(orderModel);

			transaction.setInfo(cashPaymentInfoModel);
			getModelService().save(transaction);
		}

		return transaction;
	}

	/**
	 * 
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * 
	 * @param modelService
	 *the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService
	 *         the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

}
