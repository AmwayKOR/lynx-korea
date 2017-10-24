package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductPrimaryImagePopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;

import org.apache.commons.collections4.CollectionUtils;


/**
 * Overridding OOTB {@link ProductPrimaryImagePopulator} to populate product image from gallery images if the picture
 * attribute is empty.
 *
 * @author Parvesh Goyal
 *
 */
public class AmwayApacProductPrimaryImagePopulator extends ProductPrimaryImagePopulator<ProductModel, ProductData>
{

	/**
	 * Overridding OOTB implementation to populate product image from gallery images if the picture attribute is empty.
	 *
	 */
	@Override
	protected MediaContainerModel getPrimaryImageMediaContainer(final ProductModel productModel)
	{
		MediaContainerModel mediaContainer = null;
		final MediaModel picture = (MediaModel) getProductAttribute(productModel, ProductModel.PICTURE);
		if (picture != null)
		{
			mediaContainer = picture.getMediaContainer();
		}
		else if (CollectionUtils.isNotEmpty(productModel.getGalleryImages()))
		{
			mediaContainer = productModel.getGalleryImages().iterator().next();
		}
		return mediaContainer;
	}
}
