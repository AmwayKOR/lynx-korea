package com.amway.apac.deliveryslot.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacDeliverySlotManagementServiceIntegrationTest extends ServicelayerTransactionalTest
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
	private CommerceCartService commerceCartService;

	@Resource(name = "defaultAmwayApacDeliverySlotManagementService")
	private DefaultAmwayApacDeliverySlotManagementService defaultAmwayApacDeliverySlotManagementService;

	@Resource(name = "defaultAmwayApacDeliveryService")
	private DefaultAmwayApacDeliveryService defaultAmwayApacDeliveryService;

	@Before
	public void setUp() throws Exception
	{
		//add values to parameter
		importCsv("/amwayapacdeliveryslot/test/testDeliverySlots.csv", "utf-8");
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
	public void testGetDeliverySlotByOrderDay()
	{
		final DayOfWeek orderingDay = DayOfWeek.WEDNESDAY;
		final List<AmwayDeliverySlotConfigModel> slots = defaultAmwayApacDeliverySlotManagementService
				.getDeliverySlotByOrderDay(orderingDay);
		Assert.assertTrue(CollectionUtils.isNotEmpty(slots));
		Assert.assertEquals(WeekDay.FRIDAY, slots.get(0).getDeliveryDay());
	}

	@Test
	public void retestGetDeliverySlotByOrderDay()
	{
		final DayOfWeek orderingDay = DayOfWeek.MONDAY;
		final List<AmwayDeliverySlotConfigModel> slots = defaultAmwayApacDeliverySlotManagementService
				.getDeliverySlotByOrderDay(orderingDay);
		Assert.assertTrue(CollectionUtils.isNotEmpty(slots));
		Assert.assertNotEquals(WeekDay.TUESDAY, slots.get(0).getDeliveryDay());
	}

	@Test
	public void testCreatingDeliverySlotsForDate()
	{
		final CommerceCartParameter parameter = getSessionParameter();
		final CartModel cart = parameter.getCart();
		final DayOfWeek finalDay = orderingDayOfWeek(cart);
		final List<AmwayDeliverySlotConfigModel> applicableDeliverySlots = defaultAmwayApacDeliverySlotManagementService
				.getDeliverySlotByOrderDay(finalDay);
		defaultAmwayApacDeliverySlotManagementService.createDeliverySlotForDate(applicableDeliverySlots, LocalDate.now());

	}

	/**
	 * @return parameter
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

	private DayOfWeek orderingDayOfWeek(final CartModel cart)
	{
		final Date date = cart.getDate();
		final SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		final String day = simpleDateformat.format(date);
		for (final DayOfWeek b : DayOfWeek.values())
		{
			if (b.toString().equalsIgnoreCase(day))
			{
				return b;
			}
		}
		return null;
	}

}
