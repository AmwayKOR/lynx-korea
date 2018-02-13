package com.amway.amwayinventory.populator;

import static com.amway.amwayinventory.constants.AmwayInventoryConstants.*;

import de.hybris.platform.converters.Populator;

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;


public class AmwayInventoryValidationResultPopulator
		implements Populator<Collection<Pair<AmwayInventoryBean, Errors>>, AmwayInventoryValidationResult>
{
	@Autowired
	private MessageSource amwayInventoryValidationMessageSource;

	@Override
	public void populate(Collection<Pair<AmwayInventoryBean, Errors>> source, AmwayInventoryValidationResult target)
	{
		//@formatter:off
		String errorMessage = getValidationErrorsStream(source)
				.map(this::buildErrorMessage)
				.collect(Collectors.joining(MSG_DELIMITER, MSG_PREFIX, MSG_SUFFIX));
		boolean isAnyInvalid = getValidationErrorsStream(source)
				.anyMatch(Errors::hasErrors);
		boolean isAllInvalid = getValidationErrorsStream(source)
				.allMatch(Errors::hasErrors);
		//@formatter:on

		target.setBeansWithoutErrors(findValidInventoryBeansForProcessing(source));
		target.setErrorMessage(errorMessage);
		target.setAnyInvalid(isAnyInvalid);
		target.setAllInvalid(isAllInvalid);
	}

	private Stream<Errors> getValidationErrorsStream(Collection<Pair<AmwayInventoryBean, Errors>> source)
	{
		//@formatter:off
		return source.stream()
				.map(Pair::getValue);
		//@formatter:on
	}

	private String buildErrorMessage(Errors errors)
	{
		//@formatter:off
		String fieldErrors = errors.getAllErrors().stream()
				.map(this::getLocalizedErrorMessage)
				.collect(Collectors.joining(MSG_DELIMITER, MSG_PREFIX, MSG_SUFFIX));
		return "{ " + errors.getObjectName() + " -> " + fieldErrors + " }";
		//@formatter:on
	}

	private String getLocalizedErrorMessage(ObjectError error)
	{
		return amwayInventoryValidationMessageSource.getMessage(error.getCode(), error.getArguments(), Locale.ENGLISH);
	}

	private Collection<AmwayInventoryBean> findValidInventoryBeansForProcessing(
			Collection<Pair<AmwayInventoryBean, Errors>> beanToErrorPairs)
	{
		//@formatter:off
		return beanToErrorPairs.stream()
				.filter(pair -> !pair.getValue().hasErrors())
				.map(Pair::getKey)
				.collect(Collectors.toList());
		//@formatter:on
	}
}
