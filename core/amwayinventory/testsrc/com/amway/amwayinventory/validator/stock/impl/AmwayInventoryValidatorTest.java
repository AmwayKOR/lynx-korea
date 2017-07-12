package com.amway.amwayinventory.validator.stock.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.amway.amwayinventory.AmwayInventoryTestConstants;
import com.amway.amwayinventory.data.AmwayInventoryBean;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AmwayInventoryValidatorTest
{

	private static final List<String> errorCodes = Arrays.asList("amway.inventory.bean.baseitemnumber.empty",
			"amway.inventory.bean.warehousecode.empty","amway.inventory.bean.warehousecode.doesnotexist",
			"amway.inventory.bean.available.empty");

	@Mock
	private WarehouseService warehouseService;

	@InjectMocks
	private AmwayInventoryValidator amwayInventoryValidator = new AmwayInventoryValidator();

	@Test
	public void whenBeanIsInvalidThenErrorsExist() throws Exception
	{
		AmwayInventoryBean amwayInventoryBean = new AmwayInventoryBean();
		Errors errors = new BeanPropertyBindingResult(amwayInventoryBean, AmwayInventoryTestConstants.INVENTORY_BEAN);
		amwayInventoryValidator.validate(amwayInventoryBean,errors);
		//@formatter:off
		assertFalse(errors.getAllErrors().isEmpty() && errors.getAllErrors().stream()
				.map(ObjectError::getCode)
				.allMatch(errorCodes::contains));
		//@formatter:on
	}

	@Test
	public void whenBeanIsValidThenNoErrors () throws Exception
	{
		AmwayInventoryBean amwayInventoryBean = new AmwayInventoryBean();
		amwayInventoryBean.setBaseItemNumber(AmwayInventoryTestConstants.BASE_ITEM_NUMBER);
		amwayInventoryBean.setWarehouseCode(AmwayInventoryTestConstants.WAREHOUSE_1);
		amwayInventoryBean.setAvailable(0);
		WarehouseModel warehouseMock = new WarehouseModel();
		when(warehouseService.getWarehouseForCode(AmwayInventoryTestConstants.WAREHOUSE_1)).thenReturn(warehouseMock);
		Errors errors = new BeanPropertyBindingResult(amwayInventoryBean, AmwayInventoryTestConstants.INVENTORY_BEAN);
		amwayInventoryValidator.validate(amwayInventoryBean,errors);
		assertTrue(errors.getAllErrors().isEmpty());
	}

}
