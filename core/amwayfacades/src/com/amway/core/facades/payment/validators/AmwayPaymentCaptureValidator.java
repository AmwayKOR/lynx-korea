/**
 *
 */
package com.amway.core.facades.payment.validators;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.order.data.AmwayPaymentModeData;


/**
 * Validator interface to validate the split payments
 *
 * @author mohit2496
 *
 */
public interface AmwayPaymentCaptureValidator
{

	/**
	 * Method to validate a split payment
	 *
	 * @param orderModel
	 * @param posPaymentInfoData
	 * @param errors
	 */
	void validate(final AbstractOrderModel orderModel, final AmwayPaymentInfoData posPaymentInfoData,
			Map<String, List<AmwayPaymentModeData>> supportedPaymentModeCombinations, final Errors errors);
}
