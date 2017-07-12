package com.amway.core.payment.service.impl;


import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.model.AmwayCreditPaymentInfoModel;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.payment.service.AmwayPaymentService;
import com.amway.core.refund.amount.strategies.AmwayRefundAmountCalculationStrategy;


/**
 * Default service for amway payment.
 */
public class DefaultAmwayPaymentService extends DefaultPaymentServiceImpl implements AmwayPaymentService
{
	private final Logger LOG = Logger.getLogger(DefaultAmwayPaymentService.class.getName());
	private AmwayAccountBalanceService amwayAccountBalanceService;
	private ModelService modelService;
	private AmwayRefundAmountCalculationStrategy amwayRefundAmountCalculationStrategy;
	private SessionService sessionService;

	private final String AMOUNT = "amount";
	private final String CC_CPFNUMBER = "cpfNumber";
	private final String CC_INSTALLMENTS = "installments";
	private final String DEFAULT_INSTALLMENT = "1";

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
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return amwayRefundAmountCalculationStrategy
	 */
	public AmwayRefundAmountCalculationStrategy getAmwayRefundAmountCalculationStrategy()
	{
		return amwayRefundAmountCalculationStrategy;
	}

	/**
	 * @param amwayRefundAmountCalculationStrategy the amwayRefundAmountCalculationStrategy to set
	 */
	public void setAmwayRefundAmountCalculationStrategy(
			final AmwayRefundAmountCalculationStrategy amwayRefundAmountCalculationStrategy)
	{
		this.amwayRefundAmountCalculationStrategy = amwayRefundAmountCalculationStrategy;
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

	/**
	 * This method for to capture the payment transaction entry.
	 *
	 * @param transaction
	 * @throws AdapterException
	 */
	@Override
	public PaymentTransactionEntryModel capture(final PaymentTransactionModel transaction) throws AdapterException
	{
		final PaymentInfoModel paymentInfoModel = transaction.getInfo();
		if (paymentInfoModel instanceof AmwayCreditPaymentInfoModel || paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel)
		{
			PaymentTransactionEntryModel auth = null;
			for (final PaymentTransactionEntryModel pte : transaction.getEntries())
			{
				if (!(pte.getType().equals(PaymentTransactionType.AUTHORIZATION)))
				{
					continue;
				}
				auth = pte;
				break;
			}

			if (auth == null)
			{
				throw new AdapterException("Could not capture without authorization");
			}

			final CommonResponseFieldsData response = getAmwayAccountBalanceService()
					.debitAccountBalance(transaction.getOrder(), auth.getAmount(), auth.getRequestToken());

			final PaymentTransactionType transactionType = PaymentTransactionType.CAPTURE;
			final String newEntryCode = getNewPaymentTransactionEntryCode(transaction, transactionType);

			final PaymentTransactionEntryModel paymentTransactionEntryModel = getModelService().clone(auth);
			paymentTransactionEntryModel.setCode(newEntryCode);
			paymentTransactionEntryModel.setTime(new Date());
			if (response.getReturnCd() == 1)
			{
				paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
				paymentTransactionEntryModel.setTransactionStatusDetails("SUCCESFULL");
			}
			else
			{
				LOG.error("Unable to debit AR Account for cart " + transaction.getOrder().getCode());
				paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.REJECTED.name());
				paymentTransactionEntryModel.setTransactionStatusDetails("ERROR -" + response.getReturnCd());
			}
			paymentTransactionEntryModel.setType(transactionType);
			paymentTransactionEntryModel.setPaymentTransaction(transaction);
			paymentTransactionEntryModel.setRequestToken(auth.getRequestToken());
			getModelService().save(paymentTransactionEntryModel);
			getModelService().refresh(transaction);

			return paymentTransactionEntryModel;
		}
		else
		{
			LOG.info("Passing capture request to OOB payment service");
			return super.capture(transaction);
		}
	}

