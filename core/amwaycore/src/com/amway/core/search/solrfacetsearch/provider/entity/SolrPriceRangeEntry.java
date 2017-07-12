/**
 *
 */
package com.amway.core.search.solrfacetsearch.provider.entity;


import java.math.BigDecimal;


/**
 * Entity to hold one entry in the Solr price range.
 */
public class SolrPriceRangeEntry
{
	private final BigDecimal value;
	private final String currencyIso;

	/**
	 * Constructor for SolrPriceRangeEntry
	 *
	 * @param value
	 * @param currencyIso
	 */
	public SolrPriceRangeEntry(final String value, final String currencyIso)
	{
		this.value = new BigDecimal(Double.valueOf(value));
		this.currencyIso = currencyIso;
	}

	/**
	 * @return value
	 */
	public BigDecimal getValue()
	{
		return value;
	}

	/**
	 * @return currencyIso
	 */
	public String getCurrencyIso()
	{
		return currencyIso;
	}
}
