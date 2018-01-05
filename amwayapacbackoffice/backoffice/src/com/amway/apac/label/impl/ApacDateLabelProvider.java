/**
 *
 */
package backoffice.src.com.amway.apac.label.impl;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.util.Locales;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Sessions;

import com.hybris.cockpitng.labels.impl.DefaultDateLabelProvider;


/**
 * Overrides OOTB Date Label Provider class
 */
public class ApacDateLabelProvider extends DefaultDateLabelProvider
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel(final Date date)
	{
		if (date == null)
		{
			return StringUtils.EMPTY;
		}
		else
		{
			final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locales.getCurrent());

			final TimeZone timeZone = getUserTimeZone();
			if (timeZone != null)
			{
				df.setTimeZone(timeZone);
			}
			return df.format(date);
		}
	}



	/**
	 * Gives the date with the specified date format
	 *
	 * @param date
	 *           date to be formatted
	 * @param dateFormat
	 *           date format
	 * @return the string with formatted value
	 */
	public String getDate(final Date date, final DateFormat dateFormat)
	{
		if (date == null)
		{
			return StringUtils.EMPTY;
		}
		else
		{
			final TimeZone timeZone = getUserTimeZone();
			if (timeZone != null)
			{
				dateFormat.setTimeZone(timeZone);
			}
			return dateFormat.format(date);
		}
	}

	/**
	 * @return user preferred time zone
	 */
	private TimeZone getUserTimeZone()
	{
		TimeZone preferredTimeZone = null;
		if (Sessions.getCurrent().getAttribute(Attributes.PREFERRED_TIME_ZONE) != null)
		{
			preferredTimeZone = (TimeZone) Sessions.getCurrent().getAttribute(Attributes.PREFERRED_TIME_ZONE);
		}
		return preferredTimeZone;
	}
}
