package com.amway.amwayordermanagementwebservices.services.impl;

import com.amway.amwayordermanagementwebservices.services.AmwayOrderSearchService;
import com.amway.amwayordermanagementwebservices.services.OrderSearchCriteria;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.SortData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.*;

import static com.amway.amwayordermanagementwebservices.services.OrderSearchCriteria.*;

/**
 * Default implementation on orders search
 */
public class DefaultAmwayOrderSearchService implements AmwayOrderSearchService {

    @Autowired
    private PagedFlexibleSearchService pagedFlexibleSearchService;

    @Autowired
    private UserService userService;

    /**
     * Default implementation of orders search by criteria
     *
     * @param criteria
     * @return
     */
    @Override
    public SearchPageData findOrders(final OrderSearchCriteria criteria) {
        final FlexibleSearchQuery query = createFlexibleSearchQuery(criteria);

        PageableData pageableData = criteria.getPageableData();
        SearchPageData pageData = pagedFlexibleSearchService.search(query, pageableData);

        if (pageData != null && pageableData != null) {
            pageData.setSorts(getSortData(pageableData.getSort()));
        }
        return pageData;
    }

    protected StringBuilder makeWhereBuilder(){
        return new StringBuilder("} WHERE {order:versionId} IS NULL AND ");
    }

    /**
     * @param criteria
     * @return
     */
    protected FlexibleSearchQuery createFlexibleSearchQuery(final OrderSearchCriteria criteria) {
        final StringBuilder fromBuilder = new StringBuilder();
        final StringBuilder whereBuilder = makeWhereBuilder();
        final Map<String, Object> queryParams = new HashMap<>();

        fromBuilder.append("SELECT {order:").append(ItemModel.PK).append("} FROM {").append(OrderModel._TYPECODE).append(" AS order ");

        Assert.isTrue(!StringUtils.isEmpty(criteria.getCode()) || !StringUtils.isEmpty(criteria.getAccount()),
                "Either code or account should be present");
        boolean isFirstParam = processOrderNum(criteria.getCode(), queryParams, whereBuilder);
        processAboNum(criteria.getAccount(), queryParams, fromBuilder, whereBuilder, isFirstParam);

        processOrderStatus(criteria.getStatus(), queryParams, fromBuilder, whereBuilder);
        processCountry(criteria.getCountry(), queryParams, fromBuilder, whereBuilder);
        processProductCode(criteria.getProductCode(), queryParams, fromBuilder, whereBuilder);
        processStartDate(criteria.getStartDate(), queryParams, whereBuilder);
        processEndDate(criteria.getEndDate(), queryParams, whereBuilder);

        PageableData pageableData = criteria.getPageableData();
        processSorting(whereBuilder, pageableData.getSort());

        fromBuilder.append(whereBuilder);
        String queryString = fromBuilder.toString();
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

        query.setNeedTotal(true);
        query.setStart(pageableData.getCurrentPage() * pageableData.getPageSize());
        query.setCount(pageableData.getPageSize());
        if (!queryParams.isEmpty()) {
            query.addQueryParameters(queryParams);
        }
        return query;
    }

    private boolean processOrderNum(final String code,
                                    final Map<String, Object> params,
                                    final StringBuilder whereBuilder) {
        if (!StringUtils.isEmpty(code)) {

            // @Formatter:off
            whereBuilder.append(checkFirstParam(true)).
                    append(" {order:").
                    append(OrderModel.CODE).
                    append("} = ?").append(ORDER_NUM);
            params.put(ORDER_NUM, code);
            // @Formatter:on

            return false;
        } else {
            return true;
        }
    }

    private void processAboNum(final String aboNum,
                               final Map<String, Object> params,
                               final StringBuilder fromBuilder,
                               final StringBuilder whereBuilder,
                               final boolean isFirstParam) {
        if (!StringUtils.isEmpty(aboNum)) {

            // @Formatter:off
            fromBuilder.append(" JOIN ").
                    append(AmwayAccountModel._TYPECODE).
                    append(" AS account ON {order.").
                    append(OrderModel.ACCOUNT).
                    append("}={account.").
                    append(AmwayAccountModel.PK).
                    append("}");
            whereBuilder.append(checkFirstParam(isFirstParam)).
                    append(" {account:").
                    append(AmwayAccountModel.CODE).
                    append("} = ?").append(ABO_NUM);
            // @Formatter:on

            params.put(ABO_NUM, aboNum);
        }
    }

