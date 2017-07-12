package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.los.data.BonusStatementRequestData;


/**
 * Input populator for Los bonus statement.
 */
public class LosBonusStatementInputPopulator implements Populator<BonusStatementRequestData, BonusStatementRequestData>
{
	@Override
	public void populate(final BonusStatementRequestData source, final BonusStatementRequestData target) throws ConversionException
	{
		target.setBonusPeriod(source.getBonusPeriod());
		target.setRequestingAbo(source.getRequestingAbo());
	}
}
