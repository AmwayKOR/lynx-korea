package com.amway.core.util;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayCreditPaymentInfoModel;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.order.data.AmwayPaymentModeData;

/**
 * Default Implementation
 */
public class AmwayCartHelper {
	private static final Logger LOG = Logger.getLogger(AmwayCartHelper.class);
	private static ApplicationContext context = null;
	private static SessionService sessionService = null;
	private static BaseSiteService baseSiteService = null;
	private static ModelService modelService = null;
	private static final String AMOUNT = "amount";
	private static final String CC_CPFNUMBER = "cpfNumber";
	private static final String CC_INSTALLMENTS = "installments";

	static {
		context = Registry.getApplicationContext();
		sessionService = (SessionService) context.getBean("sessionService");
		baseSiteService = (BaseSiteService) context.getBean("baseSiteService");
		modelService = (ModelService) context.getBean("modelService");
	}

	/**
	 * To check the cart type between web and webgroup
	 *
	 * @param orderModel
	 */
	public static void checkCartType(final AbstractOrderModel orderModel) {
		final SalesApplication currentChannel = (SalesApplication) JaloSession.getCurrentSession()
				.getAttribute("currentChannel");
		// as of now we have group order functionality from WEB
		if (SalesApplication.WEB.equals(currentChannel)) {
			final AmwayCartType oldCartType = orderModel.getType();
			if (AmwayCartType.WEBRECURRING.equals(oldCartType)) {
				LOG.debug("Ignoring cart type check for recurring order: " + orderModel.getCode());
				return;
			}

			// only if abo the switching is allowed.
			if (AmwayCustomerHelper.isABOCustomer()) {
				final AmwayCartType newCartType = hasEntryWithOtherVolumeABO(orderModel) ? AmwayCartType.WEBGROUP
						: AmwayCartType.WEB;

				// switching is only allowed between web and webgroup
				if (!newCartType.equals(oldCartType)) {
					LOG.info("Cart : " + orderModel.getCode() + " changed from " + oldCartType + " to " + newCartType
							+ " type.");
					orderModel.setType(newCartType);
					modelService.save(orderModel);
					modelService.refresh(orderModel);
				}
			}
		}
	}

	/**
	 * To get the list of payment infos.
	 *
	 * @param cartModel
	 * @return paymentInfoList
	 */
	public static Set<PaymentInfoModel> getPaymentInfoList(final CartModel cartModel) {
		final Set<PaymentInfoModel> paymentInfoList = new LinkedHashSet<>();
		final Set<PaymentInfoModel> cartPaymentInfoList = cartModel.getPaymentInfos();
		if (cartPaymentInfoList != null && CollectionUtils.isNotEmpty(cartPaymentInfoList)) {
			paymentInfoList.addAll(cartPaymentInfoList);
		}
		return paymentInfoList;
	}

	/**
	 * To set the payment info on cart model.
	 *
	 * @param cartModel
	 * @param paymentInfo
	 */
	public static void setSplitPaymentInfoList(final CartModel cartModel, final PaymentInfoModel paymentInfo) {
		final Set<PaymentInfoModel> paymentInfoList = getPaymentInfoList(cartModel);

		if (paymentInfoList != null) {
			paymentInfoList.add(paymentInfo);
		}
		cartModel.setPaymentInfos(paymentInfoList);
		modelService.save(cartModel);
		modelService.refresh(cartModel);
	}

	/**
	 * to get the list of payment info.
	 *
	 * @param abstractOrderModel
	 * @return paymentInfoList
	 */
	public static Set<PaymentInfoModel> getPaymentInfoList(final AbstractOrderModel abstractOrderModel) {
		final Set<PaymentInfoModel> paymentInfoList = new LinkedHashSet();
		final Set<PaymentInfoModel> cartPaymentInfoList = abstractOrderModel.getPaymentInfos();
		if (cartPaymentInfoList != null && CollectionUtils.isNotEmpty(cartPaymentInfoList)) {
			paymentInfoList.addAll(cartPaymentInfoList);
		}
		return paymentInfoList;
	}

