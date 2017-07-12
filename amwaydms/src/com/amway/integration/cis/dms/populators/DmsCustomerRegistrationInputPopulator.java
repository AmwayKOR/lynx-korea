package com.amway.integration.cis.dms.populators;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.DateFormatManager;

import com.amway.core.constants.AmwaycoreConstants.AccountTypes;
import com.amway.core.constants.AmwaycoreConstants.TaxTypes;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.RegisterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.AddressAttributesInput;
import com.amway.integration.dms.services.AddressDetailInput;
import com.amway.integration.dms.services.CustomerRegistrationInput;
import com.amway.integration.dms.services.EcommAttributesInput;
import com.amway.integration.dms.services.EcommDetailInput;
import com.amway.integration.dms.services.NameAttributesInput;
import com.amway.integration.dms.services.NameInput;
import com.amway.integration.dms.services.PartyAttributesInput;
import com.amway.integration.dms.services.PartyMasterInput;
import com.amway.integration.dms.services.PartyTaxXrefInput;
import com.amway.integration.dms.services.PersonNameInput;
import com.amway.integration.dms.services.PhoneAttributesInput;
import com.amway.integration.dms.services.PhoneDetailInput;
import com.amway.integration.dms.services.SponsorAttributesInput;
import com.amway.integration.dms.services.XrefTypeInput;


/**
 * Populator implementation for {@link RegisterRequestData} as source and {@link CustomerRegistrationInput} as target
 * type.
 */
