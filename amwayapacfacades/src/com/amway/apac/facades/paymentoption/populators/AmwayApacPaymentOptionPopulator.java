package com.amway.apac.facades.paymentoption.populators;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.PaymentType;
import com.amway.apac.core.model.AmwayApacVariantProductModel;
import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apacfacades.data.EnumData;
import com.amway.apacfacades.payment.data.AmwayPaymentOptionData;


/**
 * Payment option Populator
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacPaymentOptionPopulator implements Populator<AmwayPaymentOptionModel, AmwayPaymentOptionData>
{

	/** The enum converter. */
	private Converter<HybrisEnumValue, EnumData> enumConverter;

	/**
	 * Payment option populate method.
	 *
	 * @param source
	 *           the source
	 * @param target
	 *           the target
	 * @throws ConversionException
	 *            the conversion exception
	 */
	@Override
	public void populate(final AmwayPaymentOptionModel source, final AmwayPaymentOptionData target) throws ConversionException
	{
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.TARGET_STRING, source);
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.SOURCE_STRING, target);

		target.setAliasCode(source.getAliasCode());

		if (PaymentType.PIF.equals(source.getType()))
		{
			target.setTitle(getProductName(source));
		}
		else
		{
			if (StringUtils.isNotBlank(source.getTitle()))
			{
				target.setTitle(source.getTitle());
			}
			else
			{
				if (source.getProduct() != null)
				{
					target.setTitle(getProductName(source));
				}
			}
		}

		target.setPaymentType(source.getType());
		target.setPaymentTypeData(getEnumConverter().convert(source.getType()));
	}

	/**
	 * Gets the product name.
	 *
	 * @param paymentOption
	 *           the payment option
	 * @return Product name of product attached in payment option
	 */
	protected String getProductName(final AmwayPaymentOptionModel paymentOption)
	{
		final ProductModel product = paymentOption.getProduct();
		String productName = StringUtils.EMPTY;
		if (null != product)
		{
			productName = product.getName();
		}
		if (StringUtils.isEmpty(productName) && product instanceof AmwayApacVariantProductModel
				&& (null != ((AmwayApacVariantProductModel) product).getBaseProduct()))
		{
			productName = ((AmwayApacVariantProductModel) product).getBaseProduct().getName();
		}
		return productName;
	}

	/**
	 * Gets the enum converter.
	 *
	 * @return the enumConverter
	 */
	public Converter<HybrisEnumValue, EnumData> getEnumConverter()
	{
		return enumConverter;
	}

	/**
	 * Sets the enum converter.
	 *
	 * @param enumConverter
	 *           the enumConverter to set
	 */
	@Required
	public void setEnumConverter(final Converter<HybrisEnumValue, EnumData> enumConverter)
	{
		this.enumConverter = enumConverter;
	}
}
