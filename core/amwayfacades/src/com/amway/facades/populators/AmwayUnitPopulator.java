package com.amway.facades.populators;

import com.amway.core.model.AmwayUnitModel;
import com.amway.facades.product.data.AmwayUnitData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayUnitPopulator implements Populator<AmwayUnitModel, AmwayUnitData>
{
	@Override
	public void populate(AmwayUnitModel amwayUnitModel, AmwayUnitData amwayUnitData) throws ConversionException
	{
		amwayUnitData.setSymbol(amwayUnitModel.getSymbol());
	}
}
