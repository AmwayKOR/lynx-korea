/**
 *
 */
package com.amway.core.commerceservices.order.impl;

import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.strategies.NetGrossStrategy;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.order.OrderManager;
import de.hybris.platform.jalo.order.price.PriceFactory;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.BDDMockito.given;


@SuppressWarnings("deprecation")
@Ignore("HE-210 - removing powermock and disabling test")
@UnitTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(
//{ AmwayAccountCommerceService.class, KeyGenerator.class, ModelService.class, UserService.class, CommonI18NService.class,
//		NetGrossStrategy.class, BaseSiteService.class, BaseStoreService.class, Config.class, PriceFactory.class, User.class,
//		OrderManager.class })
public class AmwayCommerceCartFactoryUnitTest
{

	private static final String CART_CODE = "7000000000023";
	private static final String CARTMODELCLASS = "CartModel.class";
	private final AmwayCommerceCartFactory amwayCommerceCartFactory = new AmwayCommerceCartFactory();
	@Mock
	private AmwayAccountCommerceService amwayAccountCommerceService;
	@Mock
	private KeyGenerator keyGenerator;
	@Mock
	private ModelService modelService;
	@Mock
	private UserService userService;
	@Mock
	private CommonI18NService commonI18NService;
	@Mock
	private NetGrossStrategy netGrossStrategy;
	@Mock
	private BaseSiteService baseSiteService;
	@Mock
	private BaseStoreService baseStoreService;
	private AmwayAccountModel account;
	private UserModel user;
	private CurrencyModel currency;
	private BaseStoreModel store;
	private CMSSiteModel site;
	private CartModel cart;
	private User userItem;
	private OrderManager orderManager;
	private PriceFactory priceFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		cart = Mockito.spy(new CartModel());
		userItem = Mockito.mock(User.class);
		orderManager = Mockito.mock(OrderManager.class);
		priceFactory = Mockito.mock(PriceFactory.class);
		amwayCommerceCartFactory.setAmwayAccountCommerceService(amwayAccountCommerceService);
		amwayCommerceCartFactory.setKeyGenerator(keyGenerator);
		amwayCommerceCartFactory.setUserService(userService);
		amwayCommerceCartFactory.setBaseSiteService(baseSiteService);
		amwayCommerceCartFactory.setBaseStoreService(baseStoreService);
		amwayCommerceCartFactory.setCommonI18NService(commonI18NService);
		amwayCommerceCartFactory.setNetGrossStrategy(netGrossStrategy);
		amwayCommerceCartFactory.setModelService(modelService);
		amwayCommerceCartFactory.setGuidKeyGenerator(keyGenerator);
		account = Mockito.mock(AmwayAccountModel.class);
		BDDMockito.given(amwayAccountCommerceService.getCurrentAccount()).willReturn(account);

		user = Mockito.mock(UserModel.class);
		currency = Mockito.mock(CurrencyModel.class);
		site = Mockito.mock(CMSSiteModel.class);
		store = Mockito.mock(BaseStoreModel.class);

		PowerMockito.mockStatic(Config.class);
		PowerMockito.when(Config.getString(JaloSession.CART_TYPE, "Cart")).thenReturn(CARTMODELCLASS);
		given(modelService.create(CARTMODELCLASS)).willReturn(cart);
		given(modelService.getSource(user)).willReturn(userItem);

		PowerMockito.mockStatic(OrderManager.class);
		PowerMockito.when(OrderManager.getInstance()).thenReturn(orderManager);

		given(orderManager.getPriceFactory()).willReturn(priceFactory);
		given(Boolean.valueOf(priceFactory.isNetUser(userItem))).willReturn(Boolean.TRUE);

		given(userService.getCurrentUser()).willReturn(user);
		given(baseSiteService.getCurrentBaseSite()).willReturn(site);
		given(baseStoreService.getCurrentBaseStore()).willReturn(store);
		given(commonI18NService.getCurrentCurrency()).willReturn(currency);
		given(keyGenerator.generate()).willReturn(CART_CODE);


	}

	/**
	 * Test method for {@link com.amway.core.commerceservices.order.impl.AmwayCommerceCartFactory#createCartInternal()}.
	 */
	@Test
	public void testCreateCartInternal()
	{
		Assert.assertNotNull(amwayCommerceCartFactory.createCartInternal());
	}

}
