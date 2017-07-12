package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.lang.StringUtils;

import com.amway.core.data.TaxDetailsData;
import com.amway.integration.dms.services.GetPartyTaxIdDetailsRequest;


/**
 * Populator implementation for {@link TaxDetailsData} as source and {@link GetPartyTaxIdDetailsRequest} as target type.
 */
public class DmsGetPartyTaxIdInputPopulator extends AbstractDmsPopulator
		implements Populator<TaxDetailsData, GetPartyTaxIdDetailsRequest>
{
	@Override
	public void populate(final TaxDetailsData source, final GetPartyTaxIdDetailsRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setPartyId(source.getPartyId());
		target.setTaxTypeCd(StringUtils.EMPTY);
		target.setSalesPlanAff(source.getSalesPlanAff());
	}
}
