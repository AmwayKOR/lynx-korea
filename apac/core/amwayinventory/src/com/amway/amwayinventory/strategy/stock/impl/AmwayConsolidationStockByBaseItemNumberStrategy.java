package com.amway.amwayinventory.strategy.stock.impl;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.strategy.stock.AmwayConsolidationStockStrategy;


/**
 * Implementation consolidation strategy by Base Item Number and Warehouse Code. If this attributes are equals
 * then amounts are added up
 */
public class AmwayConsolidationStockByBaseItemNumberStrategy implements AmwayConsolidationStockStrategy
{
	@Override
	public Collection<AmwayConsolidatedInventoryBean> consolidateStocks(Collection<AmwayInventoryBean> inventoryBeans)
	{
		//here we are grouping inventory beans by product code and warehouse code(equals was overridden in AmwayInventoryBean)
		// and if beans are equals - amounts are added up
		//formatter:off
		return inventoryBeans.stream()
				.map(this::toConsolidatedBean)
				.collect(Collectors.toMap(Function.identity(),Function.identity(), this::sumAmount))
				.values();
		//formatter:on
	}

	private AmwayConsolidatedInventoryBean toConsolidatedBean(AmwayInventoryBean amwayInventoryBean)
	{
		AmwayConsolidatedInventoryBean consolidatedInventoryBean = new AmwayConsolidatedInventoryBean();
		consolidatedInventoryBean.setProductCode(amwayInventoryBean.getBaseItemNumber());
		consolidatedInventoryBean.setWarehouseCode(amwayInventoryBean.getWarehouseCode());
		consolidatedInventoryBean.setAmount(amwayInventoryBean.getAvailable());
		return consolidatedInventoryBean;
	}

	private AmwayConsolidatedInventoryBean sumAmount(AmwayConsolidatedInventoryBean bean1, AmwayConsolidatedInventoryBean bean2)
	{
		AmwayConsolidatedInventoryBean consolidatedInventoryBean = new AmwayConsolidatedInventoryBean();
		consolidatedInventoryBean.setWarehouseCode(bean1.getWarehouseCode());
		consolidatedInventoryBean.setProductCode(bean1.getProductCode());
		consolidatedInventoryBean.setAmount(bean1.getAmount() + bean2.getAmount());
		return consolidatedInventoryBean;
	}
}
