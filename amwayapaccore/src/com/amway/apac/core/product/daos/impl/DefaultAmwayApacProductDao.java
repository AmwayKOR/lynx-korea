/**
 *
 */
package com.amway.apac.core.product.daos.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.product.daos.AmwayApacProductDao;


/**
 * Default implementation for {@link AmwayApacProductDao}
 * 
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacProductDao implements AmwayApacProductDao
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


	private FlexibleSearchService flexibleSearchService;

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

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
