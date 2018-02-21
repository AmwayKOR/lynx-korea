/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.dms.data.UpdateBusinessNatureInputRequestData;
import com.amway.integration.dms.services.UpdateBusinessNatureRequest;



/**
 * Populator implementation for {@link UpdateBusinessNatureInputRequestData} as source and
 * {@link UpdateBusinessNatureRequest} as target type.
 */
public class DmsUpdateBusinessNatureInputPopulator
		implements Populator<UpdateBusinessNatureInputRequestData, UpdateBusinessNatureRequest>
{

	@Override
	public void populate(final UpdateBusinessNatureInputRequestData source, final UpdateBusinessNatureRequest target)
			throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setAccountSubTypeCd(source.getAccountSubTypeCd());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setOrderNum(source.getOrderNum());
		target.setReasonCd(source.getReasonCd());

	}
}
