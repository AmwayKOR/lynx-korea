package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PartyPreferenceData;
import com.amway.integration.dms.services.UpdatePartyPreferenceRequest;


/**
 * Populator implementation for {@link PartyPreferenceData} as source and {@link UpdatePartyPreferenceRequest} as target
 * type.
 */
public class DmsUpdatePartyPreferenceInputPopulator extends AbstractDmsPopulator
		implements Populator<PartyPreferenceData, UpdatePartyPreferenceRequest>
{
	@Override
	public void populate(final PartyPreferenceData source, final UpdatePartyPreferenceRequest target) throws ConversionException
	{
		target.setPartyId(source.getPartyId());
		target.setPreferenceId(source.getPreferenceId());
		target.setIsTrueFlg(source.getIsTrueFlag());
		target.setPreferenceNote(source.getPreferenceNote());
		target.setPreferenceListValue(source.getPreferenceValueCd());
		target.setPreferenceListId(source.getPreferenceListId());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setAboNum(source.getAboNum());
	}

}
