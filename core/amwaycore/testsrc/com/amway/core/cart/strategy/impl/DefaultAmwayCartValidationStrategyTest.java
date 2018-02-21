/**
 *
 */
package com.amway.core.cart.strategy.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.stock.service.AmwayCommerceStockService;




@UnitTest
public class DefaultAmwayCartValidationStrategyTest
{
	@InjectMocks
	private final DefaultAmwayCartValidationStrategy defaultAmwayCartValidationStrategy = new DefaultAmwayCartValidationStrategy();
	@Mock
	private CommerceCartService cartService;
	@Mock
	BaseSiteService baseSiteService;
	@Mock
	private AmwayCommerceStockService amwayCommerceStockService;
	@Mock
	private SessionService sessionService;
	@Mock
	private ModelService modelService;

	private CartModel cart;
	private ProductModel product1, product2;
	private OrderModel order;
	private PointOfServiceModel posModel;
	private AbstractOrderEntryModel entry1, entry2;
	private StockLevelModel stock1, stock2;
	private WarehouseModel wareHouseModel1, wareHouseModel2;



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		defaultAmwayCartValidationStrategy.setModelService(modelService);
		product1 = Mockito.spy(new ProductModel());
		product2 = Mockito.spy(new ProductModel());
		/*
		 * when(product1.getCode()).thenReturn("Product1"); when(product1.getCode()).thenReturn("Product2");
		 */

		posModel = Mockito.mock(PointOfServiceModel.class);
		entry1 = new CartEntryModel();
		entry2 = new CartEntryModel();
		entry1.setProduct(product1);
		entry1.setQuantity(Long.valueOf(1));
		entry1.setGiveAway(Boolean.TRUE);
		entry1.setDeliveryPointOfService(posModel);
		entry2.setQuantity(Long.valueOf(1));
		entry2.setGiveAway(Boolean.TRUE);
		//		entry2.setDeliveryPointOfService(posModel);
		entry2.setProduct(product2);

		cart = Mockito.spy(new CartModel());
		cart.setEntries(Arrays.asList(entry1, entry2));

		entry1.setOrder(cart);
		entry2.setOrder(cart);

		wareHouseModel1 = Mockito.spy(new WarehouseModel());
		wareHouseModel2 = Mockito.spy(new WarehouseModel());
		wareHouseModel1.setCode("warehouse1");
		wareHouseModel2.setCode("warehouse2");
		stock1 = Mockito.spy(new StockLevelModel());
		stock1.setSkuId("stock1");
		stock1.setWarehouse(wareHouseModel1);
		stock1.setAvailable(50);
		stock1.setReserved(10);
		stock2 = Mockito.spy(new StockLevelModel());
		stock2.setSkuId("stock2");
		stock2.setWarehouse(wareHouseModel2);
		stock2.setAvailable(20);
		stock2.setReserved(2);

		when(sessionService.getAttribute("validateStock")).thenReturn(Boolean.TRUE);

	}




}
