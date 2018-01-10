/**
 *
 */
package com.amway.facades.populators;

import com.amway.facades.cart.data.PaymentDetailsData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


/**
 * @author mohit2496
 *
 */
public class AmwayCartPaymentDetailsPopulator implements Populator<CartModel, CartData>
{

	private Converter<CartModel, PaymentDetailsData> amwayPaymentDetailsConverter;

	@Override
	public void populate(final CartModel source, final CartData target) throws ConversionException
	{
		validateParameterNotNull(source, "Parameter source cannot be null");
		validateParameterNotNull(target, "Parameter target cannot be null");

		target.setPaymentDetails(amwayPaymentDetailsConverter.convert(source));
	}

	public void setAmwayPaymentDetailsConverter(final Converter<CartModel, PaymentDetailsData> amwayPaymentDetailsConverter)
	{
		this.amwayPaymentDetailsConverter = amwayPaymentDetailsConverter;
	}
}