	/**
	 * To get the list of payment transactions.
	 *
	 * @param abstractOrderModel
	 * @return transactions
	 */
	public static List<PaymentTransactionModel> getPaymentTransactionList(final AbstractOrderModel abstractOrderModel) {
		final List<PaymentTransactionModel> transactions = new ArrayList<PaymentTransactionModel>();
		final List<PaymentTransactionModel> savedTransactions = abstractOrderModel.getPaymentTransactions();
		if (savedTransactions != null && CollectionUtils.isNotEmpty(savedTransactions)) {
			transactions.addAll(savedTransactions);
		}
		return transactions;
	}

	/**
	 * This method defines to remove the AR payment info from cart.
	 *
	 * @param abstractOrderModel
	 */
	public static void removeARPaymentForWeb(final AbstractOrderModel abstractOrderModel) {
		for (final PaymentInfoModel paymentInfoModel : getPaymentInfoList(abstractOrderModel)) {
			if (paymentInfoModel instanceof AmwayCreditPaymentInfoModel
					|| paymentInfoModel instanceof AmwayMonetaryPaymentInfoModel) {
				modelService.remove(paymentInfoModel);
				LOG.info("AR Payment Info Remove : [ " + paymentInfoModel.getPk() + " ] from Cart : [ "
						+ abstractOrderModel.getCode() + " ]");
			}
		}
	}

	/**
	 * To get the payment details.
	 *
	 * @param cart
	 * @return creditCardDetails
	 */
	public static Map<String, Object> getPaymentDetails(final CartModel cart) {
		final Map<String, Object> creditCardDetails = new LinkedHashMap<>();
		final Map cartPaymentDetails = cart.getPaymentDetails();
		if (cartPaymentDetails != null && CollectionUtils.isNotEmpty(cartPaymentDetails.values())) {
			for (final PaymentInfoModel paymentInfoModel : getPaymentInfoList(cart)) {
				final String pk = paymentInfoModel.getPk().toString();
				if (cartPaymentDetails.get(pk) != null) {
					creditCardDetails.put(pk, cartPaymentDetails.get(pk));
				}
			}
		}
		return creditCardDetails;
	}

	/**
	 * To get the amount for payment.
	 *
	 * @param cart
	 * @param paymentInfoModel
	 * @return double
	 */
	public static double getAmountForPayment(final CartModel cart, final PaymentInfoModel paymentInfoModel) {
		final Map<String, Object> cartPaymentMap = getPaymentDetails(cart);
		if (cartPaymentMap.get(paymentInfoModel.getPk().toString()) != null) {
			return Double.valueOf(((Map<String, String>) cartPaymentMap.get(paymentInfoModel.getPk().toString()))
					.get(AmwaycoreConstants.PaymentDetailsMap.AMOUNT)).doubleValue();
		}
		return 0;
	}

