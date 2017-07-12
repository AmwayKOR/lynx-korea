/**
 *
 */
package com.amway.core.account.service.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.session.MockSession;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.account.dao.AmwayAccountDao;
import com.amway.core.data.AccountMasterDetailsData;
import com.amway.core.data.AmwayProfileRequestData;
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
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;



@UnitTest
public class DefaultAmwayAccountServiceUnitTest
{
	private final static Logger LOG = Logger.getLogger(DefaultAmwayAccountServiceUnitTest.class);

	@Mock
	private AmwayAccountDao amwayAccountDao;
	@Mock
	private BaseStoreService baseStoreService;
	@Mock
	private BaseSiteService baseSiteService;
	@Mock
	private AmwayAccountCommerceService amwayAccountCommerceService;
	/*
	 * @Mock private DmsService<AmwayProfileRequestData, AmwayProfileResponseData> amwayProfileService;
	 */ MockAmwayProfileService amwayProfileService;
	@Mock
	private SessionService sessionService;
	@InjectMocks
	private final DefaultAmwayAccountService defaultAmwayAccountService = new DefaultAmwayAccountService();

	private final static String ACCOUNT_UID = "123456";
	private final static String ACCOUNT_NAME = "abc";
	public static final String MINDETAIL = "MinDetail";
	public static final String FULLDETAIL = "FullDetail";
	public static final String ORDERPAYMENTS = "OrderPayments";
	private AmwayAccountModel account1;
	private AmwayAccountModel account2;
	private AmwayAccountModel account3;

	private AmwayProfileRequestData amwayProfileRequestData;
	private AmwayProfileResponseData amwayProfileResponseData;
	private Session session;
	private CMSSiteModel site;
	private BaseStoreModel store;
	private CountryModel country;
	private UserModel userModel;
	private OrderModel order;



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		amwayProfileService = new MockAmwayProfileService();
		defaultAmwayAccountService.setAmwayAccountDao(amwayAccountDao);
		defaultAmwayAccountService.setAmwayAccountCommerceService(amwayAccountCommerceService);
		defaultAmwayAccountService.setAmwayProfileService(amwayProfileService);
		defaultAmwayAccountService.setBaseSiteService(baseSiteService);
		defaultAmwayAccountService.setBaseStoreService(baseStoreService);
		defaultAmwayAccountService.setAmwayProfileService(amwayProfileService);
		account1 = Mockito.mock(AmwayAccountModel.class);
		account1.setCode("123456");
		account1.setName("ABC");

		account2 = Mockito.mock(AmwayAccountModel.class);
		account2.setCode("654321");
		account2.setName("ABC");

		account3 = Mockito.mock(AmwayAccountModel.class);
		account3.setCode("121212");
		account3.setName("ABC");

		session = new MockSession();

		amwayProfileRequestData = Mockito.mock(AmwayProfileRequestData.class);
		amwayProfileRequestData.setSalesPlanAff("170");
		amwayProfileRequestData.setAboNum(account1.getCode());
		amwayProfileRequestData.setClientCntryCd("BR");
		amwayProfileRequestData.setDeltailLevelCd(FULLDETAIL);
		createAmwayProfileResponseData("ABO");

		country = Mockito.mock(CountryModel.class);
		store = Mockito.mock(BaseStoreModel.class);
		given(baseStoreService.getCurrentBaseStore()).willReturn(store);
		given(store.getAffiliateNumber()).willReturn("110");

		site = Mockito.mock(CMSSiteModel.class);
		given(baseSiteService.getCurrentBaseSite()).willReturn(site);
		given(site.getDefaultCountry()).willReturn(country);
		given(country.getIsocode()).willReturn("BR");

