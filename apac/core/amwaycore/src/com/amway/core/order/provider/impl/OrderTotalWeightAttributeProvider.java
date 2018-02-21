/**
 *
 */
package com.amway.core.order.provider.impl;

import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import com.amway.core.model.AmwayDimensionDescriptorModel;
import com.amway.core.model.AmwayDimensionModel;
import com.amway.core.product.service.AmwayProductDimensionService;


/**
 * To retrieve the total weight for the order, based on Product's AttributeDimensionDescriptor
 */
public class OrderTotalWeightAttributeProvider implements DynamicAttributeHandler<Double, AbstractOrderModel>
{
	private AmwayProductDimensionService productDimensionService;

	/**
	 * to get the total weight for the order.
	 *
	 * @param abstractOrder
	 */
	@Override
	public Double get(final AbstractOrderModel abstractOrder)
	{
		double totalWeight = 0;
		for (final AbstractOrderEntryModel orderEntry : abstractOrder.getEntries())
		{
			final AmwayDimensionDescriptorModel weightDimension = getProductDimensionService()
					.getDimension(orderEntry.getProduct(), AmwayDimensionModel.WEIGHT);
			if (weightDimension != null)
			{
				totalWeight = totalWeight + (orderEntry.getQuantity().doubleValue() * weightDimension.getValue());
			}
		}

		return Double.valueOf(CoreAlgorithms.round(totalWeight, 2));
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void set(final AbstractOrderModel arg0, final Double arg1)
	{
		throw new UnsupportedOperationException("The attribute is readonly");
	}

	/**
	 * @return productDimensionService
	 */
	public AmwayProductDimensionService getProductDimensionService()
	{
		return productDimensionService;
	}

	/**
	 * @param productDimensionService teh productDimensionService to set
	 */
	public void setProductDimensionService(final AmwayProductDimensionService productDimensionService)
	{
		this.productDimensionService = productDimensionService;
	}
}
