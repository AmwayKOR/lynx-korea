package com.amway.apac.core.product.daos.impl;

import static com.amway.apac.core.model.AmwayPaymentOptionModel.ACTIVE;
import static com.amway.apac.core.model.AmwayPaymentOptionModel.ALIASCODE;
import static com.amway.apac.core.model.AmwayPaymentOptionModel.ENDDATE;
import static com.amway.apac.core.model.AmwayPaymentOptionModel.PRODUCT;
import static com.amway.apac.core.model.AmwayPaymentOptionModel.STARTDATE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.PRODUCTCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.PROMOTIONCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.STORE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.USERID;
import static de.hybris.platform.core.model.ItemModel.PK;
import static de.hybris.platform.core.model.product.ProductModel.APPROVALSTATUS;
import static de.hybris.platform.core.model.product.ProductModel.CATALOGVERSION;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;
import com.amway.apac.core.product.daos.AmwayApacProductDao;


/**
 * Default implementation for {@link AmwayApacProductDao}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacProductDao extends DefaultProductDao implements AmwayApacProductDao
{

	/** Query to fetch all payment option available for given OMS & catalog version. */
	private static final String FIND_ALL_PAYMENTOPTION_FOR_ALIAS_CODE_AND_CATALOG = new StringBuilder(200).append("SELECT {po.")
			.append(PK).append("} FROM {").append(AmwayPaymentOptionModel._TYPECODE).append(" as po JOIN ")
			.append(ProductModel._TYPECODE).append(" as p ON {po.").append(PRODUCT).append("} = {p.").append(PK).append("} JOIN ")
			.append(CatalogVersionModel._TYPECODE).append(" AS cv ON {p.").append(CATALOGVERSION).append("} = {cv.").append(PK)
			.append("}}  WHERE {cv.").append(PK).append("} = ?").append(CATALOGVERSION).append(" AND LOWER({po.").append(ALIASCODE)
			.append("}) = LOWER(?").append(ALIASCODE).append(")").toString();

	/** Query to fetch all ACTIVE payment options available for APPROVED products. */
	private static final String FIND_PAYMENTOPTION_FOR_ALIAS_CODE_AND_CATALOG = new StringBuilder(200).append("SELECT {po.")
			.append(PK).append("} FROM {").append(AmwayPaymentOptionModel._TYPECODE).append(" as po JOIN ")
			.append(ProductModel._TYPECODE).append(" as p ON {po.").append(PRODUCT).append("} = {p.").append(PK).append("} JOIN ")
			.append(CatalogVersionModel._TYPECODE).append(" AS cv ON {p.").append(CATALOGVERSION).append("} = {cv.").append(PK)
			.append("} JOIN ").append(ArticleApprovalStatus._TYPECODE).append(" as ap on {p.").append(APPROVALSTATUS)
			.append("} = {ap.pk} } WHERE {cv.").append(PK).append("} = ?").append(CATALOGVERSION).append(" AND LOWER({po.")
			.append(ALIASCODE).append("}) = LOWER(?").append(ALIASCODE).append(") AND (({po.").append(STARTDATE)
			.append("} IS NULL AND {po.").append(ENDDATE).append("} IS NULL AND{po.").append(ACTIVE).append("} = TRUE) OR({po.")
			.append(STARTDATE).append("}<=?currentDate AND {po.").append(ENDDATE).append("}>=?currentDate AND{po.").append(ACTIVE)
			.append("} = TRUE))").append(" AND {ap.code} = 'approved'").toString();


	/** Query to fetch promotion count by user and product. */
	private static final String FIND_AMWAY_PROMOTION_COUNT_BY_USER_AND_PRODUCT = new StringBuilder(200).append("SELECT {")
			.append(PK).append("} FROM {").append(AmwayUserPromotionCountModel._TYPECODE).append("} WHERE {").append(USERID)
			.append("}=?").append(USERID).append(" AND {").append(PRODUCTCODE).append("} IN (?productCodes) AND {")
			.append(PROMOTIONCODE).append("}=?").append(PROMOTIONCODE).append(" AND {").append(STORE).append("}=?").append(STORE)
			.toString();

	/** The Constant PRODUCT_CODES. */
	private static final String PRODUCT_CODES = "productCodes";

	/** The Constant CURRENT_DATE. */
	private static final String CURRENT_DATE = "currentDate";

	/**
	 * Instantiates a new default amway apac product dao.
	 *
	 * @param typecode
	 *           the typecode
	 */
	public DefaultAmwayApacProductDao(final String typecode)
	{
		super(typecode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPaymentOptionModel> getAllAmwayPaymentOptionFromAliasCode(final String aliasCode,
			final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNullStandardMessage(ALIASCODE, aliasCode);
		validateParameterNotNullStandardMessage(CATALOGVERSION, catalogVersion);

		final Map params = new HashMap();

		params.put(ALIASCODE, aliasCode);
		params.put(CATALOGVERSION, catalogVersion);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_ALL_PAYMENTOPTION_FOR_ALIAS_CODE_AND_CATALOG);
		query.addQueryParameters(params);

		final SearchResult<AmwayPaymentOptionModel> result = getFlexibleSearchService().search(query);
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPaymentOptionModel> getAmwayPaymentOptionFromAliasCode(final String aliasCode,
			final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNullStandardMessage(ALIASCODE, aliasCode);
		validateParameterNotNullStandardMessage(CATALOGVERSION, catalogVersion);

		// search for products
		final Map params = new HashMap();
		final Calendar calendar = Calendar.getInstance();
		final Date currentDate = calendar.getTime();
		params.put(ALIASCODE, aliasCode);
		params.put(CATALOGVERSION, catalogVersion);
		params.put(CURRENT_DATE, currentDate);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_PAYMENTOPTION_FOR_ALIAS_CODE_AND_CATALOG);
		query.addQueryParameters(params);

		final SearchResult<AmwayPaymentOptionModel> result = getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayUserPromotionCountModel> getPromotionRuleCountByUserAndProduct(final String userId,
			final List<String> productCodes, final String promotionCode, final BaseStoreModel store)
	{
		validateParameterNotNullStandardMessage(USERID, userId);
		validateParameterNotNullStandardMessage(PROMOTIONCODE, promotionCode);
		validateParameterNotNullStandardMessage(PRODUCT_CODES, productCodes);
		validateParameterNotNullStandardMessage(STORE, store);

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(FIND_AMWAY_PROMOTION_COUNT_BY_USER_AND_PRODUCT);
		flexibleSearchQuery.addQueryParameter(USERID, userId);
		flexibleSearchQuery.addQueryParameter(PROMOTIONCODE, promotionCode);
		flexibleSearchQuery.addQueryParameter(PRODUCT_CODES, productCodes);
		flexibleSearchQuery.addQueryParameter(STORE, store);
		final SearchResult<AmwayUserPromotionCountModel> ruleBasedPromotionActions = getFlexibleSearchService()
				.search(flexibleSearchQuery);
		return null != ruleBasedPromotionActions ? ruleBasedPromotionActions.getResult() : Collections.emptyList();

	}
}
