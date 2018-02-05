package com.amway.apac.resourcecenter.enums;

/**
 * The Enum AmwayApacAssetsSort.
 */
public enum AmwayApacAssetsSort
{
	/** The latest date. */
	LATEST_DATE("latestDate", "Latest Date", "ORDER BY {a.date} DESC"),
	/** The tittle ascending. */
	TITTLE_ASCENDING("ATOZ", "A to Z", "ORDER BY {a.title}"),
	/** The tittle descending. */
	TITTLE_DESCENDING("ZTOA", "Z to A", "ORDER BY {a.title} DESC"),
	/** The id ascending. */
	ID_ASCENDING("ID", "A to Z", "ORDER BY {a.assetId}");

	/** The code. */
	private String code;

	/** The description. */
	private String description;

	/** The query. */
	private String query;

	/**
	 * Instantiates a new amway apac assets sort.
	 *
	 * @param code
	 *           the code
	 * @param description
	 *           the description
	 * @param query
	 *           the query
	 */
	private AmwayApacAssetsSort(final String code, final String description, final String query)
	{
		this.code = code;
		this.description = description;
		this.query = query;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery()
	{
		return query;
	}
}
