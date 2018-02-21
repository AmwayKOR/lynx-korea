package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.dms.data.*;
import com.amway.core.dms.data.BlockPrivDetailsData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.data.AccountMasterDetailsData;
import com.amway.core.data.TaxDetailsData;
import com.amway.core.los.data.SponsorDetailsData;
import com.amway.amwaydms.model.*;


/**
 * Populator implementation for {@link AccountResponse} as source and {@link AmwayProfileResponseData} as target
 * type.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class AccountResponsePopulator extends AbstractDmsPopulator
        implements Populator<AccountResponse, AmwayProfileResponseData>
{

    private static final Logger LOG = Logger.getLogger(AccountResponsePopulator.class);

    private Converter<CountryModel, CountryData> countryConverter;
    private Converter<RegionModel, RegionData> regionConverter;
    private CommonI18NService commonI18NService;

    @Override
    public void populate(final AccountResponse source, final AmwayProfileResponseData target) throws ConversionException
    {
        target.setAccountMasterDetails(populateAccountMasterDetails(source.getAccount().getAccountMst()));
        target.setSponsorDetails(populateSponsorDetails(source.getAccount().getSponsor()));
        target.setPartyDetailList(populatePartyDetails(source));
        final List<BlockPrivDetailsData> blockPrivList = new ArrayList<BlockPrivDetailsData>();
        if (CollectionUtils.isNotEmpty(source.getAccount().getBlockPrivilegeList()))
        {
            for (final BlockPrivilege blocksData : source.getAccount().getBlockPrivilegeList())
            {
                blockPrivList.add(populateBlockPrivDetailsData(blocksData));
            }
        }
        target.setBlockPrivDetailsData(blockPrivList);
        if (CollectionUtils.isNotEmpty(source.getAccount().getAccountBalanceList()))
        {
            target.setAccountBalance(populateAccountBalanceData(source.getAccount().getAccountBalanceList()));
        }
        if (source.getErrorMessage() == null) {
            target.setReturnCd(1);
        } else {
            target.setReturnCd(source.getErrorMessage().getCode());
            target.setReturnMessage(source.getErrorMessage().getMessage());
        }

        target.setSubscriptions(new HashSet<>());

        Set<PartySubscription> subs = Optional.ofNullable(source.getAccount())
                .map(Account::getSubscriptionList)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertSubscription)
                .collect(Collectors.toSet());

        target.setSubscriptions(subs);

    }

    protected PartySubscription convertSubscription(final Subscription subscription)
    {
        PartySubscription partySubscription = new PartySubscription();

        partySubscription.setCancelCd(subscription.getCancelCd());
        partySubscription.setIsPublicationCd(subscription.getIsPublicationCd());
        partySubscription.setPauseCd(subscription.getPauseCd());
        partySubscription.setRestoreCd(subscription.getRestoreCd());
        partySubscription.setSubscribeFlag(subscription.getSubscribeFlag());
        partySubscription.setSubscriptionId(subscription.getSubscriptionId());
        partySubscription.setSubscriptionName(subscription.getSubscriptionName());

        partySubscription.setLanguages(Collections.singleton(subscription.getLanguageCd()));

        partySubscription.setDeliveryWays(new HashSet<>());
        subscription.getSubscriptionDeliveryList().forEach(deliveryWay -> addDeliveryWay(partySubscription, deliveryWay));

        return partySubscription;
    }


    protected void addDeliveryWay(final PartySubscription partySubscription, final SubscriptionDelivery deliveryWay)
    {
        SubscriptionDeliveryData subscriptionDelivery = new SubscriptionDeliveryData();

        subscriptionDelivery.setDeliveryTypeCd(deliveryWay.getDeliveryTypeCd());

        partySubscription.getDeliveryWays().add(subscriptionDelivery);
    }

    /**
     * To populate the party details
     *
     * @param source
     * @return List<PartyDetailsData>
     */
    protected List<PartyDetailsData> populatePartyDetails(final AccountResponse source)
    {
        final List<PartyDetailsData> partDetailsDataList = new ArrayList<PartyDetailsData>();
        for (final Party partyDetailOutput : source.getAccount().getPartyList())
        {
            final PartyDetailsData partyDetailsData = new PartyDetailsData();

            if (CollectionUtils.isNotEmpty(partyDetailOutput.getMissingInfoList())) {
                partyDetailsData.setMissingInfoList(convertMissingInfoList(partyDetailOutput.getMissingInfoList()));
            }

            if (CollectionUtils.isNotEmpty(partyDetailOutput.getAddressList()))
            {
                partyDetailsData.setAddressMasterData(populateAddress(partyDetailOutput.getAddressList()));
            }
            if (CollectionUtils.isNotEmpty(partyDetailOutput.getTaxList()))
            {
                partyDetailsData.setTaxDetailsData(populateTaxDetailsData(partyDetailOutput.getTaxList().get(0)));
            }
            if (CollectionUtils.isNotEmpty(partyDetailOutput.getPersonalIdList()))
            {
                partyDetailsData.setPersonalIdDetailsData(populatePersonalIdDetails(partyDetailOutput.getPersonalIdList()));
            }
            if (CollectionUtils.isNotEmpty(partyDetailOutput.getNameList()))
            {
                partyDetailsData.setPartyNameDetailsData(populatePartyName(partyDetailOutput.getNameList().get(0)));
            }

            if (CollectionUtils.isNotEmpty(partyDetailOutput.getEcommList()))
            {
                partyDetailsData.setPartyEcommDetailsResponseData(populateEcommDetails(partyDetailOutput.getEcommList()));
            }

            if (CollectionUtils.isNotEmpty(partyDetailOutput.getPhoneList()))
            {
                partyDetailsData.setPartyPhoneDetailsData(populatePhoneList(partyDetailOutput.getPhoneList()));
            }

            partyDetailsData.setPartyPersonalDetailsData(populatePartyMstData(partyDetailOutput.getPartyMst()));
            partDetailsDataList.add(partyDetailsData);
        }

        return partDetailsDataList;
    }

    /**
     * To populate account master details.
     *
     * @param accountDetails
     * @return AccountMasterDetailsData
     */
    protected AccountMasterDetailsData populateAccountMasterDetails(final AccountMaster accountDetails)
    {
        final AccountMasterDetailsData accountMasterDetails = new AccountMasterDetailsData();
        if (accountDetails != null)
        {
            accountMasterDetails.setAccountName( accountDetails.getAccountName() );
            accountMasterDetails.setAccountNumber( accountDetails.getAboNum() );
            accountMasterDetails.setAccountType( accountDetails.getAccountSubTypeCd() );
            final SimpleDateFormat sdf = new SimpleDateFormat(DMSDATEPATTERN);
            accountMasterDetails.setEntryDate(parseDate(accountDetails.getAboEntryDate(),sdf,"accountDetails.aboEntryDate"));
            accountMasterDetails.setExpirationDate(parseDate(accountDetails.getAboExpireDate(),sdf,"accountDetails.aboExpireDate"));
            accountMasterDetails.setAccountName( accountDetails.getAccountName() );
            accountMasterDetails.setBusinessNature( accountDetails.getAccountSubTypeCd() );
            accountMasterDetails.setSegmentCd( accountDetails.getSegmentCd() );
            accountMasterDetails.setStatusCd( accountDetails.getStatusCd() );
            accountMasterDetails.setAccountCreditLimit( accountDetails.getAccountCreditLimit() );
            accountMasterDetails.setCntryCd( accountDetails.getCntryCd() );
            accountMasterDetails.setCurrencyCd( accountDetails.getCurrencyCd() );
            accountMasterDetails.setAccountCreditStatusCd( accountDetails.getAccountCreditStatusCd() );
            accountMasterDetails.setLanguageCd( accountDetails.getLanguageCd() );
            accountMasterDetails.setLglEntityType( accountDetails.getLglEntityType() );
            accountMasterDetails.setLoaCd( accountDetails.getLoaCd() );
            accountMasterDetails.setOrderCreditLimit( accountDetails.getOrderCreditLimit() );
            accountMasterDetails.setSalesPlanAff( accountDetails.getSalesPlanAff() );
            accountMasterDetails.setAccountSubTypeCd( accountDetails.getAccountSubTypeCd() );
            accountMasterDetails.setLateRenewalEligibleFlg( accountDetails.getLateRenewalEligibleFlg() );
        }
        return accountMasterDetails;
    }

    /**
     * To populate sponsor details.
     *
     * @param sponsorDetails
     * @return SponsorDetailsData
     */
    protected SponsorDetailsData populateSponsorDetails(final Sponsor sponsorDetails)
    {
        final SponsorDetailsData sponsorDetailsData = new SponsorDetailsData();
        if (sponsorDetails != null)
        {
            sponsorDetailsData.setSponsorABONo( sponsorDetails.getSponsorABONo() );
            sponsorDetailsData.setSponsorEmail( sponsorDetails.getSponsorEmail() );
            sponsorDetailsData.setSponsorName( sponsorDetails.getSponsorName() );
            sponsorDetailsData.setSponsorPhone( sponsorDetails.getSponsorPhone() );
        }
        return sponsorDetailsData;
    }

    /**
     * To populate the address
     *
     * @param addressList
     * @return List<AddressData>
     */
    protected List<AddressData> populateAddress(final List<Address> addressList)
    {
        final List<AddressData> addressDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(addressList))
        {
            for (final Address address : addressList)
            {
                final AddressData addressData = new AddressData();
                addressData.setPartyId( address.getPartyId() );
                addressData.setTown( address.getCityName() );
                final String[] line1Array = (address.getAddrStreet()).split(",");
                for (int i = 0; i < line1Array.length; i++)
                {
                    if (i == 0)
                    {
                        addressData.setLine1( line1Array[0].trim() );
                    }
                    else if (i == 1)
                    {
                        addressData.setNumber( line1Array[1].trim() );
                    }
                }

                addressData.setLine2( address.getAddrLineThree() );
                addressData.setLandmark( address.getAddrLineFour() );
                addressData.setPostalCode( address.getPostalCd() );
                addressData.setCompliment( address.getAddrLineTwo() );
                if (StringUtils.isNotBlank( address.getCntryCd() ))
                {
                    final CountryModel countryModel = getCommonI18NService().getCountry((address.getCntryCd()));
                    addressData.setCountry(getCountryConverter().convert(countryModel));

                    if (StringUtils.isNotBlank((address.getStateCd())))
                    {
                        final RegionModel regionModel = getCommonI18NService()
                                .getRegion(countryModel, (address.getCntryCd()) + "-" + (address.getStateCd()));
                        final RegionData regionData = getRegionConverter().convert(regionModel);
                        addressData.setRegion(regionData);

                    }
                }

                addressData.setId( address.getContactPointName() );
                addressData.setContactPointName( address.getContactPointName() );
                addressData.setContactPointTypeCd( address.getContactPointTypeCd() );
                addressData.setAddrDeliveryTypeCd( address.getAddrDeliveryTypeCd() );
                addressData.setFirstName( address.getUsageList().get(0).getCareOf() );

                for (final AddressContactUsage usageData : address.getUsageList())
                {
                    if (("Billing").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && (Boolean.TRUE)
                            == usageData.getPrimaryFlag())
                    {
                        addressData.setBillingAddress(true);
                    }
                    else if (("Shipping").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && (Boolean.TRUE)
                            == usageData.getPrimaryFlag())
                    {
                        addressData.setShippingAddress(true);
                    }
                    else if (("Mailing").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && (Boolean.TRUE)
                        == usageData.getPrimaryFlag())
                    {
                        addressData.setMailingAddress(true);
                    }
                    else if (("Registration").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && (Boolean.TRUE)
                            == usageData.getPrimaryFlag())
                    {
                        addressData.setRegistrationAddress(true);
                    }
                }
                addressDataList.add(addressData);
            }
        }
        return addressDataList;
    }

    /**
     * To populate party master data
     *
     * @param partyMasterServiceObject
     * @return PartyPersonalDetailsRequestData
     */
    protected PartyPersonalDetailsRequestData populatePartyMstData(final PartyMaster partyMasterServiceObject)
    {
        final PartyPersonalDetailsRequestData partyPersonalDetailsData = new PartyPersonalDetailsRequestData();
        if (partyMasterServiceObject != null)
        {
            partyPersonalDetailsData.setUserId(StringUtils.EMPTY);
            partyPersonalDetailsData.setUserPasswd(StringUtils.EMPTY);
            partyPersonalDetailsData.setBirthCountryCd( partyMasterServiceObject.getBirthCountryCd() );
            partyPersonalDetailsData.setLanguageCd( partyMasterServiceObject.getLanguageCd() );
            partyPersonalDetailsData.setMaritalStatusCd( partyMasterServiceObject.getMaritalStatusCd() );
            partyPersonalDetailsData.setGenderCd( partyMasterServiceObject.getGenderCd() );
            partyPersonalDetailsData.setPartyId( partyMasterServiceObject.getPartyId() );
            partyPersonalDetailsData.setPartyTypeCd( partyMasterServiceObject.getPartyTypeCd() );
            partyPersonalDetailsData
                    .setBirthdate(formatDate( partyMasterServiceObject.getBirthdate(), DMSDATEPATTERN, "dd/MM/yyyy" ));
            partyPersonalDetailsData.setPartyIsMinorFlg( partyMasterServiceObject.getPartyIsMinorFlg() );
            partyPersonalDetailsData.setPrimaryOnAccount( partyMasterServiceObject.getPrimaryOnAccount() );
        }
        return partyPersonalDetailsData;
    }

    private Date parseDate(String dateString, final SimpleDateFormat sdf, String attribute) {
        if(null != dateString) {
            try
            {
                return sdf.parse(dateString);
            }
            catch (final ParseException e)
            {
                LOG.error("Exception occured during parsing of "+attribute+ " with value "+dateString + " as date", e);
            }
        } else {
            LOG.warn(attribute+ " with value "+dateString + " can not be parsed as date");
        }
        return null;
    }

    private String formateDate(final String date)
    {
        final DateFormat simpleDateFormat = new SimpleDateFormat(DMSDATEPATTERN);
        final DateFormat requiredDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedString = null;
        try
        {
            if (StringUtils.isNotEmpty(date))
            {
                final Date retriveDate = simpleDateFormat.parse(date);
                formattedString = requiredDateFormat.format(retriveDate);
            }

        }
        catch (final ParseException e)
        {
            LOG.error("Exception occured during parsing", e);
        }
        return formattedString;
    }

    /**
     * To populate Tax details data
     *
     * @param taxDetailsOutput
     * @return TaxDetailsData
     */
    protected TaxDetailsData populateTaxDetailsData(final Tax taxDetailsOutput)
    {
        final TaxDetailsData taxDetailsData = new TaxDetailsData();
        if (taxDetailsOutput != null)
        {
            taxDetailsData.setTaxId( taxDetailsOutput.getTaxId() );
            taxDetailsData.setTaxIdType( taxDetailsOutput.getTaxTypeCd() );
            taxDetailsData.setTaxExpirationDate( taxDetailsOutput.getExpirationDate());
        }
        return taxDetailsData;
    }

    /**
     * To populate personal id details
     *
     * @param personalIdOutputList
     * @return List<PersonalIdDetailsData>
     */
    protected List<PersonalIdDetailsData> populatePersonalIdDetails(final List<PersonalId> personalIdOutputList)
    {
        final List<PersonalIdDetailsData> personalIdList = new ArrayList<>();
        if (personalIdOutputList != null)
        {
            for (final PersonalId personalIdOutput : personalIdOutputList)
            {
                final PersonalIdDetailsData personalIdDetailsData = new PersonalIdDetailsData();
                personalIdDetailsData.setPersonalId( personalIdOutput.getPersonalId() );
                personalIdDetailsData.setPersonalIdTypeCd( personalIdOutput.getPersonalIdTypeCd() );
                personalIdDetailsData.setExpirationDate( personalIdOutput.getExpirationDate() );
                personalIdDetailsData.setEffectiveDate( personalIdOutput.getEffectiveDate() );
                personalIdList.add( personalIdDetailsData );
            }
        }
        return personalIdList;
    }

    /**
     * To populate party name
     *
     * @param partyPersonName
     * @return PartyNameDetailsRequestData
     */
    protected PartyNameDetailsRequestData populatePartyName(final PartyName partyPersonName)
    {
        final PartyNameDetailsRequestData partyNameDetailsData = new PartyNameDetailsRequestData();
        final LocaleNameData localeNameData = new LocaleNameData();
        if (partyPersonName != null && partyPersonName.getLocaleName() != null)
        {
            final NameInfo partyName = partyPersonName.getLocaleName();
            localeNameData.setGivenName( partyName.getGivenName() );
            localeNameData.setFamilyName( partyName.getFamilyName() );
            partyNameDetailsData.setPartyId( partyPersonName.getPartyId().toString() );
        }
        partyNameDetailsData.setLocaleNameData(localeNameData);
        partyNameDetailsData.setPersonNameTypeCd( partyPersonName.getPersonNameTypeCd() );
        partyNameDetailsData.setLanguageCd( partyPersonName.getLanguageCd() );
        return partyNameDetailsData;
    }

    /**
     * To populate block previlege details
     *
     * @param blockPrivOutput
     * @return BlockPrivDetailsData
     */
    protected BlockPrivDetailsData populateBlockPrivDetailsData(final BlockPrivilege blockPrivOutput)
    {
        final BlockPrivDetailsData blockPrivDetailsData = new BlockPrivDetailsData();
        blockPrivDetailsData.setBlockPrivTypeId( blockPrivOutput.getBlockPrivTypeId() );

        return blockPrivDetailsData;
    }

    /**
     * To populate Account balance data
     *
     * @param accountBalanceList
     * @return List<AccountBalanceData>
     */
    protected List<AccountBalanceData> populateAccountBalanceData(final List<AccountBalance> accountBalanceList)
    {
        final List<AccountBalanceData> accountBalanceDatas = new ArrayList<AccountBalanceData>();
        final String currentIsocode = commonI18NService.getCurrentCurrency().getIsocode();
        for (final AccountBalance accountBalance : accountBalanceList)
        {
            if (StringUtils.equalsIgnoreCase(currentIsocode, accountBalance.getCurrencyCd() ))
            {
                final AccountBalanceData accountBalanceData = new AccountBalanceData();

                accountBalanceData.setBalanceTypeCd( accountBalance.getBalanceTypeCd() );
                accountBalanceData.setBalanceAmount( accountBalance.getBalanceAmount() );
                accountBalanceData.setCurrencyCd( accountBalance.getCurrencyCd() );
                accountBalanceData.setInstrumentId( accountBalance.getInstrumentId() );
                accountBalanceDatas.add( accountBalanceData );
            }
        }
        return accountBalanceDatas;
    }

    /**
     * To populate ecomm details
     *
     * @param partyEcommDataList
     * @return PartyEcommDetailsResponseData
     */
    protected PartyEcommDetailsResponseData populateEcommDetails(final List<Ecomm> partyEcommDataList)
    {
        final PartyEcommDetailsResponseData partyEcommDetailsData = new PartyEcommDetailsResponseData();
        final List<EcommMasterData> ecommMasterDataList = new ArrayList();

        for (final Ecomm partyEcommData : partyEcommDataList)
        {
            boolean isPrimary = false;
            final EcommMasterData ecommMasterData = new EcommMasterData();
            ecommMasterData.setEcommPartyId( partyEcommData.getPartyId() );
            ecommMasterData.setEcommContactPointTypeCd( partyEcommData.getContactPointTypeCd() );
            ecommMasterData.setEcommContactPointName( partyEcommData.getEcommName());
            ecommMasterData.setEcommAddr( partyEcommData.getEcommAddr() );
            ecommMasterData.setEcommTypeCd( partyEcommData.getEcommTypeCd() );
            ecommMasterData.setStatusCd( partyEcommData.getStatusCd() );
            ecommMasterData.setContactPointTypeCd( partyEcommData.getContactPointTypeCd() );
            ecommMasterData.setContactPointName( partyEcommData.getEcommName() );


            final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
            for (final ContactUsage usageData : partyEcommData.getUsageList())
            {
                String primaryFlag = (usageData.getPrimaryFlag().booleanValue() == true?"Y":"N");
                final UsageRequestData usageRequestData = new UsageRequestData();
                usageRequestData.setContactPointPurposeCd( usageData.getContactPointPurposeCd() );
                usageRequestData.setPrimaryFlag(primaryFlag);
                if (("Y").equalsIgnoreCase(primaryFlag))
                {

                    isPrimary = true;
                }
                usageDataList.add(usageRequestData);

            }
            ecommMasterData.setUsageData(usageDataList);

            if (isPrimary)
            {
                ecommMasterDataList.add(0, ecommMasterData);
            }
            else
            {
                ecommMasterDataList.add(ecommMasterData);
            }
        }
        partyEcommDetailsData.setEcommMasterListData(ecommMasterDataList);
        return partyEcommDetailsData;
    }

    protected List<MissingInfoDetailData> convertMissingInfoList(List<MissingInfoDetail> missingInfoList)
    {
        return missingInfoList.stream().map(m -> {
            MissingInfoDetailData data = new MissingInfoDetailData();
            data.setMissingInfoId(m.getMissingInfoId());
            data.setPartyId(m.getPartyId());
            data.setStatus(m.getStatus());
            return data;
        }).collect(Collectors.toList());
    }

    /**
     * To populate phone list
     *
     * @param partyPhoneList
     * @return PartyPhoneDetailsRequestData
     */
    protected PartyPhoneDetailsRequestData populatePhoneList(final List<Phone> partyPhoneList)
    {
        final PartyPhoneDetailsRequestData partyPhoneDetailsData = new PartyPhoneDetailsRequestData();
        final List<PhoneMasterRequestData> phoneMasterDataList = new ArrayList<PhoneMasterRequestData>();

        for (final Phone partyPhoneData : partyPhoneList)
        {
            boolean isPrimary = false;
            final PhoneMasterRequestData phoneMasterData = new PhoneMasterRequestData();
            phoneMasterData.setPartyId( partyPhoneData.getPartyId() );
            //phoneMasterData.setContactPointName( partyPhoneData.getContactPointName() );
            phoneMasterData.setPhoneCntryCd( partyPhoneData.getPhoneCntryCd() );
            phoneMasterData.setPhoneAreaCd( partyPhoneData.getPhoneAreaCd() );
            phoneMasterData.setSmsCapableFlag( partyPhoneData.getSmsCapableFlag().booleanValue() == true?"Y":"N" );
            phoneMasterData.setCntryCd( partyPhoneData.getCntryCd() );
            phoneMasterData.setStatusCd( partyPhoneData.getStatusCd() );
            phoneMasterData.setPhoneLocalNum( partyPhoneData.getPhoneLocalNum() );
            phoneMasterData.setContactPointTypeCd( partyPhoneData.getContactPointTypeCd() );
            phoneMasterData.setDayFlag( partyPhoneData.getDayFlag().booleanValue() == true?"Y":"N" );
            phoneMasterData.setEveningFlag( partyPhoneData.getEveningFlag().booleanValue() == true?"Y":"N" );
            phoneMasterData.setPhoneExtNum( partyPhoneData.getPhoneExtNum() );
            phoneMasterData.setContactId( partyPhoneData.getContactId() );

            final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
            for (final ContactUsage usageData : partyPhoneData.getUsageList())
            {
                final UsageRequestData usageRequestData = new UsageRequestData();
                usageRequestData.setContactPointPurposeCd( usageData.getContactPointPurposeCd() );
                usageRequestData.setPrimaryFlag( usageData.getPrimaryFlag().booleanValue() == true?"Y":"N" );

                if (("Y").equalsIgnoreCase(usageData.getPrimaryFlag().booleanValue() == true?"Y":"N"))
                {
                    isPrimary = true;
                }
                usageDataList.add(usageRequestData);
            }
            phoneMasterData.setUsageData(usageDataList);
            if (isPrimary)
            {
                phoneMasterDataList.add(0, phoneMasterData);
            }
            else
            {
                phoneMasterDataList.add(phoneMasterData);
            }

        }
        partyPhoneDetailsData.setPhoneMasterListData(phoneMasterDataList);
        return partyPhoneDetailsData;
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
}