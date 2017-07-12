package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.data.TaxDetailsData;
import com.amway.core.dms.data.TaxIdDetailsResponseData;
import com.amway.integration.dms.services.GetPartyTaxIdDetailsResponse;
import com.amway.integration.dms.services.TaxDetailsOutput;


/**
 * Populator implementation for {@link GetPartyTaxIdDetailsResponse} as source and {@link TaxIdDetailsResponseData} as
 * target type.
 */
public class DmsGetPartyTaxIdOutputPopulator extends AbstractDmsPopulator
		implements Populator<GetPartyTaxIdDetailsResponse, TaxIdDetailsResponseData>
{
	@Override
	public void populate(final GetPartyTaxIdDetailsResponse source, final TaxIdDetailsResponseData target)
			throws ConversionException
	{
		final List<TaxDetailsData> taxDetailsDataList = new ArrayList<TaxDetailsData>();
		for (final TaxDetailsOutput taxDetails : source.getPartyTaxDetailsList())
		{
			final TaxDetailsData taxDetailsData = new TaxDetailsData();
			taxDetailsData.setPartyId(taxDetails.getPartyId());
			taxDetailsData.setTaxId((taxDetails.getTaxId()));
			taxDetailsData.setTaxIdType((taxDetails.getTaxTypeCd()));
			taxDetailsData
					.setTaxExpirationDate(formatDate((taxDetails.getTaxTypeExpireDate()), DMSDATEPATTERN, "ddMMyyyy"));
			taxDetailsData.setCntryCd((taxDetails.getCountryTaxCd()));
			taxDetailsDataList.add(taxDetailsData);
		}
		target.setPartyTaxDetailsListData(taxDetailsDataList);
		target.setReturnCd(source.getReturnCd());
		target.setReturnMessage(source.getReturnMessage());
	}
}
