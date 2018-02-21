/**
 *
 */
package com.amway.core.stock.strategy;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.stock.strategy.impl.DefaultAmwayWarehouseSelectionStrategy;


@UnitTest
public class DefaultAmwayWarehouseSelectionStrategyUnitTest
{
	@InjectMocks
	@Mock
	private ProductModel productModel;
	@Mock
	private BaseStoreModel baseStoreModel;
	@Mock
	private AbstractOrderModel abstractOrderModel;
	@Mock
	private PointOfServiceModel pointOfServiceModel;
	@Mock

	//@Mock
	StockLevelModel stockLevelModel;
	List<StockLevelModel> stockList;

	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);
		stockLevelModel = Mockito.spy(new StockLevelModel());
		stockLevelModel.setAvailable(10);
		stockLevelModel.setReserved(5);
		productModel.setCode("100368");
		stockList = new ArrayList();
		stockList.add(stockLevelModel);
	}


}
