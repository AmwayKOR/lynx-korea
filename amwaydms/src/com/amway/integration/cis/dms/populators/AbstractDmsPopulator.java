package com.amway.integration.cis.dms.populators;

import de.hybris.platform.util.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;

import com.amway.integration.cis.dms.constants.AmwayDMSConstants;


/**
 * Poupulator for AbstractDms
 */
public abstract class AbstractDmsPopulator
{
	protected static final Logger LOG = Logger.getLogger(AbstractDmsPopulator.class);
	protected static final String DMSDATEPATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

	protected <T> T retrieveValue(final JAXBElement<T> element)
	{
		if (element != null)
		{
			return element.getValue();
		}
		return null;
	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
	}

	protected String formatDate(final String inputDateString, final String currentFormat, final String desiredFormat)
	{
		final SimpleDateFormat formatter = new SimpleDateFormat(desiredFormat);
		formatter.setTimeZone(TimeZone.getTimeZone(resolveTimeZone()));
		if (StringUtils.isNotEmpty(inputDateString))
		{
			Date entryDate = null;
			try
			{
				entryDate = new DateFormatManager(currentFormat).getDateFormatInstance().parse(inputDateString);
			}
			catch (final ParseException e)
			{
				LOG.error("Exception occured during parsing", e);
			}
			final String outputDateString = entryDate != null ? formatter.format(entryDate) : "";
			return outputDateString;
		}
		return "";
	}

	protected String formatInputDate(final Date inputDate, final String desiredFormat)
	{
		final SimpleDateFormat formatter = new SimpleDateFormat(desiredFormat);
		formatter.setTimeZone(TimeZone.getTimeZone(resolveTimeZone()));
		if (inputDate != null)
		{
			return formatter.format(inputDate);
		}
		return "";
	}

	//TODO: this works for only one site configuration per app server.
	//has to be changed to pick up from basesite.
	//did not use that because basesite is not active when called from a businessprocess
	private String resolveTimeZone()
	{
		return Config.getParameter("amway.cmssite.timezone") == null ? "UTC" : Config.getParameter("amway.cmssite.timezone");
	}
}
