package com.amway.core.service;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.exceptions.CalculationException;

import java.io.IOException;
import java.util.Date;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.core.los.data.LosAccountData;
import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.model.AmwayAccountModel;


/**
 * This interface provides all the functionalities for an Amway Account.
 */
public interface AmwayAccountCommerceService
{
	/**
	 * Set the account in session with the given uid.
	 *
	 * @param uid
	 */
	void setCurrentAccount(final String uid);

	/**
	 * Set the account in session for the given customer.
	 *
	 * @param user
	 */
	void setCurrentAccount(final UserModel user);

	/**
	 * Get the account set in session.
	 *
	 * @return Amway Account
	 */
	AmwayAccountModel getCurrentAccount();

	/**
	 * Get the account set in session.
	 *
	 * @return Amway Account Number(aboNumber)
	 */
	String getCurrentAccountNumber();

	/**
	 * upgrade the customer Account to ABO
	 *
	 * @param account
	 */
	void convertCustomerToAbo(AmwayAccountModel account);

	/**
	 * downgrade the ABO account to Customer
	 *
	 * @param account
	 */
	void convertAboToCustomer(AmwayAccountModel account);

	/**
	 * update the current session price group
	 *
	 * @param priceGroup
	 * @throws CalculationException
	 */
	void updatePriceGroupforSession(final String priceGroup) throws CalculationException;

	/**
	 * Set the user price group in session based on the account. This price group will be used in price calculation
	 *
	 * @param account
	 * @param customer
	 */
	void setUPGInSession(final AmwayAccountModel account, CustomerModel customer);

	void setUPGInSession();

	/**
	 * Get the account from the customer, to which it is a primary party (customer).
	 *
	 * @param userModel
	 * @return account
	 */
	AmwayAccountModel getAccountFromUser(UserModel userModel);

	/**
	 * save the logged-in customer info in hybris from getAmwayProfile response
	 *
	 * @param amwayProfileResponseData
	 * @param losAccountDetailResponseData
	 */
	void saveLoggedInCustomerInfo(final AmwayProfileResponseData amwayProfileResponseData,
			final LosAccountDetailResponseData losAccountDetailResponseData);

	/**
	 * save the logged-in customer info in hybris from getAmwayProfile response
	 *
	 * @param response
	 * @param losAccountDetailResponseData
	 * @param customerModel
	 */
	void saveLoggedInCustomerInfo(final AmwayProfileResponseData response,
			final LosAccountDetailResponseData losAccountDetailResponseData, CustomerModel customerModel);

	/**
	 * checks if account{@link AmwayAccountModel} is valid for renewal
	 *
	 * @return boolean
	 */
	boolean isCurrentAccountInactive();

	/**
	 * checks if account{@link AmwayAccountModel} is expired
	 *
	 * @return boolean
	 */
	boolean isAboAccountExpired();

	/**
	 * checks if current account{@link AmwayAccountModel} is expired
	 *
	 * @return boolean
	 */
	boolean isCurrentAccountAboutToExpire();

	/**
	 * Set the user price group in session
	 *
	 * @param pricegroup
	 */
	void setUPGInSession(final String pricegroup);

	/**
	 * Saving expire date
	 *
	 * @param account
	 * @param expiryDate
	 */
	void saveExpiryDate(final AmwayAccountModel account, final Date expiryDate);

	/**
	 * checks if account{@link AmwayAccountModel} is blocked
	 *
	 * @return boolean
	 */
	boolean isAccountBlock();

	/**
	 * checks if account{@link AmwayAccountModel} is blocked
	 *
	 * @return boolean
	 */
	boolean isLegalEntityClientBlock();


	/**
	 * methods to fetch data from MLOS
	 *
	 * @param requestData
	 * @param aboNumber
	 * @return LosAccountData
	 */
	LosAccountData getLosAccount(final LosAccountRequestData requestData, final String aboNumber);

	/**
	 * method to fetch data from MLOS
	 *
	 * @param losAccountRequestData
	 * @return LosAccountDetailResponseData
	 */
	LosAccountDetailResponseData getLosAccountDetails(final LosAccountRequestData losAccountRequestData);

	/**
	 * method to fetch data from MLOS
	 *
	 * @return LosAccountDetailResponseData
	 */
	LosAccountDetailResponseData getBusinessInfo();

	/**
	 * method to fetch data from MLOS
	 *
	 * @param losAccountRequestData
	 * @return LosAccountData
	 */
	LosAccountData getLosProfile(final LosAccountRequestData losAccountRequestData);

	/**
	 * method to fetch data from MLOS
	 *
	 * @return LosAccountResponseData
	 */
	LosAccountResponseData getBusinessInfoAccount();

	/**
	 * To get bonus statement pdf
	 *
	 * @param requestData
	 * @return files
	 * @throws IOException
	 */
	byte[] getBonusStatementPDF(BonusStatementRequestData requestData) throws IOException;

	/**
	 * method to fetch data from MLOS
	 *
	 * @param aboNum
	 * @param serviceItemCode
	 */
	void adjustCustomerBonusDetails(String aboNum, String serviceItemCode);

}
