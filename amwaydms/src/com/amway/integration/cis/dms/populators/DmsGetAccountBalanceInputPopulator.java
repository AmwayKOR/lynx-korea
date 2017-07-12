/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.AccountBalanceRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link AccountBalanceRequest} as target
 * type.
 */
public class DmsGetAccountBalanceInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, AccountBalanceRequest>
{
	@Override
	public void populate(final CommonRequestFieldsData source, final AccountBalanceRequest target) throws ConversionException
	{
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setAboNum(source.getAboNum());
		target.setClientCntryCd(source.getClientCntryCd());
	}
}
