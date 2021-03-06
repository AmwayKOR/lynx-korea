package com.amway.apac.core.search.solrfacetsearch.providers;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * Stock facet display name provider for UI display of facet.
 *
 * @author Shubham Goyal
 */

public class AmwayApacStockFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	private EnumerationService enumerationService;

	/**
	 * Stock facet display name provider - populates the name of the {@link StockLevelStatus} enum.
	 */
	@Override
	public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue)
	{
		if (StringUtils.isNotBlank(facetValue))
		{
			return getEnumerationService().getEnumerationName(StockLevelStatus.valueOf(facetValue));
		}
		return facetValue;
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	/**
	 * @param enumerationService
	 *           the enumerationService to set
	 */
	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}



}
