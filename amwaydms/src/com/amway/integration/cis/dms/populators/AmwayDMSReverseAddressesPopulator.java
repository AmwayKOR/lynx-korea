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

package com.amway.integration.cis.dms.populators;


import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.cis.api.model.CisAddress;


/**
 * Populator implementation for {@link CisAddress} as source and {@link AddressModel} as target type.
 */
public class AmwayDMSReverseAddressesPopulator implements Populator<List<CisAddress>, List<AddressModel>>
{
	private Populator<CisAddress, AddressModel> AmwayDMSReverseAddressPopulator;
	private ModelService modelService;

	@Override
	public void populate(final List<CisAddress> source, final List<AddressModel> target) throws ConversionException
	{
		//
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected Populator<CisAddress, AddressModel> getAmwayDMSReverseAddressPopulator()
	{
		return AmwayDMSReverseAddressPopulator;
	}

	/**
	 * @param AmwayDMSReverseAddressPopulator the AmwayDMSReverseAddressPopulator to set
	 */
	@Required
	public void setAmwayDMSReverseAddressPopulator(final Populator<CisAddress, AddressModel> AmwayDMSReverseAddressPopulator)
	{
		this.AmwayDMSReverseAddressPopulator = AmwayDMSReverseAddressPopulator;
	}
}
