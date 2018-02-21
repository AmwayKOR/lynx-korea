package com.amway.amwayinventory.service.impl;

import static java.util.stream.Collectors.toList;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;
import com.amway.amwayinventory.service.AmwayInventoryValidationService;


public class AmwayInventoryValidationServiceImpl implements AmwayInventoryValidationService
{
	@Autowired
	private Converter<Collection<Pair<AmwayInventoryBean, Errors>>, AmwayInventoryValidationResult> amwayInventoryValidationResultConverter;

	private List<Validator> validators;

	@Override
	public AmwayInventoryValidationResult validate(Collection<AmwayInventoryBean> inventoryBeans)
	{
		//@formatter:off
		Collection<Pair<AmwayInventoryBean, Errors>> beanToErrors = inventoryBeans.stream()
				.map(this::validateBean)
				.collect(toList());
		//@formatter:on
		return amwayInventoryValidationResultConverter.convert(beanToErrors);
	}

	private Pair<AmwayInventoryBean, Errors> validateBean(AmwayInventoryBean inventoryBean)
	{
		String beanName = "(" + inventoryBean.getBaseItemNumber() + ":" + inventoryBean.getWarehouseCode() + ")";
		Errors errors = new BeanPropertyBindingResult(inventoryBean, beanName);
		validators.forEach(validator -> validator.validate(inventoryBean, errors));
		return new ImmutablePair<>(inventoryBean, errors);
	}

	@Required
	public void setValidators(List<Validator> validators)
	{
		this.validators = validators;
	}
}
