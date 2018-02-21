package com.amway.core.service.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.internal.i18n.I18NConstants;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Required;

import com.amway.core.account.dao.AmwayAccountDao;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.bonusperiod.services.AmwayBonusPeriodService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.constants.AmwaycoreConstants.AccountTypes;
import com.amway.core.constants.AmwaycoreConstants.PriceGroups;
import com.amway.core.constants.AmwaycoreConstants.SegmentationLevelTypes;
import com.amway.core.constants.AmwaycoreConstants.SessionVariables;
import com.amway.core.data.AccountMasterDetailsData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.BlockPrivDetailsData;
import com.amway.core.dms.data.LocaleNameData;
import com.amway.core.dms.data.PartyDetailsData;
import com.amway.core.enums.AmwayAccountStatus;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.enums.QualificationLevelTypeEnum;
import com.amway.core.enums.SegmentationLevelTypeEnum;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.core.los.data.BonusStatementResultData;
import com.amway.core.los.data.CommonLosResponseData;
import com.amway.core.los.data.LosAccountData;
import com.amway.core.los.data.LosAccountDetailDownlinesResponse;
import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountDownlinesData;
import com.amway.core.los.data.LosAccountDownlinesResponse;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.los.data.TransactionData;
import com.amway.core.los.service.LosService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessLevelModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.core.util.AmwayDateHelper;
import com.amway.core.util.AmwayPeriodHelper;
import com.google.common.collect.Lists;


/**
 * Default Implementation
 */
