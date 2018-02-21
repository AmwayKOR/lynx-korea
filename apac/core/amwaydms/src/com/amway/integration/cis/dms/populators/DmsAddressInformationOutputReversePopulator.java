/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.constants.AmwaycoreConstants.AddressTypes;
import com.amway.core.dms.data.AddressResultData;
import com.amway.integration.dms.services.AddressInformationResponse;
import com.amway.integration.dms.services.AddressObjectService;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link AddressInformationResponse} as source and {@link AddressResultData} as target
 * type.
 */
public class DmsAddressInformationOutputReversePopulator extends AbstractDmsPopulator
		implements Populator<AddressInformationResponse, AddressResultData<AddressProcessDecision>>
{

	private final static Logger LOG = Logger.getLogger(DmsAddressInformationOutputReversePopulator.class);

	private ModelService modelService;
	private CommonI18NService commonI18NService;
	private static final String addressStatusFlag = "Y";
	private Converter<CountryModel, CountryData> countryConverter;
	private Converter<RegionModel, RegionData> regionConverter;

	@Override
	public void populate(final AddressInformationResponse source, final AddressResultData<AddressProcessDecision> target)
			throws ConversionException
	{
		final List<AddressData> addressList = new ArrayList();
		if (source != null)
		{
			target.setResponseMessage(source.getReturnMessage());
			for (final AddressObjectService addressMaster : source.getAddressMasterList())
			{
				try
				{
					final AddressData address = new AddressData();
					populateAddress(address, addressMaster);
					for (final UsageData usageData : addressMaster.getUsageDataList())
					{
						final String addressType = usageData.getContactPointPurposeCd();
						if (AddressTypes.BILLING.equals(addressType))
						{
							address.setBillingAddress(
									isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
						}
						else if (AddressTypes.SHIPPING.equals(addressType))
						{
							address.setShippingAddress(
									isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
						}
						else if (AddressTypes.MAILING.equals(addressType))
						{
							address.setMailingAddress(
									isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
						}
						else if (AddressTypes.REGISTRATION.equals(addressType))
						{
							address.setRegistrationAddress(
									isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
						}
					}
					final CountryModel countryModel = getCommonI18NService().getCountry((addressMaster.getCntryCd()));
					address.setCountry(getCountryConverter().convert(countryModel));

					if (countryModel != null && countryModel.getRegions() != null && !countryModel.getRegions().isEmpty())
					{
						final RegionModel regionModel = getCommonI18NService().getRegion(countryModel,
								(addressMaster.getCntryCd()) + "-" + (addressMaster.getStateCd()));
						address.setRegion(getRegionConverter().convert(regionModel));
					}
					addressList.add(address);
				}
				catch (final Exception exc)
				{
					LOG.error(exc.getMessage(), exc);
				}
			}

			target.setDecision(source.getReturnCd() == 1 ? AddressProcessDecision.ACCEPT : AddressProcessDecision.UNKNOWN);
		}
		target.setResponseMessage(source.getReturnMessage());
		target.setAddressList(addressList);
	}

	private void populateAddress(final AddressData address, final AddressObjectService addressMaster)
	{
		final String[] line1Array = (addressMaster.getAddrStreet()).split(",");
		address.setLine1(line1Array.length > 0 ? line1Array[0].trim() : StringUtils.EMPTY);
		address.setNumber(line1Array.length > 1 ? line1Array[1].trim() : StringUtils.EMPTY);
		address.setLine2((addressMaster.getAddrLineThree()));
		address.setCompliment((addressMaster.getAddrLineTwo()));
		address.setTown((addressMaster.getCityName()));
		address.setPostalCode((addressMaster.getPostalCd()));
		address.setContactPointName((addressMaster.getContactPointName()));
		address.setLandmark((addressMaster.getAddrLineFour()));
		address.setPartyId((addressMaster.getPartyId()));
		address.setContactPointTypeCd((addressMaster.getContactPointTypeCd()));
		address.setFirstName(addressMaster.getUsageDataList().get(0).getCareOf());

	}

	private boolean isPrimaryAddress(final UsageData usageData)
	{
		return usageData.getPrimaryFlag().equals(addressStatusFlag) ? true : false;
	}

	/**
	 * @return commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return countryConverter
	 */
	public Converter<CountryModel, CountryData> getCountryConverter()
	{
		return countryConverter;
	}

	/**
	 * @param countryConverter the countryConverter to set
	 */
	public void setCountryConverter(final Converter<CountryModel, CountryData> countryConverter)
	{
		this.countryConverter = countryConverter;
	}

	/**
	 * @return regionConverter
	 */
	public Converter<RegionModel, RegionData> getRegionConverter()
	{
		return regionConverter;
	}

	/**
	 * @param regionConverter the regionConverter to set
	 */
	public void setRegionConverter(final Converter<RegionModel, RegionData> regionConverter)
	{
		this.regionConverter = regionConverter;
	}

}
