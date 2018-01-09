/**
 *
 */
package com.amway.apac.storefront.util;

import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.amway.apac.storefront.response.dto.AmwayApacResponseMessageDto;


/**
 * @author Badrun Bandi
 *
 */
public class AmwayApacResponseUtils
{

	public static <T> List<AmwayApacResponseMessageDto> populateMessages(final BindingResult result,
			final MessageSource messageSource, final I18NService i18n)
	{

		final List<FieldError> fieldErrors = result.getFieldErrors();
		final List<AmwayApacResponseMessageDto> messages = new ArrayList<>();

		for (final FieldError error : fieldErrors)
		{
			if (error.getCode() != null)
			{
				final String name = messageSource.getMessage(error.getCode(), null, i18n.getCurrentLocale());
				messages.add(new AmwayApacResponseMessageDto(error.getObjectName(), error.getCode(), error.getField(), name));
			}
			else
			{
				messages.add(
						new AmwayApacResponseMessageDto(error.getObjectName(), error.getCode(), error.getField(), error.getCode()));
			}
		}
		return messages;
	}

}