/**
 *
 */
package com.amway.core.payment.service.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.CoreBasicDataCreator;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.model.AmwayCashPaymentInfoModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * Abstract integration test class for payment services integration tests
 */
@SuppressWarnings("deprecation")
public class AbstractPaymentServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(AbstractPaymentServiceIntegrationTest.class);
	protected static final String CUSTOMR_UID1 = "ahertz";
	protected static final String CUSTOMR_UID2 = "lesley";

	protected static final String TEST_BASESITE_UID = "amway";

	protected static final String CURRENT_PAYMENT_MODE = "cash";
	protected static final String BASE_RESOURCE_PATH = "/amwaycore/test/paymentsTestData";

	@Resource(name = "defaultAmwayPaymentModeService")
	protected DefaultAmwayPaymentModeService amwayPaymentModeService;

	@Resource(name = "amwayAccountCommerceService")
	protected AmwayAccountCommerceService accountCommerceService;

	@Resource
	protected SessionService sessionService;
	@Resource
	protected UserService userService;
	@Resource
	protected OrderService orderService;
	@Resource
	protected CartService cartService;
	@Resource
	protected ProductService productService;
	@Resource
	protected ModelService modelService;
	@Resource
	protected BaseSiteService baseSiteService;
	@Resource
	protected UnitService unitService;
	@Resource
	protected CatalogVersionService catalogVersionService;
	@Resource
	protected CommerceCartService commerceCartService;

	protected CartModel cartWithEntries;

	protected CartModel emptyCart;

	protected AmwayProfileResponseData amwayProfileData;

	public void prepare() throws Exception
	{
		setSalesApplicationInSession(SalesApplication.POS);
		setUpBasicData();
		setUpUserData();
		setUpCartData();
		setUpPaymentsData();
		MockitoAnnotations.initMocks(this);
	}


	protected void setSalesApplicationInSession(final SalesApplication salesApplication)
	{
		sessionService.setAttribute("currentChannel", salesApplication);
	}

	protected void setUpBasicData() throws Exception
	{

		LOG.info("Creating basic test data ..");
		userService.setCurrentUser(userService.getAdminUser());
		new CoreBasicDataCreator().createEssentialData(Collections.EMPTY_MAP, null); // importing test csv
		importCsv(BASE_RESOURCE_PATH + "/basicTestData.csv", "utf-8");
	}

	protected void setUpUserData() throws Exception
	{
		LOG.info("Creating test data for user ..");
		importCsv(BASE_RESOURCE_PATH + "/userTestData.csv", "utf-8");
	}

	protected void setUpCartData() throws Exception
	{
		LOG.info("Creating cart test data ..");
		importCsv(BASE_RESOURCE_PATH + "/cartTestData.csv", "utf-8");
		setCurrentBaseSite();
		cartWithEntries = getCartForUser(CUSTOMR_UID1);
		addNewEntryToCart(cartWithEntries);
		emptyCart = getCartForUser(CUSTOMR_UID2);
	}

	protected void setUpPaymentsData() throws ImpExException
	{
		importCsv(BASE_RESOURCE_PATH + "/paymentModeTestData.csv", "utf-8");
	}

	protected CartModel getCartForUser(final String uid)
	{
		final CustomerModel userModel = getUserForUid(uid);
		final Collection<CartModel> cartModels = userModel.getCarts();
		Assert.assertNotNull(cartModels);
		Assert.assertTrue(cartModels.size() > 0);
		final CartModel cart = cartModels.iterator().next();
		setCartType(cart, AmwayCartType.POS);
		SetAccountInCart(userModel, cart);
		modelService.save(cart);
		return cart;
	}

	protected void addNewEntryToCart(final CartModel cart) throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cart);
		parameter.setProduct(productModel);
		parameter.setQuantity(3);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);
		// Add new entry
		commerceCartService.addToCart(parameter);
	}

	protected void setCurrentBaseSite()
	{
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);
	}

	protected void setCartType(final CartModel cart, final AmwayCartType cartType)
	{
		cart.setType(cartType);
	}

	protected void SetAccountInCart(final CustomerModel userModel, final CartModel cart)
	{
		Assert.assertNotNull(userModel.getAccounts());
		Assert.assertTrue(userModel.getAccounts().size() > 0);
		cart.setAccount(userModel.getAccounts().iterator().next());
	}

	protected CustomerModel getUserForUid(final String uid)
	{
		final CustomerModel userModel = (CustomerModel) userService.getUserForUID(uid);
		return userModel;
	}

	protected OrderModel getOrder(final String userId, final PaymentStatus paymentStatus)
	{
		final Collection<OrderModel> orders = userService.getUserForUID(userId).getOrders();
		Assert.assertNotNull(orders);
		Assert.assertTrue(CollectionUtils.isNotEmpty(orders));
		final OrderModel orderForPaymentStatus = getOrderForPaymentStatus(orders, paymentStatus);
		return orderForPaymentStatus;
	}

	protected OrderModel getOrderForPaymentStatus(final Collection<OrderModel> orders, final PaymentStatus paymentStatus)
	{
		final Iterator iterator = orders.iterator();
		OrderModel overpaidOrder = null;
		OrderModel order = null;
		while (iterator.hasNext())
		{
			order = (OrderModel) iterator.next();
			if ((paymentStatus.equals(order.getPaymentStatus())))
			{
				overpaidOrder = order;
			}
		}
		return overpaidOrder;
	}


}
