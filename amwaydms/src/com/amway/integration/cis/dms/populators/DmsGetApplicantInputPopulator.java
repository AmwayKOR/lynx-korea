package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.ApplicantRequestData;
import com.amway.integration.dms.services.AccountInput;


/**
 * Populator implementation for {@link ApplicantRequestData} as source and {@link AccountInput} as target type.
 */
public class DmsGetApplicantInputPopulator implements Populator<ApplicantRequestData, AccountInput>
{
	@Override
	public void populate(final ApplicantRequestData source, final AccountInput target) throws ConversionException
	{
		target.setClientApplicationId("HybrisWebsite");
		target.setClientCntryCd("US");
		target.setClientRoleList("ROLE_TRUSTED_CLIENT");
		target.setLoggedInPartyId("351328");
		target.setClientIpAddress("10.10.123.124");
		target.setLoggedInAccountId("790112");
		target.setAboNum("517");
		target.setSalesPlanAff("010");
	}
}
