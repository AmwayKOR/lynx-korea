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

import de.hybris.platform.commerceservices.address.AddressFieldType;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.commerceservices.address.data.AddressFieldErrorData;
import de.hybris.platform.commerceservices.address.data.AddressVerificationResultData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hybris.cis.api.model.CisAddress;
import com.hybris.cis.client.avs.models.AvsResult;
import com.hybris.cis.client.avs.models.CisFieldError;

import junit.framework.Assert;


/**
 * Populator implementation for {@link AvsResult} as source and {@link AddressVerificationResultData} as target type.
 */
public class CisAddressVerificationResultPopulator implements Populator<AvsResult, AddressVerificationResultData>
{
	private Converter<List<CisAddress>, List<AddressModel>> amwayDMSReverseAddressesConverter;
	private Converter<List<CisFieldError>, List<AddressFieldErrorData>> amwayDMSFieldErrorConverter;

	@Override
	public void populate(final AvsResult source, final AddressVerificationResultData target) throws ConversionException
	{
		Assert.assertNotNull("No CIS AVS result supplied", source);

		target.setDecision(AddressVerificationDecision.lookup(source.getDecision().toString().toLowerCase()));

		if (source.getDecision().toString().equalsIgnoreCase("Error"))
		{
			target.setDecision(AddressVerificationDecision.REJECT);
		}

		final List<AddressFieldErrorData> errorList = getAmwayDMSFieldErrorConverter().convert(source.getFieldErrors());

		if (target.getDecision().equals(AddressVerificationDecision.REJECT) && CollectionUtils.isEmpty(source.getFieldErrors())
				&& CollectionUtils.isEmpty(source.getSuggestedAddresses()))
		{
			target.setDecision(AddressVerificationDecision.UNKNOWN);
			final List<AddressFieldErrorData> tempList = new ArrayList<AddressFieldErrorData>();
			final AddressFieldErrorData data = new AddressFieldErrorData();
			data.setFieldType(AddressFieldType.UNKNOWN);
			tempList.add(data);
			target.setFieldErrors(tempList);
		}
		else
		{
			target.setFieldErrors(errorList);
		}
	}

	public Converter<List<CisAddress>, List<AddressModel>> getAmwayDMSReverseAddressesConverter()
	{
		return amwayDMSReverseAddressesConverter;
	}

	public void setAmwayDMSReverseAddressesConverter(
			final Converter<List<CisAddress>, List<AddressModel>> amwayDMSReverseAddressesConverter)
	{
		this.amwayDMSReverseAddressesConverter = amwayDMSReverseAddressesConverter;
	}

	public Converter<List<CisFieldError>, List<AddressFieldErrorData>> getAmwayDMSFieldErrorConverter()
	{
		return amwayDMSFieldErrorConverter;
	}

	public void setAmwayDMSFieldErrorConverter(
			final Converter<List<CisFieldError>, List<AddressFieldErrorData>> amwayDMSFieldErrorConverter)
	{
		this.amwayDMSFieldErrorConverter = amwayDMSFieldErrorConverter;
	}

}
