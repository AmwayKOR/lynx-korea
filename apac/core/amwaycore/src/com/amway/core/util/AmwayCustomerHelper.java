package com.amway.core.util;

import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.math.BigDecimal;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.constants.AmwaycoreConstants.SessionVariables;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.BlockPrivDetailsData;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;


/**
 * Amway Customer Helper class.
 */
public class AmwayCustomerHelper
{
	private static ApplicationContext context = null;
	private static SessionService sessionService = null;
	private static BaseSiteService baseSiteService = null;
	private static UserService userService = null;

	static
	{
		context = Registry.getApplicationContext();
		sessionService = (SessionService) context.getBean("sessionService");
		baseSiteService = (BaseSiteService) context.getBean("baseSiteService");
		userService = (UserService) context.getBean("userService");
	}

	/**
	 * To get account customer from current session.
	 *
	 * @return boolean
	 */
	public static boolean isABOCustomer()
	{
		Object accountFromCurrentSession = sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT);
		// this check is added because cronjob runs with admin user and employee model cannot be cast to customer model
		if (accountFromCurrentSession == null && userService.getCurrentUser() instanceof CustomerModel)
		{
			final CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();
			if (null != customerModel && CollectionUtils.isNotEmpty(customerModel.getAccounts()))
			{
				accountFromCurrentSession = customerModel.getAccounts().stream().findFirst().orElse(null);
			}
		}
		return isABOCustomer((AmwayAccountModel) accountFromCurrentSession);
	}

	/**
	 * To get account customer from current session.
	 *
	 * @param userModel
	 *
	 * @return boolean
	 */
	public static boolean isABOCustomer(final CustomerModel customerModel)
	{
		Object accountFromCurrentSession = null;
		if (null != customerModel && CollectionUtils.isNotEmpty(customerModel.getAccounts()))
		{
			accountFromCurrentSession = customerModel.getAccounts().stream().findFirst().orElse(null);
		}
		return isABOCustomer((AmwayAccountModel) accountFromCurrentSession);
	}


	/**
	 * To check amwayaccount is Employee. AMWAYBUSINESSNATURE_7 equals Employee.
	 *
	 * @return boolean
	 */
	//TODO:make a generic method to return type of an account
	public static boolean isEmployee()
	{
		final Object accountFromCurrentSession = sessionService.getCurrentSession().getAttribute(SessionVariables.ACCOUNT);
		final AmwayAccountModel amwayAccountModel = (AmwayAccountModel) accountFromCurrentSession;
		if (amwayAccountModel != null)
		{
			final AmwayBusinessNature businessNature = amwayAccountModel.getBusinessNature();
			return AmwayBusinessNature.AMWAYBUSINESSNATURE_7.equals(businessNature);
		}
		return false;
	}


	/**
	 * To check amwayaccount is ABO customer. AMWAYBUSINESSNATURE_1 equals ABO Customer.
	 *
	 * @param amwayAccountModel
	 * @return boolean
	 */
	public static boolean isABOCustomer(final AmwayAccountModel amwayAccountModel)
	{
		if (amwayAccountModel != null)
		{
			final AmwayBusinessNature businessNature = amwayAccountModel.getBusinessNature();
			return AmwayBusinessNature.AMWAYBUSINESSNATURE_1.equals(businessNature);
		}
		return false;
	}

	/**
	 * To check customer type as Guest.
	 *
	 * @param userModel
	 * @return boolean
	 */
	public static boolean isGuestCustomer(final UserModel userModel)
	{
		if (userModel instanceof CustomerModel)
		{
			final CustomerModel customerModel = (CustomerModel) userModel;
			return CustomerType.GUEST.equals(customerModel.getType());
		}
		return false;
	}

	/**
	 * To check ABO user price group equals with session price group.
	 *
	 * @return boolean
	 */
	public static boolean isABOPriceGroupInSession()
	{
		final UserPriceGroup sessionPricegroup = (UserPriceGroup) sessionService.getCurrentSession().getAttribute(
				Europe1Constants.PARAMS.UPG);
		if (sessionPricegroup != null && AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP.equals(sessionPricegroup.getCode()))
		{
			return true;
		}
		return false;
	}


	/**
	 * To get post paid limit.
	 *
	 * @param amwayProfileData
	 *           its based on the credit limit and for customers only corporations are allowed to use AR
	 * @return BigDecimal
	 */
	public static BigDecimal getPostPaidLimit(final AmwayProfileResponseData amwayProfileData)
	{
		if (amwayProfileData != null && amwayProfileData.getAccountBalance() != null)
		{
			for (final AccountBalanceData accBal : amwayProfileData.getAccountBalance())
			{
				if (StringUtils.equals(accBal.getInstrumentId(), "0")
						&& StringUtils.equalsIgnoreCase(accBal.getBalanceTypeCd(),
								AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPECREDIT)
						&& isNotBlocked(amwayProfileData))
				{
					return new BigDecimal(accBal.getBalanceAmount()).setScale(2);
				}
			}
		}
		return BigDecimal.ZERO.setScale(2);
	}

	private static boolean isNotBlocked(final AmwayProfileResponseData amwayProfileData)
	{
		for (final BlockPrivDetailsData blockPrivelageData : amwayProfileData.getBlockPrivDetailsData())
		{
			return !StringUtils.containsIgnoreCase(blockPrivelageData.getBlockPrivTypeId().toString(), "BlockAmwayCredit");
		}
		return true;
	}



	/**
	 * To get the AR credit limit.
	 *
	 * @param amwayProfileData
	 * @return BigDecimal
	 */
	public static BigDecimal getARCreditLimit(final AmwayProfileResponseData amwayProfileData)
	{
		if (amwayProfileData != null && amwayProfileData.getAccountBalance() != null)
		{
			for (final AccountBalanceData accBal : amwayProfileData.getAccountBalance())
			{
				if (StringUtils.equals(accBal.getInstrumentId(), "0")
						&& StringUtils.equalsIgnoreCase(accBal.getBalanceTypeCd(),
								AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY))
				{
					return new BigDecimal(accBal.getBalanceAmount()).setScale(2);
				}
			}
		}
		return BigDecimal.ZERO.setScale(2);
	}

}
