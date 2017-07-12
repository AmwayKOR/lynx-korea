/**
 *
 */
package com.amway.integration.cis.dms.customerregistration.impl;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.constants.AmwaycoreConstants.AccountTypes;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.RegisterRequestData;
import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.cis.dms.customerregistration.mock.impl.MockProcessRegistrationServiceImpl;


/**
 * @author admin
 */
public class DefaultProcessCustomerRegistrationServiceTest extends ServicelayerTransactionalTest
{
	@Resource(name = "mockProcessRegistrationServiceImpl")
	private MockProcessRegistrationServiceImpl defaultProcessCustomerRegistrationService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private SessionService sessionService;

	private final RegisterRequestData regsterRequestData = new RegisterRequestData();

	private static final Logger LOG = Logger.getLogger(DefaultProcessCustomerRegistrationServiceTest.class);


	@Before
	public void setup() throws ImpExException
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");


		baseSiteService.setCurrentBaseSite("storetemplate", true);

		regsterRequestData.setLegalEntity("");
		regsterRequestData.setFirstName("test");
		regsterRequestData.setLastName("class");
		regsterRequestData.setDob("04101992");

		final RegionData regionData = new RegionData();
		regionData.setName("Sao Paulo");
		regionData.setIsocode("BR-SP");
		regionData.setIsocodeShort("SP");

		final CountryData countryData = new CountryData();
		countryData.setIsocode("BR");
		countryData.setName("Brazil");


		final AddressData enteredAddress = new AddressData();

		enteredAddress.setCountry(countryData);
		enteredAddress.setRegion(regionData);
		//enteredAddress.getCountry().setIsocode("BR");
		enteredAddress.setLandmark("Opposite");
		enteredAddress.setLine1("R VISCONDE DE PORTO SEGURO, 1-999999999");
		enteredAddress.setLine2("TATUAPE");
		enteredAddress.setCompliment("none");
		enteredAddress.setNumber("1234");
		enteredAddress.setTown("Sao Palou");
		enteredAddress.setPostalCode("0462400");
		//enteredAddress.getRegion().setIsocodeShort("SP");
		enteredAddress.setValidationResultOverRideFlg("Y");
		enteredAddress.setAddressValidatedDate(null);
		enteredAddress.setValidationResultCd("notValidated");
		enteredAddress.setContactPointName("HomeAddress");

		final PhoneMasterRequestData phoneData = new PhoneMasterRequestData();
		phoneData.setPhoneAreaCd("55");
		phoneData.setPhoneCntryCd("11");
		phoneData.setPhoneExtNum("11");
		phoneData.setPhoneLocalNum("12345678");
		phoneData.setSmsCapableFlag("N");

		final UsageRequestData usageData = new UsageRequestData();
		usageData.setContactPointPurposeCd("GeneralPurpose");
		usageData.setPrimaryFlag("N");

		final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
		usageDataList.add(usageData);

		phoneData.setUsageData(usageDataList);
		phoneData.setContactPointTypeCd("HomePhone");

		final List<PhoneMasterRequestData> phoneMasterRequestData = new ArrayList<PhoneMasterRequestData>();
		phoneMasterRequestData.add(phoneData);

		regsterRequestData.setHasIntlPrimaryBusiness(false);
		regsterRequestData.setPhoneDetails(phoneMasterRequestData);
		regsterRequestData.setIntlPrimarySponsorNumber("1");
		regsterRequestData.setIntlPrimaryAffiliateNumber("1");
		regsterRequestData.setPrimarySponsor(true);
		regsterRequestData.setSponsorId("500");
		regsterRequestData.setEmailId("test@gmail.com");
		regsterRequestData.setTaxId("12345678909");
		regsterRequestData.setAddress(enteredAddress);
	}

	@Test
	public void shouldDMSProcessCustomerRegistrationTest()
	{
		final RegistrationResultData response = defaultProcessCustomerRegistrationService.process(regsterRequestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

	@Test
	public void shouldDMSProcessABORegistrationTest()
	{
		regsterRequestData.setRegisterAs(AccountTypes.BUSINESS_OWNER);
		regsterRequestData.setAboRegistration(true);
		regsterRequestData.setAboNum("7000020315");
		final RegistrationResultData response = defaultProcessCustomerRegistrationService.process(regsterRequestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}



}
