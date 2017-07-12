/**
 *
 */
package com.amway.integration.cis.los.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p/>
 * Java class for losAccountRequest complex type.
 * <p/>
 * <p/>
 * The following schema fragment specifies the expected content contained within this class.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "losAccountRequest", propOrder = { "requestingAbo", "depth", "bonusPeriod", "abo", "aff", "getVolume",
		"getSponsorStats", "getExtAttributes", "getCustomers" })
public class LosAccountRequest
{
	private String requestingAbo;
	private String depth;
	private String bonusPeriod;
	private String abo;
	private String aff;
	private Boolean getVolume;
	private Boolean getSponsorStats;
	private Boolean getExtAttributes;
	private String bonusPeriodCount;
	private Boolean getQualification;
	private Boolean getCustomers;


	/**
	 * @return the getVolume
	 */
	public Boolean getGetVolume()
	{
		return getVolume;
	}

	/**
	 * @param getVolume the getVolume to set
	 */
	public void setGetVolume(final Boolean getVolume)
	{
		this.getVolume = getVolume;
	}

	/**
	 * @return the getSponsorStats
	 */
	public Boolean getGetSponsorStats()
	{
		return getSponsorStats;
	}

	/**
	 * @param getSponsorStats the getSponsorStats to set
	 */
	public void setGetSponsorStats(final Boolean getSponsorStats)
	{
		this.getSponsorStats = getSponsorStats;
	}

	/**
	 * @return the getExtAttributes
	 */
	public Boolean getGetExtAttributes()
	{
		return getExtAttributes;
	}

	/**
	 * @param getExtAttributes the getExtAttributes to set
	 */
	public void setGetExtAttributes(final Boolean getExtAttributes)
	{
		this.getExtAttributes = getExtAttributes;
	}


	/**
	 * @return the aff
	 */
	public String getAff()
	{
		return aff;
	}

	/**
	 * @param aff the aff to set
	 */
	public void setAff(final String aff)
	{
		this.aff = aff;
	}

	/**
	 * @return the requestingAbo
	 */
	public String getRequestingAbo()
	{
		return requestingAbo;
	}

	/**
	 * @param requestingAbo the requestingAbo to set
	 */
	public void setRequestingAbo(final String requestingAbo)
	{
		this.requestingAbo = requestingAbo;
	}

	/**
	 * @return the depth
	 */
	public String getDepth()
	{
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(final String depth)
	{
		this.depth = depth;
	}

	/**
	 * @return the bonusPeriod
	 */
	public String getBonusPeriod()
	{
		return bonusPeriod;
	}

	/**
	 * @param bonusPeriod the bonusPeriod to set
	 */
	public void setBonusPeriod(final String bonusPeriod)
	{
		this.bonusPeriod = bonusPeriod;
	}

	/**
	 * @return the abo
	 */
	public String getAbo()
	{
		return abo;
	}

	/**
	 * @param abo the abo to set
	 */
	public void setAbo(final String abo)
	{
		this.abo = abo;
	}

	/**
	 * @return the bonusPeriodCount
	 */
	public String getBonusPeriodCount()
	{
		return bonusPeriodCount;
	}

	/**
	 * @param bonusPeriodCount the bonusPeriodCount to set
	 */
	public void setBonusPeriodCount(final String bonusPeriodCount)
	{
		this.bonusPeriodCount = bonusPeriodCount;
	}

	/**
	 * @return the getQualification
	 */
	public Boolean getGetQualification()
	{
		return getQualification;
	}

	/**
	 * @param getQualification the getQualification to set
	 */
	public void setGetQualification(final Boolean getQualification)
	{
		this.getQualification = getQualification;
	}

	/**
	 * @return the getCustomers
	 */
	public Boolean getGetCustomers()
	{
		return getCustomers;
	}

	/**
	 * @param getCustomers the getCustomers to set
	 */
	public void setGetCustomers(final Boolean getCustomers)
	{
		this.getCustomers = getCustomers;
	}

}
