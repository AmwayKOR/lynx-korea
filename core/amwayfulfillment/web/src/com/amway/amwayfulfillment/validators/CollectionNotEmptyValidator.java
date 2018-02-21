package com.amway.amwayfulfillment.validators;

import de.hybris.platform.webservicescommons.validators.FieldNotEmptyValidator;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class CollectionNotEmptyValidator extends FieldNotEmptyValidator
{
	private static final Logger LOG = Logger.getLogger(CollectionNotEmptyValidator.class);

	private static final String FIELD_REQUIRED_MESSAGE_ID = "field.required";

	private static final String FIELD_ACCESS_MESSAGE_ID = "field.access.error";

	private Validator validator;

	private boolean validateEntries = false;

	@Override
	public void validate(final Object object, final Errors errors)
	{
		Assert.notNull(errors, "Errors object must not be null");
		Object fieldValue;
		try
		{
			fieldValue = errors.getFieldValue(getFieldPath());
		}
		catch (final NotReadablePropertyException e)
		{
			LOG.warn(e);
			errors.rejectValue(getFieldPath(), FIELD_ACCESS_MESSAGE_ID, new String[] { getFieldPath() }, "Can't get access to the field.");
			return;
		}

		if (fieldValue == null || (fieldValue instanceof Collection && ((Collection) fieldValue).isEmpty()))
		{
			errors.rejectValue(getFieldPath(), FIELD_REQUIRED_MESSAGE_ID, new String[]{ getFieldPath() }, "This field is required.");
		}

		if (validateEntries && fieldValue instanceof Iterable)
		{
			final Iterable collection = (Iterable) fieldValue;

			int i = 0;
			for (final Object o : collection)
			{
				Assert.notNull(validator, "Inner validator must not be null");
				errors.pushNestedPath(getFieldPath() + "[" + i++ + "]");
				validator.validate(o, errors);
				errors.popNestedPath();
			}
		}
	}

	public Validator getValidator()
	{
		return validator;
	}

	public void setValidator(Validator validator)
	{
		this.validator = validator;
	}

	public boolean isValidateEntries()
	{
		return validateEntries;
	}

	public void setValidateEntries(boolean validateEntries)
	{
		this.validateEntries = validateEntries;
	}
}
