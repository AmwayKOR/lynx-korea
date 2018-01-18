package com.amway.apac.core.account.service.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.ABO_ID;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.AFFILIATE_COUNTRY_CODE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountService;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.core.i18n.services.AmwayApacCommerceCommonI18NService;
import com.amway.apac.core.model.AmwayAccountClassificationModel;
import com.amway.core.account.service.impl.DefaultAmwayAccountService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessLevelModel;


/**
 * Default implementation for {@link AmwayApacAccountService} and extension of {@link DefaultAmwayAccountService} to add
 * functionality.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacAccountService extends DefaultAmwayAccountService implements AmwayApacAccountService
{
	private GenericDao<AmwayAccountModel> amwayApacAccountDao;
	private GenericDao<AmwayAccountClassificationModel> amwayAccountClassificationDao;
	private AmwayApacCommerceCommonI18NService amwayApacCommerceCommonI18NService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayAccountModel> getAmwayAccount(final String aboId, final String affiliateCountryCode)
	{
		validateParameterNotNullStandardMessage(ABO_ID, aboId);
		validateParameterNotNullStandardMessage(AFFILIATE_COUNTRY_CODE, affiliateCountryCode);

		final CountryModel controllingAffiliate = getAmwayApacCommerceCommonI18NService().getCountryForCode(affiliateCountryCode);
		final Map<String, Object> attributes = new HashMap<>(2);
		attributes.put(AmwayAccountModel.CODE, aboId);
		attributes.put(AmwayAccountModel.CONTROLLINGAFFILIATE, controllingAffiliate);
		return getAmwayApacAccountDao().find(attributes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AccountClassificationEnum getClassificationForAccount(final AmwayAccountModel amwayAccount)
	{
		AccountClassificationEnum accountClassification = AccountClassificationEnum.NORMAL_ABO;
		if (null != amwayAccount)
		{
			final AmwayBusinessLevelModel businessLevel = amwayAccount.getLevel();
			if ((null != businessLevel) && (null != businessLevel.getQualificationLevel()))
			{
				final Map<String, Object> attributes = new HashMap<>(1);
				attributes.put(AmwayAccountClassificationModel.QUALIFICATIONLEVEL, businessLevel.getQualificationLevel());
				final AmwayAccountClassificationModel amwayAccountClassification = getAmwayAccountClassificationDao().find(attributes)
						.iterator().next();
				if (null != amwayAccountClassification)
				{
					accountClassification = amwayAccountClassification.getClassification();
				}
			}
		}
		return accountClassification;
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

	/**
	 * @return the amwayAccountClassificationDao
	 */
	public GenericDao<AmwayAccountClassificationModel> getAmwayAccountClassificationDao()
	{
		return amwayAccountClassificationDao;
	}

	/**
	 * @param amwayAccountClassificationDao
	 *           the amwayAccountClassificationDao to set
	 */
	@Required
	public void setAmwayAccountClassificationDao(final GenericDao<AmwayAccountClassificationModel> amwayAccountClassificationDao)
	{
		this.amwayAccountClassificationDao = amwayAccountClassificationDao;
	}



}
