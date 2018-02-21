package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.*;
import static org.apache.commons.lang.StringUtils.EMPTY;
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
public class AmwayInventoryValidatorTest
{
	@InjectMocks
	private AmwayInventoryValidator amwayInventoryValidator;

	@Mock
	private WarehouseService warehouseService;

	@Mock
	private AmwayInventoryBean amwayInventoryBean;
	@Mock
	private WarehouseModel warehouse;

	private Errors errors;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		errors = new BeanPropertyBindingResult(amwayInventoryBean, INVENTORY_BEAN);

		when(warehouseService.getWarehouseForCode(any())).thenReturn(warehouse);
		when(amwayInventoryBean.getWarehouseCode()).thenReturn(WAREHOUSE_1);
		when(amwayInventoryBean.getBaseItemNumber()).thenReturn(BASE_ITEM_NUMBER);
		when(amwayInventoryBean.getAvailable()).thenReturn(10);
	}

	@Test
	public void shouldAddErrorWhenBaseItemNumberNull()
	{
		when(amwayInventoryBean.getBaseItemNumber()).thenReturn(null);

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldAddErrorWhenBaseItemNumberIsEmpty()
	{
		when(amwayInventoryBean.getBaseItemNumber()).thenReturn(EMPTY);

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldAddErrorWhenWarehouseCodeIsNull()
	{
		when(amwayInventoryBean.getWarehouseCode()).thenReturn(null);

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldAddErrorWhenWarehouseCodeIsEmpty()
	{
		when(amwayInventoryBean.getWarehouseCode()).thenReturn(EMPTY);

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldAddErrorWhenWarehouseIsNullByCode()
	{
		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenReturn(null);

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldAddErrorWhenWarehouseServiceThrowException()
	{
		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenThrow(new IllegalArgumentException());

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldAddErrorWhenAvailableIsNull()
	{
		when(amwayInventoryBean.getAvailable()).thenReturn(null);

		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldNotAddErrorsWhenBeanIsValid()
	{
		amwayInventoryValidator.validate(amwayInventoryBean, errors);

		assertFalse(errors.hasErrors());
	}
}
