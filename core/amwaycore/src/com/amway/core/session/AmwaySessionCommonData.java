package com.amway.core.session;

/**
 * POJO class
 */
public class AmwaySessionCommonData
{
	private String clientCntryCd;
	private String salesPlanAff;
	private String partyId;
	private String aboNum;
	private String loggedInPartyId;

	public AmwaySessionCommonData()
	{
		// default constructor
	}

	/**
	 * @param clientCntryCd the clientCntryCd to set
	 */
	public void setClientCntryCd(final String clientCntryCd)
	{
		this.clientCntryCd = clientCntryCd;
	}

	/**
	 * @return clientCntryCd
	 */
	public String getClientCntryCd()
	{
		return clientCntryCd;
	}

	/**
	 * @param salesPlanAff the salesPlanAff to set
	 */
	public void setSalesPlanAff(final String salesPlanAff)
	{
		this.salesPlanAff = salesPlanAff;
	}

	/**
	 * @return salesPlanAff
	 */
	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	/**
	 * @param partyId the partyId to set
	 */
	public void setPartyId(final String partyId)
	{
		this.partyId = partyId;
	}

	/**
	 * @return partyId
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * @param aboNum the aboNum to set
	 */
	public void setAboNum(final String aboNum)
	{
		this.aboNum = aboNum;
	}

	/**
	 * @return aboNum
	 */
	public String getAboNum()
	{
		return aboNum;
	}

	/**
	 * @param loggedInPartyId the loggedInPartyId to set
	 */
	public void setLoggedInPartyId(final String loggedInPartyId)
	{
		this.loggedInPartyId = loggedInPartyId;
	}

	/**
	 * @return loggedInPartyId
	 */
	public String getLoggedInPartyId()
	{
		return loggedInPartyId;
	}

}
