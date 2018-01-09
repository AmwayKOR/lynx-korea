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
import de.hybris.platform.variants.model.VariantProductModel;

import org.apache.commons.lang.StringUtils;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.product.services.AmwayApacProductService;


/**
 * Translator to change OMS code into corresponding base product code in the product hierarchy.
 *
 * @author Ashish Sabal
 */
public class AmwayApacOMSCodeToBaseProductCodeTranslator extends AbstractValueTranslator
{
	private static final String OMS_CODE_CATALOG_VERSION_SEPARATOR = ":";

	private static final String APAC_PRODUCT_SERVICE = "amwayApacProductService";
	private static final String CATALOG_VERSION_SERVICE = "catalogVersionService";

	private static final int OMS_CODE_STRING_OMS_CODE_INDEX = 0;
	private static final int OMS_CODE_STRING_CATALOG_INDEX = 1;
	private static final int OMS_CODE_STRING_VERSION_INDEX = 2;

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
	public String importValue(final String omsCode, final Item arg1) throws JaloInvalidParameterException
	{
		String baseProductCode = omsCode;
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
					final ProductModel productWithPaymentOption = amwayPaymentOptionModel.getProduct();

					baseProductCode = (productWithPaymentOption instanceof VariantProductModel)
							&& (((VariantProductModel) productWithPaymentOption).getBaseProduct() != null)
									? ((VariantProductModel) productWithPaymentOption).getBaseProduct().getCode()
									: productWithPaymentOption.getCode();
				}
			}
		}
		return baseProductCode;
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
