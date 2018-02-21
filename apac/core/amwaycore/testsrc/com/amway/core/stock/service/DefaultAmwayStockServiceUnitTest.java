/**
 *
 */
package com.amway.core.stock.service;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.stock.service.impl.DefaultAmwayStockService;


@UnitTest
public class DefaultAmwayStockServiceUnitTest
{
	@InjectMocks
	DefaultAmwayStockService defaultAmwayStockService = new DefaultAmwayStockService();

	@Mock
	ProductModel product;
	@Mock
	WarehouseModel warehouse;
	AbstractOrderEntryModel abstractOrderEntryModel;
	ReturnEntryModel returnEntryModel;


	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		abstractOrderEntryModel = Mockito.spy(new AbstractOrderEntryModel());
		returnEntryModel = Mockito.spy(new ReturnEntryModel());

	}


}
