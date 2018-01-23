/**
 *
 */
package com.amway.apac.facades.populators;

import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apacfacades.notification.data.AmwayApacNotificationData;
import com.amway.apacfacades.notification.data.AmwayApacNotificationSectionData;


/**
 * The Class AmwayApacNotificationSectionPopulator.
 */
public class AmwayApacNotificationSectionPopulator
		implements Populator<SearchPageData<AmwayNotificationModel>, AmwayApacNotificationSectionData>
{

	private Converter<AmwayNotificationModel, AmwayApacNotificationData> amwayApacNotificationConverter;

	/**
	 * @return the amwayApacNotificationConverter
	 */
	public Converter<AmwayNotificationModel, AmwayApacNotificationData> getAmwayApacNotificationConverter()
	{
		return amwayApacNotificationConverter;
	}

	/**
	 * @param amwayApacNotificationConverter
	 *           the amwayApacNotificationConverter to set
	 */
	public void setAmwayApacNotificationConverter(
			final Converter<AmwayNotificationModel, AmwayApacNotificationData> amwayApacNotificationConverter)
	{
		this.amwayApacNotificationConverter = amwayApacNotificationConverter;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final SearchPageData<AmwayNotificationModel> source, final AmwayApacNotificationSectionData target)
			throws ConversionException
	{
		if (null != source.getPagination())
		{
			target.setNoOfPages(source.getPagination().getNumberOfPages());
			target.setNotificationData(getAmwayApacNotificationConverter().convertAll(source.getResults()));
		}
	}


}
