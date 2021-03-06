package com.amway.apac.core.product.translators;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

import org.apache.commons.lang.StringUtils;

import com.amway.apac.core.model.AmwayPaymentOptionModel;


/**
 * Translator to change Alias Code into corresponding product.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacAliasCodeToProductPKTranslator extends AbstractAmwayApacProductTranslator
{
	/**
	 * Separator character to split values provided through Impex
	 */
	private static final String ALIAS_CODE_CATALOG_VERSION_SEPARATOR = ":";

	/**
	 * Alias Code index constant
	 */
	private static final int ALIAS_CODE_STRING_ALIAS_CODE_INDEX = 0;


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

			final CatalogVersionModel catalogVersion = getCatalogVersion(splitAliasCode);

			if (null != catalogVersion)
			{
				final AmwayPaymentOptionModel amwayPaymentOptionModel = getAmwayApacProductService()
						.getAllPaymentOptionForAliasCode(splitAliasCode[ALIAS_CODE_STRING_ALIAS_CODE_INDEX], catalogVersion);
				if ((amwayPaymentOptionModel != null) && (amwayPaymentOptionModel.getProduct() != null))
				{
					productCode = amwayPaymentOptionModel.getProduct();
				}
			}
		}
		return productCode;
	}

}
