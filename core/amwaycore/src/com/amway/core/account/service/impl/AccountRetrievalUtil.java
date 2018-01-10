package com.amway.core.account.service.impl;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.model.AmwayAccountModel;


/**
 * Utility class.
 */
public class AccountRetrievalUtil
{

	public static AmwayProfileRequestData createAmwayProfileRequestData(
			final String serviceMode, final AmwayAccountModel unit,
			BaseStoreService baseStoreService, BaseSiteService baseSiteService)
	{
		final AmwayProfileRequestData amwayProfileRequestData = new AmwayProfileRequestData();

		amwayProfileRequestData.setSalesPlanAff(baseStoreService.getCurrentBaseStore().getAffiliateNumber());
		if (unit != null)
		{
			amwayProfileRequestData.setAboNum(unit.getCode());
			amwayProfileRequestData.setLoggedInAccountId(unit.getCode());
		}
		amwayProfileRequestData.setClientCntryCd(baseSiteService.getCurrentBaseSite().getDefaultCountry().getIsocode());
		amwayProfileRequestData.setDeltailLevelCd(serviceMode);

		return amwayProfileRequestData;
	}

	public static AmwayProfileRequestData createAmwayProfileRequestData(final String serviceMode,
			final AbstractOrderModel abstractOrderModel)
	{
		final AmwayProfileRequestData amwayProfileRequestData = new AmwayProfileRequestData();

		amwayProfileRequestData.setSalesPlanAff(abstractOrderModel.getStore().getAffiliateNumber());
		if (abstractOrderModel.getAccount() != null)
		{
			amwayProfileRequestData.setAboNum(abstractOrderModel.getAccount().getCode());
			amwayProfileRequestData.setLoggedInAccountId(abstractOrderModel.getAccount().getCode());
		}
		amwayProfileRequestData.setClientCntryCd(abstractOrderModel.getSite().getDefaultCountry().getIsocode());
		amwayProfileRequestData.setDeltailLevelCd(serviceMode);

		return amwayProfileRequestData;
	}

	public static AmwayProfileRequestData createAmwayProfileRequestData(final String serviceMode, final String aboCode,
			BaseStoreService baseStoreService, BaseSiteService baseSiteService)
	{
		final AmwayProfileRequestData amwayProfileRequestData = new AmwayProfileRequestData();

		amwayProfileRequestData.setSalesPlanAff(baseStoreService.getCurrentBaseStore().getAffiliateNumber());
		if (aboCode != null)
		{
			amwayProfileRequestData.setAboNum(aboCode);
			amwayProfileRequestData.setLoggedInAccountId(aboCode);
		}
		amwayProfileRequestData.setDeltailLevelCd(serviceMode);

		final CountryModel currentCountry = baseSiteService.getCurrentBaseSite().getDefaultCountry();
		if (currentCountry != null)
		{
			amwayProfileRequestData.setClientCntryCd(currentCountry.getIsocode());
		}
		return amwayProfileRequestData;
	}

}
