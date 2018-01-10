/**
 *
 */
package com.amway.core.payment.strategies.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.cart.data.AmwayCCPaymentInfoData;
import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.util.AmwayDateHelper;

/**
 * Implementation of {@link AbstractAmwayCreatePaymentInfoStrategy}. Creates the
 * CC payment info.
 *
 * @author mohit2496
 *
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class AmwayCreateCCPaymentInfoStrategy extends AbstractAmwayCreatePaymentInfoStrategy {

	private static final Logger LOG = Logger.getLogger(AmwayCreateCCPaymentInfoStrategy.class);

	private static final SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public PaymentInfoModel createPaymentInfoEntry(final PaymentTransactionModel transaction, final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData) {

		final CreditCardPaymentInfoModel ccPaymentInfoModel = getModelService()
				.create(CreditCardPaymentInfoModel.class);

		ccPaymentInfoModel.setCode(orderModel.getCode() + UUID.randomUUID().toString());
		if (((AmwayCCPaymentInfoData) paymentInfoData).getCardtype() != null) {
			try {
				ccPaymentInfoModel
						.setType(CreditCardType.valueOf(((AmwayCCPaymentInfoData) paymentInfoData).getCardtype()));
			} catch (final IllegalArgumentException iAE) {
				LOG.error("Illegal Card type found in the cc payment info data received for transactiuon id : "
						+ paymentInfoData.getTransactionid(), iAE);
			}
		} else {
			LOG.error("No Card type found in the cc payment info data received for transactiuon id:"
					+ paymentInfoData.getTransactionid());
		}
		ccPaymentInfoModel.setCcOwner(userModel.getName());
		ccPaymentInfoModel.setUser(userModel);
		setCardNumber(paymentInfoData, ccPaymentInfoModel);

		ccPaymentInfoModel.setBillingAddress(orderModel.getPaymentAddress());
		setCardToFromDates(paymentInfoData, ccPaymentInfoModel);

		getModelService().save(ccPaymentInfoModel);
		return ccPaymentInfoModel;

	}

	/**
	 * method to set the card number
	 *
	 * @param paymentInfoData
	 *            the payment info data
	 * @param ccPaymentInfoModel
	 *            the cc payment info model
	 */
	private void setCardNumber(final AmwayPaymentInfoData paymentInfoData,
			final CreditCardPaymentInfoModel ccPaymentInfoModel) {
		String maskedcardnumber = ((AmwayCCPaymentInfoData) paymentInfoData).getMaskedcardnumber();
		maskedcardnumber = StringUtils.length(maskedcardnumber) < 13
				? StringUtils.leftPad(maskedcardnumber, 13 - StringUtils.length(maskedcardnumber), "*")
				: maskedcardnumber;
		ccPaymentInfoModel.setNumber(maskedcardnumber);
	}

	/**
	 * Method to set the card to and from dates.
	 *
	 * @param paymentInfoData
	 *            the payment info data
	 * @param ccPaymentInfoModel
	 *            the cc payment info model
	 */
	private void setCardToFromDates(final AmwayPaymentInfoData paymentInfoData,
			final CreditCardPaymentInfoModel ccPaymentInfoModel) {
		final Calendar calendar = AmwayDateHelper.getCalendarForSiteTimeZone();
		Date todate = new Date();
		Date fromdate = new Date();
		final AmwayCCPaymentInfoData ccPaymentInfoData = (AmwayCCPaymentInfoData) paymentInfoData;
		if (ccPaymentInfoData.getValidthru() != null) {
			try {
				todate = DATEFORMATTER.parse(ccPaymentInfoData.getValidthru());
			} catch (final ParseException e) {
				LOG.error(e.getMessage());
			}
		}

		if (ccPaymentInfoData.getValidfrom() != null) {
			try {
				fromdate = DATEFORMATTER.parse(ccPaymentInfoData.getValidfrom());
			} catch (final ParseException e) {
				LOG.error(e.getMessage());
			}
		}

		calendar.setTime(todate);
		ccPaymentInfoModel.setValidToMonth(String.valueOf(calendar.get(Calendar.MONTH)));
		ccPaymentInfoModel.setValidToYear(String.valueOf(calendar.get(Calendar.YEAR)));
		calendar.setTime(fromdate);
		ccPaymentInfoModel.setValidFromMonth(String.valueOf(calendar.get(Calendar.MONTH)));
		ccPaymentInfoModel.setValidFromYear(String.valueOf(calendar.get(Calendar.YEAR)));
	}

}
