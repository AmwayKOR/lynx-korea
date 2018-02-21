/**
 *
 */
package com.amway.facades.populators;

import com.amway.core.enums.ProductAttributesTypeEnum;
import com.amway.core.model.AmwayDimensionModel;
import com.amway.core.model.ProductAttributeModel;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.product.data.AmwayDimensionData;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Populate product's data with Amway specific information
 *
 * @author Aliaksei Sery
 */
public class AmwayProductPopulator implements Populator<ProductModel, ProductData>
{
	private static final String CATALOG_VERSION_ONLINE = "Online";
	private static final String ROLE_TRUSTED_CLIENT = "ROLE_TRUSTED_CLIENT";

	private UserService userService;
	private Converter<AmwayDimensionModel, AmwayDimensionData> dimensionConverter;

	@Autowired
	private Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter;

	public Converter<AmwayDimensionModel, AmwayDimensionData> getDimensionConverter()
	{
		return dimensionConverter;
	}

	@Required
	public void setDimensionConverter(Converter<AmwayDimensionModel, AmwayDimensionData> dimensionConverter)
	{
		this.dimensionConverter = dimensionConverter;
	}

	/*
		 * Populate product's data with Amway specific information
		 *
		 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
		 */
	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		productData.setAlias(productModel.getAlias());
		if (productModel.getDimensions() != null)
		{
			productData.setDimensions(getDimensionConverter().convert(productModel.getDimensions()));
		}

		final List<ProductAttributeModel> productAttributes = productModel.getProductAttributes();
		if (productAttributes != null)
		{
			final HashMap info = new HashMap();
			final Iterator attributesIterator = productAttributes.iterator();

			while (attributesIterator.hasNext())
			{
				final ProductAttributeModel attribute = (ProductAttributeModel) attributesIterator.next();
				final ProductAttributesTypeEnum attributeKey = (ProductAttributesTypeEnum) attribute.getAttributeType();
				if (attributeKey.getCode().equalsIgnoreCase(ProductAttributesTypeEnum.RETAILINGINFO.getCode()) && !AmwayCustomerHelper.isABOCustomer())
				{
					if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities()
							.contains(new SimpleGrantedAuthority(ROLE_TRUSTED_CLIENT)))
					{
						continue;
					}
				}
				final String attributeValue = (String) attribute.getAttributeValue();
				info.put(attributeKey.getCode(), attributeValue);
			}

			productData.setProductAttributes(info);
		}

		final CatalogVersionModel catalogVersion = productModel.getCatalogVersion();
		if (CATALOG_VERSION_ONLINE.equalsIgnoreCase(catalogVersion.getVersion()))
		{
			productData.setOnlineSince(productModel.getOnlineDate());
		}

		List<DeliveryModeData> deliveryModeData = new ArrayList();
		if (!productModel.getDeliveryModes().isEmpty())
		{
			for (DeliveryModeModel deliveryMode : productModel.getDeliveryModes())
			{
				deliveryModeData.add(deliveryModeConverter.convert(deliveryMode));
			}
		}
		productData.setDeliveryModes(deliveryModeData);
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
