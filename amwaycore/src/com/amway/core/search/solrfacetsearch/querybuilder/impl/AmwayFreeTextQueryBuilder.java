package com.amway.core.search.solrfacetsearch.querybuilder.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.querybuilder.FreeTextQueryBuilder;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQuery.Operator;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.util.ClientUtils;


// no fuzzy search for text fields, full wild card search for string fields
public class AmwayFreeTextQueryBuilder implements FreeTextQueryBuilder
{
	private static final Logger LOG = Logger.getLogger(AmwayFreeTextQueryBuilder.class);

	private String propertyName;
	private int boost;

	public AmwayFreeTextQueryBuilder()
	{
	}

	public void addFreeTextQuery(final SearchQuery searchQuery, final String fullText, final String[] textWords)
	{
		final IndexedProperty indexedProperty = searchQuery.getIndexedType().getIndexedProperties().get(this.getPropertyName());
		if (indexedProperty != null)
		{
			this.addFreeTextQuery(searchQuery, indexedProperty, fullText, textWords, this.getBoost());
		}
	}

	protected void addFreeTextQuery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String fullText,
			final String[] textWords, final int boost)
	{
		this.addFreeTextQuery(searchQuery, indexedProperty, fullText, boost * 2.0D);
		if (textWords != null && textWords.length > 1)
		{
			final String[] var9 = textWords;
			final int var8 = textWords.length;

			for (int var7 = 0; var7 < var8; ++var7)
			{
				final String word = var9[var7];
				this.addFreeTextQuery(searchQuery, indexedProperty, word, boost);
			}
		}
	}

	protected void addFreeTextQuery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String value,
			final double boost)
	{
		final String field = indexedProperty.getName();
		if (!indexedProperty.isFacet())
		{
			if ("text".equalsIgnoreCase(indexedProperty.getType()))
			{
				this.addFreeTextQuery(searchQuery, field, value.toLowerCase(), "", boost);
				this.addFreeTextQuery(searchQuery, field, value.toLowerCase(), "*", boost / 2.0D);
			}
			else
			{
				this.addFreeTextQuery(searchQuery, field, value.toLowerCase(), "", boost);
				this.addWildcardTextQuery(searchQuery, field, value.toLowerCase(), "*", boost / 2.0D);
			}
		}
		else
		{
			LOG.warn("Not searching " + indexedProperty
					+ ". Free text search not available in facet property. Configure an additional text property for searching.");
		}
	}

	protected void addFreeTextQuery(final SearchQuery searchQuery, final String field, final String value, final String suffixOp,
			final double boost)
	{
		searchQuery.addQuery(field, Operator.OR, ClientUtils.escapeQueryChars(value) + suffixOp + "^" + boost);
		//		searchQuery.searchInField(field, ClientUtils.escapeQueryChars(value) + suffixOp + "^" + boost, Operator.OR);
	}

	protected void addWildcardTextQuery(final SearchQuery searchQuery, final String field, final String value,
			final String suffixOp, final double boost)
	{
		searchQuery.addQuery(field, Operator.OR, suffixOp + ClientUtils.escapeQueryChars(value) + suffixOp + "^" + boost);
		//		searchQuery.searchInField(field, suffixOp + ClientUtils.escapeQueryChars(value) + suffixOp + "^" + boost, Operator.OR);
	}

	protected String getPropertyName()
	{
		return this.propertyName;
	}

	public void setPropertyName(final String propertyName)
	{
		this.propertyName = propertyName;
	}

	protected int getBoost()
	{
		return this.boost;
	}

	public void setBoost(final int boost)
	{
		this.boost = boost;
	}
}