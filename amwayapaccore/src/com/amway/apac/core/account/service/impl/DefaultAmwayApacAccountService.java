package com.amway.apac.core.account.service.impl;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountService;
import com.amway.apac.core.i18n.services.AmwayApacCommerceCommonI18NService;
import com.amway.core.account.service.impl.DefaultAmwayAccountService;
import com.amway.core.model.AmwayAccountModel;


/**
 * @author Shubham Goyal
 */
public class DefaultAmwayApacAccountService extends DefaultAmwayAccountService implements AmwayApacAccountService
{
	private GenericDao<AmwayAccountModel> amwayApacAccountDao;
	private AmwayApacCommerceCommonI18NService amwayApacCommerceCommonI18NService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayAccountModel> getAmwayAccount(final String aboId, final String affiliateCountryCode)
	{
		final CountryModel controllingAffiliate = getAmwayApacCommerceCommonI18NService().getCountryForCode(affiliateCountryCode);
		final Map<String, Object> attributes = new HashMap<>(1);
		attributes.put(AmwayAccountModel.CODE, aboId);
		attributes.put(AmwayAccountModel.CONTROLLINGAFFILIATE, controllingAffiliate);
		return getAmwayApacAccountDao().find(attributes);
	}


	/**
	 * @return the amwayApacCommerceCommonI18NService
	 */
	public AmwayApacCommerceCommonI18NService getAmwayApacCommerceCommonI18NService()
	{
		return amwayApacCommerceCommonI18NService;
	}

	/**
	 * @param amwayApacCommerceCommonI18NService
	 *           the amwayApacCommerceCommonI18NService to set
	 */
	@Required
	public void setAmwayApacCommerceCommonI18NService(final AmwayApacCommerceCommonI18NService amwayApacCommerceCommonI18NService)
	{
		this.amwayApacCommerceCommonI18NService = amwayApacCommerceCommonI18NService;
	}



	/**
	 * @return the amwayApacAccountDao
	 */
	public GenericDao<AmwayAccountModel> getAmwayApacAccountDao()
	{
		return amwayApacAccountDao;
	}



	/**
	 * @param amwayApacAccountDao
	 *           the amwayApacAccountDao to set
	 */
	@Required
	public void setAmwayApacAccountDao(final GenericDao<AmwayAccountModel> amwayApacAccountDao)
	{
		this.amwayApacAccountDao = amwayApacAccountDao;
	}
}
