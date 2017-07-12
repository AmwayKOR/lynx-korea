package com.amway.amwayinventory.validator.stock.impl;

import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.amway.amwayinventory.AmwayInventoryTestConstants;
import com.amway.amwayinventory.data.AmwayInventoryBean;


@UnitTest
public class AmwayFullInventoryValidatorTest
{

	private static final String NEGATIVE_AVAILABLE_AMOUNT_ERROR_CODE = "amway.inventory.bean.available.negative";

	private AmwayFullInventoryValidator amwayFullInventoryValidator = new AmwayFullInventoryValidator();

	@Test
	public void whenInventoryBeanHasNegativeAvailableAmountThenInvalid() throws Exception
	{
		AmwayInventoryBean amwayInventoryBean = new AmwayInventoryBean();
		amwayInventoryBean.setAvailable(-1);
		Errors errors = new BeanPropertyBindingResult(amwayInventoryBean, AmwayInventoryTestConstants.INVENTORY_BEAN);
		amwayFullInventoryValidator.validate(amwayInventoryBean, errors);
		//@formatter:off
		assertTrue(errors.getAllErrors().stream()
				.map(ObjectError::getCode)
				.anyMatch(NEGATIVE_AVAILABLE_AMOUNT_ERROR_CODE::equals));
		//@formatter:on
	}

}