	/**
	 * This method is for to cancel the payment transaction entry.
	 *
	 * @param transactionEntryModel
	 * @throws AdapterException
	 */
	@Override
	public PaymentTransactionEntryModel cancel(final PaymentTransactionEntryModel transactionEntryModel) throws AdapterException
	{
		final PaymentTransactionModel paymentTransactionModel = transactionEntryModel.getPaymentTransaction();
		if (paymentTransactionModel != null && (paymentTransactionModel.getInfo() instanceof AmwayCreditPaymentInfoModel
				|| paymentTransactionModel.getInfo() instanceof AmwayMonetaryPaymentInfoModel))
		{
			final PaymentTransactionType transactionType = PaymentTransactionType.CANCEL;
			final String newEntryCode = getNewPaymentTransactionEntryCode(paymentTransactionModel, transactionType);

			LOG.info("Performing " + transactionType.getCode() + " against code " + transactionEntryModel.getCode());
			final CommonResponseFieldsData response = getAmwayAccountBalanceService()
					.creditAccountBalance(transactionEntryModel, transactionEntryModel.getRequestToken(),
							paymentTransactionModel.getOrder().getCode());

			final PaymentTransactionEntryModel paymentTransactionEntryModel = getModelService().clone(transactionEntryModel);
			paymentTransactionEntryModel.setCode(newEntryCode);
			paymentTransactionEntryModel.setTime(new Date());
			if (response.getReturnCd() == 1)
			{
				paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
				paymentTransactionEntryModel
						.setTransactionStatusDetails("SUCCESFULL - " + response.getReturnMessage() + " - " + response.getReturnCd());
			}
			else
			{
				LOG.error("Unable to debit AR Account for cart " + paymentTransactionModel.getOrder().getCode());
				paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.REJECTED.name());
				paymentTransactionEntryModel
						.setTransactionStatusDetails("ERROR - " + response.getReturnMessage() + " - " + response.getReturnCd());
			}
			paymentTransactionEntryModel.setType(transactionType);
			paymentTransactionEntryModel.setPaymentTransaction(paymentTransactionModel);
			paymentTransactionEntryModel.setRequestToken(paymentTransactionEntryModel.getRequestToken());
			getModelService().save(paymentTransactionEntryModel);
			getModelService().refresh(paymentTransactionModel);
			return paymentTransactionEntryModel;
		}
		else
		{
			return super.cancel(transactionEntryModel);
		}
	}

	/**
	 * Refund for transaction entry.
	 *
	 * @param paymentTransactionModel
	 * @param amount
	 * @throws AdapterException
	 */
	@Override
	public PaymentTransactionEntryModel refundFollowOn(final PaymentTransactionModel paymentTransactionModel,
			final BigDecimal amount) throws AdapterException
	{
		if (paymentTransactionModel != null && (paymentTransactionModel.getInfo() instanceof AmwayCreditPaymentInfoModel
				|| paymentTransactionModel.getInfo() instanceof AmwayMonetaryPaymentInfoModel))
		{
			PaymentTransactionEntryModel captureTxnEntryModel = null;
			for (final PaymentTransactionEntryModel pte : paymentTransactionModel.getEntries())
			{
				if (pte.getType().equals(PaymentTransactionType.AUTHORIZATION) && pte.getTransactionStatus()
						.equals(TransactionStatus.ACCEPTED.name()))
				{
					captureTxnEntryModel = pte;
					break;
				}
			}
			if (captureTxnEntryModel == null)
			{
				throw new AdapterException("Could not refund without capture");
			}

			final PaymentTransactionType transactionType = PaymentTransactionType.REFUND_FOLLOW_ON;
			final String newEntryCode = getNewPaymentTransactionEntryCode(paymentTransactionModel, transactionType);

			LOG.info("Performing " + transactionType.getCode() + " against code " + captureTxnEntryModel.getCode());
			LOG.info("Refunding amount " + amount + " against order " + paymentTransactionModel.getOrder().getCode()
					+ " at payment provider " + paymentTransactionModel.getPaymentProvider());

			final PaymentTransactionEntryModel refundTransactionEntryModel = getModelService().clone(captureTxnEntryModel);
			refundTransactionEntryModel.setCode(newEntryCode);
			refundTransactionEntryModel.setAmount(amount);
			refundTransactionEntryModel.setType(transactionType);
			refundTransactionEntryModel.setPaymentTransaction(paymentTransactionModel);
			refundTransactionEntryModel.setRequestToken(captureTxnEntryModel.getRequestToken());
			refundTransactionEntryModel.setTime(new Date());

			final CommonResponseFieldsData response = getAmwayAccountBalanceService()
					.creditAccountBalance(refundTransactionEntryModel, captureTxnEntryModel.getRequestToken(),
							paymentTransactionModel.getOrder().getCode());


			if (response.getReturnCd() == 1)
			{
				refundTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
				refundTransactionEntryModel
						.setTransactionStatusDetails("SUCCESFULL - " + response.getReturnMessage() + " - " + response.getReturnCd());
				LOG.info("Refund is successful for transaction entry " + captureTxnEntryModel.getCode());
			}
			else
			{
				LOG.error("Unable to debit AR Account for cart " + paymentTransactionModel.getOrder().getCode());
				refundTransactionEntryModel.setTransactionStatus(TransactionStatus.REJECTED.name());
				refundTransactionEntryModel
						.setTransactionStatusDetails("ERROR - " + response.getReturnMessage() + " - " + response.getReturnCd());
			}
			getModelService().save(refundTransactionEntryModel);
			getModelService().refresh(paymentTransactionModel);

			return refundTransactionEntryModel;
		}
		else
		{
			return super.refundFollowOn(paymentTransactionModel, amount);
		}
	}

	/**
	 * Refund to AR credit.
	 *
	 * @param returnRequestModel
	 * @throws AdapterException
	 */
	@Override
	public PaymentTransactionEntryModel refundFollowOn(final ReturnRequestModel returnRequestModel) throws AdapterException
	{
		final OrderModel originalOrder = returnRequestModel.getOrder();
		final Double refundAmount = getAmwayRefundAmountCalculationStrategy().calculateRefundAmount(returnRequestModel);
		PaymentTransactionModel arPaymentTxnModel = null;
		for (final PaymentTransactionModel paymentTransactionModel : originalOrder.getPaymentTransactions())
		{
			final PaymentInfoModel paymentInfoModel = paymentTransactionModel.getInfo();
			if (paymentInfoModel != null && paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel)
			{
				arPaymentTxnModel = paymentTransactionModel;
				break;
			}
		}

		if (arPaymentTxnModel == null)
		{
			final AmwayMonetaryPaymentInfoModel arCreditPaymentInfoModel = getModelService()
					.create(AmwayMonetaryPaymentInfoModel.class);
			arCreditPaymentInfoModel.setCode(UUID.randomUUID().toString());
			arCreditPaymentInfoModel.setUser(originalOrder.getUser());
			arCreditPaymentInfoModel.setBillingAddress(originalOrder.getPaymentAddress());
			arCreditPaymentInfoModel.setOwner(originalOrder);
			getModelService().save(arCreditPaymentInfoModel);

			arPaymentTxnModel = getModelService().create(PaymentTransactionModel.class);
			arPaymentTxnModel.setOrder(originalOrder);
			arPaymentTxnModel.setCode(UUID.randomUUID().toString());
			arPaymentTxnModel.setCurrency(originalOrder.getCurrency());
			arPaymentTxnModel.setInfo(arCreditPaymentInfoModel);
			arPaymentTxnModel.setPaymentProvider("Magic");
			getModelService().save(arPaymentTxnModel);

		}

		final PaymentTransactionType transactionType = PaymentTransactionType.REFUND_FOLLOW_ON;
		final String newEntryCode = getNewPaymentTransactionEntryCode(arPaymentTxnModel, transactionType);

		final PaymentTransactionEntryModel refundTransactionEntryModel = getModelService()
				.create(PaymentTransactionEntryModel.class);
		refundTransactionEntryModel.setAmount(BigDecimal.valueOf(refundAmount.doubleValue()));
		refundTransactionEntryModel.setCode(newEntryCode);
		refundTransactionEntryModel.setPaymentTransaction(arPaymentTxnModel);
		refundTransactionEntryModel.setCurrency(arPaymentTxnModel.getCurrency());
		refundTransactionEntryModel.setRequestToken(AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY);
		refundTransactionEntryModel.setType(transactionType);
		refundTransactionEntryModel.setTime(new Date());

		final CommonResponseFieldsData response = getAmwayAccountBalanceService()
				.creditAccountBalance(refundTransactionEntryModel, refundTransactionEntryModel.getRequestToken(),
						returnRequestModel.getRMA());

		if (response.getReturnCd() == 1)
		{
			refundTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			refundTransactionEntryModel
					.setTransactionStatusDetails("SUCCESFULL - " + response.getReturnMessage() + " - " + response.getReturnCd());
			LOG.info("Refund to AR is successful for RMA " + returnRequestModel.getRMA());
		}
		else if (response.getReturnCd() == -1)
		{
			LOG.error("Unable to Refund AR Credit for return " + returnRequestModel.getRMA());
			refundTransactionEntryModel.setTransactionStatus(TransactionStatus.REJECTED.name());
			refundTransactionEntryModel
					.setTransactionStatusDetails("ERROR - " + response.getReturnMessage() + " - " + response.getReturnCd());
		}

		getModelService().save(refundTransactionEntryModel);
		return refundTransactionEntryModel;
	}

	/**
	 * amount adjustment on cart total.
	 *
	 * @param cart
	 */
	@Override
	public boolean adjustPaymentAmount(final CartModel cart)
	{
		final BigDecimal cartTotal = BigDecimal.valueOf(cart.getTotalPrice());
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal diffAmountVal = BigDecimal.ZERO;

		String latestPaymentInfoId = null;
		final Set<PaymentInfoModel> paymentInfoModels = getPaymentInfoList(cart);
		if (CollectionUtils.isEmpty(paymentInfoModels))
		{
			return false;
		}
		final Map<String, Object> paymentInfoMap = getModifiablePaymentDetails(cart);
		final Set<String> paymentInfoIds = paymentInfoMap.keySet();
		for (final String paymentInfoId : paymentInfoIds)
		{
			final Map<String, String> paymentInfoDetails = (Map<String, String>) paymentInfoMap.get(paymentInfoId);
			if (paymentInfoDetails != null && paymentInfoDetails.get(AMOUNT) != null)
			{
				totalAmount = totalAmount.add(new BigDecimal(paymentInfoDetails.get(AMOUNT)));
				latestPaymentInfoId = paymentInfoId;
			}
		}
		//if cart total != total amount...
		if (cartTotal.compareTo(totalAmount) != 0)
		{
			LOG.info("Cart Total " + cartTotal.toString() + " & Amount configured " + totalAmount.toString() + " doesn't match");
			if (!hasMultiPaymentMode(paymentInfoModels))
			{
				LOG.info("Single type of payment mode used. Auto adjusting the amount on Map");
				diffAmountVal = cartTotal.subtract(totalAmount);
				if (diffAmountVal.compareTo(BigDecimal.ZERO) > 0)
				{
					final Map<String, String> paymentInfoDetails = (Map<String, String>) paymentInfoMap.get(latestPaymentInfoId);
					paymentInfoDetails.put(AMOUNT, String.format("%.2f",
							diffAmountVal.add(new BigDecimal(paymentInfoDetails.get(AMOUNT)))));
					LOG.info("Cart total > Amount configured. Difference in amount: " + diffAmountVal.toString()
							+ " New adjusted amount on payment id " + latestPaymentInfoId + " is " + paymentInfoDetails.get(AMOUNT));
					cart.setPaymentDetails(paymentInfoMap);
					getModelService().save(cart);
				}
				else if (diffAmountVal.compareTo(BigDecimal.ZERO) < 0)
				{
					LOG.info("Cart total < Amount configured. Adjusting the amounts. Difference in amount: " + diffAmountVal.toString());
					final Set<String> removedPaymentInfoIds = resetPaymentInfoMap(paymentInfoMap, cartTotal.doubleValue());
					cart.setPaymentDetails(paymentInfoMap);
					getModelService().save(cart);
					resetPaymentInfoModels(cart, paymentInfoModels, removedPaymentInfoIds);
					LOG.info("Removed PaymentInfoIds " + removedPaymentInfoIds);
				}

				getModelService().refresh(cart);
				getSessionService().setAttribute("PAYMENT_ADJUSTED", Boolean.TRUE);
				return true;
			}
			else
			{
				getSessionService().setAttribute("PAYMENT_ADJUSTED", Boolean.FALSE);
			}
		}

		return false;
	}

	private Map<String, Object> getModifiablePaymentDetails(final CartModel cart)
	{
		final Map<String, Object> clonedPaymentDetailsMap = new LinkedHashMap<>();
		final Map<String, Map<String, String>> cartPaymentDetails = getPaymentDetails(cart);
		if (cartPaymentDetails != null && CollectionUtils.isNotEmpty(cartPaymentDetails.values()))
		{
			for (final Entry<String, Map<String, String>> entry : cartPaymentDetails.entrySet())
			{
				if (entry.getValue() != null)
				{
					clonedPaymentDetailsMap.put(entry.getKey(), new HashMap<String, String>(entry.getValue()));
				}
			}
		}
		return clonedPaymentDetailsMap;
	}

	private Map<String, Map<String, String>> getPaymentDetails(final CartModel cart)
	{
		final Map<String, Map<String, String>> creditCardDetails = new LinkedHashMap<>();
		final Map cartPaymentDetails = cart.getPaymentDetails();
		if (cartPaymentDetails != null && CollectionUtils.isNotEmpty(cartPaymentDetails.values()))
		{
			for (final PaymentInfoModel paymentInfoModel : getPaymentInfoList(cart))
			{
				final String pk = paymentInfoModel.getPk().toString();
				if (cartPaymentDetails.get(pk) != null)
				{
					creditCardDetails.put(pk, (Map<String, String>) cartPaymentDetails.get(pk));
				}
			}
		}
		return creditCardDetails;
	}

	/**
	 * To get the list of payment info.
	 *
	 * @param cartModel
	 * @return paymentInfoList
	 */
	public Set<PaymentInfoModel> getPaymentInfoList(final CartModel cartModel)
	{
		final Set<PaymentInfoModel> paymentInfoList = new LinkedHashSet<>();
		final Set<PaymentInfoModel> cartPaymentInfoList = cartModel.getPaymentInfos();
		if (cartPaymentInfoList != null && CollectionUtils.isNotEmpty(cartPaymentInfoList))
		{
			paymentInfoList.addAll(cartPaymentInfoList);
		}
		return paymentInfoList;
	}

	private boolean hasMultiPaymentMode(final Set<PaymentInfoModel> paymentInfoModels)
	{
		String itemType = null;
		for (final PaymentInfoModel paymentInfoModel : paymentInfoModels)
		{
			if (itemType == null)
			{
				itemType = paymentInfoModel.getItemtype();
			}
			else if (!paymentInfoModel.getItemtype().equals(itemType))
			{
				return true;
			}
			else
			{
				itemType = paymentInfoModel.getItemtype();
			}
		}

		return false;
	}

	private Set<String> resetPaymentInfoMap(final Map<String, Object> paymentInfoMap, final double cartTotal)
	{
		final Set<String> removedPaymentInfoIds = new HashSet();
		final Set<String> paymentInfoIds = paymentInfoMap.keySet();
		double configuredAmount = 0;
		double configuredTotalAmount = 0;
		double differenceInAmount = 0;
		for (final String paymentInfoId : paymentInfoIds)
		{
			final Map<String, String> paymentInfoDetails = (Map<String, String>) paymentInfoMap.get(paymentInfoId);
			if (paymentInfoDetails != null && paymentInfoDetails.get(AMOUNT) != null && configuredTotalAmount < cartTotal)
			{
				configuredAmount = Double.valueOf(paymentInfoDetails.get(AMOUNT)).doubleValue();
				differenceInAmount = cartTotal - configuredTotalAmount;
				if (configuredAmount < differenceInAmount)
				{
					configuredTotalAmount = configuredTotalAmount + Double.valueOf(paymentInfoDetails.get(AMOUNT)).doubleValue();
				}
				else
				{
					paymentInfoDetails.put(AMOUNT, String.format("%.2f", Double.valueOf(differenceInAmount)));
					configuredTotalAmount = configuredTotalAmount + Double.valueOf(differenceInAmount).doubleValue();
				}
				continue;
			}
			paymentInfoMap.remove(paymentInfoId);
			removedPaymentInfoIds.add(paymentInfoId);
		}
		return removedPaymentInfoIds;
	}

	private boolean resetPaymentInfoModels(final CartModel cartModel, final Set<PaymentInfoModel> paymentInfoModels,
			final Set<String> removedPaymentInfoIds)
	{
		if (CollectionUtils.isEmpty(removedPaymentInfoIds))
		{
			return false;
		}

		for (final PaymentInfoModel paymentInfoModel : paymentInfoModels)
		{
			if (removedPaymentInfoIds.contains(paymentInfoModel.getPk().toString()))
			{
				getModelService().remove(paymentInfoModel);
			}
		}

		getModelService().refresh(cartModel);
		final Set<PaymentInfoModel> finalPaymentInfos = getPaymentInfoList(cartModel);
		if (CollectionUtils.isNotEmpty(finalPaymentInfos))
		{
			final PaymentInfoModel paymentInfoModel = finalPaymentInfos.iterator().next();
			cartModel.setPaymentInfo(paymentInfoModel);
			cartModel.setPaymentAddress(paymentInfoModel.getBillingAddress() != null ?
					paymentInfoModel.getBillingAddress() :
					cartModel.getDeliveryAddress());
			LOG.info("Setting " + paymentInfoModel.getPk() + " as payment info for cart " + cartModel.getCode());
			getModelService().save(cartModel);
		}
		return true;
	}
}
