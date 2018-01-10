/**
 *
 */
package com.amway.apac.core.resource.center.daos.Impl;

import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.amway.apac.core.account.service.AmwayApacAccountClassificationService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.model.AmwayAccountClassificationModel;
import com.amway.apac.resourcecenter.daos.Impl.DefaultAmwayApacAssetDao;
import com.amway.apac.resourcecenter.enums.AmwayApacAssetsSort;


/**
 * @author Ashish Sabal
 *
 */
public class ExtendedAmwayApacAssetDao extends DefaultAmwayApacAssetDao
{
	private static final String RANK = "rank";

	private AmwayApacAccountClassificationService amwayApacAccountClassificationService;

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
	 * @param queryParams
	 * @return
	 */
	private String generateRankQuery(final Map<String, Object> queryParams)
	{
		String rankQuery;

		final String accountClassficationCode = getSessionService()
				.getAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE);
		if (StringUtils.isNotEmpty(accountClassficationCode))
		{
			final AmwayAccountClassificationModel amwayAccountClassification = getAmwayApacAccountClassificationService()
					.getAmwayAccountClassificationByCode(accountClassficationCode);

			if (null != amwayAccountClassification)
			{
				//				queryParams.put(RANK, amwayAccountClassification.getRank());
				rankQuery = "AND (({a.classification} IN ({{SELECT {ace.pk} FROM {AccountClassificationEnum as ace JOIN AmwayAccountClassification as aac ON {ace.pk}={aac.classification}} WHERE {aac.rank}<=?rank}})) OR ({a.classification} IS NULL)) ";
			}
			else
			{
				rankQuery = "AND {a.accountClassification} IS NULL ";
			}
		}
		else
		{
			rankQuery = "AND {a.accountClassification} IS NULL ";
		}
		return rankQuery;
	}


	/**
	 * @return the amwayApacAccountClassificationService
	 */
	public AmwayApacAccountClassificationService getAmwayApacAccountClassificationService()
	{
		return amwayApacAccountClassificationService;
	}

	/**
	 * @param amwayApacAccountClassificationService
	 *           the amwayApacAccountClassificationService to set
	 */
	public void setAmwayApacAccountClassificationService(
			final AmwayApacAccountClassificationService amwayApacAccountClassificationService)
	{
		this.amwayApacAccountClassificationService = amwayApacAccountClassificationService;
	}
}
