package com.amway.core.orderperiod.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.dao.AmwayOrderPeriodDao;


/**
 *
 */
@UnitTest
public class DefaultAmwayOrderPeriodServiceUnitTest
{
	@Mock
	private AmwayOrderPeriodDao amwayOrderPeriodDao;
	@Mock
	private CMSSiteService cmsSiteService;
	@Mock
	private ModelService modelService;
	@InjectMocks
	private final DefaultAmwayOrderPeriodService defaultAmwayOrderPeriodService = new DefaultAmwayOrderPeriodService();

	private AmwayOrderPeriodModel orderPeriod1Active;
	private AmwayOrderPeriodModel orderPeriod2Active;
	private AmwayOrderPeriodModel orderPeriod3Inactive;

	private Date insideOP1EqualsStart;
	private Date insideOP1;
	private Date insideOP1EqualsEnd;
	private Date outsideOP1;
	private Date insideOP2;
	private Date insideOP3;

	private OrderModel order;
	private BaseSiteModel baseSiteModel;
	private static final String TIMEZONE = "Brazil/East";


	@Before
	public void setUp() throws ParseException
	{
		MockitoAnnotations.initMocks(this);

		orderPeriod1Active = new AmwayOrderPeriodModel();
		final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		orderPeriod1Active.setStartDate(cal.getTime());
		insideOP1EqualsStart = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 13);
		insideOP1 = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		insideOP1EqualsEnd = cal.getTime();
		orderPeriod1Active.setEndDate(cal.getTime());
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 5);
		outsideOP1 = cal.getTime();
		orderPeriod1Active.setStatus(AmwayPeriodTypeEnum.ACTIVE);

		orderPeriod2Active = new AmwayOrderPeriodModel();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		orderPeriod2Active.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 11);
		insideOP2 = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		orderPeriod2Active.setEndDate(cal.getTime());
		orderPeriod2Active.setStatus(AmwayPeriodTypeEnum.ACTIVE);

		orderPeriod3Inactive = new AmwayOrderPeriodModel();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		orderPeriod3Inactive.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 11);
		insideOP3 = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		orderPeriod3Inactive.setEndDate(cal.getTime());
		orderPeriod3Inactive.setStatus(AmwayPeriodTypeEnum.INACTIVE);

		baseSiteModel = new CMSSiteModel();
		resetOrder();

		given(cmsSiteService.getCurrentSite()).willReturn((CMSSiteModel) baseSiteModel);
	}

	private void resetOrder()
	{
		final OrderEntryModel orderEntry1 = new OrderEntryModel();
		final OrderEntryModel orderEntry2 = new OrderEntryModel();
		order = new OrderModel();
		order.setOrderName("OrderPeriodServiceTestOrder");
		final AbstractOrderEntryModel orderEntries[] = { orderEntry1, orderEntry2 };
		order.setEntries(Arrays.asList(orderEntries));
		order.setSite(baseSiteModel);
	}

	@Test
	public void testFindAllActiveOrderPeriodsForSite()
	{
		given(cmsSiteService.getCurrentSite()).willReturn((CMSSiteModel) baseSiteModel);
		final List<AmwayOrderPeriodModel> amwayOrderPeriodModels = Arrays.asList(orderPeriod1Active, orderPeriod2Active);

		given(amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(baseSiteModel))).willReturn(amwayOrderPeriodModels);
		final List<AmwayOrderPeriodModel> result = defaultAmwayOrderPeriodService.findAllActiveOrderPeriodsForSite();
		//	given(result.get(0)).willReturn(orderPeriod1Active);
		assertEquals("We should find two", 2, result.size());
		//assertEquals("And should equals what the mock returned", amwayOrderPeriodModels.get(0), result.get(0));
	}

	@Test
	public void shouldAssignCurrentOrderPeriod()
	{
		//test com.amway.core.orderperiod.services.impl.DefaultAmwayOrderPeriodService.assignOrderPeriod(o, op)()
		order.setDate(insideOP1EqualsStart);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod1Active);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}
		assertEquals(orderPeriod1Active, order.getOrderPeriod());
		resetOrder();

		order.setDate(insideOP1);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod1Active);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}
		assertEquals(orderPeriod1Active, order.getOrderPeriod());
		resetOrder();

		order.setDate(insideOP1EqualsEnd);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod1Active);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}
		assertEquals(orderPeriod1Active, order.getOrderPeriod());
		resetOrder();

		//test com.amway.core.orderperiod.services.impl.DefaultAmwayOrderPeriodService.assignOrderPeriod(o)()

		final AmwayOrderPeriodModel activeOrderPeriods[] = { orderPeriod2Active, orderPeriod1Active };
		given(amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(baseSiteModel)))
				.willReturn(Arrays.asList(activeOrderPeriods));

		order.setDate(insideOP1EqualsStart);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}
		assertEquals(orderPeriod1Active, order.getOrderPeriod());
		resetOrder();

		order.setDate(insideOP1);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}
		assertEquals(orderPeriod1Active, order.getOrderPeriod());
		resetOrder();

		order.setDate(insideOP1EqualsEnd);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}
		assertEquals(orderPeriod1Active, order.getOrderPeriod());
	}

	@Test
	public void shouldNotAssignInactivePeriod() throws BusinessException
	{
		order.setDate(insideOP3);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod3Inactive);
			fail("should not assign inactive order period");
		}
		catch (final IllegalArgumentException e)
		{
			assertEquals(e.getMessage(), "Invalid order period. Should be active");
		}
		assertNull("should not assign inactive order period", order.getOrderPeriod());
	}

	@Test
	public void shouldNotAssignIfOrderPeriodIsIrrelevant()
	{
		given(amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(baseSiteModel)))
				.willReturn(Arrays.asList(orderPeriod1Active));
		order.setDate(outsideOP1);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod1Active);
			fail("Inactive period should not be assigned");
		}
		catch (final BusinessException e)
		{
			assertEquals(e.getMessage(), "Order \"" + order.getCode() + "\" date is outside order period");
		}
		assertNull("Inactive period should not be assigned", order.getOrderPeriod());
	}

	@Test
	public void shouldNotAssignIfOrderDateOutsideActivePeriod()
	{
		given(amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(baseSiteModel)))
				.willReturn(Arrays.asList(orderPeriod1Active));
		order.setDate(outsideOP1);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order);
			fail("There is no active period for order date");
		}
		catch (final BusinessException e)
		{
			assertEquals(e.getMessage(), "There is no active order period for the given order \"" + order.getCode() + "\"");
		}
		assertNull("There is no active period for order date", order.getOrderPeriod());
	}

	@Test
	public void shouldNotAssignIfThereAreNoActivePeriodsForOrder() throws BusinessException
	{
		final AmwayOrderPeriodModel emptyOrderPeriods[] = {};
		given(amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(baseSiteModel)))
				.willReturn(Arrays.asList(emptyOrderPeriods));
		order.setDate(insideOP1);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order);
			fail("There is no active period for order date");
		}
		catch (final UnknownIdentifierException e)
		{
			assertEquals(e.getMessage(), "no valid order periods found for this site");
		}
		assertNull("There is no active period for order date", order.getOrderPeriod());
	}

	@Test
	public void shouldNotAssignIfAlreadySet()
	{
		final AmwayOrderPeriodModel orderPeriods[] = { orderPeriod2Active, orderPeriod1Active };
		given(amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(baseSiteModel))).willReturn(Arrays.asList(orderPeriods));

		order.setDate(insideOP1);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod1Active);
		}
		catch (final BusinessException e)
		{
			fail("Order period should be assigned");
		}

		// set order date to the next active order period to be able to try to assign current active period
		order.setDate(insideOP2);
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(order, orderPeriod1Active);
		}
		catch (final BusinessException e)
		{
			fail("This exception should not be thrown when order period already set");
		}
		assertEquals("should be the current order period", orderPeriod1Active, order.getOrderPeriod());
	}

}
