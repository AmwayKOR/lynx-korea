package com.amway.core.cis.los.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "losOrderPeriodRequest", propOrder = { "isoCountryCode", "amwayCountryCode", "orderPeriodCloseTimestamp",
		"requestApp", "requestUser" })
/**
 * LosOrderPeriodRequest POJO
 *
 */ public class LosOrderPeriodRequest
{
	@XmlElement(name = "ISOCountryCode")
	String isoCountryCode;
	@XmlElement(name = "AmwayCountryCode")
	String amwayCountryCode;
	@XmlElement(name = "OrderPeriodCloseTimestamp")
	String orderPeriodCloseTimestamp;
	@XmlElement(name = "RequestApp")
	String requestApp;
	@XmlElement(name = "RequestUser")
	String requestUser;

	/**
	 * @return isoCountryCode
	 */
	public String getIsoCountryCode()
	{
		return isoCountryCode;
	}

	/**
	 * @param isoCountryCode the isoCountryCode to set
	 */
	public void setIsoCountryCode(final String isoCountryCode)
	{
		this.isoCountryCode = isoCountryCode;
	}

	/**
	 * @return amwayCountryCode
	 */
	public String getAmwayCountryCode()
	{
		return amwayCountryCode;
	}

	/**
	 * @param amwayCountryCode the amwayCountryCode to set
	 */
	public void setAmwayCountryCode(final String amwayCountryCode)
	{
		this.amwayCountryCode = amwayCountryCode;
	}

	/**
	 * @return orderPeriodCloseTimestamp
	 */
	public String getOrderPeriodCloseTimestamp()
	{
		return orderPeriodCloseTimestamp;
	}

	/**
	 * @param orderPeriodCloseTimestamp the orderPeriodCloseTimestamp to set
	 */
	public void setOrderPeriodCloseTimestamp(final String orderPeriodCloseTimestamp)
	{
		this.orderPeriodCloseTimestamp = orderPeriodCloseTimestamp;
	}

	/**
	 * @return requestApp
	 */
	public String getRequestApp()
	{
		return requestApp;
	}

	/**
	 * @param requestApp the requestApp to set
	 */
	public void setRequestApp(final String requestApp)
	{
		this.requestApp = requestApp;
	}

	/**
	 * @return requestUser
	 */
	public String getRequestUser()
	{
		return requestUser;
	}

	/**
	 * @param requestUser the requestUser to set
	 */
	public void setRequestUser(final String requestUser)
	{
		this.requestUser = requestUser;
	}


}
