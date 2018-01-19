package com.amway.apac.resourcecenter.services.impl;

import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.PAGEABLE_DATA_STRING;
import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.PRODUCT_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.resourcecenter.daos.AmwayAssetDao;
import com.amway.apac.resourcecenter.services.AmwayApacAssetService;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * Default implementation of {@link AmwayApacAssetService}.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacAssetService implements AmwayApacAssetService
{

	/** The cms site service. */
	private CMSSiteService cmsSiteService;

	/** The amway asset dao. */
	private AmwayAssetDao amwayAssetDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final String year)
	{
		validateParameterNotNullStandardMessage(PRODUCT_STRING, product);
		validateParameterNotNullStandardMessage(PAGEABLE_DATA_STRING, pageableData);

		return getAmwayAssetDao().getAssetsForProduct(product, pageableData, getCmsSiteService().getCurrentCatalogVersion(), year);
	}

	/**
	 * Gets the cms site service.
	 *
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * Sets the cms site service.
	 *
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * Gets the amway asset dao.
	 *
	 * @return the amwayAssetDao
	 */
	public AmwayAssetDao getAmwayAssetDao()
	{
		return amwayAssetDao;
	}

	/**
	 * Sets the amway asset dao.
	 *
	 * @param amwayAssetDao
	 *           the amwayAssetDao to set
	 */
	public void setAmwayAssetDao(final AmwayAssetDao amwayAssetDao)
	{
		this.amwayAssetDao = amwayAssetDao;
	}

}
