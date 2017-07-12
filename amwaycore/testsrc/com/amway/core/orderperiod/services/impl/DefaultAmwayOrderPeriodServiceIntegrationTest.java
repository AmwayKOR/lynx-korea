package com.amway.core.orderperiod.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.amway.core.model.AmwayOrderPeriodModel;

import junit.framework.Assert;


/**
 *
 */
@IntegrationTest
public class DefaultAmwayOrderPeriodServiceIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	DefaultAmwayOrderPeriodService defaultAmwayOrderPeriodService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/periodServicesTestData/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/periodServicesTestData/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/periodServicesTestData/orderPeriodServiceTestData/orderTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/periodServicesTestData/orderPeriodServiceTestData/orderPeriodTestData.csv", "windows-1252");
	}

	private OrderModel getOrderModel(final String code)
	{
		final String query = "SELECT {PK} FROM {Order AS o} WHERE {o.code} = ?code";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);

		final SearchResult<OrderModel> modelSearchResult = flexibleSearchService.search(query, values);
		final List<OrderModel> modelResultList = modelSearchResult.getResult();
		assertTrue(modelResultList.size() == 1);
		return modelResultList.get(0);
	}

	private AmwayOrderPeriodModel getOrderPeriod(final String code)
	{
		final String query = "SELECT {PK} FROM {AmwayOrderPeriod AS op} WHERE {op.code} = ?code";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);
		final SearchResult<AmwayOrderPeriodModel> modelSearchResult = flexibleSearchService.search(query, values);
		final List<AmwayOrderPeriodModel> orderPeriods = modelSearchResult.getResult();
		assertTrue(orderPeriods.size() == 1);
		return orderPeriods.get(0);
	}

	//ATODO: add tests
	@Test
	public void shouldAssignCurrent() throws BusinessException
	{
		final OrderModel feb2015 = getOrderModel("FEB2016");
		final AmwayOrderPeriodModel feb2015_op = getOrderPeriod("FEB2016-OP");
		defaultAmwayOrderPeriodService.assignOrderPeriod(feb2015);
		assertEquals(feb2015_op, feb2015.getOrderPeriod());

		/*
		 * OrderModel mar2015 = getOrderModel("MAR2016"); AmwayOrderPeriodModel mar2015_op = getOrderPeriod("MAR2015-OP");
		 * defaultAmwayOrderPeriodService.assignOrderPeriod(mar2015, mar2015_op); assertEquals(mar2015_op,
		 * mar2015.getOrderPeriod());
		 */
	}

	@Test
	public void shouldNotAssign()
	{
		final OrderModel feb2016 = getOrderModel("FEB2016");
		final AmwayOrderPeriodModel apr2016_op = getOrderPeriod("APR2016-OP");
		try
		{
			defaultAmwayOrderPeriodService.assignOrderPeriod(feb2016, apr2016_op);
			fail("should not assign");
		}
		catch (final BusinessException be)
		{
			assertEquals(be.getMessage(), "Order \"" + feb2016.getCode() + "\" date is outside order period");
		}
	}

	@Test
	public void shouldAssignNextActive() throws BusinessException
	{
		final OrderModel feb2016_2 = getOrderModel("FEB2016");
		final AmwayOrderPeriodModel mar2016_op = getOrderPeriod("MAR2016-OP");

		defaultAmwayOrderPeriodService.assignOrderPeriod(feb2016_2);
		Assert.assertNotSame("Should not Assign Next Active Bonus Perios", feb2016_2.getOrderPeriod(), mar2016_op);
	}

}