    private void processOrderStatus(final String orderStatus,
                                    final Map<String, Object> params,
                                    final StringBuilder fromBuilder,
                                    final StringBuilder whereBuilder) {
        if (!StringUtils.isEmpty(orderStatus)) {

            // @Formatter:off
            fromBuilder.append(" JOIN ").
                    append(OrderStatus._TYPECODE).
                    append(" AS status ON {order.").
                    append(OrderModel.STATUS).
                    append("}={status.").
                    append(OrderModel.PK).
                    append("}");
            whereBuilder.append(" AND {status:code").
                    append("} = ?").append(ORDER_STATUS);
            // @Formatter:on

            params.put(ORDER_STATUS, orderStatus);
        }
    }

    private void processCountry(final String country,
                                final Map<String, Object> params,
                                final StringBuilder fromBuilder,
                                final StringBuilder whereBuilder) {
        if (!StringUtils.isEmpty(country)) {

            // @Formatter:off
            fromBuilder.append(" JOIN ").
                    append(BaseSiteModel._TYPECODE).
                    append(" AS site ON {order.").
                    append(OrderModel.SITE).
                    append("}={site.").
                    append(BaseSiteModel.PK).
                    append("}").
                    append(" JOIN ").
                    append(CountryModel._TYPECODE).
                    append(" AS country ON {site.").
                    append(BaseSiteModel.DEFAULTCOUNTRY).
                    append("}={country.").
                    append(CountryModel.PK).
                    append("}");
            whereBuilder.append(" AND {country:").append(CountryModel.ISOCODE).
                    append("} = ?").append(COUNTRY);
            // @Formatter:on

            params.put(COUNTRY, country);
        }
    }

    private void processProductCode(final String productCode,
                                    final Map<String, Object> params,
                                    final StringBuilder fromBuilder,
                                    final StringBuilder whereBuilder) {
        if (!StringUtils.isEmpty(productCode)) {

            // @Formatter:off
            fromBuilder.append(" JOIN ").
                    append(OrderEntryModel._TYPECODE).
                    append(" AS entry ON {order.").
                    append(OrderModel.PK).
                    append("}={entry.").
                    append(OrderEntryModel.ORDER).
                    append("}").
                    append(" JOIN ").
                    append(ProductModel._TYPECODE).
                    append(" AS product ON {entry.").
                    append(OrderEntryModel.PRODUCT).
                    append("}={product.").
                    append(ProductModel.PK).
                    append("}");
            whereBuilder.append(" AND {product:").append(ProductModel.CODE).
                    append("} = ?").append(PRODUCT_CODE);
            // @Formatter:on

            userService.setCurrentUser(userService.getAdminUser());
            params.put(PRODUCT_CODE, productCode);
        }
    }

    private void processStartDate(final Date startDate,
                                  final Map<String, Object> params,
                                  final StringBuilder whereBuilder) {
        if (startDate != null) {

            // @Formatter:off
            whereBuilder.append(" AND {order:").
                    append(OrderModel.DATE).
                    append("} >= ?").
                    append(START_DATE);
            // @Formatter:on

            params.put(START_DATE, startDate);
        }
    }

    private void processEndDate(final Date endDate,
                                final Map<String, Object> params,
                                final StringBuilder whereBuilder) {
        if (endDate != null) {

            // @Formatter:off
            whereBuilder.append(" AND {order:").
                    append(OrderModel.DATE).
                    append("} <= ?").
                    append(END_DATE);
            // @Formatter:on

            params.put(END_DATE, endDate);
        }
    }

    private void processSorting(final StringBuilder whereBuilder, final String sort) {
        // @Formatter:off
        whereBuilder.append(" ORDER BY {order:").
                append(OrderModel.DATE).
                append("} " + sort);
        // @Formatter:on
    }

    private List<SortData> getSortData(final String sort) {

        final SortData sortData = new SortData();
        sortData.setCode(sort);
        sortData.setName(OrderModel.DATE);
        sortData.setSelected(true);
        return Collections.singletonList(sortData);
    }

    private String checkFirstParam(final boolean firstParam) {
        return firstParam ? "" : " AND ";
    }
}
