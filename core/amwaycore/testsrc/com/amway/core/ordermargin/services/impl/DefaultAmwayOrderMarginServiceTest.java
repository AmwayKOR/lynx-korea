/**
 *
 */
package com.amway.core.ordermargin.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayOrderMarginModel;

import junit.framework.Assert;


/**
 *
 */
@IntegrationTest
public class DefaultAmwayOrderMarginServiceTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayOrderMarginServiceTest.class);
	@Resource
	private DefaultAmwayOrderMarginService defaultAmwayOrderMarginService;

	@Resource
	private ModelService modelService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCoreData();
	}

	/**
	 * @throws ImpExException
	 */
	private void importCoreData() throws ImpExException
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderTestData.csv", "windows-1252");
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

	@Test
	public void shouldAssign()
	{
		final OrderModel order1 = getOrderModel("certainDay02_date_01");
		defaultAmwayOrderMarginService.calculateAndAssignOrderMargin(order1);
		modelService.save(order1);
		final AmwayOrderMarginModel order1Margin = order1.getMargin();
		Assert.assertNotNull(order1Margin);
		LOG.info(" shouldAssign- margin : " + order1Margin.getMargin().doubleValue());
		Assert.assertEquals(Double.valueOf(9.0), order1Margin.getMargin());
	}

	@Test
	public void shouldAssignForSomeProducts()
	{
		final OrderModel order2 = getOrderModel("certainDay02_date_01_ac1");
		defaultAmwayOrderMarginService.calculateAndAssignOrderMargin(order2);
		modelService.save(order2);
		final AmwayOrderMarginModel order2Margin = order2.getMargin();
		Assert.assertNotNull(order2Margin);
		LOG.info(" shouldAssignForSomeProducts- margin : " + order2Margin.getMargin().doubleValue());
		Assert.assertEquals(Double.valueOf(4.0), order2Margin.getMargin());
	}

	@Test
	public void shouldNotAssign()
	{
		final OrderModel order3 = getOrderModel("certainDay02_date_01_ac2");
		defaultAmwayOrderMarginService.calculateAndAssignOrderMargin(order3);
		modelService.save(order3);
		final AmwayOrderMarginModel order3Margin = order3.getMargin();
		Assert.assertNotNull(order3Margin);
		LOG.info(" shouldNotAssign- margin : " + order3Margin.getMargin().doubleValue());
		Assert.assertEquals(Double.valueOf(0.0), order3Margin.getMargin());
	}
}
