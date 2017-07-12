package com.amway.core.bonusperiod.services.impl;

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
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.orderperiod.dao.AmwayBonusPeriodDao;


/**
 *
 */
@UnitTest
public class DefaultAmwayBonusPeriodServiceUnitTest
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayBonusPeriodServiceUnitTest.class);
	@Mock
	private AmwayBonusPeriodDao amwayBonusPeriodDao;
	@Mock
	private CMSSiteService cmsSiteService;
	@Mock
	private ModelService modelService;
	@InjectMocks
	private final DefaultAmwayBonusPeriodService defaultAmwayBonusPeriodService = new DefaultAmwayBonusPeriodService();

	private AmwayBonusPeriodModel bonusPeriod1Active;
	private AmwayBonusPeriodModel bonusPeriod2Active;
	private AmwayBonusPeriodModel bonusPeriod3Inactive;
	private AmwayBonusPeriodModel bonusPeriod4Active;

	private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	private Date insideBP1EqualsStart;
	private Date insideBP1BeforeCutoff;
	private Date insideBP1EqualsCutoff;
	private Date insideBP1AfterCutoff1;
	private Date insideBP3BeforeCutoff;
	private Date outsideBP1;

	private OrderModel order;
	private BaseSiteModel baseSiteModel;
	private static final String TIMEZONE = "Brazil/East";

	@Before
	public void setUp() throws ParseException
	{
		MockitoAnnotations.initMocks(this);

		bonusPeriod1Active = new AmwayBonusPeriodModel();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		bonusPeriod1Active.setStartDate(cal.getTime());
		insideBP1EqualsStart = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 10);
		insideBP1BeforeCutoff = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 22);
		insideBP1EqualsCutoff = cal.getTime();
		bonusPeriod1Active.setCutoffDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 23);
		insideBP1AfterCutoff1 = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		bonusPeriod1Active.setEndDate(cal.getTime());

		cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
		cal.set(Calendar.DAY_OF_MONTH, 3);
		outsideBP1 = cal.getTime();
		bonusPeriod1Active.setStatus(AmwayPeriodTypeEnum.ACTIVE);

		bonusPeriod2Active = new AmwayBonusPeriodModel();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		bonusPeriod2Active.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 20);
		bonusPeriod2Active.setCutoffDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		bonusPeriod2Active.setEndDate(cal.getTime());
		bonusPeriod2Active.setStatus(AmwayPeriodTypeEnum.ACTIVE);

		bonusPeriod3Inactive = new AmwayBonusPeriodModel();
		cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
		cal.add(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		bonusPeriod3Inactive.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 10);
		insideBP3BeforeCutoff = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		bonusPeriod3Inactive.setCutoffDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 28);
		bonusPeriod3Inactive.setEndDate(cal.getTime());
		bonusPeriod3Inactive.setStatus(AmwayPeriodTypeEnum.INACTIVE);

		bonusPeriod4Active = new AmwayBonusPeriodModel();
		cal = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
		cal.add(Calendar.MONTH, -2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		bonusPeriod4Active.setStartDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 20);
		bonusPeriod4Active.setCutoffDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 28);
		bonusPeriod4Active.setEndDate(cal.getTime());
		bonusPeriod4Active.setStatus(AmwayPeriodTypeEnum.ACTIVE);

		baseSiteModel = new CMSSiteModel();
		baseSiteModel.setTimeZone(TIMEZONE);
		resetOrder();

		given(cmsSiteService.getCurrentSite()).willReturn((CMSSiteModel) baseSiteModel);
	}

	private void resetOrder()
	{
		final OrderEntryModel orderEntry1 = new OrderEntryModel();
		final OrderEntryModel orderEntry2 = new OrderEntryModel();
		order = new OrderModel();
		order.setOrderName("BonusPeriodServiceTestOrder");
		final AbstractOrderEntryModel orderEntries[] = { orderEntry1, orderEntry2 };
		order.setEntries(Arrays.asList(orderEntries));
		order.setSite(baseSiteModel);
	}

	@Test
	public void testFindAllActiveBonusPeriodsForSite()
	{
		final List<AmwayBonusPeriodModel> amwayBonusPeriodModels = Arrays.asList(bonusPeriod1Active, bonusPeriod2Active);

		given(amwayBonusPeriodDao.findActiveBonusPeriods(Arrays.asList(baseSiteModel))).willReturn(amwayBonusPeriodModels);
		final List<AmwayBonusPeriodModel> result = defaultAmwayBonusPeriodService.findAllActiveBonusPeriodsForSite();
		assertEquals("We should find Two", 2, result.size());
	}

	@Test(expected = BusinessException.class)
	public void shouldAssignCurrentBonusPeriod() throws BusinessException
	{
		order.setDate(insideBP1EqualsStart);
		defaultAmwayBonusPeriodService.assignBonusPeriod(order, bonusPeriod1Active);
		assertEquals("should be the same", bonusPeriod1Active, order.getBonusPeriod());
	}

	@Test
	public void shouldNotAssignInactiveBonusPeriod() throws BusinessException
	{
		order.setDate(insideBP3BeforeCutoff);
		try
		{
			defaultAmwayBonusPeriodService.assignBonusPeriod(order, bonusPeriod3Inactive);
			fail("Inactive Bonus period should not be assigned");
		}
		catch (final IllegalArgumentException e)
		{
			assertEquals(e.getMessage(), "Invalid bonus period. Should be active");
		}
		assertNull("Inactive Bonus period should not be assigned", order.getBonusPeriod());
	}

	@Test
	public void shouldNotAssignIfPeriodIsIrrelevant()
	{
		given(amwayBonusPeriodDao.findActiveBonusPeriods(Arrays.asList(baseSiteModel)))
				.willReturn(Arrays.asList(bonusPeriod4Active));
		order.setDate(outsideBP1);
		try
		{
			defaultAmwayBonusPeriodService.assignBonusPeriod(order, bonusPeriod4Active);
			fail("Bonus period should not be assigned");
		}
		catch (final BusinessException e)
		{
			assertEquals(e.getMessage(), "Order \"" + order.getCode() + "\" date is outside the passed bonus period");
		}
		assertNull("Bonus period should not be assigned", order.getBonusPeriod());
	}

	@Test(expected = BusinessException.class)
	public void shouldNotAssignIfAlreadySet() throws BusinessException
	{
		final AmwayBonusPeriodModel activeBonusPeriods[] = { bonusPeriod2Active, bonusPeriod1Active };
		given(amwayBonusPeriodDao.findActiveBonusPeriods(Arrays.asList(baseSiteModel)))
				.willReturn(Arrays.asList(activeBonusPeriods));

		order.setDate(insideBP1BeforeCutoff);
		defaultAmwayBonusPeriodService.assignBonusPeriod(order, bonusPeriod1Active);
		assertEquals("should be the current bonus period", bonusPeriod1Active, order.getBonusPeriod());

		//do not reset bonus period but change order date to be able to try to assign a new bonus period
		order.setDate(insideBP1AfterCutoff1);

		defaultAmwayBonusPeriodService.assignBonusPeriod(order);
		assertEquals("should be the current bonus period", bonusPeriod1Active, order.getBonusPeriod());
	}

}
