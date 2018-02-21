package com.amway.integration.cis.dms.populators;

import static com.amway.core.constants.AmwaycoreConstants.AddressTypes.*;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.amway.core.dms.data.MissingInfoDetailData;
import com.amway.core.dms.data.PartyDetailsData;
import com.amway.core.dms.data.PartyEcommDetailsResponseData;
import com.amway.core.dms.data.PartyNameDetailsRequestData;
import com.amway.core.dms.data.PartyPersonalDetailsRequestData;
import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PersonalIdDetailsData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.core.los.data.SponsorDetailsData;
import com.amway.integration.dms.services.Account;
import com.amway.integration.dms.services.AccountBalance;
import com.amway.integration.dms.services.AccountMaster;
import com.amway.integration.dms.services.Address;
import com.amway.integration.dms.services.AddressContactUsage;
import com.amway.integration.dms.services.AmwayProfileOutput;
import com.amway.integration.dms.services.BlockPrivilege;
import com.amway.integration.dms.services.ContactUsage;
import com.amway.integration.dms.services.Ecomm;
import com.amway.integration.dms.services.MissingInfoDetail;
import com.amway.integration.dms.services.NameInfo;
import com.amway.integration.dms.services.Party;
import com.amway.integration.dms.services.PartyMaster;
import com.amway.integration.dms.services.PartyName;
import com.amway.integration.dms.services.PersonalId;
import com.amway.integration.dms.services.Phone;
import com.amway.integration.dms.services.Sponsor;
import com.amway.integration.dms.services.Tax;


/**
 * Populates amway account information into AmwayProfile data object.
 */
