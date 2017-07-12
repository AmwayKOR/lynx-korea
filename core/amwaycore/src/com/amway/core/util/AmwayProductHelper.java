package com.amway.core.util;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.site.BaseSiteService;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.amway.core.enums.ServiceTypeEnum;


/**
 * Implementation for AmwayProduct
 */
public class AmwayProductHelper
{
	private static final Logger LOG = Logger.getLogger(AmwayProductHelper.class);
	private static ApplicationContext context = null;
	private static BaseSiteService baseSiteService = null;

	static
	{
		context = Registry.getApplicationContext();
		baseSiteService = (BaseSiteService) context.getBean("baseSiteService");
	}

	/**
	 * To get the service variant products from base site.
	 *
	 * @param baseSiteModel
	 * @return
	 */
	public static String getSarterKitProductCodes(final BaseSiteModel baseSiteModel)
	{
		final BaseSiteModel currentBaseSiteModel = baseSiteModel != null ? baseSiteModel : baseSiteService.getCurrentBaseSite();
		if (currentBaseSiteModel != null && MapUtils.isNotEmpty(currentBaseSiteModel.getServiceVariant()))
		{
			final Map<ServiceTypeEnum, String> serviceVariant = currentBaseSiteModel.getServiceVariant();
			return serviceVariant.get(ServiceTypeEnum.MEMBERSHIP_FEE);
		}

		return StringUtils.EMPTY;
	}

	/**
	 * To check the current base site having service variant products.
	 *
	 * @param productModel
	 * @param baseSiteModel
	 * @return
	 */
	public static boolean isServiceProduct(final ProductModel productModel, final BaseSiteModel baseSiteModel)
	{
		final BaseSiteModel currentBaseSiteModel = baseSiteModel != null ? baseSiteModel : baseSiteService.getCurrentBaseSite();
		if (currentBaseSiteModel != null && MapUtils.isNotEmpty(currentBaseSiteModel.getServiceVariant()))
		{
			return currentBaseSiteModel.getServiceVariant().values().contains(productModel.getCode()) || getSarterKitProductCodes(
					baseSiteModel).contains(productModel.getCode());
		}

		return false;
	}
}
