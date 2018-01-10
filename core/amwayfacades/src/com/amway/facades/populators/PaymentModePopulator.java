/**
 *
 */
package com.amway.facades.populators;

import com.amway.core.order.data.PaymentModeData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * Populator for Payment Mode.
 *
 * @param <SOURCE>
 * @param <TARGET>
 */
public class PaymentModePopulator<SOURCE extends PaymentModeModel, TARGET extends PaymentModeData>
		implements Populator<PaymentModeModel, PaymentModeData>
{
	/**
	 * To populate the data from PaymentModeModel to PaymentModeData.
	 *
	 * @param source
	 * @param target
	 * @throws ConversionException
	 */
	@Override
	public void populate(final PaymentModeModel source, final PaymentModeData target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setActive(source.getActive());
	}
}
