package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PersonalIdDetailsData;
import com.amway.integration.dms.services.AddPersonalIdRequest;
import com.amway.integration.dms.services.PersonalIdDetailsInput;


/**
 * Populator implementation for {@link PersonalIdDetailsData} as source and {@link AddPersonalIdRequest} as target type.
 */
public class DmsAddPartyPersonalIdInputPopulator extends AbstractDmsPopulator
		implements Populator<PersonalIdDetailsData, AddPersonalIdRequest>
{
	@Override
	public void populate(final PersonalIdDetailsData source, final AddPersonalIdRequest target) throws ConversionException
	{
		final PersonalIdDetailsInput partyPersonalIdData = new PersonalIdDetailsInput();
		partyPersonalIdData.setEffectiveDate(formatDate(source.getEffectiveDate(), "ddMMyyyy", DMSDATEPATTERN));
		partyPersonalIdData.setPartyId(source.getPartyId());
		partyPersonalIdData.setPersonalId(source.getPersonalId());
		partyPersonalIdData.setPersonalIdTypeCd(source.getPersonalIdTypeCd());
		target.getPersonalIdDetailsInput().add(partyPersonalIdData);
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
	}

}
