/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */

package com.amway.setup;

import de.hybris.platform.commerceservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;


/**
 * Event listener notified when the yacceleratorinitialdata has imported the sample data
 */
public class RFContentSearchSampleDataImportedEventListener extends AbstractEventListener<SampleDataImportedEvent>
{

	private RFContentSearchAddonSampleDataSystemSetup contentSearchAddonSampleDataSystemSetup;

	@Override
	protected void onEvent(final SampleDataImportedEvent event)
	{
		getContentSearchAddonSampleDataSystemSetup()
				.importSampleData("amwaycontentsolrsearchaddon", event.getContext(), event.getImportData());
	}

	/**
	 * @return the contentSearchAddonSampleDataSystemSetup
	 */
	public RFContentSearchAddonSampleDataSystemSetup getContentSearchAddonSampleDataSystemSetup()
	{
		return contentSearchAddonSampleDataSystemSetup;
	}

	/**
	 * @param contentSearchAddonSampleDataSystemSetup the contentSearchAddonSampleDataSystemSetup to set
	 */
	public void setContentSearchAddonSampleDataSystemSetup(
			final RFContentSearchAddonSampleDataSystemSetup contentSearchAddonSampleDataSystemSetup)
	{
		this.contentSearchAddonSampleDataSystemSetup = contentSearchAddonSampleDataSystemSetup;
	}


}
