package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.TaxDetailsData;
import com.amway.integration.dms.services.UpdateTaxIdRequest;


/**
 * Populator implementation for {@link TaxDetailsData} as source and {@link UpdateTaxIdRequest} as target type.
 */
public class DmsUpdatePartyTaxIdInputPopulator extends AbstractDmsPopulator
		implements Populator<TaxDetailsData, UpdateTaxIdRequest>
{
	@Override
	public void populate(final TaxDetailsData source, final UpdateTaxIdRequest target) throws ConversionException
	{
		target.setCntryCd(source.getCntryCd());
		target.setPartyId(source.getPartyId());
		target.setTaxId(source.getTaxId());
		target.setTaxTypeCd(source.getTaxIdType());
	}

}
