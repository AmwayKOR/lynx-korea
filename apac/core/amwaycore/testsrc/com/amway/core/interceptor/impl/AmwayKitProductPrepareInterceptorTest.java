/**
 *
 */
package com.amway.core.interceptor.impl;

import static org.mockito.Mockito.when;

import com.amway.core.enums.AmwayKitProductType;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayKitProductModel;


@UnitTest
public class AmwayKitProductPrepareInterceptorTest
{
	@InjectMocks
	private final AmwayKitProductPrepareInterceptor interceptor = new AmwayKitProductPrepareInterceptor();
	@Mock
	private ModelService modelService;
	@Mock
	private WarehouseService warehouseService;
	private AmwayKitProductModel kitProduct;
	@Mock
	private InterceptorContext ctx;
	private WarehouseModel warehouse;



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		interceptor.setModelService(modelService);

		kitProduct = new AmwayKitProductModel();
		kitProduct.setCode("K0001");
		kitProduct.setType(AmwayKitProductType.BUNDLED);

		warehouse = Mockito.mock(WarehouseModel.class);

		when(modelService.create(StockLevelModel.class)).thenReturn(new StockLevelModel());



	}

	/**
	 * Test method for
	 * {@link com.amway.core.interceptor.impl.AmwayKitProductPrepareInterceptor#onPrepare(com.amway.core.model.AmwayKitProductModel, de.hybris.platform.servicelayer.interceptor.InterceptorContext)}
	 * .
	 *
	 * @throws InterceptorException
	 */
	@Test(expected = NullPointerException.class)
	public void testOnPrepareForNullWarehouse() throws InterceptorException
	{
		when(warehouseService.getDefWarehouse()).thenReturn(null);
		interceptor.onPrepare(kitProduct, ctx);
		Assert.assertTrue(CollectionUtils.isEmpty(kitProduct.getStockLevels()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.interceptor.impl.AmwayKitProductPrepareInterceptor#onPrepare(com.amway.core.model.AmwayKitProductModel, de.hybris.platform.servicelayer.interceptor.InterceptorContext)}
	 * .
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepareNoWarehouseConfigured() throws InterceptorException
	{
		when(warehouseService.getDefWarehouse()).thenReturn(new ArrayList<WarehouseModel>());
		interceptor.onPrepare(kitProduct, ctx);
		Assert.assertTrue(CollectionUtils.isEmpty(kitProduct.getStockLevels()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.interceptor.impl.AmwayKitProductPrepareInterceptor#onPrepare(com.amway.core.model.AmwayKitProductModel, de.hybris.platform.servicelayer.interceptor.InterceptorContext)}
	 * .
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepare() throws InterceptorException
	{
		when(warehouseService.getDefWarehouse()).thenReturn(Arrays.asList(warehouse));
		interceptor.onPrepare(kitProduct, ctx);
		Assert.assertTrue("kit product stock level collection is not empty",CollectionUtils.isNotEmpty(kitProduct.getStockLevels()));
		Assert.assertEquals(1, CollectionUtils.size(kitProduct.getStockLevels()));
	}

}