		userModel = Mockito.mock(UserModel.class);
		given(amwayAccountCommerceService.getAccountFromUser(userModel)).willReturn(account1);
		order = Mockito.mock(OrderModel.class);
		given(order.getAccount()).willReturn(account1);
		given(order.getStore()).willReturn(store);
		given(order.getSite()).willReturn(site);
	}



	/**
	 * Test method for
	 * {@link com.amway.core.account.service.impl.DefaultAmwayAccountService#findAccount(java.lang.String)}.
	 */
	@Test
	public void testFindAccount()
	{
		given(amwayAccountDao.getAccount(ACCOUNT_UID)).willReturn(Arrays.asList(account1));
		final AmwayAccountModel accountModel = defaultAmwayAccountService.findAccount(ACCOUNT_UID);
		Assert.assertNotNull(accountModel);
		Assert.assertEquals(account1.getCode(), accountModel.getCode());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.service.impl.DefaultAmwayAccountService#findAccountsForAbo(java.lang.String)}.
	 */
	@Test
	public void testFindAccountsForAbo()
	{
		given(amwayAccountDao.getAccountsForUidOrName(ACCOUNT_NAME)).willReturn(Arrays.asList(account1, account2, account3));
		final List<AmwayAccountModel> accountList = defaultAmwayAccountService.findAccountsForAbo(ACCOUNT_NAME);
		Assert.assertTrue(CollectionUtils.isNotEmpty(accountList));
		Assert.assertEquals("Account count shout be 3 ", 3, CollectionUtils.size(accountList));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.service.impl.DefaultAmwayAccountService#getAccountProfile(java.lang.String)}.
	 */
	@Test
	public void testFullGetAccountProfile()
	{
		given(amwayAccountCommerceService.getCurrentAccount()).willReturn(account1);
		//		given(defaultAmwayAccountService.createAmwayProfileRequestData(FULLDETAIL, account1)).willReturn(amwayProfileRequestData);
		//		given(amwayProfileService.process(amwayProfileRequestData)).willReturn(amwayProfileResponseData);
		given(sessionService.getCurrentSession()).willReturn(session);
		final AmwayProfileResponseData amwayProfileResponse = defaultAmwayAccountService.getAccountProfile(FULLDETAIL);
		Assert.assertNotNull(amwayProfileResponse);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.service.impl.DefaultAmwayAccountService#getCustomerAccountProfile(java.lang.String, de.hybris.platform.core.model.user.UserModel)}
	 * .
	 */
	@Test
	public void testGetCustomerAccountProfile()
	{
		final AmwayProfileResponseData amwayProfileResponse = defaultAmwayAccountService
				.getCustomerAccountProfile(FULLDETAIL, userModel);
		Assert.assertNotNull(amwayProfileResponse);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.service.impl.DefaultAmwayAccountService#getAccountProfileForOrder(java.lang.String, de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testGetAccountProfileForOrder()
	{
		final AmwayProfileResponseData amwayProfileResponse = defaultAmwayAccountService
				.getAccountProfileForOrder(FULLDETAIL, order);
		Assert.assertNotNull(amwayProfileResponse);
	}

	/**
	 *
	 */
	private void createAmwayProfileResponseData(final String customerType)
	{
		amwayProfileResponseData = new AmwayProfileResponseData();
		final List<PartyDetailsData> partyList = new ArrayList<PartyDetailsData>();
		final PartyDetailsData partyDetailsData1 = new PartyDetailsData();
		final PartyDetailsData partyDetailsData2 = new PartyDetailsData();
		final List<AccountBalanceData> accountBalanceDatas = new ArrayList<AccountBalanceData>();

		final SponsorDetailsData sponsorDetails = new SponsorDetailsData();
		sponsorDetails.setSponsorName("Sponsor Name");
		sponsorDetails.setSponsorABONo("Sponsoror ABO Number");
		sponsorDetails.setSponsorEmail("Sponsoror Email Id");
		sponsorDetails.setSponsorPhone("Sponsoror Phone Number");

		//Account master details
		final AccountMasterDetailsData masterDetailsData1 = new AccountMasterDetailsData();
		masterDetailsData1.setAccountName("Globality Enterprisees PTY Ltd");
		masterDetailsData1.setAccountNumber("3108033444");
		masterDetailsData1.setAccountType(customerType);
		try
		{
			masterDetailsData1.setEntryDate(new DateFormatManager("MMddyyyy").getDateFormatInstance().parse("04221944"));
			masterDetailsData1.setExpirationDate(new DateFormatManager("MMddyyyy").getDateFormatInstance().parse("12312014"));
		}
		catch (final ParseException e)
		{

			LOG.error("Exception occured during parsing", e);
		}

		masterDetailsData1.setAccountCreditLimit("0");

		//Restrictions Data
		final BlockPrivDetailsData blockPrivDetailsData = new BlockPrivDetailsData();
		blockPrivDetailsData.setBlockPrivTypeId("BlockSponsoring");

		//Account Balance
		final AccountBalanceData accountBalanceData1 = new AccountBalanceData();
		accountBalanceData1.setBalanceTypeCd("Credit");
		accountBalanceData1.setBalanceAmount("1000");
		accountBalanceData1.setCurrencyCd("BRL");
		accountBalanceDatas.add(accountBalanceData1);

		final AccountBalanceData accountBalanceData2 = new AccountBalanceData();
		accountBalanceData2.setBalanceTypeCd("Monetary");
		accountBalanceData2.setBalanceAmount("175");
		accountBalanceData2.setCurrencyCd("BRL");
		accountBalanceDatas.add(accountBalanceData2);

		//Party Mst
		final PartyPersonalDetailsRequestData partyPersonalDetailsData1 = new PartyPersonalDetailsRequestData();
		partyPersonalDetailsData1.setPartyId("77");
		partyPersonalDetailsData1.setUserId("carlos.abo@amway.com");
		partyPersonalDetailsData1.setUserPasswd("carlos123");
		partyPersonalDetailsData1.setBirthdate("12/Dec/1983");
		partyPersonalDetailsData1.setGenderCd("Male");
		partyPersonalDetailsData1.setMaritalStatusCd("Single");
		partyPersonalDetailsData1.setLanguageCd("Portuguese");
		partyPersonalDetailsData1.setBirthCountryCd("USA");
		partyPersonalDetailsData1.setRoleCd("BusinessOwner");
		partyPersonalDetailsData1.setPartyTypeCd("Person");
		partyDetailsData1.setPartyPersonalDetailsData(partyPersonalDetailsData1);

		//party name and ID
		final PartyNameDetailsRequestData partyNameDetailsData1 = new PartyNameDetailsRequestData();
		final LocaleNameData nameData1 = new LocaleNameData();
		nameData1.setGivenName("Carlos");
		nameData1.setFamilyName("Abo");

		partyNameDetailsData1.setPartyId("77");
		partyNameDetailsData1.setLocaleNameData(nameData1);
		partyDetailsData1.setPartyNameDetailsData(partyNameDetailsData1);

		//Party Phone Details
		final PhoneMasterRequestData phoneMasterData1 = new PhoneMasterRequestData();
		phoneMasterData1.setPhoneId("603084");
		phoneMasterData1.setPhoneExtNum("8888");
		phoneMasterData1.setPhoneLocalNum("9999999999");
		//phoneMasterData1.setContactPointTypeCd("HomePhone");
		phoneMasterData1.setSmsCapableFlag("Y");
		phoneMasterData1.setPhoneCntryCd("55");
		phoneMasterData1.setPhoneAreaCd("859");

		final List<PhoneMasterRequestData> phoneMasterDataList = new ArrayList<PhoneMasterRequestData>();
		phoneMasterDataList.add(phoneMasterData1);

		final PartyPhoneDetailsRequestData partyPhoneDetailsData1 = new PartyPhoneDetailsRequestData();
		partyPhoneDetailsData1.setPartyId("77");
		partyPhoneDetailsData1.setPhoneMasterListData(phoneMasterDataList);
		partyDetailsData1.setPartyPhoneDetailsData(partyPhoneDetailsData1);

		//Party Ecomm Details
		final EcommMasterData ecommMasterData1 = new EcommMasterData();
		final List<EcommMasterData> ecommMasterDataList1 = new ArrayList<>();
		ecommMasterData1.setEcommId("0001");
		ecommMasterData1.setEcommAddr("test@test.com");
		ecommMasterDataList1.add(ecommMasterData1);

		final PartyEcommDetailsResponseData partyEcommDetailsResponseData1 = new PartyEcommDetailsResponseData();
		ecommMasterData1.setEcommPartyId("77");
		ecommMasterData1.setEcommContactPointTypeCd("PersonalEmail");
		//partyEcommDetailsResponseData1.setEcommMasterListData(ecommMasterData1);
		final List<UsageRequestData> usageDataList = new ArrayList<>();
		final UsageRequestData usageData1 = new UsageRequestData();
		final UsageRequestData usageData2 = new UsageRequestData();

		usageData1.setContactPointPurposeCd("Marketing");
		usageData2.setContactPointPurposeCd("Notification");

		usageDataList.add(usageData1);
		usageDataList.add(usageData2);
		//PartyEcommDetailsResponseData1.setUsageData(usageDataList);
		partyDetailsData1.setPartyEcommDetailsResponseData(partyEcommDetailsResponseData1);

		//Tax Details
		final TaxDetailsData taxDetailsData1 = new TaxDetailsData();
		taxDetailsData1.setTaxId("xxxx");
		taxDetailsData1.setTaxIdType("CPF");
		partyDetailsData1.setTaxDetailsData(taxDetailsData1);

		//Address Details
		final RegionData regionData = new RegionData();
		regionData.setName("Sao Paulo");
		regionData.setIsocode("SP");

		final CountryData countryData = new CountryData();
		countryData.setIsocode("BR");
		countryData.setName("Brazil");

		final List<AddressData> addressDataList = new ArrayList<>();
		final AddressData addressData1 = new AddressData();
		addressData1.setId("455093");
		addressData1.setTown("Ada");
		addressData1.setLine1("884/4 test street");
		addressData1.setLine2("TATUAPE");
		addressData1.setPostalCode("49301");
		addressData1.setCountry(countryData);
		addressData1.setRegion(regionData);
		addressData1.setPartyId("77");
		addressData1.setContactPointTypeCd("Billing");
		addressDataList.add(addressData1);
		//partyDetailsData1.setAddressMasterData(addressDataList);

		//PersonalId Details
		final List<PersonalIdDetailsData> personalIdDetailsDataList1 = new ArrayList<>();
		final PersonalIdDetailsData personalIdDetailsData1 = new PersonalIdDetailsData();
		personalIdDetailsData1.setPersonalId("56856");
		personalIdDetailsData1.setPersonalIdTypeCd("StateRegistration");
		personalIdDetailsData1.setExpirationDate("05/18/2017");
		personalIdDetailsDataList1.add(personalIdDetailsData1);
		partyDetailsData1.setPersonalIdDetailsData(personalIdDetailsDataList1);

		partyList.add(partyDetailsData1);

		//Party Details2

		//Party Mst
		final PartyPersonalDetailsRequestData partyPersonalDetailsData2 = new PartyPersonalDetailsRequestData();
		partyPersonalDetailsData2.setPartyId("78");
		partyPersonalDetailsData2.setUserId("Mindy.abo@amway.com");
		partyPersonalDetailsData2.setUserPasswd("mindy123");
		partyPersonalDetailsData2.setBirthdate("10/Dec/1983");
		partyPersonalDetailsData2.setGenderCd("Female");
		partyPersonalDetailsData2.setMaritalStatusCd("Married");
		partyPersonalDetailsData2.setLanguageCd("Spanish");
		partyPersonalDetailsData2.setBirthCountryCd("UK");
		partyDetailsData2.setPartyPersonalDetailsData(partyPersonalDetailsData2);

		//Party name and ID
		final PartyNameDetailsRequestData partyNameDetailsData2 = new PartyNameDetailsRequestData();
		final LocaleNameData nameData2 = new LocaleNameData();
		nameData2.setGivenName("Mindey");
		nameData2.setFamilyName("Abo");
		partyNameDetailsData2.setPartyId("78");
		partyNameDetailsData2.setLocaleNameData(nameData2);
		partyDetailsData2.setPartyNameDetailsData(partyNameDetailsData2);

		//Party Phone Details
		final PhoneMasterRequestData phoneMasterData2 = new PhoneMasterRequestData();
		phoneMasterData2.setPhoneId("808080");
		phoneMasterData2.setPhoneExtNum("4444");
		phoneMasterData2.setPhoneLocalNum("2222222222");
		//phoneMasterData2.setContactPointTypeCd("HomePhone");
		phoneMasterData2.setSmsCapableFlag("N");
		phoneMasterData2.setPhoneCntryCd("55");
		phoneMasterData2.setPhoneAreaCd("859");

		final List<PhoneMasterRequestData> phoneMasterDataList2 = new ArrayList<PhoneMasterRequestData>();
		phoneMasterDataList2.add(phoneMasterData2);

		final PartyPhoneDetailsRequestData partyPhoneDetailsData2 = new PartyPhoneDetailsRequestData();
		partyPhoneDetailsData2.setPartyId("3400029");
		partyPhoneDetailsData2.setPhoneMasterListData(phoneMasterDataList2);
		partyDetailsData2.setPartyPhoneDetailsData(partyPhoneDetailsData2);

		//Party Ecomm Details
		final EcommMasterData ecommMasterData2 = new EcommMasterData();
		ecommMasterData2.setEcommId("0002");
		ecommMasterData2.setEcommAddr("test@test2.com");
		final PartyEcommDetailsResponseData partyEcommDetailsData2 = new PartyEcommDetailsResponseData();
		ecommMasterData2.setEcommPartyId("78");
		ecommMasterData2.setEcommContactPointTypeCd("PersonalEmail");
		final List<UsageRequestData> usageDataList1 = new ArrayList<>();
		final UsageRequestData usageData = new UsageRequestData();
		final UsageRequestData usageData11 = new UsageRequestData();

		usageData.setContactPointPurposeCd("Marketing");
		usageData11.setContactPointPurposeCd("Billing");
		usageDataList1.add(usageData);
		usageDataList1.add(usageData11);
		partyDetailsData2.setPartyEcommDetailsResponseData(partyEcommDetailsData2);

		//Tax Details

		final TaxDetailsData taxDetailsData2 = new TaxDetailsData();
		taxDetailsData2.setTaxId("xxxx");
		taxDetailsData2.setTaxIdType("CPF");
		taxDetailsData2.setTaxExpirationDate("02/19/2018");

		partyDetailsData2.setTaxDetailsData(taxDetailsData2);

		//Address Details
		final List<AddressData> addressDataList2 = new ArrayList<>();
		final AddressData addressData2 = new AddressData();
		addressData2.setPartyId("78");
		addressData2.setId("823049");
		addressData2.setTown("Sao Palou");
		addressData2.setLine1("R VISCONDE DE PORTO SEGURO, 1-999999999");
		addressData2.setLine2("TATUAPE");
		addressData2.setPostalCode("0462400");
		addressData2.setCountry(countryData);
		addressData2.setRegion(regionData);
		addressDataList2.add(addressData2);
		//partyDetailsData2.setAddressMasterData(addressDataList2);

		//PersonalId Details
		final List<PersonalIdDetailsData> personalIdDetailsDataList2 = new ArrayList<>();
		final PersonalIdDetailsData personalIdDetailsData2 = new PersonalIdDetailsData();
		personalIdDetailsData2.setPersonalId("567567");
		personalIdDetailsData2.setPersonalIdTypeCd("StateRegistration");
		personalIdDetailsData2.setExpirationDate("05/20/2020");
		personalIdDetailsDataList2.add(personalIdDetailsData2);
		partyDetailsData2.setPersonalIdDetailsData(personalIdDetailsDataList2);

		partyList.add(partyDetailsData2);


		amwayProfileResponseData.setPartyDetailList(partyList);
		amwayProfileResponseData.setAccountMasterDetails(masterDetailsData1);
		amwayProfileResponseData.setSponsorDetails(sponsorDetails);
		//amwayProfileResponseData.setBlockPrivDetailsData(blockPrivDetailsData);
		amwayProfileResponseData.setAccountBalance(accountBalanceDatas);
		amwayProfileResponseData.setReturnCd(1);
		amwayProfileResponseData.setReturnMessage("Success");

	}
}
