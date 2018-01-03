/**
 *
 */
package com.amway.apac.core.product.translators;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.header.StandardColumnDescriptor;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.product.services.AmwayApacProductService;


/**
 * Translator to change OMS code into corresponding product code.
 */
public class AmwayApacOMSCodeADACodeToProductCodeTranslator extends AbstractValueTranslator
{
	private final Logger LOG = Logger.getLogger(AmwayApacOMSCodeADACodeToProductCodeTranslator.class);

	private static final String DEFAULT_PRODUCT_CODE = "HYB0000";

	private static final String OMS_CODE_CATALOG_VERSION_SEPARATOR = ":";

	private static final String APAC_PRODUCT_SERVICE = "amwayApacProductService";
	private static final String CATALOG_VERSION_SERVICE = "catalogVersionService";

	private static final int OMS_CODE_STRING_ADA_CODE_INDEX = 0;
	private static final int OMS_CODE_STRING_OMS_CODE_INDEX = 1;
	private static final int SKUVERSION_CODE_STRING_CATALOG_INDEX = 2;
	private static final int OMS_CODE_STRING_CATALOG_INDEX = 3;
	private static final int OMS_CODE_STRING_VERSION_INDEX = 4;

	private static final int[] omsCodeValidationParams =
	{ 5, OMS_CODE_STRING_CATALOG_INDEX, OMS_CODE_STRING_VERSION_INDEX };

	private AmwayApacProductService amwayApacProductService;

	private CatalogVersionService catalogVersionService;

	@Override
	public void init(final StandardColumnDescriptor descriptor)
	{
		amwayApacProductService = (AmwayApacProductService) Registry.getApplicationContext().getBean(APAC_PRODUCT_SERVICE);
		catalogVersionService = (CatalogVersionService) Registry.getApplicationContext().getBean(CATALOG_VERSION_SERVICE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.impex.jalo.translators.AbstractValueTranslator#exportValue(java.lang.Object)
	 */
	@Override
	public String exportValue(final Object arg0) throws JaloInvalidParameterException
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.impex.jalo.translators.AbstractValueTranslator#importValue(java.lang.String,
	 * de.hybris.platform.jalo.Item)
	 */
	@Override
	public ProductModel importValue(final String omsCode, final Item arg1) throws JaloInvalidParameterException
	{
		ProductModel productModel = null;
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
				productModel = searchProductOnADACode(splitOmsCode, catalogVersion);

				if (null == productModel)
				{
					productModel = searchProductOnOMSCode(splitOmsCode, catalogVersion);
				}

				if (null == productModel)
				{
					LOG.info("Linking default product with order entry !!!!");
					productModel = amwayApacProductService.getProductForCode(catalogVersion, DEFAULT_PRODUCT_CODE);
				}
			}
		}
		return productModel;
	}

	/**
	 * @param splitOmsCode
	 * @param catalogVersion
	 * @return
	 */
	private ProductModel searchProductOnOMSCode(final String[] splitOmsCode, final CatalogVersionModel catalogVersion)
	{
		ProductModel productModel = null;
		if (StringUtils.isNotBlank(splitOmsCode[OMS_CODE_STRING_OMS_CODE_INDEX]))
		{
			try
			{
				final AmwayPaymentOptionModel amwayPaymentOptionModel = amwayApacProductService
						.getPaymentOptionForOmsCode(splitOmsCode[OMS_CODE_STRING_OMS_CODE_INDEX], catalogVersion);
				if ((amwayPaymentOptionModel != null) && (amwayPaymentOptionModel.getProduct() != null))
				{
					productModel = amwayPaymentOptionModel.getProduct();
				}
			}
			catch (final Exception exp)
			{
				LOG.error(MessageFormat.format("Product not found on OMS code : [{0}] and catalog version [{1}]",
						splitOmsCode[OMS_CODE_STRING_OMS_CODE_INDEX], splitOmsCode[OMS_CODE_STRING_CATALOG_INDEX]
								+ OMS_CODE_CATALOG_VERSION_SEPARATOR + splitOmsCode[OMS_CODE_STRING_VERSION_INDEX]));
			}
		}

		// search product on sku code
		if (StringUtils.isNotBlank(splitOmsCode[SKUVERSION_CODE_STRING_CATALOG_INDEX]))
		{
			try
			{
				final AmwayPaymentOptionModel amwayPaymentOptionModel = amwayApacProductService
						.getPaymentOptionForOmsCode(splitOmsCode[SKUVERSION_CODE_STRING_CATALOG_INDEX].trim(), catalogVersion);
				if ((amwayPaymentOptionModel != null) && (amwayPaymentOptionModel.getProduct() != null))
				{
					productModel = amwayPaymentOptionModel.getProduct();
				}
			}
			catch (final Exception exp)
			{
				LOG.error(MessageFormat.format("Product not found on SKU Version : [{0}] and catalog version [{1}]",
						splitOmsCode[OMS_CODE_STRING_OMS_CODE_INDEX], splitOmsCode[OMS_CODE_STRING_CATALOG_INDEX]
								+ OMS_CODE_CATALOG_VERSION_SEPARATOR + splitOmsCode[OMS_CODE_STRING_VERSION_INDEX]));
			}
		}
		return productModel;
	}

	/**
	 * @param splitOmsCode
	 * @param catalogVersion
	 * @return
	 */
	private ProductModel searchProductOnADACode(final String[] splitOmsCode, final CatalogVersionModel catalogVersion)
	{
		ProductModel productModel = null;
		if (StringUtils.isNotBlank(splitOmsCode[OMS_CODE_STRING_ADA_CODE_INDEX]))
		{
			try
			{
				productModel = amwayApacProductService.getProductForCode(catalogVersion,
						splitOmsCode[OMS_CODE_STRING_ADA_CODE_INDEX]);
			}
			catch (final Exception exp)
			{
				LOG.error(MessageFormat.format("Product not found on ADA code : [{0}] and catalog version [{1}]",
						splitOmsCode[OMS_CODE_STRING_ADA_CODE_INDEX], splitOmsCode[OMS_CODE_STRING_CATALOG_INDEX]
								+ OMS_CODE_CATALOG_VERSION_SEPARATOR + splitOmsCode[OMS_CODE_STRING_VERSION_INDEX]));
			}
		}
		return productModel;
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
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}
}
