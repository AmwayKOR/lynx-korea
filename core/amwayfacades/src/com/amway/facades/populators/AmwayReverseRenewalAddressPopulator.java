package com.amway.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.amway.facades.renewal.data.AmwayRenewalAddressData;


public class AmwayReverseRenewalAddressPopulator implements Populator<AmwayRenewalAddressData, AddressModel>
{
	private static final String REGION_DELIMITER = "-";
	private CommonI18NService commonI18NService;

	@Override
	public void populate(final AmwayRenewalAddressData source, final AddressModel target) throws ConversionException
	{
		CountryModel country = commonI18NService.getCountry(source.getCountry());
		target.setCountry(country);
		target.setTown(source.getCity());
		target.setLine1(source.getLine1());
		target.setLine2(source.getLine2());
		target.setPostalcode(source.getPostCode());
		if (Objects.nonNull(country))
		{
			target.setRegion(
					commonI18NService.getRegion(country, StringUtils.join(source.getCountry(), REGION_DELIMITER, source.getState())));
		}
	}

	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
