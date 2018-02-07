package com.amway.apac.facades.cart.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CommerceCartMetadata;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.service.data.CommerceCartMetadataParameter;
import de.hybris.platform.commerceservices.util.CommerceCartMetadataParameterUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.facades.cart.AmwayApacCartFacade;
import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.facades.cart.impl.DefaultAmwayCartFacade;


/**
 * Default implementation for {@link AmwayApacCartFacade}
 *
 * Created by Govind on 11/27/2017.
 */
public class DefaultAmwayApacCartFacade extends DefaultAmwayCartFacade implements AmwayApacCartFacade
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CartData getSessionCartWithSortBySortCode(final AmwayApacCartSortCode sortBy)
	{
		final CartData data = getSessionCart();
		if (CollectionUtils.isNotEmpty(data.getEntries()))
		{
			final List<OrderEntryData> recentlyAddedListEntries = new ArrayList<>(data.getEntries());
			final AmwayApacCartSortCode resolvedSortCode = sortBy == null ? AmwayApacCartSortCode.LAST_ITEM_ADDED : sortBy;
			switch (resolvedSortCode)
			{
				case LAST_ITEM_ADDED:
					Collections.reverse(recentlyAddedListEntries);
					break;
				case PRICE_ASCENDING:
					recentlyAddedListEntries.sort((o1, o2) -> Double.compare(o1.getTotalPrice().getValue().doubleValue(),
							o2.getTotalPrice().getValue().doubleValue()));
					break;
				case PRICE_DESCEDNING:
					recentlyAddedListEntries.sort((o1, o2) -> Double.compare(o2.getTotalPrice().getValue().doubleValue(),
							o1.getTotalPrice().getValue().doubleValue()));
					break;
				case NAME_ASCENDING:
					recentlyAddedListEntries.sort((o1, o2) -> o1.getProduct().getName().compareTo(o2.getProduct().getName()));
					break;
				case NAME_DESCEDNING:
					recentlyAddedListEntries.sort((o1, o2) -> o2.getProduct().getName().compareTo(o1.getProduct().getName()));
					break;
				default:
					Collections.reverse(recentlyAddedListEntries);
					break;
			}
			data.setEntries(Collections.unmodifiableList(recentlyAddedListEntries));
		}
		return data;
	}

	/**
	 * @param metadata
	 *           -CommerceCartMetadata API to update the cart with additional metadata. This API populates the
	 *           {@link CommerceCartMetadataParameter} with additional
	 *           parameters[warehouseCode,deliveryMode,deliveryAddress,volumeAmwayAccount and orderType]
	 */
	@Override
	public void updateCartMetadata(final CommerceCartMetadata metadata)
	{
		validateParameterNotNullStandardMessage("metadata", metadata);

		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder()
				.name(metadata.getName()).description(metadata.getDescription()).expirationTime(metadata.getExpirationTime())
				.removeExpirationTime(metadata.isRemoveExpirationTime()).enableHooks(false).cart(getCartService().getSessionCart())
				.build();

		parameter.setDeliveryAddress(metadata.getDeliveryAddress());
		parameter.setDeliveryMode(metadata.getDeliveryMode());
		parameter.setVolumeAmwayAccount(metadata.getVolumeAmwayAccount());
		parameter.setOrderType(metadata.getOrderType());
		parameter.setWarehouseCode(metadata.getWarehouseCode());

		getCommerceCartService().updateCartMetadata(parameter);
	}
}
