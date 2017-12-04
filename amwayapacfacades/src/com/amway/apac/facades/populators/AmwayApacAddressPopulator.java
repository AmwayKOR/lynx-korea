package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressPopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * @author Aaron Yong
 *
 */
public class AmwayApacAddressPopulator extends AddressPopulator
{
	private Map<String, Converter<AddressModel, StringBuilder>> addressFormatConverterMap;
	private Converter<AddressModel, StringBuilder> defaultAddressFormatConverter;

	@Override
	public void populate(final AddressModel source, final AddressData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		populateBasicFields(source, target);
		populateAddressFields(source, target);
		populateRegion(source, target);
		populateCountryAndFormattedAddress(source, target);
	}

	@Override
	protected void populateBasicFields(final AddressModel source, final AddressData target)
	{
		if (source.getPk() != null)
		{
			target.setId(source.getPk().toString());
		}

		target.setBillingAddress(Boolean.TRUE.equals(source.getBillingAddress()));
		target.setShippingAddress(Boolean.TRUE.equals(source.getShippingAddress()));
		target.setVisibleInAddressBook(Boolean.TRUE.equals(source.getVisibleInAddressBook()));
	}

	@Override
	protected void populateAddressFields(final AddressModel source, final AddressData target)
	{
		if (source.getTitle() != null)
		{
			target.setTitle(source.getTitle().getName());
			target.setTitleCode(source.getTitle().getCode());
		}
		target.setFirstName(source.getFirstname());
		target.setLastName(source.getLastname());

		target.setCompanyName(source.getCompany());
		target.setLine1(source.getLine1());
		target.setLine2(source.getLine2());
		target.setLine3(source.getLine3());
		target.setTown(source.getTown());
		target.setPostalCode(source.getPostalcode());
		target.setPhone(source.getPhone1());
		target.setEmail(source.getEmail());
	}

	@Override
	protected void populateCountryAndFormattedAddress(final AddressModel source, final AddressData target)
	{
		final CountryModel countryModel = source.getCountry();
		String isoCode = null;
		if (countryModel != null)
		{
			final CountryData countryData = new CountryData();
			countryData.setIsocode(countryModel.getIsocode());
			isoCode = countryModel.getIsocode();
			countryData.setName(countryModel.getName());
			target.setCountry(countryData);
		}

		final Converter<AddressModel, StringBuilder> addressFormatConverter = addressFormatConverterMap.containsKey(isoCode)
				? addressFormatConverterMap.get(isoCode)
				: getDefaultAddressFormatConverter();

		target.setFormattedAddress(addressFormatConverter.convert(source).toString());
	}

	@Override
	protected void populateRegion(final AddressModel source, final AddressData target)
	{
		final RegionModel regionModel = source.getRegion();
		if (regionModel != null)
		{
			final RegionData regionData = new RegionData();
			regionData.setIsocode(regionModel.getIsocode());
			regionData.setIsocodeShort(regionModel.getIsocodeShort());
			regionData.setName(regionModel.getName());
			if (regionModel.getCountry() != null)
			{
				regionData.setCountryIso(regionModel.getCountry().getIsocode());
			}
			target.setRegion(regionData);
		}
	}

	@Override
	protected Map<String, Converter<AddressModel, StringBuilder>> getAddressFormatConverterMap()
	{
		return addressFormatConverterMap;
	}

	@Override
	@Required
	public void setAddressFormatConverterMap(final Map<String, Converter<AddressModel, StringBuilder>> addressFormatConverterMap)
	{
		this.addressFormatConverterMap = addressFormatConverterMap;
	}

	@Override
	protected Converter<AddressModel, StringBuilder> getDefaultAddressFormatConverter()
	{
		return defaultAddressFormatConverter;
	}

	@Override
	@Required
	public void setDefaultAddressFormatConverter(final Converter<AddressModel, StringBuilder> defaultAddressFormatConverter)
	{
		this.defaultAddressFormatConverter = defaultAddressFormatConverter;
	}
}
