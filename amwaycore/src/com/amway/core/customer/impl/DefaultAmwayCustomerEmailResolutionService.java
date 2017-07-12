package com.amway.core.customer.impl;


import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerEmailResolutionService;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.mail.MailUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;


/**
 * Changed the retrieval of email from uid to customer email attribute on {@link CustomerModel}
 */
public class DefaultAmwayCustomerEmailResolutionService extends DefaultCustomerEmailResolutionService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayCustomerEmailResolutionService.class);

	@Override
	protected String validateAndProcessEmailForCustomer(final CustomerModel customerModel)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("customerModel", customerModel);

		final String email = CustomerType.GUEST.equals(customerModel.getType()) ?
				StringUtils.substringAfter(customerModel.getUid(), "|") :
				customerModel.getCustomerEmail();
		try
		{
			MailUtils.validateEmailAddress(email, "customer email");
			return email;
		}
		catch (final EmailException e)
		{
			LOG.error("Given uid is not appropriate email [" + email + "]. cause: " + e.getMessage());
		}
		return StringUtils.EMPTY;
	}

}
