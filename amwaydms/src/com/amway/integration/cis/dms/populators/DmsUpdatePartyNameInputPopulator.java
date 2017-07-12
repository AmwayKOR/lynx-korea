/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PartyNameDetailsRequestData;
import com.amway.integration.dms.services.NameDetailsService;
import com.amway.integration.dms.services.PartyPersonNameServiceInput;
import com.amway.integration.dms.services.UpdatePartyNameRequest;


/**
 * Populator implementation for {@link PartyNameDetailsRequestData} as source and {@link UpdatePartyNameRequest} as
 * target type.
 */
public class DmsUpdatePartyNameInputPopulator implements Populator<PartyNameDetailsRequestData, UpdatePartyNameRequest>
{

	@Override
	public void populate(final PartyNameDetailsRequestData source, final UpdatePartyNameRequest target) throws ConversionException
	{
		final PartyPersonNameServiceInput personNameServiceInput = new PartyPersonNameServiceInput();
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		personNameServiceInput.setPartyId(source.getPartyId());
		personNameServiceInput.setLanguageCd(source.getLanguageCd());
		personNameServiceInput.setPersonNameTypeCd(source.getPersonNameTypeCd());
		final NameDetailsService nameDetailsService = new NameDetailsService();
		nameDetailsService.setGivenName(source.getLocaleNameData().getGivenName());
		nameDetailsService.setFamilyName(source.getLocaleNameData().getFamilyName());
		personNameServiceInput.setLocaleName(nameDetailsService);
		target.setName(personNameServiceInput);
	}

}
