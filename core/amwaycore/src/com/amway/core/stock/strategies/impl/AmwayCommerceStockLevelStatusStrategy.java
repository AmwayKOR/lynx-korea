package com.amway.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.stock.strategies.impl.CommerceStockLevelStatusStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.util.Config;

import java.util.Collection;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import de.hybris.platform.servicelayer.model.ModelService;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayCommerceStockLevelStatusStrategy extends CommerceStockLevelStatusStrategy
{
	private static final String AVAILABILITY_PERIOD = "amwaycore.productAvailabilityPeriod";

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelService modelService;

	@Override
	public StockLevelStatus checkStatus(final StockLevelModel stockLevel)
	{
		StockLevelStatus resultStatus = StockLevelStatus.NOLONGERAVAILABLE;
		if ((null != stockLevel) && (InStockStatus.NOLONGERAVAILABLE.equals(stockLevel.getInStockStatus())))
		{
			return resultStatus;
		}
		else
		{
			resultStatus = super.checkStatus(stockLevel);
			return resultStatus;
		}
	}

	@Override
	public StockLevelStatus checkStatus(final Collection<StockLevelModel> stockLevels)
	{

		StockLevelStatus resultStatus = StockLevelStatus.NOLONGERAVAILABLE;
		StockLevelStatus tmpStatus = StockLevelStatus.NOLONGERAVAILABLE;
		final Iterator iterator = stockLevels.iterator();
		resultStatus = super.checkStatus(stockLevels);
		if (resultStatus.equals(StockLevelStatus.INSTOCK) || resultStatus.equals(StockLevelStatus.OUTOFSTOCK))
		{
			while (iterator.hasNext())
			{
				final StockLevelModel level = (StockLevelModel) iterator.next();
				tmpStatus = this.checkStatus(level);
				if (StockLevelStatus.NOLONGERAVAILABLE == tmpStatus)
				{
					resultStatus = tmpStatus;
					final CatalogVersionModel catalogVersionModel = productService.getProductForCode(level.getProductCode())
							.getCatalogVersion();
					final ProductModel product = productService.getProductForCode(catalogVersionModel, level.getProductCode());
					if (product.getOfflineDate() == null)
					{
						final DateTime currentDate = new DateTime();
						final PeriodFormatter formatter = new PeriodFormatterBuilder().appendYears().appendSuffix("-").appendMonths()
								.appendSuffix("-").appendDays().appendSuffix(" ").appendHours().appendSuffix(":").appendMinutes()
								.appendSuffix(":").appendSecondsWithOptionalMillis().toFormatter();
						final Period period = formatter.parsePeriod(Config.getParameter(AVAILABILITY_PERIOD));
						product.setOfflineDate(currentDate.plus(period).toDate());
						modelService.save(product);
					}
					return resultStatus;
				}
			}
		}
		return resultStatus;
	}
}
