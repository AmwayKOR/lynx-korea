/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.CreditProfileData;
import com.amway.integration.dms.services.DeleteCreditProfileDataRequest;


/**
 * Populator implementation for {@link CreditProfileData} as source and {@link DeleteCreditProfileDataRequest} as target
 * type.
 */
public class DmsDeletePartyCreditProfileInputPopulator extends AbstractDmsPopulator
		implements Populator<CreditProfileData, DeleteCreditProfileDataRequest>
{
	@Override
	public void populate(final CreditProfileData source, final DeleteCreditProfileDataRequest target) throws ConversionException
	{
		target.setCntryCd(source.getCntryCd());
		target.setPartyId(source.getPartyId());
	}
}
