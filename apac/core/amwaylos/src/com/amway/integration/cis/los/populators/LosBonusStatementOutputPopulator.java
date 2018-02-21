package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.io.File;

import com.amway.core.los.data.BonusStatementResultData;


/**
 * Output populator for los bonus statement.
 */
public class LosBonusStatementOutputPopulator implements Populator<File, BonusStatementResultData>
{

	@Override
	public void populate(final File source, final BonusStatementResultData target) throws ConversionException
	{
		target.setBonusStatementPDF(source);
		target.setReturnCode(new Integer(1));
		target.setReturnMessage("Bonus statement file found");

	}

}