public class DefaultAmwayAccountCommerceService implements AmwayAccountCommerceService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayAccountCommerceService.class);

	private final String LEADERSHIPLEVEL = "QualificationLevelTypeEnum_";

	private AmwayAccountService amwayAccountService;
	private ConfigurationService configurationService;
	private SessionService sessionService;
	private UserService userService;
	private ModelService modelService;
	private CartService cartService;
	private CalculationService calculationService;
	private CMSSiteService cmsSiteService;
	private Converter<AddressData, AddressModel> addressReverseConverter;
	private LosService<LosAccountRequestData, LosAccountDetailResponseData> losAccountDetailService;
	private LosService<LosAccountRequestData, LosAccountResponseData> losAccountService;
	private LosService<BonusStatementRequestData, BonusStatementResultData> losBonusStatementService;
	private BaseStoreService baseStoreService;
	private EnumerationService enumerationService;
	private LosService<BonusOrderRequestData, BonusOrderResultData> bonusAdjustmentService;
	private AmwayBonusPeriodService amwayBonusPeriodService;
	private CommercePriceService commercePriceService;
	@Resource
	private ProductService productService;
	@Resource
	private WarehouseService warehouseService;
	@Resource
	private PriceService priceService;
	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private BaseSiteService baseSiteService;

	private AmwayAccountDao amwayAccountDao;

	/**
	 * Set the account in session with the given uid.
	 * <p/>
	 * {@link #setCurrentAccount(java.lang.String)}
	 */
	@Override
	public void setCurrentAccount(final String uid)
	{
		//condition for the user which belongs to the multiple unit
		if (StringUtils.isNotEmpty(uid))
		{
			final AmwayAccountModel accountForUid = getAmwayAccountService().findAccount(uid);
			LOG.debug("setting the account : " + accountForUid.getCode()
					+ "in session for customer which belongs to the multiple accounts");
			getSessionService().getCurrentSession().setAttribute(SessionVariables.ACCOUNT, accountForUid);
			setUPGInSession(accountForUid, null);
		}
		//condition for user which belongs to the single unit
		else
		{
			setCurrentAccount(getUserService().getCurrentUser());
		}
	}

	/**
	 * Set the account in session for the given customer.
	 * <p/>
	 * {@link #setCurrentAccount(de.hybris.platform.core.model.user.UserModel)}
	 */
	@Override
	public void setCurrentAccount(final UserModel user)
	{
		if (user instanceof CustomerModel)
		{
			try
			{
				final Set<AmwayAccountModel> accounts = ((CustomerModel) user).getAccounts();
				if (CollectionUtils.isNotEmpty(accounts))
				{
					final AmwayAccountModel account = accounts.iterator().next();
					getSessionService().getCurrentSession().setAttribute(SessionVariables.ACCOUNT, account);
					setUPGInSession(account, (CustomerModel) user);
					return;
				}
			}
			catch (final Exception e)
			{
				LOG.error("Could not set user's account in session.  Current account not set.", e);
			}
		}
		else
		{
			LOG.error("user param in call to setCurrentAccount is not an instance of CustomerModel. Current account not set.");
		}
	}

	/**
	 * Get the account set in session.
	 * <p/>
	 * {@link #getCurrentAccount()}
	 */
	@Override
	public AmwayAccountModel getCurrentAccount()
	{
		return getSessionService().getAttribute(SessionVariables.ACCOUNT);
	}

	/**
	 * update the current session price group
	 * <p/>
	 * {@link #updatePriceGroupforSession(java.lang.String)}
	 */
	@Override
	public void updatePriceGroupforSession(final String pricegroup) throws CalculationException
	{
		setUPGInSession(pricegroup);
		getCalculationService().recalculate(getCartService().getSessionCart());
	}

	/**
	 * Set the user price group in session
	 *
	 * @param pricegroup
	 */
	@Override
	public void setUPGInSession(final String pricegroup)
	{
		getSessionService().setAttribute(Europe1Constants.PARAMS.UPG, UserPriceGroup.valueOf(pricegroup));
		LOG.debug("Price Group in session updated to : " + pricegroup);
	}

	/**
	 * upgrade the customer Account to ABO
	 * <p/>
	 * {@link #convertCustomerToAbo(com.amway.core.model.AmwayAccountModel)}
	 */
	@Override
	public void convertCustomerToAbo(final AmwayAccountModel account)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("account", account);
		convertUser(account, AmwayBusinessNature.AMWAYBUSINESSNATURE_1, PriceGroups.ABO_USER_PRICE_GROUP);
	}

	/**
	 * downgrade the ABO account to Customer
	 * <p/>
	 * {@link #convertAboToCustomer(com.amway.core.model.AmwayAccountModel)}
	 */
	@Override
	public void convertAboToCustomer(final AmwayAccountModel account)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("account", account);
		convertUser(account, AmwayBusinessNature.AMWAYBUSINESSNATURE_4, PriceGroups.RETAIL_PRICE_GROUP);
	}


	/**
	 * Set the user price group in session based on the account. This price group will be used in price calculation
	 * <p/>
	 * {@link #setUPGInSession(com.amway.core.model.AmwayAccountModel, de.hybris.platform.core.model.user.CustomerModel)}
	 */
	@Override
	public void setUPGInSession(final AmwayAccountModel account, final CustomerModel customer)
	{
		if(account == null || customer == null) {
			getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
					UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.RETAIL_PRICE_GROUP));
			return;
		}

		LOG.debug("Setting the UserPriceGroup " + account.getUserPriceGroup() + " to session from account :" + account.getCode());
		getSessionService().setAttribute(Europe1Constants.PARAMS.UPG, (account.getUserPriceGroup() != null) ?
				account.getUserPriceGroup() :
				UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));

		//overriding the price group if additional applicant without preprinted no for AT-4599
		if (PriceGroups.ABO_USER_PRICE_GROUP.equals(account.getUserPriceGroup().toString())
				&& AmwayBusinessNature.AMWAYBUSINESSNATURE_4.equals(account.getBusinessNature()))
		{
			final SalesApplication salesApplication = (SalesApplication) JaloSession.getCurrentSession()
					.getAttribute("currentChannel");
			if (SalesApplication.POS.toString().equals(salesApplication.getCode()))
			{
				LOG.debug("overriding the the price group if order is placed from pos " + account.getUserPriceGroup()
						+ " to session from account :" + account.getCode());
				getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
						UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.RETAIL_PRICE_GROUP));
			}
			else if (customer != null && !StringUtils
					.equalsIgnoreCase(account.getPrimaryParty().getCustomerID(), customer.getCustomerID()))
			{
				LOG.debug("overriding the the price group if additional applicant logins without preprinted no " + account
						.getUserPriceGroup() + " to session from account :" + account.getCode());
				getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
						UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.RETAIL_PRICE_GROUP));
			}
		}
	}

	@Override
	public void setUPGInSession()
	{
		AmwayAccountModel currentAccount = getCurrentAccount();
		UserModel currentUser = getUserService().getCurrentUser();
		setUPGInSession(currentAccount, (CustomerModel)currentUser);
	}

	/**
	 * Get the account from the customer, to which it is a primary party (customer).
	 * <p/>
	 * {@link #getAccountFromUser(de.hybris.platform.core.model.user.UserModel)}
	 */
	@Override
	public AmwayAccountModel getAccountFromUser(final UserModel userModel)
	{
		//TODO: it should be the primary party for that account.
		ServicesUtil.validateParameterNotNullStandardMessage("user", userModel);
		if (userModel instanceof CustomerModel)
		{
			final CustomerModel customer = (CustomerModel) userModel;
			final Set<AmwayAccountModel> accounts = customer.getAccounts();

			final Optional<AmwayAccountModel> amwayAccount = accounts.stream()
					.filter(account -> account.getPrimaryParty().equals(customer)).findFirst();
			if (amwayAccount.isPresent())
				{
				return amwayAccount.get();
			}
		}
		return null;
	}

	/**
	 * Get the account set in session.
	 * <p/>
	 * {@link #getCurrentAccountNumber()}
	 */
	@Override
	public String getCurrentAccountNumber()
	{
		if (getCurrentAccount() != null)
		{
			return getCurrentAccount().getCode();
		}
		return StringUtils.EMPTY;
	}

	private void convertUser(final AmwayAccountModel account, final AmwayBusinessNature businessNature, final String priceGroup)
	{
		account.setBusinessNature(businessNature);
		account.setUserPriceGroup(UserPriceGroup.valueOf(priceGroup));
		getModelService().save(account);
		getModelService().refresh(account);
	}

	/**
	 * save the logged-in customer info in hybris from getAmwayProfile response
	 * <p/>
	 * {@link #saveLoggedInCustomerInfo(com.amway.core.dms.data.AmwayProfileResponseData, com.amway.core.los.data.LosAccountDetailResponseData)}
	 */
	@Override
	public void saveLoggedInCustomerInfo(final AmwayProfileResponseData response,
			final LosAccountDetailResponseData losAccountDetailResponseData)
	{
		saveLoggedInCustomerInfo(response, losAccountDetailResponseData, (CustomerModel) getUserService().getCurrentUser());
	}

	/**
	 * save the logged-in customer info in hybris from getAmwayProfile response
	 * <p/>
	 * {@link #saveLoggedInCustomerInfo(com.amway.core.dms.data.AmwayProfileResponseData, com.amway.core.los.data.LosAccountDetailResponseData, de.hybris.platform.core.model.user.CustomerModel)}
	 */
	@Override
	public void saveLoggedInCustomerInfo(final AmwayProfileResponseData response,
			final LosAccountDetailResponseData losAccountDetailResponseData, final CustomerModel customerModel)
	{
		final AmwayAccountModel account = getCurrentAccount();
		if (response != null)
		{
			final AccountMasterDetailsData accountMasterDetailsData = response.getAccountMasterDetails();
			final List<PartyDetailsData> partiDetailsList = response.getPartyDetailList();
			if (partiDetailsList != null)
			{
				validatePrimaryPartyOnAccount(account, partiDetailsList);
			}
			if (accountMasterDetailsData != null)
			{
				saveExpiryDate(account, accountMasterDetailsData.getExpirationDate());
				saveStatus(account, accountMasterDetailsData.getStatusCd());
				saveBusinessNature(account, accountMasterDetailsData.getBusinessNature());
				saveSegmentationLevel(account, accountMasterDetailsData.getSegmentCd());
				if (!StringUtils.equals(account.getName(), accountMasterDetailsData.getAccountName()))
				{
					account.setName(accountMasterDetailsData.getAccountName());
				}
				if (accountMasterDetailsData.getEntryDate() != null)
				{
					getSessionService().getCurrentSession().setAttribute("entryDate", accountMasterDetailsData.getEntryDate());
				}
			}
			//setting restrictions null to create new everytime user login
			//as some restrictions may be removed in response after they are inactive

			//commenting because we are fetching the existing value from dao
			//getModelService().removeAll(account.getRestrictions());
			//getModelService().saveAll();
			//getModelService().refresh(account);
			if (response.getBlockPrivDetailsData() != null)
			{
				final Set<AmwayBusinessRestrictionModel> restictions = new HashSet<>();
				for (final BlockPrivDetailsData blocksData : response.getBlockPrivDetailsData())
				{
					restictions.add(saveBusinessRestriction(account.getCode(), blocksData.getBlockPrivTypeId()));
				}
				account.setRestrictions(restictions);
			}
			if (CollectionUtils.isNotEmpty(response.getPartyDetailList()))
			{
				savePartyDetails(response.getPartyDetailList(), customerModel);
			}
			updateAccountLosDetails(account, losAccountDetailResponseData);
			getModelService().save(account);
			getModelService().refresh(account);
		}
	}

	/**
	 * @param account
	 * @param partiDetailsList
	 */
	private void validatePrimaryPartyOnAccount(final AmwayAccountModel account, final List<PartyDetailsData> partiDetailsList)
	{
		for (final PartyDetailsData partyDetailsData : partiDetailsList)
		{
			if (partyDetailsData.getPartyPersonalDetailsData() != null && StringUtils
					.equalsIgnoreCase(partyDetailsData.getPartyPersonalDetailsData().getPrimaryOnAccount(), "1") && !StringUtils
					.equalsIgnoreCase(account.getPrimaryParty().getCustomerID(),
							partyDetailsData.getPartyPersonalDetailsData().getPartyId()))
			{
				for (final CustomerModel party : account.getParties())
				{
					if (StringUtils
							.equalsIgnoreCase(party.getCustomerID(), partyDetailsData.getPartyPersonalDetailsData().getPartyId()))
					{
						account.setPrimaryParty(party);
					}
				}
			}
		}
	}

	/**
	 * @param account
	 * @param losAccountDetailResponseData
	 */
	private void updateAccountLosDetails(final AmwayAccountModel account,
			final LosAccountDetailResponseData losAccountDetailResponseData)
	{
		if (losAccountDetailResponseData != null && losAccountDetailResponseData.getLosAccountDetailResponseListData() != null)
		{
			final AmwayBusinessLevelModel level = account.getLevel() != null ?
					account.getLevel() :
					(AmwayBusinessLevelModel) getModelService().create(AmwayBusinessLevelModel.class);
			for (final LosAccountDetailDownlinesResponse accountDetailResponseList : losAccountDetailResponseData
					.getLosAccountDetailResponseListData())
			{
				if (StringUtils.isNotBlank(accountDetailResponseList.getBonus()))
				{
					level.setBonusPercentage(Double.valueOf(accountDetailResponseList.getBonus()).doubleValue());
					getModelService().save(level);
				}
				break;
			}
			if (losAccountDetailResponseData.getCurrentAwardDetails() != null)
			{
				try
				{
					final QualificationLevelTypeEnum leadershipLevel = getEnumerationService()
							.getEnumerationValue(QualificationLevelTypeEnum.class,
									LEADERSHIPLEVEL + losAccountDetailResponseData.getCurrentAwardDetails().getRank());
					level.setQualificationLevel(leadershipLevel);
					getModelService().save(level);
				}
				catch (final Exception e)
				{
					LOG.error("Got this error while updating qualification level: " + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * checks if account{@link AmwayAccountModel} is valid for renewal
	 */
	@Override
	public boolean isCurrentAccountInactive()
	{
		final AmwayAccountModel account = getCurrentAccount();
		if (account != null && account.getStatus().getCode().equals(AmwayAccountStatus.INACTIVE.getCode()))
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if account{@link AmwayAccountModel} is expired
	 */
	@Override
	public boolean isAboAccountExpired()
	{
		final Date currentDate = AmwayDateHelper.getTimeForSiteTimeZone();
		final AmwayAccountModel account = getCurrentAccount();
		if (account != null && AmwayBusinessNature.AMWAYBUSINESSNATURE_1.equals(account.getBusinessNature())
				&& account.getExpiryDate() != null && account.getExpiryDate().before(currentDate))
		{
			return true;
		}
		return false;
	}

	/**
	 * checks if current account{@link AmwayAccountModel} is expired
	 */
	@Override
	public boolean isCurrentAccountAboutToExpire()
	{
		final AmwayAccountModel account = getCurrentAccount();
		if (account != null)
		{
			final int expirationPeriod = getCmsSiteService().getCurrentSite().getExpirationPeriod();
			final Date currentDate = AmwayDateHelper.getTimeForSiteTimeZone();
			final Date expiryDate = account.getExpiryDate();
			if (expiryDate != null && expiryDate.after(currentDate))
			{
				final int days = Days.daysBetween(new DateTime(currentDate), new DateTime(expiryDate)).getDays();
				if (AmwayCustomerHelper.isABOCustomer() && days < expirationPeriod)
				{
					LOG.info("Account : " + account.getCode() + " about to Expire with in " + days + " Days");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * checks if account{@link AmwayAccountModel} is blocked
	 */
	@Override
	public boolean isAccountBlock()
	{
		//This logic should not be here because it is initiating two call to getAmwayProfile.

		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		if (customer != null)
		{
			final Set<AmwayAccountModel> accounts = customer.getAccounts();
			for (final AmwayAccountModel account : accounts)
			{
				final Set<AmwayBusinessRestrictionModel> restrictions = account.getRestrictions();
				for (final AmwayBusinessRestrictionModel restriction : restrictions)
				{
					if (restriction.getRestrictionId().equalsIgnoreCase("BlockLogin"))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * checks if account{@link AmwayAccountModel} is blocked
	 */
	@Override
	public boolean isLegalEntityClientBlock()
	{
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		final AmwayAccountModel currentAccount =
				getCurrentAccount() != null ? getCurrentAccount() : customer.getAccounts().iterator().next();
		if (AmwayBusinessNature.AMWAYBUSINESSNATURE_4.equals(currentAccount.getBusinessNature()) && CustomerType.CUSTOMERTYPE_2
				.equals(currentAccount.getPrimaryParty().getType()))
		{
			return true;
		}
		return false;
	}

	private void saveBusinessNature(final AmwayAccountModel account, final String businessNature)
	{
		String currentBusinessNature = StringUtils.EMPTY;
		//updating businessnature only if it is mismatch in hybris and Magic
		switch (account.getBusinessNature().getCode())
		{
			case "AmwayBusinessNature_1":
				currentBusinessNature = AccountTypes.BUSINESS_OWNER;
				break;
			case "AmwayBusinessNature_4":
				currentBusinessNature = AccountTypes.CUSTOMER;
				break;
			case "AmwayBusinessNature_7":
				currentBusinessNature = AccountTypes.EMPLOYEE;
				break;
			default:
				currentBusinessNature = AccountTypes.CUSTOMER;
				break;
		}
		if (StringUtils.isNotBlank(businessNature) && !businessNature.equals(currentBusinessNature))
		{
			if (businessNature.contains(AccountTypes.BUSINESS_OWNER))
			{
				account.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
				account.setUserPriceGroup(UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP));
				setUPGInSession(PriceGroups.ABO_USER_PRICE_GROUP);
			}
			if (businessNature.contains(AccountTypes.CUSTOMER))
			{
				account.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_4);
				account.setUserPriceGroup(UserPriceGroup.valueOf(PriceGroups.RETAIL_PRICE_GROUP));
				setUPGInSession(PriceGroups.RETAIL_PRICE_GROUP);
			}
			if (businessNature.contains(AccountTypes.EMPLOYEE))
			{
				account.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_7);
				account.setUserPriceGroup(UserPriceGroup.valueOf(PriceGroups.EMPLOYEE_PRICE_GROUP));
				setUPGInSession(PriceGroups.EMPLOYEE_PRICE_GROUP);
			}
		}
	}

	private void saveSegmentationLevel(final AmwayAccountModel account, final String segmentationLevel)
	{
		final AmwayBusinessLevelModel businessLevelModel = account.getLevel() != null ?
				account.getLevel() :
				(AmwayBusinessLevelModel) getModelService().create(AmwayBusinessLevelModel.class);
		if (StringUtils.isNotBlank(segmentationLevel))
		{
			if (segmentationLevel.contains(SegmentationLevelTypes.PLATINUM))
			{
				businessLevelModel.setSegmentaionLevel(SegmentationLevelTypeEnum.SEGMENTATIONLEVELTYPEENUM_2);
			}
			if (segmentationLevel.contains(SegmentationLevelTypes.SILVER_PRODUCER))
			{
				businessLevelModel.setSegmentaionLevel(SegmentationLevelTypeEnum.SEGMENTATIONLEVELTYPEENUM_4);
			}
			if (segmentationLevel.contains(SegmentationLevelTypes.SILVER_SPONSOR))
			{
				businessLevelModel.setSegmentaionLevel(SegmentationLevelTypeEnum.SEGMENTATIONLEVELTYPEENUM_6);
			}
			if (segmentationLevel.contains(SegmentationLevelTypes.NON_GROUP_LEADER))
			{
				businessLevelModel.setSegmentaionLevel(SegmentationLevelTypeEnum.SEGMENTATIONLEVELTYPEENUM_9);
			}
			getModelService().save(businessLevelModel);
			account.setLevel(businessLevelModel);
		}
	}

	private void saveStatus(final AmwayAccountModel account, final String status)
	{
		if (StringUtils.isNotBlank(status))
		{
			if (AmwayAccountStatus.ACTIVE.getCode().equalsIgnoreCase(status))
			{
				account.setStatus(AmwayAccountStatus.ACTIVE);
			}
			if (AmwayAccountStatus.INACTIVE.getCode().equalsIgnoreCase(status))
			{
				account.setStatus(AmwayAccountStatus.INACTIVE);
			}
		}
	}

	private AmwayBusinessRestrictionModel saveBusinessRestriction(final String accountCode, final String restrictionId)
	{
		final String restrictionCode = restrictionId;
		final AmwayBusinessRestrictionModel businessRestriction = amwayAccountDao.getBusinessRestrictionFromCode(restrictionCode);
		if (null == businessRestriction)
		{
			final AmwayBusinessRestrictionModel businessRestrictionModel = getModelService()
					.create(AmwayBusinessRestrictionModel.class);
			businessRestrictionModel.setCode(restrictionCode);
			businessRestrictionModel.setRestrictionId(restrictionId);
			getModelService().save(businessRestrictionModel);
			LOG.debug("creating a new restriction with code :" + restrictionCode);
			return businessRestrictionModel;
		}
		LOG.debug("sending the existing restriction code :" + businessRestriction.getCode());
		return businessRestriction;
	}

	private void savePartyDetails(final List<PartyDetailsData> partyDetailsDataList, final CustomerModel customerModel)
	{
		for (final PartyDetailsData partyDetailsData : partyDetailsDataList)
		{
			if (partyDetailsData.getPartyNameDetailsData() != null && StringUtils.equals(customerModel.getCustomerID(),
					partyDetailsData.getPartyNameDetailsData().getPartyId()))
			{
				final LocaleNameData nameData = partyDetailsData.getPartyNameDetailsData().getLocaleNameData();
				final StringBuilder partyName = new StringBuilder();
				partyName.append(StringUtils.defaultString(nameData.getGivenName())).append(" ").append(StringUtils.defaultString(nameData.getFamilyName()));
				customerModel.setName(partyName.toString());
				savePartyAddressDetails(partyDetailsData, customerModel);
				break;
			}
		}
	}

	/**
	 *
	 * @param partyDetailsData
	 * @param customerModel
	 */
	private void savePartyAddressDetails(final PartyDetailsData partyDetailsData, final CustomerModel customerModel)
	{

		List<AddressModel> addresses = new ArrayList();

		if (partyDetailsData.getAddressMasterData() != null)
		{
			for (final AddressData address : partyDetailsData.getAddressMasterData())
			{
				if (StringUtils.equals(address.getPartyId(), customerModel.getCustomerID())) {
					address.setVisibleInAddressBook(true);
					/* for express checkout name on delivery address model */
					if (StringUtils.isBlank(address.getFirstName()))
					{
						address.setFirstName(getCurrentAccount().getName());
					}

					AddressModel addressModel = (AddressModel) getModelService().create(AddressModel.class);
					addressModel.setOwner(customerModel);
					addressModel.setVisibleInAddressBook(Boolean.TRUE);
					getAddressReverseConverter().convert(address, addressModel);

					// when address is both shipping and billing
					if (address.isBillingAddress() && address.isShippingAddress()) {
						customerModel.setDefaultShipmentAddress(addressModel);
						customerModel.setDefaultPaymentAddress(addressModel);
						addresses.add(addressModel);
					}
					else  {
						if (address.isShippingAddress())
						{
							customerModel.setDefaultShipmentAddress(addressModel);
							addresses.add(addressModel);
						}
						if (address.isBillingAddress())
						{
							customerModel.setDefaultPaymentAddress(addressModel);
							addresses.add(addressModel);
						}
					}
				}
			}
		}

		customerModel.setAddresses(addresses);
		ArrayList updateItems = new ArrayList();
		updateItems.add(customerModel);
		updateItems.addAll(addresses);
		getModelService().saveAll(updateItems);
		getModelService().refresh(customerModel);

	}


	/**
	 * To save the expiry date.
	 *
	 * @param account
	 * @param expiryDate
	 */
	@Override
	public void saveExpiryDate(final AmwayAccountModel account, final Date expiryDate)
	{
		account.setExpiryDate(expiryDate);
	}

	/**
	 * method to fetch the account data from MLOS
	 *
	 * @param losAccountRequestData
	 * @param aboNumber
	 */
	@Override
	public LosAccountData getLosAccount(final LosAccountRequestData losAccountRequestData, String aboNumber)
	{
		if (StringUtils.isBlank(aboNumber))
		{
			aboNumber = getBaseStoreService().getCurrentBaseStore().getAffiliateNumber() + "-" + getCurrentAccountNumber();
		}
		losAccountRequestData.setBonusPeriod(AmwayPeriodHelper.getCurrentActiveBonusPeriod());
		losAccountRequestData.setRequestingAbo(getBaseStoreService().getCurrentBaseStore().getAffiliateNumber() + "-" + aboNumber);
		losAccountRequestData
				.setAbo(getBaseStoreService().getCurrentBaseStore().getAffiliateNumber() + "-" + getCurrentAccountNumber());
		final LosAccountResponseData losAccountResponseData = getLosAccountService().process(losAccountRequestData);

		if (checkLosServiceResponse(losAccountResponseData))
		{
			/* code for AT-2152, for each abo calling details service */
			final String requestingAbo =
					getBaseStoreService().getCurrentBaseStore().getAffiliateNumber() + "-" + getCurrentAccountNumber();
			final LosAccountDetailResponseData losAccountDetailResponseDataList = new LosAccountDetailResponseData();
			final List<LosAccountDetailDownlinesResponse> losAccountDetailResponseListData = new ArrayList<LosAccountDetailDownlinesResponse>();
			final List<String> listOfAboAndAff = losAccountResponseData.getListOfAboAndAff();
			final List<List<String>> chunkListOfAboAndAff = Lists
					.partition(listOfAboAndAff, getConfigurationService().getConfiguration().getInt("los.downline.chunk.size", 50));
			for (final List<String> aboAndAffNumber : chunkListOfAboAndAff)
			{
				losAccountRequestData.setRequestingAbo(requestingAbo);
				losAccountRequestData.setAbo(StringUtils.join(aboAndAffNumber, ","));
				final LosAccountDetailResponseData accountDetailResponseData = getLosAccountDetails(losAccountRequestData);
				if (checkLosServiceResponse(accountDetailResponseData))
				{
					for (final LosAccountDetailDownlinesResponse losAccountDetailDownlinesResponse : accountDetailResponseData
							.getLosAccountDetailResponseListData())
					{
						losAccountDetailResponseListData.add(losAccountDetailDownlinesResponse);
					}
				}
			}
			losAccountDetailResponseDataList.setLosAccountDetailResponseListData(losAccountDetailResponseListData);

			return mergeLosResponse(losAccountResponseData, losAccountDetailResponseDataList, aboNumber,
					losAccountRequestData.getDepth());
		}
		return null;
	}


	private LosAccountData mergeLosProfileResponse(final LosAccountResponseData losAccountResponseData,
			final LosAccountDetailResponseData accountDetailResponseData)
	{
		final LosAccountData losAccountData = new LosAccountData();
		final List<LosAccountDownlinesData> losAccountDownlinesDatas = new ArrayList<LosAccountDownlinesData>();
		final List<LosAccountDetailDownlinesResponse> losAccountDetailDownlinesResponse = accountDetailResponseData
				.getLosAccountDetailResponseListData();

		for (final LosAccountDetailDownlinesResponse accountDetailDownlinesResponse : losAccountDetailDownlinesResponse)
		{
			final LosAccountDownlinesData losAccountDownlinesData = new LosAccountDownlinesData();

			for (final LosAccountDownlinesResponse downlinesResponse : losAccountResponseData.getLosAccountResponseListData())
			{
				losAccountDownlinesData.setCountry(downlinesResponse.getCountry());
			}
			losAccountDownlinesData.setName(accountDetailDownlinesResponse.getName());
			losAccountDownlinesData.setAboNo(accountDetailDownlinesResponse.getAboNo());
			losAccountDownlinesData.setBonus(accountDetailDownlinesResponse.getBonus());
			losAccountDownlinesData.setGpv(accountDetailDownlinesResponse.getGpv());
			losAccountDownlinesData.setPpv(accountDetailDownlinesResponse.getPpv());
			losAccountDownlinesData.setGroupOrders(accountDetailDownlinesResponse.getGroupOrders());
			losAccountDownlinesData.setNewAbos(accountDetailDownlinesResponse.getNewAbos());
			losAccountDownlinesData.setPersonalOrders(accountDetailDownlinesResponse.getPersonalOrders());
			losAccountDownlinesData.setTotalAbos(accountDetailDownlinesResponse.getTotalAbos());
			losAccountDownlinesData.setQualifiedLegs(accountDetailDownlinesResponse.getQualifiedLegs());
			losAccountDownlinesData.setSponsor(accountDetailDownlinesResponse.getSponsor());
			losAccountDownlinesData.setPlatinumSponsor(accountDetailDownlinesResponse.getPlatinumSponsor());

			losAccountDownlinesData.setRenewalDate(accountDetailDownlinesResponse.getRenewalDate());
			losAccountDownlinesData.setBusinessEntryDate(accountDetailDownlinesResponse.getBusinessEntryDate());
			losAccountDownlinesData.setHighestPinName(accountDetailDownlinesResponse.getHighestPinName());
			losAccountDownlinesData
					.setHighestPinQualificationPeriod(accountDetailDownlinesResponse.getHighestPinQualificationPeriod());
			try
			{
				final QualificationLevelTypeEnum leadershipLevel = getEnumerationService()
						.getEnumerationValue(QualificationLevelTypeEnum.class,
								LEADERSHIPLEVEL + accountDetailDownlinesResponse.getCurrent());

				losAccountDownlinesData.setCurrent(getEnumerationService().getEnumerationName(leadershipLevel));
			}
			catch (final Exception e)
			{
				LOG.error("Got error while attempting to set current in downline data: " + e.getMessage(), e);
			}

			losAccountDownlinesData.setPhone(accountDetailDownlinesResponse.getPhone());
			losAccountDownlinesData.setEmail(accountDetailDownlinesResponse.getEmail());

			losAccountDownlinesData.setAddress(accountDetailDownlinesResponse.getAddress());

			losAccountDownlinesDatas.add(losAccountDownlinesData);

		}
		losAccountData.setLosAccountDataList(losAccountDownlinesDatas);
		return losAccountData;
	}

	/**
	 * Method to fetch the account details from MLOS
	 *
	 * @param losAccountRequestData
	 */
	@Override
	public LosAccountDetailResponseData getLosAccountDetails(final LosAccountRequestData losAccountRequestData)
	{
		losAccountRequestData.setGetVolume(Boolean.TRUE);
		losAccountRequestData.setGetExtAttributes(Boolean.TRUE);
		losAccountRequestData.setGetSponsorStats(Boolean.TRUE);
		losAccountRequestData.setGetQualification(Boolean.TRUE);
		return getLosAccountDetailService().process(losAccountRequestData);
	}

	/**
	 * method to fetch data from MLOS
	 */
	@Override
	public LosAccountDetailResponseData getBusinessInfo()
	{
		return getLosAccountDetails(createBusinessInfoRequestData());
	}

	/**
	 * method to fetch the data from MLOS.
	 */
	@Override
	public LosAccountResponseData getBusinessInfoAccount()
	{
		final LosAccountRequestData requestData = new LosAccountRequestData();
		requestData.setDepth("1");
		requestData.setBonusPeriod(AmwayPeriodHelper.getCurrentActiveBonusPeriod());
		final LosAccountRequestData losAccountRequestData = createLosAccountRequestData(requestData);
		losAccountRequestData.setGetQualification(Boolean.TRUE);
		final LosAccountResponseData losAccountResponseData = getLosAccountService().process(losAccountRequestData);
		return losAccountResponseData;
	}

	/**
	 * method to fetch los profile from MLOS.
	 *
	 * @param losAccountRequestData
	 */
	@Override
	public LosAccountData getLosProfile(final LosAccountRequestData losAccountRequestData)
	{
		losAccountRequestData.setBonusPeriod(AmwayPeriodHelper.getCurrentActiveBonusPeriod());
		final String requestingAbo =
				getBaseStoreService().getCurrentBaseStore().getAffiliateNumber() + "-" + getCurrentAccountNumber();
		final String concatedAbos = losAccountRequestData.getAbo();

		losAccountRequestData.setAbo(requestingAbo);
		losAccountRequestData.setRequestingAbo(StringUtils.substringBefore(concatedAbos, ","));
		final LosAccountResponseData losAccountResponseData = getLosAccountService().process(losAccountRequestData); //first call

		if (checkLosServiceResponse(losAccountResponseData))
		{
			losAccountRequestData.setRequestingAbo(requestingAbo);
			losAccountRequestData.setAbo(concatedAbos);
			final LosAccountDetailResponseData accountDetailResponseData = getLosAccountDetails(
					createLosProfileInfoRequestData(losAccountRequestData));//second call
			if (checkLosServiceResponse(accountDetailResponseData))
			{
				return mergeLosProfileResponse(losAccountResponseData, accountDetailResponseData);
			}
		}
		return null;
	}

	/**
	 * Method to fetch the data from MLOS.
	 *
	 * @param requestData
	 * @throws IOException
	 */
	@Override
	public byte[] getBonusStatementPDF(final BonusStatementRequestData requestData) throws IOException
	{
		final BonusStatementResultData resultData = getLosBonusStatementService().process(requestData);
		if (resultData.getReturnCode().equals(new Integer(-1)))
		{
			throw new AmwayServiceException(resultData.getReturnMessage());
		}
		final Path path = Paths.get(resultData.getBonusStatementPDF().getPath());
		return Files.readAllBytes(path);
	}

	private LosAccountRequestData createLosProfileInfoRequestData(final LosAccountRequestData requestData)
	{

		final LosAccountRequestData accountRequestData = createLosAccountRequestData(requestData);
		if (StringUtils.isBlank(accountRequestData.getAbo()))
		{
			accountRequestData.setAbo(accountRequestData.getRequestingAbo());
		}
		accountRequestData.setBonusPeriod(AmwayPeriodHelper.getBonusPeriodBefore(12));
		accountRequestData.setBonusPeriodCount("13");

		return accountRequestData;
	}

	private LosAccountRequestData createBusinessInfoRequestData()
	{
		final LosAccountRequestData accountRequestData = new LosAccountRequestData();
		createLosAccountRequestData(accountRequestData);
		accountRequestData.setAbo(accountRequestData.getRequestingAbo());
		accountRequestData.setBonusPeriod(AmwayPeriodHelper.getBonusPeriodBefore(12));
		accountRequestData.setBonusPeriodCount("13");

		return accountRequestData;
	}

	private boolean checkLosServiceResponse(final CommonLosResponseData responseData)
	{
		if (responseData != null && responseData.getReturnCode() != 1)
		{
			LOG.error("Service call is unsuccessful.");
			throw new AmwayServiceException(responseData.getReturnMessage(), responseData.getReturnCode());
		}
		LOG.info("Service call is successful.");
		return true;
	}

	private LosAccountData mergeLosResponse(final LosAccountResponseData losAccountResponseData,
			final LosAccountDetailResponseData accountDetailResponseData, final String aboNumber, final String depth)
	{
		final LosAccountData losAccountData = new LosAccountData();
		final List<LosAccountDownlinesData> losAccountDownlinesDatas = new ArrayList<LosAccountDownlinesData>();
		final List<LosAccountDetailDownlinesResponse> losAccountDetailDownlinesResponse = accountDetailResponseData
				.getLosAccountDetailResponseListData();
		for (final LosAccountDownlinesResponse downlinesResponse : losAccountResponseData.getLosAccountResponseListData())
		{
			if (depth.equals("1") || !aboNumber.equals(downlinesResponse.getAboNo()))
			{
				final LosAccountDownlinesData losAccountDownlinesData = new LosAccountDownlinesData();
				losAccountDownlinesData.setCountry(downlinesResponse.getCountry());
				losAccountDownlinesData.setName(downlinesResponse.getName());
				losAccountDownlinesData.setHasDownlines(downlinesResponse.isHasDownlines());
				losAccountDownlinesData.setPrivacyFlag(downlinesResponse.isPrivacyFlag());
				try
				{
					final QualificationLevelTypeEnum leadershipLevel = getEnumerationService()
							.getEnumerationValue(QualificationLevelTypeEnum.class,
									LEADERSHIPLEVEL + downlinesResponse.getCurrentAwardRank());

					losAccountDownlinesData.setCurrentAwardRank(getEnumerationService().getEnumerationName(leadershipLevel));
				}
				catch (final Exception e)
				{
					LOG.error("Got error while attempting to set current award rank: " + e.getMessage(), e);
				}
				for (final LosAccountDetailDownlinesResponse accountDetailDownlinesResponse : losAccountDetailDownlinesResponse)
				{
					if (downlinesResponse.getAboNo().equals(accountDetailDownlinesResponse.getAboNo()))
					{
						losAccountDownlinesData.setAboNo(downlinesResponse.getAboNo());
						losAccountDownlinesData.setBonus(accountDetailDownlinesResponse.getBonus());
						losAccountDownlinesData.setGpv(accountDetailDownlinesResponse.getGpv());
						losAccountDownlinesData.setPpv(accountDetailDownlinesResponse.getPpv());
						losAccountDownlinesData.setGroupOrders(accountDetailDownlinesResponse.getGroupOrders());
						losAccountDownlinesData.setNewAbos(accountDetailDownlinesResponse.getNewAbos());
						losAccountDownlinesData.setPersonalOrders(accountDetailDownlinesResponse.getPersonalOrders());
						losAccountDownlinesData.setTotalAbos(accountDetailDownlinesResponse.getTotalAbos());
						losAccountDownlinesData.setQualifiedLegs(accountDetailDownlinesResponse.getQualifiedLegs());
						losAccountDownlinesData.setSponsor(accountDetailDownlinesResponse.getSponsor());
						losAccountDownlinesData.setPlatinumSponsor(accountDetailDownlinesResponse.getPlatinumSponsor());
						losAccountDownlinesData.setSpMonth(accountDetailDownlinesResponse.isSpMonth());
						losAccountDownlinesDatas.add(losAccountDownlinesData);
					}
				}
				losAccountData.setLosAccountDataList(losAccountDownlinesDatas);
			}
		}
		return losAccountData;
	}

	private LosAccountRequestData createLosAccountRequestData(final LosAccountRequestData requestData)
	{
		if (StringUtils.isBlank(requestData.getRequestingAbo()))
		{
			requestData.setRequestingAbo(
					getBaseStoreService().getCurrentBaseStore().getAffiliateNumber() + "-" + getCurrentAccountNumber());
		}
		return requestData;
	}


	/**
	 * method to fetch the data from MLOS.
	 *
	 * @param aboNum
	 * @param productCode
	 */
	@Override
	public void adjustCustomerBonusDetails(final String aboNum, final String productCode)
	{
		try
		{
			if (Boolean.TRUE.equals(sessionService.getAttribute("prePrintedNumberEntered")) && StringUtils.isNotEmpty(productCode))
			{
				final Date systemDate = new Date();
				Double businessVolume = null;
				Double pointValue = null;
				final BonusOrderRequestData bonusRequestData = new BonusOrderRequestData();
				final List<TransactionData> transactionDataList = new ArrayList<>();
				final TransactionData transactionData = new TransactionData();
				final Collection<CatalogVersionModel> filteredCatalogVersions = filterCatalogVersions();
				final List<PriceInformation> prices = new ArrayList<PriceInformation>();
				sessionService.executeInLocalView(new SessionExecutionBody()
				{
					@Override
					public void executeWithoutResult()
					{
						getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
								UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));
						getSessionService().setAttribute(I18NConstants.CURRENCY_SESSION_ATTR_KEY,
								baseStoreService.getCurrentBaseStore().getDefaultCurrency());
						catalogVersionService.setSessionCatalogVersions(filteredCatalogVersions);
						final ProductModel product = productService.getProductForCode(productCode);
						prices.addAll(priceService.getPriceInformationsForProduct(product));
					}
				}, userService.getAnonymousUser());
				if (!prices.isEmpty())
				{
					final PriceInformation info = prices.get(0);
					if (null != info)
					{
						if (null != info.getQualifierValue("businessVolume"))
						{
							businessVolume = (Double) info.getQualifierValue("businessVolume");
						}
						if (null != info.getQualifierValue("pointValue"))
						{
							pointValue = (Double) info.getQualifierValue("pointValue");
						}
						if (null != info.getPriceValue())
						{
							transactionData.setAmount(Double.valueOf(info.getPriceValue().getValue()));
						}
					}
				}
				else
				{
					LOG.error("could not find prices for service product :" + productCode);
				}
				transactionData.setBonusPeriod(AmwayPeriodHelper.getCurrentActiveBonusPeriod());
				final SalesApplication salesApplication = (SalesApplication) JaloSession.getCurrentSession()
						.getAttribute("currentChannel");

				bonusRequestData.setTimestamp(getDate(systemDate));
				bonusRequestData.setIsoCountryCode(baseSiteService.getCurrentBaseSite().getDefaultCountry().getIsocode());
				bonusRequestData.setSourceApplication("Hybris");
				bonusRequestData.setId(productCode);
				bonusRequestData.setAffiliateCode(baseStoreService.getCurrentBaseStore().getAffiliateNumber());
				transactionData.setAboId(aboNum);
				transactionData.setBusinessVolume(businessVolume);
				transactionData.setPointValue(pointValue);
				transactionData.setIsoCurrencyCode("BRL");
				transactionData.setChannel(getEnumerationService().getEnumerationName(salesApplication, Locale.ENGLISH));
				transactionData.setReferenceInvoice(aboNum);
				transactionData.setReferenceId(aboNum);
				transactionData.setProfit(Double.valueOf("0.0"));

				transactionData.setWarehouseCode(warehouseService.getDefWarehouse().get(5).getCode());
				transactionDataList.add(transactionData);
				bonusRequestData.setTransactions(transactionDataList);
				getBonusAdjustmentService().process(bonusRequestData);

				LOG.info("bonusAdjustmentServiceCall");
			}
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.error("Bonus adjustment did not occur: " + e.getMessage(), e);
		}
	}

	/**
	 * filters out content, classification and any staged catalog versions
	 *
	 * @return
	 */
	protected Collection<CatalogVersionModel> filterCatalogVersions()
	{
		final List<CatalogVersionModel> result = new ArrayList();
		final List<CatalogModel> catalogs = getBaseStoreService().getCurrentBaseStore().getCatalogs();
		if (CollectionUtils.isNotEmpty(catalogs))
		{
			for (final CatalogModel catalogModel : catalogs)
			{
				if (!(catalogModel instanceof ClassificationSystemModel) && !(catalogModel instanceof ContentCatalogModel))
				{
					result.add(catalogModel.getActiveCatalogVersion());
				}
			}
		}
		return result;
	}

	private XMLGregorianCalendar getDate(final Date systemDate)
	{
		try
		{
			final GregorianCalendar gregory = new GregorianCalendar();
			gregory.setTime(systemDate);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		}
		catch (final DatatypeConfigurationException e)
		{
			LOG.error("Couldnt create XMLGregorianCalendar ", e);
		}
		return null;
	}



	/**
	 * @return the bonusAdjustmentService
	 */
	public LosService<BonusOrderRequestData, BonusOrderResultData> getBonusAdjustmentService()
	{
		return bonusAdjustmentService;
	}

	/**
	 * @param bonusAdjustmentService the bonusAdjustmentService to set
	 */
	public void setBonusAdjustmentService(final LosService<BonusOrderRequestData, BonusOrderResultData> bonusAdjustmentService)
	{
		this.bonusAdjustmentService = bonusAdjustmentService;
	}

	/**
	 * @return losAccountDetailService
	 */
	public LosService<LosAccountRequestData, LosAccountDetailResponseData> getLosAccountDetailService()
	{
		return losAccountDetailService;
	}

	/**
	 * @param losAccountDetailService the losAccountDetailService to set
	 */
	public void setLosAccountDetailService(
			final LosService<LosAccountRequestData, LosAccountDetailResponseData> losAccountDetailService)
	{
		this.losAccountDetailService = losAccountDetailService;
	}

	/**
	 * @return losAccountService
	 */
	public LosService<LosAccountRequestData, LosAccountResponseData> getLosAccountService()
	{
		return losAccountService;
	}

	/**
	 * @param losAccountService the losAccountService to set
	 */
	public void setLosAccountService(final LosService<LosAccountRequestData, LosAccountResponseData> losAccountService)
	{
		this.losAccountService = losAccountService;
	}

	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	/**
	 * @return sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * @param cmsSiteService the cmsSiteService to set
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return calculationService
	 */
	public CalculationService getCalculationService()
	{
		return calculationService;
	}

	/**
	 * @param calculationService the calculationService to set
	 */
	public void setCalculationService(final CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}

	/**
	 * @return amwayAccountService
	 */
	public AmwayAccountService getAmwayAccountService()
	{
		return amwayAccountService;
	}

	/**
	 * @param amwayAccountService the amwayAccountService to set
	 */
	public void setAmwayAccountService(final AmwayAccountService amwayAccountService)
	{
		this.amwayAccountService = amwayAccountService;
	}

	/**
	 * @return addressReverseConverter
	 */
	public Converter<AddressData, AddressModel> getAddressReverseConverter()
	{
		return addressReverseConverter;
	}

	/**
	 * @param addressReverseConverter the addressReverseConverter to set
	 */
	public void setAddressReverseConverter(final Converter<AddressData, AddressModel> addressReverseConverter)
	{
		this.addressReverseConverter = addressReverseConverter;
	}

	/**
	 * @return enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	/**
	 * @param enumerationService the enumerationService to set
	 */
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	/**
	 * @return losBonusStatementService
	 */
	public LosService<BonusStatementRequestData, BonusStatementResultData> getLosBonusStatementService()
	{
		return losBonusStatementService;
	}

	/**
	 * @param losBonusStatementService the losBonusStatementService to set
	 */
	public void setLosBonusStatementService(
			final LosService<BonusStatementRequestData, BonusStatementResultData> losBonusStatementService)
	{
		this.losBonusStatementService = losBonusStatementService;
	}


	/**
	 * @return the amwayBonusPeriodService
	 */
	public AmwayBonusPeriodService getAmwayBonusPeriodService()
	{
		return amwayBonusPeriodService;
	}

	/**
	 * @param amwayBonusPeriodService the amwayBonusPeriodService to set
	 */
	public void setAmwayBonusPeriodService(final AmwayBonusPeriodService amwayBonusPeriodService)
	{
		this.amwayBonusPeriodService = amwayBonusPeriodService;
	}

	/**
	 * @return the commercePriceService
	 */
	public CommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	/**
	 * @param commercePriceService the commercePriceService to set
	 */
	public void setCommercePriceService(final CommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}

	/**
	 * @return the amwayAccountDao
	 */
	public AmwayAccountDao getAmwayAccountDao()
	{
		return amwayAccountDao;
	}

	/**
	 * @param amwayAccountDao the amwayAccountDao to set
	 */
	public void setAmwayAccountDao(final AmwayAccountDao amwayAccountDao)
	{
		this.amwayAccountDao = amwayAccountDao;
	}
}
