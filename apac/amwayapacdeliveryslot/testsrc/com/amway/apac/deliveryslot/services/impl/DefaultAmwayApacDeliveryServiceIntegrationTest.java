package com.amway.apac.deliveryslot.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacDeliveryServiceIntegrationTest extends ServicelayerTransactionalTest
{

	private static final String TEST_USER_UID_ONE = "ahertz";

	private static final String TEST_SITE_UID = "testSite";

	private static final String TEST_CART_CODE_ONE = "ahertzCart";

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private UserService userService;

	@Resource
	private CartService cartService;

	@Resource
	private ModelService modelService;

	@Resource
	private CommerceCartService commerceCartService;

	@Resource(name = "defaultAmwayApacDeliveryService")
	private DefaultAmwayApacDeliveryService defaultAmwayApacDeliveryService;

	@Resource(name = "defaultAmwayApacDeliverySlotManagementService")
	private DefaultAmwayApacDeliverySlotManagementService defaultAmwayApacDeliverySlotManagementService;

	@Before
	public void setUp() throws Exception
	{
		//add values to parameter
		importCsv("/amwayapacdeliveryslot/test/testDeliverySlots.csv", "utf-8");
		createDeliverySlotsForPresentDayOrder();
		setCartParamters();
	}

	private void setCartParamters()
	{
		final UserModel user = userService.getUserForUID(TEST_USER_UID_ONE);
		final CartModel cartModel = commerceCartService.getCartForCodeAndUser(TEST_CART_CODE_ONE, user);
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_SITE_UID), true);
		userService.setCurrentUser(user);
		cartService.setSessionCart(cartModel);
	}

	@Test
	public void testDeliverySlotsAvailability()
	{
		final List<AmwayDeliverySlotAvailabilityModel> slotAvailabilityModels = defaultAmwayApacDeliveryService
				.getDeliverySlotsAvailability();
		Assert.assertTrue(CollectionUtils.isNotEmpty(slotAvailabilityModels));
	}

	@Test
	public void testNonNullReserve()
	{
		final CommerceCartParameter parameter = getSessionParameter();
		final CartModel cart = parameter.getCart();
		final Date date = defaultAmwayApacDeliveryService.getDeliveryDate(cart.getWarehouse());
		defaultAmwayApacDeliveryService.setDeliverySlot(cart.getWarehouse(), date, "9 AM - 1 PM");
		final AmwayDeliverySlotAvailabilityModel deliverySlot = cartService.getSessionCart().getSelectedDeliverySlot();
		final Integer reserve = defaultAmwayApacDeliveryService.reserve(deliverySlot);
		Assert.assertNotNull(reserve);
	}

	@Test
	public void testSetDeliverySlot()
	{
		final CommerceCartParameter parameter = getSessionParameter();
		final CartModel cart = parameter.getCart();
		final Date date = defaultAmwayApacDeliveryService.getDeliveryDate(cart.getWarehouse());
		final boolean setDelivery = defaultAmwayApacDeliveryService.setDeliverySlot(cart.getWarehouse(), date, "9 AM - 1 PM");
		Assert.assertTrue(setDelivery);
	}

	@Test
	public void testNonNullRelease()
	{
		final CommerceCartParameter parameter = getSessionParameter();
		final CartModel cart = parameter.getCart();
		final Date date = defaultAmwayApacDeliveryService.getDeliveryDate(cart.getWarehouse());
		defaultAmwayApacDeliveryService.setDeliverySlot(cart.getWarehouse(), date, "9 AM - 1 PM");
		final AmwayDeliverySlotAvailabilityModel deliverySlot = cartService.getSessionCart().getSelectedDeliverySlot();
		final Integer release = defaultAmwayApacDeliveryService.release(deliverySlot);
		Assert.assertNotNull(release);
	}

	/**
	 * @return session parameter
	 */
	private CommerceCartParameter getSessionParameter()
	{
		final CommerceCartParameter parameter = new CommerceCartParameter();
		final CartModel cart = cartService.getSessionCart();
		parameter.setEnableHooks(true);
		parameter.setCart(cart);
		parameter.setBaseSite(baseSiteService.getCurrentBaseSite());
		parameter.setUser(userService.getCurrentUser());
		return parameter;
	}

	private void createDeliverySlotsForPresentDayOrder()
	{
		final DayOfWeek orderingDay = LocalDate.now().getDayOfWeek();

		// If ordering day is SAT or SUN, data already exists in SYSTEM
		if ((!orderingDay.equals(DayOfWeek.SATURDAY)) && (!orderingDay.equals(DayOfWeek.SUNDAY)))
		{
			final List<AmwayDeliverySlotConfigModel> deliverySlotModels = defaultAmwayApacDeliverySlotManagementService
					.getDeliverySlotByOrderDay(orderingDay);

			defaultAmwayApacDeliverySlotManagementService.createDeliverySlotForDate(deliverySlotModels, LocalDate.now());
		}
	}
}
