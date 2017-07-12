/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.lang.StringUtils;

import com.amway.core.constants.AmwaycoreConstants.AccountTypes;
import com.amway.core.dms.data.SponsorSearchRequestData;
import com.amway.integration.dms.services.GetSponsorListRequest;


/**
 * Populator implementation for {@link SponsorSearchRequestData} as source and {@link GetSponsorListRequest} as target
 * type.
 */
public class DmsSponsorSearchInputPopulator extends AbstractDmsPopulator
		implements Populator<SponsorSearchRequestData, GetSponsorListRequest>
{

	@Override
	public void populate(final SponsorSearchRequestData source, final GetSponsorListRequest target) throws ConversionException
	{
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setPostalCd(source.getPostalCode());
		target.setCntryCd(source.getCountryCode());
		//hardcoded as per documentation provided
		target.setAccountSubTypeCd(AccountTypes.BUSINESS_OWNER);
		target.setSponsorCount(StringUtils.isNotEmpty(source.getSponsorCount()) ? source.getSponsorCount() : "3");
	}
}
