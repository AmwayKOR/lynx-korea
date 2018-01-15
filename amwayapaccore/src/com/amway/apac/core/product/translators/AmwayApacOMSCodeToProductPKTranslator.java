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
 * Translator to change OMS code into corresponding product.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacOMSCodeToProductPKTranslator extends AbstractValueTranslator
{
	/**
	 * Separator character to split values provided through Impex
	 */
	private static final String OMS_CODE_CATALOG_VERSION_SEPARATOR = ":";

	/**
	 * APAC product service name constant
	 */
	private static final String APAC_PRODUCT_SERVICE = "amwayApacProductService";

	/**
	 * Catalog version service name constant
	 */
	private static final String CATALOG_VERSION_SERVICE = "catalogVersionService";

	/**
	 * OMS code index constant
	 */
	private static final int OMS_CODE_STRING_OMS_CODE_INDEX = 0;

	/**
	 * Catalog name value index constant
	 */
	private static final int OMS_CODE_STRING_CATALOG_INDEX = 1;

	/**
	 * Catalog version value index constant
	 */
	private static final int OMS_CODE_STRING_VERSION_INDEX = 2;

	/**
	 * Parameter array constant to pass into param validation method
	 */
	private static final int[] omsCodeValidationParams =
	{ 3, OMS_CODE_STRING_CATALOG_INDEX, OMS_CODE_STRING_VERSION_INDEX };

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
	 * Returns product model for provided OMS code
	 */
	@Override
	public ProductModel importValue(final String omsCode, final Item arg1) throws JaloInvalidParameterException
	{
		ProductModel productCode = null;
		if (StringUtils.isNotBlank(omsCode))
		{
			final String[] splitOmsCode = omsCode.split(OMS_CODE_CATALOG_VERSION_SEPARATOR);

			CatalogVersionModel catalogVersion = null;
			if (amwayApacProductService.validateOmsCode(splitOmsCode, omsCodeValidationParams))
			{
				catalogVersion = getCatalogVersionService().getCatalogVersion(splitOmsCode[OMS_CODE_STRING_CATALOG_INDEX],
						splitOmsCode[OMS_CODE_STRING_VERSION_INDEX]);
			}

			if (null != catalogVersion)
			{
				final AmwayPaymentOptionModel amwayPaymentOptionModel = amwayApacProductService
						.getAllPaymentOptionForOmsCode(splitOmsCode[OMS_CODE_STRING_OMS_CODE_INDEX], catalogVersion);
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
