package com.amway.core.service.impl;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.account.service.impl.MockLosAccountDetailService;
import com.amway.core.account.service.impl.MockLosAccountService;
import com.amway.core.account.service.impl.MockLosBonusStatementService;
import com.amway.core.bonusperiod.services.AmwayBonusPeriodService;
import com.amway.core.constants.AmwaycoreConstants.PriceGroups;
import com.amway.core.constants.AmwaycoreConstants.SessionVariables;
import com.amway.core.data.AccountMasterDetailsData;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.data.TaxDetailsData;
import com.amway.core.dms.data.*;
import com.amway.core.enums.AmwayAccountStatus;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.core.los.data.*;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.core.util.AmwayDateHelper;
import com.amway.core.util.AmwayPeriodHelper;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.MockSession;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.mockito.Mockito.when;


@SuppressWarnings("deprecation")
@Ignore("HE-210 - removing powermock and disabling test")
@UnitTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(
//{ Registry.class, AmwayDateHelper.class, AmwayPeriodHelper.class, AmwayCustomerHelper.class })
public class DefaultAmwayAccountCommerceServiceUnitTest
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayAccountCommerceServiceUnitTest.class);
	private static final String ACCOUNT_UID = "1001";
	private static final String CUSTOMER_ID = "1234";
	private static final String AFFILIATE_NUMBER = "170";
	private static final String TIME_ZONE = "Brazil/East";
	private static final String CURRENT_ACTIVE_BONUS_PRIOD = "test_bonus_period";
	@InjectMocks
	private final DefaultAmwayAccountCommerceService accountCommerceService = new DefaultAmwayAccountCommerceService();
	LosAccountResponseData losAccountResponseData;
	@Mock
	private AmwayAccountService amwayAccountService;
	@Mock
	private SessionService sessionService;
	@Mock
	private UserService userService;
	@Mock
	private CalculationService calculationService;
	@Mock
	private CartService cartService;
	@Mock
	private ModelService modelService;
	@Mock
	private CMSSiteService cmsSiteService;
	@Mock
	private BaseStoreService storeService;
	private Session session;
	private AmwayAccountModel accountModel;
	//	@Mock
	//	private LosService<LosAccountRequestData, LosAccountResponseData> losAccountService;
	private MockLosAccountService losAccountService;
	//	@Mock
	//	private LosService<LosAccountRequestData, LosAccountDetailResponseData> losAccountDetailService;
	private MockLosAccountDetailService losAccountDetailService;
	private MockLosBonusStatementService bonusStatementService;
	@Mock
	private ConfigurationService configurationService;
	@Mock
	private Configuration configuration;
	@Mock
	private ApplicationContext context;
	@Mock
	private BaseSiteService baseSiteService;
	@Mock
	private AmwayBonusPeriodService<AmwayBonusPeriodModel, AbstractOrderModel> amwayBonusPeriodService;
	private CustomerModel customer;
	private CartModel cart;
	private CMSSiteModel site;
	private BaseStoreModel store;
	private Set<AmwayAccountModel> accountSet;
	private Set<CustomerModel> parties;
	private AmwayProfileResponseData profileResponseData;
	private LosAccountDetailResponseData losAccountDetailResponseData;
	private AmwayBusinessRestrictionModel bussinessRestriction;
	private Set<AmwayBusinessRestrictionModel> restrictions;
	private LosAccountRequestData losAccountRequestData;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		// mocking static class methods
		PowerMockito.mockStatic(Registry.class);
		PowerMockito.when(Registry.getApplicationContext()).thenReturn(context);
		PowerMockito.when(context.getBean("baseSiteService")).thenReturn(baseSiteService);
		PowerMockito.mockStatic(AmwayDateHelper.class);
		final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));
		PowerMockito.when(AmwayDateHelper.getTimeForSiteTimeZone()).thenReturn(cal.getTime());
		PowerMockito.when(context.getBean("bonusPeriodService")).thenReturn(amwayBonusPeriodService);
		PowerMockito.mockStatic(AmwayPeriodHelper.class);
		PowerMockito.when(context.getBean("sessionService")).thenReturn(sessionService);
		PowerMockito.mockStatic(AmwayCustomerHelper.class);
		losAccountDetailService = new MockLosAccountDetailService();
		accountCommerceService.setLosAccountDetailService(losAccountDetailService);
		losAccountService = new MockLosAccountService();
		bonusStatementService = new MockLosBonusStatementService();
		accountCommerceService.setLosAccountService(losAccountService);
		accountCommerceService.setLosBonusStatementService(bonusStatementService);
		profileResponseData = createAccountProfile();
		losAccountDetailResponseData = createLosAccountData();
		losAccountRequestData = createLosAccountRequestData();
		//		losAccountRequestData = commonResponseDataForLos();
		losAccountResponseData = createLosAccountResponseData();
		accountCommerceService.setCalculationService(calculationService);
		accountCommerceService.setModelService(modelService);
		session = new MockSession();
		site = Mockito.spy(new CMSSiteModel());
		when(cmsSiteService.getCurrentSite()).thenReturn(site);
		accountModel = Mockito.spy(new AmwayAccountModel());
		accountSet = new HashSet<>();

		customer = Mockito.spy(new CustomerModel());
		customer.setCustomerID(CUSTOMER_ID);

		parties = new HashSet<>();
		parties.add(customer);
		accountModel.setParties(parties);
		accountModel.setName("accountName");
		accountSet.add(accountModel);

		customer.setAccounts(accountSet);
		customer.setType(CustomerType.CUSTOMERTYPE_2);
		cart = Mockito.mock(CartModel.class);
		when(customer.getCustomerID()).thenReturn(CUSTOMER_ID);
		when(accountModel.getCode()).thenReturn("1001");
		when(sessionService.getCurrentSession()).thenReturn(session);
		when(userService.getCurrentUser()).thenReturn(customer);
		when(amwayAccountService.findAccount(ACCOUNT_UID)).thenReturn(accountModel);
		when(cartService.getSessionCart()).thenReturn(cart);
		//	when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		bussinessRestriction = Mockito.mock(AmwayBusinessRestrictionModel.class);
		restrictions = Mockito.spy(new HashSet<AmwayBusinessRestrictionModel>());
		restrictions.add(bussinessRestriction);

		store = Mockito.spy(new BaseStoreModel());
		when(store.getAffiliateNumber()).thenReturn(AFFILIATE_NUMBER);
		//		store.setAffiliateNumber(AFFILIATE_NUMBER);
		//	losAccountService = Mockito.spy(new MockLosAccountService<LosAccountRequestData, LosAccountResponseData>());

	}

	/**
	 *
	 */
	private void commonResponseDataForLos()
	{
		final CommonResponseFieldsData resultData = new CommonResponseFieldsData();
		//		if (verifySponsorRequestData.getSponsorNo() != null)
		//		{
		resultData.setReturnMessage("Sponsor Number is valid");
		resultData.setReturnCd(1);
		//		}

	}

	/**
	 *
	 */
	private LosAccountRequestData createLosAccountRequestData()
	{
		final LosAccountRequestData requestData = new LosAccountRequestData();
		requestData.setDepth("2");
		requestData.setAbo("1234567");
		return requestData;
	}

	private LosAccountResponseData createLosAccountResponseData()
	{
		final LosAccountResponseData losAccountResponseData = new LosAccountResponseData();
		losAccountResponseData.setReturnCode(1);
		losAccountResponseData.setReturnMessage("Valid Result.");
		return losAccountResponseData;
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#setCurrentAccount(java.lang.String)}.
	 */
	@Test
	public void testSetCurrentAccountForNullUID()
	{
		when(accountModel.getUserPriceGroup()).thenReturn(UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP));
		when(accountModel.getBusinessNature()).thenReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		accountCommerceService.setCurrentAccount(ACCOUNT_UID);
		Assert.assertNotNull(sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
		Assert.assertEquals(accountModel, sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#setCurrentAccount(java.lang.String)}.
	 */
	@Test
	public void testSetCurrentAccountString()
	{
		when(accountModel.getUserPriceGroup()).thenReturn(UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP));
		when(accountModel.getBusinessNature()).thenReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		accountCommerceService.setCurrentAccount(ACCOUNT_UID);
		Assert.assertNotNull(sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
		Assert.assertEquals(accountModel, sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#setCurrentAccount(de.hybris.platform.core.model.user.UserModel)}
	 * .
	 */
	@Test
	public void testSetCurrentAccountUserModelHasNoAccount()
	{
		when(customer.getAccounts()).thenReturn(null);
		accountCommerceService.setCurrentAccount(customer);
		Assert.assertNull(sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#setCurrentAccount(de.hybris.platform.core.model.user.UserModel)}
	 * .
	 */
	@Test
	public void testSetCurrentAccountUserModel()
	{
		when(customer.getAccounts()).thenReturn(accountSet);
		accountCommerceService.setCurrentAccount(customer);
		Assert.assertNotNull(sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
		Assert.assertEquals(accountModel, sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#updatePriceGroupforSession(java.lang.String)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testUpdatePriceGroupforSession() throws CalculationException
	{
		when(sessionService.getAttribute(Europe1Constants.PARAMS.UPG))
				.thenReturn(UserPriceGroup.valueOf(PriceGroups.RETAIL_PRICE_GROUP));
		accountCommerceService.updatePriceGroupforSession(PriceGroups.RETAIL_PRICE_GROUP);
		Assert.assertEquals(PriceGroups.RETAIL_PRICE_GROUP, sessionService.getAttribute(Europe1Constants.PARAMS.UPG).toString());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#setUPGInSession(java.lang.String)}.
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testSetUPGInSessionString() throws CalculationException
	{
		when(sessionService.getAttribute(Europe1Constants.PARAMS.UPG))
				.thenReturn(UserPriceGroup.valueOf(PriceGroups.RETAIL_PRICE_GROUP));
		accountCommerceService.setUPGInSession(PriceGroups.RETAIL_PRICE_GROUP);
		Assert.assertEquals(PriceGroups.RETAIL_PRICE_GROUP, sessionService.getAttribute(Europe1Constants.PARAMS.UPG).toString());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#convertCustomerToAbo(com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConvertCustomerToAboForNullAccount()
	{
		accountCommerceService.convertCustomerToAbo(null);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#convertCustomerToAbo(com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testConvertCustomerToAbo()
	{
		accountCommerceService.convertCustomerToAbo(accountModel);
		Assert.assertEquals(AmwayBusinessNature.AMWAYBUSINESSNATURE_1, accountModel.getBusinessNature());
		Assert.assertEquals(PriceGroups.ABO_USER_PRICE_GROUP, accountModel.getUserPriceGroup().toString());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#convertAboToCustomer(com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConvertAboToCustomerForNullAccount()
	{
		accountCommerceService.convertCustomerToAbo(null);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#convertAboToCustomer(com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testConvertAboToCustomer()
	{
		accountCommerceService.convertAboToCustomer(accountModel);
		Assert.assertEquals(AmwayBusinessNature.AMWAYBUSINESSNATURE_4, accountModel.getBusinessNature());
		Assert.assertEquals(PriceGroups.RETAIL_PRICE_GROUP, accountModel.getUserPriceGroup().toString());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#setUPGInSession(com.amway.core.model.AmwayAccountModel, de.hybris.platform.core.model.user.CustomerModel)}
	 * .
	 */
	@Test
	public void testSetUPGInSessionAmwayAccountModelCustomerModel()
	{
		when(accountModel.getUserPriceGroup()).thenReturn(UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP));
		when(accountModel.getBusinessNature()).thenReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		when(sessionService.getAttribute(Europe1Constants.PARAMS.UPG))
				.thenReturn(UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP));
		accountCommerceService.setUPGInSession(accountModel, customer);
		Assert.assertEquals(UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP),
				sessionService.getAttribute(Europe1Constants.PARAMS.UPG));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getAccountFromUser(de.hybris.platform.core.model.user.UserModel)}
	 * .
	 */
	@Test
	public void testGetAccountFromUser()
	{
		final AmwayAccountModel account = accountCommerceService.getAccountFromUser(customer);
		Assert.assertNotNull(account);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#saveLoggedInCustomerInfo(com.amway.core.dms.data.AmwayProfileResponseData, com.amway.core.los.data.LosAccountDetailResponseData)}
	 * .
	 */
	@Test
	public void testSaveLoggedInCustomerInfoAmwayProfileResponseDataLosAccountDetailResponseData()
	{
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getBusinessNature()).thenReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		accountCommerceService.saveLoggedInCustomerInfo(profileResponseData, losAccountDetailResponseData);
		Assert.assertNotNull(accountModel.getExpiryDate());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#saveLoggedInCustomerInfo(com.amway.core.dms.data.AmwayProfileResponseData, com.amway.core.los.data.LosAccountDetailResponseData, de.hybris.platform.core.model.user.CustomerModel)}
	 * .
	 */
	@Test
	public void testSaveLoggedInCustomerInfoAmwayProfileResponseDataLosAccountDetailResponseDataCustomerModel()
	{
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getBusinessNature()).thenReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		accountCommerceService.saveLoggedInCustomerInfo(profileResponseData, losAccountDetailResponseData, customer);
		Assert.assertNotNull(accountModel.getExpiryDate());
	}

	/**
	 * Test method for {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#isCurrentAccountInactive()}.
	 */
	@Test
	public void testIsCurrentAccountInactive()
	{
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getStatus()).thenReturn(AmwayAccountStatus.INACTIVE);
		Assert.assertTrue(accountCommerceService.isCurrentAccountInactive());
	}

	/**
	 * Test method for {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#isAboAccountExpired()}.
	 */
	@Test
	public void testIsAboAccountExpired()
	{
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		final Date tomorrow = cal.getTime();
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getExpiryDate()).thenReturn(tomorrow);
		Assert.assertFalse(accountCommerceService.isAboAccountExpired());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#isCurrentAccountAboutToExpire()}.
	 */
	@Test
	public void testIsCurrentAccountAboutToExpire()
	{
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getExpiryDate()).thenReturn(new Date());
		when(Integer.valueOf(site.getExpirationPeriod())).thenReturn(Integer.valueOf(30));
		Assert.assertFalse(accountCommerceService.isCurrentAccountAboutToExpire());
	}

	/**
	 * Test method for {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#isAccountBlock()}.
	 */
	@Test
	public void testIsAccountBlock()
	{
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(bussinessRestriction.getRestrictionId()).thenReturn("BlockLogin");
		when(accountModel.getRestrictions()).thenReturn(restrictions);
		Assert.assertTrue(accountCommerceService.isAccountBlock());
	}

	/**
	 * Test method for {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#isLegalEntityClientBlock()}.
	 */
	@Test
	public void testIsLegalEntityClientBlock()
	{
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getPrimaryParty()).thenReturn(customer);
		when(accountModel.getBusinessNature()).thenReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_4);
		Assert.assertTrue(accountCommerceService.isLegalEntityClientBlock());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#saveExpiryDate(com.amway.core.model.AmwayAccountModel, java.util.Date)}
	 * .
	 */
	@Test
	public void testSaveExpiryDate()
	{
		final Date currentDate = new Date();
		accountCommerceService.saveExpiryDate(accountModel, currentDate);
		Assert.assertEquals(currentDate, accountModel.getExpiryDate());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getLosAccount(com.amway.core.los.data.LosAccountRequestData, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetLosAccount()
	{
		//		fail("Not yet implemented");
		//		when(losAccountService.process(losAccountRequestData)).thenReturn(losAccountResponseData);
		when(storeService.getCurrentBaseStore()).thenReturn(store);
		when(configurationService.getConfiguration()).thenReturn(configuration);
		when(Integer.valueOf(configuration.getInt("los.downline.chunk.size", 50))).thenReturn(Integer.valueOf(50));
		accountCommerceService.getLosAccount(losAccountRequestData, "123456");
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getLosAccount(com.amway.core.los.data.LosAccountRequestData, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetLosAccountEmptyAbo()
	{
		when(storeService.getCurrentBaseStore()).thenReturn(store);
		when(configurationService.getConfiguration()).thenReturn(configuration);
		when(Integer.valueOf(configuration.getInt("los.downline.chunk.size", 50))).thenReturn(Integer.valueOf(50));
		Assert.assertNotNull(accountCommerceService.getLosAccount(losAccountRequestData, "123456"));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getLosAccountDetails(com.amway.core.los.data.LosAccountRequestData)}
	 * .
	 */
	@Test
	public void testGetLosAccountDetails()
	{
		Assert.assertNotNull(accountCommerceService.getLosAccountDetails(losAccountRequestData));

	}

	/**
	 * Test method for {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getBusinessInfo()}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetBusinessInfo() throws Exception
	{
		PowerMockito.whenNew(LosAccountRequestData.class).withNoArguments().thenReturn(losAccountRequestData);
		when(storeService.getCurrentBaseStore()).thenReturn(store);
		when(sessionService.getAttribute(SessionVariables.ACCOUNT)).thenReturn(accountModel);
		when(accountModel.getCode()).thenReturn("654321");
		Assert.assertNotNull(accountCommerceService.getBusinessInfo());

	}

	/**
	 * Test method for {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getBusinessInfoAccount()}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetBusinessInfoAccount() throws Exception
	{
		BDDMockito.when(AmwayPeriodHelper.getCurrentActiveBonusPeriod()).thenReturn(CURRENT_ACTIVE_BONUS_PRIOD);
		PowerMockito.whenNew(LosAccountRequestData.class).withNoArguments().thenReturn(losAccountRequestData);
		when(storeService.getCurrentBaseStore()).thenReturn(store);
		Assert.assertNotNull(accountCommerceService.getBusinessInfoAccount());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getLosProfile(com.amway.core.los.data.LosAccountRequestData)}
	 * .
	 */
	@Test
	public void testGetLosProfile()
	{
		when(storeService.getCurrentBaseStore()).thenReturn(store);
		Assert.assertNotNull(accountCommerceService.getLosProfile(losAccountRequestData));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.service.impl.DefaultAmwayAccountCommerceService#getBonusStatementPDF(com.amway.core.los.data.BonusStatementRequestData)}
	 * .
	 *
	 * @throws IOException
	 */
	@Test(expected = AmwayServiceException.class)
	public void testGetBonusStatementPDF() throws IOException
	{
		final BonusStatementRequestData requestData = new BonusStatementRequestData();
		Assert.assertTrue(accountCommerceService.getBonusStatementPDF(requestData).length > 0);
	}

	/**
	 * @return AmwayProfileResponseData
	 */
	private AmwayProfileResponseData createAccountProfile()
	{
		final AmwayProfileResponseData aboAccountResponseData = new AmwayProfileResponseData();
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
		masterDetailsData1.setAccountType("ABO");
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
		final AddressData addressData3 = new AddressData();
		addressData3.setId("455093");
		addressData3.setTown("Ada");
		addressData3.setLine1("884/4 test street");
		addressData3.setLine2("TATUAPE");
		addressData3.setPostalCode("49301");
		addressData3.setCountry(countryData);
		addressData3.setRegion(regionData);
		addressData3.setPartyId("77");
		addressData3.setContactPointTypeCd("Billing");
		addressDataList.add(addressData3);
		//partyDetailsData1.setAddressMasterData(addressDataList);

		//PersonalId Details
		final List<PersonalIdDetailsData> personalIdDetailsDataList1 = new ArrayList<>();
		final PersonalIdDetailsData personalIdDetailsData1 = new PersonalIdDetailsData();
		personalIdDetailsData1.setPersonalId("56856");
		personalIdDetailsData1.setPersonalIdTypeCd("StateRegistration");
		personalIdDetailsData1.setExpirationDate("05/18/2017");
		personalIdDetailsDataList1.add(personalIdDetailsData1);
		partyDetailsData1.setPersonalIdDetailsData(personalIdDetailsDataList1);
		partyDetailsData1.setAddressMasterData(addressDataList);

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
		partyDetailsData2.setAddressMasterData(addressDataList);

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


		aboAccountResponseData.setPartyDetailList(partyList);
		aboAccountResponseData.setAccountMasterDetails(masterDetailsData1);
		aboAccountResponseData.setSponsorDetails(sponsorDetails);
		//aboAccountResponseData.setBlockPrivDetailsData(blockPrivDetailsData);
		aboAccountResponseData.setAccountBalance(accountBalanceDatas);
		aboAccountResponseData.setReturnCd(1);
		aboAccountResponseData.setReturnMessage("Success");
		return aboAccountResponseData;

	}

	/**
	 * @return
	 */
	private LosAccountDetailResponseData createLosAccountData()
	{
		final LosAccountDetailResponseData accountDetailResponseData = new LosAccountDetailResponseData();
		accountDetailResponseData.setReturnCode(-1);
		accountDetailResponseData.setReturnMessage("Failed to get los account details");
		final List<LosAccountDetailDownlinesResponse> downlinesResponseList = new ArrayList<>();
		final LosAccountDetailDownlinesResponse downLoadResponse = new LosAccountDetailDownlinesResponse();
		downlinesResponseList.add(downLoadResponse);
		accountDetailResponseData.setLosAccountDetailResponseListData(downlinesResponseList);
		return accountDetailResponseData;
	}

}
