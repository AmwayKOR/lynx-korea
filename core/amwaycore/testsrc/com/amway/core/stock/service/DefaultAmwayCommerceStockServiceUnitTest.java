/**
 *
 */
package com.amway.core.stock.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.core.stock.service.impl.DefaultAmwayCommerceStockService;
import com.amway.core.stock.strategy.AmwayWarehouseSelectionStrategy;


/**
 * @author Dell-pc
 */
public class DefaultAmwayCommerceStockServiceUnitTest
{
	@InjectMocks
	DefaultAmwayCommerceStockService defaultAmwayStockService = new DefaultAmwayCommerceStockService();

	@Mock
	AmwayWarehouseSelectionStrategy amwayWarehouseSelectionStrategy;
	@Mock
	AmwayStockService amwayStockService;
	@Mock
	AbstractOrderModel abstractOrderModel;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}


}
