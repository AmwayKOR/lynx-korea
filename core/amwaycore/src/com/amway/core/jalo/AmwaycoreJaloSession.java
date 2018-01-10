/**
 *
 */
package com.amway.core.jalo;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.jalo.CommerceJaloSession;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.user.Employee;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.site.BaseSiteService;

import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class AmwaycoreJaloSession extends CommerceJaloSession
{
	private final static Logger LOG = Logger.getLogger(AmwaycoreJaloSession.class);

	@Override
	protected void initSessionContext(final Map props) throws JaloSecurityException
	{
		super.initSessionContext(props);
		updateSessionCtxTimeZone();
	}

	@Override
	public void setUser(final User user)
	{
		super.setUser(user);
		updateSessionCtxTimeZone();
	}

	private void updateSessionCtxTimeZone()
	{
		final BaseSiteModel currentBaseSite = Registry.getApplicationContext().getBean("baseSiteService", BaseSiteService.class).getCurrentBaseSite();
		final User user = getSessionContext().getUser();
		String timeZone = null;
		if (user != null && user instanceof Employee)
		{
			Object timeZoneObject = null;
			try
			{
				timeZoneObject = ((Employee) user).getAttribute(EmployeeModel.TIMEZONE);
			}
			catch (JaloInvalidParameterException | JaloSecurityException e)
			{
				LOG.error(e.getMessage(), e);
			}
			timeZone =
					timeZoneObject != null && StringUtils.isNotEmpty(timeZoneObject.toString()) ? timeZoneObject.toString() : null;
		}
		else if (currentBaseSite != null && StringUtils.isNotEmpty(currentBaseSite.getTimeZone()))
		{
			timeZone = currentBaseSite.getTimeZone();
		}
		if (StringUtils.isNotEmpty(timeZone) && !getSessionContext().getTimeZone().getID().equals(timeZone))
		{
			final TimeZone currentTimeZone = getSessionContext().getTimeZone();
			getSessionContext().setTimeZone(TimeZone.getTimeZone(timeZone));
			LOG.debug("updated the" + (currentBaseSite != null ? " (for site " + currentBaseSite.getUid() + ") " : "")
					+ " time zone in session :" + getSessionContext().getTimeZone() + " from :" + currentTimeZone);
		}
	}

}
