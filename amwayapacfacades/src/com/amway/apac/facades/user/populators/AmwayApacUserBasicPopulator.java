package com.amway.apac.facades.user.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * Populator to populate user data with basic values.
 *
 * @author Parvesh Goyal
 */
public class AmwayApacUserBasicPopulator implements Populator<UserModel, CustomerData>
{

	/**
	 * Populator to populate user data with basic values.
	 */
	@Override
	public void populate(final UserModel source, final CustomerData target) throws ConversionException
	{
		target.setUid(source.getUid());
		target.setName(source.getName());
	}

}
