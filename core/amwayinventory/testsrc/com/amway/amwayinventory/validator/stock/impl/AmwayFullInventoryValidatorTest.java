package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.amway.amwayinventory.data.AmwayInventoryBean;


@UnitTest
public class AmwayFullInventoryValidatorTest
{
	@InjectMocks
	private AmwayFullInventoryValidator amwayFullInventoryValidator;

	@Mock
	private WarehouseService warehouseService;

	@Mock
	private WarehouseModel warehouse;
	@Mock
	private AmwayInventoryBean amwayInventoryBean;

	private Errors errors;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		errors = new BeanPropertyBindingResult(amwayInventoryBean, INVENTORY_BEAN);
		when(warehouseService.getWarehouseForCode(any())).thenReturn(warehouse);
		when(amwayInventoryBean.getWarehouseCode()).thenReturn(WAREHOUSE_1);
		when(amwayInventoryBean.getBaseItemNumber()).thenReturn(BASE_ITEM_NUMBER);
	}

	@Test
	public void shouldAddErrorWhenAvailableIsNegative()
	{
		when(amwayInventoryBean.getAvailable()).thenReturn(-5);

		amwayFullInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorWhenAvailableIsPositive()
	{
		when(amwayInventoryBean.getAvailable()).thenReturn(5);

		amwayFullInventoryValidator.validate(amwayInventoryBean, errors);

		assertFalse(errors.hasErrors());
	}
}
