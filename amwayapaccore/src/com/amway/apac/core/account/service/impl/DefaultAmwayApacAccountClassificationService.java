package com.amway.apac.core.account.service.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountClassificationService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;


/**
 * Default implementation of {@link AmwayApacAccountClassificationService}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacAccountClassificationService implements AmwayApacAccountClassificationService
{
	private Comparator<AccountClassificationEnum> amwayApacAccountClassificationComparator;
	private SessionService sessionService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkUserClassification(final AccountClassificationEnum referenceClassification)
	{
		validateParameterNotNullStandardMessage("referenceClassification", referenceClassification);

		boolean result = false;
		final String accountClassficationCode = getSessionService()
				.getAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE);
		if (null != accountClassficationCode)
		{
			result = getAmwayApacAccountClassificationComparator()
					.compare(AccountClassificationEnum.valueOf(accountClassficationCode), referenceClassification) >= 0;
		}
		return result;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the amwayApacAccountClassificationComparator
	 */
	public Comparator<AccountClassificationEnum> getAmwayApacAccountClassificationComparator()
	{
		return amwayApacAccountClassificationComparator;
	}

	/**
	 * @param amwayApacAccountClassificationComparator
	 *           the amwayApacAccountClassificationComparator to set
	 */
	@Required
	public void setAmwayApacAccountClassificationComparator(
			final Comparator<AccountClassificationEnum> amwayApacAccountClassificationComparator)
	{
		this.amwayApacAccountClassificationComparator = amwayApacAccountClassificationComparator;
	}
}
