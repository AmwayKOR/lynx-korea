package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.util.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;


/**
 * Poupulator for AbstractDms
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public abstract class AbstractDmsPopulator
{
    protected static final Logger LOG = Logger.getLogger(AbstractDmsPopulator.class);
    protected static final String DMSDATEPATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";


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

    //TODO: this works for only one site configuration per app server.
    //has to be changed to pick up from basesite.
    //did not use that because basesite is not active when called from a businessprocess
    private String resolveTimeZone()
    {
        return Config.getParameter("amway.cmssite.timezone") == null ? "UTC" : Config.getParameter("amway.cmssite.timezone");
    }
}

