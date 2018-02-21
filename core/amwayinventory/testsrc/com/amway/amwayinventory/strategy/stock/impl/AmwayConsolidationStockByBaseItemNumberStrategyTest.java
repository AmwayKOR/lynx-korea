package com.amway.amwayinventory.strategy.stock.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.Test;

import com.amway.amwayinventory.AmwayInventoryTestConstants;
import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryBean;


@UnitTest
public class AmwayConsolidationStockByBaseItemNumberStrategyTest
{

	private AmwayConsolidationStockByBaseItemNumberStrategy consolidationStockStrategy = new AmwayConsolidationStockByBaseItemNumberStrategy();

	@Test
	public void testConsolidationStocks()
	{

		int availableP1W1First = 3;
		int availableP1W1Second = 1;
		int availableP2W1First = 3;
		int availableP2W1Second = 6;
		int availableP1W2First = 4;
		int availableP1W2Second = 1;
		int availableP2W2 = 7;

		Collection<AmwayInventoryBean> inventoryBeans = Arrays.asList(
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_1, availableP1W1First,
						AmwayInventoryTestConstants.WAREHOUSE_1),
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_2, availableP2W1First,
						AmwayInventoryTestConstants.WAREHOUSE_1),
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_1, availableP1W1Second,
						AmwayInventoryTestConstants.WAREHOUSE_1),
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_1, availableP1W2First,
						AmwayInventoryTestConstants.WAREHOUSE_2),
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_1, availableP1W2Second,
						AmwayInventoryTestConstants.WAREHOUSE_2),
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_2, availableP2W1Second,
						AmwayInventoryTestConstants.WAREHOUSE_1),
				createInventoryBean(AmwayInventoryTestConstants.PRODUCT_2, availableP2W2, AmwayInventoryTestConstants.WAREHOUSE_2));
		Collection<AmwayConsolidatedInventoryBean> amwayConsolidatedInventoryBeans = consolidationStockStrategy.consolidateStocks(
				inventoryBeans);
		assertTrue(checkUniqueBeans(amwayConsolidatedInventoryBeans));
		AmwayConsolidatedInventoryBean bean = getBeanByProductAndWarehouseCode(AmwayInventoryTestConstants.PRODUCT_1,
				AmwayInventoryTestConstants.WAREHOUSE_1, amwayConsolidatedInventoryBeans);
		assertEquals(Integer.valueOf(availableP1W1First + availableP1W1Second), bean.getAmount());
		bean = getBeanByProductAndWarehouseCode(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_2,
				amwayConsolidatedInventoryBeans);
		assertEquals(Integer.valueOf(availableP1W2First + availableP1W2Second), bean.getAmount());
		bean = getBeanByProductAndWarehouseCode(AmwayInventoryTestConstants.PRODUCT_2, AmwayInventoryTestConstants.WAREHOUSE_1,
				amwayConsolidatedInventoryBeans);
		assertEquals(Integer.valueOf(availableP2W1First + availableP2W1Second), bean.getAmount());
		bean = getBeanByProductAndWarehouseCode(AmwayInventoryTestConstants.PRODUCT_2, AmwayInventoryTestConstants.WAREHOUSE_2,
				amwayConsolidatedInventoryBeans);
		assertEquals(Integer.valueOf(availableP2W2), bean.getAmount());

	}

	private AmwayConsolidatedInventoryBean getBeanByProductAndWarehouseCode(String productCode, String warehouseCode,
			Collection<AmwayConsolidatedInventoryBean> amwayConsolidatedInventoryBeans)
	{
		return amwayConsolidatedInventoryBeans.stream().filter(
				bean -> Objects.equals(bean.getProductCode(), productCode) && Objects.equals(bean.getWarehouseCode(), warehouseCode))
				.findFirst().orElse(null);
	}

	private boolean checkUniqueBeans(Collection<AmwayConsolidatedInventoryBean> amwayConsolidatedInventoryBeans)
	{
		for (AmwayConsolidatedInventoryBean consolidatedInventoryBean : amwayConsolidatedInventoryBeans)
		{
			if (amwayConsolidatedInventoryBeans.stream().noneMatch(consolidatedInventoryBean::equals))
			{
				return false;
			}
		}
		return true;
	}

	private AmwayInventoryBean createInventoryBean(String baseItemNumber, Integer available, String warehouseCode)
	{
		AmwayInventoryBean amwayInventoryBean = new AmwayInventoryBean();
		amwayInventoryBean.setBaseItemNumber(baseItemNumber);
		amwayInventoryBean.setAvailable(available);
		amwayInventoryBean.setWarehouseCode(warehouseCode);
		return amwayInventoryBean;
	}

}
