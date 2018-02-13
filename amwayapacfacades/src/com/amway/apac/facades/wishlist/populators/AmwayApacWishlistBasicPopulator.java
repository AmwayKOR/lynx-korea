package com.amway.apac.facades.wishlist.populators;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.SOURCE_STRING;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TARGET_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.facades.product.data.WishlistData;


/**
 * Populator to populate wishlist data with basic values.
 *
 * @author Parvesh Goyal
 */
public class AmwayApacWishlistBasicPopulator implements Populator<Wishlist2Model, WishlistData>
{
	private Converter<UserModel, CustomerData> amwayApacUserBasicConverter;

	/**
	 * Populator to populate wishlist data with basic values.
	 */
	@Override
	public void populate(final Wishlist2Model source, final WishlistData target) throws ConversionException
	{
		validateParameterNotNullStandardMessage(SOURCE_STRING, source);
		validateParameterNotNullStandardMessage(TARGET_STRING, target);

		target.setUid(source.getUid());
		target.setDescription(source.getDescription());
		target.setIsDefault(BooleanUtils.toStringTrueFalse(source.getDefault()));
		target.setName(source.getName());
		target.setIsFavorite(BooleanUtils.isTrue(source.getFavorite()));
		target.setLastUpdated(source.getModifiedtime());
		target.setCreationTime(source.getCreationtime());

		if (source.getUser() != null)
		{
			target.setUser(getAmwayApacUserBasicConverter().convert(source.getUser()));
		}
	}

	/**
	 * @return the amwayApacUserBasicConverter
	 */
	public Converter<UserModel, CustomerData> getAmwayApacUserBasicConverter()
	{
		return amwayApacUserBasicConverter;
	}

	/**
	 * @param amwayApacUserBasicConverter
	 *           the amwayApacUserBasicConverter to set
	 */
	@Required
	public void setAmwayApacUserBasicConverter(final Converter<UserModel, CustomerData> amwayApacUserBasicConverter)
	{
		this.amwayApacUserBasicConverter = amwayApacUserBasicConverter;
	}

}