	/**
	 * to set payment mode is ARCREDIT.
	 *
	 * @param paymentModeDatas
	 * @return boolean
	 */
	public static boolean isARCreditOnPaymentModeList(final Set<AmwayPaymentModeData> paymentModeDatas) {
		for (final AmwayPaymentModeData modeData : paymentModeDatas) {
			if (StringUtils.equalsIgnoreCase(modeData.getCode(), AmwaycoreConstants.PaymentMode.ARCREDIT)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * to set payment mode is CREDITCARD.
	 *
	 * @param paymentModeDatas
	 * @return boolean
	 */
	public static boolean isCreditCardOnPaymentModeList(final Set<AmwayPaymentModeData> paymentModeDatas) {
		for (final AmwayPaymentModeData modeData : paymentModeDatas) {
			if (StringUtils.equalsIgnoreCase(AmwaycoreConstants.PaymentMode.CREDITCARD, modeData.getCode())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * to get the total paid amount from payment info.
	 *
	 * @param order
	 * @return double
	 */
	public static Double getTotalPaidAmount(final AbstractOrderModel order) {
		BigDecimal totalPaidAmount = BigDecimal.ZERO;
		final List<PaymentTransactionModel> paymentTransactionList = order.getPaymentTransactions();
		if (CollectionUtils.isNotEmpty(paymentTransactionList)) {
			for (final PaymentTransactionModel paymentTransaction : paymentTransactionList) {
				final Optional<PaymentTransactionEntryModel> cancelledTransactionEntry = paymentTransaction.getEntries()
						.stream().filter(pte -> PaymentTransactionType.CANCEL.equals(pte.getType())
								&& TransactionStatus.ACCEPTED.name().equals(pte.getTransactionStatus()))
						.findFirst();

				if (!cancelledTransactionEntry.isPresent()) {
					final Optional<PaymentTransactionEntryModel> capturedTransactionEntry = paymentTransaction
							.getEntries().stream()
							.filter(pte -> PaymentTransactionType.EXTERNAL_CAPTURE.equals(pte.getType())
									&& TransactionStatus.ACCEPTED.name().equals(pte.getTransactionStatus()))
							.findFirst();
					if (capturedTransactionEntry.isPresent()) {
						totalPaidAmount = totalPaidAmount.add(capturedTransactionEntry.get().getAmount());
					}
				}
			}
		}

		return Double.valueOf(totalPaidAmount.doubleValue());
	}
	
	/**
	 * To get the list of payment transactions.
	 *
	 * @param abstractOrderModel
	 * @return transactions
	 */
	public static List<PaymentTransactionModel> getCapturedTransactions(final AbstractOrderModel abstractOrderModel)
	{
		final List<PaymentTransactionModel> capturedTransactions = new ArrayList<PaymentTransactionModel>();
		final List<PaymentTransactionModel> paymentTransactionList = abstractOrderModel.getPaymentTransactions();
		if (CollectionUtils.isNotEmpty(paymentTransactionList))
		{
			for (final PaymentTransactionModel paymentTransaction : paymentTransactionList) {
				final Optional<PaymentTransactionEntryModel> cancelledTransactionEntry = paymentTransaction.getEntries()
						.stream().filter(pte -> PaymentTransactionType.CANCEL.equals(pte.getType())
								&& TransactionStatus.ACCEPTED.name().equals(pte.getTransactionStatus()))
						.findFirst();

				if (!cancelledTransactionEntry.isPresent()) {
					final Optional<PaymentTransactionEntryModel> capturedTransactionEntry = paymentTransaction
							.getEntries().stream()
							.filter(pte -> PaymentTransactionType.EXTERNAL_CAPTURE.equals(pte.getType())
									&& TransactionStatus.ACCEPTED.name().equals(pte.getTransactionStatus()))
							.findFirst();
					if (capturedTransactionEntry.isPresent()) {
						capturedTransactions.add(capturedTransactionEntry.get().getPaymentTransaction());
					}
				}
			}
		}
		return capturedTransactions;
	}

	/**
	 * Method to get the overpay return amount from the order refund
	 * transactions.
	 * 
	 * @param order
	 *            the abstract order
	 * @return the overpay return amount
	 */
	public static BigDecimal getOverpayReturnAmount(final AbstractOrderModel order) {
		final BigDecimal totalOverpayReturnAmount = BigDecimal.ZERO;
		final List<PaymentTransactionModel> refundTransactionList = order.getRefundTransactions();
		if (CollectionUtils.isNotEmpty(refundTransactionList)) {
			order.getRefundTransactions().stream().map(PaymentTransactionModel::getEntries).flatMap(List::stream)
					.filter(entry -> PaymentTransactionType.OVERPAY_RETURN == entry.getType())
					.map(PaymentTransactionEntryModel::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return totalOverpayReturnAmount;
	}

	/**
	 * Compares cart total price with total paid amount.
	 *
	 * @param cart
	 * @return boolean
	 */
	public static boolean isMapAmountDifferentWithCartTotal(final CartModel cart) {
		return getCartTotalPayablePrice(cart).compareTo(BigDecimal.valueOf(getTotalPaidAmount(cart))) != 0 && getPaymentInfoList(cart).size() > 0;
	}

	/**
	 * To get the balance amount.
	 *
	 * @param abstractOrderModel
	 * @return double
	 */
	public static Double getBalanceAmount(final AbstractOrderModel abstractOrderModel) {
		return Double.valueOf((getCartTotalPayablePrice(abstractOrderModel)
				.subtract(BigDecimal.valueOf(getTotalPaidAmount(abstractOrderModel).doubleValue()))).doubleValue());
	}

	/**
	 * adding bopis order in amway cart type.
	 *
	 * @param abstractOrderModel
	 * @return boolean
	 */
	public static boolean isBopisOrder(final AbstractOrderModel abstractOrderModel) {
		// ADDING BOPIS IN AMWAYCARTTYPE would be best solution
		if (!AmwayCartType.POS.equals(abstractOrderModel.getType())) {
			for (final AbstractOrderEntryModel orderEntryModel : abstractOrderModel.getEntries()) {
				if (orderEntryModel.getDeliveryPointOfService() != null) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean hasEntryWithOtherVolumeABO(final AbstractOrderModel orderModel) {
		final List<AbstractOrderEntryModel> entries = orderModel.getEntries();

		if (entries.isEmpty()) {
			return false;
		}

		final String currentAccountNumber = getCurrentAccountNumber();

		for (final AbstractOrderEntryModel entry : entries) {
			// check only if set
			if (entry.getVolumeAbo() != null) {
				if (!entry.getVolumeAbo().equals(currentAccountNumber)) {
					return true;
				}
			}

		}

		return false;
	}

	private static String getCurrentAccountNumber() {
		final AmwayAccountModel account = sessionService.getAttribute(AmwaycoreConstants.SessionVariables.ACCOUNT);
		if (account != null) {
			return account.getCode();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * this method defines for to create the cancel transaction.
	 *
	 * @param cartModel
	 */
	public static void createCancelTransction(final AbstractOrderModel cartModel) {
		for (final PaymentTransactionModel transaction : cartModel.getPaymentTransactions()) {
			for (final PaymentTransactionEntryModel pte : transaction.getEntries()) {
				if (pte.getType().equals(PaymentTransactionType.AUTHORIZATION)
						&& pte.getTransactionStatus().equals(TransactionStatus.ACCEPTED.name())) {
					final PaymentTransactionEntryModel paymentTransactionEntryModel = modelService.clone(pte);
					paymentTransactionEntryModel.setType(PaymentTransactionType.CANCEL);
					modelService.save(paymentTransactionEntryModel);
				}
			}
		}
	}

	/**
	 * Checks the validation of transaction
	 *
	 * @param paymentTransaction
	 * @return boolean
	 */
	public static boolean isValidTransction(final PaymentTransactionModel paymentTransaction) {
		for (final PaymentTransactionEntryModel pte : paymentTransaction.getEntries()) {
			if (!(pte.getType().equals(PaymentTransactionType.AUTHORIZATION)
					&& pte.getTransactionStatus().equals(TransactionStatus.ACCEPTED.name()))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to get cart total price
	 *
	 * @param cart
	 * @return totalprice the cart total price
	 */
	public static BigDecimal getCartTotalPayablePrice(final AbstractOrderModel cart) {
		BigDecimal totalPrice = new BigDecimal(0);
		if (null != cart.getTotalPrice()) {
			totalPrice = BigDecimal.valueOf(cart.getTotalPrice().doubleValue());
		}

		if (null != cart.getTotalTax()) {
			totalPrice = BigDecimal.valueOf(totalPrice.doubleValue() + cart.getTotalTax().doubleValue());
		}

		if (null != cart.getDeliveryTax()) {
			totalPrice = totalPrice.add(new BigDecimal(cart.getDeliveryTax().doubleValue()));
		}
		return totalPrice;
	}

}
