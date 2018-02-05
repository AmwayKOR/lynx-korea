package com.amway.apac.core.product.translators;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.impex.jalo.header.StandardColumnDescriptor;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.product.services.AmwayApacProductService;


/**
 * Abstract Translator for AliasCode translation.
 *
 * @author Shubham Goyal
 */
public abstract class AbstractAmwayApacProductTranslator extends AbstractValueTranslator
{

	/**
	 * APAC product service name constant
	 */
	private static final String APAC_PRODUCT_SERVICE = "amwayApacProductService";

	/**
	 * Catalog version service name constant
	 */
	private static final String CATALOG_VERSION_SERVICE = "catalogVersionService";


	/**
	 * Catalog name value index constant
	 */
	private static final int ALIAS_CODE_STRING_CATALOG_INDEX = 1;

	/**
	 * Catalog version value index constant
	 */
	private static final int ALIAS_CODE_STRING_VERSION_INDEX = 2;

	/**
	 * Parameter array constant to pass into param validation method
	 */
	private static final int[] aliasCodeValidationParams =
	{ 3, ALIAS_CODE_STRING_CATALOG_INDEX, ALIAS_CODE_STRING_VERSION_INDEX };

	private AmwayApacProductService amwayApacProductService;

	private CatalogVersionService catalogVersionService;


	@Override
	public void init(final StandardColumnDescriptor descriptor)
	{
		amwayApacProductService = (AmwayApacProductService) Registry.getApplicationContext().getBean(APAC_PRODUCT_SERVICE);
		catalogVersionService = (CatalogVersionService) Registry.getApplicationContext().getBean(CATALOG_VERSION_SERVICE);
	}

	/**
	 * Blank method stub
	 */
	@Override
	public String exportValue(final Object arg0)
	{
		// Blank method stub
		return null;
	}

	/**
	 * @return the amwayApacProductService
	 */
	public AmwayApacProductService getAmwayApacProductService()
	{
		return amwayApacProductService;
	}

	/**
	 * @param splitAliasCode
	 * @return Evaluated catalogVersion
	 */
	protected CatalogVersionModel getCatalogVersion(final String[] splitAliasCode)
	{
		CatalogVersionModel catalogVersion = null;
		if (getAmwayApacProductService().validateAliasCode(splitAliasCode, aliasCodeValidationParams))
		{
			catalogVersion = getCatalogVersionService().getCatalogVersion(splitAliasCode[ALIAS_CODE_STRING_CATALOG_INDEX],
					splitAliasCode[ALIAS_CODE_STRING_VERSION_INDEX]);
		}
		return catalogVersion;
	}

	/**
	 * @param amwayApacProductService
	 *           the amwayApacProductService to set
	 */
	@Required
	public void setAmwayApacProductService(final AmwayApacProductService amwayApacProductService)
	{
		this.amwayApacProductService = amwayApacProductService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}
}