public class DmsCustomerRegistrationInputPopulator extends AbstractDmsPopulator
		implements Populator<RegisterRequestData, CustomerRegistrationInput>
{
	private BaseStoreService baseStoreService;

	private static String TRUE = "true";
	private static String FALSE = "false";
	private static String DEFAULTID = "1";
	private static String ACCOUNTTYPE = "AmwayBusiness";
	private static String PARTYTYPE = "Person";

	@Override
	public void populate(final RegisterRequestData registerData, final CustomerRegistrationInput target) throws ConversionException
	{

		final BaseStoreModel currentStore = getBaseStoreService().getCurrentBaseStore();
		final AddressData enteredAddress = registerData.getAddress();
		if (registerData.isAllowMarketingMails())
		{
			target.setAuthorizeEmail(DEFAULTID);
		}
		//updated to send the starter kit code entered in step1
		target.setAboNum(
				StringUtils.isNotEmpty(registerData.getStarterKitCode()) ? registerData.getStarterKitCode() : StringUtils.EMPTY);
		target.setAccountSubTypeCd(registerData.getRegisterAs());
		target.setAccountTypeCd(registerData.getRegisterAs());
		if (AccountTypes.BUSINESS_OWNER.equals(registerData.getRegisterAs()))
		{
			target.setAccountTypeCd(ACCOUNTTYPE);
		}
		target.setCntryCd(enteredAddress.getCountry().getIsocode());
		target.setContractAcceptFlg(TRUE);
		target.setPartyTypeId(PARTYTYPE);
		target.setProcessTypeCd("Registration");
		target.setSalesPlanAff(currentStore.getAffiliateNumber());
		target.setSaveRegestrationFlg(FALSE);
		target.setStepNum(DEFAULTID);
		target.setLglEnttyType(registerData.getLegalEntity());

		//setting address info
		final AddressAttributesInput addresses = new AddressAttributesInput();
		final AddressDetailInput address = new AddressDetailInput();
		final XrefTypeInput xrefTypeInput = new XrefTypeInput();

		address.setAddrDeliveryTypeCd("MailingAddress");
		address.setAddrLineFour(enteredAddress.getLandmark());
		address.setAddrLineThree(enteredAddress.getLine2());
		address.setAddrLineTwo(enteredAddress.getCompliment());
		//AT-1991 Make street name to 42 chars and append building number(apartment)
		address.setAddrStreet(
				StringUtils.substring(enteredAddress.getLine1(), 0, 42) + (StringUtils.isNotBlank(enteredAddress.getNumber()) ?
						"," + enteredAddress.getNumber() :
						StringUtils.EMPTY));
		address.setCharSetCd(StringUtils.EMPTY);
		address.setCityName(enteredAddress.getTown());
		address.setCntryCd(enteredAddress.getCountry().getIsocode());
		address.setCountyName(StringUtils.EMPTY);
		address.setDeliveryInstr(StringUtils.EMPTY);
		address.setGeoCd(StringUtils.EMPTY);
		address.setLanguageCd(currentStore.getDefaultLanguage().getIsocode());
		address.setLatitude(StringUtils.EMPTY);
		address.setPostalCd(enteredAddress.getPostalCode());
		address.setStateCd(enteredAddress.getRegion().getIsocodeShort());
		address.setTaxJursidictionCd(StringUtils.EMPTY);
		address.setTzOffset(StringUtils.EMPTY);
		address.setUsePrimaryFlag(TRUE);
		address.setWarehouseCd(StringUtils.EMPTY);
		address.setValidationResultOverRideFlg(enteredAddress.getValidationResultOverRideFlg());
		address.setAddressValidatedDate(formatDate(enteredAddress.getAddressValidatedDate(), "MMddyyyy", DMSDATEPATTERN));
		address.setValidationResultCd(enteredAddress.getValidationResultCd());

		final String fName = registerData.getFirstName() + " " + registerData.getLastName();
		xrefTypeInput.setCareOf((fName.length() > 50) ? fName.substring(0, 50) : fName);
		xrefTypeInput.setCntryCd(enteredAddress.getCountry().getIsocode());
		xrefTypeInput.setContactPointName(enteredAddress.getContactPointName());
		xrefTypeInput.setContactPointPurposeCd("Shipping");
		xrefTypeInput.setContactPointTypeCd("PersonalEmail");
		xrefTypeInput.setPrimaryFlag(FALSE);
		xrefTypeInput.setSeqNum(StringUtils.EMPTY);
		xrefTypeInput.setStatusFlag(FALSE);

		address.getAddressTypeList().add(xrefTypeInput);
		addresses.setId("1");
		addresses.getAddressDetail().add(address);
		target.getAddrAttributes().add(addresses);

		// setting name attribute
		final NameAttributesInput nameAttributesInput = new NameAttributesInput();
		final PersonNameInput personNameInput = new PersonNameInput();
		final NameInput LatinInput = new NameInput();
		LatinInput.setFamilyName(registerData.getLastName());
		LatinInput.setGivenName(registerData.getFirstName());
		LatinInput.setMiddleName(StringUtils.EMPTY);
		LatinInput.setSuffix(StringUtils.EMPTY);
		LatinInput.setTitle(StringUtils.EMPTY);

		final NameInput LocalInput = new NameInput();
		LocalInput.setFamilyName(registerData.getLastName());
		LocalInput.setGivenName(registerData.getFirstName());
		LocalInput.setMiddleName(StringUtils.EMPTY);
		LocalInput.setSuffix(StringUtils.EMPTY);
		LocalInput.setTitle(StringUtils.EMPTY);

		personNameInput.setCharSetCd(StringUtils.EMPTY);
		personNameInput.setLanguageCd(currentStore.getDefaultLanguage().getIsocode());
		personNameInput.setPersonNameTypeCd("Legal");
		personNameInput.setPreferredName(StringUtils.EMPTY);
		personNameInput.setLatinName(LatinInput);
		personNameInput.setLocaleName(LocalInput);

		nameAttributesInput.getPersonName().add(personNameInput);
		nameAttributesInput.setId(DEFAULTID);
		target.getNameAttributes().add(nameAttributesInput);

		//setting party attribute
		final PartyAttributesInput partyAttributesInput = new PartyAttributesInput();
		final PartyMasterInput partyMasterInput = new PartyMasterInput();
		partyMasterInput.setBirthCountryCd(enteredAddress.getCountry().getIsocode());

		if (StringUtils.isNotBlank(registerData.getDob()))
		{
			final String dateOfbirth = registerData.getDob().replaceAll("/", "");
			final SimpleDateFormat sdf = new SimpleDateFormat(DMSDATEPATTERN);
			String birthDate;
			try
			{
				birthDate = sdf.format(new DateFormatManager("ddMMyyyy").getDateFormatInstance().parse(dateOfbirth));
				birthDate = birthDate.split("T")[0] + "T00:00:00+00:00";
				partyMasterInput.setBirthDate(birthDate);
			}
			catch (final ParseException e)
			{
				LOG.warn("Parse exception on birth date.", e);
			}
		}
		else
		{
			partyMasterInput.setBirthDate(registerData.getDob());
		}

		//partyMasterInput.setPartyIsMinorFlag(StringUtils.EMPTY);[AT-3704] removed for cscockpit chcekbox on register page
		if (registerData.isMinorFlag())
		{
			partyMasterInput.setPartyIsMinorFlag("Y");
		}
		else
		{
			partyMasterInput.setPartyIsMinorFlag("N");
		}
		partyMasterInput.setEthnicCd("Latin");
		partyMasterInput.setGenderCd(StringUtils.EMPTY);
		partyMasterInput.setLanguageCd(currentStore.getDefaultLanguage().getIsocode());

		partyMasterInput.setPartyType(PARTYTYPE);
		partyMasterInput.setPrimaryOnAccount(TRUE);
		partyMasterInput.setRelationshipToPrimaryPartyCd(StringUtils.EMPTY);
		partyMasterInput.setRoleCd(StringUtils.EMPTY);
		partyMasterInput.setUserId(StringUtils.EMPTY);
		partyMasterInput.setUserPasswd(StringUtils.EMPTY);
		partyMasterInput.setUserPin(StringUtils.EMPTY);
		partyAttributesInput.setPartyMaster(partyMasterInput);
		partyAttributesInput.setId(DEFAULTID);
		target.getPartyAttributes().add(partyAttributesInput);

		//setting phone attribute
		final PhoneAttributesInput phoneAttributesInput = new PhoneAttributesInput();
		if (CollectionUtils.isNotEmpty(registerData.getPhoneDetails()))
		{
			phoneAttributesInput.setId(DEFAULTID);
			for (final PhoneMasterRequestData phoneDetails : registerData.getPhoneDetails())
			{

				final PhoneDetailInput phoneDetailInput = new PhoneDetailInput();
				phoneDetailInput.setCntryCd(enteredAddress.getCountry().getIsocode());
				phoneDetailInput.setPhoneAreaCd(phoneDetails.getPhoneAreaCd());
				phoneDetailInput.setPhoneCntryCd(phoneDetails.getPhoneCntryCd());
				phoneDetailInput.setPhoneExtNum(phoneDetails.getPhoneExtNum());
				phoneDetailInput.setPhoneLocalNum(phoneDetails.getPhoneLocalNum());
				if (CollectionUtils.isNotEmpty(phoneDetails.getUsageData()))
				{
					for (final UsageRequestData usageRequestData : phoneDetails.getUsageData())
					{
						final XrefTypeInput usageData = new XrefTypeInput();
						usageData.setContactPointPurposeCd(usageRequestData.getContactPointPurposeCd());
						usageData.setPrimaryFlag(usageRequestData.getPrimaryFlag());
						usageData.setStatusFlag(FALSE);
						usageData.setContactPointTypeCd(phoneDetails.getContactPointTypeCd());
						phoneDetailInput.getPhoneTypeList().add(usageData);
					}
				}
				phoneDetailInput.setSmsCapableFlag(phoneDetails.getSmsCapableFlag());
				phoneDetailInput.setUsePrimaryFlag(TRUE);
				phoneAttributesInput.getPhoneDetail().add(phoneDetailInput);
			}
		}
		target.getPhoneAttributes().add(phoneAttributesInput);

		//setting sponsor attributes
		final SponsorAttributesInput sponsorDetails = new SponsorAttributesInput();
		sponsorDetails.setIntlPrimaryBusFlg(FALSE);
		if (registerData.isHasIntlPrimaryBusiness())
		{
			sponsorDetails.setAcctIntlSponsorAboNum(registerData.getIntlPrimarySponsorNumber());
			sponsorDetails.setAcctIntlSponsorCntryCd(registerData.getIntlPrimaryAffiliateNumber());
			if (registerData.isPrimarySponsor())
			{
				sponsorDetails.setIntlPrimaryBusFlg(TRUE);
			}
		}
		if (registerData.isHasSponsor() && StringUtils.isNotBlank(registerData.getSponsorId()))
		{
			sponsorDetails.setAcctSponsorAboNum(registerData.getSponsorId());
		}
		else
		{
			sponsorDetails.setSponsorListFlag(TRUE);
			sponsorDetails.setAcctSponsorAboNum(registerData.getSlectedSponsorFromLocSearch());
		}
		sponsorDetails.setSponsorListFlag(FALSE);
		sponsorDetails.setAcctSponsorAffCd(currentStore.getAffiliateNumber());
		target.setSponAttributes(sponsorDetails);

		//Eccom Details
		final EcommAttributesInput eccomInput = new EcommAttributesInput();
		final EcommDetailInput eccomDetailInput = new EcommDetailInput();
		eccomDetailInput.setAllowForLogin(FALSE);
		eccomDetailInput.setEcommAddr(registerData.getEmailId());
		eccomDetailInput.setEcommName("Email");
		eccomDetailInput.setEcommTypeCd("Email");
		final XrefTypeInput xrefTypeInputEcomm = new XrefTypeInput();
		xrefTypeInputEcomm.setCareOf(StringUtils.EMPTY);
		xrefTypeInputEcomm.setCntryCd(enteredAddress.getCountry().getIsocode());
		xrefTypeInputEcomm.setContactPointName(enteredAddress.getContactPointName());
		xrefTypeInputEcomm.setContactPointPurposeCd("GeneralPurpose");
		xrefTypeInputEcomm.setContactPointTypeCd("PersonalEmail");
		xrefTypeInputEcomm.setPrimaryFlag(TRUE);
		xrefTypeInputEcomm.setSeqNum(StringUtils.EMPTY);
		eccomDetailInput.getEcommTypeList().add(xrefTypeInputEcomm);
		eccomDetailInput.setLanguageCd(StringUtils.EMPTY);
		eccomDetailInput.setMaxMesgLength(StringUtils.EMPTY);
		eccomDetailInput.setUsePrimaryFlag(TRUE);
		eccomInput.getEcommDetail().add(eccomDetailInput);
		eccomInput.setId(DEFAULTID);
		target.getECommAttributes().add(eccomInput);

		//setting tax attribute
		final PartyTaxXrefInput partyTaxXrefInput = new PartyTaxXrefInput();
		partyTaxXrefInput.setCntryCd(enteredAddress.getCountry().getIsocode());
		partyTaxXrefInput.setTaxId(registerData.getTaxId());
		partyTaxXrefInput.setTaxTypeCd(TaxTypes.CPF);
		if (!StringUtils.isBlank(registerData.getLegalEntity()))
		{
			partyTaxXrefInput.setTaxTypeCd(TaxTypes.CNPJ);
		}

		partyTaxXrefInput.setTaxTypeExpireDate(formatDate(StringUtils.EMPTY, "ddMMyyyy", DMSDATEPATTERN));
		target.setTaxAttributes(partyTaxXrefInput);
	}

	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}
}