public class DmsAmwayAccountServiceOutputPopulator extends AbstractDmsPopulator
		implements Populator<AmwayProfileOutput, AmwayProfileResponseData>
{
	private static final Logger LOG = Logger.getLogger(DmsAmwayAccountServiceOutputPopulator.class);

	public static final String MAGIC_Y = "Y";
	public static final String MAGIC_N = "N";
	public static final String ADDRESS_VALID_STATUS = "Valid";

	@Override
	public void populate(AmwayProfileOutput amwayProfileOutput, AmwayProfileResponseData amwayProfileResponseData)
			throws ConversionException
	{
		if (amwayProfileOutput.getAccount() == null)
		{
			return;
		}
		populate(amwayProfileOutput.getAccount(), amwayProfileResponseData);
	}

	private Converter<CountryModel, CountryData> countryConverter;

	private Converter<RegionModel, RegionData> regionConverter;

	private CommonI18NService commonI18NService;

	public void populate(Account source, AmwayProfileResponseData target) throws ConversionException
	{
		target.setMissingInfoList(convertMissingInfoList(source.getMissingInfoList()));
		target.setAccountMasterDetails(populateAccountMasterDetails(source.getAccountMst()));
		target.setSponsorDetails(populateSponsorDetails(source.getSponsor()));
		target.setPartyDetailList(populatePartyDetails(source));
		final List<BlockPrivDetailsData> blockPrivList = new ArrayList<BlockPrivDetailsData>();
		if (CollectionUtils.isNotEmpty(source.getBlockPrivilegeList()))
		{
			for (BlockPrivilege privilegeData: source.getBlockPrivilegeList()) {
				blockPrivList.add(populateBlockPrivDetailsData(privilegeData));
			}
		}
		target.setBlockPrivDetailsData(blockPrivList);
		if (CollectionUtils.isNotEmpty(source.getAccountBalanceList()))
		{
			target.setAccountBalance(populateAccountBalanceData(source.getAccountBalanceList()));
		}
		target.setReturnCd(1); //means no errors
	}

	private List<PartyDetailsData> populatePartyDetails(Account source)
	{
		if (source == null) {
			return null;
		}
		final List<PartyDetailsData> partDetailsDataList = new ArrayList<PartyDetailsData>();
		for (final Party party : source.getPartyList())
		{

			final PartyDetailsData partyDetailsData = new PartyDetailsData();
			partyDetailsData.setMissingInfoList(convertMissingInfoList(party.getMissingInfoList()));

			if (CollectionUtils.isNotEmpty(party.getAddressList()))
			{
				partyDetailsData.setAddressMasterData(populateAddressList(party.getAddressList()));
			}
			if (CollectionUtils.isNotEmpty(party.getTaxList()))
			{
				//TODO AY we populate only first tax details. For boost markeds it might be changed
				partyDetailsData.setTaxDetailsData(populateTaxDetailsData(party.getTaxList().get(0)));
			}
			if (CollectionUtils.isNotEmpty(party.getPersonalIdList()))
			{
				partyDetailsData.setPersonalIdDetailsData(populatePersonalIdDetails(party.getPersonalIdList(), party));
			}
			if (CollectionUtils.isNotEmpty(party.getNameList()))
			{
				partyDetailsData.setPartyNameDetailsData(populatePartyName(party.getNameList().get(0), party));
			}

			if (CollectionUtils.isNotEmpty(party.getEcommList()))
			{
				partyDetailsData.setPartyEcommDetailsResponseData(populateEcommDetails(party.getEcommList()));
			}

			if (CollectionUtils.isNotEmpty(party.getPhoneList()))
			{
				partyDetailsData.setPartyPhoneDetailsData(populatePhoneList(party.getPhoneList()));
			}

			partyDetailsData.setPartyPersonalDetailsData(populatePartyMstData(party.getPartyMst()));

			populateAdditionalProperties(party, partyDetailsData);

			partDetailsDataList.add(partyDetailsData);
		}

		return partDetailsDataList;
	}

	protected void populateAdditionalProperties(Party party, PartyDetailsData partyDetailsData)
	{
		// to be implemented into inherited objects
	}

	private List<MissingInfoDetailData> convertMissingInfoList(List<MissingInfoDetail> missingInfoList)
	{
		//TODO AY legacy code v2, to a separate converter
		if (missingInfoList == null) {
			return null;
		}
		return missingInfoList.stream().map(m -> {
			MissingInfoDetailData data = new MissingInfoDetailData();
			data.setMissingInfoId(m.getMissingInfoId());
			data.setPartyId(m.getPartyId());
			data.setStatus(m.getStatus());
			return data;
		}).collect(Collectors.toList());
	}

	private PartyPersonalDetailsRequestData populatePartyMstData(PartyMaster partyMasterServiceObject)
	{
		final PartyPersonalDetailsRequestData partyPersonalDetailsData = new PartyPersonalDetailsRequestData();
		if (partyMasterServiceObject != null)
		{
			partyPersonalDetailsData.setUserId(StringUtils.EMPTY);
			partyPersonalDetailsData.setUserPasswd(StringUtils.EMPTY);
			partyPersonalDetailsData.setBirthCountryCd((partyMasterServiceObject.getBirthCountryCd()));
			partyPersonalDetailsData.setLanguageCd((partyMasterServiceObject.getLanguageCd()));
			partyPersonalDetailsData.setMaritalStatusCd((partyMasterServiceObject.getMaritalStatusCd()));
			partyPersonalDetailsData.setGenderCd((partyMasterServiceObject.getGenderCd()));
			partyPersonalDetailsData.setPartyId((partyMasterServiceObject.getPartyId()));
			partyPersonalDetailsData.setPartyTypeCd((partyMasterServiceObject.getPartyTypeCd()));
			partyPersonalDetailsData
					.setBirthdate(formatDate((partyMasterServiceObject.getBirthdate()), DMSDATEPATTERN, "dd/MM/yyyy"));
			partyPersonalDetailsData.setPartyIsMinorFlg((partyMasterServiceObject.getPartyIsMinorFlg()));
			partyPersonalDetailsData.setPrimaryOnAccount((partyMasterServiceObject.getPrimaryOnAccount()));
		}
		return partyPersonalDetailsData;
	}

	private PartyPhoneDetailsRequestData populatePhoneList(List<Phone> phoneList)
	{
		//TODO AY the code below is a legacy code from v2
		final PartyPhoneDetailsRequestData partyPhoneDetailsData = new PartyPhoneDetailsRequestData();
		final List<PhoneMasterRequestData> phoneMasterDataList = new ArrayList<PhoneMasterRequestData>();

		for (final Phone partyPhoneData : phoneList)
		{
			boolean isPrimary = false;
			final PhoneMasterRequestData phoneMasterData = new PhoneMasterRequestData();
			phoneMasterData.setPartyId((partyPhoneData.getPartyId()));
			phoneMasterData.setContactPointName(partyPhoneData.getContactId()); //according to Chi Duong <Chi_Duong@Amway.com>
			phoneMasterData.setPhoneCntryCd((partyPhoneData.getPhoneCntryCd()));
			phoneMasterData.setPhoneAreaCd((partyPhoneData.getPhoneAreaCd()));
			phoneMasterData.setSmsCapableFlag(formatBoolean(partyPhoneData.isSmsCapableFlag()));
			phoneMasterData.setCntryCd((partyPhoneData.getCntryCd()));
			phoneMasterData.setStatusCd((partyPhoneData.getStatusCd()));
			phoneMasterData.setPhoneLocalNum((partyPhoneData.getPhoneLocalNum()));
			phoneMasterData.setContactPointTypeCd((partyPhoneData.getContactPointTypeCd()));
			phoneMasterData.setDayFlag(formatBoolean(partyPhoneData.isDayFlag()));
			phoneMasterData.setEveningFlag(formatBoolean(partyPhoneData.isEveningFlag()));
			phoneMasterData.setPhoneExtNum((partyPhoneData.getPhoneExtNum()));
			phoneMasterData.setContactId((partyPhoneData.getContactId()));

			final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
			for (final ContactUsage usageData : partyPhoneData.getUsageList())
			{
				final UsageRequestData usageRequestData = new UsageRequestData();
				usageRequestData.setContactPointPurposeCd(usageData.getContactPointPurposeCd());
				usageRequestData.setPrimaryFlag(formatBoolean(usageData.isPrimaryFlag()));

				if (Boolean.TRUE.equals(usageData.isPrimaryFlag()))
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

	private String formatBoolean(Boolean aBoolean)
	{
		return Boolean.TRUE.equals(aBoolean) ? MAGIC_Y : MAGIC_N;
	}

	private PartyEcommDetailsResponseData populateEcommDetails(List<Ecomm> ecommList)
	{
		final PartyEcommDetailsResponseData partyEcommDetailsData = new PartyEcommDetailsResponseData();
		final List<EcommMasterData> ecommMasterDataList = new ArrayList();

		for (final Ecomm partyEcommData : ecommList)
		{
			boolean isPrimary = false;
			final EcommMasterData ecommMasterData = new EcommMasterData();
			ecommMasterData.setEcommPartyId(partyEcommData.getPartyId());
			ecommMasterData.setEcommContactPointTypeCd(partyEcommData.getContactPointTypeCd());
			ecommMasterData.setEcommContactPointName(partyEcommData.getContactId()); //According to Chi Duong <Chi_Duong@Amway.com>
			ecommMasterData.setEcommAddr(partyEcommData.getEcommAddr());
			ecommMasterData.setEcommTypeCd(partyEcommData.getEcommTypeCd());
			ecommMasterData.setStatusCd((partyEcommData.getStatusCd()));
			ecommMasterData.setContactPointTypeCd((partyEcommData.getContactPointTypeCd()));
			ecommMasterData.setContactPointName(partyEcommData.getEcommName());


			final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
			for (final ContactUsage usageData : partyEcommData.getUsageList())
			{
				//this is a legacy code from v2 populator
				final UsageRequestData usageRequestData = new UsageRequestData();
				usageRequestData.setContactPointPurposeCd(usageData.getContactPointPurposeCd());
				boolean primaryLocal = Boolean.TRUE.equals(usageData.isPrimaryFlag());
				usageRequestData.setPrimaryFlag(primaryLocal ? MAGIC_Y : MAGIC_N);
				if (primaryLocal)
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

	private PartyNameDetailsRequestData populatePartyName(PartyName partyPersonName, Party party)
	{
		final PartyNameDetailsRequestData partyNameDetailsData = new PartyNameDetailsRequestData();
		final LocaleNameData localeNameData = new LocaleNameData();
		if (partyPersonName != null && partyPersonName.getLocaleName() != null)
		{
			NameInfo localeName = partyPersonName.getLocaleName();
			localeNameData.setGivenName(localeName.getGivenName());
			localeNameData.setFamilyName(localeName.getFamilyName());
			partyNameDetailsData.setPartyId(party.getPartyMst().getPartyId());
		}
		partyNameDetailsData.setLocaleNameData(localeNameData);
		partyNameDetailsData.setPersonNameTypeCd((partyPersonName.getPersonNameTypeCd()));
		partyNameDetailsData.setLanguageCd((partyPersonName.getLanguageCd()));
		return partyNameDetailsData;

	}

	private List<PersonalIdDetailsData> populatePersonalIdDetails(List<PersonalId> personalIdOutputList, Party party)
	{
		final List<PersonalIdDetailsData> personalIdList = new ArrayList<>();
		if (personalIdOutputList != null)
		{
			for (final PersonalId personalIdOutput : personalIdOutputList)
			{
				final PersonalIdDetailsData personalIdDetailsData = new PersonalIdDetailsData();
				personalIdDetailsData.setPersonalId((personalIdOutput.getPersonalId()));
				personalIdDetailsData.setPersonalIdTypeCd((personalIdOutput.getPersonalIdTypeCd()));
				personalIdDetailsData.setExpirationDate((personalIdOutput.getExpirationDate()));
				personalIdDetailsData.setEffectiveDate((personalIdOutput.getEffectiveDate()));
				personalIdList.add(personalIdDetailsData);
			}
		}
		return personalIdList;

	}

	private TaxDetailsData populateTaxDetailsData(Tax taxDetailsOutput)
	{
		final TaxDetailsData taxDetailsData = new TaxDetailsData();
		if (taxDetailsOutput != null)
		{
			taxDetailsData.setTaxId((taxDetailsOutput.getTaxId()));
			taxDetailsData.setTaxIdType((taxDetailsOutput.getTaxTypeCd()));
			taxDetailsData.setTaxExpirationDate((taxDetailsOutput.getExpirationDate()));
		}
		return taxDetailsData;
	}

	public List<AddressData> populateAddressList(final List<Address> addressList)
	{
		// @formatter:off
		return Optional.ofNullable(addressList)
				.orElse(Collections.emptyList())
				.stream()
				.filter(address -> ADDRESS_VALID_STATUS.equals(address.getStatusCd()))
				.map(this::convertAddress)
				.collect(Collectors.toList());
		// @formatter:on
	}

	protected AddressData convertAddress(Address address)
	{
		final AddressData addressData = new AddressData();

		addressData.setPartyId(address.getPartyId());
		addressData.setTown(address.getCityName());
		final String[] line1Array = (address.getAddrStreet()).split(",");
		for (int i = 0; i < line1Array.length; i++)
		{
			if (i == 0)
			{
				addressData.setLine1(line1Array[0].trim());
			}
			else if (i == 1)
			{
				addressData.setNumber(line1Array[1].trim());
			}
		}

		addressData.setLine2(address.getAddrLineTwo());
		addressData.setLandmark(address.getAddrLineFour());
		addressData.setPostalCode(address.getPostalCd());
		addressData.setCompliment(address.getAddrLineThree());
		addressData.setCounty(address.getCountyName());

		if (StringUtils.isNotBlank(address.getCntryCd()))
		{
			final CountryModel countryModel = getCommonI18NService().getCountry((address.getCntryCd()));
			addressData.setCountry(getCountryConverter().convert(countryModel));

			try
			{

				if (StringUtils.isNotBlank(address.getStateCd()))
				{
					final RegionModel regionModel = getCommonI18NService().getRegion(countryModel,
							(address.getCntryCd()) + "-" + (address.getStateCd()));
					final RegionData regionData = getRegionConverter().convert(regionModel);
					addressData.setRegion(regionData);

				}

			}
			catch (final Exception ex)
			{
				LOG.error("Couldn't convert region data", ex);
			}

			try
			{
				if (StringUtils.isNotBlank(address.getTerritory()))
				{
					final RegionModel regionModel = getCommonI18NService().getRegion(countryModel,
							(address.getCntryCd()) + "-" + (address.getTerritory()));
					final RegionData regionData = getRegionConverter().convert(regionModel);
					addressData.setTerritory(regionData);
				}
			}
			catch (final Exception ex)
			{
				LOG.error("Couldn't convert region data", ex);
			}

		}

		addressData.setId(address.getContactPointName());
		addressData.setContactPointName(address.getContactPointName());
		addressData.setContactPointTypeCd(address.getContactPointTypeCd());
		addressData.setAddrDeliveryTypeCd(address.getAddrDeliveryTypeCd());
		addressData.setFirstName(address.getUsageList().get(0).getCareOf());
		addressData.setVisibleInAddressBook(true);

		for (final AddressContactUsage usageData : address.getUsageList())
		{
			setAddressType(addressData, usageData);
		}

		return addressData;
	}

	protected void setAddressType(final AddressData addressData, final AddressContactUsage usageData){
		final String contactPointPurposeCode = Optional.ofNullable(usageData.getContactPointPurposeCd()).orElse("");

		//final boolean isPrimary = LynxCoreConstants.MagicDmsBoolean.TRUE.equalsIgnoreCase(usageData.isPrimaryFlag());
		final boolean isPrimary = usageData.isPrimaryFlag();

		switch (contactPointPurposeCode){
			case BILLING: 			addressData.setBillingAddress(true); addressData.setPrimaryBilling(isPrimary); break;
			case MAILING: 			addressData.setMailingAddress(true); addressData.setPrimaryMailing(isPrimary); break;
			case SHIPPING: 		addressData.setShippingAddress(true); addressData.setPrimaryShipping(isPrimary); break;
			case REGISTRATION: 	addressData.setRegistrationAddress(true); break;
		}

	}

	private SponsorDetailsData populateSponsorDetails(Sponsor sponsorDetails)
	{
		final SponsorDetailsData sponsorDetailsData = new SponsorDetailsData();
		if (sponsorDetails != null)
		{
			sponsorDetailsData.setSponsorABONo((sponsorDetails.getSponsorABONo()));
			sponsorDetailsData.setSponsorEmail((sponsorDetails.getSponsorEmail()));
			sponsorDetailsData.setSponsorName((sponsorDetails.getSponsorName()));
			sponsorDetailsData.setSponsorPhone((sponsorDetails.getSponsorPhone()));
		}
		return sponsorDetailsData;
	}

	/**
	 * To populate account master details.
	 *
	 * @param accountDetails
	 * @return AccountMasterDetailsData
	 */
	public AccountMasterDetailsData populateAccountMasterDetails(final AccountMaster accountDetails)
	{
		final AccountMasterDetailsData accountMasterDetails = new AccountMasterDetailsData();
		if (accountDetails != null)
		{
			accountMasterDetails.setAccountName((accountDetails.getAccountName()));
			accountMasterDetails.setAccountNumber((accountDetails.getAboNum()));
			accountMasterDetails.setAccountType((accountDetails.getAccountSubTypeCd()));
			final SimpleDateFormat sdf = new SimpleDateFormat(DMSDATEPATTERN);
			accountMasterDetails.setEntryDate(parseDate(accountDetails.getAboEntryDate(),sdf,"accountDetails.aboEntryDate"));
			accountMasterDetails.setExpirationDate(parseDate(accountDetails.getAboExpireDate(),sdf,"accountDetails.aboExpireDate"));
			accountMasterDetails.setAccountName((accountDetails.getAccountName()));
			accountMasterDetails.setBusinessNature((accountDetails.getAccountSubTypeCd()));
			accountMasterDetails.setSegmentCd((accountDetails.getSegmentCd()));
			accountMasterDetails.setStatusCd((accountDetails.getStatusCd()));
			accountMasterDetails.setAccountCreditLimit((accountDetails.getAccountCreditLimit()));
			accountMasterDetails.setCntryCd((accountDetails.getCntryCd()));
			accountMasterDetails.setCurrencyCd((accountDetails.getCurrencyCd()));
			accountMasterDetails.setAccountCreditStatusCd((accountDetails.getAccountCreditStatusCd()));
			accountMasterDetails.setLanguageCd((accountDetails.getLanguageCd()));
			accountMasterDetails.setLglEntityType((accountDetails.getLglEntityType()));
			accountMasterDetails.setLoaCd((accountDetails.getLoaCd()));
			accountMasterDetails.setOrderCreditLimit((accountDetails.getOrderCreditLimit()));
			accountMasterDetails.setSalesPlanAff((accountDetails.getSalesPlanAff()));
			accountMasterDetails.setAccountSubTypeCd((accountDetails.getAccountSubTypeCd()));
			accountMasterDetails.setRenewalFlag(accountDetails.getRenewalFlag());
		}
		return accountMasterDetails;
	}


	private List<AccountBalanceData> populateAccountBalanceData(List<AccountBalance> accountBalanceList)
	{
		//TODO AY to populator
		final List<AccountBalanceData> accountBalanceDatas = new ArrayList<AccountBalanceData>();
		final String currentIsocode = commonI18NService.getCurrentCurrency().getIsocode();
		for (final AccountBalance accountBalance : accountBalanceList)
		{
			if (StringUtils.equalsIgnoreCase(currentIsocode, (accountBalance.getCurrencyCd())))
			{
				final AccountBalanceData accountBalanceData = new AccountBalanceData();

				accountBalanceData.setBalanceTypeCd((accountBalance.getBalanceTypeCd()));
				accountBalanceData.setBalanceAmount((accountBalance.getBalanceAmount()));
				accountBalanceData.setCurrencyCd((accountBalance.getCurrencyCd()));
				accountBalanceData.setInstrumentId((accountBalance.getInstrumentId()));
				accountBalanceDatas.add(accountBalanceData);
			}
		}
		return accountBalanceDatas;
	}

	private BlockPrivDetailsData populateBlockPrivDetailsData(BlockPrivilege privilegeData)
	{
		final BlockPrivDetailsData blockPrivDetailsData = new BlockPrivDetailsData();
		blockPrivDetailsData.setBlockPrivTypeId(privilegeData.getBlockPrivTypeId());
		return blockPrivDetailsData;
	}

	public Converter<CountryModel, CountryData> getCountryConverter()
	{
		return countryConverter;
	}

	public void setCountryConverter(Converter<CountryModel, CountryData> countryConverter)
	{
		this.countryConverter = countryConverter;
	}

	public Converter<RegionModel, RegionData> getRegionConverter()
	{
		return regionConverter;
	}

	public void setRegionConverter(Converter<RegionModel, RegionData> regionConverter)
	{
		this.regionConverter = regionConverter;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
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

}
