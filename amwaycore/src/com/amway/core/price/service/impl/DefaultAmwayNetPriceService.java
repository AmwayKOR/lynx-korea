/**
 *
 */
package com.amway.core.price.service.impl;

import de.hybris.platform.catalog.constants.CatalogConstants;
import de.hybris.platform.commerceservices.price.impl.NetPriceService;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.strategies.calculation.FindPriceStrategy;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.internal.i18n.I18NConstants;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.PriceValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.enums.AmwayKitProductType;
import com.amway.core.model.AmwayKitEntryProductModel;
import com.amway.core.model.AmwayKitProductModel;
import com.amway.core.price.service.AmwayNetPriceService;

import javax.annotation.Resource;


/**
 * Default service imlementation for amway net prices.
 */
public class DefaultAmwayNetPriceService extends NetPriceService implements AmwayNetPriceService
{
	private CommonI18NService commonI18NService;
	private static final String PRICEROW = "pricerow";
	private static final String POINTVALUE = "pointValue";
	private static final String BUSINESSVOLUME = "businessVolume";
	private FindPriceStrategy findPriceStrategy;
	private final Logger LOG = Logger.getLogger(DefaultAmwayNetPriceService.class);

	@Resource
	SessionService sessionService;


	/**
	 * To get the price information about product.
	 *
	 * @param model
	 * @return List<PriceInformation>
	 */
	@Override
	public List<PriceInformation> getPriceInformationsForProduct(final ProductModel model)
	{
		final List<PriceInformation> priceInformationList = new ArrayList<PriceInformation>();
		try
		{
			if ((model instanceof AmwayKitProductModel)
					&& (AmwayKitProductType.BUNDLED.equals(((AmwayKitProductModel) model).getType())))
			{
				double priceValue = 0;
				double pointValue = 0;
				double businessVolume = 0;
				PriceRow priceRow;

				for (final AmwayKitEntryProductModel kitEntry : ((AmwayKitProductModel) model).getKitEntry())
				{
					final List<PriceInformation> priceinfoList = super.getPriceInformationsForProduct(kitEntry.getEntry());
					if (CollectionUtils.isNotEmpty(priceinfoList))
					{
						final PriceInformation priceInformation = priceinfoList.get(0);
						priceRow = (PriceRow) priceInformation.getQualifierValue(PRICEROW);
						priceValue = priceValue + (priceRow.getPrice().doubleValue() * kitEntry.getConfiguredQty().intValue());
						pointValue = pointValue
								+ ((Double) priceRow.getAttribute(POINTVALUE)).doubleValue() * kitEntry.getConfiguredQty().intValue();
						businessVolume = businessVolume
								+ ((Double) priceRow.getAttribute(BUSINESSVOLUME)).doubleValue() * kitEntry.getConfiguredQty().intValue();
					}
				}

				final PriceRow kitPriceRow = new PriceRow();
				final PriceValue kitPriceValue = new PriceValue(getCommonI18NService().getCurrentCurrency().getIsocode(),
						CoreAlgorithms.round(priceValue, 2), true);
				final Map<String, Object> qualifiers = new HashMap<>();

				qualifiers.put(POINTVALUE, Double.valueOf(CoreAlgorithms.round(pointValue, 2)));
				qualifiers.put(BUSINESSVOLUME, Double.valueOf(CoreAlgorithms.round(businessVolume, 2)));
				qualifiers.put(PRICEROW, kitPriceRow);

				priceInformationList.add(new PriceInformation(qualifiers, kitPriceValue));
			}
			else
			{

				final List<PriceInformation> infoList = super.getPriceInformationsForProduct(model);
				if (CollectionUtils.isNotEmpty(infoList))
				{
					final PriceInformation priceInformation = infoList.get(0);

					final Map<String, Object> qualifiers = new HashMap<>();
					final PriceRow priceRow = (PriceRow) priceInformation.getQualifierValue(PRICEROW);

					qualifiers.put(POINTVALUE, priceRow.getAttribute(POINTVALUE));
					qualifiers.put(BUSINESSVOLUME, priceRow.getAttribute(BUSINESSVOLUME));
					qualifiers.put(PRICEROW, priceRow);

					priceInformationList.add(new PriceInformation(qualifiers, priceInformation.getPriceValue()));
				}
			}
		}
		catch (final JaloBusinessException e)
		{
			LOG.error(e.getMessage(), e);
		}

		return priceInformationList;
	}

