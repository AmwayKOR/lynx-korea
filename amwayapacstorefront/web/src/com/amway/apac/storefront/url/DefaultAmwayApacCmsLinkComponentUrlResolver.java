package com.amway.apac.storefront.url;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.HUNDRED_INT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import de.hybris.platform.acceleratorstorefrontcommons.tags.Functions;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;


/**
 * Resolves the url of the {@link CMSLinkComponentModel} implementing the {@link UrlResolver} interface.
 *
 * @author Parvesh Goyal
 */
public class DefaultAmwayApacCmsLinkComponentUrlResolver implements UrlResolver<CMSLinkComponentModel>
{

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacCmsLinkComponentUrlResolver.class);

	private Converter<ProductModel, ProductData> productUrlConverter;
	private Converter<CategoryModel, CategoryData> categoryUrlConverter;

	/**
	 * Resolves the URL of the {@link CMSLinkComponentModel}.
	 *
	 * @return returns empty string if source is null or has no URL
	 * @throws IllegalArgumentException
	 *            if source is null
	 */
	@Override
	public String resolve(final CMSLinkComponentModel source)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(AmwayapacCoreConstants.SOURCE_STRING, source);

		final String url = Functions.getUrlForCMSLinkComponent(source, getProductUrlConverter(), getCategoryUrlConverter());

		if (LOGGER.isDebugEnabled())
		{
			LOGGER.debug(new StringBuilder(HUNDRED_INT).append("Url resolved for CmsLinkComponent with uid [")
					.append(source.getUid()).append("] is [").append((null != url) ? url : EMPTY).append("].").toString());
		}

		return (null != url) ? url : EMPTY;
	}

	/**
	 * @return the productUrlConverter
	 */
	public Converter<ProductModel, ProductData> getProductUrlConverter()
	{
		return productUrlConverter;
	}

	/**
	 * @param productUrlConverter
	 *           the productUrlConverter to set
	 */
	@Required
	public void setProductUrlConverter(final Converter<ProductModel, ProductData> productUrlConverter)
	{
		this.productUrlConverter = productUrlConverter;
	}

	/**
	 * @return the categoryUrlConverter
	 */
	public Converter<CategoryModel, CategoryData> getCategoryUrlConverter()
	{
		return categoryUrlConverter;
	}

	/**
	 * @param categoryUrlConverter
	 *           the categoryUrlConverter to set
	 */
	@Required
	public void setCategoryUrlConverter(final Converter<CategoryModel, CategoryData> categoryUrlConverter)
	{
		this.categoryUrlConverter = categoryUrlConverter;
	}

}
