package com.amway.apac.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apacfacades.data.EnumData;


/**
 * Enum data populator
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacEnumDataPopulator implements Populator<HybrisEnumValue, EnumData>
{
	private EnumerationService enumerationService;

	/**
	 * Enum data populate method
	 */
	@Override
	public void populate(final HybrisEnumValue source, final EnumData target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setLabel(enumerationService.getEnumerationName(source));
	}

	/**
	 * @param enumerationService
	 *           the enumerationService to set
	 */
	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
