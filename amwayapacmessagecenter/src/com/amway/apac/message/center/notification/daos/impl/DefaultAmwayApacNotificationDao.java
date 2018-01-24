package com.amway.apac.message.center.notification.daos.impl;

import static com.amway.apac.message.center.model.AmwayAccountGroupTagModel.ACTIVE;
import static com.amway.apac.message.center.model.AmwayAccountGroupTagModel.AFFILIATE;
import static com.amway.apac.message.center.model.AmwayAccountGroupTagModel.GROUPTYPE;
import static com.amway.apac.message.center.model.AmwayAccountGroupTagModel._AMWAYNOTIFICATIONACCOUNTGROUPTAGREL;
import static com.amway.apac.message.center.model.AmwayNotificationModel.EXPIRYDATE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.PUBLISHDATE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.SHORTDESCRIPTION;
import static com.amway.apac.message.center.model.AmwayNotificationModel.SITE;
import static com.amway.apac.message.center.model.AmwayNotificationUserActionModel.NOTIFICATION;
import static de.hybris.platform.core.model.security.PrincipalGroupModel._PRINCIPALGROUPRELATION;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.message.center.enums.AmwayAccountGroupTagType;
import com.amway.apac.message.center.enums.AmwayNotificationStatus;
import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayAccountGroupTagModel;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.daos.AmwayApacNotificationDao;