	/**
	 * Method to get ABOBasePrice of OrderEntry
	 *
	 * @param entry
	 * @throws CalculationException
	 */
	@Override
	public PriceValue findABOBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				sessionService.setAttribute(CatalogConstants.SESSION_CATALOG_VERSIONS,
						Collections.unmodifiableCollection(Collections.singleton(entry.getProduct().getCatalogVersion())));
				sessionService.setAttribute(Europe1Constants.PARAMS.UPG,
						UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));
				sessionService.setAttribute(I18NConstants.CURRENCY_SESSION_ATTR_KEY, entry.getOrder().getCurrency());
				try
				{
					return getBasePrice(entry);
				}
				catch (final CalculationException e)
				{
					LOG.error("Error in retrieving ABO Price for product " + entry.getProduct().getCode(), e);
				}
				return new PriceValue(entry.getOrder().getCurrency().getIsocode(), 0, entry.getOrder().getNet().booleanValue());
			}
		});
	}

	/**
	 * Method to get RetailBasePrice of OrderEntry
	 *
	 * @param entry
	 * @throws CalculationException
	 */
	@Override
	public PriceValue findRetailBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				sessionService.setAttribute(CatalogConstants.SESSION_CATALOG_VERSIONS,
						Collections.unmodifiableCollection(Collections.singleton(entry.getProduct().getCatalogVersion())));
				sessionService.setAttribute(Europe1Constants.PARAMS.UPG,
						UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.RETAIL_PRICE_GROUP));
				sessionService.setAttribute(I18NConstants.CURRENCY_SESSION_ATTR_KEY, entry.getOrder().getCurrency());
				try
				{
					return getBasePrice(entry);
				}
				catch (final CalculationException e)
				{
					LOG.error("Error in retrieving Retail Price for product " + entry.getProduct().getCode(), e);
				}
				return new PriceValue(entry.getOrder().getCurrency().getIsocode(), 0, entry.getOrder().getNet().booleanValue());
			}
		});
	}

	/**
	 * To get the base price.
	 *
	 * @param entry
	 * @return PriceValue
	 * @throws CalculationException
	 */
	public PriceValue getBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
	{
		double priceValue = 0;
		PriceRow priceRow;
		final ProductModel product = entry.getProduct();
		if ((product instanceof AmwayKitProductModel)
				&& (AmwayKitProductType.BUNDLED.equals(((AmwayKitProductModel) product).getType())))
		{
			for (final AmwayKitEntryProductModel kitEntry : ((AmwayKitProductModel) product).getKitEntry())
			{
				final List<PriceInformation> infoList = getPriceInformationsForProduct(kitEntry.getEntry());
				final PriceInformation priceInformation = infoList.get(0);
				priceRow = (PriceRow) priceInformation.getQualifierValue(PRICEROW);
				priceValue = priceValue + (priceRow.getPrice().doubleValue() * kitEntry.getConfiguredQty().intValue());
			}
			return new PriceValue(entry.getOrder().getCurrency().getIsocode(), priceValue, entry.getOrder().getNet().booleanValue());
		}
		else
		{
			return getFindPriceStrategy().findBasePrice(entry);
		}
	}

	/**
	 * @return commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return findPriceStrategy
	 */
	public FindPriceStrategy getFindPriceStrategy()
	{
		return findPriceStrategy;
	}

	/**
	 * @param findPriceStrategy
	 *           the findPriceStrategy to set
	 */
	public void setFindPriceStrategy(final FindPriceStrategy findPriceStrategy)
	{
		this.findPriceStrategy = findPriceStrategy;
	}

	public SessionService getSessionService() {
		return sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
}
