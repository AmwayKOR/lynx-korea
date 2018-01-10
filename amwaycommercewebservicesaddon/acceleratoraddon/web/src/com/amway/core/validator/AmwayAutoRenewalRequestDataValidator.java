package com.amway.core.validator;

import static com.amway.core.constants.AmwaycommercewebservicesaddonWebConstants.AUTORENEWAL_COUNTRY_REQUIRED;
import static com.amway.core.constants.AmwaycommercewebservicesaddonWebConstants.AUTORENEWAL_LANGUAGE_REQUIRED;
import static com.amway.core.constants.AmwaycommercewebservicesaddonWebConstants.AUTORENEWAL_PAYMENT_ALIAS_REQUIRED;
import static com.amway.core.constants.AmwaycommercewebservicesaddonWebConstants.AUTORENEWAL_SERVICE_DATE_FORMAT;
import static com.amway.core.constants.AmwaycommercewebservicesaddonWebConstants.DATE_FORMAT_INCORRECT;
import static com.amway.core.util.AmwayDateHelper.parseDate;
import static org.springframework.validation.ValidationUtils.rejectIfEmpty;

import java.util.Date;
import java.util.Objects;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amway.facades.renewal.data.AmwayAutoRenewalRequestData;



public class AmwayAutoRenewalRequestDataValidator implements Validator
{
	private static final String COUNTRY = "country";
	private static final String LANGUAGE = "language";
	private static final String PAYMENT_ALIAS = "paymentAlias";

	@Override
	public boolean supports(Class<?> aClass)
	{
		return AmwayAutoRenewalRequestData.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors)
	{
		AmwayAutoRenewalRequestData autoRenewalRequest = (AmwayAutoRenewalRequestData) o;
		rejectIfEmpty(errors, COUNTRY, AUTORENEWAL_COUNTRY_REQUIRED);
		rejectIfEmpty(errors, LANGUAGE, AUTORENEWAL_LANGUAGE_REQUIRED);
		rejectIfEmpty(errors, PAYMENT_ALIAS, AUTORENEWAL_PAYMENT_ALIAS_REQUIRED);

		if (Objects.nonNull(autoRenewalRequest.getRegistrationDate()))
		{
			Date shipDate = parseDate(autoRenewalRequest.getRegistrationDate(), AUTORENEWAL_SERVICE_DATE_FORMAT);
			if (Objects.isNull(shipDate))
			{
				errors.reject(DATE_FORMAT_INCORRECT);
			}
		}
	}
}
