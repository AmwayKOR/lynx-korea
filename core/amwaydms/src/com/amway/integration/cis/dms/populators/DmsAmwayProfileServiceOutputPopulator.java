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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.data.AccountMasterDetailsData;
import com.amway.core.data.TaxDetailsData;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.BlockPrivDetailsData;
import com.amway.core.dms.data.EcommMasterData;
import com.amway.core.dms.data.LocaleNameData;
import com.amway.core.dms.data.PartyDetailsData;
import com.amway.core.dms.data.PartyEcommDetailsResponseData;
import com.amway.core.dms.data.PartyNameDetailsRequestData;
import com.amway.core.dms.data.PartyPersonalDetailsRequestData;
import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PersonalIdDetailsData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.core.los.data.SponsorDetailsData;
import com.amway.integration.dms.services.AccountBalance;
import com.amway.integration.dms.services.AccountFullResponse;
import com.amway.integration.dms.services.AddressObjectService;
import com.amway.integration.dms.services.AmwayProfileOutput;
import com.amway.integration.dms.services.GetBlockPrivDetSrvcOutput;
import com.amway.integration.dms.services.NameDetails;
import com.amway.integration.dms.services.PartyDetailsOutput;
import com.amway.integration.dms.services.PartyEcommData;
import com.amway.integration.dms.services.PartyMasterServiceObject;
import com.amway.integration.dms.services.PartyPersonName;
import com.amway.integration.dms.services.PartyPhoneData;
import com.amway.integration.dms.services.PersonalIdOutput;
import com.amway.integration.dms.services.SponsorDetailResponse;
import com.amway.integration.dms.services.TaxDetailsOutput;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link AmwayProfileOutput} as source and {@link AmwayProfileResponseData} as target
 * type.
 */
