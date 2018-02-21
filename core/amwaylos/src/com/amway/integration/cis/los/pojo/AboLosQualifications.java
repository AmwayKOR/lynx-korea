package com.amway.integration.cis.los.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * AboLosQualifications POJO
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aboLosQualifications", propOrder = { "performanceYear", "bonusPeriod", "qualCode", "silverProducer" })
public class AboLosQualifications
{
	@XmlElement(name = "PerformanceYear")
	protected String performanceYear;
	@XmlElement(name = "BonusPeriod")
	protected String bonusPeriod;
	@XmlElement(name = "QualCode")
	protected String qualCode;
	@XmlElement(name = "SilverProducer")
	protected String silverProducer;

	/**
	 * @return the performanceYear
	 */
	public String getPerformanceYear()
	{
		return performanceYear;
	}

	/**
	 * @param performanceYear the performanceYear to set
	 */
	public void setPerformanceYear(final String performanceYear)
	{
		this.performanceYear = performanceYear;
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
	 * @return the qualCode
	 */
	public String getQualCode()
	{
		return qualCode;
	}

	/**
	 * @param qualCode the qualCode to set
	 */
	public void setQualCode(final String qualCode)
	{
		this.qualCode = qualCode;
	}

	/**
	 * @return the silverProducer
	 */
	public String getSilverProducer()
	{
		return silverProducer;
	}

	/**
	 * @param silverProducer the silverProducer to set
	 */
	public void setSilverProducer(final String silverProducer)
	{
		this.silverProducer = silverProducer;
	}
}
