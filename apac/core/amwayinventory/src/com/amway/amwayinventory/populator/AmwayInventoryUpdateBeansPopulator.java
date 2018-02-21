package com.amway.amwayinventory.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryDeltaAdjustmentData;
import com.amway.amwayinventory.data.AmwayInventoryUpdateRequest;


/**
 * Populates collection of {@link AmwayInventoryBean} by incoming {@link AmwayInventoryUpdateRequest}
 */
public class AmwayInventoryUpdateBeansPopulator implements Populator<AmwayInventoryUpdateRequest, List<AmwayInventoryBean>>
{
	@Autowired
	private Converter<AmwayInventoryDeltaAdjustmentData, AmwayInventoryBean> amwayInventoryBeanAdjustmentConverter;

	@Override
	public void populate(AmwayInventoryUpdateRequest source, List<AmwayInventoryBean> target)
	{
		List<AmwayInventoryDeltaAdjustmentData> inventoryUpdates = source.getArray();
		if (CollectionUtils.isNotEmpty(inventoryUpdates))
		{
			//@formatter:off
			inventoryUpdates.stream()
					.map(amwayInventoryBeanAdjustmentConverter::convert)
					.peek(inventoryBean -> inventoryBean.setWarehouseCode(source.getWarehouseCode()))
					.forEach(target::add);
			//@formatter:on
		}
	}
}
