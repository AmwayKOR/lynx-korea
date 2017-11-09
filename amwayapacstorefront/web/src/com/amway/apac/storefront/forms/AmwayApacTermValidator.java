/**
 *
 */
package com.amway.apac.storefront.forms;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validator for Terms and Condition Page
 *
 * @author Badrun Bandi
 *
 */

@Component("termValidator")
public class AmwayApacTermValidator implements Validator
{
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return AmwayApacTermForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final AmwayApacTermForm termForm = (AmwayApacTermForm) object;
		final List<AmwayApacTerm> termList = termForm.getTerm();
		final Boolean verified = termForm.getVerified();

		if (BooleanUtils.isNotTrue(verified))
		{
			errors.rejectValue(null, "account.term.verified");
		}

		for (final AmwayApacTerm term : termList)
		{
			if (BooleanUtils.isNotTrue(term.getOptional()) && BooleanUtils.isNotTrue(term.getTermAccepted()))
			{
				errors.rejectValue(null, "account.term.accepted");
			}

			if (BooleanUtils.isTrue(term.getOptional()) && term.getTermAccepted() == null)
			{
				errors.rejectValue(null, "account.term.optional");
			}
		}

	}
}
