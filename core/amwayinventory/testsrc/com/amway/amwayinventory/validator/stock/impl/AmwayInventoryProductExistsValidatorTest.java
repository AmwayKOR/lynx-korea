package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.amway.amwayinventory.data.AmwayInventoryBean;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AmwayInventoryProductExistsValidatorTest
{
	@Mock
	private ProductService productService;
	@Mock
	private WarehouseService warehouseService;
	@Mock
	private SessionService sessionService;
	@Mock
	private UserService userService;
	@Mock
	private CatalogVersionModel catalogVersion;
	@Mock
	private CatalogModel catalog;
	@Mock
	private BaseStoreModel baseStore;
	@Mock
	private WarehouseModel warehouse;

	@InjectMocks
	private AmwayInventoryProductCodeValidator amwayInventoryProductExistsValidator = new AmwayInventoryProductCodeValidator();

	@Before
	public void setUp()
	{
		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenReturn(warehouse);
		when(warehouse.getBaseStores()).thenReturn(Collections.singleton(baseStore));
		when(baseStore.getCatalogs()).thenReturn(Collections.singletonList(catalog));
		when(catalog.getCatalogVersions()).thenReturn(Collections.singleton(catalogVersion));
		when(sessionService.executeInLocalView(any(),any())).thenAnswer(this::mockExecuteInLocal);
	}

	@Test
	public void testWhenCodeIsNotNullAndProductWithCodeDoesNotExistThenValidationErrorsAreNotEmpty() throws Exception
	{
		when(productService.getProductForCode(catalogVersion, PRODUCT_1)).thenThrow(new IllegalArgumentException());
		assertTrue(validateInventoryBeanWithBaseItemNumber(PRODUCT_1));
	}

	@Test
	public void testWhenCodeIsNullThenValidationErrorsAreNotEmpty() throws Exception
	{
		when(productService.getProductForCode(catalogVersion, null)).thenThrow(new IllegalArgumentException());
		assertTrue(validateInventoryBeanWithBaseItemNumber(null));
	}

	@Test
	public void testWhenProductWithExistsThenValidationErrorsAreEmpty() throws Exception
	{
		when(productService.getProductForCode(catalogVersion, PRODUCT_2)).thenReturn(new ProductModel());
		assertFalse(validateInventoryBeanWithBaseItemNumber(PRODUCT_2));
	}

	private boolean validateInventoryBeanWithBaseItemNumber(String code)
	{
		AmwayInventoryBean amwayInventoryBean = new AmwayInventoryBean();
		Errors errors = new BeanPropertyBindingResult(amwayInventoryBean, INVENTORY_BEAN);
		amwayInventoryBean.setBaseItemNumber(code);
		amwayInventoryBean.setWarehouseCode(WAREHOUSE_1);
		amwayInventoryProductExistsValidator.validate(amwayInventoryBean, errors);
		return errors.hasErrors();
	}

	private Object mockExecuteInLocal(InvocationOnMock invocationOnMock) throws Throwable
	{
		return ((SessionExecutionBody)invocationOnMock.getArguments()[0]).execute();
	}

}
