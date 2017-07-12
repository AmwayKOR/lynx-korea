/**
 *
 */
package com.amway.core.price.service;

import com.amway.core.model.AmwayKitEntryProductModel;
import com.amway.core.model.AmwayKitProductModel;
import de.hybris.platform.commerceservices.price.impl.NetPriceService;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.PriceValue;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;



public class DefaultAmwayNetPriceService extends NetPriceService
{
	private static Logger LOG = Logger.getLogger(DefaultAmwayNetPriceService.class);
	private CommonI18NService commonI18NService;

	@Override
	public List<PriceInformation> getPriceInformationsForProduct(final ProductModel model)
	{
		final List<PriceInformation> priceInformationList = new ArrayList<PriceInformation>();
		try
		{
			if (model instanceof AmwayKitProductModel)
			{
				double priceValue = 0;
				double pointValue = 0;
				double businessVolume = 0;
				PriceRow priceRow;

				for (final AmwayKitEntryProductModel kitEntry : ((AmwayKitProductModel) model).getKitEntry())
				{
					for (final PriceInformation priceInfo : super.getPriceInformationsForProduct(kitEntry.getEntry()))
					{
						priceRow = (PriceRow) priceInfo.getQualifierValue("pricerow");
						priceValue = priceValue + (priceRow.getPrice().doubleValue() * kitEntry.getConfiguredQty().intValue());
						pointValue =
								pointValue + ((Double) priceRow.getAttribute("pointValue")).doubleValue() * kitEntry.getConfiguredQty()
										.intValue();
						businessVolume = businessVolume + ((Double) priceRow.getAttribute("businessVolume")).doubleValue() * kitEntry
								.getConfiguredQty().intValue();
					}
				}

				final PriceRow kitPriceRow = new PriceRow();
				final PriceValue kitPriceValue = new PriceValue(getCommonI18NService().getCurrentCurrency().getIsocode(),
						CoreAlgorithms.round(priceValue, 2), true);
				final Map<String, Object> qualifiers = new HashMap<>();

				qualifiers.put("pointValue", Double.valueOf(CoreAlgorithms.round(pointValue, 2)));
				qualifiers.put("businessVolume", Double.valueOf(CoreAlgorithms.round(businessVolume, 2)));
				qualifiers.put("pricerow", kitPriceRow);

				priceInformationList.add(new PriceInformation(qualifiers, kitPriceValue));
			}
			else
			{

				final List<PriceInformation> infoList = super.getPriceInformationsForProduct(model);
				if (CollectionUtils.isNotEmpty(infoList))
				{
					final PriceInformation priceInformation = infoList.get(0);

					final Map<String, Object> qualifiers = new HashMap<>();
					final PriceRow priceRow = (PriceRow) priceInformation.getQualifierValue("pricerow");

					qualifiers.put("pointValue", priceRow.getAttribute("pointValue"));
					qualifiers.put("businessVolume", priceRow.getAttribute("businessVolume"));
					qualifiers.put("pricerow", priceRow);

					priceInformationList.add(new PriceInformation(qualifiers, priceInformation.getPriceValue()));
				}
			}
		}
		catch (final JaloBusinessException e)
		{
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return priceInformationList;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
