package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AddressResultData;
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
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import com.amway.amwaydms.model.AddressResponse;
import com.amway.amwaydms.model.Address;
import com.amway.amwaydms.model.AddressContactUsage;


/**
 * Populator implementation for  as source and {@link AddressResultData} as target
 * type.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class AddressResponsePopulator  extends AbstractDmsPopulator
        implements Populator<AddressResponse, AddressResultData<AddressProcessDecision>>
{

    private final static Logger LOG = Logger.getLogger(AddressResponsePopulator.class);

    private ModelService modelService;
    private CommonI18NService commonI18NService;
    private static final boolean addressStatusFlag = true;
    private Converter<CountryModel, CountryData> countryConverter;
    private Converter<RegionModel, RegionData> regionConverter;

    @Override
    public void populate(final AddressResponse source, final AddressResultData<AddressProcessDecision> target)
            throws ConversionException
    {
        final List<AddressData> addressList = new ArrayList();
        if (source != null)
        {
            if (source.getErrorMessage() != null) {
                target.setResponseMessage(source.getErrorMessage().getMessage());
                target.setReturnCd(source.getErrorMessage().getCode());
            } else {
                target.setReturnCd(1);
            }

            for (final Address addressMaster : source.getAddressList())
            {
                try
                {
                    final AddressData address = new AddressData();
                    populateAddress(address, addressMaster);
                    for (final AddressContactUsage usageData : addressMaster.getUsageList())
                    {
                        final String addressType = usageData.getContactPointPurposeCd();
                        if (AmwaycoreConstants.AddressTypes.BILLING.equals(addressType))
                        {
                            address.setBillingAddress(
                                    isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
                        }
                        else if (AmwaycoreConstants.AddressTypes.SHIPPING.equals(addressType))
                        {
                            address.setShippingAddress(
                                    isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
                        }
                        else if (AmwaycoreConstants.AddressTypes.MAILING.equals(addressType))
                        {
                            address.setMailingAddress(
                                    isPrimaryAddress(usageData) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
                        }
                        else if (AmwaycoreConstants.AddressTypes.REGISTRATION.equals(addressType))
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

            target.setDecision(target.getReturnCd() == 1 ? AddressProcessDecision.ACCEPT : AddressProcessDecision.UNKNOWN);
        }
        target.setAddressList(addressList);
    }

    private void populateAddress(final AddressData address, final Address addressMaster)
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
        address.setFirstName(addressMaster.getUsageList().get(0).getCareOf());

    }

    private boolean isPrimaryAddress(final AddressContactUsage usageData)
    {
        return usageData.getPrimaryFlag();
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
