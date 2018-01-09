package com.amway.apac.core.product.daos.impl;

import static com.amway.apac.core.model.AmwayUserPromotionCountModel.PRODUCTCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.PROMOTIONCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.USERID;
import static de.hybris.platform.core.model.ItemModel.PK;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

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
	private static final String FIND_ALL_PAYMENTOPTION_FOR_OMSCODE_AND_CATALOG = new StringBuilder(200).append("SELECT {po.")
			.append(AmwayPaymentOptionModel.PK).append("} FROM {").append(AmwayPaymentOptionModel._TYPECODE).append(" as po JOIN ")
			.append(ProductModel._TYPECODE).append(" as p ON {po.").append(AmwayPaymentOptionModel.PRODUCT).append("} = {p.")
			.append(ProductModel.PK).append("} JOIN ").append(CatalogVersionModel._TYPECODE).append(" AS cv ON {p.")
			.append(ProductModel.CATALOGVERSION).append("} = {cv.").append(CatalogVersionModel.PK).append("}}  WHERE {cv.")
			.append(CatalogVersionModel.PK).append("} = ?").append(ProductModel.CATALOGVERSION).append(" AND LOWER({po.")
			.append(AmwayPaymentOptionModel.ALIASCODE).append("}) = LOWER(?").append(AmwayPaymentOptionModel.ALIASCODE).append(")")
			.toString();

	private static final String FIND_PAYMENTOPTION_FOR_OMSCODE_AND_CATALOG = new StringBuilder(200).append("SELECT {po.")
			.append(AmwayPaymentOptionModel.PK).append("} FROM {").append(AmwayPaymentOptionModel._TYPECODE).append(" as po JOIN ")
			.append(ProductModel._TYPECODE).append(" as p ON {po.").append(AmwayPaymentOptionModel.PRODUCT).append("} = {p.")
			.append(ProductModel.PK).append("} JOIN ").append(CatalogVersionModel._TYPECODE).append(" AS cv ON {p.")
			.append(ProductModel.CATALOGVERSION).append("} = {cv.").append(CatalogVersionModel.PK).append("} JOIN ")
			.append(ArticleApprovalStatus._TYPECODE).append(" as ap on {p.").append(ProductModel.APPROVALSTATUS)
			.append("} = {ap.pk} } WHERE {cv.").append(CatalogVersionModel.PK).append("} = ?").append(ProductModel.CATALOGVERSION)
			.append(" AND LOWER({po.").append(AmwayPaymentOptionModel.ALIASCODE).append("}) = LOWER(?")
			.append(AmwayPaymentOptionModel.ALIASCODE).append(") AND (({po.").append(AmwayPaymentOptionModel.STARTDATE)
			.append("} IS NULL AND {po.").append(AmwayPaymentOptionModel.ENDDATE).append("} IS NULL AND{po.")
			.append(AmwayPaymentOptionModel.ACTIVE).append("} = TRUE) OR({po.").append(AmwayPaymentOptionModel.STARTDATE)
			.append("}<=?currentDate AND {po.").append(AmwayPaymentOptionModel.ENDDATE).append("}>=?currentDate AND{po.")
			.append(AmwayPaymentOptionModel.ACTIVE).append("} = TRUE))").append(" AND {ap.code} = 'approved'").toString();

	private static final String FIND_AMWAY_PROMOTION_COUNT_BY_USER_AND_PRODUCT = new StringBuilder(200).append("SELECT {")
			.append(PK).append("} FROM {").append(AmwayUserPromotionCountModel._TYPECODE).append("} WHERE {").append(USERID)
			.append("}=?").append(USERID).append(" AND {").append(PRODUCTCODE).append("} IN (?productCodes) AND {")
			.append(PROMOTIONCODE).append("}=?").append(PROMOTIONCODE).toString();


	public DefaultAmwayApacProductDao(final String typecode)
	{
		super(typecode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPaymentOptionModel> getAllAmwayPaymentOptionFromOmsCode(final String omsCode,
			final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNull(omsCode, "No OmsCode is specified");
		validateParameterNotNull(catalogVersion, "No catalog version is specified");

		// search for products
		final Map params = new HashMap();

		params.put(AmwayPaymentOptionModel.ALIASCODE, omsCode);
		params.put(ProductModel.CATALOGVERSION, catalogVersion);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_ALL_PAYMENTOPTION_FOR_OMSCODE_AND_CATALOG);
		query.addQueryParameters(params);

		final SearchResult<AmwayPaymentOptionModel> result = getFlexibleSearchService().search(query);
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPaymentOptionModel> getAmwayPaymentOptionFromOmsCode(final String omsCode,
			final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNull(omsCode, "No OmsCode is specified");
		validateParameterNotNull(catalogVersion, "No catalog version is specified");

		// search for products
		final Map params = new HashMap();
		final Calendar calendar = Calendar.getInstance();
		final Date currentDate = calendar.getTime();
		params.put(AmwayPaymentOptionModel.ALIASCODE, omsCode);
		params.put(ProductModel.CATALOGVERSION, catalogVersion);
		params.put("currentDate", currentDate);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_PAYMENTOPTION_FOR_OMSCODE_AND_CATALOG);
		query.addQueryParameters(params);

		final SearchResult<AmwayPaymentOptionModel> result = getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayUserPromotionCountModel> getPromotionRuleCountByUserAndProduct(final String userId,
			final List<String> productCodes, final String promotionCode)
	{
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(FIND_AMWAY_PROMOTION_COUNT_BY_USER_AND_PRODUCT);
		flexibleSearchQuery.addQueryParameter(AmwayUserPromotionCountModel.USERID, userId);
		flexibleSearchQuery.addQueryParameter(AmwayUserPromotionCountModel.PROMOTIONCODE, promotionCode);
		flexibleSearchQuery.addQueryParameter("productCodes", productCodes);
		final SearchResult<AmwayUserPromotionCountModel> ruleBasedPromotionActions = getFlexibleSearchService()
				.search(flexibleSearchQuery);
		return null != ruleBasedPromotionActions ? ruleBasedPromotionActions.getResult() : Collections.emptyList();

	}
}