/**
 * Default implementation for {@link AmwayApacNotificationDao}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacNotificationDao implements AmwayApacNotificationDao
{
	private static final String STATUSES = "statuses";
	private static final String PUBLISHED = "published";
	private static final String CLASSIFICATION = "classification";
	private static final String HYBRIS_GROUP = "hybrisGroup";
	private static final String CURRENT_DATE = "currentDate";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE_NULL_PAGEABLE_DATA = "pageableData must not be null";
	private static final String ERROR_MESSAGE_NULL_CUSTOMER = "[customer] must not be null";
	private static final String SORT_BY_DESC_DATE = " ORDER BY {an.publishDate} DESC";
	private static final String SORT_BY_ASC_DATE = " ORDER BY {an.publishDate} ASC";
	private static final String CLASSIFICATION_LIST = "classificationsList";
	private static final String GROUP_LIST = "groupsList";


	/**
	 * Query to search active PUBLISHED AmwayNotifications with publishDate before and expiryDate after the current time and
	 * given baseSite, affiliate.
	 */
	private static final StringBuilder AMWAY_NOTIFICATION_MAPPING_QUERY = new StringBuilder().append("SELECT {an.")
			.append(AmwayNotificationModel.PK).append("} FROM {").append(AmwayNotificationModel._TYPECODE).append(" as an ")
			.append(" JOIN ").append(_AMWAYNOTIFICATIONACCOUNTGROUPTAGREL).append(" as anagt ON {anagt.source} = {an.")
			.append(AmwayNotificationModel.PK).append("} JOIN ").append(AmwayAccountGroupTagModel._TYPECODE)
			.append(" as aagt ON {anagt.target} = {aagt.").append(AmwayAccountGroupTagModel.PK).append("}} WHERE {an.")
			.append(AmwayNotificationModel.STATUS).append("}=?").append(PUBLISHED).append(" AND {an.").append(PUBLISHDATE)
			.append("} <= ?").append(CURRENT_DATE).append(" AND {an.").append(EXPIRYDATE).append("} >= ?").append(CURRENT_DATE)
			.append(" AND {an.").append(SITE).append("} = ?").append(SITE).append(" AND {aagt.").append(AFFILIATE).append("}=?")
			.append(AFFILIATE).append(" AND {aagt.").append(ACTIVE).append("}=?").append(ACTIVE);


	private PagedFlexibleSearchService pagedFlexibleSearchService;
	private FlexibleSearchService flexibleSearchService;
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayNotificationModel> getNotifications(final PageableData pageableData, final BaseSiteModel baseSite,
			final CustomerModel customer, final List<AmwayNotificationUserActionStatus> statuses, final String searchText,
			final String accountClassficationCode)
	{
		validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		validateParameterNotNull(customer, ERROR_MESSAGE_NULL_CUSTOMER);

		final Map<String, Object> queryParams = new HashMap<>();
		final StringBuilder queryBuilder = new StringBuilder(AMWAY_NOTIFICATION_MAPPING_QUERY);

		buildNotificationQuery(baseSite, customer, statuses, queryParams, queryBuilder, accountClassficationCode);

		if (StringUtils.isNotEmpty(searchText))
		{
			buildSearchQuery(queryBuilder, queryParams, searchText);
		}

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[]
		{ createSortQueryData("descDate", queryBuilder.toString() + SORT_BY_DESC_DATE),
				createSortQueryData("ascDate", queryBuilder.toString() + SORT_BY_ASC_DATE), });

		return getPagedFlexibleSearchService().search(sortQueries, "descDate", queryParams, pageableData);
	}

	/**
	 * Changes the passed query for filtering on the basis of given customers classification level, user groups in which the
	 * user is present and the NotificationUserActionStatus.
	 *
	 * @param baseSite
	 * @param customer
	 * @param statuses
	 * @param queryParams
	 * @param queryBuilder
	 */
	protected void buildNotificationQuery(final BaseSiteModel baseSite, final CustomerModel customer,
			final List<AmwayNotificationUserActionStatus> statuses, final Map<String, Object> queryParams,
			final StringBuilder queryBuilder, final String accountClassficationCode)
	{
		final Date currentDate = Calendar.getInstance().getTime();

		//This section appends the group and classification based restriction to the query in the queryBuilder.
		queryBuilder.append(" AND (({aagt.").append(GROUPTYPE).append("} = ?").append(CLASSIFICATION).append(" AND {aagt.")
				.append(AmwayAccountGroupTagModel.CODE).append("} IN (?").append(CLASSIFICATION_LIST).append(")) OR ({aagt.")
				.append(GROUPTYPE).append("} = ?").append(HYBRIS_GROUP).append(" AND {aagt.").append(AmwayAccountGroupTagModel.CODE)
				.append("} IN (?").append(GROUP_LIST).append(")))");

		//This Section appends the restriction based on user notification action.
		queryBuilder.append(" AND ({an.").append(AmwayNotificationModel.PK).append("} NOT IN ({{Select {").append(NOTIFICATION)
				.append("} FROM {").append(AmwayNotificationUserActionModel._TYPECODE).append("} WHERE {")
				.append(AmwayNotificationUserActionModel.STATUS).append("} NOT IN (?").append(STATUSES).append(") AND {").append(USER)
				.append("} = ?").append(USER).append("  }}))");

		queryParams.put(STATUSES, Collections.unmodifiableList(statuses));
		queryParams.put(CURRENT_DATE, currentDate);
		queryParams.put(SITE, baseSite);
		queryParams.put(CLASSIFICATION, AmwayAccountGroupTagType.CLASSIFICATION);
		queryParams.put(HYBRIS_GROUP, AmwayAccountGroupTagType.HYBRIS_GROUP);
		queryParams.put(PUBLISHED, AmwayNotificationStatus.PUBLISHED);
		queryParams.put(ACTIVE, Boolean.TRUE);
		queryParams.put(USER, customer);
		queryParams.put(AFFILIATE, baseSite.getStores().iterator().next().getAffiliateNumber());
		queryParams.put(GROUP_LIST, Collections.unmodifiableList(getCurrentUserGroupNames(customer)));
		queryParams.put(CLASSIFICATION_LIST, Collections.unmodifiableList(getClassificationNames(accountClassficationCode)));

	}

	/**
	 * Builds search query for the searched text.
	 *
	 * @param queryBuilder
	 * @param queryParams
	 * @param searchText
	 */
	protected void buildSearchQuery(final StringBuilder queryBuilder, final Map<String, Object> queryParams,
			final String searchText)
	{
		queryBuilder.append(" AND (LOWER({an.").append(SHORTDESCRIPTION).append("}) LIKE ?searchText");
		queryParams.put("searchText", "%" + searchText.toLowerCase() + "%");
		if (searchText.split(" ").length > 1)
		{
			int i = 0;
			for (final String text : searchText.split(" "))
			{
				queryBuilder.append(" OR LOWER({an.").append(SHORTDESCRIPTION).append("}) LIKE ?token" + i);
				queryParams.put("token" + i, "%" + text.toLowerCase() + "%");
				i++;
			}
		}
		queryBuilder.append(")");
	}

	/**
	 * Returns the list of group names in which the given customer is currently present.
	 *
	 * @param customer
	 *           customer for which groups to be found
	 * @return List of group names
	 */
	protected List<String> getCurrentUserGroupNames(final CustomerModel customer)
	{
		final StringBuilder queryBuilder = new StringBuilder().append("Select {pg.").append(PrincipalGroupModel.UID)
				.append("} FROM {").append(CustomerModel._TYPECODE).append(" as c JOIN ").append(_PRINCIPALGROUPRELATION)
				.append(" as pgr ON {pgr.source}={c.").append(CustomerModel.PK).append("} JOIN ")
				.append(PrincipalGroupModel._TYPECODE).append(" as pg ON {pgr.target}={pg.").append(PrincipalGroupModel.PK)
				.append("}} where {c.").append(CustomerModel.PK).append("} = ?").append(USER);

		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(USER, customer);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryBuilder.toString(), queryParams);
		query.setResultClassList(Collections.singletonList(String.class));
		final SearchResult<String> result = getFlexibleSearchService().search(query);
		return result.getResult();

	}

	/**
	 * Returns the list of names of all classification levels that are lower in rank than that of current session user.
	 *
	 * @return List of classification levels
	 */
	protected List<String> getClassificationNames(final String accountClassficationCode)
	{
		final Integer currentAccountRank = getAmwayAccountClassificationRankMapping()
				.get(AccountClassificationEnum.valueOf(accountClassficationCode));

		final List<String> classificationNames = new ArrayList<>();
		for (final Entry<AccountClassificationEnum, Integer> mapEntry : getAmwayAccountClassificationRankMapping().entrySet())
		{
			if (mapEntry.getValue().intValue() < currentAccountRank.intValue())
			{
				classificationNames.add(mapEntry.getKey().toString());
			}
		}
		return classificationNames;
	}

	/**
	 * Creates the sort query data.
	 *
	 * @param sortCode
	 *           the sort code
	 * @param query
	 *           the query
	 * @return the sort query data
	 */
	protected SortQueryData createSortQueryData(final String sortCode, final String query)
	{
		final SortQueryData result = new SortQueryData();
		result.setSortCode(sortCode);
		result.setQuery(query);
		return result;
	}

	/**
	 * @return the pagedFlexibleSearchService
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	/**
	 * @param pagedFlexibleSearchService
	 *           the pagedFlexibleSearchService to set
	 */
	@Required
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the amwayAccountClassificationRankMapping
	 */
	public Map<AccountClassificationEnum, Integer> getAmwayAccountClassificationRankMapping()
	{
		return amwayAccountClassificationRankMapping;
	}

	/**
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
