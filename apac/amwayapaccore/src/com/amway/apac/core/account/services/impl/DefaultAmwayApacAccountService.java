package com.amway.apac.core.account.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.ABO_ID;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.AFFILIATE_COUNTRY_CODE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.customer.daos.AmwayApacCustomerAccountDao;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.core.i18n.services.AmwayApacCommerceCommonI18NService;
import com.amway.apac.core.model.AmwayAccountClassificationModel;
import com.amway.core.account.service.impl.DefaultAmwayAccountService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessLevelModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


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
	private AmwayApacCustomerAccountDao amwayApacCustomerAccountDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayAccountModel getAmwayAccount(final String aboId, final String affiliateCountryCode)
	{
		validateParameterNotNullStandardMessage(ABO_ID, aboId);
		validateParameterNotNullStandardMessage(AFFILIATE_COUNTRY_CODE, affiliateCountryCode);

		final CountryModel controllingAffiliate = getAmwayApacCommerceCommonI18NService().getCountryForCode(affiliateCountryCode);
		final Map<String, Object> attributes = new HashMap<>(2);
		attributes.put(AmwayAccountModel.CODE, aboId);
		attributes.put(AmwayAccountModel.CONTROLLINGAFFILIATE, controllingAffiliate);
		final List<AmwayAccountModel> accountsList = getAmwayApacAccountDao().find(attributes);
		ServicesUtil.validateIfSingleResult(accountsList,
				"No AmwayAccount found for code" + aboId + " and affiliate code " + affiliateCountryCode,
				"More than 1 AmwayAccounts found for code " + aboId + " and affiliate code " + affiliateCountryCode);
		return accountsList.iterator().next();
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
	 * {@inheritDoc}
	 */
	@Override
	public AmwayAccountModel getAmwayAccount(final CustomerModel customerModel)
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);

		final Optional<AmwayAccountModel> amwayAccount = customerModel.getAccounts().stream().findFirst();
		if (amwayAccount.isPresent())
		{
			return amwayAccount.get();
		}
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayBusinessRestrictionModel getMOPRestriction(final AmwayAccountModel amwayAccount)
	{
		return getAmwayApacCustomerAccountDao().getMOPRestriction(amwayAccount);
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

	/**
	 * @return the amwayApacCustomerAccountDao
	 */
	public AmwayApacCustomerAccountDao getAmwayApacCustomerAccountDao()
	{
		return amwayApacCustomerAccountDao;
	}

	/**
	 * @param amwayApacCustomerAccountDao the amwayApacCustomerAccountDao to set
	 */
	@Required
	public void setAmwayApacCustomerAccountDao(final AmwayApacCustomerAccountDao amwayApacCustomerAccountDao)
	{
		this.amwayApacCustomerAccountDao = amwayApacCustomerAccountDao;
	}
}
