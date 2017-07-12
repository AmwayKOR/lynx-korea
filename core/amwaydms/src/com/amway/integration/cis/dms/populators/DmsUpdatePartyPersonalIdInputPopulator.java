package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PersonalIdDetailsData;
import com.amway.integration.dms.services.PersonalIdDetailsInput;
import com.amway.integration.dms.services.UpdatePersonalIdRequest;


/**
 * Populator implementation for {@link PersonalIdDetailsData} as source and {@link UpdatePersonalIdRequest} as target
 * type.
 */
public class DmsUpdatePartyPersonalIdInputPopulator extends AbstractDmsPopulator
		implements Populator<PersonalIdDetailsData, UpdatePersonalIdRequest>
{
	@Override
	public void populate(final PersonalIdDetailsData source, final UpdatePersonalIdRequest target) throws ConversionException
	{
		final PersonalIdDetailsInput personalIdDetailsInput = new PersonalIdDetailsInput();
		personalIdDetailsInput.setEffectiveDate(formatDate(source.getEffectiveDate(), "ddMMyyyy", DMSDATEPATTERN));
		personalIdDetailsInput.setPartyId(source.getPartyId());
		personalIdDetailsInput.setPersonalId(source.getPersonalId());
		personalIdDetailsInput.setPersonalIdTypeCd(source.getPersonalIdTypeCd());
		target.setPersonalIdDetailsInput(personalIdDetailsInput);
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
	}

}
