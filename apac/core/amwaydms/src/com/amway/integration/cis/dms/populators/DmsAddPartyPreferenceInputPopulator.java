package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PartyPreferenceData;
import com.amway.integration.dms.services.CreatePartyPreferenceRequest;


/**
 * Populator implementation for {@link PartyPreferenceData} as source and {@link CreatePartyPreferenceRequest} as target
 * type.
 */
public class DmsAddPartyPreferenceInputPopulator extends AbstractDmsPopulator
		implements Populator<PartyPreferenceData, CreatePartyPreferenceRequest>
{
	@Override
	public void populate(final PartyPreferenceData source, final CreatePartyPreferenceRequest target) throws ConversionException
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
