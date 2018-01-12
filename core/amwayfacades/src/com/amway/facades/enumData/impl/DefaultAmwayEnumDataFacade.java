/**
 *
 */
package com.amway.facades.enumData.impl;

import de.hybris.platform.cmsfacades.data.EnumData;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.facades.enumData.AmwayEnumDataFacade;


/**
 *
 * Implementation of AmwayEnumDataFacade
 * @author rajatgoswami
 * 
 *  */
public class DefaultAmwayEnumDataFacade implements AmwayEnumDataFacade
{

	private EnumerationService enumerationService;
	private Converter<HybrisEnumValue, EnumData> enumConverter;


	@Override
	public List<EnumData> getEnumValuesByClass(final Class cnfe)
	{
		final List<HybrisEnumValue> rawEnumList = enumerationService.getEnumerationValues(cnfe);
		return enumConverter.convertAll(rawEnumList);
	}

	@Override
	public List<EnumData> getEnumValues(final List<? extends HybrisEnumValue> enumValues)
	{
		return enumConverter.convertAll(enumValues);
	}

	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	@Required
	public void setEnumConverter(final Converter<HybrisEnumValue, EnumData> enumConverter)
	{
		this.enumConverter = enumConverter;
	}
}