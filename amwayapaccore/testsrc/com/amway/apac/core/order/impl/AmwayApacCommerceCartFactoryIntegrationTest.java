/**
 *
 */
package com.amway.apac.core.order.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.impl.CommerceCartFactory;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.enums.OrderType;
import com.amway.core.service.AmwayAccountCommerceService;




/**
 * @author navishsharma <br>
 *         Test class for {@link AmwayApacCommerceCartFactory}
 */
@IntegrationTest
public class AmwayApacCommerceCartFactoryIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;


	@Resource(name = "amwayApacCommerceCartFactory")
	private CommerceCartFactory commerceCartFactory;

	@Resource(name = "amwayAccountCommerceService")
	private AmwayAccountCommerceService amwayAccountCommerceService;

	private static final String TEST_ACCOUNT = "8914266";

	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwayapaccore/test/testCartFactory.impex", "utf-8");
		baseSiteService.setCurrentBaseSite("testSite", false);
		amwayAccountCommerceService.setCurrentAccount(TEST_ACCOUNT);
	}

	@After
	public void cleanUp()
	{
		sessionService.setAttribute("currentChannel", null);
	}

	@Test
	public void testCreateWebCartForAmwayAccount()
	{
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNotNull(cartModel.getAccount().getCode());
		Assert.assertEquals(TEST_ACCOUNT, cartModel.getAccount().getCode());
	}

	@Test
	public void testCreateWebCartForVolumeAmwayAccount()
	{
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNotNull(cartModel.getVolumeAmwayAccount());
		Assert.assertEquals(TEST_ACCOUNT, cartModel.getVolumeAmwayAccount());
	}

	@Test
	public void testCreatePOSCartForAmwayAccount()
	{
		sessionService.setAttribute("currentChannel", SalesApplication.POS);
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNotNull(cartModel.getAccount().getCode());
		Assert.assertEquals(TEST_ACCOUNT, cartModel.getAccount().getCode());
	}

	@Test
	public void testCreateWebCartForDeliveryAddress()
	{
		final CartModel cartModel = commerceCartFactory.createCart();
		final AddressModel registeredAddress = amwayAccountCommerceService.getCurrentAccount().getRegisteredAddress();
		Assert.assertEquals(registeredAddress, cartModel.getDeliveryAddress());
	}

	@Test
	public void testCreateWebCartForWarehouse()
	{
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNotNull(cartModel.getWarehouse().getCode());
		Assert.assertEquals("testWarehouse", cartModel.getWarehouse().getCode());
	}

	@Test
	public void testCreateWebCartForOrderType()
	{
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertEquals(OrderType.NORMAL_ORDER, cartModel.getOrderType());
	}

	@Test
	public void testCreatePOSCartForVolumeAmwayAccount()
	{
		sessionService.setAttribute("currentChannel", SalesApplication.POS);
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNull(cartModel.getVolumeAmwayAccount());
	}

	@Test
	public void testCreatePOSCartForDeliveryAddress()
	{
		sessionService.setAttribute("currentChannel", SalesApplication.POS);
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNull(cartModel.getDeliveryAddress());
	}

	@Test
	public void testCreatePOSCartForOrderType()
	{
		sessionService.setAttribute("currentChannel", SalesApplication.POS);
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNull(cartModel.getOrderType());
	}

	@Test
	public void testCreatePOSCartForWarehouse()
	{
		sessionService.setAttribute("currentChannel", SalesApplication.POS);
		final CartModel cartModel = commerceCartFactory.createCart();
		Assert.assertNull(cartModel.getWarehouse());
	}

}
