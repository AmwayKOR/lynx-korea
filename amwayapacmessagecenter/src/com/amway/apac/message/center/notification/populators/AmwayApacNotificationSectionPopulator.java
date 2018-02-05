/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.message.center.notification.populators;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.model.AmwayNotificationModel;


/**
 * Notification section data populator
 *
 * @author Aaron Yong
 *
 */
public class AmwayApacNotificationSectionPopulator
		implements Populator<SearchPageData<AmwayNotificationModel>, SearchPageData<AmwayApacNotificationData>>
{
	private Converter<AmwayNotificationModel, AmwayApacNotificationData> amwayApacNotificationConverter;

	/**
	 * Populates search page section data
	 */
	@Override
	public void populate(final SearchPageData<AmwayNotificationModel> source,
			final SearchPageData<AmwayApacNotificationData> target) throws ConversionException
	{
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.SOURCE_STRING, source);
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.TARGET_STRING, target);

		target.setPagination(source.getPagination());
		target.setSorts(source.getSorts());
		target.setResults(Converters.convertAll(source.getResults(), getAmwayApacNotificationConverter()));
	}

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
}
