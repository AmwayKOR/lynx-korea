/**
 *
 */
package com.amway.core.stock.strategy.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;


/**
 * Compares the stock levels.
 */
public class StockLevelComparator implements Comparator<StockLevelModel>
{
	@Override
	public int compare(final StockLevelModel arg0, final StockLevelModel arg1)
	{
		if (StringUtils.isNotBlank(arg0.getVersion()) && StringUtils.isNotBlank(arg1.getVersion()))
		{
			try
			{
				return Integer.valueOf(arg0.getVersion()).compareTo(Integer.valueOf(arg1.getVersion()));
			}
			catch (final NumberFormatException nfe)
			{
				return arg0.getVersion().compareTo(arg1.getVersion());
			}
		}
		else
		{
			return Integer.valueOf(arg0.getAvailable() - arg0.getReserved())
					.compareTo(Integer.valueOf(arg1.getAvailable() - arg0.getReserved()));
		}
	}
}
