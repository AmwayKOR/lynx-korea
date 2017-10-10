package com.amway.apac.core.search.solrfacetsearch.providers;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


public class AmwayApacStockFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	private EnumerationService enumerationService;

	@Override
	public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue)
	{
		if (StringUtils.isNotBlank(facetValue))
		{
			return getEnumerationService().getEnumerationName(StockLevelStatus.valueOf(facetValue));
		}

		return null;
	}


	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

}