public class DmsAmwayProfileServiceOutputPopulator extends AbstractDmsPopulator
		implements Populator<AmwayProfileOutput, AmwayProfileResponseData>
{

	private static final Logger LOG = Logger.getLogger(DmsAmwayProfileServiceOutputPopulator.class);

	private Converter<CountryModel, CountryData> countryConverter;
	private Converter<RegionModel, RegionData> regionConverter;
	private CommonI18NService commonI18NService;

	@Override
	public void populate(final AmwayProfileOutput source, final AmwayProfileResponseData target) throws ConversionException
	{
		target.setAccountMasterDetails(populateAccountMasterDetails(source.getAccountMasterDetails()));
		target.setSponsorDetails(populateSponsorDetails(source.getSponsorDetails()));
		target.setPartyDetailList(populatePartyDetails(source));
		final List<BlockPrivDetailsData> blockPrivList = new ArrayList<BlockPrivDetailsData>();
		if (CollectionUtils.isNotEmpty(source.getBlockPrivList()))
		{
			for (final GetBlockPrivDetSrvcOutput blocksData : source.getBlockPrivList())
			{
				blockPrivList.add(populateBlockPrivDetailsData(blocksData));
			}
		}
		target.setBlockPrivDetailsData(blockPrivList);
		if (CollectionUtils.isNotEmpty(source.getAccountBalance()))
		{
			target.setAccountBalance(populateAccountBalanceData(source.getAccountBalance()));
		}
		target.setReturnCd(source.getReturnCd());
		target.setReturnMessage(source.getReturnMessage());
	}

	/**
	 * To populate the party details
	 *
	 * @param source
	 * @return List<PartyDetailsData>
	 */
	public List<PartyDetailsData> populatePartyDetails(final AmwayProfileOutput source)
	{
		final List<PartyDetailsData> partDetailsDataList = new ArrayList<PartyDetailsData>();
		for (final PartyDetailsOutput partyDetailOutput : source.getPartyDetailList())
		{
			final PartyDetailsData partyDetailsData = new PartyDetailsData();

			if (CollectionUtils.isNotEmpty(partyDetailOutput.getAddressMasterList()))
			{
				partyDetailsData.setAddressMasterData(populateAddress(partyDetailOutput.getAddressMasterList()));
			}
			if (CollectionUtils.isNotEmpty(partyDetailOutput.getTaxDetail()))
			{
				partyDetailsData.setTaxDetailsData(populateTaxDetailsData(partyDetailOutput.getTaxDetail().get(0)));
			}
			if (CollectionUtils.isNotEmpty(partyDetailOutput.getPersonalIds()))
			{
				partyDetailsData.setPersonalIdDetailsData(populatePersonalIdDetails(partyDetailOutput.getPersonalIds()));
			}
			if (CollectionUtils.isNotEmpty(partyDetailOutput.getPartyNameList()))
			{
				partyDetailsData.setPartyNameDetailsData(populatePartyName(partyDetailOutput.getPartyNameList().get(0)));
			}

			if (CollectionUtils.isNotEmpty(partyDetailOutput.getPartyEcommDetailList()))
			{
				partyDetailsData.setPartyEcommDetailsResponseData(populateEcommDetails(partyDetailOutput.getPartyEcommDetailList()));
			}

			if (CollectionUtils.isNotEmpty(partyDetailOutput.getPartyPhoneList()))
			{
				partyDetailsData.setPartyPhoneDetailsData(populatePhoneList(partyDetailOutput.getPartyPhoneList()));
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
	public AccountMasterDetailsData populateAccountMasterDetails(final AccountFullResponse accountDetails)
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
			//	accountMasterDetails.setLateRenewalEligibleFlg( accountDetails.getLateRenewalEligibleFlg() );
		}
		return accountMasterDetails;
	}

	/**
	 * To populate sponsor details.
	 *
	 * @param sponsorDetails
	 * @return SponsorDetailsData
	 */
	public SponsorDetailsData populateSponsorDetails(final SponsorDetailResponse sponsorDetails)
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
	public List<AddressData> populateAddress(final List<AddressObjectService> addressList)
	{
		final List<AddressData> addressDataList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(addressList))
		{
			for (final AddressObjectService address : addressList)
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
				addressData.setFirstName( address.getUsageDataList().get(0).getCareOf() );

				for (final UsageData usageData : address.getUsageDataList())
				{
					if (("Billing").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && ("Y")
							.equalsIgnoreCase(usageData.getPrimaryFlag()))
					{
						addressData.setBillingAddress(true);
					}
					else if (("Shipping").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && ("Y")
							.equalsIgnoreCase(usageData.getPrimaryFlag()))
					{
						addressData.setShippingAddress(true);
					}
					else if (("Mailing").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && ("Y")
							.equalsIgnoreCase(usageData.getPrimaryFlag()))
					{
						addressData.setMailingAddress(true);
					}
					else if (("Registration").equalsIgnoreCase(usageData.getContactPointPurposeCd()) && ("Y")
							.equalsIgnoreCase( usageData.getPrimaryFlag() ))
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
	public PartyPersonalDetailsRequestData populatePartyMstData(final PartyMasterServiceObject partyMasterServiceObject)
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
	public TaxDetailsData populateTaxDetailsData(final TaxDetailsOutput taxDetailsOutput)
	{
		final TaxDetailsData taxDetailsData = new TaxDetailsData();
		if (taxDetailsOutput != null)
		{
			taxDetailsData.setTaxId( taxDetailsOutput.getTaxId() );
			taxDetailsData.setTaxIdType( taxDetailsOutput.getTaxTypeCd() );
			taxDetailsData.setTaxExpirationDate( taxDetailsOutput.getTaxTypeExpireDate() );
		}
		return taxDetailsData;
	}

	/**
	 * To populate personal id details
	 *
	 * @param personalIdOutputList
	 * @return List<PersonalIdDetailsData>
	 */
	public List<PersonalIdDetailsData> populatePersonalIdDetails(final List<PersonalIdOutput> personalIdOutputList)
	{
		final List<PersonalIdDetailsData> personalIdList = new ArrayList<>();
		if (personalIdOutputList != null)
		{
			for (final PersonalIdOutput personalIdOutput : personalIdOutputList)
			{
				final PersonalIdDetailsData personalIdDetailsData = new PersonalIdDetailsData();
				personalIdDetailsData.setPersonalId( personalIdOutput.getPersonalId() );
				personalIdDetailsData.setPersonalIdTypeCd( personalIdOutput.getPersonalIdTypeCd() );
				personalIdDetailsData.setExpirationDate( personalIdOutput.getExpirationDate() );
				personalIdDetailsData.setEffectiveDate( personalIdOutput.getEffectiveDate() );
				personalIdDetailsData.setPartyId( personalIdOutput.getPartyId() );
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
	public PartyNameDetailsRequestData populatePartyName(final PartyPersonName partyPersonName)
	{
		final PartyNameDetailsRequestData partyNameDetailsData = new PartyNameDetailsRequestData();
		final LocaleNameData localeNameData = new LocaleNameData();
		if (partyPersonName != null && partyPersonName.getLocaleName() != null)
		{
			final NameDetails partyName = partyPersonName.getLocaleName();
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
	public BlockPrivDetailsData populateBlockPrivDetailsData(final GetBlockPrivDetSrvcOutput blockPrivOutput)
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
	public List<AccountBalanceData> populateAccountBalanceData(final List<AccountBalance> accountBalanceList)
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
	public PartyEcommDetailsResponseData populateEcommDetails(final List<PartyEcommData> partyEcommDataList)
	{
		final PartyEcommDetailsResponseData partyEcommDetailsData = new PartyEcommDetailsResponseData();
		final List<EcommMasterData> ecommMasterDataList = new ArrayList();

		for (final PartyEcommData partyEcommData : partyEcommDataList)
		{
			boolean isPrimary = false;
			final EcommMasterData ecommMasterData = new EcommMasterData();
			ecommMasterData.setEcommPartyId( partyEcommData.getPartyId() );
			ecommMasterData.setEcommContactPointTypeCd( partyEcommData.getContactPointTypeCd() );
			ecommMasterData.setEcommContactPointName( partyEcommData.getContactPointName() );
			ecommMasterData.setEcommAddr( partyEcommData.getEcommAddr() );
			ecommMasterData.setEcommTypeCd( partyEcommData.getEcommTypeCd() );
			ecommMasterData.setStatusCd( partyEcommData.getStatusCd() );
			ecommMasterData.setContactPointTypeCd( partyEcommData.getContactPointTypeCd() );
			ecommMasterData.setContactPointName( partyEcommData.getContactPointName() );


			final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
			for (final UsageData usageData : partyEcommData.getUsageDataList())
			{
				final UsageRequestData usageRequestData = new UsageRequestData();
				usageRequestData.setContactPointPurposeCd( usageData.getContactPointPurposeCd() );
				usageRequestData.setPrimaryFlag( usageData.getPrimaryFlag() );
				if (("Y").equalsIgnoreCase(usageData.getPrimaryFlag()))
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

	/**
	 * To populate phone list
	 *
	 * @param partyPhoneList
	 * @return PartyPhoneDetailsRequestData
	 */
	public PartyPhoneDetailsRequestData populatePhoneList(final List<PartyPhoneData> partyPhoneList)
	{
		final PartyPhoneDetailsRequestData partyPhoneDetailsData = new PartyPhoneDetailsRequestData();
		final List<PhoneMasterRequestData> phoneMasterDataList = new ArrayList<PhoneMasterRequestData>();

		for (final PartyPhoneData partyPhoneData : partyPhoneList)
		{
			boolean isPrimary = false;
			final PhoneMasterRequestData phoneMasterData = new PhoneMasterRequestData();
			phoneMasterData.setPartyId( partyPhoneData.getPartyId() );
			phoneMasterData.setContactPointName( partyPhoneData.getContactPointName() );
			phoneMasterData.setPhoneCntryCd( partyPhoneData.getPhoneCntryCd() );
			phoneMasterData.setPhoneAreaCd( partyPhoneData.getPhoneAreaCd() );
			phoneMasterData.setSmsCapableFlag( partyPhoneData.getSmsCapableFlag() );
			phoneMasterData.setCntryCd( partyPhoneData.getCntryCd() );
			phoneMasterData.setStatusCd( partyPhoneData.getStatusCd() );
			phoneMasterData.setPhoneLocalNum( partyPhoneData.getPhoneLocalNum() );
			phoneMasterData.setContactPointTypeCd( partyPhoneData.getContactPointTypeCd() );
			phoneMasterData.setDayFlag( partyPhoneData.getDayFlag() );
			phoneMasterData.setEveningFlag( partyPhoneData.getEveningFlag() );
			phoneMasterData.setPhoneExtNum( partyPhoneData.getPhoneExtNum() );
			phoneMasterData.setContactId( partyPhoneData.getContactId() );

			final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
			for (final UsageData usageData : partyPhoneData.getUsageDataList())
			{
				final UsageRequestData usageRequestData = new UsageRequestData();
				usageRequestData.setContactPointPurposeCd( usageData.getContactPointPurposeCd() );
				usageRequestData.setPrimaryFlag( usageData.getPrimaryFlag() );

				if (("Y").equalsIgnoreCase(usageData.getPrimaryFlag()))
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
