package com.amway.core.account.service;

import com.amway.core.dms.data.PartyNameDetailResponseData;
import com.amway.core.dms.data.PartyProfileAndNameInputRequestData;


/**
 * Interface for Party And Name Service
 */

public interface PartyProfileAndNameService
{
	/**
	 * get Party Profile and Name for ABO Account
	 *
	 * @param partyInputRequestData
	 * @return PartyMasterNameDtlListData
	 */
	PartyNameDetailResponseData getPartyProfileAndNameService(PartyProfileAndNameInputRequestData partyInputRequestData);
}
