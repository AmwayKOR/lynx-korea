package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

import java.util.ArrayList;
import java.util.List;

import com.amway.facades.populators.AmwaySearchResultProductPopulator;


/**
 * Overridding {@link AmwaySearchResultProductPopulator} to populate apac specific data
 *
 * @author Parvesh Goyal
 *
 */
public class AmwayApacSearchResultProductPopulator extends AmwaySearchResultProductPopulator
{

	/**
	 * Overridding OOTB implementation to populate the media specific to apac
	 */
	@Override
	protected List<ImageData> createImageData(final SearchResultValueData source)
	{
		final List<ImageData> result = new ArrayList<ImageData>();
		addImageData(source, "listingPage", result);
		return result;
	}

}
