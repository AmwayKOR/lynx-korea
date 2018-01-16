package com.amway.apac.core.resource.center.daos.Impl;

import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountClassificationService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.resourcecenter.daos.Impl.DefaultAmwayAssetDao;
import com.amway.apac.resourcecenter.enums.AmwayApacAssetsSort;


/**
 * Asset DAO implementation extended from {@link DefaultAmwayAssetDao} of resource center extension
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacAssetDao extends DefaultAmwayAssetDao
{

	/** Classification Rank constant. */
	private static final String RANK = "rank";

	/** Query condition part with rank and classification conditions. */
	private static final String QUERY_STRING_WITH_RANK_PARAM_AND_CLASSIFICATION_NULL = "AND ({a.rank}<=?rank OR ({a.classification} IS NULL)) ";

	/** Query condition part with classification NULL. */
	private static final String QUERY_STRING_WITH_CLASSIFICATION_NULL = "AND {a.accountClassification} IS NULL ";

	/** The amway apac account classification service. */
	private AmwayApacAccountClassificationService amwayApacAccountClassificationService;

	/** The amway account classification rank mapping. */
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/**
	 * BuildSearchQuery method of parent overridden to add classification rank in asset search query.
	 *
	 * @param queryParams
	 *           the query params
	 * @param year
	 *           the year
	 * @param typQuery
	 *           the typ query
	 * @return the list
	 */
	@Override
	protected List<SortQueryData> buildSearchQuery(final Map<String, Object> queryParams, final String year, final String typQuery)
	{
		final String yearQuery = generateYearQuery(queryParams, year);
		final String rankQuery = generateRankQuery(queryParams);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[]
		{ createSortQueryData(AmwayApacAssetsSort.LATEST_DATE.getCode(),
				typQuery + yearQuery + rankQuery + AmwayApacAssetsSort.LATEST_DATE.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.TITTLE_ASCENDING.getCode(),
						typQuery + yearQuery + rankQuery + AmwayApacAssetsSort.TITTLE_ASCENDING.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.ID_ASCENDING.getCode(),
						typQuery + yearQuery + rankQuery + AmwayApacAssetsSort.ID_ASCENDING.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.TITTLE_DESCENDING.getCode(),
						typQuery + yearQuery + rankQuery + AmwayApacAssetsSort.TITTLE_DESCENDING.getQuery()), });

		return sortQueries;
	}

	/**
	 * Generate rank query.
	 *
	 * @param queryParams
	 *           the query params
	 * @return Condition clause for account classification
	 */
	private String generateRankQuery(final Map<String, Object> queryParams)
	{
		String rankQuery;

		final String accountClassficationCode = getSessionService()
				.getAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE);
		if (StringUtils.isNotEmpty(accountClassficationCode))
		{
			queryParams.put(RANK,
					getAmwayAccountClassificationRankMapping().get(AccountClassificationEnum.valueOf(accountClassficationCode)));
			rankQuery = QUERY_STRING_WITH_RANK_PARAM_AND_CLASSIFICATION_NULL;
		}
		else
		{
			rankQuery = QUERY_STRING_WITH_CLASSIFICATION_NULL;
		}
		return rankQuery;
	}


	/**
	 * Gets the amway apac account classification service.
	 *
	 * @return the amwayApacAccountClassificationService
	 */
	public AmwayApacAccountClassificationService getAmwayApacAccountClassificationService()
	{
		return amwayApacAccountClassificationService;
	}

	/**
	 * Sets the amway apac account classification service.
	 *
	 * @param amwayApacAccountClassificationService
	 *           the amwayApacAccountClassificationService to set
	 */
	@Required
	public void setAmwayApacAccountClassificationService(
			final AmwayApacAccountClassificationService amwayApacAccountClassificationService)
	{
		this.amwayApacAccountClassificationService = amwayApacAccountClassificationService;
	}

	/**
	 * Gets the amway account classification rank mapping.
	 *
	 * @return the amwayAccountClassificationRankMapping
	 */
	public Map<AccountClassificationEnum, Integer> getAmwayAccountClassificationRankMapping()
	{
		return amwayAccountClassificationRankMapping;
	}

	/**
	 * Sets the amway account classification rank mapping.
	 *
	 * @param amwayAccountClassificationRankMapping
	 *           the amwayAccountClassificationRankMapping to set
	 */
	@Required
	public void setAmwayAccountClassificationRankMapping(
			final Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping)
	{
		this.amwayAccountClassificationRankMapping = amwayAccountClassificationRankMapping;
	}
}
