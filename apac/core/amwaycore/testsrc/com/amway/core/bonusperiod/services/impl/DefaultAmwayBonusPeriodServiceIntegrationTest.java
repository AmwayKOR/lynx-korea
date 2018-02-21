package com.amway.core.bonusperiod.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayBonusPeriodModel;

import junit.framework.Assert;


/**
 *
 */
@IntegrationTest
public class DefaultAmwayBonusPeriodServiceIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	DefaultAmwayBonusPeriodService defaultAmwayBonusPeriodService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/periodServicesTestData/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/periodServicesTestData/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/periodServicesTestData/orderTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/periodServicesTestData/bonusPeriodServiceTestData/bonusPeriodTestData.csv", "windows-1252");
	}

	private OrderModel getOrderModel(final String code)
	{
		final String query = "SELECT {PK} FROM {Order AS o} WHERE {o.code} = ?code";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);

		final SearchResult<OrderModel> modelSearchResult = flexibleSearchService.search(query, values);
		final List<OrderModel> modelResultList = modelSearchResult.getResult();
		Assert.assertTrue(modelResultList.size() == 1);
		return modelResultList.get(0);
	}

	private AmwayBonusPeriodModel getBonusPeriod(final String code)
	{
		final String query = "SELECT {PK} FROM {AmwayBonusPeriod AS bp} WHERE {bp.code} = ?code";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);
		final SearchResult<AmwayBonusPeriodModel> modelSearchResult = flexibleSearchService.search(query, values);
		final List<AmwayBonusPeriodModel> bonusPeriods = modelSearchResult.getResult();
		assertTrue(bonusPeriods.size() == 1);
		return bonusPeriods.get(0);
	}

	//ATODO: add tests
	@Test
	public void shouldAssignCurrent() throws BusinessException
	{
		final OrderModel order = getOrderModel(getCurrentMonthOrderCode());
		//final AmwayBonusPeriodModel feb2016_bp = getBonusPeriod("FEB2016-BP");

		defaultAmwayBonusPeriodService.assignBonusPeriod(order);
		assertEquals(order.getBonusPeriod().getCode(), getCurrentBonusPeriodCode());
	}

	@Test
	public void shouldAssignNextActive() throws BusinessException
	{
		final OrderModel feb2016_2 = getOrderModel(getCurrentMonthOrderCode());
		//final AmwayBonusPeriodModel mar2016_bp = getBonusPeriod("MAR2016-BP");

		defaultAmwayBonusPeriodService.assignBonusPeriod(feb2016_2);
		Assert.assertNotSame("Should not Assign Next Active Bonus Perios", feb2016_2.getBonusPeriod(), getCurrentBonusPeriodCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAssign() throws BusinessException
	{
		final OrderModel order = getOrderModel(getCurrentMonthOrderCode());
		final AmwayBonusPeriodModel bonusPeriod = getBonusPeriod("NEXT_MONTH_BP");
		defaultAmwayBonusPeriodService.assignBonusPeriod(order, bonusPeriod);
	}

	/**
	 * @return
	 */
	private String getCurrentBonusPeriodCode()
	{
		return "CURRENT_MONTH_BP";
	}

	private String getCurrentMonthOrderCode()
	{
		return "CURRENT_MONTH_ORDER";
	}
}
