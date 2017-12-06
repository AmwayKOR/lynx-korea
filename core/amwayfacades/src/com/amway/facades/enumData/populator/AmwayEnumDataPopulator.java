/**
 * 
 */
package com.amway.facades.enumData.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.cmsfacades.data.EnumData;

public class AmwayEnumDataPopulator implements Populator<HybrisEnumValue, EnumData>
{
	
	private EnumerationService enumerationService;

	@Override
	public void populate(final HybrisEnumValue source, final EnumData target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setLabel(enumerationService.getEnumerationName(source));
	}
	
	@Required
	public void setEnumerationService(final EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}
}
