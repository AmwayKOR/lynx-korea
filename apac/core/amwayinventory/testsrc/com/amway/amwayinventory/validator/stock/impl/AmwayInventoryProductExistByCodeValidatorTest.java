package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.*;
import static org.apache.commons.lang.StringUtils.EMPTY;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.amway.amwayinventory.data.AmwayInventoryBean;


@UnitTest
public class AmwayInventoryProductExistByCodeValidatorTest
{
	@InjectMocks
	private AmwayInventoryProductExistByCodeValidator amwayInventoryProductExistsValidator;

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

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenReturn(warehouse);
		when(warehouse.getBaseStores()).thenReturn(Collections.singleton(baseStore));
		when(baseStore.getCatalogs()).thenReturn(Collections.singletonList(catalog));
		when(catalog.getCatalogVersions()).thenReturn(Collections.singleton(catalogVersion));
		when(sessionService.executeInLocalView(any(), any())).thenAnswer(this::mockExecuteInLocal);
	}

	@Test
	public void shouldAddErrorWhenCodeIsNotNullAndProductWithCodeDoesNotExist()
	{
		AmwayInventoryBean inventoryBean = createInventoryBean(PRODUCT_1);
		Errors errors = createErrors(inventoryBean);
		when(productService.getProductForCode(catalogVersion, PRODUCT_1)).thenThrow(new IllegalArgumentException());

		amwayInventoryProductExistsValidator.validate(inventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorWhenCodeIsNull()
	{
		AmwayInventoryBean inventoryBean = createInventoryBean(null);
		Errors errors = createErrors(inventoryBean);

		amwayInventoryProductExistsValidator.validate(inventoryBean, errors);

		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorWhenCodeIsEmpty()
	{
		AmwayInventoryBean inventoryBean = createInventoryBean(EMPTY);
		Errors errors = createErrors(inventoryBean);

		amwayInventoryProductExistsValidator.validate(inventoryBean, errors);

		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorWhenWarehouseNotFound()
	{
		AmwayInventoryBean inventoryBean = createInventoryBean(PRODUCT_1);
		Errors errors = createErrors(inventoryBean);
		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenReturn(null);

		amwayInventoryProductExistsValidator.validate(inventoryBean, errors);

		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorWhenWarehouseServiceThrowException()
	{
		AmwayInventoryBean inventoryBean = createInventoryBean(PRODUCT_1);
		Errors errors = createErrors(inventoryBean);
		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenThrow(new IllegalArgumentException());

		amwayInventoryProductExistsValidator.validate(inventoryBean, errors);

		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorsWhenProductWithExists()
	{
		AmwayInventoryBean inventoryBean = createInventoryBean(PRODUCT_2);
		Errors errors = createErrors(inventoryBean);
		when(productService.getProductForCode(catalogVersion, PRODUCT_2)).thenReturn(new ProductModel());

		amwayInventoryProductExistsValidator.validate(inventoryBean, errors);

		assertFalse(errors.hasErrors());
	}

	private AmwayInventoryBean createInventoryBean(String code)
	{
		AmwayInventoryBean amwayInventoryBean = new AmwayInventoryBean();
		amwayInventoryBean.setBaseItemNumber(code);
		amwayInventoryBean.setWarehouseCode(WAREHOUSE_1);
		return amwayInventoryBean;
	}

	private Errors createErrors(AmwayInventoryBean amwayInventoryBean)
	{
		return new BeanPropertyBindingResult(amwayInventoryBean, INVENTORY_BEAN);
	}

	private Object mockExecuteInLocal(InvocationOnMock invocationOnMock) throws Throwable
	{
		return ((SessionExecutionBody) invocationOnMock.getArguments()[0]).execute();
	}
}
