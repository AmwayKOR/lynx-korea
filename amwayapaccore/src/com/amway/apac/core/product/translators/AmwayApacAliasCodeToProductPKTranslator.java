package com.amway.apac.core.product.translators;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.header.StandardColumnDescriptor;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.product.services.AmwayApacProductService;


/**
 * Translator to change Alias Code into corresponding product.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacAliasCodeToProductPKTranslator extends AbstractValueTranslator
{
	/**
	 * Separator character to split values provided through Impex
	 */
	private static final String ALIAS_CODE_CATALOG_VERSION_SEPARATOR = ":";

	/**
	 * APAC product service name constant
	 */
	private static final String APAC_PRODUCT_SERVICE = "amwayApacProductService";

	/**
	 * Catalog version service name constant
	 */
	private static final String CATALOG_VERSION_SERVICE = "catalogVersionService";

	/**
	 * Alias Code index constant
	 */
	private static final int ALIAS_CODE_STRING_OMS_CODE_INDEX = 0;

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
	public String exportValue(final Object arg0) throws JaloInvalidParameterException
	{
		// Blank method stub
		return null;
	}

	/**
	 * Returns product model for provided Alias Code
	 */
	@Override
	public ProductModel importValue(final String aliasCode, final Item arg1) throws JaloInvalidParameterException
	{
		ProductModel productCode = null;
		if (StringUtils.isNotBlank(aliasCode))
		{
			final String[] splitAliasCode = aliasCode.split(ALIAS_CODE_CATALOG_VERSION_SEPARATOR);

			CatalogVersionModel catalogVersion = null;
			if (amwayApacProductService.validateAliasCode(splitAliasCode, aliasCodeValidationParams))
			{
				catalogVersion = getCatalogVersionService().getCatalogVersion(splitAliasCode[ALIAS_CODE_STRING_CATALOG_INDEX],
						splitAliasCode[ALIAS_CODE_STRING_VERSION_INDEX]);
			}

			if (null != catalogVersion)
			{
				final AmwayPaymentOptionModel amwayPaymentOptionModel = amwayApacProductService
						.getAllPaymentOptionForAliasCode(splitAliasCode[ALIAS_CODE_STRING_OMS_CODE_INDEX], catalogVersion);
				if ((amwayPaymentOptionModel != null) && (amwayPaymentOptionModel.getProduct() != null))
				{
					productCode = amwayPaymentOptionModel.getProduct();
				}
			}
		}
		return productCode;
	}

	/**
	 * @return the amwayApacProductService
	 */
	public AmwayApacProductService getAmwayApacProductService()
	{
		return amwayApacProductService;
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
