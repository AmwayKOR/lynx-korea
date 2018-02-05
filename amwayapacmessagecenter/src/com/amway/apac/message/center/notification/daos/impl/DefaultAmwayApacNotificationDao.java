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
import static com.amway.apac.message.center.model.AmwayNotificationUserActionModel.USER;
import static de.hybris.platform.core.model.security.PrincipalGroupModel._PRINCIPALGROUPRELATION;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.message.center.enums.AmwayAccountGroupTagType;
import com.amway.apac.message.center.enums.AmwayNotificationStatus;
import com.amway.apac.message.center.model.AmwayAccountGroupTagModel;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.NotificationSearchParamData;
import com.amway.apac.message.center.notification.daos.AmwayApacNotificationDao;


/**
 * Default implementation for {@link AmwayApacNotificationDao}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacNotificationDao extends DefaultGenericDao implements AmwayApacNotificationDao
{
	private static final String STATUSES = "statuses";
	private static final String PUBLISHED = "published";
	private static final String CLASSIFICATION = "classification";
	private static final String HYBRIS_GROUP = "hybrisGroup";
	private static final String CURRENT_DATE = "currentDate";
	private static final String SORT_BY_DESC_DATE = " ORDER BY {an.publishDate} DESC";
	private static final String SORT_BY_ASC_DATE = " ORDER BY {an.publishDate} ASC";
	private static final String CLASSIFICATION_LIST = "classificationsList";
	private static final String GROUP_LIST = "groupsList";


	/**
	 * Query to search active PUBLISHED AmwayNotifications with publishDate before and expiryDate after the current time
	 * and given baseSite, affiliate.
	 */
	private static final String AMWAY_NOTIFICATION_MAPPING_QUERY = new StringBuilder().append("SELECT {an.")
			.append(AmwayNotificationModel.PK).append("} FROM {").append(AmwayNotificationModel._TYPECODE).append(" as an ")
			.append(" JOIN ").append(_AMWAYNOTIFICATIONACCOUNTGROUPTAGREL).append(" as anagt ON {anagt.source} = {an.")
			.append(AmwayNotificationModel.PK).append("} JOIN ").append(AmwayAccountGroupTagModel._TYPECODE)
			.append(" as aagt ON {anagt.target} = {aagt.").append(AmwayAccountGroupTagModel.PK).append("}} WHERE {an.")
			.append(AmwayNotificationModel.STATUS).append("}=?").append(PUBLISHED).append(" AND {an.").append(PUBLISHDATE)
			.append("} <= ?").append(CURRENT_DATE).append(" AND {an.").append(EXPIRYDATE).append("} >= ?").append(CURRENT_DATE)
			.append(" AND {an.").append(SITE).append("} = ?").append(SITE).append(" AND {aagt.").append(AFFILIATE).append("}=?")
			.append(AFFILIATE).append(" AND {aagt.").append(ACTIVE).append("}=?").append(ACTIVE).toString();

	/** This section appends the group and classification based restriction to the query in the queryBuilder. */
	private static final String GROUP_AND_CLASSIFICATION_QUERY = new StringBuilder().append(" AND (({aagt.").append(GROUPTYPE)
			.append("} = ?").append(CLASSIFICATION).append(" AND {aagt.").append(AmwayAccountGroupTagModel.CODE).append("} IN (?")
			.append(CLASSIFICATION_LIST).append(")) OR ({aagt.").append(GROUPTYPE).append("} = ?").append(HYBRIS_GROUP)
			.append(" AND {aagt.").append(AmwayAccountGroupTagModel.CODE).append("} IN (?").append(GROUP_LIST).append(")))")
			.toString();

	/** This Section appends the restriction based on user notification action. */
	private static final String NOTIFICATION_ACTION_PART_FOR_BUILDQUERY = new StringBuilder().append(" AND ({an.")
			.append(AmwayNotificationModel.PK).append("} NOT IN ({{Select {").append(NOTIFICATION).append("} FROM {")
			.append(AmwayNotificationUserActionModel._TYPECODE).append("} WHERE {").append(AmwayNotificationUserActionModel.STATUS)
			.append("} NOT IN (?").append(STATUSES).append(") AND {").append(USER).append("} = ?").append(USER).append("  }}))")
			.toString();

	/** Query to fetch notification action using customer and notification model. */
	private static final String NOTIFICATION_ACTION_QUERY = new StringBuilder().append("Select {actn.")
			.append(AmwayNotificationUserActionModel.PK).append("} FROM {").append(AmwayNotificationUserActionModel._TYPECODE)
			.append(" as actn JOIN ").append(AmwayNotificationModel._TYPECODE).append(" as msg ON {actn.").append(NOTIFICATION)
			.append("} = {msg.").append(AmwayNotificationModel.PK).append("} JOIN ").append(CustomerModel._TYPECODE)
			.append(" as cstmr ON {actn.").append(USER).append("} = {cstmr.").append(CustomerModel.PK).append("}} WHERE {actn.")
			.append(NOTIFICATION).append("}=?").append(NOTIFICATION).append(" AND {actn.").append(USER).append("}=?").append(USER)
			.toString();

	/** Query to find user groups list of a user. */
	private static final StringBuilder USER_GROUP_QUERY = new StringBuilder().append("Select {pg.").append(PrincipalGroupModel.UID)
			.append("} FROM {").append(CustomerModel._TYPECODE).append(" as c JOIN ").append(_PRINCIPALGROUPRELATION)
			.append(" as pgr ON {pgr.source}={c.").append(CustomerModel.PK).append("} JOIN ").append(PrincipalGroupModel._TYPECODE)
			.append(" as pg ON {pgr.target}={pg.").append(PrincipalGroupModel.PK).append("}} where {c.").append(CustomerModel.PK)
			.append("} = ?").append(USER);

	private PagedFlexibleSearchService pagedFlexibleSearchService;
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayApacNotificationDao()
	{
		super(AmwayNotificationModel._TYPECODE);
	}

	@Override
	public AmwayNotificationUserActionModel getNotificationAction(final AmwayNotificationModel notification,
			final CustomerModel customer)
	{
		final Map<String, Object> params = new HashMap<>(2);
		params.put(NOTIFICATION, notification);
		params.put(USER, customer);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(NOTIFICATION_ACTION_QUERY, params);

		final SearchResult<AmwayNotificationUserActionModel> result = getFlexibleSearchService().search(query);

		return (CollectionUtils.isNotEmpty(result.getResult())) ? result.getResult().iterator().next() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayNotificationModel> getNotifications(final NotificationSearchParamData notificationSearchParam,
			final BaseSiteModel currentBaseSite)
	{
		final Map<String, Object> queryParams = new HashMap<>();
		final StringBuilder queryBuilder = new StringBuilder(AMWAY_NOTIFICATION_MAPPING_QUERY);

		buildNotificationQuery(notificationSearchParam, currentBaseSite, queryParams, queryBuilder);

		if (StringUtils.isNotEmpty(notificationSearchParam.getSearchText()))
		{
			buildSearchQuery(queryBuilder, queryParams, notificationSearchParam.getSearchText());
		}

		final List<SortQueryData> sortQueries = Arrays.asList(
				createSortQueryData("descDate", queryBuilder.append(SORT_BY_DESC_DATE).toString()),
				createSortQueryData("ascDate", queryBuilder.append(SORT_BY_ASC_DATE).toString()));

		return getPagedFlexibleSearchService().search(sortQueries, "descDate", queryParams,
				notificationSearchParam.getPageableData());
	}

	/**
	 * Changes the passed query for filtering on the basis of given customers classification level, user groups in which
	 * the user is present and the NotificationUserActionStatus.
	 *
	 * @param queryParams
	 * @param queryBuilder
	 */
	protected void buildNotificationQuery(final NotificationSearchParamData notificationSearchParam, final BaseSiteModel baseSite,
			final Map<String, Object> queryParams, final StringBuilder queryBuilder)
	{
		final Date currentDate = Calendar.getInstance().getTime();
		queryBuilder.append(GROUP_AND_CLASSIFICATION_QUERY).append(NOTIFICATION_ACTION_PART_FOR_BUILDQUERY);

		queryParams.put(STATUSES, Collections.unmodifiableList(notificationSearchParam.getNotificationStatuses()));
		queryParams.put(CURRENT_DATE, currentDate);
		queryParams.put(SITE, baseSite);
		queryParams.put(CLASSIFICATION, AmwayAccountGroupTagType.CLASSIFICATION);
		queryParams.put(HYBRIS_GROUP, AmwayAccountGroupTagType.HYBRIS_GROUP);
		queryParams.put(PUBLISHED, AmwayNotificationStatus.PUBLISHED);
		queryParams.put(ACTIVE, Boolean.TRUE);
		queryParams.put(USER, notificationSearchParam.getCurrentCustomer());
		queryParams.put(AFFILIATE, baseSite.getStores().iterator().next().getAffiliateNumber());
		queryParams.put(GROUP_LIST,
				Collections.unmodifiableList(getCurrentUserGroupNames(notificationSearchParam.getCurrentCustomer())));
		queryParams.put(CLASSIFICATION_LIST,
				Collections.unmodifiableList(getClassificationNames(notificationSearchParam.getAccountClassficationCode())));

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
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(USER, customer);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(USER_GROUP_QUERY.toString(), queryParams);
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
		Integer currentAccountRank = Integer.valueOf(0);

		if (StringUtils.isNotBlank(accountClassficationCode))
		{
			currentAccountRank = getAmwayAccountClassificationRankMapping()
					.get(AccountClassificationEnum.valueOf(accountClassficationCode));
		}
		final List<String> classificationNames = new ArrayList<>();
		for (final Entry<AccountClassificationEnum, Integer> mapEntry : getAmwayAccountClassificationRankMapping().entrySet())
		{
			if (mapEntry.getValue().intValue() <= currentAccountRank.intValue())
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
