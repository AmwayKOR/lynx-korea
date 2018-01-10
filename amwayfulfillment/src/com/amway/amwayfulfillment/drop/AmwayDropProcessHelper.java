package com.amway.amwayfulfillment.drop;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;


/**
 * Contains utility methods used in drop process.
 */
public class AmwayDropProcessHelper
{
	private AmwayDropProcessHelper() {

	}

	public static <T extends AbstractOrderModel> List<String> getOrderCodes(final List<T> orders)
	{
		// @formatter:off
		return CollectionUtils.emptyIfNull(orders).stream()
				.map(T::getCode)
				.collect(Collectors.toList());
		// @formatter:on
	}
}
